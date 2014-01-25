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

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;

import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.deemsysinc.sms.R;


public class EditProfileActivity extends Activity {
	   final Context context=this;
	   public ProgressDialog pDialog;
	   RadioButton male,female;
	   RadioButton maleee,femaleee;
	   int selectedId;
	   JSONObject jsonPID ;
	   JSONObject jsonPG ;
	   Button uppro;
	   RadioGroup gendering;
	   JsonParser jsonPE = new JsonParser();
	   JSONArray userUP= null;
	   JSONArray userUG= null;
	   JSONArray userUID = null;
	   Boolean temp1 = false;
	    Boolean check12 = false;
	    String providername;
	    String usrnamee;
	    boolean t = true; 
	    public static int prsize;
	    MultiSelectionSpinner1 spinUP;
	    public static String prname155;
	    int a;
	    private RadioButton radioSexButton;
	    String prosUA,prosUB,prosUC;
	//   private static final String TAG_PROLISTUP = "Providers List";
	    
	   // private static final String TAG_SRESUP= "serviceresponse";
	   
	  //  private static final String TAG_NAMEUP = "Provider username";
	    private static final String TAG_SRESU= "serviceresponse";
	    private static final String TAG_MESSAGEU= "message";
	    private static final String TAG_SUCCESSU= "success";
		      
	  

	   Spinner spinnerU1;
	   private static final String TAG_PRGROUPLIST = "Group List";
		private static final String TAG_SRESPG = "serviceresponse";
	
		private static final String TAG_GNAMEPG = "groupname";
		private static final String TAG_GRID = "groupid";
		 private static final String TAG_SUCCESSUG = "success";
		  private static final String TAG_SRESPID= "serviceresponse";
		 
		   private static final String TAG_PGLID = "Participants_groups List";
		
			
			private static final String TAG_GNAMEPID = "group_name";
			private static final String TAG_GRPID= "group_id";
			 private static final String TAG_SUCCESSPID = "success";
		EditText firstname,usrname,mobile,mailid,city;
		String successt1;
		String successtag1;
		String mss;
		String successu;
		 boolean status = MultiSelectionSpinner1.foundOne;
		
		 public static ArrayList<String> prGID= new ArrayList<String>();
		 public static ArrayList<String> prID= new ArrayList<String>();
		public static ArrayList<String> prgrouplistIN= new ArrayList<String>();
	      public static ArrayList<String> prgrouplistUP= new ArrayList<String>();
	      public static ArrayList<String> providerlistUP= new ArrayList<String>();
	      public static ArrayList<String> prgridlistUP= new ArrayList<String>();
	   public static TextView pgname,username;
	  private static String urlUP = "http://www.medsmonit.com/bcreasearchT/Service/genericUpdate.php?service=patientupdate";
	 
	   private static String urlPG = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=getGroups";
	   private static String urlPID = "http://www.medsmonit.com/bcreasearchT/Service/genericSelect.php?service=participantsgrouplist";
	/*   private static String urlUP = "http://192.168.1.200/bcreasearchT/Service/genericUpdate.php?service=patientupdate";
		 
	   private static String urlPG = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=getGroups";
	   private static String urlPID = "http://192.168.1.200/bcreasearchT/Service/genericSelect.php?service=participantsgrouplist";*/
	   String loginid,pswd,oldmail,oldmob,first,second,third,fourth,fifth,sixth,seventh,eight,ninth,tenth,eleventh,twelth,thirteenth,fourteenth,fifteenth,sixteenth,seventeenth;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
        
	        setContentView(R.layout.editprofile);
	        ActionBar actbar=getActionBar();
	        actbar.show();
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	       new ProviderGroup().execute();
	     maleee = (RadioButton)findViewById(R.id.radi);
	      femaleee = (RadioButton)findViewById(R.id.rad1);
	      
	        showprofile();
	        mobile=(EditText)findViewById(R.id.mobedit);
	        InputFilter filter = new InputFilter() {

	            
	    		@Override
	    		public CharSequence filter(CharSequence source, int start, int end,
	    				Spanned dest, int dstart, int dend) {
	    			
	    			 if (source.length() > 0) {

	                     if (!Character.isDigit(source.charAt(0)))
	                         return "";
	                     else {
	                         if (dstart == 3) {
	                             return source + ") ";
	                         } else if (dstart == 0) {
	                             return "(" + source;
	                         } else if ((dstart == 5) || (dstart == 9))
	                             return "-" + source;
	                         else if (dstart >= 14)
	                             return "";
	                     }

	                 } else {

	                 }
	    			return null;
	    		}
	            };

