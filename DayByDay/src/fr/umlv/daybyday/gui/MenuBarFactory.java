/*
 * Created on 25 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.umlv.daybyday.actions.InstancesActions;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MenuBarFactory {
	public static JMenu CreateMultiMenu (String name, String nameicone,ArrayList subname, Object [] refs){
		JMenu		submenu = new JMenu(name);
		JMenuItem subitem;
		
		submenu.setIcon(Images.getImageIcon(nameicone));
		
		for (int i = 0; i  < subname.size() ; ++i){
			subitem =  new JMenuItem(InstancesActions.getAction((String)subname.get(i),refs));	
			subitem.setAccelerator(KeyStroke.getKeyStroke(MnemonicMap.getMnemonic((String)subname.get(i)), MnemonicMap.getMask((String)subname.get(i))));
			submenu.add(subitem);
		}
		
		return submenu;
	}
	
	public static JMenuItem CreateMenuItem (String name, Object [] refs){
		JMenuItem subitem = new JMenuItem(InstancesActions.getAction(name,refs));
		subitem.setAccelerator(KeyStroke.getKeyStroke(MnemonicMap.getMnemonic(name), MnemonicMap.getMask(name)));
		return subitem;
	}
	
}
