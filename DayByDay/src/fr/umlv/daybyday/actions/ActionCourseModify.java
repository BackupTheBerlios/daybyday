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
import fr.umlv.daybyday.ejb.timetable.section.SectionBusinessPK;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
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
public class ActionCourseModify extends AbstractAction {

	Object [] refs;
	
	public ActionCourseModify(Object [] refs) {
		super("Modifier",Images.getImageIcon("coursmodify"));
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
		try{
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
				
					Windows.createWindow("WindowModifyCourse",new Object[]{
					refs[0],
					MainFrame.myDaybyday.getSubjectsOfSection(dto.getSectionId()).toArray(),
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
					refs[7],
					refs[8]
					});
			}
			if (obj instanceof Formation){

				FormationDto dto = ((FormationDto)((Formation)obj).getDto());
				SectionDto defaultsec = MainFrame.myDaybyday.getSection(new SectionBusinessPK("GENERALE",dto.getFormationId()));

				
					Windows.createWindow("WindowModifyCourse",new Object[]{
					refs[0],
					MainFrame.myDaybyday.getSubjectsOfSection(defaultsec.getSectionId()).toArray(),
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
					refs[7],
					refs[8]
					});
			}
			
		} catch (RemoteException e1) {
			mainframe.showError(e1.getMessage());
		} catch (EntityNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}catch(ArrayIndexOutOfBoundsException e2){
			mainframe.showError("Emploi du temps non spécifié pour l'ajout" + e2);
		}
	}
	}

