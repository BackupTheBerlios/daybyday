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
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;
import fr.umlv.daybyday.model.Course;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.FormationTreeModel;
import fr.umlv.daybyday.model.Section;
import fr.umlv.daybyday.model.Subject;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionDelete extends AbstractAction {

	Object [] refs;
	
	public ActionDelete(Object [] refs) {
		super("Supprimer",Images.getImageIcon("delete"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		MainFrame mainframe = (MainFrame)refs[0];
		if (refs.length == 3){
			if (refs[2] instanceof JTabbedPane){
				Integer i = (Integer) refs[1];
				mainframe.removeTabbePane(i.intValue());
			}
		}
		if (refs.length == 1){
			Object obj = mainframe.getSelectedObject();
			if (obj instanceof Section){
				//System.out.println(((Section)obj).getNamePath());
				FormationElement elt = ((Section)obj).getFather();
				elt.removeFiliere((Section)obj);
				obj = mainframe.getModelSelectedObject();
				FormationTreeModel tree = (FormationTreeModel)obj;
				tree.reload(elt);
				//tree = new FormationTreeModel((FormationElement)tree.getRoot());	
			}
			if (obj instanceof Subject){
				
				FormationElement elt = ((Subject)obj).getFather();
				//System.out.println(elt.getName() + " " + ((Subject)obj).getNamePath());
				elt.removeMatiere((Subject)obj);
				obj = mainframe.getModelSelectedObject();
				FormationTreeModel tree = (FormationTreeModel)obj;
				tree.reload(elt);
				//tree = new FormationTreeModel((FormationElement)tree.getRoot());	
			}
			if (obj instanceof Course){
			
			}
		}
		if (refs.length == 4){
			Object obj = mainframe.getSelectedObject();
			if (obj instanceof Section){
				Object cours = refs[3];
				if (cours instanceof Course){
					try {
						TimeTableTable df = (TimeTableTable) refs[1];
						MainFrame.myDaybyday.removeCourse(((CourseDto)(((Course)cours).getDto())).getCoursePK());
						
						SectionDto dto = ((Section)obj).getDTO();
						FormationElement father = ((Section)obj).getFather();
						df.changeSource(new Section(dto,father));
						MainFrame.myDaybyday.updateSection(dto);
						final Section section   = (Section)obj;
						section.upDateDto(MainFrame.myDaybyday.getSection(section.getDTO().getSectionPK()));
						
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteDeniedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (StaleUpdateException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("je supprimerai le cous" + cours);
				//tree = new FormationTreeModel((FormationElement)tree.getRoot());	
			}
		}

	}
}
