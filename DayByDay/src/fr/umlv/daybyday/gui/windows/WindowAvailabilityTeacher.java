package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.umlv.daybyday.gui.DBDColor;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to manage 
 * the avaibility teacher.
 */
public class WindowAvailabilityTeacher extends WindowAbstract {

	/**
	 * This method builds the window to permit to adjust
	 * the teacher availability.  
	 * 
	 * @param frame the frame of the window 
	 * @param obj the object
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		try {
		final MainFrame mainframe = (MainFrame) obj[0];
		
		initWindow(frame,"Gestion des disponibilités d'un enseignant", 550, 450, mainframe.getFrameX(), mainframe.getFrameY());
		Container contentPane = frame.getContentPane();
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
		JComboBox teacherCombo = new JComboBox(MainFrame.myDaybyday.getAllTeachers().toArray());
		teacherCombo.setRenderer(new ComboBoxFormationElementRenderer());
		gridbag.setConstraints(teacherCombo, c);
		contentPane.add(teacherCombo);
		
		
		
		JPanel availability = new JPanel();
		

		availability.setBorder(BorderFactory.createTitledBorder(" Disponibilités : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		availability.setLayout(gridbag2);
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = GridBagConstraints.REMAINDER; 
		
		c2.insets =new Insets(5,0,5,0);
		c2.anchor = GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel dateBegin = new JLabel("    Date de début        Date de fin" +
				"                                                             D" +
				"                          I");
		gridbag2.setConstraints(dateBegin, c2);
		availability.add(dateBegin);
		

		c2.insets =new Insets(5,5,5,5); 
		c2.anchor = GridBagConstraints.LINE_START;	
		
		JPanel date = new JPanel(null);
		GridBagLayout gridbag3 = new GridBagLayout();
		GridBagConstraints c3 = new GridBagConstraints();
		date.setLayout(gridbag3);
		
		c3.weightx = 1; 
		c3.weighty = 1; 
		c3.gridwidth = 1; 
		
		c3.insets =new Insets(0,5,0,5);
		c3.anchor = GridBagConstraints.LINE_START;
		c3.fill = GridBagConstraints.HORIZONTAL;
		
		//TODO Faire une bouble sur les dispo et non prof
		for(int i=0; i<2; i++ ){
			c3.gridwidth = 3; 
			c3.fill = GridBagConstraints.HORIZONTAL;
			JLabel begin = new JLabel("debut");
			begin.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag3.setConstraints(begin, c3);
			date.add(begin);
			
			c3.gridwidth = 3;
			JLabel end = new JLabel("fin");
			end.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag3.setConstraints(end, c3);
			date.add(end);
			
			c3.gridwidth = 1;
			c3.fill = GridBagConstraints.CENTER;
			JButton delete = new JButton("Supprimer");
			//delete.setBackground(DBDColor.getColor("DARK_GRAY"));
			gridbag3.setConstraints(delete, c3);
			date.add(delete);
			
			JCheckBox dBox = new JCheckBox("", true);
			gridbag3.setConstraints(dBox, c3);
			date.add(dBox);
			
			c3.gridwidth = GridBagConstraints.REMAINDER;
			JCheckBox iBox = new JCheckBox("", true);
			gridbag3.setConstraints(iBox, c3);
			date.add(iBox);
		}		
	
		JScrollPane dateScrollpane = new JScrollPane(date);
		dateScrollpane.setPreferredSize(new Dimension(500,60));
		dateScrollpane.setMinimumSize(new Dimension(500,60));
		gridbag2.setConstraints(dateScrollpane, c2);
		availability.add(dateScrollpane);
		
		JPanel newAvailability = createPanelNewAvailability();
		gridbag2.setConstraints(newAvailability, c2);
		availability.add(newAvailability);
		
		c2.anchor = GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.insets =new Insets(10,0,0,0);
		JLabel title = new JLabel("     Jour      Fréquence" +
				"      Intervalle" +
				"            Horaire          " +
				"                                      " +
				"      D              I");
		gridbag2.setConstraints(title, c2);
		availability.add(title);
		
		c2.insets =new Insets(5,5,5,5);
		JPanel hour = createAvailabilityHours(MainFrame.myDaybyday.getAllTeachers().toArray());
		JScrollPane hourScrollpane = new JScrollPane(hour);	
		gridbag2.setConstraints(hourScrollpane, c2);
		availability.add(hourScrollpane);
		gridbag.setConstraints(availability,c);
		contentPane.add(availability);
		
		JPanel newAvailability2 = createPanelNewAvailability();
		gridbag.setConstraints(newAvailability2,c);
		contentPane.add(newAvailability2);
		
		// Add buttons OK and Annuler
		addButtonValidation(contentPane, c, gridbag );
		frame.setVisible(true);
		}
		catch (Exception e){
			
		}
	}
}