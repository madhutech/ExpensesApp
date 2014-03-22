package com.expensesapp.core;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensesapp.R;
import com.expensesapp.exceptions.StorageNotAvailableException;
import com.expensesapp.utilities.Constants;
import com.expensesapp.utilities.WriteCSV;
import com.expensesapp.vo.Expense;

import static com.expensesapp.utilities.Utilities.*;

public class EnterExpActivity extends Activity{	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		TextView txtView = new TextView(this); txtView.setTextSize(20);
		txtView.setText(msg);
		setContentView(txtView);
		setContentView(R.layout.activity_enterexp);
	}
	
	public void persistExpense(View v) {
		//Read below CSV or regex delimited files
		if(isStorageWritable())
		{
			try
			{
			EditText editTextDesc = (EditText) findViewById(R.id.editText_Exp_Desc);
			EditText editTextCatg = (EditText) findViewById(R.id.editText_Exp_Categ);
			EditText editTextAmt = (EditText) findViewById(R.id.editText_Exp_Amnt);
			EditText editTextDate = (EditText) findViewById(R.id.editText_Exp_Date);
			
			//create new Expense VO, and set values
			Expense exp = new Expense();
			exp.setExp_Desc(editTextDesc.getText().toString().trim());
			exp.setExp_Categ(editTextCatg.getText().toString().trim());
			exp.setExp_Amnt(Integer.parseInt(editTextAmt.getText().toString().trim()));
			exp.setExp_Date(editTextDate.getText().toString().trim());
			
			if(!(exp.getExp_Desc().equals(null)) && (exp.getExp_Desc().length()>0)
				&& !(exp.getExp_Categ().equals(null)) && (exp.getExp_Categ().length()>0)
				&& !(exp.getExp_Amnt().equals(null)) && (exp.getExp_Amnt()>0)
				&& !(exp.getExp_Date().equals(null)) && (exp.getExp_Date().length()>0)
				&& (isValidTextInput(exp.getExp_Desc()))
				&&(isValidTextInput(exp.getExp_Categ()))
				&& (isValidDateInput(exp.getExp_Date()))
				)//also some date input validations to be done
				//input validations to be done like ; not allowed in text fields
			{
			if(new WriteCSV().writeCSV(Constants.FILE_PATH,
										Constants.FILE_NAME,
										editTextDesc.getText().toString(),
										editTextCatg.getText().toString(),
										editTextAmt.getText().toString(),
										editTextDate.getText().toString(),
										Constants.DATA_DELIMITER))
				//Show a success splash screen.
				Toast.makeText(getApplicationContext(),
						"Expense Saved",Toast.LENGTH_LONG).show();
			else
				{
				//Show "Expense Not Saved" error splash screen.
				Toast.makeText(getApplicationContext(),
						"Expense Not Saved.View All Expenses to review",
						Toast.LENGTH_LONG).show();
				startActivity(new Intent(this,MainActivity.class));
				}
			}
			else
				//Show "All fields required" message
				Toast.makeText(getApplicationContext(),
						"Please input all and valid values",
						Toast.LENGTH_LONG).show();
			}
			catch(StorageNotAvailableException storNotAvailExc)
			{
				//Show "Storage Exception" message
				Toast.makeText(getApplicationContext(),
						storNotAvailExc.toString(),
						Toast.LENGTH_LONG).show();				
			}
			catch(FileNotFoundException fileNotFoundExc)
			{
				//Show "File Not Found exception" message
				Toast.makeText(getApplicationContext(),
						"File Not Found: "+fileNotFoundExc.getMessage(),
						Toast.LENGTH_LONG).show();
			}
				
			catch(IOException ioExc)
			{
				//Show "IOException" message
				Toast.makeText(getApplicationContext(),
						"I/O Exception: "+ioExc.getMessage(),
						Toast.LENGTH_LONG).show();
			}
			catch(Exception exc)
			{
				//Show "Exception" message
				Toast.makeText(getApplicationContext(),
						"Exception: "+exc.getMessage(),
						Toast.LENGTH_LONG).show();				
			}
		}
		else
		{
		//Show "storage NA" error splash screen.
		Toast.makeText(getApplicationContext(),
								"Storage Not Available, Please try later",
								Toast.LENGTH_LONG).show();
		startActivity(new Intent(this,MainActivity.class));
		}
	}

}