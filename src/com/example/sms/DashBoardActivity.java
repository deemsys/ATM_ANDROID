
package com.example.sms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;

import android.app.Activity;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.TextView;
import com.deemsysinc.sms.R;

/* Activity used to load daily messages and weekly surveys */
public class DashBoardActivity extends Activity {
	int weeklyeval=0;
	public int counter;
	TimerTask mTimerTask;
	int dailyun;
	JSONObject jsonW ;
	public int dailyread;
	public static int dailyunread;
	public static int change;
	final Handler handler = new Handler();
	Timer t = new Timer();
	final Context context=this;
	public static int total;
	  Button dailymsg,weeklymsg;
	TextView unread,due;
	String teeet;
	public String text,text1,text2;
	DatabaseHandler dbHandler = new DatabaseHandler(this);
	  public ProgressDialog pDialog;
	  ProgressDialog myPd_bar;
	  ProgressDialog myPd_ring;
	  int totalrows;
	 public static final String KEY_USERID = "userid";
	  private static final String TAG_WLOGLIST = "Weekly_logs List";
	    private static final String TAG_WLOGID = "log_id";
	    private static final String TAG_SRESW= "serviceresponse";
	
	    private static final String TAG_WEEK = "week";
	    		private static final String TAG_DATETIME = "date_time";
	    		private static final String TAG_CONTINUE = "continuous";
	    		private static final String TAG_COUNT = "count";
	    		private static final String TAG_STAT = "status";
	     
	      JSONArray board = null;
	      JSONArray pro = null;
	      JSONArray user = null; 
	      private static String urlW = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=weeklyevaluationSelect";
	     /* private static String urlW = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=weeklyevaluationSelect";*/
	      public static ArrayList<String> participant = new ArrayList<String>();
         
