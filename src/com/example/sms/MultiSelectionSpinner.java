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

public class MultiSelectionSpinner extends Spinner implements  
OnMultiChoiceClickListener {
	String[] _items = null;  
	 Context context=null;
	 boolean[] mSelection = null;  
	 public static StringBuilder ms= null;
	 public static String mspin=null;
	  
	 public static String mspin1=null;
	 public static String mspin2=null;
	 public static int dd=0;
	 public static String allr="";
	 public static String allr1="";
	 public static String allr2="";
	 ArrayAdapter<String> simple_adapter; 

	public MultiSelectionSpinner(Context context) {
		super(context);
		simple_adapter = new ArrayAdapter<String>(context,  
			    android.R.layout.simple_spinner_item);  
			  super.setAdapter(simple_adapter);  
		
	}
	 public MultiSelectionSpinner(Context context, AttributeSet attrs) {  
		  super(context, attrs);  
		  
		  simple_adapter = new ArrayAdapter<String>(context,  
		    android.R.layout.simple_spinner_item);  
		  super.setAdapter(simple_adapter);  
		 }  

	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		
		 if (mSelection != null && which < mSelection.length) {  
			   mSelection[which] = isChecked;  
			  
			   simple_adapter.clear();  
			   simple_adapter.add(buildSelectedItemString());  
			  } else {  
			   throw new IllegalArgumentException(  
			     "Argument 'which' is out of bounds.");  
			  }  
	}
	 @Override  
	 public boolean performClick() {  
	  AlertDialog.Builder builder = new AlertDialog.Builder(getContext());  
	  builder.setMultiChoiceItems(_items, mSelection, this);  
	
	  builder.show();  
	  return true;  
	 }  
	  
	 @Override  
	 public void setAdapter(SpinnerAdapter adapter) {  
	  throw new RuntimeException(  
	    "setAdapter is not supported by MultiSelectSpinner.");  
	 }  
	  
	 public void setItems(String[] items) {  
	  _items = items;  
	  mSelection = new boolean[_items.length];  
	  simple_adapter.clear();  
	  simple_adapter.add(_items[0]);  
	  Arrays.fill(mSelection, false);  
	 }  
	 public void setItemsA(List<String> items) {  
		  _items = items.toArray(new String[items.size()]);  
		  mSelection = new boolean[_items.length];  
		  simple_adapter.clear();  
		  System.out.println("Array list is setting");
		  /*mspin="null";
		  mspin1="null";
		  mspin2="null";*/
		  mspin="";
		  mspin1="";
		  mspin2="";
		  simple_adapter.add(_items[0]);  
		  Arrays.fill(mSelection, false);  
		 } 
	  
	 public void setItems(List<String> items) {  
	  _items = items.toArray(new String[items.size()]);  
	  mSelection = new boolean[_items.length];  
	  simple_adapter.clear();  
	//  mspin="";
	 
	/*  mspin=RegisterActivity.prgrouplist.get(0);
	  mspin1=RegisterActivity.prgrouplist.get(0);
	  mspin2=RegisterActivity.prgridlist.get(0);*/
	 mspin=MultiSelectionSpinner.allr;
 	 mspin1=MultiSelectionSpinner.allr1;
 	 mspin2=MultiSelectionSpinner.allr2;
	  simple_adapter.add(_items[0]); 
	  Arrays.fill(mSelection, false);  
	 }  
	  
	 public void setSelection(String[] selection) {  
	  for (String cell : selection) {  
	   for (int j = 0; j < _items.length; ++j) {  
	    if (_items[j].equals(cell)) {  
	     mSelection[j] = true;  
	    }  
	   }  
	  }  
	 }  
	  
	 public void setSelection(List<String> selection) {  
	  for (int i = 0; i < mSelection.length; i++) {  
	   mSelection[i] = false;  
	  }  
	  for (String sel : selection) {  
	   for (int j = 0; j < _items.length; ++j) {  
	    if (_items[j].equals(sel)) {  
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
	  }  
	  if (index >= 0 && index < mSelection.length) {  
	   mSelection[index] = true;  
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
		    System.out.println("Selected Strings"+ a1);
	    selection.add(_items[i]); 
	   
	   }  
	  }  
	  return selection;  
	 }  
	  
	 public List<Integer> getSelectedIndicies() {  
	  List<Integer> selection = new LinkedList<Integer>();  
	  for (int i = 0; i < _items.length; ++i) {  
	   if (mSelection[i]) {  
	    selection.add(i);  
	   }  
	  }  
	  return selection;  
	 }  

	 
	 
	 private String buildSelectedItemString() {  
		  StringBuilder sb = new StringBuilder(); 
		  StringBuilder sb1 = new StringBuilder();
		  StringBuilder sb2 = new StringBuilder();
		  
 boolean foundOne = false; 
		  
		  int r = 0;
		  int t = 0;
		 
		  for (int i = 0; i < _items.length; ++i) {  
		   if (mSelection[i]) {  
			   if(t<4)
			    {
		    if (foundOne) {  
		    	
		    	
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
		    
		    dd= t;
		    System.out.println("current seletced item is------>"+ _items[i]);
		    sb1.append(_items[i]);
		    //System.out.println("current id for selected item is"+ RegisterActivity.prgridlist.get(i));
	
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
		   sb2.append(RegisterActivity.prgridlist.get(i));
		  
		
		   
		
			  mspin= mspin+""+sb;
			  mspin1 = mspin1+""+sb1;
			  mspin2= mspin2+""+sb2;
			  
			// String mspin = "...";
			//  List<String> eList = Arrays.asList(mspin.split(","));
			  System.out.println("mspin is"+mspin);
			//  int s = eList.size();
			 // if(s>=4)
			  //{
				  
			  //}
			  ms = sb;
	
		    }
		  else
		    {
		    	sb.append("");
		   
		 
		    if(dd<5)
		    {
		    	 t++;
				    System.out.println("t is" +t);
				    dd=t;
				    System.out.println("ddissss" + dd);
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
		  
	allr = sb.toString();
	allr1=sb1.toString();
	allr2=sb2.toString();
	System.out.println("all r is" +allr);
	if(allr.equals(""))
		
	{
		//allr="Select Group";
		mspin="";
		//RegisterActivity.groupn.setVisibility(View.VISIBLE);
		RegisterActivity.groupn.setText("Select Group");
		System.out.println("empty selction" + allr);
		return allr;
	}
	else
	{
		//EditProfileActivity.pgname.setText(allr);
		System.out.println("nadakutha???");
		//RegisterActivity.groupn.setVisibility(View.VISIBLE);
		RegisterActivity.groupn.setText(allr);
		  return sb.toString();
	}
		/*  boolean foundOne = false; 
		  
		  int r = 0;
		  int t = 0;
		  for (int i = 0; i < _items.length; ++i) {  
		   if (mSelection[i]) {  
			   if(t<4)
			    {
		    if (foundOne) {  
		    	
		    	
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
		    System.out.println("current id for selected item is"+ RegisterActivity.prgridlist.get(i));
	
		   sb2.append(RegisterActivity.prgridlist.get(i));
		  
		
		   
		
			  mspin= mspin+""+sb;
			  mspin1 = mspin1+""+sb1;
			  mspin2= mspin2+""+sb2;
			  
			
			  ms = sb;
	
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
		  
	
		  return sb.toString();  */
		 }  
	}  
	 
	 
	 
	 
	 
	 
	 
	


