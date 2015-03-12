package com.competitiveexamapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Examxml2 extends Activity implements OnClickListener{

	String ename, result, Uname, mockid, examid, op1, op2, op3, op4, op5, op6, op7, sub,eid;
	InputStream is, as;
	StringBuilder sb;
	int a;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("log_tag", "In Intent data 3" );
		setContentView(R.layout.examxml2);
		
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		TextView tv4 = (TextView) findViewById(R.id.textView4);
		TextView tv6 = (TextView) findViewById(R.id.textView6);
		TextView tv8 = (TextView) findViewById(R.id.textView8);
		TextView tv10 = (TextView) findViewById(R.id.textView10);
		TextView tv12 = (TextView) findViewById(R.id.textView12);
		TextView tv14 = (TextView) findViewById(R.id.textView14);
		TextView tv16 = (TextView) findViewById(R.id.textView16);
		
	
		Button btn2 = (Button)findViewById(R.id.examxml2_btn2);

		btn2.setOnClickListener(this);
		
		Log.e("log_tag", "In Intent data 1" );
		SharedPreferences exam1 = getBaseContext().getSharedPreferences("exam", MODE_PRIVATE);
		eid = exam1.getString("examid", "");
		ename = exam1.getString("ename", "");
		sub = exam1.getString("cat", "");
		Log.e("log_tag", "In Intent data 2" );
			
		
		try{
			
			if(sub.equalsIgnoreCase("mock")){
			
			JSONObject json = new JSONObject();
        	System.out.println(""+ eid);
		  	json.put("id",eid);
            HttpPost httppost = new HttpPost("http://marksinstitute.heliohost.org/admin/mock.php");
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(1);
		  	nvp.add(new BasicNameValuePair("json",json.toString()));
		  	httppost.setEntity(new UrlEncodedFormEntity(nvp));
		  	Log.e("log_tag", "In Intent data 4" );
            HttpClient httpclient1 = new DefaultHttpClient();
 
            HttpResponse response1 = httpclient1.execute(httppost);
            HttpEntity entity = response1.getEntity();
            is = entity.getContent();
            Log.e("log_tag", "In Intent data 5" );
          
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            sb = new StringBuilder();
	            sb.append(reader.readLine() + "\n");
	            String line="0";
	          
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	             
	            is.close();
	            result=sb.toString();
	            Log.e("log_tag", "In Intent data 6" + result.toString() );
	       
	            JSONObject object = new JSONObject(result);
	   //         JSONArray jA = object.getJSONArray("mo");
	            
 //           JSONArray jA = new JSONArray(result);
	//        JSONObject json_data=null;
	        Log.e("log_tag", "In Intent data 7" );
	        for(int i=0;i<1;i++){
	//        	 mockid = object.optString("id");
      //         mockid=object.optString("id");
                op1=object.optString("mockname");
                op2=object.optString("totalmarks");
                op3=object.optString("noofque");
                op5=object.optString("marksperque");
                op6=object.optString("duration");
                op7=object.optString("negativemark");
	        }
    //            json_data = jA.getJSONObject(i);
    //            examid=object.optString("examid");
       //        	a = Integer.valueOf(examid);
        //        a +=1;
        //        examid = String.valueOf(a);
      //          tv2.setText(examid);
	        
	        
	        }
			else{
				
				JSONObject json = new JSONObject();
	        	System.out.println(""+ eid);
			  	json.put("id",eid);
	            HttpPost httppost = new HttpPost("http://marksinstitute.heliohost.org/admin/subject1.php");
	            List<NameValuePair> nvp = new ArrayList<NameValuePair>(1);
			  	nvp.add(new BasicNameValuePair("json",json.toString()));
			  	httppost.setEntity(new UrlEncodedFormEntity(nvp));
			  	Log.e("log_tag", "In Intent data 4" );
	            HttpClient httpclient1 = new DefaultHttpClient();
	 
	            HttpResponse response1 = httpclient1.execute(httppost);
	            HttpEntity entity = response1.getEntity();
	            is = entity.getContent();
	            Log.e("log_tag", "In Intent data 5" );
	          
		            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		            sb = new StringBuilder();
		            sb.append(reader.readLine() + "\n");
		            String line="0";
		          
		            while ((line = reader.readLine()) != null) {
		                sb.append(line + "\n");
		            }
		             
		            is.close();
		            result=sb.toString();
		            Log.e("log_tag", "In Intent data 6" + result.toString() );
		       
		            JSONObject object = new JSONObject(result);
		   //         JSONArray jA = object.getJSONArray("mo");
		            
	 //           JSONArray jA = new JSONArray(result);
		//        JSONObject json_data=null;
		        Log.e("log_tag", "In Intent data 7" );
		        for(int i=0;i<1;i++){
		//        	 mockid = object.optString("id");
	      //         mockid=object.optString("id");
	                op1=object.optString("subname");
	                op2=object.optString("totalmarks");
	                op3=object.optString("noofque");
	                op5=object.optString("marksperque");
	                op6=object.optString("duration");
	                op7=object.optString("negativemark");
		        }
	    //            json_data = jA.getJSONObject(i);
	    //            examid=object.optString("examid");
	       //        	a = Integer.valueOf(examid);
	        //        a +=1;
	        //        examid = String.valueOf(a);
	      //          tv2.setText(examid);
				
			}
			 System.out.println("Success till here");
             tv4.setText(op1);
             tv6.setText("NA");
             tv8.setText(op3);
             tv10.setText(op6);
             tv12.setText(op2);
             tv14.setText(op5);
             tv16.setText(op7);
             Log.e("log_tag", "In Intent data 8" );
		}catch(Exception e){
			Log.e("log_tag", "error some where here...." + e.toString() );
		}
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
			Intent ourIntent = new Intent("com.competitiveexamapplication.EXAM1");
			startActivity(ourIntent);
			
		}

}
