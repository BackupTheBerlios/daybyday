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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.DBDColor;
import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;
/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowMaterial extends WindowAbstract {

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
		initWindow(frame,"Nouveau matériel", 400, 250);
		
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		// Réference 
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(5,20,5,20);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.CENTER;
		
		// identifiant
		JLabel idLabel = new JLabel("  Identifiant : ");
		gridbag.setConstraints(idLabel, c);
		contentPane.add(idLabel);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JTextField idTextField = new JTextField();
		gridbag.setConstraints(idTextField, c);
		contentPane.add(idTextField);;
		
		//building 
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel buildingLabel = new JLabel("  Batiment : ");
		gridbag.setConstraints(buildingLabel, c);
		contentPane.add(buildingLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JTextField batimentTextField = new JTextField();
		gridbag.setConstraints(batimentTextField, c);
		contentPane.add(batimentTextField);
		
		// Site 
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel siteLabel = new JLabel("  Site : ");
		gridbag.setConstraints(siteLabel, c);
		contentPane.add(siteLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JTextField siteTextField = new JTextField();
		gridbag.setConstraints(siteTextField, c);
		contentPane.add(siteTextField);
		
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		JLabel infoLabel = new JLabel("  Informations : ");
		gridbag.setConstraints(infoLabel, c);
		contentPane.add(infoLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JTextArea infoList= new JTextArea("\n\n");
		
		JScrollPane infoScrollpane = new JScrollPane(infoList);
		gridbag.setConstraints(infoScrollpane, c);
		contentPane.add(infoScrollpane);
	
		// Add button OK and Annuler
		addButtonValidation(contentPane, c, gridbag );
	}

	
}
