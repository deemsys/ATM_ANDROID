package com.example.sms;


import java.io.File;
import java.util.ArrayList;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.deemsysinc.sms.BuildConfig;
import com.deemsysinc.sms.R;
public class WeeklyendActivity extends Activity {
	final Context context=this;
	File audiofilepath;
	  String ack;
	  ConnectionDetector cd;
	  Boolean isInternetPresent = false;
	  String message;
      public ProgressDialog pDialog;
	  String sender_mail;
	 public static ArrayList<String> weekend = new ArrayList<String>();
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.weeklyend);
	        ActionBar actbar=getActionBar();
	        actbar.show();
	        TextView textt = (TextView) findViewById(R.id.end);
	        String texty=textt.getText().toString();
	        weekend.add(0,texty);
	   	 cd = new ConnectionDetector(getApplicationContext());
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        String patty=getIntent().getStringExtra("mnt/sdcard-text.pcm");
	        
	        System.out.println("paathhhy frfom ::"+patty);
	        Button sendans = (Button) findViewById(R.id.send);
	        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
	        final RadioButton yes = (RadioButton) findViewById(R.id.yes);
	        final RadioButton no = (RadioButton) findViewById(R.id.no);
	      
	        sendans.setOnClickListener(new View.OnClickListener() {
	       	 
	            public void onClick(View arg0) {
	            	 if(!yes.isChecked()&&!no.isChecked()){
	            		 final Dialog dialog = new Dialog(context);
	           			 dialog.setContentView(R.layout.custom_dialog);
	           			 dialog.setTitle("Failed");
	           			 dialog.setCancelable(false);
	        			 dialog.setCanceledOnTouchOutside(false);
	           			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
	           			  txt.setText("Select your option!");
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
		            
		                weekend.add(1,selectedValue);
		                
		                 	if(selectedValue.equals("YES")){
		                 		isInternetPresent = cd.isConnectingToInternet();
		           			 if (isInternetPresent){
		                 		sendmail();
		           			 }
		           			 else{
		           				 final Dialog dialog = new Dialog(context);
		        			     
		               			 dialog.setContentView(R.layout.custom_dialog);
		               			 dialog.setTitle("Login Failed");
		               			 dialog.setCancelable(false);
		            			 dialog.setCanceledOnTouchOutside(false);
		               			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		               			  txt.setText("No Network Connection!");
		               			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
		               			  dialogButton.setOnClickListener(new OnClickListener() {
		               				  public void onClick(View vd) {
		               					   dialog.dismiss();
		            				
		            				}
		               			});
		               			  dialog.show(); 
		           			 }
	        		                                                     
	                         }
	            	else{
	            Intent ennn=new Intent(getApplicationContext(),EndActivity.class);
	    			startActivity(ennn);    
	            	}
	            }
	            }
	        });
	        
}
	 private void sendmail(){
		 new SendEmailAsyncTask().execute();
		 
		 Intent sigout=new Intent(getApplicationContext(),EndActivity.class);
			startActivity(sigout); 
	 }
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	            finish();
	      }
	     return true;
	 }
	 
	 class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
		    GMailSender sender = new GMailSender("learnguild@gmail.com", "deemsys@123");
		    
		    public SendEmailAsyncTask() {
		    	}
		    @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	      
	           
		         
	        } 
		    

		    @Override
		    protected Boolean doInBackground(Void... params) {
		        if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
		        try {
		        	String participantname =LoginActivity.participant.get(0);
		        	String providername =LoginActivity.provider.get(1);
				    String weekno=DashBoardActivity.week.get(0);
				  System.out.println("participant name::"+participantname);
				  System.out.println("provider name::"+providername);
				  System.out.println("week number::"+weekno);
			
				  
				  
				    String question1=EvaluationActivity.evaluation.get(0);
				    String answer1=EvaluationActivity.evaluation.get(1);
				    System.out.println("question one::"+question1);
					  System.out.println("answer one::"+answer1);
				    String question2=Questionnaire.questionnaire.get(0);
				    String answer2=Questionnaire.questionnaire.get(1);
				    System.out.println("question ntwo::"+question2);
					  System.out.println("answer two::"+answer2);
				    String sende_mail=LoginActivity.participant.get(3);
					// String sende_mail="thendralthenmozhis@gmail.com@gmail.com";
					 // sender_mail="senthamilthendral@gmail.com";
				  sender_mail=LoginActivity.provider.get(3);
				    
				    String q1=WeeklyendActivity.weekend.get(0);
				    String a1=WeeklyendActivity.weekend.get(1);
					 
					
					  System.out.println("sender mailone::"+sende_mail);
					  System.out.println("sender mail two::"+sender_mail);
					  System.out.println("weekly ques::"+q1);
					  System.out.println("weekly ans::"+a1);
				    
			         message = String.format("Hi " +"%s,\n\n" +"Welcome To Adhere To Medication!\n\n"+
					    		"Weekly Message Details for the Adhere To Medication.\n\n"+"Participant Name : "+"%s\n\n"+"Week Number : "+"%s\n\n"
			        		  +"%s : "+"%s\n\n"+"%s "+"%s\n\n"+"%s "+"%s",providername,participantname,weekno,question1,answer1,question2,answer2,q1,a1);
		           
			      ack = String.format("Hi "+"%s,\n\n" +"Welcome To Adhere To Medication!\n\n"+"Your Weekly Message Details has been Submitted to your respective Provider Successfully.\n\n"+"Keep on Answering your Weekly Assessments.\n\n"
				    		+"Thank you!",participantname);
		        
		        	audiofilepath=OtherActivity.file;
		        	System.out.print(audiofilepath);
		        	  sender.sendMail("Adhere To Medication Acknowledgement",ack, "learnguild@gmail.com",sende_mail)  ;

			         sender.sendMail("Adhere To Medication Participant Report",message, "learnguild@gmail.com",sender_mail,audiofilepath)  ;
			       //  sender.sendMail("Adhere To Medication Acknowledgement",ack, "learnguild@gmail.com",sende_mail)  ;
			        
			         
			         return true;
		        } catch (AuthenticationFailedException e) {
		            Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
		            e.printStackTrace();
		            return false;
		        } catch (MessagingException e) {
		        	  try {
						sender.sendMail("Adhere To Medication Participant Report",message, "learnguild@gmail.com",sender_mail);
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            e.printStackTrace();
		            return false;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return false;
		        }
		    }
		
	    
}
}