package com.example.sms;

public class Message {
	 public int _id;
	 public int msgid;
	 String userid;
	String msgfrom;
	String to;
	 String msg;
	 String datey;
	 String status;
	 
	 
	
	 public int getID() {
			return this._id;
		    }

		 
		    public void setID(int id) {
			this._id = id;
		    }
		    
		    public String getuserid() {
		  	  return userid;
		  	 }

		  	 public void setuserid(String userid) {
		  	  this.userid = userid;
		  	 }		    
	 public String getmsgfrom() {
	  return msgfrom;
	 }

	 public void setmsgfrom(String msgfrom) {
	  this.msgfrom = msgfrom;
	 }

	 public String getmsg() {
	  return msg;
	 }

	 public void setmsg(String msg) {
	  this.msg = msg;
	 }

	 public String getdate() {
	  return datey;
	 }
	 public void setdate(String date) {
		  this.datey = date;
		 }
	 public void setstatus(String status) {
	  this.status = status;
	 }

	 public String getstatus() {
		  return status;
		 }
	 public void setto(String to) {
		  this.to = to;
		 }

		 public String getto() {
			  return to;
			 }
	
		
		 public Message(int id,String name, String tonum, String body, String date,
					String status) {
				
				
				  this.msgfrom = name;
				  this.to=tonum;
				  this.msg = body;
				  this.datey = date;
				  this.status=status;
				
				
			}

		 
		 
		 public Message(String userid, String name, String tonum, String body, String date,
					String status) {
				
				  this.userid = userid;
				  this.msgfrom = name;
				  this.to=tonum;
				  this.msg = body;
				  this.datey = date;
				  this.status=status;
				
			
			}

	public Message(String userid,int id, String name, String tonum, String body, String date,
			String status) {
	
		 this._id=id;
		 this.userid = userid;
		  this.msgfrom = name;
		  this.to=tonum;
		  this.msg = body;
		  this.datey = date;
		  this.status=status;
		
	}

	}
