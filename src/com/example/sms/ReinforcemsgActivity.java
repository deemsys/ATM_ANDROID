package com.example.sms;

import java.util.ArrayList;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.deemsysinc.sms.R;

public class ReinforcemsgActivity extends Activity{
	  public static ArrayList<String> reinf = new ArrayList<String>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reinforcementmsg);
        TextView opt1=(TextView)findViewById(R.id.reinforce);
        String tip=gettip();
       
        opt1.setText(tip);
       Questionnaire.questionnaire.add(0,"");
       Questionnaire.questionnaire.add(1,"");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Button next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
 	       	 
            public void onClick(View arg0) {
            	
            	Intent sigout=new Intent(getApplicationContext(),WeeklyendActivity.class);
    			startActivity(sigout);  
    			
            }
        });
}
	private String gettip(){
		
		String tip1="Well done! ";
		String tip2="Keep up the good work!";
		String tip3="Way to go! ";
		String tip4="Nice job!";
		String tip5="Keep it up! ";
		String tip6="Fantastic!";
		String tip7="Great job staying on track!";
		String tip8="Wonderful!";
		String tip9="Sweet!";
		String tip10="You're taking care of yourself!";
		String tip11="Awesome!";
		String tip12="Keep the streak alive!";
		
		reinf.add(tip1);
		reinf.add(tip2);
		reinf.add(tip3);
		reinf.add(tip4);
		reinf.add(tip5);
		reinf.add(tip6);
		reinf.add(tip7);
		reinf.add(tip8);
		reinf.add(tip9);
		reinf.add(tip10);
		reinf.add(tip11);
		reinf.add(tip12);
	
		
		Random r = new Random();
		String tp=(reinf.get(r.nextInt(reinf.size())));
		
		return tp;
	}
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	        	 
	            finish();
	      }
	     return true;
	 }
}