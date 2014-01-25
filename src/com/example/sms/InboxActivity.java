package com.example.sms;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.deemsysinc.sms.R;


public class InboxActivity extends Activity{ 

	public static int smscount;

	ListView Message_listview;
	
	Message_Adapter cAdapter;
	public int smsm;
	DatabaseHandler dbHandler = new DatabaseHandler(this);
	public static List<Message> newmessage= new ArrayList<Message>();
	DatabaseHandler db;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.inbox);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	   
	        try {
	    	   Message_listview = (ListView) findViewById(R.id.listView1);
	    	   Message_listview.setItemsCanFocus(false);
	    	    

	    	    Set_Referash_Data();
	    	  
	    	    

	    	} catch (Exception e) {
	    	    
	    	    Log.e("some error", "" + e);
	    	}
	        
	
	 }
	 public void onItemClick(int mPosition)
     {
		 int msggidvalue=mPosition;
     
       
         Intent intent = new Intent(InboxActivity.this, DetailedMsg.class);
         Bundle b=new Bundle();
         b.putInt("User_ID",msggidvalue);
         b.putInt("Msg_Id",smsm);
         System.out.println("Position passed from inbox activity:::"+msggidvalue);
         intent.putExtras(b);
        
      
      startActivity(intent);
       
     }
	 public void Set_Referash_Data() {
			newmessage.clear();
			db = new DatabaseHandler(this);
			ArrayList<Message> contact_array_from_db = db.Get_Contacts();
		
		
			String useid,tonum,num,msbody, date, state;
			for (int i = 0; i < contact_array_from_db.size(); i++) {
              
			   int msgid = contact_array_from_db.get(i).getID();
			
			   useid=contact_array_from_db.get(i).getuserid();
			     num = contact_array_from_db.get(i).getmsgfrom();
			   tonum = contact_array_from_db.get(i).getto();
			     msbody = contact_array_from_db.get(i).getmsg();
			   date = contact_array_from_db.get(i).getdate();
			    state = contact_array_from_db.get(i).getstatus();
			    Message cnt = new Message(useid, msgid, num, tonum, msbody, date, state);
			    cnt.setuserid(useid);
			    cnt.setID(msgid);
			    cnt.setmsgfrom(num);
			    cnt.setto(tonum);
			    cnt.setmsg(msbody);
			    cnt.setdate(date);
			    cnt.setstatus(state);
               newmessage.add(cnt);
			}
			
			cAdapter = new Message_Adapter(InboxActivity.this, R.layout.inboxcustom,
				newmessage);
			 Message_listview.setEmptyView(findViewById(android.R.id.empty));
			Message_listview.setAdapter(cAdapter);
			Message_listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
			 

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
	        });

				
		    }

public class Message_Adapter extends ArrayAdapter<Message> implements android.widget.AdapterView.OnItemClickListener {
	final Activity context;
			int layoutResourceId;
			Message user;
			ArrayList<Message> data = new ArrayList<Message>();
             
			public Message_Adapter(Activity context, int layoutResourceId,
				List<Message> data) {
				super(context, R.layout.inboxcustom, data);
			  
			    this.layoutResourceId = layoutResourceId;
			    this.context = context;
			  
			  
			    this.data = (ArrayList<Message>) data;
			  
			    
			}

			
			 @Override
			  public int getCount() {
			    return data.size();
			  }


			  @Override
			  public Message getItem(int position) {
			    return data.get(position);
			  }

			  @Override
			  public long getItemId(int position) {
			    return position;
			  }
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
			
				
			    View row = convertView;  
			    UserHolder holder = null;
			
			    if (row== null) {
			    	
		
				LayoutInflater inflater = context.getLayoutInflater();
				
			  	row = inflater.inflate(R.layout.inboxcustom,parent, false);
			    	
				holder = new UserHolder();
				holder.fromnum = (TextView) row.findViewById(R.id.fromno);
				holder.tonum = (TextView) row.findViewById(R.id.tonum);
				holder.body= (TextView) row.findViewById(R.id.msgbody);
				holder.date = (TextView) row.findViewById(R.id.msgdate);
				holder.readunread=(ImageView) row.findViewById(R.id.imageView1);
				
				row.setTag(holder);
			    } else {
				holder = (UserHolder) row.getTag();
			    }
			    user = data.get(position);
			  
			    smsm=user.getID();
			  System.out.println("Id of the msg:::"+smsm);
			    holder.fromnum.setText(user.getmsgfrom());
			    holder.tonum.setText(user.getto());
			    holder.body.setText(user.getmsg());
			    holder.date.setText(user.getdate());
			
            
             String zero="1";
            
			    if(user.getstatus().equals(zero)){
			    	
			      
			    	
			holder.readunread.setImageResource(R.drawable.messageunread);
			    } 
			    else
			    {
			    	holder.readunread.setImageResource(R.drawable.unread_message);
			    }
			   
			  row.setOnClickListener(new OnItemClickListener(smsm));
			
			    return row; 

			}
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
		        InboxActivity sct = (InboxActivity)context;
		   	 
                sct.onItemClick(arg2);
				  user = data.get(getPosition(null));
				
			}
		
		 private class OnItemClickListener  implements OnClickListener{           
	             private int mPosition;
	              
	             OnItemClickListener(int position){
	                  mPosition = position;
	             }
	              
	             @Override
	             public void onClick(View arg0) {
	 
	        
	              InboxActivity sct = (InboxActivity)context;
	 
	                           sct.onItemClick(mPosition);
	             }    	

             }
			class UserHolder {
			    TextView fromnum;
			    TextView tonum;
			    TextView body;
			    TextView date;
			    
			    ImageView readunread;
			
			}
			
			
   
}   
	        @Override
			 public boolean onOptionsItemSelected(MenuItem item) {
			     switch (item.getItemId()) {
			         case android.R.id.home:
			            
			        	 Intent intentSignUP=new Intent(getApplicationContext(),DashBoardActivity.class);
			    			startActivity(intentSignUP);
			      }
			     return true;
			 }
	        @Override
	        public void onResume() {
	    	
	    	super.onResume();
	    	Set_Referash_Data();

	        }
			
	        }

