
/*
 * Created on 28 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.daybyday.gui.MainFrame;


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
		final MainFrame mainframe = (MainFrame) obj[0];
		initWindow(frame,"", 550, 520, mainframe.getFrameX(), mainframe.getFrameY());
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
		


		
		JPanel section = new JPanel(null);
		section.setBorder(BorderFactory.createTitledBorder(" Filière : "));
		
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
	
		section.setLayout(gridbag2);
	
		c2.weightx = 1;
		c2.weighty = 1;
		c2.insets =new Insets(5,5,5,5);
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.FIRST_LINE_START;
		c2.anchor = GridBagConstraints.LINE_START;
		
		JLabel label = new JLabel(" Sélection : ");
		gridbag2.setConstraints(label, c2);
		section.add(label);
		
		c2.fill = GridBagConstraints.HORIZONTAL;
		JComboBox combo = new JComboBox(((ArrayList)obj[0]).toArray());
		gridbag2.setConstraints(combo, c2);
		section.add(combo);
	
		c2.anchor= GridBagConstraints.LINE_END;
		JButton delete = new JButton("Supprimer");
		//delete.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(delete, c2);
		section.add(delete);
		
		c2.anchor = GridBagConstraints.LINE_START;
		JButton modify = new JButton("Modifier");
		//modify.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(modify, c2);
		section.add(modify);
		
		gridbag.setConstraints(section , c);
		contentPane.add(section );
		
		// ROOM PANEL 
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
		frame.setVisible(true);
	}

	
}

