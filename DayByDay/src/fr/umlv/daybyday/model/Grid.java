/*
 * Created on 1 mars 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Grid {
	public static int gridBgHour = 8;
	public static int gridEndHour = 19;
	public static int gridSlice = 4;
	public static int gridNbDays = 7;
	public static int gridbgDay = 7;
	public static int gridbgMonth = 1;
	public static int gridendDay = 11;
	public static int gridendMonth = 1;
	public static GregorianCalendar calendar = new GregorianCalendar(Locale.FRENCH);
	public static int sens = 0;

	public static void  changeWeek (int inc){
		int tmp = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.set(Calendar.WEEK_OF_YEAR, tmp + inc);
	}
	public static void initCalendar(){ 
		//System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - (calendar.get(Calendar.DAY_OF_WEEK) - 2) );
		//System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
	}
}
