
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.daybyday.ejb.admin.user.UserBusinessPK;
import fr.umlv.daybyday.ejb.admin.user.UserDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.Teacher;


/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to create 
 * a new teacher
 */
public class WindowModifyTeacher extends WindowAbstract {

	
	
	/**
	 * This method builds the windows witch permit to
	 * create a teacher
	 * 
	 * @param frame the frame of the window
	 * @param obj the table object. in position 0 the mainframe,
	 */
	public static void createWindow(final JFrame frame,Object [] obj){
		
		final MainFrame mainframe = (MainFrame) obj[0];
		
		Teacher teachref = (Teacher) mainframe.getSelectedObject();
		TeacherDto oldteachref = (TeacherDto)teachref.getDto();
		
		initWindow(frame,"Modifier Enseignant", 400, 400, mainframe.getFrameX(), mainframe.getFrameY());
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
		final JTextField nameTextField = new JTextField(oldteachref.getName());
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
		final JTextField firstnameTextField = new JTextField(oldteachref.getFirstname());
		gridbag.setConstraints(firstnameTextField, c);
		contentPane.add(firstnameTextField);				
		
		//email
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel emailLabel = new JLabel("  Email : ");
		gridbag.setConstraints(emailLabel, c);
		contentPane.add(emailLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JTextField emailTextField = new JTextField(oldteachref.getEmail());
		gridbag.setConstraints(emailTextField, c);
		contentPane.add(emailTextField);		
		
		//phone
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel phoneLabel = new JLabel("  Tel : ");
		gridbag.setConstraints(phoneLabel, c);
		contentPane.add(phoneLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JTextField phoneTextField = new JTextField(oldteachref.getTel());
		gridbag.setConstraints(phoneTextField, c);
		contentPane.add(phoneTextField);	
		
		//Office
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel officeLabel = new JLabel("  Bureau : ");
		gridbag.setConstraints(officeLabel, c);
		contentPane.add(officeLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JTextField officeTextField = new JTextField(oldteachref.getOffice());
		gridbag.setConstraints(officeTextField, c);
		contentPane.add(officeTextField);
		
		//Office
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel profileLabel = new JLabel("  Profile : ");
		gridbag.setConstraints(profileLabel, c);
		contentPane.add(profileLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JComboBox profileTextField = new JComboBox(new Object []{"interne","externe"});
		profileTextField.setSelectedItem(oldteachref.getProfile());
		gridbag.setConstraints(profileTextField, c);
		contentPane.add(profileTextField);
		
		
		//Information
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		JLabel infoLabel = new JLabel("  Informations : ");
		gridbag.setConstraints(infoLabel, c);
		contentPane.add(infoLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		final JTextArea infoList= new JTextArea(oldteachref.getDescription());
		
		JScrollPane infoScrollpane = new JScrollPane(infoList);
		gridbag.setConstraints(infoScrollpane, c);
		contentPane.add(infoScrollpane);
		
		UserDto olduserdto = null;
		try {
			// Identifiant 
			olduserdto = MainFrame.myDaybyday.getUser(new UserBusinessPK(oldteachref.getName(),oldteachref.getFirstname()));
		} catch (RemoteException e1) {
			olduserdto = new UserDto();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		
		JLabel idLabel = new JLabel("  Identifiant : ");
		gridbag.setConstraints(idLabel, c);
		contentPane.add(idLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JTextField idTextField = new JTextField(olduserdto.getName());
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
		
		final JPasswordField passewordField = new JPasswordField();
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
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
			//	("Zipstein","zipo","zip@univ-mlv.fr","06 66 66 66 66","0001","interne","prof de crypto et d'algorithmes",new Boolean(true));
			TeacherDto newdto = new TeacherDto(nameTextField.getText(),
					firstnameTextField.getText(),
					emailTextField.getText(),
					phoneTextField.getText(),
					officeTextField.getText(),
					"login",
					"pass",
					(String) profileTextField.getSelectedItem(),
					infoList.getText()
					);

		//	("Stéphanie","Martinez","stephanie.martin@univ-mlv.fr","06 21 23 24 25","3x100","stephpass","user",new Boolean(true));
			UserDto newuserdto = new UserDto
					(		nameTextField.getText(),
							firstnameTextField.getText(),
							emailTextField.getText(),
							phoneTextField.getText(),
							officeTextField.getText(),
							"login",
							new String(passewordField.getPassword()),
							"user"
							);

				
				
				try {
				
					MainFrame.myDaybyday.createTeacher(newdto);
					MainFrame.myDaybyday.createUser(newuserdto);
					mainframe.showError(frame,"Nouvel enseignant créé : \n"+
												"Login : " + nameTextField.getText() + 
												"Mot de passe : " + new String(passewordField.getPassword()));
					framefinal.dispose();
				} catch (RemoteException e) {
					mainframe.showError(frame,e.toString());
				} catch (CreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
			
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridwidth = GridBagConstraints.RELATIVE;
		gridbag.setConstraints(ok, c);
		contentPane.add(ok);
		JButton cancel = new JButton("Annuler");
		cancel.setPreferredSize(new Dimension(100,20));
		cancel.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				framefinal.dispose();
				
			}
			
		});
		c.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(cancel, c);
		contentPane.add(cancel);
		frame.setVisible(true);
	}

	
}