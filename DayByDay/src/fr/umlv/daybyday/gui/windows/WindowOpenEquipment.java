/*
 * Created on 1 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author eemond
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WindowOpenEquipment extends WindowAbstract{


	public static void createWindow(JFrame frame, Object[] obj){
		
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		initWindow(frame,"Ouverture d'un matèriel", 400, 350);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(5,20,5,20);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.CENTER;
		
		JLabel importLabel = new JLabel("Importer: ");
		gridbag.setConstraints(importLabel, c);
		contentPane.add(importLabel);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JComboBox importCombo =  new JComboBox(obj);
		gridbag.setConstraints(importCombo, c);
		contentPane.add(importCombo);

		addButtonValidation(contentPane, c, gridbag );
	}

	
}
