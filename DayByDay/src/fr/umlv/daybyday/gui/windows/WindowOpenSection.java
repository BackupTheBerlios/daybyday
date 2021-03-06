package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Emond Emmanuelle, Marc meynier
 *
 * This class creates the window which permit to open the timetable
 * of a section. 
 */
public class WindowOpenSection extends WindowAbstract {

	
	/**
	 * This method creates the window which permit to open the timetable
	 * of a section. 
	 * 
	 * @param frame The frame of the window
	 * @param obj an object table which contains in position 0 
	 * the main frame
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		final MainFrame mainframe = (MainFrame) obj[0];
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		initWindow(frame,"Ouverture d'une fili�re", 400, 350, mainframe.getFrameX(), mainframe.getFrameY());
		
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
		frame.setVisible(true);
	}

}
