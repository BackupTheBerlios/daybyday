/*
 * Created on 23 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import com.sun.jmx.snmp.Timestamp;

import fr.umlv.daybyday.ejb.timetable.course.CourseDto;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Course {

	private int day;
	private int bghour;
	private int bgminute;
	private int endhour;
	private int endminute;
	public String text; 
	public int colorR;
	public int colorG;
	public int colorB;
	CourseDto dto;
	
	public Course (int day,
	 int bghour,
	 int bgminute,
	 int endhour,
	 int endminute,
	 String text,
	 int colorR,
	 int colorG,
	 int colorB
	){
		
		this.day = day;
		this.bghour =bghour;
		this.bgminute = bgminute;
		this.endhour = endhour;
		this.endminute =endminute;
		this.text = text;
		this.colorR = colorR;
		this.colorG = colorG;
		this.colorB = colorB;
		
	}

	/**
	 * @param dto
	 */
	public Course(CourseDto dto) {
		java.sql.Timestamp bgdate = dto.getStartDate();
		java.sql.Timestamp enddate = dto.getEndDate();
		this.dto = dto;
		this.day = bgdate.getDay();
		this.bghour = bgdate.getHours();
		this.bgminute =  bgdate.getMinutes();
		this.endhour = enddate.getHours();
		this.endminute = enddate.getMinutes();
		this.text = dto.getSubjectName();
		Integer i = dto.getColor();
		if (i != null){
		int color =i.intValue();
		char rcolor = (char) (color >> 16);
		char gcolor = (char) (color >> 8);
		char bcolor = (char) (color);
		this.colorR = rcolor % 256;
		this.colorG = gcolor % 256;
		this.colorB = bcolor % 256;
		}
		else {
			this.colorR = 225;
			this.colorG = 183;
			this.colorB = 107;
		}
			
	}
	
	public int getBgHour(){
		return bghour - Grid.gridBgHour;
	}

	public int getBgMin(){
		if ((bgminute) / (60/Grid.gridSlice) == 0)
			if (bgminute != 0) return (bgminute+1) / (60/Grid.gridSlice);
		return (bgminute) / (60/Grid.gridSlice);
	}
	public int getEndHour(){
		return endhour - Grid.gridBgHour;
	}
	public int getEndMin(){
		if ((endminute) / (60/Grid.gridSlice) == 0)
			if (endminute != 0) return (endminute+1) / (60/Grid.gridSlice);
		return (endminute) / (60/Grid.gridSlice);
	}
	public int getDay(){
		return day;
	}
	public Object getDto(){
		return dto;
	}
}
