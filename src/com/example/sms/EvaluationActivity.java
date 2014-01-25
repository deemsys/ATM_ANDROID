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
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import com.deemsysinc.sms.R;


public class EvaluationActivity extends Activity {
	 public static ArrayList<String> evaluation= new ArrayList<String>();
	 public static ArrayList<String> counting= new ArrayList<String>();
	 public static ArrayList<String> continuouscount= new ArrayList<String>();
	 public static final String urlEN = "http://www.medsmonit.com/bcreasearchT/Service/genericUpdate.php?service=sequenceUpdate"; 
	 private static String urlEV = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=sequenceSelect";
	    private static final String TAG_WLSEQ = "Weekly_logs sequence List";
	 
	    private static final String TAG_SRESS= "serviceresponse";
	  static int number=1;
	  String weeknumber;
	  	private static final String TAG_CONTINUES = "continuous";
	   	private static final String TAG_COUNTS = "count";
	   	static int value=0;
	    public static int numbercount=0;
	    int weeek;
	    JSONArray userEV = null; 
public int weeklynumcount=0;
public int four=0;
	final Context context=this;
    int day;
  public static int countty=0;
  Spinner days;
    public static String count;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate);
        ActionBar actbar=getActionBar();
        actbar.show();
       weeknumber=DashBoardActivity.week.get(0);
      weeek=Integer.parseInt(weeknumber);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        final Dialog dialog = new Dialog(context);
	   
		 dialog.setContentView(R.layout.custom_dialog);
		dialog.setTitle("Weekly Evaluation");
		 dialog.setCancelable(false);
		 dialog.setCanceledOnTouchOutside(false);
		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		  txt.setText("System Begins Your Evaluation.");
		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
		  dialogButton.setOnClickListener(new OnClickListener() {
			  public void onClick(View vd) {
				   dialog.dismiss();
		
		}
		});
		  dialog.show();
        TextView text=(TextView)findViewById(R.id.textView1);
        String que=text.getText().toString();
        evaluation.add(0,que);
      spinvaluate();
        new SequenceCheck().execute();
       
   			  
        Button send=(Button)findViewById(R.id.send);
     	
        send.setOnClickListener(new View.OnClickListener() {
             
         	public void onClick(View v) {
                    Spinner days1 = (Spinner)findViewById(R.id.dayspin);
		    	    String dayval =days1.getSelectedItem().toString();
		    	     if(dayval.equals("Select Day")){
		    		 final Dialog dialog = new Dialog(context);
           			 dialog.setContentView(R.layout.custom_dialog);
           			 dialog.setTitle("Invalid day");
           			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
           			  txt.setText("Invalid day selection.Please Select days from 1 to 7");
           			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
           			  dialogButton.setOnClickListener(new OnClickListener() {
           				  public void onClick(View vd) {
           					   dialog.dismiss();
        				
        				}
           			});
           			  dialog.show();
        			  }
		    	 else{
		    		  day=Integer.parseInt(dayval);
		    		  val(day);
		    	 }
         	     evaluation.add(1,dayval);
          
               //  System.out.println("In evaluation page value at 0 pos::"+evaluation.get(0));
                // System.out.println("In evaluation page vale::"+evaluation.get(1));
                	
                 
         	}
                 
         	   }); 
        
}
public void spinvaluate(){
	 Spinner daysp= (Spinner) findViewById(R.id.dayspin);
 	ArrayAdapter<CharSequence> dayadapt= ArrayAdapter.createFromResource(
 	       this, R.array.selectday, android.R.layout.simple_spinner_item);
 	dayadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 	daysp.setAdapter(dayadapt);
  	 String dayva =daysp.getSelectedItem().toString();
  	day=Integer.parseInt(dayva);
  	daysp.setOnItemSelectedListener(new OnItemSelectedListener() {
  	    @Override
  	    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
  	    	System.out.println("Psition value is::"+position);
  			if(position>5){
  				
  		    switch (weeek){
  		        case 1:
  		        	 final Dialog dialog = new Dialog(context);
  	       			 dialog.setContentView(R.layout.custom_dialog);
  	       			 dialog.setTitle("Weekly Survey");
  	       		dialog.setCancelable(false);
   			 dialog.setCanceledOnTouchOutside(false);
  	       			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
  	       			  txt.setText("Well done!");
  	       			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
  	       			  dialogButton.setOnClickListener(new OnClickListener() {
  	       				  public void onClick(View vd) {
  	       					   dialog.dismiss();
  	    				
  	    				}
  	       			});
  	       			  dialog.show();
  				     break;         
  		           
  		        case 2:
  		        	final Dialog dialog1 = new Dialog(context);
  	      			 dialog1.setContentView(R.layout.custom_dialog);
  	      			 dialog1.setTitle("Weekly Survey");
  	      			dialog1.setCancelable(false);
       			 dialog1.setCanceledOnTouchOutside(false);
  	      			 TextView txt1 = (TextView) dialog1.findViewById(R.id.errorlog);
  	      			  txt1.setText("Keep up the good work!");
  	      			  Button dialogButton1 = (Button) dialog1.findViewById(R.id.release);
  	      			  dialogButton1.setOnClickListener(new OnClickListener() {
  	      				  public void onClick(View vd) {
  	      					   dialog1.dismiss();
  	   				
  	   				}
  	      			});
  	      			  dialog1.show();
  			            
  			     break;  
  		            
  		        case 3:
  		        	final Dialog dialog11 = new Dialog(context);
  	     			 dialog11.setContentView(R.layout.custom_dialog);
  	     			 dialog11.setTitle("Weekly Survey");
  	     			dialog11.setCancelable(false);
       			 dialog11.setCanceledOnTouchOutside(false);
  	     			 TextView txt11= (TextView) dialog11.findViewById(R.id.errorlog);
  	     			  txt11.setText("Way to go!");
  	     			  Button dialogButton11 = (Button) dialog11.findViewById(R.id.release);
  	     			  dialogButton11.setOnClickListener(new OnClickListener() {
  	     				  public void onClick(View vd) {
  	     					   dialog11.dismiss();
  	  				
  	  				}
  	     			});
  	     			  dialog11.show();
  		       
  		            break;
  		        case 4:
  		        	final Dialog dialog2 = new Dialog(context);
  	    			 dialog2.setContentView(R.layout.custom_dialog);
  	    			 dialog2.setTitle("Weekly Survey");
  	    			dialog2.setCancelable(false);
       			 dialog2.setCanceledOnTouchOutside(false);
  	    			 TextView txt2= (TextView) dialog2.findViewById(R.id.errorlog);
  	    			  txt2.setText("Nice job! ");
  	    			  Button dialogButton2 = (Button) dialog2.findViewById(R.id.release);
  	    			  dialogButton2.setOnClickListener(new OnClickListener() {
  	    				  public void onClick(View vd) {
  	    					   dialog2.dismiss();
  	 				
  	 				}
  	    			});
  	    			  dialog2.show();
  		            
  		            break;
  		        case 5:
  		        	final Dialog dialog4= new Dialog(context);
  	   			 dialog4.setContentView(R.layout.custom_dialog);
  	   			 dialog4.setTitle("Weekly Survey");
  	   		dialog4.setCancelable(false);
			 dialog4.setCanceledOnTouchOutside(false);
  	   			 TextView txt4= (TextView) dialog4.findViewById(R.id.errorlog);
  	   			  txt4.setText("Keep it up!");
  	   			  Button dialogButton4 = (Button) dialog4.findViewById(R.id.release);
  	   			  dialogButton4.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog4.dismiss();
  					
  					}
  	   			});
  	   			  dialog4.show();
  		            break;
  		            
  		        case 6:
  		        	final Dialog dialog5 = new Dialog(context);
  	   			 dialog5.setContentView(R.layout.custom_dialog);
  	   			 dialog5.setTitle("Weekly Survey");
  	   		dialog5.setCancelable(false);
			 dialog5.setCanceledOnTouchOutside(false);
  	   			 TextView txt5= (TextView) dialog5.findViewById(R.id.errorlog);
  	   			  txt5.setText("Fantastic!");
  	   			  Button dialogButton5= (Button) dialog5.findViewById(R.id.release);
  	   			  dialogButton5.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog5.dismiss();
  					
  					}
  	   			});
  	   			  dialog5.show();
  		            break;
  		        case 7:
  		        	final Dialog dialog6 = new Dialog(context);
  	   			 dialog6.setContentView(R.layout.custom_dialog);
  	   			 dialog6.setTitle("Weekly Survey");
  	   		dialog6.setCancelable(false);
			 dialog6.setCanceledOnTouchOutside(false);
  	   			 TextView txt6= (TextView) dialog6.findViewById(R.id.errorlog);
  	   			  txt6.setText("Great job staying on track!");
  	   			  Button dialogButton6= (Button) dialog6.findViewById(R.id.release);
  	   			  dialogButton6.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog6.dismiss();
  					
  					}
  	   			});
  	   			  dialog6.show();
  		            break;
  		        case 8:
  		        	final Dialog dialog27= new Dialog(context);
  	   			 dialog27.setContentView(R.layout.custom_dialog);
  	   			 dialog27.setTitle("Weekly Survey");
  	   		dialog27.setCancelable(false);
			 dialog27.setCanceledOnTouchOutside(false);
  	   			 TextView txt27= (TextView) dialog27.findViewById(R.id.errorlog);
  	   			  txt27.setText("Wonderful!");
  	   			  Button dialogButton27 = (Button) dialog27.findViewById(R.id.release);
  	   			  dialogButton27.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog27.dismiss();
  					
  					}
  	   			});
  	   			  dialog27.show();
  		            break;
  		            
  		        case 9:
  		        	final Dialog dialog28 = new Dialog(context);
  	   			 dialog28.setContentView(R.layout.custom_dialog);
  	   			 dialog28.setTitle("Weekly Survey");
  	   		dialog28.setCancelable(false);
			 dialog28.setCanceledOnTouchOutside(false);
  	   			 TextView txt28= (TextView) dialog28.findViewById(R.id.errorlog);
  	   			  txt28.setText("Sweet!");
  	   			  Button dialogButton28 = (Button) dialog28.findViewById(R.id.release);
  	   			  dialogButton28.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog28.dismiss();
  					
  					}
  	   			});
  	   			  dialog28.show();
  		            break;
  		        case 10:
  		        	final Dialog dialog9= new Dialog(context);
  	   			 dialog9.setContentView(R.layout.custom_dialog);
  	   			 dialog9.setTitle("Weekly Survey");
  	   		dialog9.setCancelable(false);
			 dialog9.setCanceledOnTouchOutside(false);
  	   			 TextView txt9= (TextView) dialog9.findViewById(R.id.errorlog);
  	   			  txt9.setText("You're taking care of yourself!");
  	   			  Button dialogButton9 = (Button) dialog9.findViewById(R.id.release);
  	   			  dialogButton9.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog9.dismiss();
  					
  					}
  	   			});
  	   			  dialog9.show();
  		            break;
  		        case 11:
  		        	final Dialog dialog10 = new Dialog(context);
  	   			 dialog10.setContentView(R.layout.custom_dialog);
  	   			 dialog10.setTitle("Weekly Survey");
  	   		dialog10.setCancelable(false);
			 dialog10.setCanceledOnTouchOutside(false);
  	   			 TextView txt10= (TextView) dialog10.findViewById(R.id.errorlog);
  	   			  txt10.setText("Awesome!");
  	   			  Button dialogButton10 = (Button) dialog10.findViewById(R.id.release);
  	   			  dialogButton10.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog10.dismiss();
  					
  					}
  	   			});
  	   			  dialog10.show();
  		            break;
  		        case 12:
  		        	final Dialog dialog114= new Dialog(context);
  	   			 dialog114.setContentView(R.layout.custom_dialog);
  	   			 dialog114.setTitle("Weekly Survey");
  	   		dialog114.setCancelable(false);
			 dialog114.setCanceledOnTouchOutside(false);
  	   			 TextView txt114= (TextView) dialog114.findViewById(R.id.errorlog);
  	   			  txt114.setText("Keep the streak alive!");
  	   			  Button dialogButton114 = (Button) dialog114.findViewById(R.id.release);
  	   			  dialogButton114.setOnClickListener(new OnClickListener() {
  	   				  public void onClick(View vd) {
  	   					   dialog114.dismiss();
  					
  					}
  	   			});
  	   			  dialog114.show();
  		            break;
  		            
  		    }
  		
  			}
  			
  	    }

  	    @Override
  	    public void onNothingSelected(AdapterView<?> parentView) {
  	      
  	    }

  	});
			 
  
}
	
