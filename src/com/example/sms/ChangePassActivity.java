package com.example.sms;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;




import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.deemsysinc.sms.R;

/* Activity used to change participants Password*/
public class ChangePassActivity extends Activity {
	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	EditText oldpass,newpass,confirmpass;
	Button change;
	public static String newpw,old,confirm;
	public ProgressDialog pDialog;
	String successL;
	public static String olld;
	   private static final String TAG_SREST= "serviceresponse";
	    
	      private static final String TAG_SUCCESS1 = "success";
	private static String urlCH = "http://www.medsmonit.com/bcreasearchT/Service/genericUpdate.php?service=passwordUpdate";
 public static ArrayList<String> userPass = new ArrayList<String>();
	  String userpass[];
	 final Context context=this;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        ActionBar actbar=getActionBar();
	        actbar.show();
	        olld=LoginActivity.password;
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	     setContentView(R.layout.changepassword);
         changevalid();
}
	  private void changevalid() {
	        oldpass = (EditText) findViewById(R.id.oldpassword);
	        
	       oldpass.addTextChangedListener(new TextWatcher() {
	            public void afterTextChanged(Editable s) {
	               // Validation.hasTextOld(oldpass);
	            }
	            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	            public void onTextChanged(CharSequence s, int start, int before, int count){}
	        });

	       newpass  = (EditText) findViewById(R.id.newpassword);
	        
	       newpass.addTextChangedListener(new TextWatcher() {
	            public void afterTextChanged(Editable s) {
	              //  Validation.hasTextNew(newpass);
	            }
	            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	            public void onTextChanged(CharSequence s, int start, int before, int count){}
	        });
	        
	      
	        
	        confirmpass = (EditText) findViewById(R.id.confirmpassword);
	        confirmpass.addTextChangedListener(new TextWatcher() {
	         
	            public void afterTextChanged(Editable s) {
	              //  Validation.hasTextConfirm(confirmpass);
	            }
	            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	            public void onTextChanged(CharSequence s, int start, int before, int count){}
	        });


	  change= (Button) findViewById(R.id.update);
      change.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          	change.setEnabled(false);
            cd = new ConnectionDetector(getApplicationContext());
	        isInternetPresent = cd.isConnectingToInternet();
			 if (isInternetPresent) {
              if ( checkValidation () ){
                  submitForm();
              }
			 
              else{
              	final Dialog dialog = new Dialog(context);
    			 dialog.setContentView(R.layout.custom_dialog);
    			 dialog.setTitle("INFO!");
    			 dialog.setCancelable(false);
    			 dialog.setCanceledOnTouchOutside(false);
    			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
    			  txt.setText("Enter valid details.");
    			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
    			  dialogButton.setOnClickListener(new OnClickListener() {
    				  public void onClick(View vd) {
    					  change.setEnabled(true);
    					   dialog.dismiss();
 				
 				}
    			});
    			  dialog.show();
 			  }
          }
          else{
        		final Dialog dialog = new Dialog(context);
   			 dialog.setContentView(R.layout.custom_dialog);
   			 dialog.setTitle("INFO!");
   			 dialog.setCancelable(false);
   			 dialog.setCanceledOnTouchOutside(false);
   			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
   			  txt.setText("No network connection.");
   			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
   			  dialogButton.setOnClickListener(new OnClickListener() {
   				  public void onClick(View vd) {
   					  change.setEnabled(true);
   					   dialog.dismiss();
				
				}
   			});
   			  dialog.show();
          }
          }  });
	  }
      private void submitForm() {
    	    

     	  old=oldpass.getText().toString().trim();
     	
     	 newpw=newpass.getText().toString().trim();
     	 
     	 confirm=confirmpass.getText().toString().trim();
     	
            userPass.add(old); 
            userPass.add(newpw); 
            userPass.add(confirm); 
            
            if(old.equals(olld)){
         
                 new ChangePass().execute();
            }
            else
            {
            	final Dialog dialog = new Dialog(context);
     			 dialog.setContentView(R.layout.custom_dialog);
     			 dialog.setTitle("INFO!");
     			 dialog.setCancelable(false);
    			 dialog.setCanceledOnTouchOutside(false);
     			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
     			  txt.setText("Invalid old password.");
     			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
     			  dialogButton.setOnClickListener(new OnClickListener() {
     				  public void onClick(View vd) {
     					oldpass.setText("");
     					 change.setEnabled(true);
     					   dialog.dismiss();
   				
   				}
     			});
     			  dialog.show();
            }
 			  }
         
      private boolean checkValidation() {
          boolean ret = true;
          if (!Validation.hasTextold(oldpass)) ret = false;
        //  Validation.hasTextConfirm(confirmpass);
        	  if(!Validation.isEqualpass(newpass,confirmpass,true))
        		  ret=false; 
             
         

          return ret;
      }
              
      @Override
		 public boolean onOptionsItemSelected(MenuItem item) {
		     switch (item.getItemId()) {
		         case android.R.id.home:
		        	 Intent intentSignUP=new Intent(getApplicationContext(),SettingsActivity.class);
		    			startActivity(intentSignUP);  
		    finish();
		     }
		     return true;
		 } 
	 
      
      class ChangePass extends AsyncTask<String, String, String> {
    	  
 		@Override
 	        protected void onPreExecute() {
 	            super.onPreExecute();
 	            pDialog = new ProgressDialog(ChangePassActivity.this);
 	            pDialog.setMessage("Changing password");
 	            pDialog.setIndeterminate(false);
 	            pDialog.setCancelable(false);
 	            pDialog.show();
 	        }
 		@Override
 		protected String doInBackground(String... params) {
 			
 			
 			
 			 String logId = LoginActivity.proInfo.get(0);
 	 		
 			
 		    String  newpass1 = ChangePassActivity.newpw;
 		  
 		
 		  System.out.println("password in post execute:::olld");
 		  String  oldpass1 = ChangePassActivity.old;
 		  
 		
       	 
       	   
           List<NameValuePair> paramsC = new ArrayList<NameValuePair>();
           
           paramsC.add(new BasicNameValuePair("newpassword", newpass1));
         
           paramsC.add(new BasicNameValuePair("oldpassword", oldpass1));
           
           paramsC.add(new BasicNameValuePair("loginid",logId ));
           
           
           JsonParser jChange = new JsonParser();
           JSONObject jsonCP = jChange.makeHttpRequest(urlCH,"POST", paramsC);
           
            
           Log.i("tagconvertstr12", "["+jsonCP+"]");
 		
           try
           {
        	   if(jsonCP!=null){
          	 JSONObject jUser = jsonCP.getJSONObject(TAG_SREST);
         
         
          	 successL = jUser.getString(TAG_SUCCESS1);
          	
        	   }
          
           }
           catch(JSONException e)
           {
          	 e.printStackTrace();
          	 
           }
         
        return null;
 		}
 			
 	

     protected void onPostExecute(String file_url) {
  	 
        if(JsonParser.jss.equalsIgnoreCase("empty")){
        	 final Dialog dialog = new Dialog(context);
		     
   			 dialog.setContentView(R.layout.custom_dialog);
   			 dialog.setTitle("INFO!");
   			 dialog.setCancelable(false);
			 dialog.setCanceledOnTouchOutside(false);
   			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
   			  txt.setText("No network connection.");
   			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
   			  dialogButton.setOnClickListener(new OnClickListener() {
   				  public void onClick(View vd) {
   					   dialog.dismiss();
				
				}
   			});
   			  dialog.show(); 
   			  pDialog.dismiss();
        }
        else if(successL.equalsIgnoreCase("Yes"))
         {
        	 userPass.clear();
        	 olld=newpw;
        	 final Dialog dialog = new Dialog(context);
  			 dialog.setContentView(R.layout.custom_dialog);
  			 dialog.setTitle("INFO!");
  			 dialog.setCancelable(false);
			 dialog.setCanceledOnTouchOutside(false);
  			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
  			  txt.setText("Your password has been updated successfully.");
  			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
  			  dialogButton.setOnClickListener(new OnClickListener() {
  				  public void onClick(View vd) {
  					  reset();
  					 change.setEnabled(true);
  					   dialog.dismiss();
				
				}
  			});
  			  dialog.show();
  			  pDialog.dismiss();
			  
      	
         }
   
         else{
        	 final Dialog dialog = new Dialog(context);
  			 dialog.setContentView(R.layout.custom_dialog);
  			 dialog.setTitle("INFO!");
  			 dialog.setCancelable(false);
			 dialog.setCanceledOnTouchOutside(false);
  			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
  			  txt.setText("Your password does not updated successfully.");
  			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
  			  dialogButton.setOnClickListener(new OnClickListener() {
  				  public void onClick(View vd) {
  					   dialog.dismiss();
  					 change.setEnabled(true);
				
				}
  			});
  			  dialog.show();
  			  pDialog.dismiss();
			  }
       
     }
       
        
     }
 	    public void reset(){
 	    	oldpass.setText("");
 	    	newpass.setText("");
 	    	confirmpass.setText("");
 	    }
 	    
     }
	  
	  

	  