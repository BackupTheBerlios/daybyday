
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;


/**
 * @author Emond Emmanuelle, Marc meynier
 *
 * This class createsthe window which adjust the ressources.
 * A ressources is a section, room, material, subject and
 * teacher. 
 */
public class WindowRessources extends WindowAbstract {

	/**
	 * This method builds the window witch adjust the ressources.
	 * A ressources is a section, room, material, subject and
	 * teahcer. 
	 * 
	 * @param frame the frame of the window
	 * @param obj object
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		initWindow(frame,"", 550, 520);
		Container contentPane = frame.getContentPane();

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = GridBagConstraints.REMAINDER; 
		
		c.insets =new Insets(0,5,0,5);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel section = createPanelRessource(obj, "Filière");
		gridbag.setConstraints(section , c);
		contentPane.add(section );
		
		JPanel room = createPanelRessource(obj, "Salle");
		gridbag.setConstraints(room, c);
		contentPane.add(room);
		
		JPanel material = createPanelRessource(obj, "Matériel");
		gridbag.setConstraints(material, c);
		contentPane.add(material);
		
		JPanel subject = createPanelRessource(obj, "Matière");	
		gridbag.setConstraints(subject, c);
		contentPane.add(subject);
		
		JPanel teacher = createPanelRessource(obj, "Enseignant");
		gridbag.setConstraints(teacher, c);
		contentPane.add(teacher);
		
		addButtonValidation(contentPane, c, gridbag );
	}
	
}
=======
/*
 * Created on 28 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;


/**
 * @author Emond Emmanuelle, Marc meynier
 *
 * This class creates the window which permit to create a ressource
 */
public class WindowRessources extends WindowAbstract {

	/**
	 * This method builds the window witch adjust the ressources.
	 * A ressources is a section, room, material, subject and
	 * teacher. 
	 * 
	 * @param frmae the frame of the window
	 * @param obj object
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		initWindow(frame,"", 550, 520);
		Container contentPane = frame.getContentPane();

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = GridBagConstraints.REMAINDER; 
		
		c.insets =new Insets(0,5,0,5);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel section = createPanelRessource(obj, "Filière");
		gridbag.setConstraints(section , c);
		contentPane.add(section );
		
		JPanel room = createPanelRessource(obj, "Salle");
		gridbag.setConstraints(room, c);
		contentPane.add(room);
		
		JPanel material = createPanelRessource(obj, "Matériel");
		gridbag.setConstraints(material, c);
		contentPane.add(material);
		
		JPanel subject = createPanelRessource(obj, "Matière");	
		gridbag.setConstraints(subject, c);
		contentPane.add(subject);
		
		JPanel teacher = createPanelRessource(obj, "Enseignant");
		gridbag.setConstraints(teacher, c);
		contentPane.add(teacher);
		
		addButtonValidation(contentPane, c, gridbag );
	}

	
}

