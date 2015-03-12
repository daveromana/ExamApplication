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
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class Chapter extends Activity implements OnItemClickListener{
	RelativeLayout rootLayout ;
	LinearLayout rootContainer ;
	RadioGroup rdgViewStyle ;
	
	InputStream as;
	StringBuilder sb;
	String result, mock,id,name;
	String [] values ,mock1;
	
	ListView lstFileList ;
	String [] fileNames ;
	private MyCustomAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chapter);
		
		adapter = new MyCustomAdapter(this);
		try{
			SharedPreferences studys = getBaseContext().getSharedPreferences("study", MODE_PRIVATE);
			id = studys.getString("studyid", "");
			 Log.e("log_tag", "Fetching data from chapter.php in FileList 0" +id.toString());
			 JSONObject json2 = new JSONObject();
		json2.put("id", id);
		 Log.e("log_tag", "Fetching data from chapter.php in FileList 1");
		HttpClient httpclient2 = new DefaultHttpClient();
        HttpPost httppost2 = new HttpPost("http://192.168.1.11/chapter.php");
        List<NameValuePair> nvp2 = new ArrayList<NameValuePair>(1);
	  	nvp2.add(new BasicNameValuePair("json",json2.toString()));
	  	httppost2.setEntity(new UrlEncodedFormEntity(nvp2));
	  	HttpResponse response2 = httpclient2.execute(httppost2);
	  	HttpEntity entity2 = response2.getEntity();
        as = entity2.getContent();
        Log.e("log_tag", "Fetching data from chapter.php in FileList 2" );
	  	 BufferedReader reader = new BufferedReader(new InputStreamReader(as,"iso-8859-1"),8);
         sb = new StringBuilder();
         sb.append(reader.readLine() + "\n");
         String line="0";		 	      
         while ((line = reader.readLine()) != null) {
             sb.append(line + "\n");
         }
         as.close();
         result=sb.toString();
         
         Log.e("log_tag", "Fetching data from chapter.php in FileList3");
         JSONArray jArray = new JSONArray(result);
	       Log.e("log_tag", "Fetching data FileList  1" );
	       mock1 = new String [jArray.length()];
	       this.values = new String [jArray.length()];
	       Log.e("log_tag", "Fetching data FileList" +jArray.length() );
          for(int i=0;i<jArray.length();i++){
   //     	  this.values[i] = json2.toString(VIEW_LIST);
	       json2= jArray.getJSONObject(i);
	       Log.e("log_tag", "Fetching data FileList  3" );
	       mock1[i] = json2.getString("chapname");
	       Log.e("log_tag", "Fetching data FileList  4" );

          }
		}catch(Exception e){
			Log.e("log_tag", "Error converting result in FileList"+e.toString()+mock.toString());
		}
		
		adapter.setValues(mock1);
		
		rootLayout = (RelativeLayout) findViewById(R.id.rootLayoutListFiles);
		rootContainer = (LinearLayout) findViewById(R.id.rootContainer);
		
		rootContainer.removeAllViews();
			
			adapter.setViewStyle(MyCustomAdapter.VIEW_LIST);
			
			lstFileList = new ListView(this);
			lstFileList.setPadding(10, 10, 10, 10);
			lstFileList.setAdapter(adapter);
			
			rootContainer.addView(lstFileList);
			lstFileList.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Object a = lstFileList.getItemAtPosition(position);
		String b = a.toString();
		SharedPreferences chapter = getSharedPreferences("chapter", MODE_PRIVATE);
	   	 Editor edit = chapter.edit();
	   	 edit.clear();
	   	 edit.putString("chapid", String.valueOf(position));
	   	 edit.putString("chapname", b.toString());
	   	 edit.apply();
		Intent ourIntents = new Intent("com.competitiveexamapplication.IMAGE");
		startActivity(ourIntents);
		
	}
}
