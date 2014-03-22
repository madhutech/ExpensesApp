package com.expensesapp.core;

import com.example.expensesapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.expensesapp.MESSAGE1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Submit an expense button
		Button btn_SubmitExpense = (Button) findViewById(R.id.button_Main_EnterExpense);
		btn_SubmitExpense.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {openExpenseActivity(v);}
		});
		
		//View All Expenses button
		Button btn_ViewAllExp = (Button)findViewById(R.id.button_Main_ViewAllExpenses);
		btn_ViewAllExp.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) { printAllExpenses(v);}
		});
		
		//View by Category button
		Button btn_ViewByCatg = (Button)findViewById(R.id.button_Main_ViewCatgOfExpenses);
		btn_ViewByCatg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { viewCatgOfExpenses(v);}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void openExpenseActivity(View view)
	{
		Intent intentEnterExp = new Intent(this, EnterExpActivity.class);
		intentEnterExp.putExtra(EXTRA_MESSAGE,"expensesbelow:");
		startActivity(intentEnterExp);
	}
	
	private void printAllExpenses(View view)
	{
		Intent intentViewAllExp = new Intent(this,ViewAllExpActivity.class) ;
		intentViewAllExp.putExtra(EXTRA_MESSAGE, "All Expenses:");
		startActivity(intentViewAllExp);
	}
	
	private void viewCatgOfExpenses(View view)
	{
		Intent intentViewCatgExp = new Intent(this,ViewByCatgActivity.class) ;
		intentViewCatgExp.putExtra(EXTRA_MESSAGE, "Expenses By Category");
		startActivity(intentViewCatgExp);		
	}

}