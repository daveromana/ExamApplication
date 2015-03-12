package com.competitiveexamapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.competitiveexamapplication.R;


public class Imagepage1 extends Activity{

	@Override
	protected void onCreate(Bundle first01) {
		// TODO Auto-generated method stub
		super.onCreate(first01);
		setContentView(R.layout.imagepage1);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openSecondpage= new Intent("com.competitiveexamapplication.LOGIN");
					startActivity(openSecondpage);
				}
			}
		};
		timer.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}

