package com.expensesapp.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensesapp.R;
import com.expensesapp.utilities.Constants;
import com.expensesapp.utilities.ReadCSV;

public class ViewByCatgActivity extends Activity{
	private Map<String,Double> aggrCost = new TreeMap<String,Double>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		TextView txtView = new TextView(this); txtView.setTextSize(100);
		txtView.setText(msg);
		txtView.setText("\n");
		try{
		ArrayList<String> rawdata = new ReadCSV().readCSV(Constants.FILE_PATH,
														Constants.FILE_NAME);
		ArrayList<String> newlist = new ArrayList<String>();
		for(String s: rawdata)
		{
			String catg = s.split(Constants.DATA_DELIMITER)[1];
			Double cost = Double.valueOf(s.split(Constants.DATA_DELIMITER)[2]);
			if(aggrCost.containsKey(catg))
				aggrCost.put(catg,(cost+aggrCost.get(catg)));
			else
				aggrCost.put(catg,cost);
		}
		
		for(Map.Entry<String, Double> entry : aggrCost.entrySet() )
		{
			newlist.add("\n"+entry.getKey()+Constants.UI_SEPARATOR+roundcost(entry.getValue())+"\n");
		}
		setContentView(txtView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
													R.layout.activity_viewbycatg,
													newlist);
		ListView list = (ListView)findViewById(R.id.listView_catgView);
		list.setAdapter(adapter);
		setContentView(R.layout.activity_enterexp);
	}
		catch(FileNotFoundException fnfExc)
		{
			//Show "File Not Found" error splash screen.
			Toast.makeText(getApplicationContext(),
							"Data file not found!",
							Toast.LENGTH_LONG).show();

		}
		catch(IOException ioExc)
		{
			//Show "File Not Found" error splash screen.
			Toast.makeText(getApplicationContext(),
							"Some IO Exception has occured",
							Toast.LENGTH_LONG).show();
			
		}
		catch (Exception exc)
		{
			//Show "File Not Found" error splash screen.
			Toast.makeText(getApplicationContext(),
							"Exception occured!",
							Toast.LENGTH_LONG).show();
		}
		finally{}
		
		startActivity(new Intent(this,MainActivity.class));


	}	

/*	private Map<String,Double> readCSV()
	{
		Map<String,Double> aggrCost = new TreeMap<String,Double>();
		
		if(!isStorageWritable())
			//Show "storage NA" error splash screen.
			Toast.makeText(getApplicationContext(),"Storage Not Available, try later",Toast.LENGTH_SHORT).show();
		else{
		//Read below CSV or regex delimited files
		File filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		String fileName = "MonthlyExpenses.csv";
		//String filePath = "C:\\Madhu\\Dropbox\\MyFinances";
		String delimiter = ";";//does not always have to be a comma as in this case.

		//now let's try reading the file
		try{
		File file = new File(filepath,fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String inStr;
		System.out.println("Contents of file: "+fileName+ " at "+filepath+" are:");
		int linecnt=0;
		while((inStr=br.readLine())!=null)
		{
			System.out.println(inStr);
			if(linecnt>0)//skip header
			{
			String catg = inStr.split(delimiter)[1];
			Double cost = new Double(inStr.split(delimiter)[2]);
			if(aggrCost.containsKey(catg))
				aggrCost.put(catg,(cost+aggrCost.get(catg)));
			else
				aggrCost.put(catg,cost);
			}
			linecnt++;
		}
		br.close();br=null;fr.close();fr=null;file=null;
		}
		catch(FileNotFoundException fnfExc){fnfExc.printStackTrace();}
		catch(IOException ioExc){ioExc.printStackTrace();}
		catch(Exception exc){exc.printStackTrace();}
		finally
		{
			System.out.println(aggrCost);
		}		
		}
		
		return aggrCost;
	}
*/	
	
	/* Checks if external storage is available for read and write */
/*	private static boolean isStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state))
			return true;
		else
			return false;
	}
*/
	
	//utility to format the cost (double)
	private static String roundcost(double cost){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);nf.setMinimumFractionDigits(2);
		return nf.format(cost);
	}

}
