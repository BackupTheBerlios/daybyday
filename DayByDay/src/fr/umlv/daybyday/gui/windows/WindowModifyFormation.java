package fr.umlv.daybyday.gui.windows;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.Formation;


/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to create 
 * a new formation
 */
public class WindowModifyFormation extends WindowAbstract {

	/**
	 * This method buildsthe window which permits to create 
	 * a new formation
	 * 
	 * @param frame the frame of the window
	 * @param obj the table of object . In position 0, the mainframe,
	 * in position 1 the formation, in position 2 the teacher,
	 * in position 3 the rooms 
	 */	
	public static void createWindow(JFrame frame,Object [] obj){
		final MainFrame mainframe = (MainFrame) obj[0];
		
		Formation form = (Formation)mainframe.getSelectedObject();
		FormationDto oldformdto = (FormationDto)form.getDto();
		
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		initWindow(frame,"Modifier formation", 430, 350, mainframe.getFrameX(), mainframe.getFrameY());
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(5,20,5,20);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.CENTER;
		
		JLabel nameLabel = new JLabel("Intitulé : ");
		gridbag.setConstraints(nameLabel, c);
		contentPane.add(nameLabel);
	
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JTextField nameTextField = new JTextField(oldformdto.getName());
		gridbag.setConstraints(nameTextField, c);
		contentPane.add(nameTextField);
	
		JPanel formationPanel = new JPanel(null);
		formationPanel.setBorder(BorderFactory.createTitledBorder("  Formation :  "));
	
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
	
		formationPanel.setLayout(gridbag2);
	
		c2.weightx = 1;
		c2.weighty = 1;
		c2.insets =new Insets(5,2,5,2);
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		c2.anchor = GridBagConstraints.LINE_START;
		
		Object [] teacherlist = new Object [0]; 
		
		JLabel importLabel = new JLabel("  Importer : ");
		gridbag2.setConstraints(importLabel, c2);
		formationPanel.add(importLabel);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
	
		final JComboBox importBox = new JComboBox((Object [])obj[1]);
		importBox.setRenderer(new ListCellRenderer(){

			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				
				if (arg1 instanceof FormationDto){
					FormationDto dto = (FormationDto)arg1;
					return new JLabel(dto.getName() + " " + dto.getFormationYear());
				}
				if (arg1 instanceof TeacherDto){
					TeacherDto dto = (TeacherDto)arg1;
					return new JLabel(dto.getName());
				}
				if (arg1 instanceof RoomDto){
					RoomDto dto = (RoomDto)arg1;
					return new JLabel(dto.getName() + " " + dto.getArea() + " " + dto.getBuilding());
				}
				if (arg1 instanceof EquipmentDto){
					EquipmentDto dto = (EquipmentDto)arg1;
					return new JLabel(dto.getName());
				}
				return new JLabel();
			}
			
		});
		gridbag2.setConstraints(importBox, c2);
		formationPanel.add(importBox);
				
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		final JLabel nameLabel2 = new JLabel("  Intitulé : ");
		gridbag2.setConstraints(nameLabel2, c2);
		formationPanel.add(nameLabel2);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		final JTextField nameTextField2 = new JTextField(oldformdto.getName());
		gridbag2.setConstraints(nameTextField2, c2);
		formationPanel.add(nameTextField2);
		
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		final JLabel yearLabel = new JLabel("  Année : ");
		gridbag2.setConstraints(yearLabel, c2);
		formationPanel.add(yearLabel);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		final JTextField yearTextField = new JTextField(oldformdto.getFormationYear());
		gridbag2.setConstraints(yearTextField, c2);
		formationPanel.add(yearTextField);
		

		
		// Information panel
		c2.gridwidth = 1;
		c2.fill = GridBagConstraints.CENTER;
	
		final JLabel infoLabel = new JLabel("  Informations : ");
		gridbag2.setConstraints(infoLabel, c2);
		formationPanel.add(infoLabel);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		final JTextArea infoList= new JTextArea(oldformdto.getDescription());		
		JScrollPane infoScrollpane = new JScrollPane(infoList);		
		gridbag2.setConstraints(infoScrollpane, c2);
		formationPanel.add(infoScrollpane);
	
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		c2.anchor = GridBagConstraints.LINE_START;
		
