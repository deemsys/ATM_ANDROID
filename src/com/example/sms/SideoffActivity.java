package com.example.sms;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.deemsysinc.sms.R;

public class SideoffActivity extends Activity{
	  RadioGroup rg1;
	  RadioButton yes1;
	  
	final Context context=this;
	  public static ArrayList<String> sideefftip = new ArrayList<String>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option2);
        TextView opt1=(TextView)findViewById(R.id.sideeff);
        String tip="Have you talked to your doctor about this";
        opt1.setText(tip);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Button next=(Button)findViewById(R.id.next);
       rg1 = (RadioGroup) findViewById(R.id.selecty);
        final RadioButton yes1 = (RadioButton) findViewById(R.id.yesans);
        final RadioButton no1 = (RadioButton) findViewById(R.id.noans);
        yes1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 final Dialog dialog = new Dialog(context);
       			 dialog.setContentView(R.layout.custom_dialog);
       			 dialog.setTitle("Weekly Survey");
       			 dialog.setCancelable(false);
    			 dialog.setCanceledOnTouchOutside(false);
       			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
       			  txt.setText("Great job!");
       			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
       			  dialogButton.setOnClickListener(new OnClickListener() {
       				  public void onClick(View vd) {
       					   dialog.dismiss();
    				
    				}
       			});
       			  dialog.show();
				
			}
		});
      
      
        
     
        
        next.setOnClickListener(new View.OnClickListener() {
	       	 
            public void onClick(View arg0) {
            	 if(!yes1.isChecked()&&!no1.isChecked()){
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
	            if(selectedValue.equals("YES")){
	                 		Intent sigout=new Intent(getApplicationContext(),WeeklyendActivity.class);
	            			startActivity(sigout);  
	                 		}
	                 	else{
	                 		 final Dialog dialog = new Dialog(context);
	               			 dialog.setContentView(R.layout.custom_dialog);
	               			 dialog.setTitle("Weekly Survey");
	               			 dialog.setCancelable(false);
	            			 dialog.setCanceledOnTouchOutside(false);
	               			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
	               			  txt.setText("Please press ‘Record’ and tell us about your symptoms");
	               			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
	               			  dialogButton.setOnClickListener(new OnClickListener() {
	               				  public void onClick(View vd) {
	               					Intent sigout1=new Intent(getApplicationContext(),OtherActivity.class);
	               	    			startActivity(sigout1);  
	               					   dialog.dismiss();
	            				
	            				}
	               			});
	               			  dialog.show();
	                 	}
	                 	}
            	
    			
            }
        });
}
	/*private String gettip(){
		
		String tip1="SideEff Tip1";
		String tip2="SideEff Tip2";
		String tip3="SideEff Tip3";
		String tip4="SideEff Tip4";
		sideefftip.add(tip1);
		sideefftip.add(tip2);
		sideefftip.add(tip3);
		sideefftip.add(tip4);
		
		Random r = new Random();
		String tp=(sideefftip.get(r.nextInt(sideefftip.size())));
		
		
		return tp;
	}*/
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	        	 Intent intentSignUP=new Intent(getApplicationContext(),Questionnaire.class);
	    			startActivity(intentSignUP);  
	            finish();
	      }
	     return true;
	 }
}