package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;


/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to manage
 * the calendar
 */
public class WindowCalendar extends WindowAbstract {

	/**
	 * This method builds the window calendar.  
	 * 
	 * @param frame the frame of the window
	 * @param obj the object 
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		initWindow(frame,"Visualisation des emplois du temps", 470, 350);
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		c.insets =new Insets(0,5,0,5);
		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel week = createPanelWeek();
		gridbag.setConstraints(week, c);
		contentPane.add(week);
		
		DBDCalendarPanel calendar =new DBDCalendarPanel();	
		gridbag.setConstraints(calendar, c);
		contentPane.add(calendar);	
	}

	
}