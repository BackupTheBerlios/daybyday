
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.CourseDetail;

/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to create 
 * availibilities teacher
 */
public class WindowDetailsCourse extends WindowAbstract {




	

	public static void createWindow(final JFrame frame,Object [] obj){
		final MainFrame mainframe = (MainFrame) obj[0];
		initWindow(frame,"Niveau de détails des cours", 550, 350);

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
		
		JPanel formation = new JPanel(null);
		formation.setBorder(BorderFactory.createTitledBorder(" Formation : "));
		
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();	
		formation.setLayout(gridbag2);
	
		c2.weightx = 1;
		c2.weighty = 1;
		c2.insets =new Insets(5,5,5,5);
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		c2.anchor = GridBagConstraints.LINE_START;

			final JCheckBox formationname = new JCheckBox(" Intitulé ", CourseDetail.formId);		
			gridbag2.setConstraints(formationname, c2);
			formation.add(formationname);
			 
			final JCheckBox formationinfo = new JCheckBox(" Information ", CourseDetail.formInfo);
			gridbag2.setConstraints(formationinfo, c2);
			formation.add(formationinfo);
			
			JLabel blank1 = new JLabel("                                       ");
			gridbag2.setConstraints(blank1, c2);
			formation.add(blank1);
			JLabel blank2 = new JLabel();
			gridbag2.setConstraints(blank2, c2);
			formation.add(blank2);
		

		gridbag.setConstraints(formation, c);
		contentPane.add(formation);
		
		
		JPanel teacher = new JPanel(null);
		teacher.setBorder(BorderFactory.createTitledBorder(" Enseignant : "));
		
		GridBagLayout gridbag3 = new GridBagLayout();
		GridBagConstraints c3 = new GridBagConstraints();	
		teacher.setLayout(gridbag3);
	
		c3.weightx = 1;
		c3.weighty = 1;
		c3.insets =new Insets(5,5,5,5);
		c3.gridwidth = 1; 
		c3.fill = GridBagConstraints.CENTER;
		c3.anchor = GridBagConstraints.LINE_START;
		

			final JCheckBox teachername = new JCheckBox(" Nom ", CourseDetail.teacherName);
			gridbag3.setConstraints(teachername, c3);
			teacher.add(teachername);
	
			final JCheckBox teacherfirstname = new JCheckBox(" Prénom ", CourseDetail.teacherFirstname);
			gridbag3.setConstraints(teacherfirstname, c3);
			teacher.add(teacherfirstname);
	
			final JCheckBox teacheroffice = new JCheckBox(" Bureau ", CourseDetail.teacherOffice);
			gridbag3.setConstraints(teacheroffice, c3);
			teacher.add(teacheroffice);
			blank1.setText("                ");
			gridbag3.setConstraints(blank1, c3);
			teacher.add(blank1);
		
		gridbag.setConstraints(teacher, c);
		contentPane.add(teacher);
		
		
		JPanel material = new JPanel(null);
		material.setBorder(BorderFactory.createTitledBorder(" Matériel : "));
		
		GridBagLayout gridbag4 = new GridBagLayout();
		GridBagConstraints c4 = new GridBagConstraints();	
		material.setLayout(gridbag4);
	
		c4.weightx = 1;
		c4.weighty = 1;
		c4.insets =new Insets(5,5,5,5);
		c4.gridwidth = 1; 
		c4.fill = GridBagConstraints.CENTER;
		c4.anchor = GridBagConstraints.LINE_START;
		
			final JCheckBox materialid = new JCheckBox(" Identifiant ", CourseDetail.equipmentName);
			gridbag4.setConstraints(materialid, c4);
			material.add(materialid);
	
			final JCheckBox materialdesc = new JCheckBox(" Description ", CourseDetail.equipmentDesc);
			gridbag4.setConstraints(materialdesc, c4);
			material.add(materialdesc);
			
			blank1.setText("                                       ");
			gridbag4.setConstraints(blank1, c4);
			material.add(blank1);
			JLabel blank4 = new JLabel("                     ");
			gridbag4.setConstraints(blank4, c4);
			material.add(blank4);
		

		gridbag.setConstraints(material, c);
		contentPane.add(material);
		
		
		
		JPanel room = new JPanel(null);
		room.setBorder(BorderFactory.createTitledBorder(" Salle : "));
		
		GridBagLayout gridbag5 = new GridBagLayout();
		GridBagConstraints c5 = new GridBagConstraints();	
		room.setLayout(gridbag5);
	
		c5.weightx = 1;
		c5.weighty = 1;
		c5.insets =new Insets(5,5,5,5);
		c5.gridwidth = 1; 
		c5.fill = GridBagConstraints.CENTER;
		c5.anchor = GridBagConstraints.LINE_START;
		

			final JCheckBox roomid = new JCheckBox(" Identifiant ", CourseDetail.roomName);
			gridbag5.setConstraints(roomid, c5);
			room.add(roomid);
	
			final JCheckBox roomdesc = new JCheckBox(" Description ", CourseDetail.roomInfo);
			gridbag5.setConstraints(roomdesc, c5);
			room.add(roomdesc);
			
			blank1 = new JLabel("                                       ");
			gridbag5.setConstraints(blank1, c5);
			room.add(blank1);
			JLabel blank5 = new JLabel("                     ");
			gridbag5.setConstraints(blank5, c5);
			room.add(blank5);
		

		gridbag.setConstraints(room, c);
		contentPane.add(room);
		
		
		JPanel subject = new JPanel(null);
		subject.setBorder(BorderFactory.createTitledBorder(" Matière : "));
		
		GridBagLayout gridbag6 = new GridBagLayout();
		GridBagConstraints c6 = new GridBagConstraints();	
		subject.setLayout(gridbag6);
	
		c6.weightx = 1;
		c6.weighty = 1;
		c6.insets =new Insets(6,6,6,6);
		c6.gridwidth = 1; 
		c6.fill = GridBagConstraints.CENTER;
		c6.anchor = GridBagConstraints.LINE_START;
		

			final JCheckBox subjectname = new JCheckBox(" Intitulé ", CourseDetail.subjectName);
			gridbag6.setConstraints(subjectname, c6);
			subject.add(subjectname);
	
			final JCheckBox subjecttype = new JCheckBox(" Type ", CourseDetail.subjectType);
			gridbag6.setConstraints(subjecttype , c6);
			subject.add(subjecttype );
	
			final JCheckBox subjectgrp = new JCheckBox(" Groupe ", CourseDetail.subjectGroupe);
			gridbag6.setConstraints(subjectgrp, c6);
			subject.add(subjectgrp );
			blank1 = new JLabel("                         ");
			gridbag6.setConstraints(blank1, c6);
			subject.add(blank1);			

		gridbag.setConstraints(subject, c);
		contentPane.add(subject);
		
		
		JPanel generale = new JPanel(null);
		generale.setBorder(BorderFactory.createTitledBorder(" Générale : "));
		
		GridBagLayout gridbag7 = new GridBagLayout();
		GridBagConstraints c7 = new GridBagConstraints();	
		generale.setLayout(gridbag7);
	
		c7.weightx = 1;
		c7.weighty = 1;
		c7.insets =new Insets(7,7,7,7);
		c7.gridwidth = 1; 
		c7.fill = GridBagConstraints.CENTER;
		c7.anchor = GridBagConstraints.LINE_START;
		
			final JCheckBox generalecolor = new JCheckBox(" Couleur ", CourseDetail.coursColor);
			gridbag7.setConstraints(generalecolor, c7);
			generale.add(generalecolor);
			
			final JCheckBox generaleperiod = new JCheckBox(" Période ", CourseDetail.coursPeriode);
			gridbag7.setConstraints(generaleperiod, c7);
			generale.add(generaleperiod);
			
			final JCheckBox generalefrq= new JCheckBox(" Fréquence ", CourseDetail.coursFrequence);
			gridbag7.setConstraints(generalefrq, c7);
			generale.add(generalefrq );
			
			final JCheckBox generalehour = new JCheckBox(" Horaire ", CourseDetail.coursHour);
			gridbag7.setConstraints(generalehour, c7);
			generale.add(generalehour);			
		

		gridbag.setConstraints(generale, c);
		contentPane.add(generale);
		
//		 Add button OK and Annuler
		
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				
				try{
				CourseDetail.formId = formationname.isSelected();
				CourseDetail.formInfo = formationinfo.isSelected();
				
				CourseDetail.teacherName = teachername.isSelected();
				CourseDetail.teacherFirstname = teacherfirstname.isSelected();
				CourseDetail.teacherOffice = teacheroffice.isSelected();
				
				CourseDetail.equipmentName = materialid.isSelected();
				CourseDetail.equipmentDesc = materialdesc.isSelected();
				
				CourseDetail.roomName = roomid.isSelected();
				CourseDetail.roomInfo = roomdesc.isSelected();
				
				CourseDetail.subjectName = subjectname.isSelected();
				CourseDetail.subjectType = subjecttype.isSelected();
				CourseDetail.subjectGroupe = subjectgrp.isSelected();
				
				CourseDetail.coursColor = generalecolor.isSelected();
				CourseDetail.coursPeriode = generaleperiod.isSelected();
				CourseDetail.coursFrequence = generalefrq.isSelected();
				CourseDetail.coursHour = generalehour.isSelected();
				
				mainframe.refreshAllTimeTable();
				frame.dispose();
				}catch(NumberFormatException e){
					mainframe.showError(frame,"Paramètres saisies incorects ou manquants");
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
	}

	
}