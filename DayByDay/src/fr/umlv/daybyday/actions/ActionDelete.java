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

import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionPK;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectPK;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;
import fr.umlv.daybyday.model.Course;
import fr.umlv.daybyday.model.Formation;
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
		System.out.println(refs.length);
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
				SectionDto dto = (SectionDto) ((Section)obj).getDto();
				try {
					if (elt.getName().equals(dto.getFormationName()) ){
							MainFrame.myDaybyday.removeSection(new SectionPK(dto.getName(),dto.getFormationName(),dto.getFormationYear()));
					}
					else	{
						MainFrame.myDaybyday.removeSection(new SectionPK(dto.getName(),dto.getFormationName(),dto.getFormationYear()));
					}					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteDeniedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				elt.removeFiliere((Section)obj);
				obj = mainframe.getModelSelectedObject();
				FormationTreeModel tree = (FormationTreeModel)obj;
				tree.reload(elt);
				//tree = new FormationTreeModel((FormationElement)tree.getRoot());	
			}
			if (obj instanceof Subject){
				
				FormationElement elt = ((Subject)obj).getFather();
				SubjectDto dto = (SubjectDto) ((Subject)obj).getDto();

				try {
					if (elt.getName().equals(dto.getFormationName()) ){
							MainFrame.myDaybyday.removeSubject(new SubjectPK(dto.getName(),"GENERALE",dto.getFormationName(),dto.getFormationYear()));
					
					}
					else	{
							MainFrame.myDaybyday.removeSubject(new SubjectPK(dto.getName(),elt.getName(),dto.getFormationName(),dto.getFormationYear()));
					}					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteDeniedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				//System.out.println(elt.getName() + " " + ((Subject)obj).getNamePath());
				elt.removeMatiere((Subject)obj);
				obj = mainframe.getModelSelectedObject();
				FormationTreeModel tree = (FormationTreeModel)obj;
				tree.reload(elt);
				//tree = new FormationTreeModel((FormationElement)tree.getRoot());	
			}
		}
		if (refs.length == 8){
			Object obj = mainframe.getSelectedObject();
//System.out.println(obj);
			if (obj instanceof Section){
				Object cours = refs[3];
				if (cours instanceof Course){
					try {
//System.out.println("Suppression de cours");
						TimeTableTable df = (TimeTableTable) refs[1];
						MainFrame.myDaybyday.removeCourse(((CourseDto)(((Course)cours).getDto())).getCoursePK());
						
						SectionDto dto = ((Section)obj).getDTO();
						FormationElement father = ((Section)obj).getFather();
//System.out.println(father);
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
				
				//tree = new FormationTreeModel((FormationElement)tree.getRoot());	
			}
			if (obj instanceof Formation){
				Object cours = refs[3];
				if (cours instanceof Course){
					try {
//System.out.println("Suppression de cours" + " formation");
						Formation form = (Formation) obj;
						TimeTableTable df = (TimeTableTable) refs[1];
						MainFrame.myDaybyday.removeCourse(((CourseDto)(((Course)cours).getDto())).getCoursePK());
						
						SectionDto dto =MainFrame.myDaybyday.getSection(new SectionPK("GENERALE", form.getName(), form.getYear()));
						
						MainFrame.myDaybyday.updateFormation((FormationDto) form.getDto());
						df.changeSource( form);
						//final Section section   = (Section)obj;
						//section.upDateDto(MainFrame.myDaybyday.getSection(section.getDTO().getSectionPK()));
						
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
				
				//tree = new FormationTreeModel((FormationElement)tree.getRoot());	
			}
		}

	}
}
