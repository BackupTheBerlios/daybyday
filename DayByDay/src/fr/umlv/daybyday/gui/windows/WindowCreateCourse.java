/*
 * Created on 28 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui.windows;

import java.awt.Container;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.table.DefaultTableModel;

import org.jboss.net.axis.SetClassLoaderHandler;

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionPK;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.util.exception.ConstraintException;
import fr.umlv.daybyday.ejb.util.exception.CourseConfusionException;
import fr.umlv.daybyday.ejb.util.exception.ResourceUnavailableException;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.TimeslotException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.DBDColor;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.TimeTableTable;
import fr.umlv.daybyday.gui.calendar.DBDCalendarPanel;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.Section;
/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WindowCreateCourse extends WindowAbstract {

	/**
	 * This method builds the window calendar.  
	 * 
	 * @param contentPane The container of the JFrame
	 * @param obj the object 
	 */

	//ArrayList matiere, ArrayList responsable, ArrayList salle, ArrayList materiel)
	public static void createWindow(final JFrame frame, Object [] obj){ 
	{		
		final MainFrame mainframe = (MainFrame) obj[0];
		final Formation formation = (Formation) obj[5];
		final FormationElement section   = (FormationElement) obj[6];
		final TimeTableTable df   = (TimeTableTable) obj[7];
		
		initWindow(frame,"Nouveau cours", 930, 450);
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
	
		contentPane.setLayout(gridbag);
		
		//Panel  en haut a gauche
		// Réference 
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(15,15,5,5);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		
		c.fill= GridBagConstraints.BOTH; 
		
		JPanel totalePanel = new JPanel(null);
		
			GridBagLayout gridbag2 = new GridBagLayout();
			GridBagConstraints c2 = new GridBagConstraints();
		
			totalePanel.setLayout(gridbag2);
			
			// Réference 
			c2.weightx = 1; 
			c2.weighty = 1; 
			c2.gridwidth = GridBagConstraints.REMAINDER; 
			c2.anchor = GridBagConstraints.LINE_START;
			c2.fill = GridBagConstraints.HORIZONTAL;
			

			String title = "Créneau horaire"; String first = "Début"; String end = "Fin"; String sep = "h";
			JPanel hour = new JPanel(null);
			hour.setBorder(BorderFactory.createTitledBorder(" " +title + " : "));
		
			GridBagLayout gridbag4 = new GridBagLayout();
			GridBagConstraints c4 = new GridBagConstraints();
			hour.setLayout(gridbag4);
			
			c4.weightx = 1; 
			c4.weighty = 1; 
			c4.gridwidth = 1;
			c4.insets =new Insets(5,15,5,5);
			c4.anchor = GridBagConstraints.LINE_START;
			c4.fill = GridBagConstraints.HORIZONTAL;
			JLabel firstLabel = new JLabel(first + " : ");
			gridbag4.setConstraints(firstLabel, c4);
			hour.add(firstLabel);
			
			c4.gridwidth = 3;
			c4.fill = GridBagConstraints.HORIZONTAL;
			
			final JTextField firstTextField = new JTextField("");
			firstTextField.setPreferredSize(new Dimension(20,20));
			gridbag4.setConstraints(firstTextField, c4);
			hour.add(firstTextField);
			
			c4.gridwidth = 1;
			JLabel separatorLabel = new JLabel(sep);
			gridbag4.setConstraints(separatorLabel, c4);
			hour.add(separatorLabel);
				 
			final JTextField fTextField = new JTextField("");
			fTextField.setPreferredSize(new Dimension(20,20));
			gridbag4.setConstraints(fTextField, c4);
			hour.add(fTextField);
			
			c4.gridwidth = 1;
			JLabel endLabel = new JLabel(end + " : ");
			gridbag4.setConstraints(endLabel, c4);
			hour.add(endLabel);
			
			
			final JTextField firstEndTextField = new JTextField("");
			firstEndTextField.setPreferredSize(new Dimension(20,20));
			gridbag4.setConstraints(firstEndTextField, c4);
			hour.add(firstEndTextField);
			
			c4.gridwidth = 1;
			JLabel sepLabel = new JLabel(sep);
			gridbag4.setConstraints(sepLabel, c4);
			hour.add(sepLabel);
			
			c4.gridwidth = GridBagConstraints.REMAINDER;
			final JTextField endEndTextField = new JTextField("");
			endEndTextField.setPreferredSize(new Dimension(20,20));
			gridbag4.setConstraints(endEndTextField, c4);
			hour.add(endEndTextField);
			gridbag2.setConstraints(hour, c2);
			totalePanel.add(hour);
			

			
			 title = "Période";  first = "Du";  end = "au";  sep = "/";
			JPanel period = new JPanel(null);
			period.setBorder(BorderFactory.createTitledBorder(" " +title + " : "));
		
			GridBagLayout gridbag5 = new GridBagLayout();
			GridBagConstraints c5 = new GridBagConstraints();
			period.setLayout(gridbag5);
			
			c5.weightx = 1; 
			c5.weighty = 1; 
			c5.gridwidth = 1;
			c5.insets =new Insets(5,15,5,5);
			c5.anchor = GridBagConstraints.LINE_START;
			c5.fill = GridBagConstraints.HORIZONTAL;
			JLabel firstLabelp = new JLabel(first + " : ");
			gridbag5.setConstraints(firstLabelp, c5);
			period.add(firstLabelp);
			
			c5.gridwidth = 3;
			c5.fill = GridBagConstraints.HORIZONTAL;
			
			final JTextField firstTextFieldp = new JTextField("");
			firstTextFieldp.setPreferredSize(new Dimension(20,20));
			gridbag5.setConstraints(firstTextFieldp, c5);
			period.add(firstTextFieldp);
			
			c5.gridwidth = 1;
			JLabel separatorLabelp = new JLabel(sep);
			gridbag5.setConstraints(separatorLabelp, c5);
			period.add(separatorLabelp);
				 
			final JTextField fTextFieldp = new JTextField("");
			fTextFieldp.setPreferredSize(new Dimension(20,20));
			gridbag5.setConstraints(fTextFieldp, c5);
			period.add(fTextFieldp);
			
			c5.gridwidth = 1;
			JLabel endLabelp = new JLabel(end + " : ");
			gridbag5.setConstraints(endLabelp, c5);
			period.add(endLabelp);
			
			
			final JTextField firstEndTextFieldp = new JTextField("");
			firstEndTextFieldp.setPreferredSize(new Dimension(20,20));
			gridbag5.setConstraints(firstEndTextFieldp, c5);
			period.add(firstEndTextFieldp);
			
			c5.gridwidth = 1;
			JLabel sepLabelp = new JLabel(sep);
			gridbag5.setConstraints(sepLabelp, c5);
			period.add(sepLabelp);
			
			c5.gridwidth = GridBagConstraints.REMAINDER;
			final JTextField endEndTextFieldp = new JTextField("");
			endEndTextFieldp.setPreferredSize(new Dimension(20,20));
			gridbag5.setConstraints(endEndTextFieldp, c5);
			period.add(endEndTextFieldp);
			gridbag2.setConstraints(period, c2);
			totalePanel.add(period);

			JPanel frqp= createPanelFrequence();
			gridbag2.setConstraints(frqp, c2);
			totalePanel.add(frqp);
			
			c2.gridwidth = 1;
			JLabel subjectLabel = new JLabel("Matiere : ");
			gridbag2.setConstraints(subjectLabel,c2);
			totalePanel.add(subjectLabel);
			
			c2.gridwidth = GridBagConstraints.REMAINDER;
			final JComboBox subjectCombo = new JComboBox((Object [])obj[1]);
			subjectCombo.setRenderer(new ComboBoxFormationElementRenderer());
			gridbag2.setConstraints(subjectCombo,c2);
			totalePanel.add(subjectCombo);
			
			JPanel typeGroup = createPanelTypeAndGroupe();
			gridbag2.setConstraints(typeGroup,c2);
			totalePanel.add(typeGroup);
			
		gridbag.setConstraints(totalePanel, c);
		contentPane.add(totalePanel);
		
		c.insets =new Insets(15,15,5,5);
		c.anchor= GridBagConstraints.FIRST_LINE_END;
		c.gridwidth = GridBagConstraints.REMAINDER;
		 
		
		JPanel totalePanel2 = new JPanel(null);
		
			GridBagLayout gridbag3 = new GridBagLayout();
			GridBagConstraints c3 = new GridBagConstraints();
		
			totalePanel2.setLayout(gridbag3);
			
			c3.weightx = 1; 
			c3.weighty = 1; 
			c3.gridwidth = 1; 
			c3.insets =new Insets(5,15,5,5);
			c3.anchor = GridBagConstraints.LINE_START;
			c3.fill = GridBagConstraints.HORIZONTAL;
			
			JLabel resp = new JLabel("Responsable : ");
			gridbag3.setConstraints(resp, c3);
			totalePanel2.add(resp);
			
			c3.gridwidth = 13;

			final JComboBox respCombo = new JComboBox((Object [])obj[2]);
			gridbag3.setConstraints(respCombo, c3);
			totalePanel2.add(respCombo);
			respCombo.setRenderer(new ComboBoxFormationElementRenderer());
			c3.gridwidth= GridBagConstraints.REMAINDER;
			JButton more = new JButton("+");
			//more.setBackground(DBDColor.getColor("DARK_GRAY"));
			gridbag3.setConstraints(more, c3);
			totalePanel2.add(more);
			
			c3.gridwidth = 1;
			c3.fill = GridBagConstraints.HORIZONTAL;
			JLabel roomLabel = new JLabel("Salle : ");
			gridbag3.setConstraints(roomLabel, c3);
			totalePanel2.add(roomLabel);
			
			c3.gridwidth= GridBagConstraints.REMAINDER;
			final JComboBox roomCombo = new JComboBox((Object [])obj[3]);
			roomCombo.setRenderer(new ComboBoxFormationElementRenderer());
			gridbag3.setConstraints(roomCombo, c3);
			totalePanel2.add(roomCombo);
				
			c3.gridwidth = 1;
			JLabel materialLabel = new JLabel("Materiel : ");
			gridbag3.setConstraints(materialLabel, c3);
			totalePanel2.add(materialLabel);

			c3.gridwidth= GridBagConstraints.REMAINDER;
			final JComboBox materialCombo = new JComboBox((Object [])obj[4]);
			materialCombo.setRenderer(new ComboBoxFormationElementRenderer());
			gridbag3.setConstraints(materialCombo, c3);
			totalePanel2.add(materialCombo);
			
			c3.gridwidth= 1;
			JLabel infoLabel = new JLabel("Informations : ");
			gridbag3.setConstraints(infoLabel, c3);
			totalePanel2.add(infoLabel);
		
			c3.gridwidth = GridBagConstraints.REMAINDER;
			c3.fill = GridBagConstraints.HORIZONTAL;
			
			final JTextArea infoList= new JTextArea("\n\n");
			
			JScrollPane infoScrollpane = new JScrollPane(infoList);		
			gridbag3.setConstraints(infoScrollpane, c3);
			totalePanel2.add(infoScrollpane);
			
			c3.gridwidth = 1;
			JLabel colorLabel = new JLabel("Couleur : ");
			gridbag3.setConstraints(colorLabel, c3);
			totalePanel2.add(colorLabel);
					
			c3.gridwidth= GridBagConstraints.REMAINDER;
			ArrayList color = DBDColor.getListColor();
			
			final JColorChooser jcc = new JColorChooser();
			AbstractColorChooserPanel jccc = jcc.getChooserPanels()[1];
			
			for(int i = 0; i< color.size(); i++){
				jcc.setColor(DBDColor.getColor((String)color.get(i)));
			}
			gridbag3.setConstraints(jccc, c3);
			totalePanel2.add(jccc);
			
			
		gridbag.setConstraints(totalePanel2, c);
		contentPane.add(totalePanel2);
		
		// Add button OK and Annuler
		
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				
				SubjectDto obj =   (SubjectDto)subjectCombo.getSelectedItem();
				TeacherDto obj2 =  (TeacherDto)respCombo.getSelectedItem();
				EquipmentDto obj3 =  (EquipmentDto) materialCombo.getSelectedItem();
				RoomDto obj4 =  (RoomDto) roomCombo.getSelectedItem();
				
				try{
				int bghour = Integer.parseInt(firstTextField.getText());
				int bgmin = Integer.parseInt(fTextField.getText());
				int endhour = Integer.parseInt(firstEndTextField.getText());
				int endmin = Integer.parseInt(endEndTextField.getText());
				
				int bgday =  Integer.parseInt(firstTextFieldp.getText());
				int bgmonth = Integer.parseInt(fTextFieldp.getText());
				int endday = Integer.parseInt(firstEndTextFieldp.getText());
				int endmonth = Integer.parseInt(endEndTextFieldp.getText());
				
				Color color = jcc.getColor();
				
				int colorf = 0;
				colorf |= (color.getRed() << 16); 
				colorf |= (color.getGreen() << 8); 
				colorf |= (color.getBlue()); 
		           Timestamp startDate = toTimeStamp(2005, bgmonth-1,bgday,bghour,bgmin,00);
		           Timestamp endDate = toTimeStamp(2005, endmonth-1,endday,endhour,endmin,00);
		         
		           String sectionname ;
		           if (section instanceof Formation)
		           		sectionname = "GENERALE";
		           else
		           		sectionname = section.getName();
		           		
				CourseDto newdto = new CourseDto(
						obj.getName(),
						sectionname,
						formation.getName(),
						formation.getYear(),
						"cours",
						startDate,
						endDate,
						"groupe1",
						obj2.getName(),
						obj2.getFirstname(),
						obj4.getName(),
						obj4.getBuilding(),
						obj4.getArea(),
						null,
						null,
						null,
						infoList.getText(),
						new Integer(colorf),
						new Boolean(true)
						);
				
				
					MainFrame.myDaybyday.createCourse(newdto);
					//section.getDTO().setVersion(new Long(section.getDTO().getVersion().longValue()+1));
					if (section instanceof Section){
						MainFrame.myDaybyday.updateSection(((Section)section).getDTO());
					
						((Section)section).upDateDto(MainFrame.myDaybyday.getSection(((Section)section).getDTO().getSectionPK()));
						//mainframe.addFormationTabbePane(new Formation(newdto));
						framefinal.dispose();
						df.changeSource(new Section(((Section)section).getDTO(),section.getFather()));
					}
					if (section instanceof Formation){
						try{
							MainFrame.myDaybyday.updateFormation((FormationDto) ((Formation)section).getDto());
						}catch (RemoteException e) {
							e.printStackTrace();
							mainframe.showError(frame,e.toString());
						} 
						((Formation)section).getCourseList();
						((Formation)section).upDateDto(null);
						framefinal.dispose();
						df.changeSource((Formation)section);
					}
					
				}catch (java.lang.NumberFormatException e){
					mainframe.showError(frame,"Champs manquant(s) ou mal renseigné(s)");
					
				}
				catch (RemoteException e) {
					mainframe.showError(frame,e.toString());
				} catch (ConstraintException e) {
					mainframe.showError(frame,e.toString());
				} catch (ResourceUnavailableException e) {
					mainframe.showError(frame,"Ressource(s) indisponible(s)");
				} catch (CourseConfusionException e) {
					mainframe.showError(frame,"Attention! recouvrement du cours que vous voulez créer avec un cours existant!");
				} catch (TimeslotException e) {
					mainframe.showError(frame,"La date de début est supérieure à la date de fin");
				} catch (CreateException e) {
					mainframe.showError(frame,e.toString());
				} catch (FinderException e) {
					mainframe.showError(frame,e.toString());
				} catch (StaleUpdateException e) {
					mainframe.showError(frame,"Problème de version " + e);
				} catch (WriteDeniedException e) {
					mainframe.showError(frame,e.toString());
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
	
    public static Timestamp toTimeStamp(int year, int month, int day, int hour, int minute, int second){

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        calendar.set(Calendar.MILLISECOND,0);



        return new Timestamp(calendar.getTimeInMillis());


    }

}
