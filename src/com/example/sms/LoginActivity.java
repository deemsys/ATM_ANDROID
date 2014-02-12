package com.example.sms;
import java.util.ArrayList;
import java.util.List;



import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.deemsysinc.sms.R;

public class LoginActivity extends Activity {
	
	
	Button signin,signup,forget;
	 EditText username;
	    EditText passw;
	    String uname;
		public static String password;
	final Context context=this;
	public static String usrname;
	public ProgressDialog pDialog;
	 Boolean isInternetPresent = false;
	public static int  Messagescountlist;
	public static ArrayList<String> fromlist= new ArrayList<String>();
	public static ArrayList<String> tolist = new ArrayList<String>();
	public static ArrayList<String> msgbodylist= new ArrayList<String>();
	public static ArrayList<String> dateandtimelist= new ArrayList<String>();
	public static ArrayList<String> messagestatuslist = new ArrayList<String>();
	ConnectionDetector cd;
	 private static final String TAG_MSGG = "message";
	
	
	public static ArrayList<String> proInfo = new ArrayList<String>();
	 private static final String TAG_SRESP= "serviceresponse";
	   
	    private static final String TAG_PROINFO1 = "Provider Info";
	    private static final String TAG_ADMINNAME = "adminusername";
	    private static final String TAG_ADMINMOB = "adminmobile";
	    private static final String TAG_ADMINEMAIL = "adminemail";
	    private static final String TAG_ADMINFNAME = "adminfirstname";
	   
	    
	    
