package com.expensesapp.exceptions;

public class StorageNotAvailableException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString()
	{
		return "Storage not available to read/write. Check SD card is available";
	}

}
