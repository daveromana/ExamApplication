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
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Result extends Activity{

	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11;
	InputStream is, as;
	StringBuilder sb;
	TableRow row;
	int a = 1;
	JSONObject object;
	String [] q,op1, op2, op3, op4, op5, op6, op7,op8,op9,op10;
	String result, uname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		TableLayout ll = (TableLayout) findViewById(R.id.tablelayout);
		
		SharedPreferences user = getBaseContext().getSharedPreferences("userdetails", MODE_PRIVATE);
		uname = user.getString("username", "");
		Log.e("log_tag", "In result data 1" );
		try{
			
		JSONObject json = new JSONObject();
		Log.e("log_tag", "In result data 2" );
    	System.out.println(""+ uname);
	  	json.put("uname",uname);
        HttpPost httppost = new HttpPost("http://192.168.1.11/result1.php");
        List<NameValuePair> nvp = new ArrayList<NameValuePair>(1);
	  	nvp.add(new BasicNameValuePair("json",json.toString()));
	  	httppost.setEntity(new UrlEncodedFormEntity(nvp));
	  	Log.e("log_tag", "In result data 3" );
        HttpClient httpclient1 = new DefaultHttpClient();

        HttpResponse response1 = httpclient1.execute(httppost);
        HttpEntity entity = response1.getEntity();
        is = entity.getContent();
        Log.e("log_tag", "In result data 4" );
      
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");
            String line="0";
          
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
             
            is.close();
            result=sb.toString();
            Log.e("log_tag", "In result data 5" + result.toString() );
		}catch(Exception e){
			Log.e("log_tag", "error some where here...." + e.toString() );
		}
		try{
			JSONArray jArray = new JSONArray(result);
	//		 object = new JSONObject(result);
			Log.e("log_tag", "In result data 9" );
//			object = object.optJSONObject("ar");
	//		JSONArray jA = object.getJSONArray();
			Log.e("log_tag", "In result data 10" );
		JSONObject object = null;
		op4 = new String [jArray.length()] ;
		op1 = new String [jArray.length()] ;
		op2 = new String [jArray.length()] ;
		op3 = new String [jArray.length()] ;
		op5 = new String [jArray.length()] ;
		op6 = new String [jArray.length()] ;
		op7 = new String [jArray.length()] ;
		op8 = new String [jArray.length()] ;
		op9 = new String [jArray.length()] ;
		op10 = new String [jArray.length()] ;
		for(int i=0;i<jArray.length();i++){
					object = jArray.getJSONObject(i);
					Log.e("log_tag", "In result data 11" + i);
						
						
						op4[i]=object.optString("examid");
		                op1[i]=object.optString("rightans");
		                op2[i]=object.optString("wrongans");
		                op3[i]=object.optString("skipped");
		                op5[i]=object.optString("marksobt");
		                op6[i]=object.optString("percentage");
		                op7[i]=object.optString("totalmarks");
		                if(i<jArray.length()-1){
		                	
		                object = jArray.getJSONObject(i+1);
		                op8[i] = object.optString("ename");
		    			op9[i] = object.optString("catid");
		    			op10[i] = object.optString("date");
		                }
		                i++;
		    			Log.e("log_tag", "In result data 12" +jArray.length());
		     //           op8=object.optString("percentage");
		}
		for(int b=0;b<jArray.length();b++){
				
				Log.e("log_tag", "In result data 13" +b);
		        TableRow row= new TableRow(this);
		   		TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
		   		row.setLayoutParams(lp);
		   		  Log.e("log_tag", "In result data 6" +b);
		   		     tv1 = new TextView(this);
		   		     tv2 = new TextView(this);
		   		     tv3 = new TextView(this);
		   		     tv4 = new TextView(this);
		   		     tv5 = new TextView(this);
		   		     tv6 = new TextView(this);
		   		     tv7 = new TextView(this);
		   		     tv8 = new TextView(this);
		   		     tv9 = new TextView(this);
		   		     tv10 = new TextView(this);
		   		     tv11 = new TextView(this);
		   		     tv1.setTextColor(Color.WHITE);
		   		  tv2.setTextColor(Color.WHITE);
		   		tv3.setTextColor(Color.WHITE);
		   		tv4.setTextColor(Color.WHITE);
		   		tv5.setTextColor(Color.WHITE);
		   		tv6.setTextColor(Color.WHITE);
		   		tv7.setTextColor(Color.WHITE);
		   		tv8.setTextColor(Color.WHITE);
		   		tv9.setTextColor(Color.WHITE);
		   		tv10.setTextColor(Color.WHITE);
		   		tv11.setTextColor(Color.WHITE);
		//   		     tv1.setMinWidth(50);
		   		     if(b%2==0){
		   		     String z = String.valueOf(a);
		   		     Log.e("log_tag", "In result data 7"+b );
		   		     tv1.setText(z);
		   		     a++;
		   		     }
		   		     tv11.setText(op10[b]);
		   		     tv2.setText(op4[b]);
//		   		     tv3.setText("Category");
//		   		     tv4.setText("Mock");
		   		     tv5.setText(op1[b]);
		   		     tv6.setText(op2[b]);
		   		     tv7.setText(op3[b]);
		   		     tv8.setText(op5[b]);
		   		     tv9.setText(op7[b]);
		   		     tv10.setText(op6[b]);
		   		     tv4.setText(op9[b]);
		   		     tv3.setText(op8[b]);
		   		     
		   		  Log.e("log_tag", "In result data 00" );
		   		     row.addView(tv1);
		   		     row.addView(tv11);
		   		     row.addView(tv2);
		   		     row.addView(tv3);
				     row.addView(tv4);
		   		     row.addView(tv5);
		   		     row.addView(tv6);
		   		     row.addView(tv7);
		   		     row.addView(tv9);
		   		     row.addView(tv8);  
		   		     row.addView(tv10);
		   		     ll.addView(row, b+1);
		   		     
		   		  Log.e("log_tag", "In result data 8" );
			        }

		}catch(Exception e){
			Log.e("log_tag", "error some where here down...." + e.toString() );
		}
		
	}

}
