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
import fr.umlv.daybyday.model.Course;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionCut extends AbstractAction {

	Object [] refs;
	
	public ActionCut(Object [] refs) {
		super("Couper",Images.getImageIcon("cut"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		//MainFrame mainframe = (MainFrame) refs[0];
		//System.out.println(((Course) MainFrame.getSelectedCourse()).getRepresentation());
		ActionPaste.coursref = (Course) MainFrame.getSelectedCourse();
		ActionPaste.cut = true;
		
	}
}
