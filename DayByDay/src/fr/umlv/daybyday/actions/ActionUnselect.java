/*
 * Created on 24 f�vr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.umlv.daybyday.gui.Images;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionUnselect extends AbstractAction {

	Object [] refs;
	
	public ActionUnselect(Object [] refs) {
		super("D�selectionner",Images.getImageIcon("unselect"));
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
	}
}
