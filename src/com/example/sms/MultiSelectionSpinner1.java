package com.example.sms;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.deemsysinc.sms.R;

public class MultiSelectionSpinner1  extends Spinner implements  
OnMultiChoiceClickListener {
	
	String[] _items = null;  
	 public static boolean[] mSelection = null;  
	 public static StringBuilder ms= null;
	 public static String mspin=null;
	 public static String mspin1=null;
	 public static String mspin2=null;
	 String all;
	 public static int mm;
	 ArrayAdapter<String> simple_adapter; 
	public static boolean  foundOne ;
	 public static  String bool;
	 int select,unselect;
	public MultiSelectionSpinner1(Context context) {
		super(context);
		simple_adapter = new ArrayAdapter<String>(context,  
			    android.R.layout.simple_spinner_item);  
			  super.setAdapter(simple_adapter);  
		
	}
	 public MultiSelectionSpinner1(Context context, AttributeSet attrs) {  
		  super(context, attrs);  
		  
		  simple_adapter = new ArrayAdapter<String>(context,  
		    android.R.layout.simple_spinner_item);  
		  System.out.println("adapter settingddd");
		  super.setAdapter(simple_adapter);  
		 }  

	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		
		 if (mSelection != null && which < mSelection.length) { 
			/* ArrayList<String> mss = new  ArrayList<String>(Arrays.asList(ViewProfileActivity.participantview.get(13).split(",")));
			System.out.println("mss size is" + mss.size());
			int yy=mss.size();
			 // List<String> mss = Arrays.asList(ViewProfileActivity.participantview.get(13).split("\\s*,\\s*"));
			 for(int j=0;j<EditProfileActivity.prsize;j++)
			 {
				 for(int k=0;k<yy;k++)
				 {
			 if(EditProfileActivity.prgridlistUP.get(j).equalsIgnoreCase(mss.get(k)))
			 {
				 mSelection[j] = isChecked;  
			 }
				 }
			 }*/
			 System.out.println("1211111111");
			 System.out.println("foundone is" + foundOne);
			 System.out.println("mselction is " + mSelection);
			// bool = foundOne;
			   mSelection[which] = isChecked;  
			   if(mSelection[which] == isChecked){
				   System.out.println("888888");
			   }
			   
			   else
			   {
				   System.out.println("777777");
			   }
			   System.out.println("which is" +which);
			   System.out.println("mselecttttt" + mSelection[which]);
			   
				   

			   
			  
			  
			  System.out.println("yyyyy is");
			   simple_adapter.clear();  
			   simple_adapter.add(buildSelectedItemString());
			   if (all.equals("")){
				   
				   System.out.println("alllllllllll");
				   
			   }
			   System.out.println("zzzz is");
			   
			  } else {  
			   throw new IllegalArgumentException(  
			     "Argument 'which' is out of bounds.");  
			  }  
	}
	 @Override  
	 public boolean performClick() { 
		 System.out.println("perform click");
	  AlertDialog.Builder builder = new AlertDialog.Builder(getContext());  
	  builder.setMultiChoiceItems(_items, mSelection, this);  
	  builder.show();  
	  return true;  
	 }  
	  
	 @Override  
	 public void setAdapter(SpinnerAdapter adapter) {  
		 System.out.println("haiiiiiiii");
	  throw new RuntimeException(  
	    "setAdapter is not supported by MultiSelectSpinner.");  
	 }  
	 public void setIte(List<String> items) {  
		 _items = items.toArray(new String[items.size()]);  
		  mSelection = new boolean[_items.length];  
		  simple_adapter.clear();  
		  System.out.println("Array list is setting");
		 /* mspin="null";
		  mspin1="null";
		  mspin2="null";*/
		  mspin="";
		  mspin1="";
		  mspin2="";
		  simple_adapter.add(_items[0]);  
		  Arrays.fill(mSelection, false);  
		 } 
	  
	 public void setItems(String[] items) {  
	  _items = items;  
	  mSelection = new boolean[_items.length]; 
	  simple_adapter.clear();  
	  simple_adapter.add(_items[0]);  
	  Arrays.fill(mSelection, false);  
	 }  
	  
	 public void setItems1(List<String> items) {  
	  _items = items.toArray(new String[items.size()]);  
	  mSelection = new boolean[_items.length];  
	  simple_adapter.clear();  
	mspin=ViewProfileActivity.participantview.get(13);
	/*  mspin=EditProfileActivity.prgrouplistUP.get(0);
	  mspin1=EditProfileActivity.prgrouplistUP.get(0);
	  mspin2=EditProfileActivity.prgridlistUP.get(0);*/
	  simple_adapter.add(_items[0]);  
	  Arrays.fill(mSelection, false);  
	 }  
	  
	 public void setSelection(String[] selection) {  
	  for (String cell : selection) {  
		  System.out.println("string selection");
	   for (int j = 0; j < _items.length; ++j) {  
		   System.out.println("items checking");
	    if (_items[j].equals(cell)) {  
	    	System.out.println("set mselect as true");
	     mSelection[j] = true;  
	    }  
	   }  
	  }  
	 }  
	  
	 public void setSelection(List<String> selection) {  
	  for (int i = 0; i < mSelection.length; i++) {  
	   mSelection[i] = false;  
	   System.out.println("initially all items are not selected");
	  }  
	  for (String sel : selection) {  
	   for (int j = 0; j < _items.length; ++j) {  
	    if (_items[j].equals(sel)) {
	    	System.out.println("here only all items are selected ");
	     mSelection[j] = true;  
	    }  
	   }  
	  }  
	  simple_adapter.clear();
	
	  simple_adapter.add(buildSelectedItemString());  
	 }  
	  
	 public void setSelection(int index) {  
	  for (int i = 0; i < mSelection.length; i++) {  
	   mSelection[i] = false;  
	   System.out.println("mselection index is");
	  }  
	  if (index >= 0 && index < mSelection.length) {  
	   mSelection[index] = true;  
	   System.out.println("mselcetion index true");
	  } else {  
	   throw new IllegalArgumentException("Index " + index  
	     + " is out of bounds.");  
	  }  
	  simple_adapter.clear();  
	  simple_adapter.add(buildSelectedItemString());  
	 }  
	  
	 public void setSelection(int[] selectedIndicies) {  
	  for (int i = 0; i < mSelection.length; i++) {  
	   mSelection[i] = false;  
	  }  
	  for (int index : selectedIndicies) {  
	   if (index >= 0 && index < mSelection.length) {
		   System.out.println("how to check selected items");
	    mSelection[index] = true;  
	   } else {  
	    throw new IllegalArgumentException("Index " + index  
	      + " is out of bounds.");  
	   }     
	  }  
	  simple_adapter.clear();  
	  simple_adapter.add(buildSelectedItemString());  
	 }  
	  
	 public List<String> getSelectedStrings() {  
	  List<String> selection = new LinkedList<String>();  
	  for (int i = 0; i < _items.length; ++i) {  
	   if (mSelection[i]) {  
		   String a1 = _items[i]; 
		    System.out.println("Selected Strings a111111111111 is"+ a1);
	    selection.add(_items[i]); 
	   
	   }  
	  }  
	  return selection;  
	 }  
	  
	
	 public List<Integer> getSelectedIndicies() {  
	  List<Integer> selection = new LinkedList<Integer>();  
	  for (int i = 0; i < _items.length; ++i) {  
	   if (mSelection[i]) { 
		   System.out.println("selected indexces as integer");
	    selection.add(i);  
	   }  
	  }  
	  return selection;  
	 }  
	  

	 
	 
	 private String buildSelectedItemString() {  
		  StringBuilder sb = new StringBuilder(); 
		  StringBuilder sb1 = new StringBuilder();
		  StringBuilder sb2 = new StringBuilder();
		   foundOne = false;  
		  
			
		  int r = 0;
		  int t = 0;
		  
		  for (int i = 0; i < _items.length; ++i) {  
		   if (mSelection[i]) {  
			   if(t<4)
			    {
		    if (foundOne) {  
		    	
		    	System.out.println("items selection process");
		    	System.out.println("mSelection is-->"+ mSelection[i]);
		    	r++;
		    	System.out.println("r--->" +r);
		     sb.append(",");  
		     sb1.append("-");
		     sb2.append("-");
		    }  
		    
		    foundOne = true;  
		    System.out.println("foundOne is" + foundOne);
		    System.out.println("mSelection 1 is-->"+ mSelection[i]);
		    mspin ="";
		    mspin1 ="";
		    mspin2 = "";
		   
		    sb.append(_items[i]);
		    t++;
		    System.out.println("t -->"+ t);
		    mm=t;
		    System.out.println("mm is" + mm);
		    System.out.println("current seletced item is------>"+ _items[i]);
		    if(_items[i].equalsIgnoreCase(""))
		    {
		    	final Dialog dialog = new Dialog(getContext());
		 	     
		 		 dialog.setContentView(R.layout.custom_dialog);
		 		 dialog.setTitle("Select Valid Group");
		 	
		 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		 		  txt.setText("Empty Group!");
		 		  Button dialogButton =
		 				   (Button) dialog.findViewById(R.id.release);
		 		  dialogButton.setOnClickListener(new OnClickListener() {
		 			  public void onClick(View vd) {
		 				
		 				   dialog.dismiss();
		 		
		 		}
		 		});
		 		  dialog.show();
		 		  break;
		    }
		    sb1.append(_items[i]);
		    System.out.println("current id for selected item is"+ EditProfileActivity.prgridlistUP.get(i));
		 
		   sb2.append(EditProfileActivity.prgridlistUP.get(i));
		  
		
		   
		   
			  mspin= mspin+""+sb;
			  mspin1 = mspin1+""+sb1;
			  mspin2= mspin2+""+sb2;
			  
			  System.out.println("selected a11 out of loop ---->"+ mspin);
			  System.out.println("selected a12 out of loop----->"+ mspin1);
			  System.out.println("selected a13 out of loop ---->"+ mspin2);
			  ms = sb;
			  
			  System.out.println("MS VALUE is"+ ms);
			  
			  
			
		    } 
		  else
		    {
			  /*sb.append("");
		    
		    	
		    	final Dialog dialog = new Dialog(getContext());
		 	     
		 		 dialog.setContentView(R.layout.custom_dialog);
		 		 dialog.setTitle("Update Failed");
		 	
		 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		 		  txt.setText("More Than Four Groups are not Allowed!");
		 		  Button dialogButton =
		 				   (Button) dialog.findViewById(R.id.release);
		 		  dialogButton.setOnClickListener(new OnClickListener() {
		 			  public void onClick(View vd) {
		 				
		 				   dialog.dismiss();
		 		
		 		}
		 		});
		 		  dialog.show();  */
			  sb.append("");
				   
		 		 
				    if(mm<5)
				    {
				    	 t++;
						    System.out.println("t is" +t);
						    mm=t;
						    System.out.println("ddissss" + mm);
				    	System.out.println("dialog box will appear only dd id less than 5");
				    	final Dialog dialog = new Dialog(getContext());
				 	     
				 		 dialog.setContentView(R.layout.custom_dialog);
				 		 dialog.setTitle("Update Failed");
				 	
				 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
				 		  txt.setText("More Than Four Groups are not Allowed!");
				 		  Button dialogButton =
				 				   (Button) dialog.findViewById(R.id.release);
				 		  dialogButton.setOnClickListener(new OnClickListener() {
				 			  public void onClick(View vd) {
				 				
				 				   dialog.dismiss();
				 		
				 		}
				 		});
				 		  dialog.show();
				    }
		    }
		   }  
		 
		  }
	 String kk= ViewProfileActivity.participantview.get(13);
		   all = sb.toString();
		  System.out.println("all is " +all);
		  if (all.equals(""))
		  {
			  mspin=kk;
			  return kk;
			  
		  }
		  else
		  {
			  EditProfileActivity.pgname.setText(all);
		  return sb.toString();  
		  }
		  //String a11 = null;
		/*  boolean foundOne = false;  
		  
	
		  int r = 0;
		  int t = 0;
		  
		  for (int i = 0; i < _items.length; ++i) {  
		   if (mSelection[i]) {  
			   if(t<4)
			    {
		    if (foundOne) {  
		    	
		    	System.out.println("items selection process");
		    	System.out.println("selected current item is-->"+ mSelection[i]);
		    	r++;
		    	System.out.println("r--->" +r);
		     sb.append(",");  
		     sb1.append("-");
		     sb2.append("-");
		    }  
		    
		    foundOne = true;  
		    mspin ="";
		    mspin1 ="";
		    mspin2 = "";
		   
		    sb.append(_items[i]);
		    t++;
		    System.out.println("t -->"+ t);
		    
		    System.out.println("current seletced item is------>"+ _items[i]);
		    sb1.append(_items[i]);
		    System.out.println("current id for selected item is"+ EditProfileActivity.prgridlistUP.get(i));
		 
		   sb2.append(EditProfileActivity.prgridlistUP.get(i));
		  
		
		   
		   
			  mspin= mspin+""+sb;
			  mspin1 = mspin1+""+sb1;
			  mspin2= mspin2+""+sb2;
			  
			  System.out.println("selected a11 out of loop ---->"+ mspin);
			  System.out.println("selected a12 out of loop----->"+ mspin1);
			  System.out.println("selected a13 out of loop ---->"+ mspin2);
			  ms = sb;
			  
			  System.out.println("MS VALUE is"+ ms);
			  
			  
			
		    } 
		  else
		    {
			  
			  
			  
		    	sb.append("");
		    
		    	
		    	final Dialog dialog = new Dialog(getContext());
		 	     
		 		 dialog.setContentView(R.layout.custom_dialog);
		 		 dialog.setTitle("Update Failed");
		 	
		 		 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
		 		  txt.setText("More Than Four Groups are not Allowed!");
		 		  Button dialogButton =
		 				   (Button) dialog.findViewById(R.id.release);
		 		  dialogButton.setOnClickListener(new OnClickListener() {
		 			  public void onClick(View vd) {
		 				
		 				   dialog.dismiss();
		 		
		 		}
		 		});
		 		  dialog.show();  
		    }
		   }  
		 
		  }
	 
		  return sb.toString(); */ 
		 }  

}
