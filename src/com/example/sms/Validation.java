package com.example.sms;

import android.widget.EditText;
import java.util.regex.Pattern;

public class Validation {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX ="\\([1-9]{1}[0-9]{2}\\) [0-9]{3}\\-[0-9]{4}$";
    //^(\([0-9]{3}\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}
    private static final String CODE_REGEX ="[0-9]{6}";
    private static final String ZIP_REGEX ="[0-9]{5}";

    
    private static final String ERR_MSGOLD = "Invalid Password";
  
    
    
    private static final String ERR_MSG = "Invalid Password";
    private static final String REQUIRED_MSG = "Invalid Name";
    private static final String req_MSG = "Invalid User Name";
    private static final String req_code = "Invalid Code";
    private static final String EMAIL_MSG = "Invalid email";
    private static final String PHONE_MSG = "Invalid Mobile Number";
    private static final String PASS_MSG="Password Does Not Match";
    private static final String emt="Field Cant be Empty";
    private static final String zipval="Invalid Zipcode.";
 
    
    
    public static boolean isconAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX,EMAIL_MSG, required);
    }
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX,EMAIL_MSG, required);
    }
    public static boolean iszip(EditText editText, boolean required) {
        return isValidzip(editText,ZIP_REGEX,zipval, required);
    }
    
   
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX ,PHONE_MSG ,required);
    }
    public static boolean codever(EditText editText, boolean required) {
        return isValid(editText, CODE_REGEX, req_code, required);
    }
    
    public static boolean isEqualpass(EditText editText,EditText editText1,boolean required){
    	
    	return isequal(editText,editText1,required);
    }
    
    
    
    public static boolean isValidzip(EditText editText, String regex, String errMsg, boolean required) {
        String text = editText.getText().toString().trim();
        
        editText.setError(null);

    

        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

  
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
        String text = editText.getText().toString().trim();
     
        editText.setError(null);

      if ( required && !hasTextemt(editText) ) return false;

        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

   
    public static boolean hasText1(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

       
        if (text.length() == 0) {
            editText.setError(req_MSG);
            return false;
        }

        return true;
    }
    public static boolean hasTextemt(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

    
        if (text.length() == 0) {
            editText.setError(emt);
            return false;
        }

        return true;
    }
    
    
    public static boolean hasTextConfirm(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

       
        if (text.length() == 0) {
            editText.setError(ERR_MSG);
            return false;
        }

        return true;
    }
    public static boolean hasTextNew(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

       
        if (text.length() == 0) {
            editText.setError(ERR_MSG);
            return false;
        }

        return true;
    }
    public static boolean hasTextOld(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        String olld=ChangePassActivity.olld;
     
    
       
      
        if (text.length() == 0||!olld.equals(text)) {
            editText.setError(ERR_MSGOLD);
            return false;
        }

        return true;
    }
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

       
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
    public static boolean hasText2(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

     
        if (text.length() == 0) {
            editText.setError(ERR_MSG);
            return false;
        }

        return true;
    }
    public static boolean isequal(EditText pass, EditText confirmpass,boolean required ) {
        String text = pass.getText().toString().trim();
        String text1 = confirmpass.getText().toString().trim();
        pass.setError(null);
       confirmpass.setError(null);
       if ( required && !hasText2(pass)&& !hasText2(confirmpass) ) return false;
       
     if(!text.equals(text1)){
    	 pass.setError(PASS_MSG);
        	confirmpass.setError(PASS_MSG);
        	return false;
        	}
        return true;
       
    }
    
    
}

