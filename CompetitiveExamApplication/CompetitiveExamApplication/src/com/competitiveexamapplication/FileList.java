package com.competitiveexamapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FileList extends Activity implements OnItemClickListener{


	RelativeLayout rootLayout ;
	LinearLayout rootContainer ;
	RadioGroup rdgViewStyle ;
	
	InputStream as;
	StringBuilder sb;
	String result, mock;
	String [] values ,mock1;
	
	ListView lstFileList ;
//	GridView gridFileList ;
	
	File filesDirectory ;
	String [] fileNames ;

	private MyCustomAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_file_list_main);
		
//		filesDirectory = getFilesDir();
//		fileNames = filesDirectory.list();
		
		adapter = new MyCustomAdapter(this);
		try{
		JSONObject json2 = null;
		
		HttpClient httpclient2 = new DefaultHttpClient();
        HttpPost httppost2 = new HttpPost("http://marksinstitute.heliohost.org/admin/mock1.php");
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
         result=sb.toString();
         
         Log.e("log_tag", "Fetching data from exam.php in FileList");
         JSONArray jArray = new JSONArray(result);
	       Log.e("log_tag", "Fetching data FileList  1" );
	       mock1 = new String [jArray.length()];
	       this.values = new String [jArray.length()];
	       Log.e("log_tag", "Fetching data FileList" +jArray.length() );
          for(int i=0;i<jArray.length();i++){
   //     	  this.values[i] = json2.toString(VIEW_LIST);
	       json2= jArray.getJSONObject(i);
	       Log.e("log_tag", "Fetching data FileList  3" );
	       mock1[i] = json2.getString("mockname");
	       Log.e("log_tag", "Fetching data FileList  4" );

          }
		}catch(Exception e){
			Log.e("log_tag", "Error converting result in FileList"+e.toString()+mock.toString());
		}
		
		adapter.setValues(mock1);
		
		rootLayout = (RelativeLayout) findViewById(R.id.rootLayoutListFiles);
		rootContainer = (LinearLayout) findViewById(R.id.rootContainer);
		
	//	rdgViewStyle = (RadioGroup) findViewById(R.id.rdgViewStyle);
	//	rdgViewStyle.setOnCheckedChangeListener(this);
		
		rootContainer.removeAllViews();
			
			adapter.setViewStyle(MyCustomAdapter.VIEW_LIST);
			
			lstFileList = new ListView(this);
			lstFileList.setPadding(10, 10, 10, 10);
			lstFileList.setAdapter(adapter);
			
			rootContainer.addView(lstFileList);
			lstFileList.setOnItemClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_list_main, menu);
		return true;
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Object a = lstFileList.getItemAtPosition(position);
		String b = a.toString();
		Log.e("log_tag", "Loading Intent data FileList  1" );
		 SharedPreferences exam = getSharedPreferences("exam", MODE_PRIVATE);
    	 Editor edit = exam.edit();
    	 edit.clear();
    	 edit.putString("examid", String.valueOf(position));
    	 edit.putString("cat", "Mock");
    	 edit.putString("ename", b.toString());
    	 edit.apply();
    	 Log.e("log_tag", "Loading Intent data FileList  2" );
    	 Toast.makeText(getBaseContext(), "Exam details are saved..", 3000).show();
		Toast.makeText(getBaseContext(), "This is it...!!!" + b.toString(), Toast.LENGTH_LONG).show();
		Intent ourIntent = new Intent("com.competitiveexamapplication.EXAMXML2");
		startActivity(ourIntent);
		Log.e("log_tag", "Loading Intent data FileList  3" );
	}
	
}
