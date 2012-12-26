package com.olam.blsheet.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DateFormatter {
	
	
	
	
	public static String getDateForDataBase(String date){
		try{
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					date = String.valueOf(df.format((dateFormat.parse(date))));
				} catch (Exception e) {
					System.out.println(" Rxception in parsing The String >>> "+e);
					date = null;
				}
				System.out.println(date.toString());
			}
		}catch (ParseException pe) {
			pe.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String args[]) {
		
		String date = "12/25/2012";
		try{
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					date = df.format((dateFormat.parse(date)));
				} catch (Exception e) {
					
					System.out.println(" Exception in parsing The String >>> "+e);
					date = null;
				}
				System.out.println(date.toString());
			}
		}catch (ParseException pe) {
			pe.printStackTrace();
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