	            mobile.setFilters(new InputFilter[] { filter });
	            mobile.addTextChangedListener(new TextWatcher() {
	                public void afterTextChanged(Editable s) {
	                	System.out.println("mobile number pressed::"+mobile.getText().toString());
	                    Validation.isPhoneNumber(mobile, true);
	                }
	                public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	                public void onTextChanged(CharSequence s, int start, int before, int count){}
	            });
	        city=(EditText)findViewById(R.id.citedit);
	        city.addTextChangedListener(new TextWatcher() {
	            public void afterTextChanged(Editable s) {
	                Validation.iszip(city, true);
	            }
	            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	            public void onTextChanged(CharSequence s, int start, int before, int count){}
	        });
	        
	        
	         uppro=(Button)findViewById(R.id.edit);
	            uppro.setOnClickListener(new View.OnClickListener() {
       	 
         public void onClick(View arg0) {
         	 if ( checkValidation () ){
         		if(MultiSelectionSpinner1.mm>4)
	            {
	            System.out.println("ss value is greater than 5");
			  final Dialog dialog = new Dialog(EditProfileActivity.this);
					 dialog.setCancelable(false);
	    			 dialog.setCanceledOnTouchOutside(false);
    	 		 dialog.setContentView(R.layout.custom_dialog);
    	 		 dialog.setTitle("Update Failed");
    	 		
    	 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
    	 		  txt.setText("More than Four Groups are not allowed ");
    	 		  Button dialogButton = (Button) dialog.findViewById(R.id.release);
    	 		  dialogButton.setOnClickListener(new OnClickListener() {
    	 			  public void onClick(View vd) {
    	 				
    	 				   dialog.dismiss();
    	 		
    	 		}
    	 		});
    	 		  dialog.show();
	            }
         		
         		else 
         		{
         			 String grname =ViewProfileActivity.participantview.get(13);
         			 System.out.println("grname is" + grname);
         			if(MultiSelectionSpinner1.mspin.equalsIgnoreCase(grname))
         			{
         			MultiSelectionSpinner1.mspin=grname;
         				System.out.println("checking groupname selected");
         				System.out.println("value is" + MultiSelectionSpinner1.mspin);
         			new PrgroupId().execute();
         			System.out.println("arraylist started to convert");
         			
                     System.out.println("wwwwwwwww");
         			
         			submitForm();
         			System.out.println("vvvvvvv");
         			}
         			else
         			{
         				System.out.println("selected group is differ from old one");
         				submitForm();
         			}
         		}
         		
              }
         	
         	
 			
         }
     });
}
private boolean checkValidation() {
   boolean ret = true;
 
   if (!Validation.hasText(firstname)) ret = false;
  
   if (!Validation.isEmailAddress(mailid, true)) ret = false;
   if (!Validation.isPhoneNumber(mobile, true)) ret = false;
 return ret;
}
private void submitForm() {
	  
	 EditText city=(EditText)findViewById(R.id.citedit);
	   String zipp=city.getText().toString();
	   if(zipp.equals("")||zipp.length()==5){
	
		  update();
	   }
	   else{
		   final Dialog dialog = new Dialog(EditProfileActivity.this);
			 dialog.setContentView(R.layout.custom_dialog);
			 dialog.setTitle("Update Failed");
			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
			  txt.setText("Enter Valid Zipcode!");
			  dialog.setCancelable(false);
			 dialog.setCanceledOnTouchOutside(false);
			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
			  dialogButton.setOnClickListener(new OnClickListener() {
				  public void onClick(View vd) {
					  
					  dialog.dismiss();
				
				}
			});
			  dialog.show(); 
	   }
		  
	  
	 
	
}



public void update(){
	new updateprofileTask().execute();	
	
	 
}
	
