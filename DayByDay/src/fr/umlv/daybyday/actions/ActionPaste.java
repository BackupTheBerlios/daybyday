/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import javax.swing.AbstractAction;

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;

import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.util.exception.ConstraintException;
import fr.umlv.daybyday.ejb.util.exception.CourseConfusionException;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.ejb.util.exception.OperationFailedException;
import fr.umlv.daybyday.ejb.util.exception.ResourceUnavailableException;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.TimeslotException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;
import fr.umlv.daybyday.gui.windows.WindowCreateCourse;
import fr.umlv.daybyday.model.Course;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.Grid;
import fr.umlv.daybyday.model.Section;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionPaste extends AbstractAction {

	public static boolean cut = false;
	public static Course  coursref;
	
	Object [] refs;
	
	public ActionPaste(Object [] refs) {
		super("Coller",Images.getImageIcon("paste"));
		this.refs = refs;
	}

	public void setRefs(Object [] refs) {
		this.refs = refs;
	}
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame mainframe = (MainFrame) refs[0];
		
		TimeTableTable df = (TimeTableTable) refs[1];
		FormationElement section = null;
		Object obj = mainframe.getSelectedObject();
		
		if (obj == null){
			mainframe.showError("Veuiller choisir la formation ou une filière");
			return;
		}
		if (obj instanceof Section){
			section = (Section)obj;
		}
		else if (obj instanceof Formation){
			section = (Formation)obj;
		}
		else {
			mainframe.showError("Veuiller choisir la formation ou une filière");
			return;
		}
		
	    String sectionname ;
	    String formationname ;
	    String formationyear ;

	    Integer bgHour   = (Integer) refs[2];
		Integer bgMinute  = (Integer) refs[4];
		Integer endHour   = (Integer) refs[6];
		Integer endMinute  = (Integer) refs[7];
		int index  = ((Integer) refs[5]).intValue();
		
		CourseDto olddto =  (CourseDto)coursref.getDto();
		
		int bghour = bgHour.intValue();
		int bgmin = bgMinute.intValue();
		int endhour = bghour + (olddto.getEndDate().getHours() - olddto.getStartDate().getHours());
		int endmin = bgmin + (olddto.getEndDate().getMinutes() - olddto.getStartDate().getMinutes());
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(Grid.calendar.getTimeInMillis());
        cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR) + (index -1));

		int bgday =  cal.get(Calendar.DAY_OF_MONTH);
		int bgmonth = (cal.get(Calendar.MONTH)+1);
		int endday = cal.get(Calendar.DAY_OF_MONTH);
		int endmonth = (cal.get(Calendar.MONTH)+1);
		
	    Timestamp startDate = WindowCreateCourse.toTimeStamp(2005, bgmonth-1,bgday,bghour,bgmin,00);
        Timestamp endDate = WindowCreateCourse.toTimeStamp(2005, endmonth-1,endday,endhour,endmin,00);
        
        
        if (section instanceof Formation){
        		sectionname = "GENERALE";
        		formationname = ((FormationDto) section.getDto()).getName();
        		formationyear = ((FormationDto) section.getDto()).getFormationYear();
        }
        else{
        		sectionname = section.getName();
        		FormationDto ff=null;
				try {
					ff = MainFrame.myDaybyday.getFormation(((SectionDto) section.getDto()).getFormationId());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (EntityNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				formationname = ff.getName();
        		formationyear = ff.getFormationYear();
        }
        		
        //String typesubject = "cours";
        
        
		CourseDto newdto = new CourseDto(
				olddto.getSubjectId(),
				olddto.getSubjectType(),
				olddto.getGroupeName(),
				startDate,
				endDate,
				olddto.getColor(),
				olddto.getDescription()
						
				);
		
		
			try {
				String course_id = MainFrame.myDaybyday.createCourse(newdto);
				newdto.setCourseId(course_id);
			} catch (RemoteException e2) {
				mainframe.showError(e2.getMessage());
				return;
			} catch (ResourceUnavailableException e2) {
				mainframe.showError(e2.getMessage());
				return;
			} catch (ConstraintException e2) {
				mainframe.showError(e2.getMessage());
				return;
			} catch (CourseConfusionException e2) {
				mainframe.showError(e2.getMessage());
				return;
			} catch (TimeslotException e2) {
				mainframe.showError(e2.getMessage());
				return;
			} catch (CreationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			//section.getDTO().setVersion(new Long(section.getDTO().getVersion().longValue()+1));
			
			
			try {
				ArrayList teachers = MainFrame.myDaybyday.getTeachersOfCourse(olddto.getCourseId());
				for (int i = 0;i<teachers.size();i++)
				{
					TeacherDto teacher = (TeacherDto)teachers.get(i);
					
					MainFrame.myDaybyday.addTeacherToCourse(teacher.getTeacherId(),newdto.getCourseId());
					MainFrame.myDaybyday.updateTeacher(teacher);
				}
				
				ArrayList rooms = MainFrame.myDaybyday.getRoomsOfCourse(olddto.getCourseId());
				for (int i = 0;i<teachers.size();i++)
				{
					RoomDto room = (RoomDto)rooms.get(i);
					MainFrame.myDaybyday.addRoomToCourse(room.getRoomId(),newdto.getCourseId());
					MainFrame.myDaybyday.updateRoom(room);
					
				}
				
				ArrayList equipments = MainFrame.myDaybyday.getEquipmentsOfCourse(olddto.getCourseId());
				for (int i = 0;i<teachers.size();i++)
				{
					EquipmentDto equipment = (EquipmentDto)equipments.get(i);
					MainFrame.myDaybyday.addEquipmentToCourse(equipment.getEquipmentId(),newdto.getCourseId());
					MainFrame.myDaybyday.updateEquipment(equipment);
					
				}
				
				
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (StaleUpdateException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (WriteDeniedException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (EntityNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			catch (OperationFailedException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			if (cut){
				try {
					
					
					ArrayList teachers = MainFrame.myDaybyday.getTeachersOfCourse(olddto.getCourseId());
					for (int i = 0;i<teachers.size();i++)
					{
						TeacherDto teacher = (TeacherDto)teachers.get(i);
						
						MainFrame.myDaybyday.removeTeacherFromCourse(teacher.getTeacherId(),olddto.getCourseId());
						MainFrame.myDaybyday.updateTeacher(teacher);
					}
					
					ArrayList rooms = MainFrame.myDaybyday.getRoomsOfCourse(olddto.getCourseId());
					for (int i = 0;i<teachers.size();i++)
					{
						RoomDto room = (RoomDto)rooms.get(i);
						MainFrame.myDaybyday.removeRoomFromCourse(room.getRoomId(),olddto.getCourseId());
						MainFrame.myDaybyday.updateRoom(room);
						
					}
					
					ArrayList equipments = MainFrame.myDaybyday.getEquipmentsOfCourse(olddto.getCourseId());
					for (int i = 0;i<teachers.size();i++)
					{
						EquipmentDto equipment = (EquipmentDto)equipments.get(i);
						MainFrame.myDaybyday.removeEquipmentFromCourse(equipment.getEquipmentId(),olddto.getCourseId());
						MainFrame.myDaybyday.updateEquipment(equipment);
						
					}
					MainFrame.myDaybyday.removeCourse(olddto.getCourseId());
					
				
				} catch (RemoteException e1) {
					mainframe.showError(e1.getMessage());
					return;
				} catch (WriteDeniedException e1) {
					mainframe.showError(e1.getMessage());
					return;
				} catch (EntityNotFoundException e1) {
					// TODO Auto-generated catch block
					mainframe.showError(e1.getMessage());
					e1.printStackTrace();
				} catch (OperationFailedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (StaleUpdateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cut = false;
			} 
			
			if (section instanceof Section){
				try {
					MainFrame.myDaybyday.updateSection(((Section)section).getDTO());
				
			
				((Section)section).upDateDto(MainFrame.myDaybyday.getSection(((Section)section).getDTO().getSectionId()));
				} catch (RemoteException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (StaleUpdateException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (WriteDeniedException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (EntityNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				df.changeSource(new Section(((Section)section).getDTO(),section.getFather()));
			}
			if (section instanceof Formation){
				try{
					MainFrame.myDaybyday.updateFormation((FormationDto) ((Formation)section).getDto());
				}catch (RemoteException e10) {

				} catch (StaleUpdateException e10) {
					// TODO Auto-generated catch block
					e10.printStackTrace();
				} catch (WriteDeniedException e10) {
					// TODO Auto-generated catch block
					e10.printStackTrace();
				} catch (EntityNotFoundException e10) {
					// TODO Auto-generated catch block
					e10.printStackTrace();
				} 
				((Formation)section).getCourseList();
				((Formation)section).upDateDto(null);
			//	framefinal.dispose();
				df.changeSource((Formation)section);
			}
			
			
		
	}
}
