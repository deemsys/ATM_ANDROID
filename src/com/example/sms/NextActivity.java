package com.example.sms;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v4.widget.SimpleCursorAdapter;


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




public class NextActivity extends Activity {
	 public static final String Gen = "genKey"; 
	    public static final int Age=0; 
	    public static final String Zip = "zipKey"; 
	    public static final String MyPREFERENCES1 = "MyPrefs1" ;
	    public static final String Edu = "EduKey"; 
	    public static final String Med = "MedKey"; 
	RadioGroup rg1;
	private RadioButton radioSexButton;
	Spinner agespin;
	Spinner provispin;
	int selectedidspin,selectedidspin1;
	 String selectedValue;
	 SharedPreferences sharedpreferences1;
	Spinner grospin;
	Button next2;
	Button clear,can;
	 String cityy;
	EditText cit,medicine;
	int selectedId,id;
	Spinner spinner,spinner1;
		 SimpleCursorAdapter cursorAdapter;
		 Cursor cursor;
		   final Context context=this;
		   public static ArrayList<String> userInfo1 = new ArrayList<String>();
		    String userinfo1[];
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        ActionBar actbar=getActionBar();
		        actbar.show();
		      
		        setContentView(R.layout.register_1);
		        sharedpreferences1 = getSharedPreferences(MyPREFERENCES1, Context.MODE_PRIVATE);
id=R.id.male;
cit=(EditText)findViewById(R.id.city);
spinner = (Spinner) findViewById(R.id.selage);
 spinner1 = (Spinner) findViewById(R.id.eduspin);
/*cit.addTextChangedListener(new TextWatcher() {
    public void afterTextChanged(Editable s) {
      //  Validation.iszip(cit, true);
    }
    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
    public void onTextChanged(CharSequence s, int start, int before, int count){}
});*/
medicine=(EditText)findViewById(R.id.medicinedetails);

rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
 selectedId = rg1.getCheckedRadioButtonId();
 rg1.check(id);

 radioSexButton = (RadioButton) findViewById(selectedId);
	getActionBar().setDisplayHomeAsUpEnabled(true);
	
	
	 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this, R.array.age_array, android.R.layout.simple_spinner_item);
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  spinner.setAdapter(adapter);
  
  
   ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
         this, R.array.eduarray, android.R.layout.simple_spinner_item);
  adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinner1.setAdapter(adapter1);
  