          public static ArrayList<String> logid = new ArrayList<String>();
      	public static ArrayList<String> week = new ArrayList<String>();
      	public static ArrayList<String> datetime = new ArrayList<String>();
      	public static ArrayList<String> continuous = new ArrayList<String>();
      	public static ArrayList<String> count = new ArrayList<String>();
      	public static ArrayList<String> status = new ArrayList<String>();
	      public static ArrayList<String> provider = new ArrayList<String>();
		    String userinfo1[];
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dashboard);
	        new WeekLogs(myPd_bar).execute();
	        ActionBar actbar=getActionBar();
	        actbar.show();
	    
	    
	    	dailymsg = (Button) findViewById(R.id.readmsg);
	        weeklymsg = (Button) findViewById(R.id.evaluation);
	         unread = (TextView)findViewById(R.id.unreaddaily);
	        due = (TextView)findViewById(R.id.week);
	        
	        TextView wel=(TextView)findViewById(R.id.welcome);
	        String welcomestring=LoginActivity.usrname;
	   //     welcomestring=welcomestring.append("!");
	        wel.setText(welcomestring);
	        final TextView setmsg1 = (TextView)findViewById(R.id.welcome);
	        setmsg1.setText("Welcome "+LoginActivity.usrname+"!");
	      
	       
	//  dbHandler.delete_all();
	        TextView viewpro=(TextView)findViewById(R.id.viewprof);
	        TextView loginScreen = (TextView) findViewById(R.id.signout);
	      
	     int i;
	     int msgunread=dbHandler.calculateunread();
	     if(msgunread>0){
	    	 Intent intent = new Intent(DashBoardActivity.this, NotificationActivity.class);
	    	 PendingIntent pendingIntent = PendingIntent.getBroadcast(DashBoardActivity.this, 2, intent, 0);
	    	 AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
	    	 am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),60*60*12, pendingIntent);
	    	  
	     }
	     dailyunread=LoginActivity.Messagescountlist;
	  
	     change=LoginActivity.Messagescountlist-dailyunread;
	     change=change+dailyun;
	  
	     totalrows=dbHandler.Get_Total_Contacts();
 System.out.println("total rows::"+totalrows);
 System.out.println("array list in dashboard::"+LoginActivity.Messagescountlist);
	    for(i=totalrows;i<=LoginActivity.Messagescountlist-1;i++){
	   
	    	 String fromno=LoginActivity.fromlist.get(i);
	        	String tonum=LoginActivity.tolist.get(i);
	        	String userrrid=LoginActivity.proInfo.get(0);
	        	String bodyy=LoginActivity.msgbodylist.get(i);
	        	String datt=LoginActivity.dateandtimelist.get(i);
	        	String stt="0";

	       System.out.println(fromno);
	        	 
    		

	dbHandler.Add_Message(new Message(userrrid,fromno,tonum,bodyy,datt,stt));
     totalrows=dbHandler.Get_Total_Contacts();
    		System.out.println("execute 1:::");
	     }
	    totalrows=dbHandler.Get_Total_Contacts();
	     
	       dailyun=dbHandler.calculateunread();
	     
	        loginScreen.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View arg0) {
	            	logid.clear();
                 	week.clear();
                 	continuous.clear();
                 	datetime.clear();
                 	count.clear();
                 	status.clear();
                 	LoginActivity.proInfo.clear();
                 	LoginActivity.userid=null;
	            	DashBoardActivity.datetime.clear();
	                participant.clear();
	                provider.clear();
	                dailyun=0;
	                LoginActivity.Messagescountlist=0;
	            	
	            
	    			Intent sigout=new Intent(getApplicationContext(),LoginActivity.class);
	    			startActivity(sigout);                                                       
	               
	                finish();
	            }
	        });
	      
	        viewpro.setOnClickListener(new View.OnClickListener() {
	       	  public void onClick(View arg0) {
	            	
	           Intent sigout=new Intent(getApplicationContext(),ViewProfileActivity.class);
    			startActivity(sigout); 
	            }
	        });
	      
	  
	       
	        dailymsg.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
	        	
	        Intent in=new Intent(getApplicationContext(),InboxActivity.class);
	        startActivity(in);              
	                
	            }
	        });
	        weeklymsg.setOnClickListener(new View.OnClickListener() {
	       	 
	            public void onClick(View arg0) {
	            	
	           			Intent changepwd=new Intent(getApplicationContext(),EvaluationActivity.class);
	    			startActivity(changepwd);                
	                finish();
	            }
	        });
	        TextView set=(TextView)findViewById(R.id.settings);
	        set.setOnClickListener(new View.OnClickListener() {
		       	 
	            public void onClick(View arg0) {
	            	
	           			Intent changepwd=new Intent(getApplicationContext(),SettingsActivity.class);
	    			startActivity(changepwd);                
	                finish();
	            }
	        });
	  
	        
	        
	        
 }   
	    public void evaluview(int status_count){
	    	 totalrows=dbHandler.Get_Total_Contacts();
      	
      	
	       dailyun=dbHandler.calculateunread();
	    	text=String.format("You have not reviewed  "+"%s"+ " messages out of the"+" %s"+"  messages in your library .",dailyun,totalrows);
	     
	        teeet="You don't have any new messages.";
	       if(totalrows==0){
	    	   unread.setText(teeet);
	    	  
	       }
	       else{
	    	   unread.setText(text); 
	       }
	        text1=String.format("You have completed all your weekly surveys.");
	        due.setText(text1);
	        text2=String.format("You have  "+"%s"+" survey that is overdue.",status_count);
	        due.setText(text2);
	      
	        if(status_count>0){
	        	 due.setText(text2);
	        }
	        else
	        	due.setText(text1);
	        	
	        CharSequence value=due.getText();
	       if(value==text1){
		   weeklymsg.setVisibility(View.INVISIBLE);
	       }
	       else{
	    	
	       }   
	    }
 /*public void scheduleAlarm(){
		 Long time = new GregorianCalendar().getTimeInMillis()+24*60*60*1000;
		  Intent intentAlarm = new Intent(this, NotificationActivity.class);
	 
		  AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		  alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
	 }
	    
	        @SuppressWarnings("deprecation")
			private void Notification(String notificationTitle, String notificationMessage) {
	            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	            android.app.Notification notification = new android.app.Notification(R.drawable.ic_launcher, "This is message from BCResearch Project (BCResearch)!",
	            System.currentTimeMillis());

	            Intent notificationIntent = new Intent(this, NotificationActivity.class);
	            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	            notification.setLatestEventInfo(this, notificationTitle, notificationMessage, pendingIntent);
	            notificationManager.notify(10001, notification);
	        }
	        */
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	        class WeekLogs extends AsyncTask<String,String,String>{
	        	
				public WeekLogs(ProgressDialog progress) {
	        		    
	        		     myPd_ring=ProgressDialog.show(DashBoardActivity.this, "Please wait", "Synchronizing Data", true);
                        myPd_ring.setCancelable(false);
                        new Thread(new Runnable() {  
                              @Override
                              public void run() {
                                 
                                    try
                                    {
                                     Thread.sleep(5000);
                                    }catch(Exception e){}
                                    
                              }
                        }).start();                                          
                  
	        		  }

	    		@Override
	    		protected String doInBackground(String... params) {
	    						List<NameValuePair> params2 = new ArrayList<NameValuePair>();
	    			 String LogId1 = LoginActivity.proInfo.get(0);
	    			
	    			params2.add(new BasicNameValuePair("loginid", LogId1));
	    			 JsonParser jLogin = new JsonParser();
	                 jsonW = jLogin.makeHttpRequest(urlW,"POST", params2);
	                 Log.i("tagconvertstr3", "["+jsonW+"]");
	                 try
	                 {
	                	 if(jsonW!=null){
	                 	JSONObject c = jsonW.getJSONObject(TAG_SRESW);
	                 	Log.i("tagconvertstr", "["+c+"]");
	                 	
	                 
	                 	
	                 	user = c.getJSONArray(TAG_WLOGLIST);
	                 	Log.i("tagconvertstr1", "["+user+"]"); 
	                 
	                 	logid.clear();
	                 	week.clear();
	                 	continuous.clear();
	                 	datetime.clear();
	                 	count.clear();
	                 	status.clear();
	                 	
	                 	for(int i=0;i<user.length();i++)
	                 	{
	                 	
	                 		JSONObject c1 = user.getJSONObject(i);
	                 		JSONObject c2 = c1.getJSONObject(TAG_SRESW);
	                 		
	    	                	
	    	                	String logi = c2.getString(TAG_WLOGID);
	    	                	String weeknum= c2.getString(TAG_WEEK);
	    	               
	    	                	String conutin = c2.getString(TAG_CONTINUE);
	    	                	String datet = c2.getString(TAG_DATETIME);
	    	                	String coun = c2.getString(TAG_COUNT);
	    	                	String stat = c2.getString(TAG_STAT);
	    	                   
	    	                	logid.add(logi);
	    	                	week.add(weeknum);
	    	                	datetime.add(datet);
	    	                	continuous.add(conutin);
	    	                	count.add(coun);
	    	                	status.add(stat);
	    	                	}
	                 	
	                	 }
	             }catch (JSONException e) {
	                 e.printStackTrace();
	             }
	                
	               return null;
	    		}
	    		@Override
	    		 protected void onPostExecute(String file_url) {
	    			if(JsonParser.jss.equals("empty")){
	    				myPd_ring.dismiss();
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
	    			}
	    			else{
	    			 try {
	    				weeklylogeval();
	    				myPd_ring.dismiss();
	    			} catch (ParseException e) {
	    				
	    				e.printStackTrace();
	    			}
	    			}
	    		 }
	        	
	        }
	        @SuppressLint("SimpleDateFormat")
	        public void weeklylogeval() throws ParseException{
	            	int i;
	            	int status_counter=0;
	            
	            	
	                for(i=0;i<datetime.size();i++){
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            
	            	Date date = new Date();
	            	String pastdate=datetime.get(i);
	            	String todaydate = sdf.format(date);
	               
	                Date todate = sdf.parse(pastdate);
	                Date frmdate = sdf.parse(todaydate);
	             
	                if(todate.compareTo(frmdate)<=0) {
	                 status_counter++;
	               
	                 } else {
	                   
	                 }
	          
	                }
	              
	             
	               evaluview(status_counter);
	               
	                }	
	        
	        @SuppressLint("SimpleDateFormat")
	        public void daily() throws ParseException{
	            	int i;
	            	int status_counter=0;
	            
	            	
	                for(i=0;i<LoginActivity.dateandtimelist.size();i++){
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            
	            	Date date = new Date();
	            	String pastdate=datetime.get(i);
	            	String todaydate = sdf.format(date);
	               
	                Date todate = sdf.parse(pastdate);
	                Date frmdate = sdf.parse(todaydate);
	                
	              
	                if(todate.compareTo(frmdate)<=0) {
	                 status_counter++;
	                   System.out.println("counter value:::"+status_counter);
	                 } else {
	                   
	                 }
	          
	                }
	               
	            
	                }	
	        
	        
	        
	 
}



















