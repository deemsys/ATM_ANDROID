package com.example.sms;


import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.mail.AuthenticationFailedException;

import javax.mail.MessagingException;

import javax.mail.Session;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.deemsysinc.sms.BuildConfig;
import com.deemsysinc.sms.R;



public class RegisterActivity extends Activity {
	 Boolean isInternetPresent = false;
	 SharedPreferences sharedpreferences,sharedpreferences2;
		ConnectionDetector cd;
	JsonParser jsonParser = new JsonParser();
	  public ProgressDialog pDialog,cDialog;
	  String successstag;
	  public static int spinneosition;
	 public static int  emptygroup; 
	  Button submit;
	  JSONObject jArray;
	  public static String prname = null;
	  String successiden,usrmsg,mnum;
	  public static String prname123;
	  public static String pgrname = null;
	int tme1,tme2,tme3,meri1,meri2,meri3;
	  public static  String pgrname123 = null;
	  private static final String TAG_SUCCESS = "success";
      private static final String TAG_MESSAGE = "message";
      private static final String TAG_MESSAGEU = "message";
    private static String url = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=simpleselect";
     // private static String url1 = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=groupSelect";
      private static String urlPG = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=getGroups";
      public static String urlTWS = "http://www.medsmonit.com/bcreasearchT/Service/twilioservice.php?service=sendmessage";
    /*  private static String url = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=simpleselect";
      // private static String url1 = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=groupSelect";
       private static String urlPG = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=getGroups";
       public static String urlTWS = "http://192.168.1.200/bcreasearchT/Service/twilioservice.php?service=sendmessage";*/
      
      public static String password=null;
      public static String group = null;
      
	//public static Object ;
      
      private static final String TAG_SRESR= "serviceresponse";
      private static final String TAG_SUCCESSR = "success";
      
      public static ArrayList<String> prgrouplist= new ArrayList<String>();
      public static ArrayList<String> providerlist= new ArrayList<String>();
      public static ArrayList<String> prgridlist= new ArrayList<String>();
	public static String imc_met = null;
    public static int aG ;
    public static int aGID;
	Session session=null;
    Context context=this;
    private static final String TAG_PROLIST = "Providers List";
    private static final String TAG_PROID = "Provider id";
    private static final String TAG_SRES= "serviceresponse";
    private static final String TAG_SNAME = "servicename";
    private static final String TAG_NAME = "Provider username";
    
    
    
    
   
    private static final String TAG_SUCCESS1 = "success";
  
    //private static final String TAG_EMAIL = "email";
    int seconds;
    JSONArray user = null;
    JSONArray user1 =null;
    Boolean temp = false;
    Boolean check1 = false;
    ArrayAdapter<String> simple_adapter1; 
    JSONArray userPG = null; 
    MultiSelectionSpinner spin;
    Spinner spinner1;
    public static final String MyPREFERENCES2 = "MyPrefs2" ;
	private static final String TAG_PRGROUPLIST = "Group List";
	private static final String TAG_SRESPG = "serviceresponse";
	private static final String TAG_SNAMEPG = "servicename";
	private static final String TAG_GNAMEPG = "groupname";
	private static final String TAG_GRID = "groupid";
	int check=0;
	public static TextView groupn;
    Spinner timer1,timer2,timer3,mer1,mer2,mer3;
    private static final String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
	public static final String LOGIN_URL = "http://www.medsmonit.com/bcreasearchT/Service/participantregister.php?service=partinsert";
	/*public static final String LOGIN_URL = "http://192.168.1.200/bcreasearchT/Service/participantregister.php?service=partinsert";*/
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        cd = new ConnectionDetector(getApplicationContext());
	        isInternetPresent = cd.isConnectingToInternet();
	        sharedpreferences2 = getSharedPreferences(MyPREFERENCES2, Context.MODE_PRIVATE);
	      //  sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
			 if (isInternetPresent) {
	        new Providerlist().execute(); 
			 }
			
			 else{
				 final Dialog dialog = new Dialog(RegisterActivity.this);
		 	     dialog.setContentView(R.layout.custom_dialog);
		 		 dialog.setTitle("INFO!");
		 		 dialog.setCancelable(false);
				 dialog.setCanceledOnTouchOutside(false);
		 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		 		  txt.setText("No network connection!");
		 		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
		 		  dialogButton.setOnClickListener(new OnClickListener() {
		 			  public void onClick(View vd) {
		 				 Intent sigout=new Intent(RegisterActivity.this,LoginActivity.class);
		 			 	startActivity(sigout); 
		 				   dialog.dismiss();
		 		
		 		}
		 		});
		 		  dialog.show(); 
			 }
	        ActionBar actbar=getActionBar();
	        actbar.show();
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	     setContentView(R.layout.register_2);
	     spin = (MultiSelectionSpinner) findViewById(R.id.group);
	     spin.setVisibility(View.INVISIBLE);
	    groupn= (TextView) findViewById(R.id.selgroup);
	    groupn.setEnabled(false);
	    groupn.setOnClickListener(new View.OnClickListener() {
	         
		     	public void onClick(View v) {
		     		groupn.setVisibility(View.INVISIBLE);
		     		 spin.setVisibility(View.VISIBLE);
		     	}
		     	});
	   
