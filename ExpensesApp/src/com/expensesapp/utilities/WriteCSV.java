package com.expensesapp.utilities;

import java.io.*;

import com.expensesapp.exceptions.StorageNotAvailableException;

import static com.expensesapp.utilities.Utilities.*;
/**
 * 
 * @author madhumikkili

 */
public class WriteCSV {

	public boolean writeCSV(File filePath, String fileName, String str_ExpDesc,
							String str_ExpCatg, String str_ExpAmt, 
							String str_ExpDate,String delimiter) 
									throws StorageNotAvailableException,
									FileNotFoundException,
									IOException,
									Exception
	{
		boolean hasWritten = false;
		try
		{
			if(isStorageWritable())
			{
				File file = new File(filePath, fileName);
				filePath.mkdirs();
				FileWriter fw = new FileWriter(file, true);
				fw.write("\n" + str_ExpDesc + delimiter + str_ExpCatg + delimiter
						+ str_ExpAmt + delimiter + str_ExpDate + "\n");
				fw.flush();
				fw.close();
				hasWritten=true;
			}
			else
				throw new StorageNotAvailableException();
		}
		catch (FileNotFoundException fnfExc)
		{
			//fnfExc.printStackTrace(); 
			throw fnfExc;
		} 
		catch (IOException ioExc) 
		{
			//ioExc.printStackTrace(); 
			throw ioExc;
		} 
		catch (Exception exc) 
		{
			//exc.printStackTrace(); 
			throw exc;
		}
		finally{}
		return hasWritten;
	}
}