		JLabel responsableLabel = new JLabel("  Responsable : ");
		gridbag2.setConstraints(responsableLabel, c2);
		formationPanel.add(responsableLabel);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		final JComboBox responsableBox = new JComboBox((Object [])obj[2]);

		//TODO faire un truc plus prope
		for (int i = 0; i < responsableBox.getItemCount(); ++i){
			TeacherDto teachdto = (TeacherDto)responsableBox.getItemAt(i);
			if (teachdto.getTeacherId().compareTo(oldformdto.getTeacherId()) == 0) {
				responsableBox.setSelectedIndex(i);
				break;
			}
				
		}
		
		responsableBox.setRenderer(new ListCellRenderer(){

			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				
				if (arg1 instanceof FormationDto){
					FormationDto dto = (FormationDto)arg1;
					return new JLabel(dto.getName() + " " + dto.getFormationYear());
				}
				if (arg1 instanceof TeacherDto){
					TeacherDto dto = (TeacherDto)arg1;
					return new JLabel(dto.getName());
				}
				if (arg1 instanceof RoomDto){
					RoomDto dto = (RoomDto)arg1;
					return new JLabel(dto.getName() + " " + dto.getArea() + " " + dto.getBuilding());
				}
				if (arg1 instanceof EquipmentDto){
					EquipmentDto dto = (EquipmentDto)arg1;
					return new JLabel(dto.getName());
				}
				return new JLabel();
			}
			
		});
		gridbag2.setConstraints(responsableBox, c2);
		formationPanel.add(responsableBox);
		
		// Year
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.CENTER;
		

		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridwidth = GridBagConstraints.RELATIVE;
		JLabel roomLabel = new JLabel("  Salle : ");
		gridbag2.setConstraints(roomLabel, c2);
		formationPanel.add(roomLabel);
		
		final JComboBox roomBox = new JComboBox((Object [])obj[3]);
		
		roomBox.setRenderer(new ComboBoxFormationElementRenderer());
		gridbag2.setConstraints(roomBox, c2);
		formationPanel.add(roomBox);
	
		gridbag.setConstraints(formationPanel, c);
		contentPane.add(formationPanel);
		
		importBox.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				FormationDto dto = (FormationDto)importBox.getSelectedItem();
				yearTextField.setText(dto.getFormationYear());
				infoList.setText(dto.getDescription());
				nameTextField2.setText(dto.getName());
				
	/*			try {
					responsableBox.setSelectedItem(MainFrame.myDaybyday.getTeacher(new TeacherBusinessPK(dto.getTeacherName(), dto.getTeacherFirstname())));
				} catch (RemoteException e) {
				}
				try {
					roomBox.setSelectedItem(MainFrame.myDaybyday.getRoom(new RoomBusinessPK(dto.getRoomName(), dto.getRoomBuilding(), dto.getRoomArea())));
				} catch (RemoteException e) {
					//	e.printStackTrace();
				}
		*/		
			}
			
		});
		

		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				Object obj =  importBox.getSelectedItem();
				Object obj2 =  responsableBox.getSelectedItem();
				Object obj3 =  roomBox.getSelectedItem();
				
				FormationDto newdto = new FormationDto(
						nameTextField.getText(),
						yearTextField.getText(),
						((TeacherDto)obj2).getTeacherId(),
						mainframe.getUser().getUserId(),
						((RoomDto)obj3).getRoomId(),
						infoList.getText()
						);
				
				try {
				
					MainFrame.myDaybyday.createFormation(newdto);
					
					SectionDto sectiondefault = new SectionDto(
							"GENERALE",
							null,
							newdto.getFormationId(),
							newdto.getTeacherId(),
							""
							);
					MainFrame.myDaybyday.createSection(sectiondefault);
					mainframe.addFormationTabbePane(new Formation(newdto));
					framefinal.dispose();
				} catch (RemoteException e) {
					e.printStackTrace();
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