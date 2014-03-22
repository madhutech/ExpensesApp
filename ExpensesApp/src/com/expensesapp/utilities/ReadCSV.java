package com.expensesapp.utilities;

import java.io.*;
import java.util.ArrayList;
import com.expensesapp.exceptions.StorageNotAvailableException;

import static com.expensesapp.utilities.Utilities.*;
/**
 * Generic class to read the delimiter separated data file
 * ,although it's named CSV(comma separated!)
 * @author madhumikkili
 *
 */
public class ReadCSV {
	/**
	 * This file skips (doesn't read) the header!
	 * @param filePath
	 * @param fileName
	 * @param delimiter
	 * @return
	 */
	public ArrayList<String> readCSV(File filePath,String fileName)
									throws StorageNotAvailableException,
											FileNotFoundException, 
											IOException, 
											Exception
	{
		ArrayList<String> data = new ArrayList<String>();
		//now let's try reading the file
		try
		{
			if(isStorageWritable())//checking for writable. Obv, it'll be readable if it's writable
			{
				File file = new File(filePath,fileName);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String inStr;
				System.out.println("Contents of file: "+fileName+ " at "+filePath+" are:");
				int linecnt=0;
				while((inStr=br.readLine())!=null)
				{
					//System.out.println(inStr);
					if(linecnt>0)//skip header
						data.add(inStr);
					linecnt++;
				}
				br.close();br=null;fr.close();fr=null;file=null;
			}
			else
				throw new StorageNotAvailableException();
		}
		catch(FileNotFoundException fnfExc)
		{
			fnfExc.printStackTrace();
			throw fnfExc;
		}
		catch(IOException ioExc)
		{
			ioExc.printStackTrace();
			throw ioExc;
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			throw exc;
		}
		finally
		{
			System.out.print(data);
		}
		return data;
	}
	
}
