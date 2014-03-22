package com.expensesapp.utilities;

import android.os.Environment;
import java.io.File;

public class Constants {
	public static final String FILE_NAME = "MonthlyExpenses.csv";
	public static final String DATA_DELIMITER = ";";
	public static final File FILE_PATH = Environment
			.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	public static final String UI_SEPARATOR = "-->";
	public static final String INPUT_DATE_SEPERATOR = "-";

}
