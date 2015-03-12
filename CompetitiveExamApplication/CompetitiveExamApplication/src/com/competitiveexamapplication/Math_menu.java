package com.competitiveexamapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Math_menu extends ListActivity {
	String MathsMenu[] = {"Percentage", "Profit & Loss", "Simple Interst", 
			"Compound Interest", "Time & Work", "Pipes & Cisterns", "Time Distance, Train & Boat",
			"Area & Volume", "Ratio & Proportion", "Aligation & Mixture", "Age",
			"Average", "Clock & Calendar", "Real Numbers", "Set Theory", "Indices",
			"Sequence & Series", "Quadratic Equation", "Matrix", "Permutation",
			"Probability", "Trigonometrical Function"};

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String cheese = MathsMenu[position];
		try{
		Class ourClass = Class.forName("com.competitiveexamapplication." + cheese);
		Intent ourIntent = new Intent(Math_menu.this, ourClass);
		startActivity(ourIntent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Math_menu.this, 
				android.R.layout.simple_list_item_1, MathsMenu));
	}

	

}
