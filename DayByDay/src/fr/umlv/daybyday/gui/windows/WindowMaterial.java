
/*
 * Created on 28 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.util.exception.ConstraintException;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowMaterial extends WindowAbstract {

	public final static int EQUIP = 0;
	public final static int ROOM = 1;
	
	/**
	 * This method creates the window which permits to 
	 * create an equipment
	 * 
	 * @param frame the frame of the window
	 * @param obj. In position 0, the mainframe,
	 * in position 1 the type, 
	 */
	public static void createWindow(final JFrame frame,Object [] obj){
		final MainFrame mainframe = (MainFrame) obj[0];
		final Integer type = (Integer)obj[1]; 
		if (type.intValue() == EQUIP)
		initWindow(frame,"Nouveau matériel", 400, 250);
		if (type.intValue() == ROOM)
			initWindow(frame,"Nouvelle Salle", 400, 250);
		
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
		final JTextField idTextField = new JTextField();
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
		final JTextField batimentTextField = new JTextField();
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
		final JTextField siteTextField = new JTextField();
		gridbag.setConstraints(siteTextField, c);
		contentPane.add(siteTextField);
		
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		JLabel infoLabel = new JLabel("  Informations : ");
		gridbag.setConstraints(infoLabel, c);
		contentPane.add(infoLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		final JTextArea infoList= new JTextArea("\n\n");
		
		JScrollPane infoScrollpane = new JScrollPane(infoList);
		gridbag.setConstraints(infoScrollpane, c);
		contentPane.add(infoScrollpane);
	
//		Add button ok and annuler
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				
			if (type.intValue() == ROOM){
				//("1A001","copernic","mlv","description....", new Integer(50), new Boolean(true));
					RoomDto newdto = new RoomDto(
							idTextField.getText(),
							batimentTextField.getText(),
							siteTextField.getText(),
							infoList.getText(),
							new Integer(50),
							new Boolean(true)	
					);
						
					
				
					try{
				
						MainFrame.myDaybyday.createRoom(newdto);
						mainframe.showError(frame,"Nouvelle salle créée : \n"+
												"\tId : " + idTextField.getText());
						framefinal.dispose();
				
					} catch (RemoteException e) {
						mainframe.showError(frame,"Champs non renseigné ou salle déja exisante");
					}
			}
			
			if (type.intValue() == EQUIP){
				//("1A001","copernic","mlv","description....", new Integer(50), new Boolean(true));
					EquipmentDto newdto = new EquipmentDto(
							idTextField.getText(),
							batimentTextField.getText(),
							siteTextField.getText(),
							infoList.getText(),
							new Boolean(true)	
					);
						
					
				
					try{
				
						MainFrame.myDaybyday.createEquipment(newdto);
						mainframe.showError(frame,"Nouvel equipement créée : \n"+
												"\tId : " + idTextField.getText());
						framefinal.dispose();
				
					} catch (RemoteException e) {
						mainframe.showError(frame,"Champs non renseigné ou équipement déja exisant");
					} catch (ConstraintException e) {
						mainframe.showError(frame,"Champs non renseigné ou équipement déja exisant");
					}		
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