	      timer1 = (Spinner) findViewById(R.id.timer1);
		 ArrayAdapter<CharSequence> timerr1 = ArrayAdapter.createFromResource(
	         this, R.array.timer_array, android.R.layout.simple_spinner_item);
	  timerr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	timer1.setAdapter(timerr1);
	
	 mer1 = (Spinner) findViewById(R.id.meridian1);
	 ArrayAdapter<CharSequence> merdi1 = ArrayAdapter.createFromResource(
         this, R.array.meridianarray, android.R.layout.simple_spinner_item);
  merdi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
mer1.setAdapter(merdi1);

 mer2 = (Spinner) findViewById(R.id.meridian2);
ArrayAdapter<CharSequence> merdi2= ArrayAdapter.createFromResource(
    this, R.array.meridianarray, android.R.layout.simple_spinner_item);
merdi2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
mer2.setAdapter(merdi2);

 mer3 = (Spinner) findViewById(R.id.meridian3);
ArrayAdapter<CharSequence> merdi3 = ArrayAdapter.createFromResource(
    this, R.array.meridianarray, android.R.layout.simple_spinner_item);
merdi3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
mer3.setAdapter(merdi3);
    
	

	timer2 = (Spinner) findViewById(R.id.timer2);
	ArrayAdapter<CharSequence> timeadapt2 = ArrayAdapter.createFromResource(
	       this, R.array.timer_array, android.R.layout.simple_spinner_item);
	timeadapt2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	timer2.setAdapter(timeadapt2);
	
	
	 timer3 = (Spinner) findViewById(R.id.timer3);
	ArrayAdapter<CharSequence> timeadapt3 = ArrayAdapter.createFromResource(
	       this, R.array.timer_array, android.R.layout.simple_spinner_item);
	timeadapt3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	timer3.setAdapter(timeadapt3);
	
 
    
     
     spinner1 = new Spinner(this);
	   spinner1 = (Spinner) findViewById(R.id.prospin);
	   ArrayAdapter<String> adapter156 = new ArrayAdapter<String>(RegisterActivity.this,
               android.R.layout.simple_spinner_item,providerlist);
	   
	  adapter156.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  
	    spinner1.setAdapter(adapter156);
	   
		  
     
