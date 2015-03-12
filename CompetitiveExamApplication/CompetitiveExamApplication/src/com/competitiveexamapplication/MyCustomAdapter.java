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

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomAdapter extends BaseAdapter {

	Context context ;
	String [] values ,mock1;
	int viewStyle ;
	InputStream as;
	StringBuilder sb;
	String result, mock;

	public static final int VIEW_LIST = 0 ;
	public static final int VIEW_GRID = 1 ;
	
	public MyCustomAdapter(Context context){
		this.context = context ;
	}
	
	public void setValues ( String []values){
		
		try{
				    
	       this.values = new String [values.length];
	       Log.e("log_tag", "Fetching data MyCustom Adapter" +values.length );
          for(int i=0;i<values.length;i++){
        	  this.values[i] = values[i];
	       Log.e("log_tag", "Success in Fetched" );
	       
          }
		}catch(Exception e){
			Log.e("log_tag", "Error converting result in MyCustomadapter"+e.toString()+mock.toString());
		}
	}
	
	public void setViewStyle ( int viewStyle ){
		this.viewStyle = viewStyle;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return values[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		TextView txt = new TextView(context);
		txt.setText(values[arg0]);
		txt.setWidth(android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		
		LinearLayout layout = new LinearLayout(context);

		switch (viewStyle) {
		case VIEW_LIST:

			txt.setLayoutParams(new ListView.LayoutParams(150, 50));
			txt.setGravity(Gravity.CENTER_VERTICAL);
			txt.setTextColor(Color.YELLOW);
			break;

		case VIEW_GRID:

			txt.setLayoutParams(new GridView.LayoutParams(50, 50));
			
			break ;
		}
		
		layout.addView(txt);
		
		return layout;
	}

}
