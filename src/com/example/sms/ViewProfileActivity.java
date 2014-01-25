package com.example.sms;


import java.util.ArrayList;
import java.util.List;

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

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.deemsysinc.sms.R;

public class ViewProfileActivity extends Activity{
	
      JSONArray board = null;
     
      String fname,uname,proname,email,city,gender,educat,t1,t2,t3,age,medical,mobile,group,time1,time2,time3,timeformat1,timeformat2,timeformat3;
      private static String urlD = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=participantSelect"; 
    /*  private static String urlD = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=participantSelect"; */
      String sex;
      private static final String TAG_SRESD= "serviceresponse";
      
      private static final String TAG_UNAME = "username";
      public ProgressDialog cDialog;
      Context context=this;
      private static final String TAG_FNAME = "firstname";
      private static final String TAG_MOBNUM = "mobilenum";
      private static final String TAG_GENDER = "gender";
      private static final String TAG_CITY = "city";
      private static final String TAG_AGE = "age";
      private static final String TAG_EMAIL = "email";
      private static final String TAG_EDUCAT = "education";
      private static final String TAG_MEDICAL = "medical";
      private static final String TAG_GROUP = "group";
      private static final String TAG_TIME1 = "time1";
      private static final String TAG_TIME2 = "time2";
      private static final String TAG_TIME3 = "time3";
      private static final String TAG_PRONAME = "providername";
      private static final String TAG_AMPM1= "time1format";
      private static final String TAG_AMPM2 = "time2format";
      private static final String TAG_AMPM3 = "time3format";
      
      private static final String TAG_PROINFO = "Patient info";
      public static ArrayList<String> participantview= new ArrayList<String>();
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
           
	        setContentView(R.layout.viewprofile);
	        ActionBar actbar=getActionBar();
	        new ViewProfile().execute();
	        actbar.show();
	    	getActionBar().setDisplayHomeAsUpEnabled(true);
	     
