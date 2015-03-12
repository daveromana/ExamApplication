package com.competitiveexamapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	Button btn , btn1;
	String student;
	EditText txt_username, txt_psw, user;
	TextView tv;
	StringBuilder sb;
	String result1,result, ans;
	InputStream is;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		
		final EditText txt_username = (EditText) findViewById(R.id.et_login_username);
	    final EditText txt_psw = (EditText) findViewById(R.id.et_login_pwd);
	    Button btn_signin=(Button) findViewById(R.id.btn_login_signin);

		
		
		 //Inner class to implement Button Listener when button is clicked.
	    btn_signin.setOnClickListener(new OnClickListener()
	    {

	    	public void onClick(View v) 
	    	{		    		
	    		
	    		   String username = txt_username.getText().toString();
	    		   String password = txt_psw.getText().toString();
	    		   
	    		   SharedPreferences userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);
	            	 Editor edit = userDetails.edit();
	            	 edit.clear();
	            	 edit.putString("username", username.toString());
	            	 edit.putString("password", password.toString());
	            	 edit.apply();
	            	 Toast.makeText(getBaseContext(), "Login details are saved..", Toast.LENGTH_SHORT).show();
	    		   
	    		  try
	    		  {
	    		  	JSONObject json = new JSONObject();
	    		  	json.put("username",username);
	    		  	json.put("password",password);
	    		  	postData(json);
	    		  } catch (JSONException e){
	    		  	e.printStackTrace();
	    		  }
	 	    
		     } //Closes the onClick method
	    	}//Closes the inner class
	    ); //Closes the onClickListener
	    
	   
	    	
	}
	public void postData(JSONObject json) throws JSONException {
		 try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost("http://192.168.1.11/sarjaksel.php");
	            List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
	            nvp.add(new BasicNameValuePair("json",json.toString()));
				httppost.setEntity(new UrlEncodedFormEntity(nvp));
	            HttpResponse response = httpclient.execute(httppost);
	            if(response != null){
	            	is = response.getEntity().getContent();
	            }
	           
	         //   Toast.makeText(getBaseContext(), "Connection Found", Toast.LENGTH_LONG).show();
	        }catch(Exception e){
	            Log.e("log_tag", "Error in http connection"+e.toString());
	        }
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
	            ans = result1.toString();
	            result = "Matches";
	            Log.e("log_tag", "Result"+result1.toString()+ans.length());
	             if(ans.length() < 7){
	            	 Toast.makeText(getBaseContext(), "unSuccess", Toast.LENGTH_LONG).show();
	             }
	             else{
	            	 
	 	    			Toast.makeText(getBaseContext(), "You have Successfully Logged In.", Toast.LENGTH_LONG).show();
	      	        	Intent ourIntent = new Intent("com.competitiveexamapplication.MAINACTIVITY");
	     				startActivity(ourIntent);
	            	
	             }
	        }catch(Exception e){
	            Log.e("log_tag", "Error converting result "+e.toString());
	        }
	}
	    }


