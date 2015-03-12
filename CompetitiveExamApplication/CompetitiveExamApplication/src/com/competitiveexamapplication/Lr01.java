package com.competitiveexamapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.competitiveexamapplication.R;

public class Lr01 extends Activity{
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lr01);
		
		btn = (Button) findViewById(R.id.btn_lr_nxt);
		
		btn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ourIntent = new Intent("com.competitiveexamapplication.LR_MENU");
				startActivity(ourIntent);
			}
		});
	}

}

