package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.MainFrame;


/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits
 *  the user to connect him. 
 */
public class WindowAdministration extends WindowAbstract{

	/**
	 * Tihs method builds the window of the administrator panel. 
	 * 
	 * @param frame the frame of the window 
	 * @param obj the object
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		final MainFrame mainframe = (MainFrame) obj[0];
		initWindow(frame,"Panneau Administrateur", 430, 450, mainframe.getFrameX(), mainframe.getFrameY());
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
		
		JLabel id = new JLabel("Identifant :");
		gridbag.setConstraints(id, c);
		contentPane.add(id);
		
		c.anchor = GridBagConstraints.LINE_START;
		JTextField loginTextField = new JTextField();
		gridbag.setConstraints(loginTextField, c);
		contentPane.add(loginTextField);

		c.anchor = GridBagConstraints.LINE_END;
		JLabel password = new JLabel("Mot de passe :");
		gridbag.setConstraints(password, c);
		contentPane.add(password);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.LINE_START;
		JPasswordField passewordField = new JPasswordField();
		gridbag.setConstraints(passewordField, c);
		contentPane.add(passewordField);
		
		JPanel user = createPanelUser(obj);
		gridbag.setConstraints(user, c);
		contentPane.add(user);
		
		JPanel bdd = createPanelBase(" Base de données : ");
		gridbag.setConstraints(bdd, c);
		contentPane.add(bdd);
		
		JPanel ldap = createPanelBase(" LDAP : ");
		gridbag.setConstraints(ldap, c);
		contentPane.add(ldap);
		
		c.gridwidth = 1;
		JLabel blank = new JLabel("");
		gridbag.setConstraints(blank, c);
		contentPane.add(blank);
		
		// Add buttons Ok and Annuler
		addButtonValidation(contentPane, c, gridbag );	
		frame.setVisible(true);
	}
}