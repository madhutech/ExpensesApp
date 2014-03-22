package com.expensesapp.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensesapp.R;
import com.expensesapp.exceptions.StorageNotAvailableException;
import com.expensesapp.utilities.Constants;
import com.expensesapp.utilities.ReadCSV;

public class ViewAllExpActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewallexp);		
		ListView list = (ListView)findViewById(R.id.activity_ViewAllExp);
		Intent intent = getIntent();
		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		TextView txtView = new TextView(this); txtView.setTextSize(100);
		try
		{
			ArrayList<String> fileData = new ReadCSV().readCSV(Constants.FILE_PATH, Constants.FILE_NAME);
			//String[] fileData = {"Black" , "Blue" , "Green" , "White"};
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.textview_allexp,fileData);
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.grid_viewallexp,fileData);	
			txtView.setText(msg);
			txtView.setText("\n");
			for(String s:fileData)
				System.out.println("contents of file: "+s);
			//setContentView(txtView);
			//System.out.println("list.toString(): "+list.toString());
			list.setAdapter(adapter);
		}
		catch(StorageNotAvailableException strNotAvailExc)
		{
			//Show "Storage Not Available" error splash screen.
			Toast.makeText(getApplicationContext(),
							strNotAvailExc.toString(),
							Toast.LENGTH_LONG).show();

			startActivity(new Intent(this,MainActivity.class));
		}
		catch(FileNotFoundException fnfExc)
		{
			//Show "File Not Found" error splash screen.
			Toast.makeText(getApplicationContext(),
							"Data file not found!",
							Toast.LENGTH_LONG).show();

			startActivity(new Intent(this,MainActivity.class));

		}
		catch(IOException ioExc)
		{
			//Show "File Not Found" error splash screen.
			Toast.makeText(getApplicationContext(),
					"Some IO Exception has occured",
					Toast.LENGTH_LONG).show();
			startActivity(new Intent(this,MainActivity.class));

		}
		catch (Exception exc)
		{
			//Show "File Not Found" error splash screen.
			Toast.makeText(getApplicationContext(),
							"Exception occured!"+exc.toString(),
							Toast.LENGTH_LONG).show();
			exc.printStackTrace();
			startActivity(new Intent(this,MainActivity.class));
		}
		finally{}

	}
}