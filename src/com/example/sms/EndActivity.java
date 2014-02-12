package com.example.sms;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
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
import android.widget.TextView;
import com.deemsysinc.sms.R;

/*Activity used to update the database regarding weekly surveys*/
public class EndActivity extends Activity {
	public ProgressDialog pDialog;
		public static final String urlE = "http://www.medsmonit.com/bcreasearchT/Service/participantregister.php?service=weeklyevaluation";
		final Context context=this;
		JSONObject jsonE ;
		@Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.endconver);
		        ActionBar actbar=getActionBar();
		        actbar.show();
		        final Button ended = (Button) findViewById(R.id.theend);
		        final Dialog dialog = new Dialog(context);
		        getActionBar().setDisplayHomeAsUpEnabled(true);
			//	dialog.getWindow();
			 //   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);    
   			 dialog.setContentView(R.layout.custom_dialog);
   			 dialog.setTitle("INFO!");
   			 dialog.setCancelable(false);
			 dialog.setCanceledOnTouchOutside(false);
   			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
   			  txt.setText("You have successfully completed your weekly survey.");
   			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
   			  dialogButton.setOnClickListener(new OnClickListener() {
   				  public void onClick(View vd) {
   					   dialog.dismiss();
				
				}
   			});
   			  dialog.show();
		        ended.setOnClickListener(new View.OnClickListener() {
		 
		            public void onClick(View arg0) {
		            	ended.setEnabled(false);
		            	if(OtherActivity.audioname.equalsIgnoreCase(""))
		            	{
		            	new EndConver().execute();
		            	}
		            	else
		            	{
		            		new EndConver().execute();
		            		new AudioData().execute();
		            		
		            	}
		            	
		            	
		            }
		        });
}
		
		class AudioData extends AsyncTask<String,String,String>{

			@Override
			protected String doInBackground(String... params) {
			
				AudioInsert();
				return null;
			}
			 
		 }
		@Override
		 public boolean onOptionsItemSelected(MenuItem item) {
		     switch (item.getItemId()) {
		         case android.R.id.home:
		           
		        	 finish();
		      }
		     return true;
		 }
		 public void AudioInsert()
		 {
			 HttpURLConnection connection = null;
			 DataOutputStream outputStream = null;
			
			 String pathToOurFile = OtherActivity.audiofilename;
			 String weekid= DashBoardActivity.logid.get(0);
			 String patientid= LoginActivity.proInfo.get(0);
			

			 String urlServer = "http://www.medsmonit.com/bcreasearchT/Service/participantregister.php?service=audioinsert";
			 String lineEnd = "\r\n";
			 String twoHyphens = "--";
			 String boundary =  "*****";

			 int bytesRead, bytesAvailable, bufferSize;
			 byte[] buffer;
			// int maxBufferSize = 1*1024*1024;

			 try
			 {
			 FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile) );

			 URL url = new URL(urlServer);
			 connection = (HttpURLConnection) url.openConnection();

			 // Allow Inputs & Outputs
			 connection.setDoInput(true);
			 connection.setDoOutput(true);
			 connection.setUseCaches(false);

			 // Enable POST method
			 connection.setRequestMethod("POST");

			 connection.setRequestProperty("Connection", "Keep-Alive");
			 connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

			 outputStream = new DataOutputStream( connection.getOutputStream() );
			/* outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			 outputStream.writeBytes("Content-Disposition: form-data; name=\"patientaudio\";filename=\"" + pathToOurFile +"\"" + lineEnd);
			 outputStream.writeBytes(lineEnd);
*/
			 outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			 outputStream.writeBytes("Content-Disposition: form-data; name=\"weeklogid\""+ lineEnd);
			 outputStream.writeBytes(lineEnd);
			 outputStream.writeBytes(weekid);
			 outputStream.writeBytes(lineEnd);
			 
			 outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                 
			 outputStream.writeBytes("Content-Disposition: form-data; name=\"patientid\""+ lineEnd);
			 outputStream.writeBytes(lineEnd);
			 outputStream.writeBytes(patientid);
			 outputStream.writeBytes(lineEnd);
			 outputStream.writeBytes(twoHyphens + boundary + lineEnd);
             
			 outputStream.writeBytes("Content-Disposition: form-data; name=\"patientaudio\";filename=\"" +pathToOurFile+"\"" + lineEnd);
			 outputStream.writeBytes(lineEnd);
			 Log.e("tag","Headers are written");
				
			
			 bytesAvailable = fileInputStream.available();
			 int maxBufferSize1=1024;
			 bufferSize = Math.min(bytesAvailable, maxBufferSize1);
			 buffer = new byte[bufferSize];

			 // Read file
			 bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			 while (bytesRead > 0)
			 {
			 outputStream.write(buffer, 0, bufferSize);
			 bytesAvailable = fileInputStream.available();
			 bufferSize = Math.min(bytesAvailable, maxBufferSize1);
			 bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			 }

			 outputStream.writeBytes(lineEnd);
			 outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			 fileInputStream.close();
			 outputStream.flush();
			 outputStream.close();
			 
			 Log.e("tag","File Sent, Response: "+String.valueOf(connection.getResponseCode()));
             
             InputStream is = connection.getInputStream();
                 
             // retrieve the response from server
             int ch;

             StringBuffer b =new StringBuffer();
             while( ( ch = is.read() ) != -1 ){ b.append( (char)ch ); }
             String s=b.toString();
             Log.i("Response",s);
             outputStream.close();

			 }
			 catch (Exception ex)
			 {
			 //Exception handling
			 }
		 }
		 class EndConver extends AsyncTask<String,String,String>{

			   @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            pDialog = new ProgressDialog(EndActivity.this);
		            pDialog.setMessage("Submitting weekly survey");
		            pDialog.setIndeterminate(false);
		            pDialog.setCancelable(false);
		            pDialog.show();
		           
			         
		        } 
			    
			 @Override
				protected String doInBackground(String... params) {
				
					
					List<NameValuePair> paramsE = new ArrayList<NameValuePair>();
					 String LogId2 = LoginActivity.proInfo.get(0);
				
					// System.out.println("from evaluatin act 0th pos::"+EvaluationActivity.evaluation.get(0));
					// System.out.println("from evaluatin act 1th pos::"+EvaluationActivity.evaluation.get(1));
					    String answ1=EvaluationActivity.evaluation.get(1);
					    Log.i("tagconvertstr", "["+answ1+"]");
					  
					  
					 System.out.println("answer1post"+answ1);
					    String answ2=Questionnaire.questionnaire.get(1);
						 System.out.println("answer2post"+answ2);
					    String answ3=WeeklyendActivity.weekend.get(1);
						 System.out.println("answer3post"+answ3);
					    String weekno1=DashBoardActivity.week.get(0);
						 System.out.println("weeknumber"+weekno1);
			            String weekdate1=DashBoardActivity.datetime.get(0);
			       	 System.out.println("weekdate"+weekdate1);
			            String weeklogid1=DashBoardActivity.logid.get(0);
			       	 System.out.println("weeklogid"+weeklogid1);
			            String count1=EvaluationActivity.count;
			       	 System.out.println("countttt"+count1);
					    paramsE.add(new BasicNameValuePair("loginid", LogId2));
					    paramsE.add(new BasicNameValuePair("answer1", answ1));
					    paramsE.add(new BasicNameValuePair("answer2", answ2));
					    paramsE.add(new BasicNameValuePair("answer3", answ3));
					    paramsE.add(new BasicNameValuePair("weeknum", weekno1));
				     	paramsE.add(new BasicNameValuePair("weekdate", weekdate1));
					    paramsE.add(new BasicNameValuePair("weeklogid", weeklogid1));
					    paramsE.add(new BasicNameValuePair("countcol", count1));
					
					 JsonParser jLogin = new JsonParser();
					
		            jsonE = jLogin.makeHttpRequest(urlE,"POST", paramsE);
		             Log.i("tagconvertstr3", "["+jsonE+"]");
		             
		            
		         	
		             return null;
				}
			 @Override
    		 protected void onPostExecute(String file_url) {
				 if(jsonE!=null){
			      pDialog.dismiss();
				DashBoardActivity.datetime.clear();
				EvaluationActivity.continuouscount.clear();
				EvaluationActivity.counting.clear();
				EvaluationActivity.countty=0;
				WeeklyendActivity.weekend.clear();
				OtherActivity.audiofilename="";
				Questionnaire.questionnaire.clear();
				EvaluationActivity.evaluation.clear();
				final Dialog dialog = new Dialog(context);
				 dialog.setContentView(R.layout.custom_dialog);
	   			 dialog.setTitle("INFO!");
	   			 dialog.setCancelable(false);
				 dialog.setCanceledOnTouchOutside(false);
	   			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
	   			  txt.setText("Your weekly survey has been submitted successfully.");
	   			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
	   			  dialogButton.setOnClickListener(new OnClickListener() {
	   				  public void onClick(View vd) {
	   					 Intent ending=new Intent(getApplicationContext(),DashBoardActivity.class);
	 	    			startActivity(ending); 
	   					   dialog.dismiss();
					
					}
	   			});
	   			  dialog.show();
				
    		 }
				 else{
					 final Dialog dialog = new Dialog(context);
					 dialog.setContentView(R.layout.custom_dialog);
		   			 dialog.setTitle("INFO!");
		   			 dialog.setCancelable(false);
					 dialog.setCanceledOnTouchOutside(false);
		   			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		   			  txt.setText("Server not connected.");
		   			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
		   			  dialogButton.setOnClickListener(new OnClickListener() {
		   				  public void onClick(View vd) {
		   					 Intent ending=new Intent(getApplicationContext(),DashBoardActivity.class);
		 	    			startActivity(ending); 
		   					   dialog.dismiss();
						
						}
		   			});
		   			  dialog.show();
					 
				 }
			 }
			
		 }
}
