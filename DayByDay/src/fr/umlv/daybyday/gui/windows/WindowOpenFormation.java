package fr.umlv.daybyday.gui.windows;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.Equipment;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.Room;
import fr.umlv.daybyday.model.Teacher;



/**
 * @author Emond Emmanuelle, Marc meynier
 *
 * This class creates the window which permit to open the timetable
 * of a formation. 
 */
public class WindowOpenFormation extends WindowAbstract {

	
	/**
	 * This method creates the window which permit to open the timetable
	 * of a formation. 
	 * 
	 * @param frame The frame of the window
	 * @param obj an object table which contains in position 0 
	 * the main frame
	 */
	public static void createWindow(JFrame frame, Object[] obj){
		
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		initWindow(frame,"Ouvrir...", 300, 100);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(5,20,5,20);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.CENTER;
		
		JLabel importLabel = new JLabel("Source: ");
		gridbag.setConstraints(importLabel, c);
		contentPane.add(importLabel);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JComboBox importCombo =  new JComboBox((Object[])obj[1]);
		importCombo.setRenderer(new ListCellRenderer(){

			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				// TODO Auto-generated method stub
				
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
		gridbag.setConstraints(importCombo, c);
		contentPane.add(importCombo);
		final MainFrame mainframe = (MainFrame) obj[0];
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				Object obj =  importCombo.getSelectedItem();
				if (obj instanceof FormationDto)
					mainframe.addFormationTabbePane(new Formation((FormationDto)obj));
				else if (obj instanceof TeacherDto){
					mainframe.addFormationTabbePane(new Teacher((TeacherDto)obj));
				}
				else if (obj instanceof RoomDto)
					mainframe.addFormationTabbePane(new Room((RoomDto)obj));
				else if (obj instanceof EquipmentDto)
					mainframe.addFormationTabbePane(new Equipment((EquipmentDto)obj));
				framefinal.dispose();
				
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
