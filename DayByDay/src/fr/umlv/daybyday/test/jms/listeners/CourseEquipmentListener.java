package fr.umlv.daybyday.test.jms.listeners;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;

public class CourseEquipmentListener implements MessageListener {

    EquipmentDto equipDto;
    Daybyday daybyday;
    TimeTableTable equipView;

    public CourseEquipmentListener(EquipmentDto equipDto, TimeTableTable equipView,  Daybyday daybyday) {
        this.equipDto = equipDto;
        this.daybyday = daybyday;
        this.equipView = equipView;
    }
    public void onMessage(Message message) {
        ObjectMessage objecMessage = (ObjectMessage)message;
        try {
        	CourseDto courseDto = (CourseDto) objecMessage.getObject();
        	
			ArrayList rooms =null;
			try {
				rooms = MainFrame.myDaybyday.getEquipmentsOfCourse(courseDto.getCourseId());
				for (int i=0;i<rooms.size();i++)
				{
					EquipmentDto equipment = (EquipmentDto)rooms.get(i);
					
					if (this.equipDto.equals(equipment)){
						equipView.refresh();
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
