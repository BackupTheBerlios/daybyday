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
public class WindowCreateTeacher extends WindowAbstract {

	/**
	 * This method builds the window calendar.  
	 * 
	 * @param contentPane The container of the JFrame
	 * @param obj the object 
	 */
	public static void createWindow(JFrame frame,Object [] obj){
		createWindow(frame);
	}
	


	
	/**
	 * This method builds the windows witch permit to
	 * create a teacher
	 * 
	 * @param contentPane the container of the window
	 */
	public static void createWindow(JFrame frame){
		initWindow(frame,"Nouvel Enseignant", 400, 320);
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(5,20,5,20);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.CENTER;
		
		//name
		JLabel nameLabel = new JLabel("  Nom : ");
		gridbag.setConstraints(nameLabel, c);
		contentPane.add(nameLabel);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JTextField nameTextField = new JTextField();
		gridbag.setConstraints(nameTextField, c);
		contentPane.add(nameTextField);;
		
		//firstname
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel firstnameLabel = new JLabel("  Prénom : ");
		gridbag.setConstraints(firstnameLabel, c);
		contentPane.add(firstnameLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JTextField firstnameTextField = new JTextField();
		gridbag.setConstraints(firstnameTextField, c);
		contentPane.add(firstnameTextField);				
		
		//Information
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
		
		// Identifiant 
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		
		JLabel idLabel = new JLabel("  Identifiant : ");
		gridbag.setConstraints(idLabel, c);
		contentPane.add(idLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JTextField idTextField = new JTextField();
		gridbag.setConstraints(idTextField, c);
		contentPane.add(idTextField);
		
		//password
		c.gridwidth = 1; 
		JLabel password = new JLabel("  Mot de passe : ");
		c.fill = GridBagConstraints.CENTER;
		gridbag.setConstraints(password, c);
		contentPane.add(password);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL; 
		
		JPasswordField passewordField = new JPasswordField();
		gridbag.setConstraints(passewordField, c);
		contentPane.add(passewordField);
		
		// confirmation 
		c.gridwidth = 1; 
		JLabel confirmation = new JLabel(" Confirmation : ");
		c.fill = GridBagConstraints.CENTER;
		gridbag.setConstraints(confirmation, c);
		contentPane.add(confirmation);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL; 
		
		JPasswordField confPassewordField = new JPasswordField();
		gridbag.setConstraints(confPassewordField, c);
		contentPane.add(confPassewordField);
		
		//Add button ok and annuler
		addButtonValidation(contentPane, c, gridbag );
	}

	
}
