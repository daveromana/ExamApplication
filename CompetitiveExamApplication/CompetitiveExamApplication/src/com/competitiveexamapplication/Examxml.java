package com.competitiveexamapplication;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Examxml extends Activity implements OnClickListener{

	Button btn1, btn2;
	TextView tv1;
	AlertDialog.Builder alertDialogBuilder ;
	AlertDialog alertDialog ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam);
		
		btn1 = (Button) findViewById(R.id.btn_mock_exam);
		btn2 = (Button) findViewById(R.id.btn_sub_exam);
		tv1 = (TextView) findViewById(R.id.tv_logout);
		btn2.setOnClickListener(this);
		tv1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
		
				alertDialogBuilder = new AlertDialog.Builder(Examxml.this);
		  		alertDialogBuilder.setTitle("LogOut");
		  		alertDialogBuilder.setIcon(R.drawable.ic_launcher);
		  		
		  		alertDialogBuilder.setMessage("Are you sure you want to Logout!!!");
		  		alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
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
		});
		btn1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
		
				Intent ourIntent = new Intent("com.competitiveexamapplication.FILELIST");
				startActivity(ourIntent);
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent ourIntents = new Intent("com.competitiveexamapplication.SUBJECTLIST");
		startActivity(ourIntents);
	}
	
}
