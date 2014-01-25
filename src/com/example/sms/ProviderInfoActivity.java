package com.example.sms;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.deemsysinc.sms.R;


public class ProviderInfoActivity extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.providerinfo);
        ActionBar actbar=getActionBar();
        actbar.show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
       
        showProInfo();
        TextView call=(TextView)findViewById(R.id.textView4);
        PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
			.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
        call.setOnClickListener(new View.OnClickListener() {
        	 
             public void onClick(View arg0) {
             	String number=LoginActivity.provider.get(2);
            	 Intent callIntent = new Intent(Intent.ACTION_DIAL); 
            	
            	 callIntent.setData(Uri.parse("tel:"+number));
            	 startActivity(callIntent);
             }
         });
        
        TextView mail1=(TextView)findViewById(R.id.textView1);
       mail1.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View arg0) {
            	
            	 Intent email = new Intent(Intent.ACTION_SEND);
            	    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"youremail@yahoo.com"});          
            	    email.putExtra(Intent.EXTRA_SUBJECT, "About Medicines");
            	    email.putExtra(Intent.EXTRA_TEXT, "");
            	    email.putExtra(Intent.EXTRA_TEXT, "Thanks,");
            	    email.setType("message/rfc822");
            	    startActivity(Intent.createChooser(email, "Choose an Email client :"));
            	
            }
        });
}
	@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	            finish();
	      }
	     return true;
	 }
	private void showProInfo()
	{ 
		
		
		TextView un=(TextView)findViewById(R.id.usernamepro);
		 String fn1=LoginActivity.provider.get(0);
		un.setText(fn1);
    TextView ph=(TextView)findViewById(R.id.mobppro);
    String mobi=LoginActivity.provider.get(2);
    mobi = "(" + mobi.substring( 0,3 ) + ") " + mobi.substring( 3,6 ) + "-" + mobi.substring( 6,10 );
    ph.setText(mobi);
    
    
    TextView mail=(TextView)findViewById(R.id.maiilpro);
    String mil=LoginActivity.provider.get(3);
    mail.setText(mil);
    
    
    TextView firstname=(TextView)findViewById(R.id.textView2pro);
	String frs=LoginActivity.provider.get(1);
	 firstname.setText(frs);
	       }
	private class PhoneCallListener extends PhoneStateListener {
		 
		private boolean isPhoneCalling = false;
 
		String LOG_TAG = "LOGGING 123";
 
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
 
			if (TelephonyManager.CALL_STATE_RINGING == state) {
				
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}
 
			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(LOG_TAG, "OFFHOOK");
 
				isPhoneCalling = true;
			}
 
			if (TelephonyManager.CALL_STATE_IDLE == state) {
				
				Log.i(LOG_TAG, "IDLE");
 
				if (isPhoneCalling) {
 
					Log.i(LOG_TAG, "restart app");
 
					
					Intent i = getBaseContext().getPackageManager()
						.getLaunchIntentForPackage(
							getBaseContext().getPackageName());
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
 
					isPhoneCalling = false;
				}
 
			}
		}
	}
}