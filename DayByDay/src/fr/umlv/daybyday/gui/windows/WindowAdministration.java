package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.umlv.daybyday.ejb.admin.user.UserDto;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.DBDColor;
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
	public static void createWindow(final JFrame frame, final Object[] obj){
		try{
		final MainFrame mainframe = (MainFrame) obj[0];
		
		File property = new File((new JLabel()).getClass().getResource("/property").toURI());
		BufferedReader in = new BufferedReader(new FileReader(property));

		LineNumberReader lnr = new LineNumberReader(in);

		
		
		String arg1=lnr.readLine();
		String arg2=lnr.readLine();
		String arg3=lnr.readLine();
		String arg4=lnr.readLine();
		String arg5=lnr.readLine();
		String arg6=lnr.readLine();

		/*in.close();
		lnr.close();
		*/
		initWindow(frame,"Panneau Administrateur", 500, 450, mainframe.getFrameX(), mainframe.getFrameY());
		
		final UserDto userdto = mainframe.getUser();

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
		final JTextField loginTextField = new JTextField(userdto.getLogin());
		gridbag.setConstraints(loginTextField, c);
		contentPane.add(loginTextField);

		c.anchor = GridBagConstraints.LINE_END;
		JLabel password = new JLabel("Mot de passe :");
		gridbag.setConstraints(password, c);
		contentPane.add(password);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.LINE_START;
		final JPasswordField passewordField = new JPasswordField(userdto.getPass());
		gridbag.setConstraints(passewordField, c);
		contentPane.add(passewordField);
		
		JPanel user = new JPanel();
		user.setBorder(BorderFactory.createTitledBorder(" Compte utilisateur : "));
		GridBagLayout gridbag4 = new GridBagLayout();
		GridBagConstraints c4 = new GridBagConstraints();
		user.setLayout(gridbag4);
		
		c4.weightx = 1; 
		c4.weighty = 1; 
		c4.gridwidth = GridBagConstraints.REMAINDER; 
		
		c4.insets =new Insets(4,5,4,5);
		c4.anchor = GridBagConstraints.LINE_START;
		c4.fill = GridBagConstraints.HORIZONTAL;
		
		final JPanel currentAccount = new JPanel();
		setCurrentUserPanel(currentAccount,frame);
		
		
		JScrollPane currentAccountScrollpane = new JScrollPane(currentAccount);	
		currentAccountScrollpane.setMinimumSize(new Dimension(500,60));
		gridbag4.setConstraints(currentAccountScrollpane, c4);
		user.add(currentAccountScrollpane);
		
		JPanel newAccount = new JPanel();
		newAccount.setBorder(BorderFactory.createTitledBorder(" Nouveau : "));
		
		GridBagLayout gridbag7 = new GridBagLayout();
		GridBagConstraints c7 = new GridBagConstraints();
		newAccount.setLayout(gridbag7);
		
		c7.weightx = 1; 
		c7.weighty = 1; 
		c7.gridwidth = 1; 
		
		c7.insets =new Insets(0,5,0,5);
		c7.anchor = GridBagConstraints.LINE_START;
		c7.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel id8 = new JLabel("Identifant :");
		gridbag7.setConstraints(id8, c7);
		newAccount.add(id8);
		
		c7.gridwidth = 10;		
		final JTextField loginTextField8 = new JTextField();
		loginTextField8.setPreferredSize(new Dimension(80,20));
		loginTextField8.setMinimumSize(new Dimension(80,20));
		gridbag7.setConstraints(loginTextField8, c7);
		newAccount.add(loginTextField8);
	
		c7.gridwidth = 1; 
		c7.anchor = GridBagConstraints.LINE_END;
		JLabel password8 = new JLabel("Mot de passe :");
		gridbag7.setConstraints(password8 , c7);
		newAccount.add(password8 );
	
		
		c7.anchor = GridBagConstraints.LINE_START;
		c7.gridwidth = 10;
		final JPasswordField passewordField8 = new JPasswordField();
		passewordField8.setPreferredSize(new Dimension(80,20));
		passewordField8.setMinimumSize(new Dimension(80,20));
		gridbag7.setConstraints(passewordField8, c7);
		newAccount.add(passewordField8);
		
		c7.gridwidth = GridBagConstraints.REMAINDER;
		c7.fill = GridBagConstraints.CENTER;
		c7.anchor = GridBagConstraints.LINE_END;
		JButton add = new JButton(" Ajouter ");
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
					try {
						UserDto userDto = new UserDto(loginTextField8.getText(),loginTextField8.getText(),loginTextField8.getText(),loginTextField8.getText(),loginTextField8.getText(),loginTextField8.getText(),passewordField8.getText(),"");
						MainFrame.myDaybyday.createUser(userDto);
						setCurrentUserPanel(currentAccount,frame);
						frame.setVisible(true);
					} catch (RemoteException e) {
						mainframe.showError(e.getMessage());
					} catch (CreationException e) {
						mainframe.showError(e.getMessage());
					}
				
			}
		});
		//add.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag7.setConstraints(add, c7);
		newAccount.add(add);
			
		gridbag4.setConstraints(newAccount, c4);
		user.add(newAccount);
		gridbag.setConstraints(user, c);
		contentPane.add(user);
		
		JPanel bdd = new JPanel();
		
		bdd.setBorder(BorderFactory.createTitledBorder(" Base de données : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		bdd.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1;
		
		c2.insets =new Insets(2,5,2,5);
		c2.anchor = GridBagConstraints.LINE_END;
		c2.fill = GridBagConstraints.HORIZONTAL;
				
		JLabel id2 = new JLabel("Identifant :");
		gridbag2.setConstraints(id2, c2);
		bdd.add(id2);
		
		c2.gridwidth = 4;		
		c2.anchor = GridBagConstraints.LINE_START;
		final JTextField loginTextField2 = new JTextField(arg1);
		gridbag2.setConstraints(loginTextField2, c2);
		bdd.add(loginTextField2);
	
		c2.gridwidth = 1; 
		c2.anchor = GridBagConstraints.LINE_END;
		JLabel password2 = new JLabel("Mot de passe :");
		gridbag2.setConstraints(password2, c2);
		bdd.add(password2);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.anchor = GridBagConstraints.LINE_START;
		final JPasswordField passewordField2 = new JPasswordField(arg2);
		gridbag2.setConstraints(passewordField2, c2);
		bdd.add(passewordField2);
		
		c2.gridwidth = 1;
		JLabel access = new JLabel(" Accès :");
		gridbag2.setConstraints(access, c2);
		bdd.add(access);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		final JTextField accessField = new JTextField(arg3);
		gridbag2.setConstraints(accessField, c2);
		bdd.add(accessField);
		gridbag.setConstraints(bdd, c);
		contentPane.add(bdd);
		
		JPanel ldap = new JPanel();
		ldap.setBorder(BorderFactory.createTitledBorder(" LDAP : "));
		GridBagLayout gridbag3 = new GridBagLayout();
		GridBagConstraints c3 = new GridBagConstraints();
		ldap.setLayout(gridbag3);
		
		c3.weightx = 1; 
		c3.weighty = 1; 
		c3.gridwidth = 1;
		
		c3.insets =new Insets(3,5,3,5);
		c3.anchor = GridBagConstraints.LINE_END;
		c3.fill = GridBagConstraints.HORIZONTAL;
				
		JLabel id3 = new JLabel("Identifant :");
		gridbag3.setConstraints(id3, c3);
		ldap.add(id3);
		
		c3.gridwidth = 4;		
		c3.anchor = GridBagConstraints.LINE_START;
		final JTextField loginTextField3 = new JTextField(arg4);
		gridbag3.setConstraints(loginTextField3, c3);
		ldap.add(loginTextField3);
	
		c3.gridwidth = 1; 
		c3.anchor = GridBagConstraints.LINE_END;
		JLabel password3 = new JLabel("Mot de passe :");
		gridbag3.setConstraints(password3, c3);
		ldap.add(password3);
	
		c3.gridwidth = GridBagConstraints.REMAINDER;
		c3.anchor = GridBagConstraints.LINE_START;
		final JPasswordField passewordField3 = new JPasswordField(arg5);
		gridbag3.setConstraints(passewordField3, c3);
		ldap.add(passewordField3);
		
		c3.gridwidth = 1;
		JLabel access2 = new JLabel(" Accès :");
		gridbag3.setConstraints(access2, c3);
		ldap.add(access2);
	
		c3.gridwidth = GridBagConstraints.REMAINDER;
		c3.anchor = GridBagConstraints.LINE_START;
		c3.fill = GridBagConstraints.HORIZONTAL;
		final JTextField accessField2 = new JTextField(arg6);
		gridbag3.setConstraints(accessField2, c3);
		ldap.add(accessField2);
		gridbag.setConstraints(ldap, c);
		contentPane.add(ldap);
		
		c.gridwidth = 1;
		JLabel blank = new JLabel();
		gridbag.setConstraints(blank, c);
		contentPane.add(blank);
		
		// Add buttons Ok and Annuler
//		 Add button OK and Annuler
		
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				File property2;
				try {
					userdto.setLogin(loginTextField.getText());
					userdto.setPass(passewordField.getText());
					try{
						MainFrame.myDaybyday.updateUser(userdto);
					}catch (fr.umlv.daybyday.ejb.util.exception.StaleUpdateException e1){
						
					}
					property2 = new File((new JLabel()).getClass().getResource("/property").toURI());
					BufferedWriter on = new BufferedWriter(new FileWriter(property2));
					on.write(loginTextField2.getText()+ "\n" +
							passewordField2.getText() +"\n" +
							accessField.getText()     +"\n" +
							loginTextField3.getText() +"\n" +
							passewordField3.getText()+"\n" +
							accessField2.getText());
					on.flush();
					on.close();
					frame.dispose();
					
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (WriteDeniedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EntityNotFoundException e) {
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
		
			
		frame.setVisible(true);
		}catch(Exception e){
			System.out.println("ohoh" + obj[0] + " "+ e);
		}
	}
	
	public static void setCurrentUserPanel(final JPanel currentAccount, final JFrame frame) throws RemoteException{
		currentAccount.removeAll();
		GridBagLayout gridbag5 = new GridBagLayout();
		GridBagConstraints c5 = new GridBagConstraints();
		currentAccount.setLayout(gridbag5);
		
		c5.weightx = 1; 
		c5.weighty = 1; 
		c5.gridwidth = 1; 
		
		c5.insets =new Insets(5,5,5,5);
		c5.anchor = GridBagConstraints.LINE_START;
		c5.fill = GridBagConstraints.HORIZONTAL;
		
		Object [] refs = MainFrame.myDaybyday.getAllUsers().toArray();
		//TODO Faire une bouble le nombre d'utilisateur
		for(int i=0; i< refs.length; i++ ){
			
			final UserDto userDto = (UserDto)refs[i];
			
			c5.gridwidth = 4; 
			c5.fill = GridBagConstraints.HORIZONTAL;
			JLabel id4 = new JLabel(userDto.getLogin());
			id4.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag5.setConstraints(id4, c5);
			currentAccount.add(id4);
			
			c5.gridwidth = 4;
			JLabel login = new JLabel(userDto.getPass());
			login.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag5.setConstraints(login, c5);
			currentAccount.add(login);
			
			c5.gridwidth = 1;
			c5.fill = GridBagConstraints.CENTER;
			JButton delete = new JButton("Supprimer");
			
			delete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					
						try {
							MainFrame.myDaybyday.removeUser(userDto.getUserId());
							setCurrentUserPanel(currentAccount,frame);
							frame.setVisible(true);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (WriteDeniedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (EntityNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
			});
			
			gridbag5.setConstraints(delete, c5);
			currentAccount.add(delete);
			
			
			JCheckBox uBox = new JCheckBox("U", true);
			gridbag5.setConstraints(uBox, c5);
			currentAccount.add(uBox);
			
			c5.gridwidth = GridBagConstraints.REMAINDER;
			JCheckBox adBox = new JCheckBox("AD", true);
			gridbag5.setConstraints(adBox, c5);
			currentAccount.add(adBox);
			
			ButtonGroup btgroup = new ButtonGroup();
			btgroup.add(uBox); btgroup.add(adBox);
			if (userDto.getProfile().compareTo("user") == 0) uBox.setSelected(true);
			else adBox.setSelected(true);
				
			
		}
	}
}