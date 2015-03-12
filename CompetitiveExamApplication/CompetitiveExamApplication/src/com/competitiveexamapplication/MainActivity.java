package com.competitiveexamapplication;

import com.competitiveexamapplication.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	Button btn, btn1, btn2, btn3;
	TextView textview1,textview2;
	AlertDialog.Builder alertDialogBuilder ;
	AlertDialog alertDialog ;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	 
	btn = (Button)findViewById(R.id.Btn_main_1);
	btn1 = (Button) findViewById(R.id.Btn_main_2);
	btn2 = (Button) findViewById(R.id.Btn_main_3);
	btn3 = (Button) findViewById(R.id.Btn_main_4);
	textview1 = (TextView) findViewById(R.id.TV1);
	textview2 = (TextView) findViewById(R.id.tv_logout);
	textview2.setOnClickListener(this);
	btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
		
				Intent ourIntent = new Intent("com.competitiveexamapplication.STUDY");
				startActivity(ourIntent);
			}
		});
	
	btn1.setOnClickListener(new OnClickListener()
	{
		@Override
		public void onClick(View arg0) {
	
			Intent ourIntent = new Intent("com.competitiveexamapplication.EXAMXML");
			startActivity(ourIntent);
		}
	});
	
	btn2.setOnClickListener(new OnClickListener()
	{
		@Override
		public void onClick(View arg0) {
	
			Intent ourIntent = new Intent("com.competitiveexamapplication.RESULT1");
			startActivity(ourIntent);
		}
	});
	
	btn3.setOnClickListener(new OnClickListener()
	{
		@Override
		public void onClick(View arg0) {
	
			Intent ourIntent = new Intent("com.competitiveexamapplication.SIMPLEXYPLOTACTIVITY");
			startActivity(ourIntent);
		}
	});
	 }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		alertDialogBuilder = new AlertDialog.Builder(this);
  		alertDialogBuilder.setTitle("LogOut");
  		alertDialogBuilder.setIcon(R.drawable.ic_launcher);
  		
  		alertDialogBuilder.setMessage("Are you sure you want to Logout!!!");
  		alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) 
	        {       
	               //do some thing here which you need
		//		Toast.makeText(getBaseContext(),"Done", Toast.LENGTH_LONG).show();
				Intent openSecondpage= new Intent("com.competitiveexamapplication.LOGIN");
				startActivity(openSecondpage);
	    }
		});
  		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
  		alertDialog = alertDialogBuilder.create();	
		alertDialog.show();
		
	}

}