	   spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {
		   
	        public void onItemSelected(AdapterView<?> arg0, View v, 
	                                                     int position, long id)
	        {
	          
	        	
	        	System.out.println("on item selected--->");
	        	if(temp){
	                check1 = true;
	                seconds=1;
	                
	                MultiSelectionSpinner.allr="";
	                MultiSelectionSpinner.allr1="";
	                MultiSelectionSpinner.allr2="";
	                groupn.setText("Select Group");
	               RegisterActivity.prgrouplist.clear();
	                String prname7=spinner1.getSelectedItem().toString();
	     	       prname123=prname7;
	     	      System.out.println("Selected spinner value is:"+ prname123);
	     	      
	     	      if(prname123.equals("Select Provider"))
	     	      {
	     	    	 groupn.setVisibility(View.VISIBLE);
	     	    	  groupn.setText("Select Group");
	     	    	 groupn.setEnabled(false);
	     	    	 spin.setVisibility(View.INVISIBLE);
	     	    	 System.out.println("Selected provider is not valid");
	     	      }
	     	      else
	     	      {
	     	      spin.setVisibility(View.INVISIBLE);
	     	      spin.setEnabled(true);
	     	     // groupn.setText("Select Group");
	     	      
	     	     
	     	     new ProviderGroup().execute();
	     	    groupn.setEnabled(true);
	     	    groupn.setVisibility(View.VISIBLE);
	     	      }
	     	   
	            } 
	        	temp=true;
	        	System.out.println( "Selected spinner value is :"+ prname123);
	        
	        }

	        public void onNothingSelected(AdapterView<?> arg0)
	        {
	            
	        }
	    });
	
	 
	  
	   
	    spin = (MultiSelectionSpinner) findViewById(R.id.group);
	    Button re=(Button)findViewById(R.id.reset);
	    re.setOnClickListener(new View.OnClickListener() {
	         
	     	public void onClick(View v) {
	     		timer1.setSelection(0);
	     		timer2.setSelection(0);
	     		timer3.setSelection(0);
	     		mer1.setSelection(0);
	     		mer2.setSelection(0);
	     		mer3.setSelection(0);
	     		spinner1.setSelection(0);
	     		RegisterActivity.prname123="null";
	     		MultiSelectionSpinner.allr="";
                MultiSelectionSpinner.allr1="";
                MultiSelectionSpinner.allr2="";
	     	
	     		groupn.setVisibility(View.VISIBLE);
	     		groupn.setText("Select Group");
	     		groupn.setEnabled(false);
	     		spin.setVisibility(View.INVISIBLE);
	     		
	     		SharedPreferences settings2 = context.getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
	     		settings2.edit().clear().commit();
	     		
	     
	 }
	     });
	    Button can=(Button)findViewById(R.id.cancel);
 	
    can.setOnClickListener(new View.OnClickListener() {
         
     	public void onClick(View v) {
     		RegisterActivity.prname123="null";
     		SharedPreferences settings = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
     		settings.edit().clear().commit();
     		SharedPreferences settings1 = context.getSharedPreferences("MyPrefs1", Context.MODE_PRIVATE);
     		settings1.edit().clear().commit();
     		SharedPreferences settings2 = context.getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
     		settings2.edit().clear().commit();
     		Intent intentSignUP=new Intent(getApplicationContext(),LoginActivity.class);
 			startActivity(intentSignUP);
     
 }
     }); 
     submit=(Button)findViewById(R.id.submit);
  
   
    submit.setOnClickListener(new View.OnClickListener() {
        
     	public void onClick(View v) {
     		
     		System.out.println("sss");
     	   spinner1 = (Spinner) findViewById(R.id.prospin);
     	 // spinner1 = (Spinner) findViewById(R.id.prospin);
		   groupn= (TextView) findViewById(R.id.selgroup);
		   spin = (MultiSelectionSpinner) findViewById(R.id.group);
     	   String valid=spinner1.getSelectedItem().toString();
     	   
     	   if(valid.equals("Select Provider")){
     		  
     		     			 final Dialog dialog = new Dialog(RegisterActivity.this);
     	 	     
     	 		 dialog.setContentView(R.layout.custom_dialog);
     	 		 dialog.setTitle("INFO!");
     	 		 dialog.setCancelable(false);
    			 dialog.setCanceledOnTouchOutside(false);
     	 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
     	 		  txt.setText("Please select your provider!");
     	 		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
     	 		  dialogButton.setOnClickListener(new OnClickListener() {
     	 			  public void onClick(View vd) {
     	 				
     	 				   dialog.dismiss();
     	 		
     	 		}
     	 		});
     	 		  dialog.show();
     		 }
     	  else if(MultiSelectionSpinner.mspin.equals(""))
 		  {
 			  final Dialog dialog = new Dialog(RegisterActivity.this);
				 dialog.setCancelable(false);
    			 dialog.setCanceledOnTouchOutside(false);
	 		 dialog.setContentView(R.layout.custom_dialog);
	 		 dialog.setTitle("INFO!");
	 		//dialog.setTitleColor();
	 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
	 		  txt.setText("Please select your group");
	 		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
	 		  dialogButton.setOnClickListener(new OnClickListener() {
	 			  public void onClick(View vd) {
	 				// finish();
	 				   dialog.dismiss();
	 		
	 		}
	 		});
	 		  dialog.show();
 		
 		  }
     	 else if(MultiSelectionSpinner.dd>4)
         {
        
		  final Dialog dialog = new Dialog(RegisterActivity.this);
				 dialog.setCancelable(false);
 			 dialog.setCanceledOnTouchOutside(false);
	 		 dialog.setContentView(R.layout.custom_dialog);
	 		 dialog.setTitle("INFO!");
	 		
	 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
	 		  txt.setText("More than four groups are not allowed.");
	 		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
	 		  dialogButton.setOnClickListener(new OnClickListener() {
	 			  public void onClick(View vd) {
	 			
	 				   dialog.dismiss();
	 		
	 		}
	 		});
	 		  dialog.show();
         }
     	 
     	   else{
     		 
     		password=genpass();
     	
     		new RegisterUser().execute();
     	
     		
     	
     	
     	   }
     
 }
     });
	    }
	  @Override
	    protected void onResume() {
	    	super.onResume();
	    	System.out.println("In onresume of 3rd register");
	    	timer1.setSelection(sharedpreferences2.getInt("spinnerSelection1",0));
	    	timer2.setSelection(sharedpreferences2.getInt("spinnerSelection2",0));
	    	timer3.setSelection(sharedpreferences2.getInt("spinnerSelection3",0));
	    	mer1.setSelection(sharedpreferences2.getInt("spinnerSelection4",0));
	    	mer2.setSelection(sharedpreferences2.getInt("spinnerSelection5",0));
	    	mer3.setSelection(sharedpreferences2.getInt("spinnerSelection6",0));
	    	
	  
	  }
	  @Override
		 public boolean onOptionsItemSelected(MenuItem item) {
		     switch (item.getItemId()) {
		         case android.R.id.home:
		        	
		        	  timer1 = (Spinner) findViewById(R.id.timer1);
		        	timer2 = (Spinner)findViewById(R.id.timer2);
		        	timer3= (Spinner)findViewById(R.id.timer3);
		        	 mer1 = (Spinner) findViewById(R.id.meridian1);
		        	 mer2 = (Spinner) findViewById(R.id.meridian2);
		        	 mer3 = (Spinner) findViewById(R.id.meridian3);
		        	tme1=timer1.getSelectedItemPosition();
		        	tme2=timer2.getSelectedItemPosition();
		        	tme3=timer3.getSelectedItemPosition();
		        	meri1=mer1.getSelectedItemPosition();
		        	meri2=mer2.getSelectedItemPosition();
		        	meri3=mer3.getSelectedItemPosition();
		        	
		        	Editor editt=sharedpreferences2.edit();
		        	editt.putInt("spinnerSelection1", tme1);
		        	editt.putInt("spinnerSelection2", tme2);
		        	editt.putInt("spinnerSelection3", tme3);
		        	editt.putInt("spinnerSelection4", meri1);
		        	editt.putInt("spinnerSelection5", meri2);
		        	editt.putInt("spinnerSelection6", meri3);
		        	editt.commit();
		           NextActivity.userInfo1.clear();
		          // finish();
		           Intent sigout=new Intent(RegisterActivity.this,NextActivity.class);
	 			 startActivity(sigout); 
		     }
		     return true;
		 } 
	 
	  public void twilio()
	 	{
	 		new SendMsg().execute();
	 	}
	  private void sendmail(String pass){
		//  String password=pass;
		  new SendEmailAsyncTask().execute();
	  }
	  public void spinloadd()
	  {
		  
		    
		     spinner1 = new Spinner(this);
			   spinner1 = (Spinner) findViewById(R.id.prospin);
			   groupn= (TextView) findViewById(R.id.selgroup);
			   spin = (MultiSelectionSpinner) findViewById(R.id.group);
			   ArrayAdapter<String> adapter156 = new ArrayAdapter<String>(RegisterActivity.this,
		               android.R.layout.simple_spinner_item,providerlist);
			 
			  adapter156.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			    
			    spinner1.setAdapter(adapter156);
			   
				   System.out.println("provider list loaded");
				   spinneosition = adapter156.getPosition(prname123);
				   System.out.println("spinner value is" + prname123);
				    System.out.println("spinner position is" + spinneosition);
			        spinner1.setSelection(spinneosition);
			        if(spinneosition!=-1)
			        {
			        	if(emptygroup!=1)
			        	{
			        		String emt=groupn.getText().toString();
			        		System.out.println("emt is" + emt);
			        		if(emt.equals(""))
			        		{
			        			groupn.setText("Select Group");
			        		}
			        		else
			        		{
			        			if(MultiSelectionSpinner.allr.equals(""))
			        			{
			        				groupn.setText("Select Group");
			        			}
			        			else
			        			{
			        	 groupn.setText(MultiSelectionSpinner.allr);
			        	 System.out.println("213312493172048931");
			        			}
			        		}
			        	/* MultiSelectionSpinner.mspin=MultiSelectionSpinner.allr;
			        	 MultiSelectionSpinner.mspin1=MultiSelectionSpinner.allr1;
			        	 MultiSelectionSpinner.mspin2=MultiSelectionSpinner.allr2;*/
			        	/* groupn.setVisibility(View.VISIBLE);
			        	 groupn.setEnabled(true);
			        	new ProviderGroup().execute();*/
			        	 
			        	}
			        	else
			        	{
			        		groupn.setText("Select Group");
			        	}
			        	
			             groupn.setVisibility(View.VISIBLE);
			        	 groupn.setEnabled(true);
			        	new ProviderGroup().execute();
			        }
		    
	  }
	  class SendMsg extends AsyncTask<String,String,String>
	  {

	  	@Override
	  	protected String doInBackground(String... params) {
	  		
	  		String tonum =SignupActivity.userInfo.get(2);
 			
 			String firstname =SignupActivity.userInfo.get(0);
	          String username =SignupActivity.userInfo.get(1);
 			  String pass=RegisterActivity.password;
	          String message = String.format("Hi "+"%s,\n\n" +"Welcome To Adhere To Medication\n\n"+
			    		"Here is your login details!\n\n"+"Username: "+"%s\n\n"+"Password: "+"%s",firstname,username,pass);
 			 List<NameValuePair> paramsTWS = new ArrayList<NameValuePair>();
 	         
 	         paramsTWS.add(new BasicNameValuePair("to", tonum));
 	         paramsTWS.add(new BasicNameValuePair("msgbody", message));
 	         
 	        
 	     
 	         JsonParser jTW = new JsonParser();
 	         JSONObject jsonTWS = jTW.makeHttpRequest(urlTWS,"POST", paramsTWS);
 	         System.out.println("http request is finished");
 	         
 	         Log.i("tagconvertst1", "["+jsonTWS+"]");
 	         
 			
 			return null;
	  	}
	  	 protected void onPostExecute(String file_url) {
	  		 
	  		 super.onPostExecute(file_url);
			 if(JsonParser.jss1.equals("empty"))
	           {
	        	   System.out.println("json11 null value");
	        	  // System.out.println("jss1 value is" + JsonParser.jss1);
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
							  submit.setEnabled(false);
							  startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
	    					  finish(); 
							   dialog.dismiss();
							   
						
						}
					});
					  dialog.show();
					  cDialog.dismiss();
	           }
			 else{
				 SharedPreferences settings = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
				 settings.edit().clear().commit();
				 prname123="Select Provider";
		     		SharedPreferences settings1 = context.getSharedPreferences("MyPrefs1", Context.MODE_PRIVATE);
		     		settings1.edit().clear().commit();
		     		SharedPreferences settings2 = context.getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
		     		settings2.edit().clear().commit();
		     		MultiSelectionSpinner.allr="";
					MultiSelectionSpinner.allr1="";
					MultiSelectionSpinner.allr2="";
				 final Dialog dialog = new Dialog(RegisterActivity.this);
		 	     dialog.setContentView(R.layout.custom_dialog);
		 		 dialog.setTitle("INFO!");
		 		 dialog.setCancelable(false);
				 dialog.setCanceledOnTouchOutside(false);
		 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		 		  txt.setText("Your password has been sent to your mail and mobile number.");
		 		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
		 		  dialogButton.setOnClickListener(new OnClickListener() {
		 			  public void onClick(View vd) {
		 				 Intent sigout=new Intent(RegisterActivity.this,LoginActivity.class);
		 			 	startActivity(sigout); 
		 			 	
		 				   dialog.dismiss();
		 		
		 		}
		 		});
		 		  dialog.show();
	     
	        pDialog.dismiss();
			 }
	 		 }
	  }

		  
		                    
	static String genpass(){
		  final Random myRandom = new Random();
			String pw="";
	      int PASSWORD_LENGTH=6;
	  	for (int i=0; i<PASSWORD_LENGTH; i++)
	      {
	          int index = (int)(myRandom.nextDouble()*letters.length());
	          pw += letters.substring(index, index+1);
	      }
	    System.out.println("Password:"+pw);
	    
	    Log.d("TAG", pw);
	    return pw;
		  
	  }
		 	
  


