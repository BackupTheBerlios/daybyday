/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.Windows;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionCreateSubject extends AbstractAction {

	Object [] refs;
	
	public ActionCreateSubject(Object [] refs) {
		super("Ajouter une matière",Images.getImageIcon("matiere"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			Object [] obj = new Object[2];
			obj[0] = refs[0];
			obj[1] = MainFrame.myDaybyday.getAllTeachers().toArray();
			Windows.createWindow("WindowCreateSubject",obj);
		} catch (RemoteException e1) {

		}
	}
}
