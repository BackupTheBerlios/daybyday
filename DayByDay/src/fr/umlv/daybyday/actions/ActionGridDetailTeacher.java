/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.CourseDetail;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionGridDetailTeacher extends AbstractAction {

	Object [] refs;
	
	public ActionGridDetailTeacher(Object [] refs) {
		super("Enseignant",Images.getImageIcon("enseignant"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		MainFrame mainframe = (MainFrame) refs[0];
		
		CourseDetail.formId = true;
		CourseDetail.formInfo = false;
		
		CourseDetail.teacherName = false;
		CourseDetail.teacherFirstname = false;
		CourseDetail.teacherOffice = false;
		
		CourseDetail.equipmentName = false;
		CourseDetail.equipmentDesc = false;
		
		CourseDetail.roomName = true;
		CourseDetail.roomInfo = false;
		
		CourseDetail.subjectName = true;
		CourseDetail.subjectType = true;
		CourseDetail.subjectGroupe = true;
		
		CourseDetail.coursColor = true;
		CourseDetail.coursPeriode = true;
		CourseDetail.coursFrequence = false;
		CourseDetail.coursHour = true;
		
		mainframe.refreshAllTimeTable();
	}
}