can=(Button)findViewById(R.id.cancel);
can.setOnClickListener(new View.OnClickListener() {
     
 	public void onClick(View v) {
 		SharedPreferences settings = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
 		settings.edit().clear().commit();
 		SharedPreferences settings1 = context.getSharedPreferences("MyPrefs1", Context.MODE_PRIVATE);
 		settings1.edit().clear().commit();
 		SharedPreferences settings2 = context.getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
 		settings2.edit().clear().commit();
 		
 		startActivity(new Intent(NextActivity.this,LoginActivity.class));
}
 });
  
  Button clr=(Button)findViewById(R.id.clear);
  clr.setOnClickListener(new View.OnClickListener() {
       
   	public void onClick(View v) {
   	 spinner.setSelection(0);
   	 spinner1.setSelection(0);
   		cit.setText("");
   medicine.setText("");
 rg1.check(id);
}
   });
  
  Button next = (Button) findViewById(R.id.next2);
  next.setOnClickListener(new View.OnClickListener() {
 @Override
  public void onClick(View view) {
	 if ( checkValidation () )
         submitForm();
	 else{
		 final Dialog dialog = new Dialog(context);
		 dialog.setContentView(R.layout.custom_dialog);
		 dialog.setTitle("INFO!");
		 dialog.setCancelable(false);
		 dialog.setCanceledOnTouchOutside(false);
		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		  txt.setText("Enter valid details.");
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
		
		    private boolean checkValidation() {
		    	 boolean ret = true;
		    	 if (!Validation.iszip(cit, true)) ret = false;
		    	 return ret;
		    }
		 @Override
		    protected void onResume() {
		    	super.onResume();
		    	  System.out.println("Outsside shared pref");
		    	 spinner.setSelection(sharedpreferences1.getInt("spinnerSelection",0));
		    	 spinner1.setSelection(sharedpreferences1.getInt("spinnerSelection1",0));
		    	
			      
		    	 /*String selectedage=PreferenceManager
	                     .getDefaultSharedPreferences(context)
	                     .getString("savedValue","");
		    	 System.out.println("agespin content:"+selectedage);
		    	 for(int i=0;i<10;i++)
		    		 if(selectedage.equals(spinner.getItemAtPosition(i).toString())){
		    		      spinner.setSelection(i);
		    		      break;
		    		 }*/
		     
		         if (sharedpreferences1.contains(Zip))
		        {
		        cit.setText(sharedpreferences1.getString(Zip, "")); 

		         }
		         
		        rg1.check(sharedpreferences1.getInt("gender", 0));
		 
		        
		         if (sharedpreferences1.contains(Med))
			        {
			        medicine.setText(sharedpreferences1.getString(Med, ""));

			        }
		    }	 
		 
		 
		 
		 @Override
		    public boolean onTouchEvent(MotionEvent event) {
		        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
		                                                        INPUT_METHOD_SERVICE);
		        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		        return true;
		    }
		 @Override
		 public boolean onOptionsItemSelected(MenuItem item) {
		     switch (item.getItemId()) {
		         case android.R.id.home:
		        	 String medic=medicine.getText().toString().trim();
				    	cityy=cit.getText().toString();
			    	 spinner = (Spinner)findViewById(R.id.selage);
			    	 selectedidspin=spinner.getSelectedItemPosition();
			    	 spinner1 = (Spinner)findViewById(R.id.eduspin);
			    	 selectedidspin1=spinner1.getSelectedItemPosition();
			    	
			    //	 String education = spinner1.getSelectedItem().toString();
			    	  selectedId = rg1.getCheckedRadioButtonId();
			    	//  int checkedButton = gendertext.getCheckedRadioButtonId();
				    	// radioSexButton = (RadioButton) findViewById(selectedId);
				    	// selectedValue = radioSexButton.getText().toString();
				    	
				    						 Editor editt=sharedpreferences1.edit();
					 
					 editt.putInt("spinnerSelection", selectedidspin);
					 editt.putInt("gender",selectedId);
					 editt.putInt("spinnerSelection1", selectedidspin1);
					 
					// editt.putString("savedValue1",spinner1.getSelectedItem().toString());
					 editt.putString(Med, medic);  
					 editt.putString(Zip, cityy);
					 editt.commit();
		            startActivity(new Intent(NextActivity.this,SignupActivity.class));
		        	 
		      }
		     return true;
		 }
		

private void submitForm() {
	  final RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
      final RadioButton malee = (RadioButton) findViewById(R.id.male);
      final RadioButton femalee = (RadioButton) findViewById(R.id.female);
    
	
		  
      if(!malee.isChecked()&&!femalee.isChecked()){
 		 final Dialog dialog = new Dialog(context);
			 dialog.setContentView(R.layout.custom_dialog);
			 dialog.setTitle("INFO!");
			 dialog.setCancelable(false);
			 dialog.setCanceledOnTouchOutside(false);
			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
			  txt.setText("Select your gender.");
			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
			  dialogButton.setOnClickListener(new OnClickListener() {
				  public void onClick(View vd) {
					   dialog.dismiss();
				
				}
			});
			  dialog.show();
      }
      else{
    	          String sex;
		    	  selectedId = rg1.getCheckedRadioButtonId();
		    
		    	 radioSexButton = (RadioButton) findViewById(selectedId);
		    	 selectedValue = radioSexButton.getText().toString();
		    	
		    	 if(selectedValue.equalsIgnoreCase("Male")){
		    		 sex="0";
		    	 }
		    	 else
		    	 { sex="1";
		    	 }
		    	 
		    	
		    	
		         String medic=medicine.getText().toString().trim();
		    	
		    	 Spinner agesp = (Spinner)findViewById(R.id.selage);
		    	 String age = agesp.getSelectedItem().toString();
		    	 Spinner edu = (Spinner)findViewById(R.id.eduspin);
		    	 String education = edu.getSelectedItem().toString();
		    	 if(education.equalsIgnoreCase("Select Education")){
		    		 education="";
		    	 }
		    	
			    	cityy=cit.getText().toString();
		    	 spinner = (Spinner)findViewById(R.id.selage);
		    	 selectedidspin=spinner.getSelectedItemPosition();
		    	 spinner1 = (Spinner)findViewById(R.id.eduspin);
		    	 selectedidspin1=spinner1.getSelectedItemPosition();
		    	
		    //	 String education = spinner1.getSelectedItem().toString();
		    	  selectedId = rg1.getCheckedRadioButtonId();
		    	//  int checkedButton = gendertext.getCheckedRadioButtonId();
			    	// radioSexButton = (RadioButton) findViewById(selectedId);
			    	// selectedValue = radioSexButton.getText().toString();
			    	
			    						 Editor editt=sharedpreferences1.edit();
				 
				 editt.putInt("spinnerSelection", selectedidspin);
				 editt.putInt("gender",selectedId);
				 editt.putInt("spinnerSelection1", selectedidspin1);
				 
				// editt.putString("savedValue1",spinner1.getSelectedItem().toString());
				 editt.putString(Med, medic);  
				 editt.putString(Zip, cityy);
				 editt.commit();
		    	 userInfo1.add(sex);
		    	        userInfo1.add(age);
		                userInfo1.add(cityy); 
		                userInfo1.add(education); 
		                userInfo1.add(medic); 
		         startActivity(new Intent(NextActivity.this,RegisterActivity.class));
			    
		    }
 }
		  
}
