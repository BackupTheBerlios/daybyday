package fr.umlv.daybyday.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.daybyday.gui.windows.WindowAbstract;



/**
 * @author Emmanuelle Emond
 * 
 * Created the 19 févr. 2005
 * 
 * This class builds all the windows of daybyday
 * 
 */
public class Windows extends WindowAbstract{




	/**
	 * This method create a window knowing is identfier. 
	 * It calls the appropriate mathod to construct the window.
	 * 
	 * @param identifier The identifier to cre	te the appropriate window 
	 * @param obj All the parameter neccessary to construct the window.
	 */
	public static void createWindow(String identifier, Object[] obj) {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		Container contentPane = frame.getContentPane();
		

		try {
			Method m =  Class.forName("fr.umlv.daybyday.gui.windows." + identifier).getMethod("createWindow",
					new Class[] { frame.getClass(), obj.getClass()});
			m.invoke(null,new Object[] { frame, obj });
			//(null).newInstance(null);
		} catch (ClassNotFoundException e) {
			System.out.println("fr.umlv.daybyday.gui.windows" + identifier);
			throw new InternalError("Request for a wrong class.");
		} catch (IllegalAccessException e) {
			throw new InternalError("Illegal access.");
		} catch (IllegalArgumentException e) {
			throw new InternalError("Illegal argument.");
		} catch (SecurityException e) {
			throw new InternalError("Security exception.");
		} catch (InvocationTargetException e) {
			throw new InternalError("Invocation target exception.");
		} catch (NoSuchMethodException e) {
			throw new InternalError("No such constructor.");
		}

	/*
		else if(identifier.equals("Error")){	
			initWindow(frame,identifier, 350, 140);
			createWindowInformation(contentPane, DBDIcon.ERROR, " yrtyrtyrr !!!");
		}
		
		else if(identifier.equals("Information")) {
			initWindow(frame,identifier, 350, 140);
			createWindowInformation(contentPane, DBDIcon.INFO, " yrtyrtyrr fffffffffff!!!");
		}
		else if (identifier.equals("Question")){
			initWindow(frame,identifier, 350, 140);
			createWindowInformation(contentPane, DBDIcon.QUESTION, " yrtyrtyrr !!!");
		}
		
		else if(identifier.equals("A_propos")){
			initWindow(frame,identifier, 300, 220);
			createWindowPropos(contentPane, DBDIcon.DAYBYDAY);
		}*/		
		frame.setVisible(true);
	}
	
	
	/**
	 * This method builds the window "a propos"
	 * 
	 * @param contentPane the container of the frame
	 * @param icon the logo of Day bY Day
	 */
	private static void createWindowPropos(Container contentPane, ImageIcon icon){
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 
		c.gridwidth = 1; 
		
		c.insets =new Insets(0,10,0,10);
		c.anchor = GridBagConstraints.CENTER;
		
		JPanel info = new JPanel(null);
		info.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));

		GridBagLayout gridbagInfo = new GridBagLayout();
		GridBagConstraints cInfo = new GridBagConstraints();	
		info.setLayout(gridbagInfo);
		
		// Fixed the constraint
		cInfo.weightx = 1; 
		cInfo.weighty = 1; 
		cInfo.insets =new Insets(2,0,2,0);
		cInfo.gridwidth = GridBagConstraints.REMAINDER; 
		cInfo.fill = GridBagConstraints.HORIZONTAL;
		cInfo.anchor = GridBagConstraints.LINE_START;
		
		JLabel nameProject = new JLabel("  Day bY Day v1.0  ");
		gridbagInfo.setConstraints(nameProject, cInfo);
		info.add(nameProject);
		
		JLabel subject = new JLabel("  UMLV Génie logiciel  ");
		gridbagInfo.setConstraints(subject, cInfo);
		info.add(subject);
		
		//Put a space after the label matiere : top = 15
		cInfo.insets =new Insets(15,2,2,2);
		JLabel actor1 = new JLabel("  Abdennebi Mohamed  ");
		gridbagInfo.setConstraints(actor1, cInfo);
		info.add(actor1);				
		cInfo.insets =new Insets(2,0,2,0);
		JLabel actor2 = new JLabel("  Boudigou Ildut  ");
		gridbagInfo.setConstraints(actor2, cInfo);
		info.add(actor2);
		JLabel actor3 = new JLabel("  Emond Emmanuelle  ");
		gridbagInfo.setConstraints(actor3, cInfo);
		info.add(actor3);
		JLabel actor4 = new JLabel("  Meynier Marc  ");
		gridbagInfo.setConstraints(actor4, cInfo);
		info.add(actor4);
		JLabel actor5 = new JLabel("  Verriere Victor  ");
		gridbagInfo.setConstraints(actor5, cInfo);
		info.add(actor5);
		
		// Put the JPanel in the contentPanel of the JFrame
		// on the top left
		gridbag.setConstraints(info,c);
		contentPane.add(info);
		
		// constraint moved for the icon dayByDay
		// put on the bottom right
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
			
		JLabel icone = new JLabel();
		icone.setIcon(icon);
		gridbag.setConstraints(icone, c);
		contentPane.add(icone);		
	}

	
	/**
	 * This method builds the window of informations
	 * (windows error, windows question, windows informations). 
	 * 
	 * @param contentPane the container of the wondow
	 * @param icon the logo of Day bY Day
	 * @param message the message to write
	 */
	private static void createWindowInformation(Container contentPane, ImageIcon icon, String message){
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		
		c.weightx = 1; 
		c.weighty = 1; 	
		c.insets = new Insets(4,5,0,5);
		
	    c.fill = GridBagConstraints.BOTH;
	    c.anchor = GridBagConstraints.CENTER;
	    c.gridwidth = GridBagConstraints.REMAINDER;
	    JPanel text = createPanelText(message, icon);
	    gridbag.setConstraints(text, c);
	    contentPane.add(text);

	    c.fill = GridBagConstraints.CENTER;
	    JButton okButton = new JButton("OK");
	    okButton.setBackground(DBDColor.getColor("DARK_GRAY"));
	    gridbag.setConstraints(okButton,c);
	    contentPane.add(okButton);	
	}

}