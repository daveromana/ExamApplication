package com.competitiveexamapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.competitiveexamapplication.Lr_menu;

public class Lr_menu extends ListActivity{
	String LRMenu[] = {"Direction Test" , "Number Ranking" , 
			"Coding-Decoding" , "Number & Alphabetic Series" , "Blood Relation" ,
			"Mathematical Conclusion", "Verbal Analog", "Input Puzzle",
			"Ven Diagram", "Syllogism", "Problem Solving", "Decision Making",
			"Data Sufficiency", "Statements & Arguments", "Statements & Assumptions",
			"Statements & Conclusions", "Courses of Action", "Miscellaneous Exercise"};
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String cheese = LRMenu[position];
		try{
		Class ourClass = Class.forName("com.competitiveexamapplication." + cheese);
		Intent ourIntent = new Intent(Lr_menu.this, ourClass);
		startActivity(ourIntent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(Lr_menu.this, 
				android.R.layout.simple_list_item_1, LRMenu));
	}

}
