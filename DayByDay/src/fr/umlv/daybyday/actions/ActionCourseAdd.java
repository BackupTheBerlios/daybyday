/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.AbstractAction;

import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionPK;
import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;
import fr.umlv.daybyday.gui.Windows;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.Section;
import fr.umlv.daybyday.model.Subject;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionCourseAdd extends AbstractAction {
	Object [] refs;
	public ActionCourseAdd(Object [] refs) {
		super("Ajouter",Images.getImageIcon("coursadd"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame mainframe = (MainFrame) refs[0];
		
		TimeTableTable df = (TimeTableTable) refs[1];
		
		Object obj = mainframe.getSelectedObject();
		
		if (obj == null){
			mainframe.showError("Veuiller choisir la formation ou une filière");
			return;
		}
		if (obj instanceof Subject){
			mainframe.showError("Veuiller choisir la formation ou une filière");
			return;
		}
		try {
			if (obj instanceof Section){
				SectionDto dto = ((Section)obj).getDTO();
				FormationElement father = ((Section)obj).getFather();
				while (!(father instanceof Formation)){
					father = ((Section)obj).getFather();
				}
				
					Windows.createWindow("WindowCreateCourse",new Object[]{
					refs[0],
					MainFrame.myDaybyday.getSubjectsOfSection(dto.getSectionPK()).toArray(),
					MainFrame.myDaybyday.getAllTeachers().toArray(),
					MainFrame.myDaybyday.getAllRooms().toArray(),
					MainFrame.myDaybyday.getAllEquipments().toArray(),
					father, 
					(Section)obj,
					df,
					refs[2],
					refs[4],
					refs[5],
					refs[6],
					refs[7]
					});
			}
			if (obj instanceof Formation){

				FormationDto dto = ((FormationDto)((Formation)obj).getDto());
				SectionDto defaultsec = MainFrame.myDaybyday.getSection(new SectionPK("GENERALE",dto.getName(),dto.getFormationYear()));

				
					Windows.createWindow("WindowCreateCourse",new Object[]{
					refs[0],
					MainFrame.myDaybyday.getSubjectsOfSection(defaultsec.getSectionPK()).toArray(),
					MainFrame.myDaybyday.getAllTeachers().toArray(),
					MainFrame.myDaybyday.getAllRooms().toArray(),
					MainFrame.myDaybyday.getAllEquipments().toArray(),
					(Formation)obj, 
					(Formation)obj,
					df,
					refs[2],
					refs[4],
					refs[5],
					refs[6],
					refs[7]
					});
			}
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
