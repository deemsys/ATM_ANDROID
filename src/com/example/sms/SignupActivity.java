package com.example.sms;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;

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
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey"; 
    public static final String Phone = "phoneKey"; 
    public static final String Email = "emailKey"; 
    public static final String user = "userKey"; 
    SharedPreferences sharedpreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.register);
     ActionBar actbar=getActionBar();
     actbar.show();
     getActionBar().setDisplayHomeAsUpEnabled(true);
     firstname = (EditText) findViewById(R.id.firstnam);
     username = (EditText) findViewById(R.id.frstname);
     email = (EditText) findViewById(R.id.mail);
     mobile = (EditText) findViewById(R.id.mobile);
     
      Button clr=(Button)findViewById(R.id.canny);
      Button cann=(Button)findViewById(R.id.cler);
      sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

   
      cann.setOnClickListener(new View.OnClickListener() {
       public void onClick(View v) {
     
        username.setText("");
       email.setText("");
       mobile.setText("");
       firstname.setText("");
       
    }
       
       });
      clr.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
        
        	  SharedPreferences settings = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
       		settings.edit().clear().commit();
       	RegisterActivity.prname123="null";
     		SharedPreferences settings1 = context.getSharedPreferences("MyPrefs1", Context.MODE_PRIVATE);
     		settings1.edit().clear().commit();
     		SharedPreferences settings2 = context.getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
     		settings2.edit().clear().commit();
       	 startActivity(new Intent(SignupActivity.this,LoginActivity.class));
       }
          
          });
      registerValid();
      next1 = (Button) findViewById(R.id.next);
      next1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
        	  
        	//  startActivity(new Intent(SignupActivity.this,NextActivity.class));
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
    @Override
    protected void onResume() {
    	super.onResume();
    	  System.out.println("Outsside shared pref");
        if (sharedpreferences.contains(Name))
        {
        	  System.out.println("Inside shared pref");
        firstname.setText(sharedpreferences.getString(Name, ""));

        }
        if (sharedpreferences.contains(Phone))
        {
        mobile.setText(sharedpreferences.getString(Phone, ""));

        }
         if (sharedpreferences.contains(Email))
        {
        email.setText(sharedpreferences.getString(Email, "")); 

         }
         if (sharedpreferences.contains(user))
        {
        username.setText(sharedpreferences.getString(user, ""));

        }
    }
    public void run(View view){
        String n  = firstname.getText().toString();
        System.out.println("In run method::"+n);
        String ph  = mobile.getText().toString();
        System.out.println("In run method::"+ph);
        String e  = email.getText().toString();
        System.out.println("In run method::"+e);
        String u  = username.getText().toString();
        System.out.println("In run method::"+u);
       
        Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Phone, ph);
        editor.putString(Email, e);
        editor.putString(user, u);
       

        editor.commit(); 

     }
private void registerValid() {
       
        
       firstname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
               // Validation.isFirstname(firstname, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

       
        
       username.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
              //  Validation.isUsername(username, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        
      
        
       
        email.addTextChangedListener(new TextWatcher() {
         
            public void afterTextChanged(Editable s) {
               // Validation.isEmailAddress(email, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

     
      /* InputFilter filter = new InputFilter() {

        
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
        };*/

       // mobile.setFilters(new InputFilter[] { filter });
      mobile.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
           // 	System.out.println("mobile number pressed::"+mobile.getText().toString());
              //  Validation.isPhoneNumber(mobile, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

       /* next1 = (Button) findViewById(R.id.next);
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
        });*/
    }
    
    private void submitForm() {
    	 SignupActivity.userInfo.clear();
    	 String n  = firstname.getText().toString();
         System.out.println("In run method::"+n);
         String p = mobile.getText().toString();
         System.out.println("In run method::"+p);
         String e  = email.getText().toString();
         System.out.println("In run method::"+e);
         String u  = username.getText().toString();
         System.out.println("In run method::"+u);
        
         Editor editor = sharedpreferences.edit();
         editor.putString(Name, n);
         editor.putString(user, u);
         editor.putString(Phone, p);
         editor.putString(Email, e);
         
        

         editor.commit();
    	//editor.clear();
    	 String uname=username.getText().toString().trim();
    	 String fname=firstname.getText().toString().trim();
    	 String mob=mobile.getText().toString().trim();
    	 String mail=email.getText().toString().trim();
    	// System.out.println("mobile number in b4 replace submit:::"+mob);
    	 mob = mob.replaceAll("\\D+","");
     //   System.out.println("mobile number in submit:::"+mob);
                userInfo.add(fname); 
              
                userInfo.add(uname); 
          
                userInfo.add(mob); 
            
                userInfo.add(mail); 
             
    	
	    startActivity(new Intent(SignupActivity.this,NextActivity.class));
	    
    }

    private boolean checkValidation() {
    	
        boolean ret = true;

        if (!Validation.isFirstname(firstname, true)) ret = false;
        if (!Validation.isUsername(username, true)) ret = false;
       
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
	        	  SharedPreferences settings = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
	         		settings.edit().clear().commit();
	         	
	       		SharedPreferences settings1 = context.getSharedPreferences("MyPrefs1", Context.MODE_PRIVATE);
	       		settings1.edit().clear().commit();
	       		SharedPreferences settings2 = context.getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
	       		settings2.edit().clear().commit();
	            
	             Intent intent = new Intent(this, LoginActivity.class);
	             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent);
	             return true;
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	 }

   

}

