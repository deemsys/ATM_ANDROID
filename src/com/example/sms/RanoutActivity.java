package com.example.sms;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.deemsysinc.sms.R;

public class RanoutActivity extends Activity{
	  public static ArrayList<String> ranouttip = new ArrayList<String>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option3);
        TextView opt1=(TextView)findViewById(R.id.ranout);
        String tip="Please talk to your health care team about this. They may be able to help you.";
       
        opt1.setText(tip);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Button next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
	       	 
            public void onClick(View arg0) {
            	
            	Intent sigout=new Intent(getApplicationContext(),WeeklyendActivity.class);
    			startActivity(sigout);  
    			
            }
        });
}
	
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	        	// Intent intentSignUP=new Intent(getApplicationContext(),Questionnaire.class);
	    		//	startActivity(intentSignUP);  
	            finish();
	      }
	     return true;
	 }
}