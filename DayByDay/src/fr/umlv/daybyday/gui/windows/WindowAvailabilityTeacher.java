package fr.umlv.daybyday.gui.windows;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.umlv.daybyday.ejb.constraint.equipment.EquipmentConstraintDto;
import fr.umlv.daybyday.ejb.constraint.room.RoomConstraintDto;
import fr.umlv.daybyday.ejb.constraint.teacher.TeacherConstraintDto;
import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.util.exception.ConstraintException;
import fr.umlv.daybyday.ejb.util.exception.CourseConfusionException;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.ejb.util.exception.ResourceUnavailableException;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.TimeslotException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.DBDColor;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.Grid;
import fr.umlv.daybyday.model.Section;

/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to manage 
 * the avaibility teacher.
 */
public class WindowAvailabilityTeacher extends WindowAbstract {

	/**
	 * This method builds the window to permit to adjust
	 * the teacher availability.  
	 * 
	 * @param frame the frame of the window 
	 * @param obj the object
	 */
	public static int TEACHER = 0;
	public static int ROOM = 1;
	public static int EQUIP = 2;
	
	public static void createWindow(final JFrame frame, Object[] obj){
		try {
		final MainFrame mainframe = (MainFrame) obj[0];
		final int typeObjectRef = ((Integer) obj[1]).intValue();
		
		final GregorianCalendar cal = new GregorianCalendar();

        
		if (typeObjectRef == TEACHER) initWindow(frame,"Gestion des disponibilités d'un enseignant", 550, 300, mainframe.getFrameX(), mainframe.getFrameY());
		if (typeObjectRef == ROOM) initWindow(frame,"Gestion des disponibilités d'une salle", 550, 300, mainframe.getFrameX(), mainframe.getFrameY());
		if (typeObjectRef == EQUIP) initWindow(frame,"Gestion des disponibilités d'un équipement", 550, 300, mainframe.getFrameX(), mainframe.getFrameY());
	
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		
		c.insets =new Insets(0,5,0,5);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel teacher = new JLabel(" Enseignant : ");
		if (typeObjectRef == TEACHER) teacher.setText("Enseignant : ");
		if (typeObjectRef == ROOM) teacher.setText("Salle : ");
		if (typeObjectRef == EQUIP) teacher.setText("Equipement : ");
		gridbag.setConstraints(teacher, c);
		contentPane.add(teacher);
		
		Object [] Objectref = null;
		if (typeObjectRef == TEACHER) Objectref = MainFrame.myDaybyday.getAllTeachers().toArray();
		if (typeObjectRef == ROOM) Objectref = MainFrame.myDaybyday.getAllRooms().toArray();
		if (typeObjectRef == EQUIP) Objectref = MainFrame.myDaybyday.getAllEquipments().toArray();
		
		
		c.gridwidth = GridBagConstraints.REMAINDER; 
		final JComboBox teacherCombo = new JComboBox(Objectref);
		teacherCombo.setRenderer(new ComboBoxFormationElementRenderer());
		gridbag.setConstraints(teacherCombo, c);
		contentPane.add(teacherCombo);
		
		
		
		JPanel availability = new JPanel();
		

		availability.setBorder(BorderFactory.createTitledBorder(" Disponibilités : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		availability.setLayout(gridbag2);
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = GridBagConstraints.REMAINDER; 
		
		c2.insets =new Insets(5,0,5,0);
		c2.anchor = GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel dateBegin = new JLabel("    Date de début        Date de fin" +
				"                                                             D" +
				"                          I");
		gridbag2.setConstraints(dateBegin, c2);
		availability.add(dateBegin);
		

		c2.insets =new Insets(5,5,5,5); 
		c2.anchor = GridBagConstraints.LINE_START;	
		
		final JPanel date = new JPanel();
		final JScrollPane dateScrollpane = new JScrollPane(date);
		
		//TODO Faire une bouble sur les dispo et non prof
		teacherCombo.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				//TeacherDto dto = (TeacherDto)teacherCombo.getSelectedItem();

				date.removeAll();
				GridBagLayout gridbag3 = new GridBagLayout();
				GridBagConstraints c3 = new GridBagConstraints();
				date.setLayout(gridbag3);
				
				c3.weightx = 1; 
				c3.weighty = 1; 
				c3.gridwidth = 1; 
				
				c3.insets =new Insets(0,5,0,5);
				c3.anchor = GridBagConstraints.LINE_START;
				c3.fill = GridBagConstraints.HORIZONTAL;		
				
				Object ref = teacherCombo.getSelectedItem();
				
				Object [] Objectref = null;
				try {
				if (typeObjectRef == TEACHER)
						Objectref = MainFrame.myDaybyday.getConstraintsOfTeacher(((TeacherDto)ref).getTeacherId()).toArray();
					
				if (typeObjectRef == ROOM) Objectref = MainFrame.myDaybyday.getConstraintsOfRoom(((RoomDto)ref).getRoomId()).toArray();
				if (typeObjectRef == EQUIP) Objectref = MainFrame.myDaybyday.getConstraintsOfEquipment(((EquipmentDto)ref).getEquipmentId()).toArray();
				
				for(int i=0; i<Objectref.length; i++ ){
						
						c3.gridwidth = 3; 
						c3.fill = GridBagConstraints.HORIZONTAL;
						JLabel begin = new JLabel("debut");
						
						begin.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
						gridbag3.setConstraints(begin, c3);
						date.add(begin);
						
						c3.gridwidth = 3;
						JLabel end = new JLabel("fin");
						end.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
						gridbag3.setConstraints(end, c3);
						date.add(end);
						
						String desc = "";
						if (typeObjectRef == TEACHER){
							begin.setText(((TeacherConstraintDto)Objectref[i]).getStartDate() + "");
							end.setText(((TeacherConstraintDto)Objectref[i]).getEndDate() + "");
							desc = ((TeacherConstraintDto)Objectref[i]).getDescription();
						}
						if (typeObjectRef == ROOM) {
							begin.setText(((RoomConstraintDto)Objectref[i]).getStartDate() + "");
							end.setText(((RoomConstraintDto)Objectref[i]).getEndDate() + "");
							desc = ((RoomConstraintDto)Objectref[i]).getDescription();
						
						}
						if (typeObjectRef == EQUIP) {
							begin.setText(((EquipmentConstraintDto)Objectref[i]).getStartDate() + "");
							end.setText(((EquipmentConstraintDto)Objectref[i]).getEndDate() + "");	
							desc = ((EquipmentConstraintDto)Objectref[i]).getDescription();
						}
					
						c3.gridwidth = 1;
						c3.fill = GridBagConstraints.CENTER;
						JButton delete = new JButton("Supprimer");
						final int j = i;
						final Object [] Objectreffinal = Objectref;
						delete.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								
									try {
										if (typeObjectRef == TEACHER) MainFrame.myDaybyday.removeTeacherConstraint(((TeacherConstraintDto)Objectreffinal[j]).getTeacherConstraintId());
										if (typeObjectRef == ROOM) MainFrame.myDaybyday.removeRoomConstraint(((RoomConstraintDto)Objectreffinal[j]).getRoomConstraintId());
										if (typeObjectRef == EQUIP) MainFrame.myDaybyday.removeEquipmentConstraint(((EquipmentConstraintDto)Objectreffinal[j]).getEquipmentConstraintId());
										teacherCombo.setSelectedItem(teacherCombo.getSelectedItem());
										dateScrollpane.getViewport().setVisible(true);
										//frame.setVisible(true);
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
						//delete.setBackground(DBDColor.getColor("DARK_GRAY"));
						gridbag3.setConstraints(delete, c3);
						date.add(delete);
						
						JCheckBox dBox = new JCheckBox("", true);
						gridbag3.setConstraints(dBox, c3);
						date.add(dBox);
						
						c3.gridwidth = GridBagConstraints.REMAINDER;
						JCheckBox iBox = new JCheckBox("", true);
						gridbag3.setConstraints(iBox, c3);
						date.add(iBox);
						
						ButtonGroup boxgroupe = new ButtonGroup();
						boxgroupe.add(dBox);boxgroupe.add(iBox);
						dBox.setEnabled(false);iBox.setEnabled(false);
						if (desc.compareTo("Disponibilité") == 0) dBox.setSelected(true);
						else iBox.setSelected(true);
						
					}	
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					date.setVisible(true);
					dateScrollpane.setVisible(true);

					frame.setVisible(true);
				}
			
		});
		
		dateScrollpane.setPreferredSize(new Dimension(500,60));
		dateScrollpane.setMinimumSize(new Dimension(500,60));
		gridbag2.setConstraints(dateScrollpane, c2);
		availability.add(dateScrollpane);
		teacherCombo.setSelectedItem(teacherCombo.getSelectedItem());
		
		JPanel newAvailability = new JPanel();
		
		GridBagLayout gridbag5 = new GridBagLayout();
		GridBagConstraints c5 = new GridBagConstraints();
		newAvailability.setBorder(BorderFactory.createTitledBorder(" Nouveau : "));
		newAvailability.setLayout(gridbag5);
		
		c5.weightx = 1; 
		c5.weighty = 1; 
		c5.gridwidth = 1; 
		
		c5.insets =new Insets(0,5,0,5);
		c5.anchor = GridBagConstraints.LINE_START;
		c5.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel firstLabel = new JLabel(" Début : ");
		gridbag5.setConstraints(firstLabel, c5);
		newAvailability.add(firstLabel);
	
		final JTextField firstTextField = new JTextField();
		firstTextField.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(firstTextField, c5);
		newAvailability.add(firstTextField);
		
		JLabel separatorLabel = new JLabel("/");
		gridbag5.setConstraints(separatorLabel, c5);
		newAvailability.add(separatorLabel);
		
		c5.anchor = GridBagConstraints.FIRST_LINE_START;
		final JTextField fTextField = new JTextField();
		fTextField.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(fTextField, c5);
		newAvailability.add(fTextField);
		
		c5.anchor = GridBagConstraints.CENTER;
		final JTextField secondTextField = new JTextField();
		gridbag5.setConstraints(secondTextField, c5);
		newAvailability.add(secondTextField);
		
		JLabel separatorSecondLabel = new JLabel("h");
		gridbag5.setConstraints(separatorSecondLabel, c5);
		newAvailability.add(separatorSecondLabel);
		
		final JTextField fSecondTextField = new JTextField();
		fSecondTextField.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(fSecondTextField, c5);
		newAvailability.add(fSecondTextField);
		
		c5.gridwidth = 2;
		JLabel separatorSecondLabel3 = new JLabel("Fréqence : ");
		gridbag5.setConstraints(separatorSecondLabel3, c5);
		newAvailability.add(separatorSecondLabel3);
		
		c5.gridwidth = 1;
		c5.gridwidth = GridBagConstraints.REMAINDER;
		//c5.fill = GridBagConstraints.FIRST_LINE_START;
		final JTextField fSecondTextField3 = new JTextField("1");
		fSecondTextField3.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(fSecondTextField3, c5);
		newAvailability.add(fSecondTextField3);
		
		
		/*JLabel blank= new JLabel();
		gridbag5.setConstraints(blank, c5);
		newAvailability.add(blank);
		*/
		c5.gridwidth = 1;
		c5.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel endLabel = new JLabel(" Fin : ");
		gridbag5.setConstraints(endLabel, c5);
		newAvailability.add(endLabel);
		
		final JTextField secondLineTextField = new JTextField();
		secondLineTextField.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(secondLineTextField, c5);
		newAvailability.add(secondLineTextField);
		
		JLabel separatorSecondLineLabel = new JLabel("/");
		gridbag5.setConstraints(separatorSecondLineLabel, c5);
		newAvailability.add(separatorSecondLineLabel);
				
		final JTextField fSecondLineTextField = new JTextField();
		fSecondLineTextField.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(fSecondLineTextField, c5);
		newAvailability.add(fSecondLineTextField);
		
		final JTextField secondSecondLineTextField = new JTextField();
		secondSecondLineTextField.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(secondSecondLineTextField, c5);
		newAvailability.add(secondSecondLineTextField);
		
		JLabel separatorSecondLineSecondLabel = new JLabel("h");
		gridbag5.setConstraints(separatorSecondLineSecondLabel, c5);
		newAvailability.add(separatorSecondLineSecondLabel);
	
		final JTextField fSecondLineSecondTextField = new JTextField();
		fSecondLineSecondTextField.setPreferredSize(new Dimension(30,20));
		gridbag5.setConstraints(fSecondLineSecondTextField, c5);
		newAvailability.add(fSecondLineSecondTextField);
		
		c5.gridwidth = 1;
		c5.gridheight = 5;
		final JCheckBox dBox = new JCheckBox("D", true);
		gridbag5.setConstraints(dBox, c5);
		newAvailability.add(dBox);
		
		JCheckBox iBox = new JCheckBox("I", true);
		gridbag5.setConstraints(iBox, c5);
		newAvailability.add(iBox);
		
		ButtonGroup boxgroupe = new ButtonGroup();
		boxgroupe.add(dBox);boxgroupe.add(iBox);
		
		JButton add = new JButton("Ajouter");
		add.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				try{
				int bghour = Integer.parseInt(secondTextField.getText());
				int bgmin = Integer.parseInt(fSecondTextField.getText());
				int endhour = Integer.parseInt(secondSecondLineTextField.getText());
				int endmin = Integer.parseInt(fSecondLineSecondTextField.getText());
				
				int bgday =  Integer.parseInt(firstTextField.getText());
				int bgmonth = Integer.parseInt(fTextField.getText());
				int endday = Integer.parseInt(secondLineTextField.getText());
				int endmonth = Integer.parseInt(fSecondLineTextField.getText());
				int freq = 1;//Integer.parseInt(firstTextField11.getText());

				final GregorianCalendar cal2 = new GregorianCalendar();
				cal2.set(2005, endmonth - 1, endday);
				cal.set(2005, bgmonth - 1, bgday);
				//cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR) + (index -1));
		        int nbweek = (cal2.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR))/7;
		        int daycpt = 0; 
		        
		        String typeDispo = "";
		        if (dBox.isSelected()) typeDispo = "Disponibilité";
		        else typeDispo = "Indisponibilité";
				for (int i = 0; i < nbweek + 1; ++i){
					cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR) + (daycpt));
					if (i%freq == 0){
//System.out.println(cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR));

			           Timestamp startDate = WindowCreateCourse.toTimeStamp(2005,cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),bghour,bgmin,00);
			           Timestamp endDate = WindowCreateCourse.toTimeStamp(2005,cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),endhour,endmin,00);
			           try {
			           if (typeObjectRef == TEACHER)
						
							MainFrame.myDaybyday.createTeacherConstraint(new TeacherConstraintDto(
									((TeacherDto)(teacherCombo.getSelectedItem())).getTeacherId(),
									startDate,
									endDate,
									typeDispo
							));
						
						
			   			if (typeObjectRef == ROOM)  
			   				MainFrame.myDaybyday.createRoomConstraint(new RoomConstraintDto(
									((RoomDto)(teacherCombo.getSelectedItem())).getRoomId(),
									startDate,
									endDate,
									typeDispo
						));
			   			
			   			if (typeObjectRef == EQUIP) 
			   			 MainFrame.myDaybyday.createEquipmentConstraint(new EquipmentConstraintDto(
								((EquipmentDto)(teacherCombo.getSelectedItem())).getEquipmentId(),
								startDate,
								endDate,
								typeDispo
							));
			           } catch (Exception e) {
			           	mainframe.showError("ATTENTION : CREATION D UNE CONTRAINTE IMPOSSIBLE : une contrainte de temps en recouvre une autre");
						}
			          
					
					
						/*if (obj2 != null)
						MainFrame.myDaybyday.addTeacherToCourse(obj2.getTeacherId(),newdto.getCourseId());
						if (obj3 != null)
							MainFrame.myDaybyday.addEquipmentToCourse(obj3.getEquipmentId(),newdto.getCourseId());
						if (obj4 != null)	
							MainFrame.myDaybyday.addRoomToCourse(obj4.getRoomId(),newdto.getCourseId());
					*/}
					if (i == 0)
						daycpt += 7;
				}
				teacherCombo.setSelectedItem(teacherCombo.getSelectedItem());
			}
			
				catch(NumberFormatException e1){
					mainframe.showError(frame,"Paramètres saisies incorects ou manquants");
				}
			}
		});
		//add.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag5.setConstraints(add, c5);
		newAvailability.add(add);
		
		
		gridbag2.setConstraints(newAvailability, c2);
		availability.add(newAvailability);
		
		c2.anchor = GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.insets =new Insets(10,0,0,0);
	
		gridbag.setConstraints(availability,c);
		contentPane.add(availability);

//		 Add button OK and Annuler
		
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				framefinal.dispose();
			}
			
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridwidth = GridBagConstraints.RELATIVE;
		gridbag.setConstraints(ok, c);
		contentPane.add(ok);
		frame.setVisible(true);
		}
		catch (Exception e){
			
		}
	}
}