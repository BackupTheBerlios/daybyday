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
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to 
 * configure the table of the timetable.
 */
public class WindowConfigTable extends WindowAbstract {

	/**
	 * This method builds the window which permits to 
	 * configure the table of the timetable.  
	 * 
	 * @param frame the frame of the window
	 * @param obj the object 
	 */
	public static void createWindow(JFrame frame,Object [] obj){
		createWindow(frame, (ArrayList) obj[0]);
	}
	
	
	/**
	 * This method builds the window which permits to 
	 * configure the table of the timetable.  
	 * 
	 * @param frame the frame of the window
	 * @param tmp a temporary list
	 */
	public static void createWindow(JFrame frame, ArrayList tmp){
		initWindow(frame,"Configuration de la grille", 430, 250);
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
		
		JPanel day = createPanelDay();
		gridbag.setConstraints(day, c);
		contentPane.add(day);
		
		JPanel interval = createPanelInterval();
		gridbag.setConstraints(interval, c);
		contentPane.add(interval);
		
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		JLabel hour = new JLabel(" Découpage des heures : ");
		gridbag.setConstraints(hour, c);
		contentPane.add(hour);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.LINE_START;
		JComboBox hourCombo = new JComboBox();
		gridbag.setConstraints(hourCombo, c);
		contentPane.add(hourCombo);
		
		//Add button OK and Annuler
		addButtonValidation(contentPane, c, gridbag );			
	}
	
}