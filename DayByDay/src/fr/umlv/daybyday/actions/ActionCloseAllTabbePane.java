/*
 * Created on 24 f�vr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTabbedPane;

import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionCloseAllTabbePane extends AbstractAction {

	Object [] refs;
	
	public ActionCloseAllTabbePane(Object [] refs) {
		super("Fermer tous",Images.getImageIcon("deleteall"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame mainframe = (MainFrame)refs[0];
		if (refs.length == 3){
			if (refs[2] instanceof JTabbedPane){
				mainframe.removeAllTabbePane();
			}
		}
		

	}
}
