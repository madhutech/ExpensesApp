package com.expensesapp.vo;

/**
 * Value Object for Expense Object
 * Exp_Desc,Exp_Categ,Exp_Amnt,Exp_Date;
 * 
 * @author madhumikkili
 *
 */
public class Expense {
	
	private String str_Exp_Desc;
	private String str_Exp_Categ;
	private Integer int_Exp_Amnt;
	private String str_Exp_Date;

	public String getExp_Desc() {
		return str_Exp_Desc;
	}
	public void setExp_Desc(String exp_Desc) {
		if(exp_Desc!=null && exp_Desc.trim().length()>0)
		str_Exp_Desc = exp_Desc.trim();
	}
	public String getExp_Categ() {
		return str_Exp_Categ;
	}
	public void setExp_Categ(String exp_Categ) {
		if(exp_Categ!=null && exp_Categ.trim().length()>0)
		str_Exp_Categ = exp_Categ.trim();
	}
	public Integer getExp_Amnt() {
		return int_Exp_Amnt;
	}
	public void setExp_Amnt(Integer exp_Amnt) {
		if(exp_Amnt>0)
			int_Exp_Amnt = exp_Amnt;
		else
			int_Exp_Amnt=0;
	}
	public String getExp_Date() {
		return str_Exp_Date;
	}
	public void setExp_Date(String exp_Date) {
		if(exp_Date!=null && exp_Date.trim().length()>0)
		str_Exp_Date = exp_Date.trim();
	}
	
	/**
	 * In case we wanted to see if an expense entry is same as the another, 
	 * we override equals method. Two expenses will be treated same/equals 
	 * if all 4 parameters are the same.
	 */
	public boolean equals(Object o)
	{
		boolean equal=false;
		if((o instanceof Expense))
		{
			Expense exp = (Expense)o;
			if((exp.int_Exp_Amnt.equals(this.int_Exp_Amnt))
				&& (exp.str_Exp_Date.equals(this.str_Exp_Date))
				&& (exp.str_Exp_Categ.equals(this.str_Exp_Categ))
				&& (exp.str_Exp_Desc.equals(this.str_Exp_Desc)))

				equal=true;
			else
				equal=false;
		}
		return equal;
	}		
}
