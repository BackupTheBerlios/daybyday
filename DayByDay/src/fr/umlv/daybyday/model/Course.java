/*
 * Created on 23 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.MainFrame;

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
		SubjectDto sub=null;
		try {
			sub = MainFrame.myDaybyday.getSubject(dto.getSubjectId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.text = sub.getName();
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
			System.out.println("defaut color");
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
	
	public String getRepresentation(){
		String tag = "";
		
		if (CourseDetail.subjectType)
			tag += dto.getSubjectType();
		
			if (CourseDetail.subjectName)
			{
				SubjectDto sub=null;
				try {
					sub = MainFrame.myDaybyday.getSubject(dto.getSubjectId());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tag += " " +sub.getName();
			}
			if(CourseDetail.subjectGroupe )
				tag += " " +dto.getGroupeName();
		
		
		if(CourseDetail.formId ){
			
			SubjectDto sub=null;
			try {
				sub = MainFrame.myDaybyday.getSubject(dto.getSubjectId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SectionDto sec = null;
			FormationDto ff = null;
			try {
				sec = MainFrame.myDaybyday.getSection(sub.getSectionId());
				ff  = MainFrame.myDaybyday.getFormation(sec.getFormationId());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EntityNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			tag += "\n" +ff.getName();
			if(CourseDetail.formInfo )
				tag += " " +dto.getDescription();
		}
		
		if(CourseDetail.teacherName ){
			ArrayList teachers =null;
			try {
				
				teachers = MainFrame.myDaybyday.getTeachersOfCourse(dto.getCourseId());
			
			for (int i=0;i<teachers.size();i++)
			{
				TeacherDto teacher = (TeacherDto)teachers.get(i);
			if(CourseDetail.teacherFirstname )
			tag += "\n" +teacher.getFirstname().charAt(0)+"." ;
		
			tag += " " +teacher.getName() ;
		
			if(CourseDetail.teacherOffice );
			}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(CourseDetail.equipmentName ){
			ArrayList rooms =null;
			try {
				rooms = MainFrame.myDaybyday.getEquipmentsOfCourse(dto.getCourseId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i=0;i<rooms.size();i++)
			{
				EquipmentDto equipment = (EquipmentDto)rooms.get(i);
				tag += "\n" +equipment.getName();
				if(CourseDetail.equipmentDesc );
			}
		}

		if(CourseDetail.roomName ){
			ArrayList rooms =null;
			try {
				rooms = MainFrame.myDaybyday.getRoomsOfCourse(dto.getCourseId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i=0;i<rooms.size();i++)
			{
				RoomDto room = (RoomDto)rooms.get(i);
				tag += "\n" +room.getName();
				if(CourseDetail.roomInfo );
			}
		}
		
		if(CourseDetail.coursPeriode ){
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis(dto.getStartDate().getTime());
			tag += 	"\n" +  cal.get(Calendar.DAY_OF_MONTH)+"/"+  (cal.get(Calendar.MONTH)+1);
			cal.setTimeInMillis(dto.getEndDate().getTime());
			tag +=  "-" +  cal.get(Calendar.DAY_OF_MONTH)+"/"+  (cal.get(Calendar.MONTH) + 1);

		}
		
		if(CourseDetail.coursFrequence ){
			//tag += "\nOublie de vico et momo";
		}
		if(CourseDetail.coursHour ){
			tag += "\n" + ((bghour < 10 ) ? "0" + bghour: ""+bghour)  +"h" 
			+ ((bgminute < 10 ) ? "0" + bgminute: ""+bgminute)  + " - " + 
			((endhour < 10 ) ? "0" + endhour: ""+endhour) +"h" + 
			((endminute < 10 ) ? "0" + endminute: ""+endminute);
		}

		return tag;
		
	}
}
