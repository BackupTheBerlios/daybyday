package fr.umlv.daybyday.test.jms.listeners;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;

public class CourseRoomListener implements MessageListener {

	RoomDto roomDto;
    Daybyday daybyday;
    TimeTableTable roomView;

    public CourseRoomListener(RoomDto roomDto, TimeTableTable table,  Daybyday daybyday) {
        this.roomDto = roomDto;
        this.daybyday = daybyday;
        this.roomView = table;
    }
    
    public void onMessage(Message message) {
        ObjectMessage objecMessage = (ObjectMessage)message;
        try {
        	CourseDto courseDto = (CourseDto) objecMessage.getObject();
        	
			ArrayList rooms =null;
			try {
				rooms = MainFrame.myDaybyday.getRoomsOfCourse(courseDto.getCourseId());
				
				for (int i=0;i<rooms.size();i++)
				{
					RoomDto dto = (RoomDto) rooms.get(i);
					if (this.roomDto.equals(dto)){
						roomView.refresh();
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

    }
}