	        Button editpro=(Button)findViewById(R.id.edit);
	       editpro.setOnClickListener(new View.OnClickListener() {
		       	 
	            public void onClick(View arg0) {
	            	Intent sigout=new Intent(getApplicationContext(),EditProfileActivity.class);
	    			startActivity(sigout); 
	    			
	            }
	        });
	 
}
	 class ViewProfile extends AsyncTask<String, String, String> {
	     	@Override
	          protected void onPreExecute() {
	              super.onPreExecute();
	              cDialog = new ProgressDialog(ViewProfileActivity.this);
	              cDialog.setMessage("Fetching Details");
	              cDialog.setIndeterminate(false);
	              cDialog.setCancelable(false);
	              cDialog.show();
	              
	          }

	  		@Override
	  		protected String doInBackground(String... args) {
	  		
	  		     String uId = LoginActivity.proInfo.get(0);
	  		   
	               List<NameValuePair> paramsD = new ArrayList<NameValuePair>();
	               paramsD.add(new BasicNameValuePair("loginid", uId));
	             
	               JsonParser jDash = new JsonParser();
	               JSONObject jsonD = jDash.makeHttpRequest(urlD,"POST", paramsD);
	               
	               
	               Log.i("tagconvertstr1", "["+jsonD+"]");
	               try
		                {
	            	   if(jsonD!=null)
	            	   {
		                	JSONObject c = jsonD.getJSONObject(TAG_SRESD);
		                	Log.i("tagconvertstr", "["+c+"]");
		                	
		                
		                	board = c.getJSONArray(TAG_PROINFO);
		                	Log.i("tagconvertstr1", "["+board+"]");
		                	
		                	for(int i=0;i<board.length();i++)
		                	{
		                	
		                		JSONObject c1 = board.getJSONObject(i);
		                		JSONObject c2 = c1.getJSONObject(TAG_SRESD);
		                	
			                	
			                	fname= c2.getString(TAG_FNAME);
			                	 proname = c2.getString(TAG_PRONAME);
			                	 uname = c2.getString(TAG_UNAME);
			                	 email = c2.getString(TAG_EMAIL);
			                	 city = c2.getString(TAG_CITY);
			                	 gender = c2.getString(TAG_GENDER);
			                	 educat = c2.getString(TAG_EDUCAT);
			                	 group  = c2.getString(TAG_GROUP);
			                	 t1 = c2.getString(TAG_TIME1);
			                	 t2 = c2.getString(TAG_TIME2);
			                     t3 = c2.getString(TAG_TIME3);
			                	 age = c2.getString(TAG_AGE);
			                	 medical = c2.getString(TAG_MEDICAL);
			                     mobile= c2.getString(TAG_MOBNUM);
			                     timeformat1=c2.getString(TAG_AMPM1);
			                     timeformat2=c2.getString(TAG_AMPM2);
			                     timeformat3=c2.getString(TAG_AMPM3);
			                  
			 	             
			 	                  participantview.add(0,fname);
				             	  participantview.add(1,uname);
				             	  participantview.add(2,mobile);
				             	  participantview.add(3,email);
				             	  participantview.add(4,gender);
				             	  participantview.add(5,age);
				             	  participantview.add(6,city);
				             	  participantview.add(7,educat);
				             	  participantview.add(8,medical);
				             	  participantview.add(9,t1);
				             	  participantview.add(10,t2);
				             	  participantview.add(11,t3);
				             	  participantview.add(12,proname);
				             	  participantview.add(13,group);
				             	  participantview.add(14,timeformat1);
				             	  participantview.add(15,timeformat2);
				             	  participantview.add(16,timeformat3);
				             	 
				     		                   
			   }
		                	
		                }  
		            }catch (JSONException e) {
		                e.printStackTrace();
		            }
	               cDialog.dismiss();
	               
	  			return null;
	  		}

	  	
	       protected void onPostExecute(String file_url) {
	    	   if(JsonParser.jss.equals("empty"))
	           {
	        	   System.out.println("json yy null value");
					 final Dialog dialog = new Dialog(context);
					 dialog.setContentView(R.layout.custom_dialog);
					 dialog.setTitle("Failed");
					 dialog.setCancelable(false);
					 dialog.setCanceledOnTouchOutside(false);
					 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
					  txt.setText("Server not Connected!");
					  Button dialogButton = (Button) dialog.findViewById(R.id.release);
					  dialogButton.setOnClickListener(new OnClickListener() {
						  public void onClick(View vd) {
							   dialog.dismiss();
						
						}
					});
					  dialog.show();
					  cDialog.dismiss();
	           }
	    	   else
	    	   {
	    		  showprofile();
	    	   }
	    }
	      	
	      }
	 private void showprofile(){
	       TextView firstname=(TextView)findViewById(R.id.first);
	        String fn=participantview.get(0);
	        firstname.setText(fn);
	      
	      TextView usrname=(TextView)findViewById(R.id.user);
	      String un=participantview.get(1);
	      
	      usrname.setText(un);
	      
	      
	      TextView mobile=(TextView)findViewById(R.id.mob);
	      String mobi=participantview.get(2);
	       
	mobi = "(" + mobi.substring( 0,3 ) + ") " + mobi.substring( 3,6 ) + "-" + mobi.substring( 6,10 );  
	   //  mobi=mobi.substring(0,0)+"("+mobi.substring(4,4)+")"+mobi.substring(9,9)+"-";
	     System.out.println(mobi);
	      mobile.setText(mobi);
	     
	      TextView mailid=(TextView)findViewById(R.id.maill);
	      String maii=participantview.get(3);
	        mailid.setText(maii);
	       
	         
	         
	      TextView gen =(TextView)findViewById(R.id.gender);
	      String gendd=participantview.get(4);
	   System.out.println("Gender::"+gendd);
	      if(gendd.equals("0")){
	    	  sex="male";
	      }
	      else
	    	  sex="female";
	       gen.setText(sex);
	      
	     
	      TextView age=(TextView)findViewById(R.id.agee);
	      String agg=participantview.get(5);
	       age.setText(agg);
	         
	      TextView city=(TextView)findViewById(R.id.cit);
	      String cit=participantview.get(6);
	      city.setText(cit);
	     
	         
	      TextView edu=(TextView)findViewById(R.id.educ);
	      String eddd=participantview.get(7);
	       edu.setText(eddd);
	       System.out.println("education::"+eddd);
	       
	      TextView medi=(TextView)findViewById(R.id.meddet);
	      String medicat=participantview.get(8);
	        medi.setText(medicat);
	      
	        TextView timee1=(TextView)findViewById(R.id.time1);
		      String timee=participantview.get(9);
		      String ampm1=participantview.get(14);
		      System.out.println("timeee value1::"+timee);
		      if(timee.equalsIgnoreCase("null")){
		    	  timee="";
		      }
		     
		      if(TextUtils.isEmpty(timee)){
		    	  timee1.setText("null");
		      }
		    
		      else{
		      timee=timee+ampm1;
		       timee1.setText(timee);
		      }
		         
		      TextView timee2=(TextView)findViewById(R.id.time2);
		      String time2=participantview.get(10);
		      String ampm2=participantview.get(15);
		    
		      if(time2.equalsIgnoreCase("null")){
		    	  time2="";
		      }
		     
		      if(TextUtils.isEmpty(time2)){
		    	  timee2.setText("null");
		      }
		     
		      else{
		      time2=time2+ampm2;
		        timee2.setText(time2);
		      }
		         
		      TextView timee3=(TextView)findViewById(R.id.time3);
		      String time3=participantview.get(11);
		      String ampm=participantview.get(16);
		      if(time3.equalsIgnoreCase("null")){
		    	  time3="";
		      }
		     
		      if(TextUtils.isEmpty(time3)){
		    	  timee3.setText("null");
		      }
		     
		      else{
		      time3=time3+ampm;
		        timee3.setText(time3);
		      }

	      TextView provid=(TextView)findViewById(R.id.provider);
	     
	      String pro=participantview.get(12);
	      provid.setText(pro);
	     
	      
	      TextView providergroup=(TextView)findViewById(R.id.providergroup);
	      String providerg=participantview.get(13);
	     
	       
	      providergroup.setText(providerg);
	     
	 }
	
	  
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	        	 Intent MainActivityIntent = new Intent(ViewProfileActivity.this,DashBoardActivity.class); 
	   	      startActivity(MainActivityIntent);
	           
	      }
	     return true;
	 }
	
	 
 
}