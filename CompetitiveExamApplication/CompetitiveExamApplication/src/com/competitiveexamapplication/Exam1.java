package com.competitiveexamapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Exam1 extends Activity implements OnClickListener{

	Button btn, btn2;
	static int a=0,b=1;
	int id;
	int random;
	int time , min, sec;  
	   Timer t;  
	   TimerTask task;
	AlertDialog.Builder alertDialogBuilder ;
	AlertDialog alertDialog ;
	TextView tv1, tv2;
	RadioButton rb1, rb2, rb3, rb4;
	ArrayList nameValuePairs;
	StringBuilder sb;
	String result1,result, ans, username, result2, Uname,ename,catname;
	InputStream is, as;
	HttpPost httppost;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mock_exam);
	         result = null;
	         is = null;
	         as=null;
	          sb=null;
	          result1=null;
	          nameValuePairs = new ArrayList();
	          tv1 = (TextView) findViewById(R.id.tvmock1);
	          tv2 = (TextView) findViewById(R.id.tvmock2);
	          rb1 = (RadioButton) findViewById(R.id.rb_mock1);
	          rb2 = (RadioButton) findViewById(R.id.rb_mock2);
	          rb3 = (RadioButton) findViewById(R.id.rb_mock3);
	          rb4 = (RadioButton) findViewById(R.id.rb_mock4);
	          btn = (Button) findViewById(R.id.btn_moxk_exam1);
	          btn2 = (Button) findViewById(R.id.btn_moxk_exam2);
	          
	          btn2.setOnClickListener(this);
	 
	        btn.setOnClickListener(new OnClickListener()
		    {
		    	public void onClick(View v) 
		    	{		    
		    		if (btn.getText().toString().equals("START")) {  
		//   	         start.setText("STOP");  
		   	         time = 60;  
		   	         min = 5;
		   	         startTimer();       
		   	           
		   	        }else{  
//		   	         t.cancel();  
	//	   	         t.purge();  
//		   	         TextView tv1 = (TextView) findViewById(R.id.timer_txt);  
//		   	         tv1.setText("20");   
	//	   	         start.setText("START");
		   	        }
		    		
		    		if(a<=0){
		    			try{
		    				SharedPreferences userDetails = getBaseContext().getSharedPreferences("userdetails", MODE_PRIVATE);
		    				Uname = userDetails.getString("username", "");
		 //   				String pass = userDetails.getString("password", "");
		    				SharedPreferences exams = getBaseContext().getSharedPreferences("exam", MODE_PRIVATE);
		    				ename = exams.getString("ename","");
		    				catname = exams.getString("cat","");
		    				JSONObject json2 = new JSONObject();
		    				json2.put("username", Uname);
		    				json2.put("ename", ename);
		    				json2.put("subid", "NA");
		    				json2.put("catid", catname);
		    				json2.put("mockid", "m1");
		    				HttpClient httpclient2 = new DefaultHttpClient();
				            HttpPost httppost2 = new HttpPost("http://192.168.1.11/exam.php");
				            List<NameValuePair> nvp2 = new ArrayList<NameValuePair>(5);
			    		  	nvp2.add(new BasicNameValuePair("json",json2.toString()));
			    		  	httppost2.setEntity(new UrlEncodedFormEntity(nvp2));
			    		  	HttpResponse response2 = httpclient2.execute(httppost2);
			    		  	HttpEntity entity2 = response2.getEntity();
				            as = entity2.getContent();
			    		  	
			    		  	 BufferedReader reader = new BufferedReader(new InputStreamReader(as,"iso-8859-1"),8);
			 	            sb = new StringBuilder();
			 	            sb.append(reader.readLine() + "\n");
			 	            String line="0";		 	      
			 	          
			 	            while ((line = reader.readLine()) != null) {
			 	                sb.append(line + "\n");
			 	            }
			 	             
			 	            as.close();
			 	            result2=sb.toString();
			 	           Log.e("log_tag", "Fetching data from exam.php");
			 	           JSONArray jArray1 = new JSONArray(result2);
		    		       JSONObject json2_data=null;
			 	           for(int i=0;i<jArray1.length();i++){
			 	           
		    		       json2_data = jArray1.getJSONObject(i);
		    		       random = json2_data.getInt("noofque");
		    		       Log.e("log_tag", "Success in Fetched");
			 	           }
		    			}catch(Exception e){
		    				 Log.e("log_tag", "Error converting result "+e.toString());
		    			}
		    			 
		    		}
		    		if(a >= (random+1))
			    	{
			    		a=0;
			    		Toast.makeText(getBaseContext(), "The Exam has Ended Successfully", Toast.LENGTH_LONG).show();
			    		Intent openSecondpage= new Intent("com.competitiveexamapplication.RESULT");
						startActivity(openSecondpage);
			    	}
			    	else
			    	{
			    		if(a >= random)
			    		{
			    			Toast.makeText(getBaseContext(), "" +
				    				"Click Once Again to End Exam.... ):", Toast.LENGTH_LONG).show();
			    		}
			    		if(a<=random)
			    		{

	        //http post
	        try{

	        	if((btn.getText().equals("Next")) || (btn.getText().equals("End")))
	        	{
	        		try{
	        			if(rb1.isChecked())
				    	{
				    		ans = (String) rb1.getText();
				    	}
				    	else if(rb2.isChecked())
				    	{
				    		ans = (String) rb2.getText();
				    	}
				    	else if(rb3.isChecked())
				    	{
				    		ans = (String) rb3.getText();
				    	}else if(rb4.isChecked())
				    	{
				    		ans = (String) rb4.getText();
				    	}
				    	else
				    	{
				    		ans = (String) "*";
				    	}
	        			String exans = ans;
	        			rb1.setChecked(false);
			    		rb2.setChecked(false);
			    		rb3.setChecked(false);
			    		rb4.setChecked(false);
	        		JSONObject json1 = new JSONObject();
	        		json1.put("id", id);
	        		json1.put("exans", exans);
	        		json1.put("username", Uname);
	        		System.out.println("Sending "+ id + ans);
	        		HttpClient httpclient2 = new DefaultHttpClient();
		            HttpPost httppost1 = new HttpPost("http://192.168.1.11/result.php");
		            List<NameValuePair> nvp1 = new ArrayList<NameValuePair>(2);
	    		  	nvp1.add(new BasicNameValuePair("json",json1.toString()));
	    		  	httppost1.setEntity(new UrlEncodedFormEntity(nvp1));
	    		  	HttpResponse response2 = httpclient2.execute(httppost1);
	        		}catch(Exception e){
	        			Log.e("log_tag", "Error in sending Result "+e.toString());
	        		}
	        	}
	        	if(a<=(random-1))
	        	{
	        	Random r = new Random();
	        	
	        	id = r.nextInt(4 - 0) + 1;
	        
	        	JSONObject json = new JSONObject();
	        	System.out.println(""+ id);
    		  	json.put("id",id);
    	//	  	HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost("http://192.168.1.11/question.php");
	            List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
    		  	nvp.add(new BasicNameValuePair("json",json.toString()));
    		  	httppost.setEntity(new UrlEncodedFormEntity(nvp));

	            HttpClient httpclient1 = new DefaultHttpClient();
	 
	            HttpResponse response1 = httpclient1.execute(httppost);
	            HttpEntity entity = response1.getEntity();
	            is = entity.getContent();
	        	}
	        }catch(Exception e){
	            Log.e("log_tag", "Error in http connection"+e.toString());
	        }
	        if(a<=(random-1))
        	{
	        //convert response to string
	        try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            sb = new StringBuilder();
	            sb.append(reader.readLine() + "\n");
	            String line="0";
	          
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	             
	            is.close();
	            result1=sb.toString();
	             
	        }catch(Exception e){
	            Log.e("log_tag", "Error converting result "+e.toString());
	        }
	 
	        //paring data
	        String fd_id;
	        String fd_name, op1, op2, op3, op4;
	        try{
	        JSONArray jArray = new JSONArray(result1);
	        JSONObject json_data=null;
	        System.out.println("Success till here");
	        for(int i=0;i<jArray.length();i++){
	                json_data = jArray.getJSONObject(i);
	       //         fd_id=json_data.getString("id");
	                fd_name=json_data.getString("ques");
	                op1=json_data.getString("option1");
	                op2=json_data.getString("option2");
	                op3=json_data.getString("option3");
	                op4=json_data.getString("option4");
	                System.out.println("Success till here");
	  //              String z = (String) b;
	                tv1.setText(String.valueOf(b));
	                tv2.setText(fd_name);
	                rb1.setText(op1);
	                rb2.setText(op2);
	                rb3.setText(op3);
	                rb4.setText(op4);
	            	btn.setText("Next");
	            	b+=1;
	        }
	        
	        }catch(JSONException e1){
	            Toast.makeText(getBaseContext(), "No Food Found", Toast.LENGTH_LONG).show();
	        }catch (ParseException e1){
	            e1.printStackTrace();
	        }
        	}
		    	}
			    		if(a >= (random-1))
				    	{
				    		btn.setText("End");
				    	}
				    	a+=1;
			    	}
		    	}

				private void startTimer() {
					// TODO Auto-generated method stub
					t = new Timer();     
					time = 00;
					min = 00;
					sec = 60;
				      task = new TimerTask() {  
				      
				        @Override  
				       public void run() {  
				        runOnUiThread(new Runnable() {  
				      
				          @Override  
				         public void run() {  
				          TextView tv1 = (TextView) findViewById(R.id.tv_timer_hr); 
				          TextView tv2 = (TextView) findViewById(R.id.tv_timer_min);
				          TextView tv3 = (TextView) findViewById(R.id.tv_timer_sec);
				          tv1.setText(String.valueOf(time) + " : ");
				          tv2.setText(String.valueOf(min) + " : ");
				          tv3.setText(String.valueOf(sec));
				          if (sec > 0){  
				           sec -= 1;  }
				          else if(min > 0) {  
				 //          tv1.setText("Welcome");
				           min -= 1;
				           sec = 60;
				          }else if(time > 0){
				        	  time -= 1;
				        	  min = 60;
				        	  sec = 60;
				          }
				          else{
				        	  t.cancel();
				        	  t.purge();
				        	  alertDialogBuilder = new AlertDialog.Builder(Exam1.this);
				      		
				      		alertDialogBuilder.setTitle("OOOppss...!!!");
				      		alertDialogBuilder.setIcon(R.drawable.ic_launcher);
				      		
				      		alertDialogBuilder.setMessage("Sorry... Time is Up...!!!");
				      		alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				    			public void onClick(DialogInterface dialog, int which) 
				    	        {       
				    	               //do some thing here which you need
				    				Toast.makeText(getBaseContext(),"Done", Toast.LENGTH_LONG).show();
				    				Intent openSecondpage= new Intent("com.competitiveexamapplication.RESULT");
				    				startActivity(openSecondpage);
				    	    }
				    		});
				      		alertDialog = alertDialogBuilder.create();	
				    		alertDialog.show();
				          }
				         }  
				        });  
				       }  
				      };  
				      t.scheduleAtFixedRate(task, 0, 1000);
					
				}
	 }

				);
		    
	 }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		showdialog();
	}

	private void showdialog() {
		// TODO Auto-generated method stub
		alertDialogBuilder = new AlertDialog.Builder(this);
		
		alertDialogBuilder.setTitle("Alert Dialog");
		alertDialogBuilder.setIcon(R.drawable.ic_launcher);
		
		alertDialogBuilder.setMessage("Are you sure you want to Exit the Exam...?");
		alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) 
	        {       
	               //do some thing here which you need
				Toast.makeText(getBaseContext(),"Done", Toast.LENGTH_LONG).show();
				Intent openSecondpage= new Intent("com.competitiveexamapplication.RESULT");
				startActivity(openSecondpage);
	    }
		});
		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
//		alertDialogBuilder.setNeutralButton("Can't Say", null);
		
		alertDialog = alertDialogBuilder.create();	
		alertDialog.show();
	}
	 }

