/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.Course;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionCourseCancel extends AbstractAction {

	Object [] refs;
	
	public ActionCourseCancel(Object [] refs) {
		super("Annuler",Images.getImageIcon("courscancel"));
		this.refs = refs;
	}

	public void setRefs(Object [] refs) {
		this.refs = refs;
	}
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Course coursRef = (Course) refs [8];
		CourseDto coursDtoRef = (CourseDto)coursRef.getDto();
		coursDtoRef.setDescription("ANNULE - " + coursDtoRef.getDescription());
		try {
			MainFrame.myDaybyday.updateCourse(coursDtoRef);
		} catch (Exception e1) {
			//
		} 
	}
}
