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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.DBDColor;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;
/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowLogin extends WindowAbstract {

	/**
	 * This method builds the window calendar.  
	 * 
	 * @param contentPane The container of the JFrame
	 * @param obj the object 
	 */
	
	public static void createWindow(JFrame frame,Object [] obj){
		final MainFrame mainframe = (MainFrame) obj [0];
		
		final JFrame framefinal = frame;
		Container contentPane = frame.getContentPane();
		initWindow(frame,"Login", 400, 140);
	
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);		
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;	
		c.insets =new Insets(0,10,0,10);
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel id = new JLabel("Identifant : ");
		gridbag.setConstraints(id, c);
		contentPane.add(id);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JTextField loginTextField = new JTextField();
		gridbag.setConstraints(loginTextField, c);
		contentPane.add(loginTextField);
	
		c.gridwidth = 1; 
		
		JLabel password = new JLabel("Mot de passe : ");
		c.fill = GridBagConstraints.CENTER;
		gridbag.setConstraints(password, c);
		contentPane.add(password);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
				
		JPasswordField passewordField = new JPasswordField();
		gridbag.setConstraints(passewordField, c);
		contentPane.add(passewordField);
	
		c.anchor = GridBagConstraints.EAST;
		c.insets =new Insets(0,0,0,20);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				mainframe.setEnable(true);
				framefinal.dispose();
			}
			
		});

		
		JButton cancelButton = new JButton("Quitter");
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				mainframe.closeApp();
				framefinal.dispose();
			}
			
		});
				
	
		gridbag.setConstraints(okButton, c);
		contentPane.add(okButton);
		
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
	
		gridbag.setConstraints(cancelButton, c);
		contentPane.add(cancelButton);		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	
}
