
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.umlv.daybyday.ejb.admin.user.UserPK;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowLogin extends WindowAbstract {

	
	/**
	 * This method builds the connection window 
	 * 
	 * @param frame the frame of the window 
	 * @param obj the object. in position 0 the mainframe
	 */
	public static void createWindow(final JFrame frame,Object [] obj){
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
		
		final JTextField loginTextField = new JTextField();
		gridbag.setConstraints(loginTextField, c);
		contentPane.add(loginTextField);
	
		c.gridwidth = 1; 
		
		JLabel password = new JLabel("Mot de passe : ");
		c.fill = GridBagConstraints.CENTER;
		gridbag.setConstraints(password, c);
		contentPane.add(password);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
				
		final JPasswordField passewordField = new JPasswordField();
		gridbag.setConstraints(passewordField, c);
		contentPane.add(passewordField);
	
		c.anchor = GridBagConstraints.EAST;
		c.insets =new Insets(0,0,0,20);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					
					mainframe.setUser(MainFrame.myDaybyday.getUser(new UserPK(loginTextField.getText(), new String(passewordField.getPassword()))));
					mainframe.setEnable(true);
					framefinal.dispose();
				} catch (Exception e) {
					mainframe.showError(frame,"Utilisateur ou mot de passe incorrect");
				}
				
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

