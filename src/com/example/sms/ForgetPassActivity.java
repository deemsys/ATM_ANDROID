package com.example.sms;

import java.util.ArrayList;
import java.util.List;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.deemsysinc.sms.BuildConfig;
import com.deemsysinc.sms.R;

public class ForgetPassActivity extends Activity {
	
	EditText mail;
	Button sub;
	
	final Context context=this;
	public ProgressDialog pDialog;
	public static String mailid;
    String fpass;
	private static String urlF = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=passwordSelect";
	private static final String TAG_SRESF= "serviceresponse";
  
    private static final String TAG_SUCCESS = "success";
   
    private static final String TAG_PATIENTPASS = "Patient password";
    
    private static final String TAG_USERPASS = "userpassword"; 
    JSONArray forget = null;
    String success;
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.forgetpass);
	        ActionBar actbar=getActionBar();
	        actbar.show();
	        mail = (EditText)findViewById(R.id.emailveri);
	        
	        mail.addTextChangedListener(new TextWatcher() {
	            
	            public void afterTextChanged(Editable s) {
	             //   Validation.isEmailAddress(mail, true);
	            }
	            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	            public void onTextChanged(CharSequence s, int start, int before, int count){}
	        });
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	       
	        sub=(Button)findViewById(R.id.submit);
	        sub.setOnClickListener(new View.OnClickListener() {
	             
	         	public void onClick(View v) {
	         		
	         		 if(checkValidation()){
	         			// sub.setEnabled(false);
	         			 submitForm();
	         		 }
	         	
	         	else{
	         		final Dialog dialog = new Dialog(context);
	      			 dialog.setContentView(R.layout.custom_dialog);
	      			 dialog.setTitle("INFO!");
	      			 dialog.setCancelable(false);
	    			 dialog.setCanceledOnTouchOutside(false);
	      			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
	      			  txt.setText("Enter valid  email id.");
	      			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
	      			  dialogButton.setOnClickListener(new OnClickListener() {
	      				  public void onClick(View vd) {
	      					   dialog.dismiss();
	   				
	   				}
	      			});
	      			  dialog.show();
	         	}
	            	
	         	}  
	         	});
	        }
	        
	
private void submitForm() {
	
	new ForgetPass().execute();
	
	
}
	 private void sendmail(String pass){
		  
		  new SendEmailAsyncTask().execute();
	  }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	            
	             Intent intent = new Intent(this, LoginActivity.class);
	             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent);
	             return true;
	         default:
	            return super.onOptionsItemSelected(item);
	
	     }
	     
	 } 
	 @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
	                                                        INPUT_METHOD_SERVICE);
	        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	        return true;
	    }
	 class ForgetPass extends AsyncTask<String, String, String> {
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(ForgetPassActivity.this);
	            pDialog.setMessage("Sending password to mail");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... params) {
			
			mailid = mail.getText().toString();
		    
		    String  mId = ForgetPassActivity.mailid;
		  
          List<NameValuePair> paramsF = new ArrayList<NameValuePair>();
          
          paramsF.add(new BasicNameValuePair("emailid", mId));
         
        
          JsonParser jLogin = new JsonParser();
          JSONObject jsonFP = jLogin.makeHttpRequest(urlF,"POST", paramsF);
      
          Log.i("tagconvertstr1", "["+jsonFP+"]");
          
          try
          {
        	  if(jsonFP!=null){
         	 JSONObject jUser = jsonFP.getJSONObject(TAG_SRESF);
         	
         	success= jUser.getString(TAG_SUCCESS);
         	
          
         	 forget = jUser.getJSONArray(TAG_PATIENTPASS);
         	 for (int i=0;i<forget.length();i++)
         	 {
         		
         		 JSONObject pass1 = forget.getJSONObject(i);
         		 JSONObject p2 = pass1.getJSONObject(TAG_SRESF);
         		fpass = p2.getString(TAG_USERPASS);
         		
         		 
          	 }
         	
        	  }
         	 
          }
          catch(JSONException e)
          {
         	 e.printStackTrace();
         	 
          }
			
			
			return null;
		}
		protected void onPostExecute(String file_url) {
         // sub.setEnabled(true);
            pDialog.dismiss();
            if(JsonParser.jss.equalsIgnoreCase("empty")){
            	 final Dialog dialog = new Dialog(context);
			     
       			 dialog.setContentView(R.layout.custom_dialog);
       			 dialog.setTitle("Info!");
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
            }
            else if(success.equals("Yes")){
            	sendmail(fpass);
            final Dialog dialog1 = new Dialog(context);
            dialog1.setContentView(R.layout.custom_dialog);
            dialog1.setTitle("Info!");
            dialog1.setCancelable(false);
			 dialog1.setCanceledOnTouchOutside(false);
            			 TextView txt = (TextView) dialog1.findViewById(R.id.errorlog);
            			  txt.setText("Your password has been sent to your email id");
            			  Button dialogButton = (Button) dialog1.findViewById(R.id.release);
            			  dialogButton.setOnClickListener(new OnClickListener() {
            				  public void onClick(View vd) {
            					  mail.setText("");
            					   dialog1.dismiss();
         				
         				}
            			});
            			  dialog1.show();
          
            
        
            }
		
		else{
			 final Dialog dialog3 = new Dialog(context);
	            dialog3.setContentView(R.layout.custom_dialog);
	            dialog3.setTitle("Info!");
	            dialog3.setCancelable(false);
   			 dialog3.setCanceledOnTouchOutside(false);
	            			 TextView txt = (TextView) dialog3.findViewById(R.id.errorlog);
	            			  txt.setText("Invalid Mail-id!");
	            			  Button dialogButton = (Button) dialog3.findViewById(R.id.release);
	            			  dialogButton.setOnClickListener(new OnClickListener() {
	            				  public void onClick(View vd) {
	            					  mail.setText("");
	            					   dialog3.dismiss();
	         				
	         				}
	            			});
					  dialog3.show();
		}
		}  
		 
	 }
	 class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
		    GMailSender sender = new GMailSender("learnguild@gmail.com", "deemsys@123");
		   
		    
		    public SendEmailAsyncTask() {
		    	}
		     

		    @Override
		    protected Boolean doInBackground(Void... params) {
		        if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
		        try {
		        	 
			        
			          String message = String.format("Hi,"+"\n\n"+"Your password is : %s"+"\n\n"+"From Adhere To Medication Team.",fpass);
		           sender.sendMail("Adhere To Medication Password",message, "learnguild@gmail.com",mailid )  ;
		          System.out.println ( "message is"+message);
		            return true;
		        } catch (AuthenticationFailedException e) {
		            Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
		            e.printStackTrace();
		            return false;
		        } catch (MessagingException e) {
		           
		            e.printStackTrace();
		            return false;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return false;
		        }
		    }
		}

	 private boolean checkValidation() {
		 boolean ret = true;
		  if (!Validation.isEmailAddress(mail, true)) 
			  ret = false;
		  return ret;
	 }
	 
}
