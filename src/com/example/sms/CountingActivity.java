package com.example.sms;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.deemsysinc.sms.BuildConfig;
import com.deemsysinc.sms.R;


/* Activity used to count number of weeks participant given answer less than 6 in weekly survey */

public class CountingActivity extends Activity {
	EditText anb;
	String str;
	int valued;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.count);
	        final Context context=this;
	        ActionBar actbar=getActionBar();
	        actbar.show();
	        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.radiogro);
	        final RadioButton yes = (RadioButton) findViewById(R.id.yes1);
	        final RadioButton no = (RadioButton) findViewById(R.id.no1);
	    
		      
	        Button next=(Button)findViewById(R.id.next);  
	        next.setOnClickListener(new View.OnClickListener() {
		       	 
	            public void onClick(View arg0) {
	            	 if(!yes.isChecked()&&!no.isChecked()){
	            		 final Dialog dialog = new Dialog(context);
	           			 dialog.setContentView(R.layout.custom_dialog);
	           			 dialog.setTitle("Oops!");
	           			
	           			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
	           			  txt.setText("Select your option");
	           			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
	           			  dialogButton.setOnClickListener(new OnClickListener() {
	           				  public void onClick(View vd) {
	           					   dialog.dismiss();
	        				
	        				}
	           			});
	           			  dialog.show();
	            	 }
	            	 else{
		         		int id = rg1.getCheckedRadioButtonId();
		                RadioButton radioButton = (RadioButton) findViewById(id);
		                String selectedValue = radioButton.getText().toString();
		               
		                 	if(selectedValue.equalsIgnoreCase("No")){
	            		Intent sigout=new Intent(getApplicationContext(),Questionnaire.class);
		    			startActivity(sigout);                                                        
	                         }
	            	else{
	            		
       		
       			
       		
	            		sendmail();
	            
	            	Intent ennn=new Intent(getApplicationContext(),Questionnaire.class);
	    			startActivity(ennn);    
	            	}
	            }
	            }
	        });
	        
	            	
	            	
	             
}
	 private void sendmail(){
		 new SendEmailAsyncTask().execute();
		 
	 }
	
	 class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
		    GMailSender sender = new GMailSender("learnguild@gmail.com", "deemsys@123");
		    
		    public SendEmailAsyncTask() {
		    	}
		     

		    @Override
		    protected Boolean doInBackground(Void... params) {
		        if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
		        try {
		        	String participantname =LoginActivity.participant.get(0);
		        	String providername=LoginActivity.provider.get(1);
		            String maii=LoginActivity.provider.get(3);
				    
		      // String maii="senthamilthendral@gmail.com";
		            String message = String.format("Hi " +"%s,\n\n" +"Welcome To Adhere To Medication.\n\n"+" The participant "+"%s "+"under your treatment had misses his/her medications frequently thrice in a row entering values less than 5.\n\n Also he wants the admin to contact him.\n\n Thank you!"
				    		,providername,participantname);
			        sender.sendMail("Adhere To Medication",message, "learnguild@gmail.com",maii)  ;
			     
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
		    protected void onPostExecute(String file_url) {
		     
		                                                            
		    	Intent sigout=new Intent(getApplicationContext(),Questionnaire.class);
    			startActivity(sigout);    	
		}	
	 }
}