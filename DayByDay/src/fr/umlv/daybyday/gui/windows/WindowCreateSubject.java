/*
 * Created on 28 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationPK;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.util.exception.ConstraintException;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.DBDColor;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.FormationTreeModel;
/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowCreateSubject extends WindowAbstract {
	
	public static JTextField coursgroupeText;
	public static JTextField courshourText;
	public static JTextField TDgroupeText;
	public static JTextField TDhourText;
	public static JTextField TPgroupeText;
	public static JTextField TPhourText;

	/**
	 * This method builds the window witch create a new subject
	 * 
	 * @param contentPane the container of the window
	 * @param refName the reference name
	 * @param listTeacher the list of the teacher
	 */
	public static void createWindow(final JFrame frame, Object [] obj){
		final MainFrame mainframe = (MainFrame) obj[0];
		int height = 360;
		height = height + 50 *(((Object [])obj[1]).length - 1);
		initWindow(frame,"Nouvelle matière", 600, height);	
			Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
	
		// Panel section à  gauche en haut
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(0,2,0,20);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
	
		contentPane.setLayout(gridbag);
		
		JPanel sectionPanel = new JPanel(null);
		
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
	
		sectionPanel.setLayout(gridbag2);
		
		// Réference 
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		c2.insets =new Insets(5,15,5,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.CENTER;
	
		JLabel refLabel = new JLabel("Réference : ");
		gridbag2.setConstraints(refLabel, c2);
		sectionPanel.add(refLabel);
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		JLabel refNameLabel = new JLabel(((FormationElement)mainframe.getSelectedObject()).getNamePath());
		gridbag2.setConstraints(refNameLabel, c2);
		sectionPanel.add(refNameLabel);
		
		// Intitulé
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		
		JLabel nameLabel = new JLabel("Intitulé : ");
		gridbag2.setConstraints(nameLabel, c2);
		sectionPanel.add(nameLabel);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		final JTextField nameTextField = new JTextField();
		gridbag2.setConstraints(nameTextField, c2);
		sectionPanel.add(nameTextField);
		
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		JLabel infoLabel = new JLabel("Informations : ");
		gridbag2.setConstraints(infoLabel, c2);
		sectionPanel.add(infoLabel);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		final JTextArea infoList= new JTextArea("");
		
		JScrollPane infoScrollpane = new JScrollPane(infoList);		
		gridbag2.setConstraints(infoScrollpane, c2);
		sectionPanel.add(infoScrollpane);
		
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		c2.anchor = GridBagConstraints.LINE_START;
		JLabel responsableLabel = new JLabel("Responsable : ");
		gridbag2.setConstraints(responsableLabel, c2);
		sectionPanel.add(responsableLabel);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		final JComboBox responsableBox = new JComboBox((Object [])obj[1]);
		responsableBox.setRenderer(new ComboBoxFormationElementRenderer());
		gridbag2.setConstraints(responsableBox, c2);
		sectionPanel.add(responsableBox);
		gridbag.setConstraints(sectionPanel, c);
		contentPane.add(sectionPanel);
		
		//Panel en haut à droite 
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;		
		
		JPanel typePanel = createPanelType();
		gridbag.setConstraints(typePanel, c);
		contentPane.add(typePanel);
		
		//Panel des enseignant en bas 	
		c.insets =new Insets(0,10,0,20);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;	
		
		JPanel teacherPanel = new JPanel(null);		
		teacherPanel.setBorder(BorderFactory.createTitledBorder(" Enseignants associés : "));
	
		GridBagLayout gridbag3 = new GridBagLayout();
		GridBagConstraints c3 = new GridBagConstraints();
	
		teacherPanel.setLayout(gridbag3);
		
		//Teacher
		int i;
		ArrayList typeCourse = new ArrayList();
		typeCourse.add("cours");
		typeCourse.add("TD");
		typeCourse.add("TP");
	
		c3.anchor = GridBagConstraints.LINE_END;
		
		c3.gridwidth = 10;
		JLabel cLabel = new JLabel("Cours");
		gridbag3.setConstraints(cLabel, c3);
		teacherPanel.add(cLabel);
	
		c3.gridwidth = 11;
		cLabel = new JLabel("TD  ");
		gridbag3.setConstraints(cLabel, c3);
		teacherPanel.add(cLabel);
				
		c3.gridwidth = GridBagConstraints.REMAINDER;
		
		cLabel = new JLabel("TP  ");
		gridbag3.setConstraints(cLabel, c3);
		teacherPanel.add(cLabel);
		
		for(i =0; i<((Object [])obj[1]).length; i++){
			c3.weightx = 1; 
			c3.weighty = 1; 
			c3.insets =new Insets(5,4,5,5);
			c3.gridwidth = 3; 
			c3.fill = GridBagConstraints.HORIZONTAL;
			c3.anchor = GridBagConstraints.LINE_START;
	
			JLabel nameLabel2 = new JLabel(" Nom ");			
			nameLabel2.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			
			gridbag3.setConstraints(nameLabel2, c3);
			teacherPanel.add(nameLabel2);
			
			c3.gridwidth = 3;
			JLabel firstnameLabel = new JLabel(" Prénom ");
			firstnameLabel.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag3.setConstraints(firstnameLabel, c3);
			teacherPanel.add(firstnameLabel);
			
			c3.gridwidth = 3; 			
			c3.fill = GridBagConstraints.CENTER;
			c3.anchor = GridBagConstraints.LINE_END;
			JButton deleteButton = new JButton("Supprimer");
			//deleteButton.setBackground(DBDColor.getColor("DARK_GRAY"));
			gridbag3.setConstraints(deleteButton, c3);
			teacherPanel.add(deleteButton);
			
			int j=0;
			c3.gridwidth = 1;
			c3.anchor= GridBagConstraints.LINE_END;
			for(j =0; j<typeCourse.size(); j++){
				if(j == typeCourse.size()-1)				
					c3.gridwidth = GridBagConstraints.REMAINDER;
									
				JCheckBox typecheckBox = new JCheckBox();
				
				gridbag3.setConstraints(typecheckBox, c3);
				teacherPanel.add(typecheckBox);			
			}
		}
		
		c3.gridwidth = 1;
		
		c3.fill = GridBagConstraints.WEST;
		c3.anchor = GridBagConstraints.LINE_START;
		JLabel newLabel = new JLabel(" Nouveau : ");
		gridbag3.setConstraints(newLabel, c3);
		teacherPanel.add(newLabel);
		c3.gridwidth = 9;
		c3.fill= GridBagConstraints.HORIZONTAL;
		c3.insets =new Insets(5,4,5,30);
		JComboBox combo = new JComboBox((Object [])obj[1]);
		combo.setRenderer(new ComboBoxFormationElementRenderer());
		combo.setSize(new Dimension(100, combo.getHeight()));
		gridbag3.setConstraints(combo, c3);
		teacherPanel.add(combo);
		
		c3.gridwidth = GridBagConstraints.REMAINDER;
		c3.fill = GridBagConstraints.HORIZONTAL;
		JButton addButton = new JButton("Ajouter");
		//addButton.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag3.setConstraints(addButton, c3);
		teacherPanel.add(addButton);
		gridbag.setConstraints(teacherPanel, c);
		contentPane.add(teacherPanel);
		
		// Add button OK and Annuler
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				try {
				TeacherDto obj2 =  (TeacherDto)responsableBox.getSelectedItem();
				Object ref = ((FormationElement)mainframe.getSelectedObject()).getDto();
				
				SubjectDto newdto = null;
				FormationDto formglo = null;
				SectionDto father = null;
				if (ref instanceof FormationDto){
					FormationDto form = (FormationDto) ref;
					formglo = form;
					newdto = new SubjectDto(nameTextField.getText(),
							"GENERALE",
							form.getName(),
							form.getFormationYear(),
							obj2.getName(),
							obj2.getFirstname(),
							new Integer(courshourText.getText()),
							new Integer(TPhourText.getText()),
							new Integer(TDhourText.getText()),
							new Integer(coursgroupeText.getText()),
							new Integer(TPhourText.getText()),
							new Integer(TDhourText.getText()),
							infoList.getText(),
							new Boolean(true)
							);
				}
				if (ref instanceof SectionDto){
						father = (SectionDto) ref;
					
						formglo = MainFrame.myDaybyday.getFormation(new FormationPK(father.getFormationName(),father.getFormationYear()));
		
						newdto = new SubjectDto(nameTextField.getText(),
							father.getName(),
							father.getFormationName(),
							father.getFormationYear(),
							obj2.getName(),
							obj2.getFirstname(),
							new Integer(courshourText.getText()),
							new Integer(TPhourText.getText()),
							new Integer(TDhourText.getText()),
							new Integer(coursgroupeText.getText()),
							new Integer(TPhourText.getText()),
							new Integer(TDhourText.getText()),
							infoList.getText(),
							new Boolean(true)
							);

				}
				
				
				
					MainFrame.myDaybyday.createSubject(newdto);
					Object obj = mainframe.getModelSelectedObject();
					FormationTreeModel tree = (FormationTreeModel)obj;
					
					//SectionDto sectiondefault = new SectionDto("GENERALE",newdto.getName(),newdto.getFormationYear(),null,newdto.getTeacherName(),newdto.getTeacherFirstname(),"",new Boolean(true));
					//MainFrame.myDaybyday.createSection(sectiondefault);
					//mainframe.addFormationTabbePane(new Formation(newdto));
					if (father != null){
						MainFrame.myDaybyday.updateSection(father);
						//tree.nodesWereInserted((FormationElement)mainframe.getSelectedObject(),tree.);
					}
					MainFrame.myDaybyday.updateFormation(formglo);
					FormationElement father2 = ((FormationElement)mainframe.getSelectedObject()).getFather();
					while (father != null && father2.getFather() != null) father2 = father2.getFather();
					if (father2 == null) tree.nodeStructureChanged((FormationElement)mainframe.getSelectedObject());
					else tree.nodeStructureChanged(father2);
					framefinal.dispose();
				} catch (RemoteException e) {
					mainframe.showError(frame,e.toString());
				}catch (NumberFormatException e){
					mainframe.showError(frame,e.toString());
				}catch (ConstraintException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (StaleUpdateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteDeniedException e) {
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
		//addButtonValidation(contentPane, c, gridbag );
	}

	/**
	 * This method builds the window witch contains the
	 * informations about a section.
	 * 
	 * @param contentPane the container of the window
	 * @param refName the name reference
	 * @param listTeacher the list of the teacher
	 */
	public static void createWindow2(final JFrame frame,Object [] obj){
		initWindow(frame,"Nouvelle matière", 400, 250);
		final MainFrame mainframe = (MainFrame) obj[0];
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
	
		JLabel refLabel = new JLabel(" Réference : ");
		gridbag.setConstraints(refLabel, c);
		contentPane.add(refLabel);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel refNameLabel = new JLabel(((FormationElement)mainframe.getSelectedObject()).getNamePath());
		gridbag.setConstraints(refNameLabel, c);
		contentPane.add(refNameLabel);
		
		//Intitulé
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel nameLabel = new JLabel("  Intitulé : ");
		gridbag.setConstraints(nameLabel, c);
		contentPane.add(nameLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JTextField nameTextField = new JTextField();
		gridbag.setConstraints(nameTextField, c);
		contentPane.add(nameTextField);
		
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
		
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.LINE_START;
		JLabel responsableLabel = new JLabel("  Responsable : ");
		gridbag.setConstraints(responsableLabel, c);
		contentPane.add(responsableLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		final JComboBox responsableBox = new JComboBox((Object [])obj[1]);
		responsableBox.setRenderer(new ComboBoxFormationElementRenderer());
		gridbag.setConstraints(responsableBox, c);
	
		contentPane.add(responsableBox);
	
		// Add button OK and Annuler
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				TeacherDto obj2 =  (TeacherDto)responsableBox.getSelectedItem();
				Object ref = ((FormationElement)mainframe.getSelectedObject()).getDto();
				
				SectionDto newdto = null;
				FormationDto formglo = null;
				SectionDto father = null;
				if (ref instanceof FormationDto){
					FormationDto form = (FormationDto) ref;
					formglo = form;
					newdto = new SectionDto(nameTextField.getText(),
							form.getName(),
							form.getFormationYear(),
							"GENERALE",
							obj2.getName(),
							obj2.getFirstname(),
							infoList.getText(),
							new Boolean(true)
							);
				}
				if (ref instanceof SectionDto){
						father = (SectionDto) ref;
					try {
						formglo = MainFrame.myDaybyday.getFormation(new FormationPK(father.getFormationName(),father.getFormationYear()));
					} catch (RemoteException e1) {


					} 
					newdto = new SectionDto(nameTextField.getText(),
							father.getFormationName(),
							father.getFormationYear(),
							father.getName(),
							obj2.getName(),
							obj2.getFirstname(),
							infoList.getText(),
							new Boolean(true)
							);
				}
				
				try {
				
					MainFrame.myDaybyday.createSection(newdto);
					Object obj = mainframe.getModelSelectedObject();
					FormationTreeModel tree = (FormationTreeModel)obj;
					
					//SectionDto sectiondefault = new SectionDto("GENERALE",newdto.getName(),newdto.getFormationYear(),null,newdto.getTeacherName(),newdto.getTeacherFirstname(),"",new Boolean(true));
					//MainFrame.myDaybyday.createSection(sectiondefault);
					//mainframe.addFormationTabbePane(new Formation(newdto));
					if (father != null){
						MainFrame.myDaybyday.updateSection(father);
						//tree.nodesWereInserted((FormationElement)mainframe.getSelectedObject(),tree.);
					}
					MainFrame.myDaybyday.updateFormation(formglo);
					FormationElement father2 = ((FormationElement)mainframe.getSelectedObject()).getFather();
					while (father != null && father2.getFather() != null) father2 = father2.getFather();
					if (father2 == null) tree.nodeStructureChanged((FormationElement)mainframe.getSelectedObject());
					else tree.nodeStructureChanged(father2);
					framefinal.dispose();
				} catch (RemoteException e) {
					mainframe.showError(frame,e.toString());
				} catch (ConstraintException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (StaleUpdateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteDeniedException e) {
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
		//addButtonValidation(contentPane, c, gridbag );
	}
}