@Override
	    public boolean onTouchEvent(MotionEvent event) {
	        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
	                                                        INPUT_METHOD_SERVICE);
	        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	        return true;
	    }

	 private void showprofile(){
	       firstname=(EditText)findViewById(R.id.firstedit);
	        String fn=ViewProfileActivity.participantview.get(0);
	        firstname.setText(fn);
	        
	        username=(TextView)findViewById(R.id.useredit);
		       String usrn=ViewProfileActivity.participantview.get(1);
		       username.setText(usrn);
	   
	      
	    mobile=(EditText)findViewById(R.id.mobedit);
	      String mobi=ViewProfileActivity.participantview.get(2);
	      mobi = "(" + mobi.substring( 0,3 ) + ") " + mobi.substring( 3,6 ) + "-" + mobi.substring( 6,10 );
	        System.out.println("In edit profile"+mobi);
	        mobile.setText(mobi);
	        
	       
	     
	     mailid=(EditText)findViewById(R.id.mailledit);
	      String maii=ViewProfileActivity.participantview.get(3);
	        mailid.setText(maii);
	      
	        TextView pronamee=(TextView)findViewById(R.id.providertext);
		       String pro=ViewProfileActivity.participantview.get(12);
		       pronamee.setText(pro);
		       
		       
		       
		        pgname=(TextView)findViewById(R.id.progroup);
		       String prog=ViewProfileActivity.participantview.get(13);
		       pgname.setText(prog);
	         
	        
	      String gendd=ViewProfileActivity.participantview.get(4);
	    
	  
	      
	      male=(RadioButton)findViewById(R.id.radi);
	       female=(RadioButton)findViewById(R.id.rad1);
	       if(gendd.equals("0")){
	    	  
	    	   male.setChecked(true);
	    	  
	       }
	       else
	    	   {
	    	    female.setChecked(true);
	      
	    	   }
	   
	         Spinner age=(Spinner)findViewById(R.id.spinner1);
	         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	                 this, R.array.age_array, android.R.layout.simple_spinner_item);
	         String agg=ViewProfileActivity.participantview.get(5);
		       
	          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	          age.setAdapter(adapter);
		         int spinnerPosition = adapter.getPosition(agg);
			     
			        age.setSelection(spinnerPosition);
			       
	       
	    
	         
	         EditText city=(EditText)findViewById(R.id.citedit);
	      String cit=ViewProfileActivity.participantview.get(6);
	      city.setText(cit);
	     
	         Spinner edu=(Spinner)findViewById(R.id.spinner2);
	         
	         ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
	                 this, R.array.eduarray, android.R.layout.simple_spinner_item);
	          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	          String educa=ViewProfileActivity.participantview.get(7);
		      
	          edu.setAdapter(adapter1);
	          int spinnePosition = adapter1.getPosition(educa);
		        edu.setSelection(spinnePosition);
		       
	 
	       
	       EditText medi=(EditText)findViewById(R.id.meddetedit);
	       String medicat=ViewProfileActivity.participantview.get(8);
	        medi.setText(medicat);
	        
	    
	        
	        
	        
	         Spinner timee1=(Spinner)findViewById(R.id.spinner3);
	         ArrayAdapter<CharSequence> timee1adapter = ArrayAdapter.createFromResource(
			         this, R.array.timer_array, android.R.layout.simple_spinner_item);
	         timee1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      String timee=ViewProfileActivity.participantview.get(9);
	    
        timee1.setAdapter(timee1adapter);
        int spinneosition = timee1adapter.getPosition(timee);
	    
	        timee1.setSelection(spinneosition);
	     
	        Spinner timee2=(Spinner)findViewById(R.id.spinner4);
	        ArrayAdapter<CharSequence> timee2adapter = ArrayAdapter.createFromResource(
			         this, R.array.timer_array, android.R.layout.simple_spinner_item);
	        timee2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      String time2=ViewProfileActivity.participantview.get(10);
	      timee2.setAdapter(timee2adapter);
      int spinnesition = timee2adapter.getPosition(time2);
     
        timee2.setSelection(spinnesition);
       
	         
	        Spinner timee3=(Spinner)findViewById(R.id.spinner5);
	        ArrayAdapter<CharSequence> timee3adapter = ArrayAdapter.createFromResource(
			         this, R.array.timer_array, android.R.layout.simple_spinner_item);
	        timee3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      String time3=ViewProfileActivity.participantview.get(11);
	   
    timee3.setAdapter(timee3adapter);
    int spinneition = timee3adapter.getPosition(time3);
   
      timee3.setSelection(spinneition);
    
       
       
       
       Spinner ampm1=(Spinner)findViewById(R.id.ampm);
       ArrayAdapter<CharSequence> ampmadapter = ArrayAdapter.createFromResource(
		         this, R.array.meridianarray, android.R.layout.simple_spinner_item);
       ampmadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     String amm1=ViewProfileActivity.participantview.get(14);
     ampm1.setAdapter(ampmadapter);
 int spinnition = ampmadapter.getPosition(amm1);

   ampm1.setSelection(spinnition);
     
    
    Spinner ampm2=(Spinner)findViewById(R.id.spinner8);
    ArrayAdapter<CharSequence> ampmadapter1 = ArrayAdapter.createFromResource(
		         this, R.array.meridianarray, android.R.layout.simple_spinner_item);
    ampmadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  String amm2=ViewProfileActivity.participantview.get(15);
  ampm2.setAdapter(ampmadapter1);
