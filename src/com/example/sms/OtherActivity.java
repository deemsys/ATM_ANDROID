package com.example.sms;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import com.deemsysinc.sms.R;



public class OtherActivity extends Activity{
	public static String audiofilename;
	private static Button stopButton;
	private static Button playButton;
	private static Button recordButton;
	private static TextView audioopt;
	final Context context=this;
	String reasons;
	public static String audioname = "test.pcm";
	private boolean recording = false;
	public static File file = new File(Environment.getExternalStorageDirectory(), "test.pcm");
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option4);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        recordButton = (Button)findViewById(R.id.start);
        stopButton= (Button)findViewById(R.id.stop);
        audioopt=(TextView)findViewById(R.id.audiooptions);
         playButton= (Button)findViewById(R.id.play);
        final TextView textu=(TextView)findViewById(R.id.reason); 
       reasons=textu.getText().toString();
      //  Button save=(Button)findViewById(R.id.save);
        Button neext=(Button)findViewById(R.id.butnext);
        neext.setOnClickListener(new View.OnClickListener() {
	       	 
            public void onClick(View arg0) {
            	Questionnaire.questionnaire.add(1,reasons);
            	Intent sigout=new Intent(getApplicationContext(),WeeklyendActivity.class);
        		sigout.putExtra("mnt/sdcard-test.pcm",file);
    			startActivity(sigout);  
            
            }});
      
        
        if (!hasMicrophone())
		{
			stopButton.setEnabled(false);
			playButton.setEnabled(false);
			recordButton.setEnabled(false);
		} else {
			playButton.setEnabled(false);
			stopButton.setEnabled(false);
		}
	recordButton.setOnClickListener(startRecOnClickListener);
    stopButton.setOnClickListener(stopRecOnClickListener);
    playButton.setOnClickListener(playBackOnClickListener);
	}
	protected boolean hasMicrophone() {
		PackageManager pmanager = this.getPackageManager();
		return pmanager.hasSystemFeature(
               PackageManager.FEATURE_MICROPHONE);
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
	        	 Intent intentSignUP=new Intent(getApplicationContext(),Questionnaire.class);
	    			startActivity(intentSignUP);  
	            finish();
	      }
	     return true;
	 }
    OnClickListener startRecOnClickListener   = new OnClickListener()
    {
          @Override
          public void onClick(View arg0)
          {
        	  String start="Recording";
        	  audioopt.setText(start);
        	 // recordButton.
        	  playButton.setEnabled(false);
        	 	recordButton.setEnabled(false);
        	 	stopButton.setEnabled(true);
                Thread recordThread = new Thread(new Runnable()
                {
                      @Override
                      public void run()
                      {
                            recording = true;
                            startRecord();
                      }

                });

                recordThread.start();
          }};

          OnClickListener stopRecOnClickListener = new OnClickListener()
          {
                @Override
                public void onClick(View arg0)
                {String stop="";
                audioopt.setText(stop);
                	 playButton.setEnabled(true);
             	 	recordButton.setEnabled(true);
             	 	stopButton.setEnabled(false);
                	
                      recording = false;
                }};

                OnClickListener playBackOnClickListener   = new OnClickListener()
                {

                      @Override
                      public void onClick(View v)
                      {
                    	  String play="Playing";
                          audioopt.setText(play);
                    	  playButton.setEnabled(false);
                  	 	recordButton.setEnabled(false);
                  	 	stopButton.setEnabled(true);

                            playRecord();
                      }

                };

                private void startRecord()
                { 
                     

                      try
                      {
                            file.createNewFile();

                            OutputStream outputStream = new FileOutputStream(file);
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

                            int minBufferSize = AudioRecord.getMinBufferSize(8000,
                                        AudioFormat.CHANNEL_IN_MONO,
                                        AudioFormat.ENCODING_PCM_16BIT);

                            short[] audioData = new short[minBufferSize];

                            AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                                        8000,
                                        AudioFormat.CHANNEL_IN_MONO,
                                        AudioFormat.ENCODING_PCM_16BIT,
                                        minBufferSize);

                            audioRecord.startRecording();

                            while(recording)
                            {
                                  int numberOfShort = audioRecord.read(audioData, 0, minBufferSize);
                                  for(int i = 0; i < numberOfShort; i++)
                                  {
                                        dataOutputStream.writeShort(audioData[i]);
                                  }
                            }
                            audioRecord.stop();
                            audioRecord.release();
                            dataOutputStream.close();

                      }
                      catch (IOException e)
                      {
                            e.printStackTrace();
                      }
                    
                    
                      System.out.println("File Path::::"+file);
                }

                void playRecord()
                {
                      File file = new File(Environment.getExternalStorageDirectory(), "test.pcm");

                      int shortSizeInBytes = Short.SIZE/Byte.SIZE;

                      int bufferSizeInBytes = (int)(file.length()/shortSizeInBytes);
                      short[] audioData = new short[bufferSizeInBytes];

                      try {
                            InputStream inputStream = new FileInputStream(file);
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

                            int i = 0;
                            while(dataInputStream.available() > 0)
                            {
                                  audioData[i] = dataInputStream.readShort();
                                  i++;
                            }

                            dataInputStream.close();

                            @SuppressWarnings("deprecation")
							AudioTrack audioTrack = new AudioTrack(
                                        AudioManager.STREAM_MUSIC,
                                        11025,
                                        AudioFormat.CHANNEL_CONFIGURATION_MONO,
                                        AudioFormat.ENCODING_PCM_16BIT,
                                        bufferSizeInBytes,
                                        AudioTrack.MODE_STREAM);

                            audioTrack.play();
                           // audioTrack.setNotificationMarkerPosition(bufferSizeInBytes);
                            audioTrack.write(audioData, 0, bufferSizeInBytes);
                            audioTrack.stop();
                            audioTrack.release();
                            
                         /*   audioTrack.setPlaybackPositionUpdateListener(
                                    new AudioTrack.OnPlaybackPositionUpdateListener() {
               @Override
               public void onMarkerReached(AudioTrack track) {
            	   final Dialog dialog = new Dialog(context);
         			 dialog.setContentView(R.layout.custom_dialog);
         			 dialog.setTitle("Audio Playing finished");
         			//dialog.setTitleColor();
         			 TextView txt = (TextView) dialog.findViewById(R.id.errorlog);
         			  txt.setText("Audio Played!");
         			  Button dialogButton = (Button) dialog.findViewById(R.id.release);
         			  dialogButton.setOnClickListener(new OnClickListener() {
         				  public void onClick(View vd) {
         					   dialog.dismiss();
      				
      				}
         			});
         			  dialog.show();
               }

			@Override
			public void onPeriodicNotification(AudioTrack arg0) {
				// TODO Auto-generated method stub
				
			}
           });*/

                      } catch (FileNotFoundException e)
                      {
                            e.printStackTrace();
                      } catch (IOException e)
                      {
                            e.printStackTrace();
                      }
              
                
                
                } 

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	








































                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         

























}
	