class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
    GMailSender sender = new GMailSender("learnguild@gmail.com", "deemsys@123");
    String firstname =SignupActivity.userInfo.get(0);
    String username =SignupActivity.userInfo.get(1);
    String sender_mail=SignupActivity.userInfo.get(3);
    
    public SendEmailAsyncTask() {
    	}
     

    @Override
    protected Boolean doInBackground(Void... params) {
        if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
        try {
        	 String firstname =SignupActivity.userInfo.get(0);
	          String username =SignupActivity.userInfo.get(1);
	          String sender_mail=SignupActivity.userInfo.get(3);
	        String pass=RegisterActivity.password;
	        String message = String.format("Hi "+"%s,\n\n" +"Welcome To Adhere To Medication!\n\n"+
			    		"Here is your login details : \n\n"+"Username : "+"%s\n\n"+"Password : "+"%s",firstname,username,pass);
           sender.sendMail("Adhere To Medication Registration",message, "learnguild@gmail.com",sender_mail )  ;
           System.out.println(message); 
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
    	
	 	    
 		  
   	 	 }
}

class Providerlist extends AsyncTask<String,String,String>{
	@Override
    protected void onPreExecute() {
		  cDialog = new ProgressDialog(RegisterActivity.this);
          cDialog.setMessage("Fetching provider details");
          cDialog.setIndeterminate(false);
          cDialog.setCancelable(false);
          cDialog.show();
	}
		
