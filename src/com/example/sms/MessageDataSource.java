package com.example.sms;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MessageDataSource {

 
  private SQLiteDatabase database;
  private DatabaseHandler dbHelper;
  private String[] allColumns = { DatabaseHandler.KEY_USERID,DatabaseHandler.KEY_FROM,
      DatabaseHandler.KEY_TO,DatabaseHandler.KEY_BODY,DatabaseHandler.KEY_DATE,DatabaseHandler.KEY_STATUS };

  public MessageDataSource(Context context) {
    dbHelper = new DatabaseHandler(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Message createMessage(String userid,String from,String to,String body,String date,String status) {
    ContentValues values = new ContentValues();
    
    values.put(DatabaseHandler.KEY_USERID, userid);
   
    values.put(DatabaseHandler.KEY_FROM,from);
    values.put(DatabaseHandler.KEY_TO, to);
    values.put(DatabaseHandler.KEY_BODY,body);
    values.put(DatabaseHandler.KEY_DATE,date);
    values.put(DatabaseHandler.KEY_STATUS,status);
    long insertId = database.insert(DatabaseHandler.TABLE_MESSAGE, null,
        values);
    Cursor cursor = database.query(DatabaseHandler.TABLE_MESSAGE,
        allColumns, DatabaseHandler.KEY_FROM + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
  Message newComment = cursorToComment(cursor);
    cursor.close();
    return newComment;
  }
  
  public void deleteComment(Message comment) {
	String userid = comment.getuserid();  
    String from = comment.getmsgfrom();
  
    System.out.println("Comment deleted with id: " + userid);
   
    
    
    database.delete(DatabaseHandler.TABLE_MESSAGE, DatabaseHandler.KEY_FROM
        + " = " + from, null);
  }

  public List<Message> getAllComments() {
    List<Message> comments = new ArrayList<Message>();

    Cursor cursor = database.query(DatabaseHandler.TABLE_MESSAGE,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
    	Message from = cursorToComment(cursor);
    	Message to = cursorToComment(cursor);
    	Message msg = cursorToComment(cursor);
    	Message date = cursorToComment(cursor);
      Message status = cursorToComment(cursor);
      comments.add(from);
      comments.add(to);
      comments.add(msg);
      comments.add(date);
      comments.add(status);
       cursor.moveToNext();
    }
    
    cursor.close();
    return comments;
  }

  private Message cursorToComment(Cursor cursor) {
   Message comment = new Message(null,0, null, null, null, null, null);
    comment.setmsgfrom(cursor.getString(0));
    comment.setto(cursor.getString(1));
    comment.setmsg(cursor.getString(1));
    comment.setdate(cursor.getString(1));
    comment.setstatus(cursor.getString(1));
    return comment;
  }
}