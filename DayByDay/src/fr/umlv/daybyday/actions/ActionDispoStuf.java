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
import fr.umlv.daybyday.gui.Windows;
import fr.umlv.daybyday.gui.windows.WindowAvailabilityTeacher;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionDispoStuf extends AbstractAction {

	Object [] refs;
	
	public ActionDispoStuf(Object [] refs) {
		super("Matériel",Images.getImageIcon("materiel"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object [] refsplus = new Object [2];
		refsplus[0] = refs [0];
		refsplus[1] = new Integer(WindowAvailabilityTeacher.EQUIP);
		Windows.createWindow("WindowAvailabilityTeacher",refsplus);
		
	}
}
