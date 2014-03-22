package com.expensesapp.utilities;

import android.os.Environment;

public class Utilities {
	
	/* Checks if external storage is available for read and write. Returns true if yes */
	public static boolean isStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state))
			return true;
		else
			return false;
	}
	
	// Check if the input is a valid text
	public static boolean isValidTextInput(String str) {
		char c = '0';
		boolean isValid = true;
		for (int i = 0; i < str.length(); i++) 
		{
			c = str.charAt(i);
			if ((!Character.isLetterOrDigit(c))
				&& (!Character.isWhitespace(c))
				&& (!Character.isSpaceChar(c))
				&& (Character.CONNECTOR_PUNCTUATION != Character.getType(c))
				&& (Character.DASH_PUNCTUATION != Character.getType(c))
				&& (Character.MATH_SYMBOL != Character.getType(c)
				&& (Character.START_PUNCTUATION != Character.getType(c)))
				&& (Character.END_PUNCTUATION != Character.getType(c))
				)
			{
				isValid = false;
				break;
			}
		}
		// we don't want user to enter delimiter in text
		if (str.contains(Constants.DATA_DELIMITER))
			isValid = false;
		return isValid;
	}
	
	//Check if the date input is valid format (DD-MM-YYYY)
	public static boolean isValidDateInput(String str)
	{
		boolean isValid = true;
		final int DAY =0, MONTH=1, YEAR=2;
		//really basic checking...will come back to it and write serious checks
		if(str.contains(Constants.INPUT_DATE_SEPERATOR))
		{
			String[] dd_mm_yyyy = str.split(Constants.INPUT_DATE_SEPERATOR);
			if((Integer.parseInt(dd_mm_yyyy[DAY])>=1) 
				&& (Integer.parseInt(dd_mm_yyyy[DAY])<=31)
				&& (Integer.parseInt(dd_mm_yyyy[MONTH])>=1)
				&& (Integer.parseInt(dd_mm_yyyy[MONTH])<=12)
				&& (Integer.parseInt(dd_mm_yyyy[YEAR])>=1000)
				&& (Integer.parseInt(dd_mm_yyyy[YEAR])<=10000)
			)
				isValid=true;
		}
		else
			isValid=false;

		return isValid;
	}


}
