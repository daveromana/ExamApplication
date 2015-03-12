package com.competitiveexamapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Result1 extends Activity implements OnClickListener{
	
	Button btn1, btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result1);
		
		btn1 = (Button) findViewById(R.id.res_button1);
		btn2 = (Button) findViewById(R.id.res_button2);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.res_button1:
			Intent ourIntent = new Intent("com.competitiveexamapplication.RESULT");
			startActivity(ourIntent);
			break;
		case R.id.res_button2:
			Intent ourIntents = new Intent("com.competitiveexamapplication.SIMPLEXYPLOTACTIVITY");
			startActivity(ourIntents);
			break;
		}
	}

}
