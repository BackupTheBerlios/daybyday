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
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;
/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowDetailsCourse extends WindowAbstract {

	/**
	 * This method builds the window calendar.  
	 * 
	 * @param contentPane The container of the JFrame
	 * @param obj the object 
	 */
	public static void createWindow(JFrame frame,Object [] obj){
		createWindow(frame);
	}
	
	public static void createWindow(JFrame frame){
		initWindow(frame,"Niveau de détails des cours", 550, 520);
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
		
		JPanel formation = createPanelDetail("Formation");
		gridbag.setConstraints(formation, c);
		contentPane.add(formation);
		
		JPanel teacher = createPanelDetail("Enseignant");
		gridbag.setConstraints(teacher, c);
		contentPane.add(teacher);
		
		JPanel material = createPanelDetail("Matériel");
		gridbag.setConstraints(material, c);
		contentPane.add(material);
		
		JPanel room = createPanelDetail("Salle");
		gridbag.setConstraints(room, c);
		contentPane.add(room);
		
		JPanel subject = createPanelDetail("Matière");
		gridbag.setConstraints(subject, c);
		contentPane.add(subject);
		
		JPanel generale = createPanelDetail("Générale");
		gridbag.setConstraints(generale, c);
		contentPane.add(generale);
		
		//Add button OK and Annuler
		addButtonValidation(contentPane, c, gridbag );
	}

	
}
