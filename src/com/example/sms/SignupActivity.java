package com.example.sms;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.deemsysinc.sms.R;
public class SignupActivity extends Activity {
	private EditText username;
    private EditText email;
    private EditText mobile;
    private EditText firstname;
    public static ArrayList<String> userInfo = new ArrayList<String>();
    String userinfo[];
    private Button next1;
    final Context context=this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.register);
     ActionBar actbar=getActionBar();
     actbar.show();
      registerValid();
      getActionBar().setDisplayHomeAsUpEnabled(true);
      Button clr=(Button)findViewById(R.id.cler);
      clr.setOnClickListener(new View.OnClickListener() {
       public void onClick(View v) {
         firstname = (EditText) findViewById(R.id.firstnam);
       	 username  = (EditText) findViewById(R.id.frstname);
       	 email = (EditText) findViewById(R.id.mail);
       	 mobile = (EditText) findViewById(R.id.mobile);
        username.setText("");
       email.setText("");
       mobile.setText("");
       firstname.setText("");
       
    }
       });
    }

private void registerValid() {
        firstname = (EditText) findViewById(R.id.firstnam);
        
       firstname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(firstname);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

       username = (EditText) findViewById(R.id.frstname);
        
       username.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(username);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        
      
        
        email = (EditText) findViewById(R.id.mail);
        email.addTextChangedListener(new TextWatcher() {
         
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(email, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

       mobile = (EditText) findViewById(R.id.mobile);
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

        next1 = (Button) findViewById(R.id.next);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
                if ( checkValidation () )
                    submitForm();
                else{
                	final Dialog dialog = new Dialog(context);
      			 dialog.setContentView(R.layout.custom_dialog);
      			 dialog.setTitle("Registration Failed");
      			 dialog.setCancelable(false);
    			 dialog.setCanceledOnTouchOutside(false);
      			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
      			  txt.setText("Enter Valid Details.");
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
    
    private void submitForm() {
    

    	 String uname=username.getText().toString().trim();
    	 String fname=firstname.getText().toString().trim();
    	 String mob=mobile.getText().toString().trim();
    	 String mail=email.getText().toString().trim();
    	 System.out.println("mobile number in b4 replace submit:::"+mob);
    	 mob = mob.replaceAll("\\D+","");
        System.out.println("mobile number in submit:::"+mob);
                userInfo.add(fname); 
              
                userInfo.add(uname); 
          
                userInfo.add(mob); 
            
                userInfo.add(mail); 
             
    	
	    startActivity(new Intent(SignupActivity.this,NextActivity.class));
	    
    }

    private boolean checkValidation() {
    	
        boolean ret = true;

        if (!Validation.hasText(firstname)) ret = false;
        if (!Validation.hasText1(username)) ret = false;
       
        if (!Validation.isEmailAddress(email, true)) ret = false;
        if (!Validation.isPhoneNumber(mobile, true)) ret = false;

        return ret;
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
	            
	             Intent intent = new Intent(this, LoginActivity.class);
	             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent);
	             return true;
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	 }

   

}

