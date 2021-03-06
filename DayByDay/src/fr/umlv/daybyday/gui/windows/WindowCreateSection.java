package fr.umlv.daybyday.gui.windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionBusinessPK;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.util.exception.WriteDeniedException;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.FormationTreeModel;


/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to create 
 * a new section
 */
public class WindowCreateSection extends WindowAbstract {

	
	
	/**
	 * This method builds the window witch contains the
	 * informations about a section.
	 * 
	 * @param frame the frame of the window
	 * @param obj the table object. in position 0 the mainframe,
	 * in position 1 the teachers
	 */
	public static void createWindow(final JFrame frame,Object [] obj){
		
		final MainFrame mainframe = (MainFrame) obj[0];
		Container mainpanel = frame.getContentPane();
		Container contentPane = new Container();  
		initWindow(frame,"Nouvelle fili�re", 400, 250, mainframe.getFrameX(), mainframe.getFrameY());
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		// R�ference 
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		c.insets =new Insets(5,20,5,20);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.CENTER;
	
		JLabel refLabel = new JLabel(" R�ference : ");
		gridbag.setConstraints(refLabel, c);
		contentPane.add(refLabel);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel refNameLabel = new JLabel(((FormationElement)mainframe.getSelectedObject()).getNamePath());
		gridbag.setConstraints(refNameLabel, c);
		contentPane.add(refNameLabel);
		
		//Intitul�
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.CENTER;
		
		JLabel nameLabel = new JLabel("  Intitul� : ");
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
					SectionDto sec =null;
					try {
						 sec= MainFrame.myDaybyday.getSection(new SectionBusinessPK("GENERALE",form.getFormationId()));
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (EntityNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					newdto = new SectionDto(nameTextField.getText(),
							sec.getSectionId(),
							form.getFormationId(),
							obj2.getTeacherId(),
							infoList.getText()
							);
				}
				if (ref instanceof SectionDto){
						father = (SectionDto) ref;
					try {
						
						formglo = MainFrame.myDaybyday.getFormation(father.getFormationId());
					} catch (RemoteException e1) {


					} catch (EntityNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					System.out.println("section id : "+father.getSectionId());
					newdto = new SectionDto(nameTextField.getText(),
							father.getSectionId(),
							father.getFormationId(),	
							obj2.getTeacherId(),
							infoList.getText()
							
							);
				}
				
				try {
					System.out.println("section : "+newdto.toString());
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
				} catch (StaleUpdateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteDeniedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CreationException e) {
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
		
		mainpanel.add(contentPane,BorderLayout.CENTER);
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		ok.setMinimumSize(new Dimension(100,20));
		cancel.setMinimumSize(new Dimension(100,20));
		ok.setPreferredSize(new Dimension(100,20));
		cancel.setPreferredSize(new Dimension(100,20));
		southPanel.add(ok);
		southPanel.add(cancel);
		mainpanel.add(southPanel,BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
}