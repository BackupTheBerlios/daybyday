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
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowTool extends WindowAbstract {

	/**
	 * This method builds the configuration tool bar of the window.
	 * 
	 * @param contentPane the container  of the window
	 * @param obj the object
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		initWindow(frame,"Barre d'outils", 430, 180);
		Container contentPane = frame.getContentPane();

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1;
		
		c.insets =new Insets(0,5,0,5);
		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel command = createPanelId(obj, " Commandes : ");	
		gridbag.setConstraints(command, c);
		contentPane.add(command);
		
		c.gridwidth = 2;
		JPanel buttonArrow = createPanelArrowsButton();
		gridbag.setConstraints(buttonArrow, c);
		contentPane.add(buttonArrow);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		JPanel tools = createPanelId(obj, " Barre d'outils : ");	
		gridbag.setConstraints(tools, c);
		contentPane.add(tools);
		
		c.gridwidth = 1;
		JLabel blank = new JLabel("");
		gridbag.setConstraints(blank , c);
		contentPane.add(blank );
		
		addButtonValidation(contentPane, c, gridbag );			
	}

	
}
