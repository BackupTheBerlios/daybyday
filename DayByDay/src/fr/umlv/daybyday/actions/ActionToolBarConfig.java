/*
 * Created on 24 f�vr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.Windows;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionToolBarConfig extends AbstractAction {

	Object [] refs;
	
	public ActionToolBarConfig(Object [] refs) {
		super("Configurer",Images.getImageIcon("config2"));
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		ArrayList tmp = new ArrayList(); tmp.add("0");
		Object [] obj = new Object[] {tmp,tmp,tmp,tmp };
		Windows.createWindow("WindowTool",obj);
	}
}