		@Override
		protected void onPostExecute(String file_url) {
	   
			 super.onPostExecute(file_url);
			 if(JsonParser.jss1.equals("empty"))
	           {
	        	   System.out.println("json11 null value");
	        	  // System.out.println("jss1 value is" + JsonParser.jss1);
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
							  submit.setEnabled(false);
							  startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
	    					  finish(); 
							   dialog.dismiss();
							   
						
						}
					});
					  dialog.show();
					  cDialog.dismiss();
	           }
			 else{
				 
			spinloadd();
			 }
			
		
	} 

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			providerlist.clear();
			prgrouplist.clear();
		 jArray= jsonParser.getJSONFromUrl(url);
		    Log.i("tagconvertstr", "["+jArray+"]");
		    
		    try
		    {
		    	if(jArray != null){
		    	int a = 0;
		    	JSONObject c = jArray.getJSONObject(TAG_SRES);
		    	Log.i("tagconvertstr", "["+c+"]");
		    	
		    	String sp = c.getString(TAG_SNAME);
		    	String ss = c.getString(TAG_SUCCESS);
		    	System.out.println(ss);
		    	System.out.println(sp);
		    	System.out.println("GETTING PROVIDERLIST");
		    	user = c.getJSONArray(TAG_PROLIST);
		    	Log.i("tagconvertstr1", "["+user+"]");
		    	providerlist.add(0,"Select Provider");
		    	for(int i=0;i<user.length();i++)
		    	{
		    		System.out.println("forloop1");
		    		JSONObject c1 = user.getJSONObject(i);
		    		JSONObject c2 = c1.getJSONObject(TAG_SRES);
		    		String sp1 = c2.getString(TAG_SNAME);
		    		System.out.println(sp1);
		        	String ss1 = c2.getString(TAG_SUCCESS);
		        	System.out.println(ss1);
		       
		        	String proname= c2.getString(TAG_NAME);
		        	String ss2 = c2.getString(TAG_PROID);
		        	String sp3 = c2.getString(TAG_MESSAGE);
		        	
		        	System.out.println(proname);
		        	System.out.println(ss2);
		        	System.out.println(sp3);
		        	
		        	providerlist.add(proname);
		    		a=providerlist.size();
		    		System.out.println("size of aray list"+a);
		    		
		    	}
		    	System.out.println("size of aray list"+a);
		    	}
		    	
		    	}catch (JSONException e) {
		        e.printStackTrace();
		    }
		    cDialog.dismiss();
			return null;
		}
		
		
		
	}



