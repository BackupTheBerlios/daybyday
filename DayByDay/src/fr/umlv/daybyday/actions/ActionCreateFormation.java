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
public class ActionCreateFormation extends AbstractAction {

	Object [] refs;
	
	public ActionCreateFormation(Object [] refs) {
		super("Formation",Images.getImageIcon("formation"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame mainframe = (MainFrame) refs[0];
		try {

			Windows.createWindow("WindowCreateFormation",new Object[]{
					refs[0],
					MainFrame.myDaybyday.getAllFormations().toArray(),
					MainFrame.myDaybyday.getAllTeachers().toArray(),
					MainFrame.myDaybyday.getAllRooms().toArray()});
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
