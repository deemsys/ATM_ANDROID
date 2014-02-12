package com.example.sms;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.deemsysinc.sms.R;


public class Questionnaire extends Activity{
	  public static ArrayList<String> questionnaire = new ArrayList<String>();
	final Context context=this;
	private RadioButton radioSexButton;
	int selectedId;
	 String selectedValue;
	  RadioGroup rg1;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.questionnaire1);
	       
	        Button send=(Button)findViewById(R.id.send);
	        TextView question=(TextView)findViewById(R.id.ques);
	        String ques=question.getText().toString();
	        questionnaire.add(0,ques);
	     
	         rg1 = (RadioGroup) findViewById(R.id.selectrad);
	        final RadioButton one= (RadioButton) findViewById(R.id.rad0);
	        final RadioButton two= (RadioButton) findViewById(R.id.rad1);
	        final RadioButton three = (RadioButton) findViewById(R.id.rad2);
	        final RadioButton four = (RadioButton) findViewById(R.id.rad3);
	        final RadioButton five = (RadioButton) findViewById(R.id.rad4);
	        final RadioButton six = (RadioButton) findViewById(R.id.rad5);
	        final RadioButton seven = (RadioButton) findViewById(R.id.rad6);
	        final RadioButton eight= (RadioButton) findViewById(R.id.rad7);
	        spinvalue();
	        send.setOnClickListener(new View.OnClickListener() {
	             
	        	
	         		public void onClick(View v) {
	             	
	         			 if(!one.isChecked()&&!two.isChecked()&&!three.isChecked()&&!four.isChecked()&&!five.isChecked()&&!six.isChecked()&&!seven.isChecked()&&!eight.isChecked()){
	         				 final Dialog dialog = new Dialog(context);
		           			 dialog.setContentView(R.layout.custom_dialog);
		           			 dialog.setTitle("Info!");
		           			 dialog.setCancelable(false);
		        			 dialog.setCanceledOnTouchOutside(false);
		           			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		           			  txt.setText("Select your option.");
		           			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
		           			  dialogButton.setOnClickListener(new OnClickListener() {
		           				  public void onClick(View vd) {
		           					   dialog.dismiss();
		        				
		        				}
		           			});
		           			  dialog.show();

	         			 }
	         			 else{
	         				int id =rg1.getCheckedRadioButtonId();
	         				
	         				 radioSexButton = (RadioButton) findViewById(id);
	        		    	selectedValue = radioSexButton.getText().toString();
	         				valid(id);
	         				}
	         			
	         			 questionnaire.add(1,selectedValue);
	         			 }
	         		
	        });
	 }
	 public void spinvalue(){
		 
	 }
	/* @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	        	 EvaluationActivity.evaluation.clear();
	            finish();
	      }
	     return true;
	 }*/
	 private void valid(int idd)
	 {
		 
		 switch(idd){
		 case R.id.rad0:
			
			
			 Intent forgettip=new Intent(Questionnaire.this,Forgetactivity.class);
			startActivity(forgettip); 
			break;
		case R.id.rad1:
			
			
			 Intent sideeff=new Intent(Questionnaire.this,SideoffActivity.class);
 			startActivity(sideeff); 
 			break;
		 case R.id.rad2:
			
			 Intent ranout=new Intent(Questionnaire.this,RanoutActivity.class);
 			startActivity(ranout); break;
		 case R.id.rad3:
		
		 Intent other=new Intent(Questionnaire.this,CostActivity.class);
			startActivity(other); 
			break;
		 case R.id.rad4:
			
		 Intent fifth=new Intent(Questionnaire.this,FamilyActivity.class);
			startActivity(fifth); 
			break;
		 case R.id.rad5:
			
		 Intent sixth2=new Intent(Questionnaire.this,TooMedActivity.class);
			startActivity(sixth2); 
			break;
		 case R.id.rad6:
			
		 Intent seventh2=new Intent(Questionnaire.this,DonlikeActivity.class);
			startActivity(seventh2); 
			break;
		 case R.id.rad7:
			
		 Intent eight2=new Intent(Questionnaire.this,OtherActivity.class);
			startActivity(eight2); 
			break;
			
		default:final Dialog dialog = new Dialog(context);
			 dialog.setContentView(R.layout.custom_dialog);
			 dialog.setTitle("Info!");
			 dialog.setCancelable(false);
			 dialog.setCanceledOnTouchOutside(false);
			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
			  txt.setText("Invalid option selection.Please select option from 1 to 4.");
			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
			  dialogButton.setOnClickListener(new OnClickListener() {
				  public void onClick(View vd) {
					   dialog.dismiss();
			
			}
			});
			  dialog.show();
			  break;
			 
		 }
		 
	 }
}
