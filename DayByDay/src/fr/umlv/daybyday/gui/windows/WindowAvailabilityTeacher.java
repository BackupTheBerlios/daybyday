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
/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowAvailabilityTeacher extends WindowAbstract {

	/**
	 * This method builds the window to permit to adjust
	 * the teacher availability.  
	 * 
	 * @param contentPane the container of the windws
	 * @param ens the list oh the teacher
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		initWindow(frame,"Gestion des disponibilités d'un enseignant", 550, 650);
		Container contentPane = frame.getContentPane();
		//TODO changer ArrayList en Teacher
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		
		c.insets =new Insets(0,5,0,5);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel teacher = new JLabel(" Enseignant : ");
		gridbag.setConstraints(teacher, c);
		contentPane.add(teacher);
		
		c.gridwidth = GridBagConstraints.REMAINDER; 
		JComboBox teacherCombo = new JComboBox(obj);
		gridbag.setConstraints(teacherCombo, c);
		contentPane.add(teacherCombo);
		
		JPanel availability = createPanelAvailability(obj);
		gridbag.setConstraints(availability,c);
		contentPane.add(availability);
		
		JPanel newAvailability = createPanelNewAvailability();
		gridbag.setConstraints(newAvailability,c);
		contentPane.add(newAvailability);
		
		// Add buttons OK and Annuler
		addButtonValidation(contentPane, c, gridbag );
	}

	
}