  private static final String TAG_SRESD= "serviceresponse";
  private static final String TAG_UNAME = "username";
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
  private static final String TAG_SREST= "serviceresponse";
  private static final String TAG_PROINFO = "Patient info";
  private static final String TAG_TWR = "Patient info";
  private static final String TAG_FRNUM = "From_num";
  private static final String TAG_STATT = "status";
  private static final String TAG_CTEXT = "contenttext";
  private static final String TAG_TONUM = "To_num";
  private static final String TAG_DTIME = "date_time";
 JSONArray twilio = null;
 private static String urlTW = "http://www.medsmonit.com/bcreasearchT/Service/twilioservice.php?service=readmessage";
 private static String urlD = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=participantSelect"; 
	 private static String urlP= "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=providerSelect";
 /*private static String urlTW = "http://192.168.1.200/bcreasearchT/Service/twilioservice.php?service=readmessage";
 private static String urlD = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=participantSelect"; 
	 private static String urlP= "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=providerSelect";*/
     JSONArray board = null;
     JSONArray pro = null;
   public static  TextView mobnum;
	public static TextView pass;
	public static String userid;
 String successL;
	private static String url = "http://www.medsmonit.com/bcreasearchT/Service/loginresponse.php?service=login";
	/*private static String url = "http://192.168.1.200/bcreasearchT/Service/loginresponse.php?service=login";*/
    private static final String TAG_SRESL= "serviceresponse";
	private static final String TAG_SUCCESS1 = "success";
	    public static ArrayList<String> provider = new ArrayList<String>();
	    private static final String TAG_USERID = "userid";
	    String fname,usname,proname,email,city,gender,educat,t1,t2,t3,age,medical,mobile,group,timeformat1,timeformat2,timeformat3,msgprf;
	    public static ArrayList<String> participant = new ArrayList<String>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login);
        ActionBar actbar=getActionBar();
        actbar.show();
        signin=(Button)findViewById(R.id.signin);
        signup=(Button)findViewById(R.id.signup);
        forget=(Button)findViewById(R.id.forget);
        mobnum=(TextView)findViewById(R.id.mobnum);
     pass=(TextView)findViewById(R.id.password);
        Button signup=(Button)findViewById(R.id.signup);
    	Button signin=(Button)findViewById(R.id.signin);
    	 cd = new ConnectionDetector(getApplicationContext());
       signup.setOnClickListener(new View.OnClickListener() {
            
        	public void onClick(View v) {
        		  SharedPreferences settings = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
             		settings.edit().clear().commit();
             	RegisterActivity.prname123="null";
           		SharedPreferences settings1 = context.getSharedPreferences("MyPrefs1", Context.MODE_PRIVATE);
           		settings1.edit().clear().commit();
           		SharedPreferences settings2 = context.getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
           		settings2.edit().clear().commit();
        		Intent intentSignUP=new Intent(getApplicationContext(),SignupActivity.class);
    			startActivity(intentSignUP);
        
                     }
        });
       forget.setOnClickListener(new View.OnClickListener() {
           
       	public void onClick(View v) {
       		
       		Intent intentSignUP=new Intent(getApplicationContext(),ForgetPassActivity.class);
   			startActivity(intentSignUP);
       
   }
       });
 signin.setOnClickListener(new View.OnClickListener() {
	
            
        	public void onClick(View v) {
        		
        		 String uname=mobnum.getText().toString();
        		 String password=pass.getText().toString();
        		
        		if(!uname.equals("")&& !password.equals("")){
        			 isInternetPresent = cd.isConnectingToInternet();
        			 if (isInternetPresent) {
        		new AttemptLogin().execute();
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
               					   dialog.dismiss();
            				
            				}
               			});
               			  dialog.show(); 
        			 }
        		
        		}	
        			
        		 else if ( ( !mobnum.getText().toString().equals("")) )
                 {
        			 
        			 final Dialog dialog = new Dialog(context);
        			     
           			 dialog.setContentView(R.layout.custom_dialog);
           			 dialog.setTitle("INFO!");
           			 dialog.setCancelable(false);
        			 dialog.setCanceledOnTouchOutside(false);
           			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
           			  txt.setText("Password cannot be empty.");
           			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
           			  dialogButton.setOnClickListener(new OnClickListener() {
           				  public void onClick(View vd) {
           					   dialog.dismiss();
        				
        				}
           			});
           			  dialog.show();
        			  }
                 
                 else if ( ( !pass.getText().toString().equals("")) )
                 {
                	 final Dialog dialog = new Dialog(context);
                	      
           			 dialog.setContentView(R.layout.custom_dialog);
           			 dialog.setTitle("INFO!");
           			 dialog.setCancelable(false);
        			 dialog.setCanceledOnTouchOutside(false);
           			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
           			  txt.setText("Username cannot be empty.");
           			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
           			  dialogButton.setOnClickListener(new OnClickListener() {
           				  public void onClick(View vd) {
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
       			  txt.setText("Invalid username and password.");
       			
       			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
       			  dialogButton.setOnClickListener(new OnClickListener() {
       				  public void onClick(View vd) {
       					  mobnum.setText("");
       	       			  pass.setText("");
       					   dialog.dismiss();
    				
    				}
       			});
       			  dialog.show();
    			  }
 }
});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    public void nulvalue()
	{
		
		
			System.out.println("json null value");
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


    
    class AttemptLogin extends AsyncTask<String, String, String> {
    	@Override
        protected void onPreExecute() {
            super.onPreExecute();
        	username = (EditText) findViewById(R.id.mobnum);
		       passw = (EditText) findViewById(R.id.password);
		       uname = username.getText().toString();
			  
			      password = passw.getText().toString();	
			      
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Validating user");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

		@Override
		protected String doInBackground(String... params) {
		
         
         	  proInfo.clear();
         	  
             List<NameValuePair> params1 = new ArrayList<NameValuePair>();
             
             params1.add(new BasicNameValuePair("username", uname));
             params1.add(new BasicNameValuePair("pswd", password));
             
             JsonParser jLogin = new JsonParser();
             JSONObject json = jLogin.makeHttpRequest(url,"POST", params1);
           
             
             Log.i("tagconvertstr3", "["+json+"]");
             try
             {
            	 if(json != null){
            		 System.out.println("json value::"+json);
            	 JSONObject jUser = json.getJSONObject(TAG_SRESL);
            	 userid = jUser.getString(TAG_USERID );
            	
            	 successL = jUser.getString(TAG_SUCCESS1);
            
            	 proInfo.add(userid);
            	 }
            	
            	 }
             
             catch(JSONException e)
             {
            	 e.printStackTrace();
            	 
             }
             
			
			return null;
		}

       protected void onPostExecute(String file_url) {
    	   super.onPostExecute(file_url);
          System.out.println("successL value::"+successL);
         
          if(JsonParser.jss.equals("empty"))
          {
       	   System.out.println("json null value");
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
          else if(successL != null && successL.equalsIgnoreCase("Yes"))
           {
        	   usrname=uname;
        	   new ViewProfile().execute();
        	
           }
           
           else if(successL.equalsIgnoreCase("No")){
        	   final Dialog dialog = new Dialog(context);
  			 dialog.setContentView(R.layout.custom_dialog);
  			 dialog.setTitle("INFO!");
  			 dialog.setCancelable(false);
  			 dialog.setCanceledOnTouchOutside(false);
  			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
  			  txt.setText("Incorrect username or password.");
  			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
  			  dialogButton.setOnClickListener(new OnClickListener() {
  				  public void onClick(View vd) {
  					 LoginActivity.mobnum.setText("");
   	       			  LoginActivity.pass.setText("");
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
            }
            
 
        }
    	
 
	 class ReadMsg extends AsyncTask<String,String,String>
	    {

			@Override
			protected String doInBackground(String... arg0) {
				
				
			
				String userno = participant.get(2);
				
				String uno = "+1"+userno;
				
				
	           List<NameValuePair> paramsT = new ArrayList<NameValuePair>();
	             
	             paramsT.add(new BasicNameValuePair("usernumber", uno));
	            
	          
	             JsonParser jTWR = new JsonParser();
	             JSONObject jsonT = jTWR.makeHttpRequest(urlTW,"POST", paramsT);
	            
	         
	             Log.i("tagconvertstr1", "["+jsonT+"]");
	             try
	             {
	            	 if(jsonT!=null){
	             	JSONObject tww = jsonT.getJSONObject(TAG_SREST);
	             	Log.i("tagtwilio", "["+tww+"]");
	             	
	             	twilio = tww.getJSONArray(TAG_TWR);
	             	Log.i("tagconvertstr1", "["+twilio+"]");
	             	int i;
	             	for(i=0;i<twilio.length();i++)
	             	{
	             		
	             		JSONObject t1 = twilio.getJSONObject(i);
	             		JSONObject t2 = t1.getJSONObject(TAG_SREST);
	             		 
	             		String fno = t2.getString(TAG_FRNUM);
	             	System.out.println(fno);
	             		String tonumber = t2.getString(TAG_TONUM);
	             		
	             		String bdy = t2.getString(TAG_CTEXT);
	             	
	             		String dattime =  t2.getString(TAG_DTIME);
	             	
	             		String statust = t2.getString(TAG_STATT);
	             		
	             		fromlist.add(fno);
	             		tolist.add(tonumber);
	             		msgbodylist.add(bdy);
	             		dateandtimelist.add(dattime);
	             		messagestatuslist.add(statust);
	             		System.out.println("list size for receiving messages::"+fromlist.size());
	             		
	            }
	             	
	             	Messagescountlist=twilio.length();
	             System.out.println("Messages count list in login activity::"+Messagescountlist);
	            	 }
	            	 }
	             catch(JSONException e)
	             {
	            	 e.printStackTrace();
	            	 }
			
	             
			
				return null;
			}
			 protected void onPostExecute(String file_url) {
		       	if(JsonParser.jss.equals("empty")){
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
		       	else{
		         calld();
		         pDialog.dismiss();
		       	}
		    }
			
	    }
	 class proInfo extends AsyncTask<String, String, String>

     {
     	@Override
         protected void onPreExecute() {
          
         }
			@Override
			protected String doInBackground(String... arg0) {
			
				String uId1 = LoginActivity.proInfo.get(0);
 		   
              List<NameValuePair> paramsD1 = new ArrayList<NameValuePair>();
              
              paramsD1.add(new BasicNameValuePair("patid", uId1));
             
              JsonParser jDash1 = new JsonParser();
              JSONObject jsonP = jDash1.makeHttpRequest(urlP,"POST", paramsD1);
           
          
              Log.i("tagconvertstr1", "["+jsonP+"]");
              try
              {
      	     if(jsonP!=null)
      	    {
              	JSONObject p = jsonP.getJSONObject(TAG_SRESP);
              	Log.i("tagconvertstr", "["+p+"]");
              	
              	
              	pro = p.getJSONArray(TAG_PROINFO1);
              	Log.i("tagconvertstr1", "["+pro+"]");
              	
              	for(int i=0;i<pro.length();i++)
              	{
              		
              		JSONObject p1 = pro.getJSONObject(i);
              		JSONObject p2 = p1.getJSONObject(TAG_SRESP);
              
              		String adname = p2.getString(TAG_ADMINNAME);
              		
              		String adfname =p2.getString(TAG_ADMINFNAME);
              		
              		String admob = p2.getString(TAG_ADMINMOB);
              	
              		String ademail =p2.getString(TAG_ADMINEMAIL);
              	
              		provider.add(0,adname);
              		provider.add(1,adfname);
              		provider.add(2,admob);
              		provider.add(3,ademail);
              			  
              		
              	} 		
            }
                   
     }catch (JSONException e) {
              e.printStackTrace();
          }
             return null; 
			}


	     	
         protected void onPostExecute(String file_url) {
        		if(JsonParser.jss.equals("empty")){
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
        		else
        		{
        			new ReadMsg().execute();
        		}
   		       	
             /* try
	                {
            	  if(jsonP!=null)
            	  {
	                	JSONObject p = jsonP.getJSONObject(TAG_SRESP);
	                	Log.i("tagconvertstr", "["+p+"]");
	                	
	                	
	                	pro = p.getJSONArray(TAG_PROINFO1);
	                	Log.i("tagconvertstr1", "["+pro+"]");
	                	
	                	for(int i=0;i<pro.length();i++)
	                	{
	                		
	                		JSONObject p1 = pro.getJSONObject(i);
	                		JSONObject p2 = p1.getJSONObject(TAG_SRESP);
	                
	                		String adname = p2.getString(TAG_ADMINNAME);
	                		
	                		String adfname =p2.getString(TAG_ADMINFNAME);
	                		
	                		String admob = p2.getString(TAG_ADMINMOB);
	                	
	                		String ademail =p2.getString(TAG_ADMINEMAIL);
	                	
	                		provider.add(0,adname);
	                		provider.add(1,adfname);
	                		provider.add(2,admob);
	                		provider.add(3,ademail);
	                		new ReadMsg().execute();	  
	                		
	                	} 		
	              }
	                     
	       }catch (JSONException e) {
	                e.printStackTrace();
	            }*/
            
              
				
			}
           
     }
	 class ViewProfile extends AsyncTask<String, String, String> {
     	@Override
          protected void onPreExecute() {
              super.onPreExecute();
          }

  		@Override
  		protected String doInBackground(String... args) {
  		
  		
  		     String uId = LoginActivity.proInfo.get(0);
  		     System.out.println(uId);
           	
               List<NameValuePair> paramsD = new ArrayList<NameValuePair>();
               paramsD.add(new BasicNameValuePair("loginid", uId));
              
               JsonParser jDash = new JsonParser();
               JSONObject jsonDV = jDash.makeHttpRequest(urlD,"POST", paramsD);
            
               
               Log.i("tagconvertstr1", "["+jsonDV+"]");
               try
	                {
            	   if(jsonDV!=null)
            	   {
	                	JSONObject c = jsonDV.getJSONObject(TAG_SRESD);
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
		                     msgprf=c2.getString(TAG_MSGG);
		 	        System.out.println("message preference value:::"+msgprf);
		 	                  participant.add(0,fname);
			             	  participant.add(1,uname);
			             	  participant.add(2,mobile);
			             	  participant.add(3,email);
			             	  participant.add(4,gender);
			             	  participant.add(5,age);
			             	  participant.add(6,city);
			             	  participant.add(7,educat);
			             	  participant.add(8,medical);
			             	  participant.add(9,t1);
			             	  participant.add(10,t2);
			             	  participant.add(11,t3);
			             	  participant.add(12,proname);
			             	  participant.add(13,group);
			             	  participant.add(14,timeformat1);
			             	  participant.add(15,timeformat2);
			             	  participant.add(16,timeformat3);
			             	 participant.add(17,msgprf);
	                	}     
			     		                   
		   }
	                	
	              
	            }catch (JSONException e) {
	                e.printStackTrace();
	            }
               
               
  			return null;
  		}

  	
       protected void onPostExecute(String file_url) {
    		if(JsonParser.jss.equals("empty")){
		       	 final Dialog dialog = new Dialog(context);
				 dialog.setContentView(R.layout.custom_dialog);
				 dialog.setTitle("INFO!");
				 dialog.setCancelable(false);
				 dialog.setCanceledOnTouchOutside(false);
				 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
				  txt.setText("Server not connected!");
				  Button dialogButton = (Button) dialog.findViewById(R.id.release);
				  dialogButton.setOnClickListener(new OnClickListener() {
					  public void onClick(View vd) {
						   dialog.dismiss();
					
					}
				});
				  dialog.show();
				  pDialog.dismiss();
		       	}
    		else
    		{
    	   new proInfo().execute();
    		}
    }
      	
      }
     
    private void calld(){
     	              Intent sigout=new Intent(getApplicationContext(),DashBoardActivity.class);
			    			startActivity(sigout); 
             finish();
     }
	
	 }
 


