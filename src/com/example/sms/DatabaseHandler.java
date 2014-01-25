package com.example.sms;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DatabaseHandler extends SQLiteOpenHelper{

	public int status=0;
	private static final int DATABASE_VERSION = 5;
	public static final String TABLE_MESSAGE = "Messages";
	public static final String KEY_USERID = "userid";
	public static final String KEY_FROM = "fromdate";
	private static final String KEY_ID = "id";
	public static final String KEY_TO = "todate";
	public static final String KEY_BODY = "bodycontent";
	public static final String KEY_DATE = "datespecification";
	public static final String KEY_STATUS = "statusy";
	public String userr;
	private static final String DATABASE_NAME = "SmsMessages";
	 private final ArrayList<Message> contact_list = new ArrayList<Message>();
	 public DatabaseHandler(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		    }

	@Override
	public void onCreate(SQLiteDatabase db) {
	
		
		String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGE + " (" + KEY_USERID + " TEXT," + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FROM + " TEXT," + KEY_TO + " TEXT,"
				+ KEY_BODY + " TEXT," + KEY_DATE + " TEXT," + KEY_STATUS + " TEXT" + ") ";
				db.execSQL(CREATE_MESSAGES_TABLE);
		
					}
	
	
	

	@Override
	
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE);

		
			onCreate(db);	
		
	}
	
    
	public void Add_Message(Message contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_USERID, contact.getuserid());
		values.put(KEY_FROM, contact.getmsgfrom()); 
		values.put(KEY_TO, contact.getto());
		values.put(KEY_BODY, contact.getmsg()); 
		values.put(KEY_DATE, contact.getdate());
		values.put(KEY_STATUS, contact.getstatus());
	
		db.insert(TABLE_MESSAGE, null, values);
		db.close();
	    }

	    Message Get_Contact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_MESSAGE, new String[] {KEY_USERID, KEY_ID,
			KEY_FROM, KEY_TO, KEY_BODY,KEY_DATE,KEY_STATUS }, KEY_ID + "=?",
			new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
		    cursor.moveToFirst();

		Message contact = new Message(cursor.getString(0),Integer.parseInt(cursor.getString(1)),
			cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
	
		cursor.close();
		db.close();

		return contact;
	    }

	 
	    public ArrayList<Message> Get_Contacts() {
	    	
		try {
		    contact_list.clear();
		    userr=LoginActivity.proInfo.get(0);
		    System.out.println("user id:::"+userr);
		   
		   
		    String selectQuery = "SELECT  * FROM " + TABLE_MESSAGE + " WHERE " + KEY_USERID + " = " + userr;
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		    System.out.println("userrrrrrrr:::::"+selectQuery);
		    
		   
		    if (cursor.moveToFirst()) {
			do {
			    Message contact = new Message(selectQuery,0, selectQuery, selectQuery, selectQuery, selectQuery, selectQuery);
			    contact.setuserid(cursor.getString(0));
			    System.out.println("cursor value 0:::"+cursor.getString(0));
			    contact.setID(Integer.parseInt(cursor.getString(1)));
			    System.out.println("cursor value 1:::"+cursor.getString(1));
			    contact.setmsgfrom(cursor.getString(2));
			    System.out.println("cursor value 2:::"+cursor.getString(2));
			    contact.setto(cursor.getString(3));
			    System.out.println("cursor value 3:::"+cursor.getString(3));
			    contact.setmsg(cursor.getString(4));
			    System.out.println("cursor value 4:::"+cursor.getString(4));
			    contact.setdate(cursor.getString(5));
			    System.out.println("cursor value 5:::"+cursor.getString(5));
			    contact.setstatus(cursor.getString(6));
			    System.out.println("cursor value 6:::"+cursor.getString(6));
			    contact_list.add(contact);
			} while (cursor.moveToNext());
		    }

		  
		    cursor.close();
		    db.close();
		    return contact_list;
		} catch (Exception e) {
		  
		    Log.e("all_contact", "" + e);
		}

		return contact_list;
	    }

	    
	    public void Update_Contact(Message contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USERID, contact.getuserid());
		values.put(KEY_FROM, contact.getmsgfrom());
		values.put(KEY_TO, contact.getto());
		values.put(KEY_BODY, contact.getmsg());
		values.put(KEY_DATE, contact.getdate());
		values.put(KEY_STATUS, contact.getstatus());
		
		
		db.update(TABLE_MESSAGE, values, KEY_ID + " = ?",
			new String[] { String.valueOf(contact.getID()) });
db.close();
	    }

	    
	    public void Delete_Contact(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MESSAGE, KEY_ID + " = ?",
			new String[] { String.valueOf(id) });
		db.close();
	    }

	    
	    public int Get_Total_Contacts() {
	    userr=LoginActivity.proInfo.get(0);
	
		  String countQuery = "SELECT  * FROM " + TABLE_MESSAGE + " WHERE " + KEY_USERID + " = " + userr;
	    	SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		
int a=cursor.getCount();
cursor.close();
db.close();
		return a;
		
	    }
public void delete_all(){
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_MESSAGE,null,null);
	db.close();
	
}

	public int calculateunread(){
			userr=LoginActivity.proInfo.get(0);
			
	  String unreadquery = "SELECT  * FROM " + TABLE_MESSAGE + " WHERE " + KEY_USERID + " = " + userr + " AND " + KEY_STATUS + " = " + '0';

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(unreadquery, null);
		
		int a=cursor.getCount();
		cursor.close();
	db.close();
		
				return a;
	}
	public void unreadchangeclr(){
		
	}

	}



