/*
 * Created on 1 mars 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui.windows;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ComboBoxFormationElementRenderer implements ListCellRenderer{

	public ComboBoxFormationElementRenderer(){};
	
	public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
		// TODO Auto-generated method stub
		
		if (arg1 instanceof FormationDto){
			FormationDto dto = (FormationDto)arg1;
			return new JLabel(dto.getName() + " " + dto.getFormationYear());
		}
		if (arg1 instanceof TeacherDto){
			TeacherDto dto = (TeacherDto)arg1;
			return new JLabel(dto.getName());
		}
		if (arg1 instanceof SubjectDto){
			SubjectDto dto = (SubjectDto)arg1;
			return new JLabel(dto.getName());
		}
		if (arg1 instanceof RoomDto){
			RoomDto dto = (RoomDto)arg1;
			return new JLabel(dto.getName() + " " + dto.getArea() + " " + dto.getBuilding());
		}
		if (arg1 instanceof EquipmentDto){
			EquipmentDto dto = (EquipmentDto)arg1;
			return new JLabel(dto.getName());
		}
		return new JLabel();
	}
	
}
