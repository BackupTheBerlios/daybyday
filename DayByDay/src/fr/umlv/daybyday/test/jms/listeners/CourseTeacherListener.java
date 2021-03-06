package fr.umlv.daybyday.test.jms.listeners;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;

public class CourseTeacherListener implements MessageListener {

    TeacherDto teacherDto;
    Daybyday daybyday;
    TimeTableTable teacherView;

    public CourseTeacherListener(TeacherDto teacherDto, TimeTableTable teacherView,  Daybyday daybyday) {
        this.teacherDto = teacherDto;
        this.daybyday = daybyday;
        this.teacherView = teacherView;
    }
    public void onMessage(Message message) {
        ObjectMessage objecMessage = (ObjectMessage)message;
        try {
        	
        	CourseDto courseDto = (CourseDto) objecMessage.getObject();

               
            ArrayList teachers = new ArrayList();
			try {
				teachers = MainFrame.myDaybyday.getTeachersOfCourse(courseDto.getCourseId());
				
				for (int i=0;i< teachers.size();++i)
				{
					TeacherDto teacher = (TeacherDto) teachers.get(i);
					if (this.teacherDto.equals(teacher)){
						teacherView.refresh();
					}
				}
			}
			catch (RemoteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}	
			

            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
