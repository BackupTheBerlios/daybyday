package fr.umlv.daybyday.gui.windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.Grid;

/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class create the window which permits to 
 * configure the table of the timetable.
 */
public class WindowConfigTable extends WindowAbstract {


	/**
	 * This method builds the window which permits to 
	 * configure the table of the timetable.  
	 * 
	 * @param frame the frame of the window
	 * @param obj the object 
	 */

	public static void createWindow(final JFrame frame,Object [] obj){
		final MainFrame mainframe = (MainFrame) obj[0];
		//initWindow(frame,"Configuration de la grille", 330, 250);

		initWindow(frame,"Configuration de la grille", 430, 250, mainframe.getFrameX(), mainframe.getFrameY());
		Container contentPane = frame.getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		/*
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = GridBagConstraints.REMAINDER;
		*/
		//c.insets =new Insets(0,5,0,5);
		//c.anchor = GridBagConstraints.LINE_START;
		//c.fill = GridBagConstraints.HORIZONTAL;
		

		
		
		JPanel daypanel = new JPanel(null);
		daypanel.setBorder(BorderFactory.createTitledBorder(" Intervalle : "));
		GridBagLayout gridbag3 = new GridBagLayout();
		GridBagConstraints c3 = new GridBagConstraints();
		daypanel.setLayout(gridbag3);
		
		ArrayList days = new ArrayList();
		days.add("Lundi");
		days.add( "Mardi");
		days.add( "Mercredi");
		days.add( "Jeudi");
		days.add( "Vendredi");
		days.add( "Samedi");
		days.add( "Dimanche");
		
		
		c3.weightx = 1; 
		c3.weighty = 1; 
		c3.gridwidth = 1; 
		
		c3.insets =new Insets(0,5,0,5);
		c3.anchor = GridBagConstraints.LINE_END;
		c3.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel du = new JLabel(" Du : ");
		gridbag3.setConstraints(du, c3);
		daypanel.add(du);
		
		JComboBox duCombo = new JComboBox(days.toArray());
		gridbag3.setConstraints(duCombo, c3);
		daypanel.add(duCombo);
		
		JLabel auLabel = new JLabel(" Au : ");
		gridbag3.setConstraints(auLabel, c3);
		daypanel.add(auLabel);
		
		final JComboBox auCombo = new JComboBox(days.toArray());
		auCombo.setSelectedIndex(Grid.gridNbDays-1);
		gridbag3.setConstraints(auCombo, c3);
		daypanel.add(auCombo);
		
		/*JLabel tailleLabel = new JLabel(" Taille : ");
		gridbag3.setConstraints(tailleLabel, c3);
		daypanel.add(tailleLabel);
		
		JTextField ptField2 = new JTextField("    ");
		gridbag3.setConstraints(ptField2, c3);
		daypanel.add(ptField2);
		
		JLabel ptLabel2 =new JLabel("pt");
		gridbag3.setConstraints(ptLabel2, c3);
		daypanel.add(ptLabel2);*/
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 1.0;
		//c.gridwidth = GridBagConstraints.RELATIVE ;
		gridbag.setConstraints(daypanel, c);
		contentPane.add(daypanel);
		

		

		
		JPanel infopanel = new JPanel(null);
		infopanel.setBorder(BorderFactory.createTitledBorder(" Journée : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		infopanel.setLayout(gridbag2);
				
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(0,5,0,5);
		c2.anchor = GridBagConstraints.LINE_END;
		c2.fill = GridBagConstraints.HORIZONTAL;
				
		JLabel beggining  = new JLabel(" Début : ");
		gridbag2.setConstraints(beggining , c2);
		infopanel.add(beggining );
		
		c2.anchor = GridBagConstraints.LINE_START;
		JPanel firstInter = new JPanel(null);
		firstInter.setLayout(new BorderLayout());
		final JTextField bgHour = new JTextField("" + Grid.gridBgHour);
		bgHour.setPreferredSize(new Dimension(20,20));
		firstInter.add(bgHour, BorderLayout.WEST);
		firstInter.add(new JLabel("   h   "), BorderLayout.CENTER);
		//JTextField bgMin = new JTextField("0");
		//bgMin.setPreferredSize(new Dimension(20,20));
		//firstInter.add(bgMin, BorderLayout.EAST);
		gridbag2.setConstraints(firstInter, c2);
		infopanel.add(firstInter);
		
		c2.anchor = GridBagConstraints.LINE_END;
		JLabel end = new JLabel(" Fin : ");
		gridbag2.setConstraints(end, c2);
		infopanel.add(end);
		
		c2.anchor = GridBagConstraints.LINE_START;
		
		JPanel secondInter = new JPanel(null);
		secondInter.setLayout(new BorderLayout());
		final JTextField endHour = new JTextField("" + Grid.gridEndHour);
		endHour.setPreferredSize(new Dimension(20,20));
		secondInter.add(endHour, BorderLayout.WEST);
		secondInter.add(new JLabel("   h   "), BorderLayout.CENTER);
		//JTextField endMin = new JTextField("0");
		//endMin.setPreferredSize(new Dimension(20,20));
		//secondInter.add(endMin, BorderLayout.EAST);

		gridbag2.setConstraints(secondInter, c2);
		infopanel.add(secondInter);
		
		/*JLabel size = new JLabel(" Taille : ");
		gridbag2.setConstraints(size, c2);
		infopanel.add(size);
		
		JTextField ptField = new JTextField("    ");
		gridbag2.setConstraints(ptField, c2);
		infopanel.add(ptField);
		
		JLabel ptLabel =new JLabel("pt");
		gridbag2.setConstraints(ptLabel, c2);
		infopanel.add(ptLabel);
		*/
		c.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(infopanel, c);
		contentPane.add(infopanel);
		
		c.gridwidth = GridBagConstraints.RELATIVE;
		
		c.anchor = GridBagConstraints.LINE_START;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		JLabel hour = new JLabel(" Découpage des heures : ");
		gridbag.setConstraints(hour, c);
		contentPane.add(hour);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		
		final JComboBox hourCombo = new JComboBox(new Object[]{"1","2","4","5"});
		hourCombo.setPreferredSize(new Dimension(100,20));
		hourCombo.setSelectedItem(""+Grid.gridSlice);
		gridbag.setConstraints(hourCombo, c);
		contentPane.add(hourCombo);
		
//		 Add button OK and Annuler
		
		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100,20));
		final JFrame framefinal = frame;
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				
				try{
				Grid.gridNbDays = auCombo.getSelectedIndex() + 1;
				Grid.gridBgHour = Integer.parseInt(bgHour.getText());
				Grid.gridEndHour = Integer.parseInt(endHour.getText());
				Grid.gridSlice = Integer.parseInt((String) (hourCombo.getSelectedItem()));
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
		frame.setVisible(true);
	}
	
}