@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	            evaluation.clear();
	            Intent intentSignUP=new Intent(getApplicationContext(),DashBoardActivity.class);
    			startActivity(intentSignUP);  
	        	 finish();
	      }
	     return true;
	 }

private void val(int day)
{
	
if(day<6){
	int a=1;
	  count=Integer.toString(a);
	  System.out.println("counting list size:::"+counting.size());
	  for(int i=0;i<counting.size();i++){
		if(counting.get(i).equals("1")){
			value++;
		}
	  }
	  System.out.println("value value in eval activity:::"+value);
	  
	  
	  
	  int due=counting.size()-1;
		 System.out.println("countty value b4 for loop:::"+countty);
		  
	int nub=0;
	  for(int i=0;i<due;i++){
		  System.out.println("dueis:"+due);
		  System.out.println("i value::"+i);
		  if(counting.get(i).equals("1")){
			  nub=i+1;
		  }
		  System.out.println("nub value::"+nub);
		 System.out.println("countty value b4 if loop:::"+countty);
		  System.out.println("number variable value::"+number);
		if((counting.get(i).equals("1")&&counting.get(number).equals("1"))||((counting.get(i).equals("1")&&(counting.get(nub).equals("1"))))){
			System.out.println("values of count list::"+counting.get(i));
			System.out.println("values of count list::"+counting.get(number));
			number++;
			countty++;
		}
		  
		  
		System.out.println("countty value::"+countty); 
	  }
	  
	  
	  
	System.out.println("countty value::"+countty);
		if(countty==1||countty==2){
			Intent intentSignUP=new Intent(getApplicationContext(),CountingActivity.class);
			startActivity(intentSignUP);
			new EndUpdate().execute();
			
		}
		
		
		else if(value==3){
			Intent intentSignUP1=new Intent(getApplicationContext(),RandomActivity.class);
			startActivity(intentSignUP1);
		}
		else{
			 Intent intentSignUP1=new Intent(getApplicationContext(),Questionnaire.class);
				startActivity(intentSignUP1);
		}
	  }
		
		

	else{
		int b=0;
		count=Integer.toString(b);
		System.out.println("Week number::"+weeek);
	Questionnaire.questionnaire.add(0,"");
	Questionnaire.questionnaire.add(1,"");
		 Intent intentSignUP=new Intent(getApplicationContext(),WeeklyendActivity.class);
		startActivity(intentSignUP);
	}
}	


 
  class EndUpdate extends AsyncTask<String,String,String>
	 {
		@Override
		protected String doInBackground(String... params) {
	
			String LogId4 = LoginActivity.proInfo.get(0);
			List<NameValuePair> paramsEN = new ArrayList<NameValuePair>();
			paramsEN.add(new BasicNameValuePair("loginid", LogId4));
			 JsonParser jLogin = new JsonParser();
			
          JSONObject jsonEN = jLogin.makeHttpRequest(urlEN,"POST", paramsEN);
          Log.i("tagconvertstr3", "["+jsonEN+"]");
      
			return null;
		}
		 @Override
		 protected void onPostExecute(String file_url) {
			    if(JsonParser.jss.equalsIgnoreCase("empty")){
		           	 final Dialog dialog = new Dialog(context);
					     
		      			 dialog.setContentView(R.layout.custom_dialog);
		      			 dialog.setTitle("Failed");
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
			    else{
			 counting.clear();
			 continuouscount.clear();
		    countty=0;
			count="0";
			number=1;
			System.out.println("on post execute of endupdate:::"+count);
		 }
		 }
	 }

class SequenceCheck extends AsyncTask<String,String,String>{

	@Override
	protected String doInBackground(String... params) {
	
		 String LogId3 = LoginActivity.proInfo.get(0);
		 List<NameValuePair> paramsEV = new ArrayList<NameValuePair>();
		 paramsEV.add(new BasicNameValuePair("loginid", LogId3));
		 JsonParser jLogin = new JsonParser();
		 
         JSONObject jsonEV = jLogin.makeHttpRequest(urlEV,"POST", paramsEV);
         Log.i("tagconvertstr3", "["+jsonEV+"]");
         try
         {
        	 if(jsonEV!=null){
        	 JSONObject ev = jsonEV.getJSONObject(TAG_SRESS);
 	         Log.i("tagconvertstr", "["+ev+"]");
             	
             	
             
             	userEV = ev.getJSONArray(TAG_WLSEQ);
             	Log.i("tagconvertstr1", "["+userEV+"]");
             	counting.clear();
          	  continuouscount.clear();
             	
             	for(int i=0;i<userEV.length();i++)
             	{
             		
             		JSONObject c1 = userEV.getJSONObject(i);
             		JSONObject c2 = c1.getJSONObject(TAG_SRESS);
             	
             		
	                
	                
	           
	                	String  cont2 = c2.getString(TAG_CONTINUES);
	               
	                	String countt2 = c2.getString(TAG_COUNTS);
	                
	                	counting.add(countt2);
	                	continuouscount.add(cont2);
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
      
                     if(JsonParser.jss.equalsIgnoreCase("empty")){
           	 final Dialog dialog = new Dialog(context);
			     
      			 dialog.setContentView(R.layout.custom_dialog);
      			 dialog.setTitle("Failed");
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
}
}