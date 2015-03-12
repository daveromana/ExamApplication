package com.competitiveexamapplication;

import com.competitiveexamapplication.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Study01 extends Activity{
	
	Button btn, btn1, btn2, btn3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study01);
		/*
		btn = (Button) findViewById(R.id.Btn_study_1);
		btn1 = (Button) findViewById(R.id.Btn_study_2);
		btn2 = (Button) findViewById(R.id.Btn_study_3);
		btn3 = (Button) findViewById(R.id.Btn_study_4);
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ourIntent = new Intent("com.competitiveexamapplication.MATH01");
				startActivity(ourIntent);
		}
				
		
		});
		
	btn1.setOnClickListener(new OnClickListener()
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent ourIntent = new Intent("com.competitiveexamapplication.LR01");
			startActivity(ourIntent);
	}
			
	
	});
	btn2.setOnClickListener(new OnClickListener()
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent ourIntent = new Intent("com.competitiveexamapplication.MAINACTIVITY");
			startActivity(ourIntent);
	}
			
	
	});
	btn3.setOnClickListener(new OnClickListener()
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent ourIntent = new Intent("com.competitiveexamapplication.MAINACTIVITY");
			startActivity(ourIntent);
	}
			
	
	});*/
	}
	
}