int spinniion = ampmadapter1.getPosition(amm2);

ampm2.setSelection(spinniion);
 
 
 
 Spinner ampm3=(Spinner)findViewById(R.id.spinner9);
 ArrayAdapter<CharSequence> ampmadapter2 = ArrayAdapter.createFromResource(
	         this, R.array.meridianarray, android.R.layout.simple_spinner_item);
 ampmadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
String amm3=ViewProfileActivity.participantview.get(16);
ampm3.setAdapter(ampmadapter2);
int spinition = ampmadapter2.getPosition(amm3);

ampm3.setSelection(spinition);


spinUP = (MultiSelectionSpinner1) findViewById(R.id.spinner7); 

	 }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	       
	    finish();
	     }
	     return true;
	 }
	
	 class PrgroupId extends AsyncTask<String,String,String>
	 {

		@Override
		protected String doInBackground(String... arg0) {
			
			prGID.clear();
			prID.clear();
			String patidd = LoginActivity.proInfo.get(0);
			List<NameValuePair> pIdd = new ArrayList<NameValuePair>();
            pIdd.add(new BasicNameValuePair("loginid", patidd));
            
            jsonPID = jsonPE.makeHttpRequest(urlPID, "POST", pIdd);
            Log.i("tagconvertstr1", "["+jsonPID+"]");
            try
            {
            	if(jsonPID!=null){
            	JSONObject pi = jsonPID.getJSONObject(TAG_SRESPID);
         
          prGID.clear();
          prID.clear();
          
            	 successt1=pi.getString(TAG_SUCCESSPID);
        	
        		userUID =pi.getJSONArray(TAG_PGLID);
            	for(int i=0;i<userUID.length();i++)
            	{
            		System.out.println("grouplist id group");
            		JSONObject pd1 = userUID.getJSONObject(i);
            		JSONObject pd2 = pd1.getJSONObject(TAG_SRESPID);
            		String groupname = pd2.getString(TAG_GNAMEPID);
            		
            		String gid = pd2.getString(TAG_GRPID);
            	
            	
            		
            		
            	
               prGID.add(groupname);
                
               prID.add(gid);
                
            	
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
			
	if(JsonParser.jss.equalsIgnoreCase("empty")){
		
	}
	else{
			int  pr=prGID.size();
			StringBuilder sb = new StringBuilder();
		    for (String s : prGID) {
		   	 System.out.println("converting arraylist to string");
		           sb.append(s);
		   
		          pr--;
		           if(pr>0)
		           {
		        	   System.out.println("prgid size is" + pr);
		        	   System.out.println("sb.lenth" + sb.length());
		                 sb.append("-");
		           }
		    }
		    String aaa = sb.toString();
		    System.out.println("returnedItems is" + aaa);
		    
		    int pr1=prID.size();
		    StringBuilder sb1 = new StringBuilder();
		    for (String s1 : prID) {
		   	 
		           sb1.append(s1);
		         
		          pr1--;
		           if(pr1>0)
		           {
		        	   System.out.println("prID isze"+pr1);
		        	   System.out.println("sb1.lenth" + sb.length());
		                 sb1.append("-");
		           System.out.println("converting arraylist1 to string");
		           }
		    }
		    String bbb = sb1.toString();
		    System.out.println("returnedItems1 is" + bbb);
		    MultiSelectionSpinner1.mspin1 = aaa;
		    System.out.println("returnedItems 1234" + MultiSelectionSpinner1.mspin1);
		    MultiSelectionSpinner1.mspin2 = bbb;
		    System.out.println("returnedItems  34566 is" + MultiSelectionSpinner1.mspin2);
		}
		} 
	 }
	 class ProviderGroup extends AsyncTask<String,String,String>
		{

		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	        		
				      
	            pDialog = new ProgressDialog(EditProfileActivity.this);
	            pDialog.setMessage("Loading Provider Group");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
			@Override
			protected String doInBackground(String... params) {
			
         
           String pross = ViewProfileActivity.participantview.get(12);

             List<NameValuePair> paramsPG = new ArrayList<NameValuePair>();
             paramsPG.add(new BasicNameValuePair("providername", pross));
            
				JsonParser jparserPG = new JsonParser();
             jsonPG = jparserPG.makeHttpRequest(urlPG, "POST", paramsPG);
             Log.i("tagconvertstr1", "["+jsonPG+"]");
             try
             {
            	 if(jsonPG!=null){
             	JSONObject pg = jsonPG.getJSONObject(TAG_SRESPG);
          
           
             	prgrouplistUP.clear();
             	prgridlistUP.clear();
             	 successt1=pg.getString(TAG_SUCCESSUG);
        
         		userUG =pg.getJSONArray(TAG_PRGROUPLIST);
             	for(int i=0;i<userUG.length();i++)
             	{
             		JSONObject pg1 = userUG.getJSONObject(i);
             		JSONObject pg2 = pg1.getJSONObject(TAG_SRESPG);
             		String groupname = pg2.getString(TAG_GNAMEPG);
             		String gid = pg2.getString(TAG_GRID);
             		
             	
                 prgrouplistUP.add(groupname);
                 
                 prgridlistUP.add(gid);
                 
             	
             		System.out.println("providerlist size is " + prgrouplistUP.size());
             	 prsize = prgrouplistUP.size();
             	System.out.println("prsize is" + prsize);
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
				pDialog.dismiss();
				if(JsonParser.jss.equalsIgnoreCase("empty")){
					uppro.setEnabled(false);
					 final Dialog dialog = new Dialog(context);
				     
					 dialog.setContentView(R.layout.custom_dialog);
					 dialog.setTitle("Update Failed");
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
				else if(successt1.equals("Yes")){
					
				spinUP = (MultiSelectionSpinner1) findViewById(R.id.spinner7);
				if(EditProfileActivity.prgrouplistUP!=null)
				{
					
				spinUP.setItems1(EditProfileActivity.prgrouplistUP);
				}
				else
				{
					 Intent sigout1=new Intent(getApplicationContext(),EditProfileActivity.class);
		    			startActivity(sigout1);   
				}
					
				}
				else{
					spinUP = (MultiSelectionSpinner1) findViewById(R.id.spinner7);
					EditProfileActivity.prgrouplistUP.clear();
					EditProfileActivity.prgrouplistIN.clear();
				
					prgrouplistUP.add(0,"");
					
					
				
					
				
		spinUP.setIte(EditProfileActivity.prgrouplistUP);
					
					
					 final Dialog dialog = new Dialog(EditProfileActivity.this);
        	 	     
        	 		 dialog.setContentView(R.layout.custom_dialog);
        	 		 dialog.setTitle("Oops");
        	 		 dialog.setCancelable(false);
        			 dialog.setCanceledOnTouchOutside(false);
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
		
	 
	 class updateprofileTask extends AsyncTask <String,String,String> {
		 
			@Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	        
				      
	            pDialog = new ProgressDialog(EditProfileActivity.this);
	            pDialog.setMessage("UPDATING USER DETAILS");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
		 @Override
			protected String doInBackground(String... params) {
			 String sex;
			 gendering = (RadioGroup) findViewById(R.id.radioGroup2);
		     
			  selectedId = gendering.getCheckedRadioButtonId();
			
			 radioSexButton = (RadioButton) findViewById(selectedId);
			
			 String selectedValue = radioSexButton.getText().toString();
			
			 if(selectedValue.equals("Male")){
				 sex="0";
			 }
			 else
			 { sex="1";
			 }
			 EditText firstname=(EditText)findViewById(R.id.firstedit);
			
			  EditText mobile=(EditText)findViewById(R.id.mobedit);
			  EditText mailid=(EditText)findViewById(R.id.mailledit);
			   Spinner age=(Spinner)findViewById(R.id.spinner1);
			   
			   EditText city=(EditText)findViewById(R.id.citedit);
			  seventh=city.getText().toString();
			
			   Spinner edu=(Spinner)findViewById(R.id.spinner2);
			   EditText medi=(EditText)findViewById(R.id.meddetedit);
			   Spinner timee1=(Spinner)findViewById(R.id.spinner3);
			   Spinner timee2=(Spinner)findViewById(R.id.spinner4);
			   Spinner timee3=(Spinner)findViewById(R.id.spinner5);
			   Spinner ampm1=(Spinner)findViewById(R.id.ampm);
			   Spinner ampm2=(Spinner)findViewById(R.id.spinner8);
			   Spinner ampm3=(Spinner)findViewById(R.id.spinner9);
	         	first=firstname.getText().toString();
	                           
	         	
	         System.out.println("second is " );
	         String	second2 = ViewProfileActivity.participantview.get(1);
	         	System.out.println("third is" + second2);
	         	third=mobile.getText().toString();
	         	third= third.replaceAll("\\D+","");
	         	fourth=mailid.getText().toString();
	         	fifth=sex;
	         
	         	sixth=age.getSelectedItem().toString();
	         
	         	eight=edu.getSelectedItem().toString();
	         	if(eight.equalsIgnoreCase("Select Education")){
	         		eight="null";
	         	}
	         	ninth=medi.getText().toString();
	         	tenth=timee1.getSelectedItem().toString();
	         	if(tenth.equalsIgnoreCase("Select Time")){
	         		tenth="";
	         	}
	         	eleventh=timee2.getSelectedItem().toString();
	         	if(eleventh.equalsIgnoreCase("Select Time")){
	         		eleventh="";
	         	}
	         	twelth=timee3.getSelectedItem().toString();
	         	if(twelth.equalsIgnoreCase("Select Time")){
	         		twelth="";
	         	}
	         	thirteenth=ampm1.getSelectedItem().toString();
	         	fourteenth=ampm2.getSelectedItem().toString();
	         	fifteenth=ampm3.getSelectedItem().toString();
	         	
	        String Update7 = ViewProfileActivity.participantview.get(12);
	        System.out.println("update 7 is" + Update7);
	         	prosUA = MultiSelectionSpinner1.mspin;
	       System.out.println("prosa is" + prosUA);
	            prosUB = MultiSelectionSpinner1.mspin1;
	            System.out.println("prosb is" + prosUB);
	           
	             prosUC = MultiSelectionSpinner1.mspin2;
	           System.out.println("prosc is" + prosUC);
	       
	            	  System.out.println("name value pair is BUILDING");
	            	  loginid=LoginActivity.userid;
	            	  oldmail=ViewProfileActivity.participantview.get(3); 
	            	  oldmob = ViewProfileActivity.participantview.get(2);
	            	  System.out.println("old mob is" + oldmob);
	            	  pswd=LoginActivity.password; 
	            	  
		                List<NameValuePair> paramsU = new ArrayList<NameValuePair>();
		                paramsU.add(new BasicNameValuePair("fname", first));
		                paramsU.add(new BasicNameValuePair("loginid", loginid));
		                paramsU.add(new BasicNameValuePair("oldemailid", oldmail));
		                paramsU.add(new BasicNameValuePair("oldmobilenum",oldmob));
		                paramsU.add(new BasicNameValuePair("pass", pswd));
		                paramsU.add(new BasicNameValuePair("username1", second2));
		                paramsU.add(new BasicNameValuePair("mobile_num",third));
		                paramsU.add(new BasicNameValuePair("email", fourth));
		                paramsU.add(new BasicNameValuePair("gender", fifth));
		                paramsU.add(new BasicNameValuePair("age", sixth));
		                paramsU.add(new BasicNameValuePair("city", seventh));
		                paramsU.add(new BasicNameValuePair("education", eight));
		                paramsU.add(new BasicNameValuePair("medical_details", ninth));
		                paramsU.add(new BasicNameValuePair("time1", tenth));
		                paramsU.add(new BasicNameValuePair("time1format", thirteenth));
		                paramsU.add(new BasicNameValuePair("time2", eleventh));
		                paramsU.add(new BasicNameValuePair("time2format", fourteenth));
		                paramsU.add(new BasicNameValuePair("time3",twelth ));
		                paramsU.add(new BasicNameValuePair("time3format", fifteenth));
		                paramsU.add(new BasicNameValuePair("Provider_name", Update7));
		                paramsU.add(new BasicNameValuePair("group_name", prosUA));
		                paramsU.add(new BasicNameValuePair("groupname", prosUB));
		                paramsU.add(new BasicNameValuePair("groupid", prosUC));
		  
	                Log.d("request!", "starting");
	                JSONObject json = jsonPE.makeHttpRequest(urlUP, "POST", paramsU);
	                
		              
		              Log.i("tagconvertstr", "["+json+"]");
		              
		              try
		              {
		            	  if(json!=null){
		             	 JSONObject jUpdate = json.getJSONObject(TAG_SRESU);
		             	 mss= jUpdate.getString(TAG_MESSAGEU );
		             	System.out.println("mss is" + mss);
		             	 successu = jUpdate.getString(TAG_SUCCESSU);
		             System.out.println("successis" + successu);
		         
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
			 if(JsonParser.jss.equalsIgnoreCase("empty")){
				 pDialog.dismiss();
				 final Dialog dialog = new Dialog(context);
			     
       			 dialog.setContentView(R.layout.custom_dialog);
       			 dialog.setTitle("Update Failed");
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
			 }
			 
			 else if(mss.equals("Already Exist"))
			 {
				 pDialog.dismiss();
				 final Dialog dialog = new Dialog(EditProfileActivity.this);
				 dialog.setContentView(R.layout.custom_dialog);
				 dialog.setTitle("Update Failed");
				 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
				  txt.setText("Your Email Id is Already exist!");
				  dialog.setCancelable(false);
	 			 dialog.setCanceledOnTouchOutside(false);
				  Button dialogButton = (Button) dialog.findViewById(R.id.release);
				  dialogButton.setOnClickListener(new OnClickListener() {
					  public void onClick(View vd) {
						  Intent reve=new Intent(getApplicationContext(),ViewProfileActivity.class);
				 			startActivity(reve); 
						  dialog.dismiss();
					
					}
				});
				  dialog.show();
				
				 
			 }
			 else if(mss.equals("Mobile Number Exist"))
			 {
				 pDialog.dismiss();
				 final Dialog dialog = new Dialog(EditProfileActivity.this);
				 dialog.setContentView(R.layout.custom_dialog);
				 dialog.setTitle("Update Failed");
				 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
				  txt.setText("Mobile Number Already exist!");
				  dialog.setCancelable(false);
	 			 dialog.setCanceledOnTouchOutside(false);
				  Button dialogButton = (Button) dialog.findViewById(R.id.release);
				  dialogButton.setOnClickListener(new OnClickListener() {
					  public void onClick(View vd) {
						  Intent reve=new Intent(getApplicationContext(),ViewProfileActivity.class);
				 			startActivity(reve); 
						  dialog.dismiss();
					
					}
				});
				  dialog.show();
				
				 
			 }
			 else if(successu.equalsIgnoreCase("No"))
			 {
				 pDialog.dismiss();
				 final Dialog dialog = new Dialog(EditProfileActivity.this);
				 dialog.setContentView(R.layout.custom_dialog);
				 dialog.setTitle("Update Failed");
				 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
				  txt.setText("Your profile is not changed");
				  dialog.setCancelable(false);
	 			 dialog.setCanceledOnTouchOutside(false);
				  Button dialogButton = (Button) dialog.findViewById(R.id.release);
				  dialogButton.setOnClickListener(new OnClickListener() {
					  public void onClick(View vd) {
						  Intent reve=new Intent(getApplicationContext(),ViewProfileActivity.class);
				 			startActivity(reve); 
						  dialog.dismiss();
					
					}
				});
				  dialog.show();
				
				 
			 }
			 else
			 {
				 pDialog.dismiss();
			 final Dialog dialog = new Dialog(EditProfileActivity.this);
			 dialog.setContentView(R.layout.custom_dialog);
			 dialog.setTitle("Update Success");
			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
			  txt.setText("Your Profile has been changed!");
			  dialog.setCancelable(false);
 			 dialog.setCanceledOnTouchOutside(false);
			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
			  dialogButton.setOnClickListener(new OnClickListener() {
				  public void onClick(View vd) {
					  Intent reve=new Intent(getApplicationContext(),ViewProfileActivity.class);
			 			startActivity(reve); 
					  dialog.dismiss();
				
				}
			});
			  dialog.show();
		 }
		 }
	 }
}