class RegisterUser extends AsyncTask<String, String, String> {


			boolean failure = false;
			
		
			
			
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(RegisterActivity.this);
	            pDialog.setMessage("Registering");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
	    
			
			@Override
			protected String doInBackground(String... args) {
				
	    
	            System.out.println("getting the values");
	            
	            Spinner mer1 = (Spinner)findViewById(R.id.meridian1);
		    	 String merd1 =mer1.getSelectedItem().toString();
		    	 Spinner mer2 = (Spinner)findViewById(R.id.meridian2);
		    	 String merd2 =mer2.getSelectedItem().toString();
		    	 Spinner mer3 = (Spinner)findViewById(R.id.meridian3);
		    	 String merd3 =mer3.getSelectedItem().toString();
	            
	          
	            Spinner timer1 = (Spinner)findViewById(R.id.timer1);
		    	 String time1 =timer1.getSelectedItem().toString();
		    	if(time1.equalsIgnoreCase("Select Time")){
		    		time1="null";
		    		merd1="AM";
		    	}
		    	 Spinner timer2 = (Spinner)findViewById(R.id.timer2);
		    	 String time2 =timer2.getSelectedItem().toString();
		    	 if(time2.equalsIgnoreCase("Select Time")){
			    		time2="null";
			    		merd2="AM";
			    	}
		    	 Spinner timer3= (Spinner)findViewById(R.id.timer3);
		    	 String time3 =timer3.getSelectedItem().toString();
		    	 if(time3.equalsIgnoreCase("Select Time")){
			    		time3="null";
			    		merd3="AM";
			    	}
		    	System.out.println("time 1:::::"+time1);
		    	System.out.println("time 2:::::"+time2);
		    	System.out.println("time 3:::::"+time3);
	            String firstname =SignupActivity.userInfo.get(0);
	            String username =SignupActivity.userInfo.get(1);
	            String mobilenum =SignupActivity.userInfo.get(2);
	            String emailid=SignupActivity.userInfo.get(3);
	            String gender =NextActivity.userInfo1.get(0);
	            String age =NextActivity.userInfo1.get(1);
	            String city =NextActivity.userInfo1.get(2);
	            String education =NextActivity.userInfo1.get(3);
	            String medicine =NextActivity.userInfo1.get(4);
	            String pass1=RegisterActivity.password;
	        
	            String pros1 = RegisterActivity.prname123;
	            
	         
	            String pros3 = MultiSelectionSpinner.mspin;
	         
	            String pros22 = MultiSelectionSpinner.mspin1;
	          
	            String pros33 = MultiSelectionSpinner.mspin2;
	           
	        
	           
	          
	         
	            	  
	            	  
		                List<NameValuePair> params = new ArrayList<NameValuePair>();
		                params.add(new BasicNameValuePair("fname", firstname));
		                params.add(new BasicNameValuePair("username1", username));
		                params.add(new BasicNameValuePair("mobile_num", mobilenum));
		                params.add(new BasicNameValuePair("email", emailid));
		                params.add(new BasicNameValuePair("gender", gender));
		                params.add(new BasicNameValuePair("age", age));
		                params.add(new BasicNameValuePair("city", city));
		                params.add(new BasicNameValuePair("education", education));
		                params.add(new BasicNameValuePair("medical_details", medicine));
		                params.add(new BasicNameValuePair("time1", time1));
		                params.add(new BasicNameValuePair("time1_am_pm", merd1));
		                params.add(new BasicNameValuePair("time2", time2));
		                params.add(new BasicNameValuePair("time2_am_pm", merd2));
		                params.add(new BasicNameValuePair("time3", time3));
		                params.add(new BasicNameValuePair("time3_am_pm", merd3));
		                params.add(new BasicNameValuePair("Provider_name", pros1));
		                params.add(new BasicNameValuePair("group_name", pros3));
		               
		                params.add(new BasicNameValuePair("groupname12", pros22));
		                params.add(new BasicNameValuePair("groupid1", pros33));
		                params.add(new BasicNameValuePair("pass", pass1));
		                
		                
	                
	                
	                
	                System.out.println("name value pair is finished");
	 
	                Log.d("request!", "starting");
	                
	              
		              JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
		              
		              Log.i("in register", "["+json+"]");
		              try
		              {
		            	  if(json!=null){
		             	 JSONObject jUser = json.getJSONObject(TAG_SRESR);
		             	
		             	 successiden= jUser.getString(TAG_SUCCESSR);
		             //	 System.out.println( " success is"+successiden);
		                 usrmsg=jUser.getString(TAG_MESSAGEU);
		             	// System.out.println("user message is"+ usrmsg);
		             	
		            	  }
		            	  System.out.println("provider list is not selected ");
		              }
		              catch(JSONException e)
		              {
		             	 e.printStackTrace();
		             	 
		              }
		              
		              
		              
		              
		              
		            
			   return null;
	            
			}
			
			
			

	            
				
			
			   protected void onPostExecute(String file_url) {
				  // System.out.println("successiden value:::"+successiden);
				   if(JsonParser.jss.equals("empty"))
		           {
		        	   System.out.println("json null value in register");
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
								 
								   dialog.dismiss();
							
							}
						});
						  dialog.show();
						  pDialog.dismiss();
		           }
				  /* if(TextUtils.isEmpty(successiden)){
			        	  System.out.println("true print pannu");
			        	  final Dialog dialog = new Dialog(context);
			   			 dialog.setContentView(R.layout.custom_dialog);
			   			 dialog.setTitle("Registration Failed");
			   			 dialog.setCancelable(false);
			   			 dialog.setCanceledOnTouchOutside(false);
			   			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
			   			  txt.setText("Server Not Connected!");
			   		
			   			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
			   			  dialogButton.setOnClickListener(new OnClickListener() {
			   				  public void onClick(View vd) {
			   					   dialog.dismiss();
			 				
			 				}
			   			});
			   			  dialog.show();
			   			  pDialog.dismiss();
			          }*/
			   else if(successiden!=null && successiden.equalsIgnoreCase("Yes"))
			           {
			    		  pDialog.dismiss();
			    		  twilio();
			    		  sendmail(password);
			    		
			    		
			        	  
			        	   
			           }
			     
			           else if(usrmsg.equalsIgnoreCase("Already Exist")){
			        	   pDialog.dismiss();
			          	 final Dialog dialog = new Dialog(RegisterActivity.this);
			    			 dialog.setContentView(R.layout.custom_dialog);
			    			 dialog.setTitle("INFO!");
			    			 dialog.setCancelable(false);
			    			 dialog.setCanceledOnTouchOutside(false);
			    			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
			    			  txt.setText("E-mail already exist.");
			    			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
			    			  dialogButton.setOnClickListener(new OnClickListener() {
			    				  public void onClick(View vd) {
			    					 // startActivity(new Intent(RegisterActivity.this,SignupActivity.class));
			      					
			    					  //  finish(); 
			    					  dialog.dismiss();
			 				
			 				}
			    			});
			    			  dialog.show();
			 			  }
			           else if(usrmsg.equalsIgnoreCase("Mobile Number Exist")){
			        	   pDialog.dismiss();
			          	 final Dialog dialog = new Dialog(RegisterActivity.this);
			    			 dialog.setContentView(R.layout.custom_dialog);
			    			 dialog.setTitle("INFO!");
			    			 dialog.setCancelable(false);
			    			 dialog.setCanceledOnTouchOutside(false);
			    			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
			    			  txt.setText("Mobile number already exist.");
			    			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
			    			  dialogButton.setOnClickListener(new OnClickListener() {
			    				  public void onClick(View vd) {
			    					 // startActivity(new Intent(RegisterActivity.this,SignupActivity.class));
			      					
			    					   // finish(); 
			    					  dialog.dismiss();
			 				
			 				}
			    			});
			    			  dialog.show();
			 			  }
			           else if(usrmsg.equalsIgnoreCase("Username Exist")){
			        	   pDialog.dismiss();
				          	 final Dialog dialog = new Dialog(RegisterActivity.this);
				    			 dialog.setContentView(R.layout.custom_dialog);
				    			 dialog.setTitle("INFO!");
				    			 dialog.setCancelable(false);
				    			 dialog.setCanceledOnTouchOutside(false);
				    			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
				    			  txt.setText("Username already exist.");
				    			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
				    			  dialogButton.setOnClickListener(new OnClickListener() {
				    				  public void onClick(View vd) {
				    					  
				    					//  startActivity(new Intent(RegisterActivity.this,SignupActivity.class));
				    					 // finish(); 
				    					  dialog.dismiss();
				 				
				 				}
				    			});
				    			  dialog.show();
				 			  }
				      
			      
			         
			         
			          
			       }
			          
		} 	  
		class ProviderGroup extends AsyncTask<String,String,String>
		{
			@Override
		    protected void onPreExecute() {
				  cDialog = new ProgressDialog(RegisterActivity.this);
		          cDialog.setMessage("Fetching group details");
		          cDialog.setIndeterminate(false);
		          cDialog.setCancelable(false);
		          cDialog.show();
			}
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
                String pros2 = RegisterActivity.prname123;
                System.out.println("passed providername is---->"+pros2);
              
				//String progroupname = RegisterActivity.provider123;

                List<NameValuePair> paramsPG = new ArrayList<NameValuePair>();
                paramsPG.add(new BasicNameValuePair("providername", pros2));
               
				JsonParser jparserPG = new JsonParser();
                JSONObject jsonPG = jparserPG.makeHttpRequest(urlPG, "POST", paramsPG);
                Log.i("tagconvertstr1", "["+jsonPG+"]");
           
                try
                {
                	if(jsonPG!=null){
                	JSONObject pg = jsonPG.getJSONObject(TAG_SRESPG);
                	String serviceN = pg.getString(TAG_SNAMEPG);
                	System.out.println(serviceN);
                
                	prgrouplist.clear();
                	prgridlist.clear();
                	   successstag=pg.getString(TAG_SUCCESS1);
                	 	System.out.println("Success Tag:::"+successstag);
                		userPG =pg.getJSONArray(TAG_PRGROUPLIST);
                	for(int i=0;i<userPG.length();i++)
                	{
                		JSONObject pg1 = userPG.getJSONObject(i);
                		JSONObject pg2 = pg1.getJSONObject(TAG_SRESPG);
                		String groupname = pg2.getString(TAG_GNAMEPG);
                		String gid = pg2.getString(TAG_GRID);
                		
                	
                		
                    prgrouplist.add(groupname);
                    
                    prgridlist.add(gid);
                    
                		 aG= prgrouplist.size();
                		 aGID = prgridlist.size();
                	
                	}
                	 
                	}
             }
                catch(JSONException e)
                {
                	e.printStackTrace();
                }
                
				
				return null;
			}
			
			@Override
			protected void onPostExecute(String file_url) {
				
				
				cDialog.dismiss();
				if(JsonParser.jss1.equals("empty")||JsonParser.jss.equals("empty"))
						{
					 System.out.println("json11 null value");
		        	  // System.out.println("jss1 value is" + JsonParser.jss1);
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
								  submit.setEnabled(false);
								
								   dialog.dismiss();
							
							}
						});
						  dialog.show();
						  cDialog.dismiss();
						}
				else
				{
				if(successstag.equals("Yes")){
				spin = (MultiSelectionSpinner) findViewById(R.id.group);
				if(RegisterActivity.prgrouplist!=null)
				{
					if(spinneosition!=-1)
					{
						if(seconds!=1){
				spin.setItems(RegisterActivity.prgrouplist);
						}
						else
						{
							
							MultiSelectionSpinner.allr="";
							MultiSelectionSpinner.allr1="";
							MultiSelectionSpinner.allr2="";
							spin.setItemsA(RegisterActivity.prgrouplist);
						}
					}
					else
					{
						spin.setItemsA(RegisterActivity.prgrouplist);
					}
				emptygroup=0;
			//	groupn.setVisibility(View.VISIBLE);
				}
				else
				{
					 Intent sigout1=new Intent(getApplicationContext(),RegisterActivity.class);
		    			startActivity(sigout1);   
				}
					
				}
				else{
					RegisterActivity.prgrouplist.clear();
					System.out.println("list sizeee isss" + RegisterActivity.prgrouplist.size());
					
					RegisterActivity.prgrouplist.add(0,"");
					
					System.out.println("grouplist size is" + RegisterActivity.prgrouplist.size());
					groupn.setText("Select Group");
					spin.setItemsA(RegisterActivity.prgrouplist);
				//	groupn.setVisibility(View.VISIBLE);
					emptygroup=1;
					spin.setEnabled(false);
					System.out.println("grouplist size is" + RegisterActivity.prgrouplist.size());
					 final Dialog dialog = new Dialog(RegisterActivity.this);
					 dialog.setCancelable(false);
	    			 dialog.setCanceledOnTouchOutside(false);
        	 		 dialog.setContentView(R.layout.custom_dialog);
        	 		 dialog.setTitle("Failed");
        	 		//dialog.setTitleColor();
        	 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
        	 		  txt.setText("There is no Group Available for this Provider!");
        	 		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
        	 		  dialogButton.setOnClickListener(new OnClickListener() {
        	 			  public void onClick(View vd) {
        	 				
        	 				   dialog.dismiss();
        	 		
        	 		}
        	 		});
        	 		  dialog.show();
				}
			}
		} 
		}
	
		class mySpinnerListener implements Spinner.OnItemSelectedListener
		{

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub
				String item = adapter.getItemAtPosition(position).toString();
				System.out.println("Spinner selected value::"+item);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		
	        
			
}	

}





																																																																																																																						



