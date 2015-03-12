package com.competitiveexamapplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Image extends Activity{
	InputStream is, as;
	HttpPost httppost;
	String chapid, chapname;
	int srno=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		TextView tv = (TextView) findViewById(R.id.image_TV1);
		final Button btn_pre = (Button) findViewById(R.id.img_button1);
		final Button btn_next = (Button) findViewById(R.id.img_button2);
		final ImageView img = (ImageView) findViewById(R.id.imageview);
		SharedPreferences userDetails = getBaseContext().getSharedPreferences("chapter", MODE_PRIVATE);
		chapid = userDetails.getString("chapid", "");
		chapname = userDetails.getString("chapname", "");
		tv.setText(chapname);
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("chapid", chapid);
			json2.put("srno", srno);
			HttpPost httppost2 = new HttpPost("http://192.168.1.11/image.php");
            HttpClient httpclient1 = new DefaultHttpClient();
            List<NameValuePair> nvp2 = new ArrayList<NameValuePair>(1);
    	  	nvp2.add(new BasicNameValuePair("json",json2.toString()));
    	  	httppost2.setEntity(new UrlEncodedFormEntity(nvp2));
            HttpResponse response1 = httpclient1.execute(httppost2);
            HttpEntity imageUrl = response1.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(imageUrl);
            InputStream input = b_entity.getContent();
            // where imageUrl is what you pulled out from the rss
            // feed
            Bitmap bitmap = BitmapFactory
                    .decodeStream(input);
            if (bitmap != null) {
                img.setImageBitmap(bitmap);
            }
        } catch (MalformedURLException e1) {
            // log exception here
        } catch (IOException e1) {
            Log.e("...............................", "" + e1);
            // log exception here
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btn_pre.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(srno>1){
					btn_next.setEnabled(true);
					srno-=1;
					if(srno>=2){
						btn_pre.setEnabled(true);
					}
					else if(srno<=1){
						btn_pre.setEnabled(false);
					}
					
					JSONObject json2 = new JSONObject();
					try {
						json2.put("chapid", chapid);
						json2.put("srno", srno);
						HttpPost httppost2 = new HttpPost("http://192.168.1.11/image.php");
			            HttpClient httpclient1 = new DefaultHttpClient();
			            List<NameValuePair> nvp2 = new ArrayList<NameValuePair>(1);
			    	  	nvp2.add(new BasicNameValuePair("json",json2.toString()));
			    	  	httppost2.setEntity(new UrlEncodedFormEntity(nvp2));
			            HttpResponse response1 = httpclient1.execute(httppost2);
			            HttpEntity imageUrl = response1.getEntity();
			            BufferedHttpEntity b_entity = new BufferedHttpEntity(imageUrl);
			            InputStream input = b_entity.getContent();
			            // where imageUrl is what you pulled out from the rss
			            // feed
			            Bitmap bitmap = BitmapFactory
			                    .decodeStream(input);
			            if (bitmap != null) {
			                img.setImageBitmap(bitmap);
			            }
			        } catch (MalformedURLException e1) {
			            // log exception here
			        } catch (IOException e1) {
			            Log.e("...............................", "" + e1);
			            // log exception here
			        } catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		btn_next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(srno<4){
					btn_pre.setEnabled(true);
					srno+=1;
					if(srno>=4){
						btn_next.setEnabled(false);
					}
					else if(srno>=1){
						btn_next.setEnabled(true);
					}
					
					JSONObject json2 = new JSONObject();
					try {
						json2.put("chapid", chapid);
						json2.put("srno", srno);
						HttpPost httppost2 = new HttpPost("http://192.168.1.11/image.php");
			            HttpClient httpclient1 = new DefaultHttpClient();
			            List<NameValuePair> nvp2 = new ArrayList<NameValuePair>(1);
			    	  	nvp2.add(new BasicNameValuePair("json",json2.toString()));
			    	  	httppost2.setEntity(new UrlEncodedFormEntity(nvp2));
			            HttpResponse response1 = httpclient1.execute(httppost2);
			            HttpEntity imageUrl = response1.getEntity();
			            BufferedHttpEntity b_entity = new BufferedHttpEntity(imageUrl);
			            InputStream input = b_entity.getContent();
			            // where imageUrl is what you pulled out from the rss
			            // feed
			            Bitmap bitmap = BitmapFactory
			                    .decodeStream(input);
			            if (bitmap != null) {
			                img.setImageBitmap(bitmap);
			            }
			        } catch (MalformedURLException e1) {
			            // log exception here
			        } catch (IOException e1) {
			            Log.e("...............................", "" + e1);
			            // log exception here
			        } catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
	}
}
