package fr.umlv.daybyday.gui.windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.daybyday.gui.DBDColor;
import fr.umlv.daybyday.gui.icone.DBDIcon;

/**
 * @author Emmanuelle Emond et Marc Meynier
 *
 * This class in an abstract window factory
 */
public abstract class WindowAbstract {

	/**
	 * This method init the window with a title and his size.
	 * 
	 * @param name the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public static void initWindow(JFrame frame, String name, int width, int height) {
		frame.setTitle(name);
		frame.setSize(width, height);
		centerWindow(frame);
	}
	
	
	/**
	 * This method center the windows to the screen.
	 * 
	 * @param f the window to center
	 */
	public static void centerWindow(JFrame f) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Dimension frameSize = f.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        f.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }
	
	
	/**
	 * This method creates a new availability of the teacher.
	 * 
	 * @param ens the thist of the teacher
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createAvailabilityHours(Object [] ens){
		JPanel panel = new JPanel(null);
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(0,5,0,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		//TODO Faire une bouble sur les dispo et non prof
		for(int i=0; i<ens.length; i++ ){
			c2.gridwidth = 2; 
			c2.fill = GridBagConstraints.HORIZONTAL;
			JLabel day = new JLabel("  jour");
			day.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(day, c2);
			panel.add(day);
	
			JLabel frq = new JLabel("    frq");
			frq.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(frq, c2);
			panel.add(frq);
	
			JLabel interval = new JLabel("    n° a n°");
			interval.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(interval, c2);
			panel.add(interval);
		
			JLabel hour = new JLabel("    H:M à H:M");
			hour.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(hour, c2);
			panel.add(hour);
			
			c2.gridwidth = 1;
			c2.fill = GridBagConstraints.CENTER;
			JButton delete = new JButton("Supprimer");
			gridbag2.setConstraints(delete, c2);
			panel.add(delete);
			
			JCheckBox dBox = new JCheckBox("", true);
			gridbag2.setConstraints(dBox, c2);
			panel.add(dBox);
			
			c2.gridwidth = GridBagConstraints.REMAINDER;
			JCheckBox iBox = new JCheckBox("", true);
			gridbag2.setConstraints(iBox, c2);
			panel.add(iBox);
		}		
		return panel;
	}

	/**
	 * This metho create the panel witch contians all the arrows.
	 * The arrows permit to do slip elements to the commands to 
	 * the list of thetool bar or to do slip elements  to the
	 * tools bar to  the list of command. 
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createPanelArrowsButton(){
		JPanel panel = new JPanel(null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createHorizontalStrut(2));
	
		// Line 1
		Box line1 = new Box(BoxLayout.X_AXIS);
		
		JButton north = new JButton(DBDIcon.HAUT_FLECHE);
		line1.add(north);
		
		// Line 2
		Box line2 = new Box(BoxLayout.X_AXIS);
		
		line2.add(Box.createHorizontalGlue());
		
		JButton west = new JButton(DBDIcon.GAUCHE_FLECHE);
		line2.add(west);
		
		line2.add(Box.createHorizontalGlue());
		
		JButton east = new JButton(DBDIcon.DROITE_FLECHE);
		line2.add(east);
		line2.add(Box.createHorizontalGlue());
		
		// line 3
		Box line3 = new Box(BoxLayout.X_AXIS);
	
		JButton south = new JButton(DBDIcon.BAS_FLECHE);
		line3.add(south);
				
		panel.add(line1);
		panel.add(line2);
		panel.add(line3);
		
		return panel;		
	}

	/**
	 * This method creates the panel witch adjust the availability
	 * all the teacher. 
	 * 
	 * @param ens the list of the teacher.
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelAvailability(Object [] ens){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" Disponibilités : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
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
		panel.add(dateBegin);
	
		c2.insets =new Insets(5,5,5,5); 
		c2.anchor = GridBagConstraints.LINE_START;	
		JPanel date = createPanelAvailabilityDate(ens);
		JScrollPane dateScrollpane = new JScrollPane(date);	
		gridbag2.setConstraints(dateScrollpane, c2);
		panel.add(dateScrollpane);
		
		JPanel newAvailability = createPanelNewAvailability();
		gridbag2.setConstraints(newAvailability, c2);
		panel.add(newAvailability);
		
		c2.anchor = GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.insets =new Insets(10,0,0,0);
		JLabel title = new JLabel("     Jour      Fréquence" +
				"      Intervalle" +
				"            Horaire          " +
				"                                      " +
				"      D              I");
		gridbag2.setConstraints(title, c2);
		panel.add(title);
		
		c2.insets =new Insets(5,5,5,5);
		JPanel hour = createAvailabilityHours(ens);
		JScrollPane hourScrollpane = new JScrollPane(hour);	
		gridbag2.setConstraints(hourScrollpane, c2);
		panel.add(hourScrollpane);
		
		return panel;
	}

	/**
	 * This method create the panel witch permit to get
	 * the availability date of the teacher.
	 * 
	 * @param ens the list of the teacher.
	 * 
	 * @return the enw panel created.
	 */
	  public static JPanel createPanelAvailabilityDate(Object [] ens) {
		JPanel panel = new JPanel(null);
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(0,5,0,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		//TODO Faire une bouble sur les dispo et non prof
		for(int i=0; i<ens.length; i++ ){
			c2.gridwidth = 2; 
			c2.fill = GridBagConstraints.HORIZONTAL;
			JLabel begin = new JLabel("debut");
			begin.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(begin, c2);
			panel.add(begin);
			
			c2.gridwidth = 2;
			JLabel end = new JLabel("fin");
			end.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(end, c2);
			panel.add(end);
			
			c2.gridwidth = 1;
			c2.fill = GridBagConstraints.CENTER;
			JButton delete = new JButton("Supprimer");
			//delete.setBackground(DBDColor.getColor("DARK_GRAY"));
			gridbag2.setConstraints(delete, c2);
			panel.add(delete);
			
			JCheckBox dBox = new JCheckBox("", true);
			gridbag2.setConstraints(dBox, c2);
			panel.add(dBox);
			
			c2.gridwidth = GridBagConstraints.REMAINDER;
			JCheckBox iBox = new JCheckBox("", true);
			gridbag2.setConstraints(iBox, c2);
			panel.add(iBox);
		}		
		return panel;		
	}

	/**
	 * This method creates the panel witch contains  
	 * the login, the password and the access of an 
	 * element (Base de donnée, LDAP) 
	 * 
	 * @param title the title of the panel
	 * @return the new panel created
	 */
	  public static JPanel createPanelBase(String title){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(title));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1;
		
		c2.insets =new Insets(2,5,2,5);
		c2.anchor = GridBagConstraints.LINE_END;
		c2.fill = GridBagConstraints.HORIZONTAL;
				
		JLabel id = new JLabel("Identifant :");
		gridbag2.setConstraints(id, c2);
		panel.add(id);
		
		c2.gridwidth = 4;		
		c2.anchor = GridBagConstraints.LINE_START;
		JTextField loginTextField = new JTextField();
		gridbag2.setConstraints(loginTextField, c2);
		panel.add(loginTextField);
	
		c2.gridwidth = 1; 
		c2.anchor = GridBagConstraints.LINE_END;
		JLabel password = new JLabel("Mot de passe :");
		gridbag2.setConstraints(password, c2);
		panel.add(password);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.anchor = GridBagConstraints.LINE_START;
		JPasswordField passewordField = new JPasswordField();
		gridbag2.setConstraints(passewordField, c2);
		panel.add(passewordField);
		
		c2.gridwidth = 1;
		JLabel access = new JLabel(" Accès :");
		gridbag2.setConstraints(access, c2);
		panel.add(access);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		JTextField accessField = new JTextField();
		gridbag2.setConstraints(accessField, c2);
		panel.add(accessField);
		
		return panel;
	}

	/**
	 * This method creates a panel witch permit to
	 * add or remove a number. 
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelCounter(){
		JPanel panel = new JPanel(null);
		panel.setLayout(new BorderLayout());
		
		JButton less = new JButton("-");
		panel.add(less , BorderLayout.WEST);
		
		panel.add(new JTextField(" "),BorderLayout.CENTER);
		
		JButton more = new JButton("+");
		panel.add(more ,BorderLayout.EAST);
	
		return panel;
	}

	/**
	 * This method create the panel witch contains all the 
	 * user's password and user's login. This panel permit to
	 * delete a user account.
	 * 
	 * @param obj object 
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createPanelCurrentAccount(Object[] obj){
		JPanel panel = new JPanel(null);
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(2,5,2,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		//TODO Faire une bouble le nombre d'utilisateur
		for(int i=0; i<((ArrayList)obj[0]).size(); i++ ){
			
			c2.gridwidth = 4; 
			c2.fill = GridBagConstraints.HORIZONTAL;
			JLabel id = new JLabel("  Identifiant");
			id.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(id, c2);
			panel.add(id);
			
			c2.gridwidth = 4;
			JLabel login = new JLabel("    Mot de passe");
			login.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			gridbag2.setConstraints(login, c2);
			panel.add(login);
			
			c2.gridwidth = 1;
			c2.fill = GridBagConstraints.CENTER;
			JButton delete = new JButton("Supprimer");
			gridbag2.setConstraints(delete, c2);
			panel.add(delete);
			
			JCheckBox uBox = new JCheckBox("U", true);
			gridbag2.setConstraints(uBox, c2);
			panel.add(uBox);
			
			c2.gridwidth = GridBagConstraints.REMAINDER;
			JCheckBox adBox = new JCheckBox("AD", true);
			gridbag2.setConstraints(adBox, c2);
			panel.add(adBox);
		}		
		return panel;
	}

	/**
	 * This method creates the panel witch permit 
	 * to adjust the interval of the days visbles. 
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelDay(){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" Journée : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
				
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(0,5,0,5);
		c2.anchor = GridBagConstraints.LINE_END;
		c2.fill = GridBagConstraints.HORIZONTAL;
				
		JLabel beggining  = new JLabel(" Début : ");
		gridbag2.setConstraints(beggining , c2);
		panel.add(beggining );
		
		c2.anchor = GridBagConstraints.LINE_START;
		JPanel firstInter = createPanelInterval("h");
		gridbag2.setConstraints(firstInter, c2);
		panel.add(firstInter);
		
		c2.anchor = GridBagConstraints.LINE_END;
		JLabel end = new JLabel(" Fin : ");
		gridbag2.setConstraints(end, c2);
		panel.add(end);
		
		c2.anchor = GridBagConstraints.LINE_START;
		JPanel secondInter = createPanelInterval("h");
		gridbag2.setConstraints(secondInter, c2);
		panel.add(secondInter);
		
		JLabel size = new JLabel(" Taille : ");
		gridbag2.setConstraints(size, c2);
		panel.add(size);
		
		JTextField ptField = new JTextField("    ");
		gridbag2.setConstraints(ptField, c2);
		panel.add(ptField);
		
		JLabel ptLabel =new JLabel("pt");
		gridbag2.setConstraints(ptLabel, c2);
		panel.add(ptLabel);
		
		return panel;
	}





	/**
	 * This method creates the panel of the period of course 
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createPanelFrequence(){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" Fréquence : "));
	
		GridBagLayout gridbag10 = new GridBagLayout();
		GridBagConstraints c10 = new GridBagConstraints();
	
		panel.setLayout(gridbag10);
		
		c10.weightx = 1; 
		c10.weighty = 1; 
		c10.gridwidth = 1;
		c10.insets =new Insets(5,15,5,5);
		c10.anchor = GridBagConstraints.LINE_START;
		c10.fill = GridBagConstraints.HORIZONTAL;
		JLabel firstLabel = new JLabel("Toute les : ");
		gridbag10.setConstraints(firstLabel, c10);
		panel.add(firstLabel);
		
		c10.gridwidth = 1;
		c10.fill = GridBagConstraints.HORIZONTAL;
		
		JTextField firstTextField = new JTextField();
		gridbag10.setConstraints(firstTextField, c10);
		panel.add(firstTextField);
		
		JLabel separatorLabel = new JLabel("semaine (s)");
		gridbag10.setConstraints(separatorLabel, c10);
		panel.add(separatorLabel);
		
		return panel;
	}




	/**
	 * This method create a panel witch contain a JList.
	 * the JList is the list of identifier.
	 * 
	 * @param obj the object witch contains the list of identifier
	 * @param title the title of the panel
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createPanelId(Object[] obj, String title){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(title));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1;		
		c2.insets =new Insets(2,5,2,5);
		c2.anchor = GridBagConstraints.LINE_END;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridwidth = GridBagConstraints.REMAINDER;
	
		//TODO Faire une bouble le nombre d'utilisateur
		JList idList = new JList(((ArrayList)obj[0]).toArray());
		idList.setVisibleRowCount(3);
		JScrollPane scrollpane = new JScrollPane(idList);
		gridbag2.setConstraints(scrollpane, c2);
		panel.add(scrollpane);	
	
		return panel;				
	}

	

	/**
	 * This method create a panel knowing the 
	 * interval separator. It gets the first 
	 * and the second element of the interval. 
	 * 
	 * @param sep the interval separator.
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelInterval(String sep){
		JPanel panel = new JPanel(null);
		panel.setLayout(new BorderLayout());
	
		panel.add(new JTextField("   1   "), BorderLayout.WEST);
		panel.add(new JLabel("   " + sep+ "   "), BorderLayout.CENTER);
		panel.add(new JTextField("   1   "), BorderLayout.EAST);
		
		return panel;	
	}



	/**
	 * This method creates the panel witch permits to
	 * create a user new account. The new account is
	 * created getting the new login and new passeword. 
	 * 
	 * @param obj  object
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelNewAccount(Object[] obj){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" Nouveau : "));
	
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(0,5,0,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel id = new JLabel("Identifant :");
		gridbag2.setConstraints(id, c2);
		panel.add(id);
		
		c2.gridwidth = 10;		
		JTextField loginTextField = new JTextField();
		gridbag2.setConstraints(loginTextField, c2);
		panel.add(loginTextField);
	
		c2.gridwidth = 1; 
		c2.anchor = GridBagConstraints.LINE_END;
		JLabel password = new JLabel("Mot de passe :");
		gridbag2.setConstraints(password , c2);
		panel.add(password );
	
		
		c2.anchor = GridBagConstraints.LINE_START;
		c2.gridwidth = 10;
		JPasswordField passewordField = new JPasswordField();
		gridbag2.setConstraints(passewordField, c2);
		panel.add(passewordField);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.CENTER;
		c2.anchor = GridBagConstraints.LINE_END;
		JButton add = new JButton(" Ajouter ");
		//add.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(add, c2);
		panel.add(add);
		
		return panel;
	}

	/**
	 * This method create a new availability of the teacher.
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelNewAvailability(){
		JPanel panel = new JPanel(null);
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setBorder(BorderFactory.createTitledBorder(" Nouveau : "));
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(0,5,0,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel firstLabel = new JLabel(" Début : ");
		gridbag2.setConstraints(firstLabel, c2);
		panel.add(firstLabel);
	
		JTextField firstTextField = new JTextField();
		gridbag2.setConstraints(firstTextField, c2);
		panel.add(firstTextField);
		
		JLabel separatorLabel = new JLabel("/");
		gridbag2.setConstraints(separatorLabel, c2);
		panel.add(separatorLabel);
		
		c2.anchor = GridBagConstraints.FIRST_LINE_START;
		JTextField fTextField = new JTextField();
		gridbag2.setConstraints(fTextField, c2);
		panel.add(fTextField);
		
		c2.anchor = GridBagConstraints.CENTER;
		JTextField secondTextField = new JTextField();
		gridbag2.setConstraints(secondTextField, c2);
		panel.add(secondTextField);
		
		JLabel separatorSecondLabel = new JLabel("h");
		gridbag2.setConstraints(separatorSecondLabel, c2);
		panel.add(separatorSecondLabel);
		
		JTextField fSecondTextField = new JTextField();
		gridbag2.setConstraints(fSecondTextField, c2);
		panel.add(fSecondTextField);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.FIRST_LINE_START;
		JLabel blank= new JLabel();
		gridbag2.setConstraints(blank, c2);
		panel.add(blank);
		
		c2.gridwidth = 1;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel endLabel = new JLabel(" Fin : ");
		gridbag2.setConstraints(endLabel, c2);
		panel.add(endLabel);
		
		JTextField secondLineTextField = new JTextField();
		gridbag2.setConstraints(secondLineTextField, c2);
		panel.add(secondLineTextField);
		
		JLabel separatorSecondLineLabel = new JLabel("/");
		gridbag2.setConstraints(separatorSecondLineLabel, c2);
		panel.add(separatorSecondLineLabel);
				
		JTextField fSecondLineTextField = new JTextField();
		gridbag2.setConstraints(fSecondLineTextField, c2);
		panel.add(fSecondLineTextField);
		
		JTextField secondSecondLineTextField = new JTextField();
		gridbag2.setConstraints(secondSecondLineTextField, c2);
		panel.add(secondSecondLineTextField);
		
		JLabel separatorSecondLineSecondLabel = new JLabel("h");
		gridbag2.setConstraints(separatorSecondLineSecondLabel, c2);
		panel.add(separatorSecondLineSecondLabel);
	
		JTextField fSecondLineSecondTextField = new JTextField();
		gridbag2.setConstraints(fSecondLineSecondTextField, c2);
		panel.add(fSecondLineSecondTextField);
		
		c2.gridwidth = 1;
		c2.gridheight = 2;
		JCheckBox dBox = new JCheckBox("D", true);
		gridbag2.setConstraints(dBox, c2);
		panel.add(dBox);
		
		JCheckBox iBox = new JCheckBox("I", true);
		gridbag2.setConstraints(iBox, c2);
		panel.add(iBox);
		
		JButton add = new JButton("Ajouter");
		//add.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(add, c2);
		panel.add(add);
		
		return panel;
	}

	/**
	 * This method create a panel witch permit to get the new
	 * availability of the teacher (date and hours).
	 * 
	 * @param ens the list of the teacher
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createPanelNewAvailability(ArrayList ens){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" Nouveau : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
		
		c2.insets =new Insets(5,2,7,2);
		c2.anchor = GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		
		JLabel day = new JLabel(" Jour : ");
		gridbag2.setConstraints(day, c2);
		panel.add(day);
		
		ArrayList days = new ArrayList();
		days.add("Lundi");
		days.add( "Mardi");
		days.add( "Mercredi");
		days.add( "Jeudi");
		days.add( "Vendredi");
		days.add( "Samedi");
		days.add( "Dimanche");
		
		JComboBox dayCombo = new JComboBox(days.toArray());
		gridbag2.setConstraints(dayCombo, c2);
		panel.add(dayCombo);
				
		JLabel frq = new JLabel("   Fréquence : ");
		gridbag2.setConstraints(frq, c2);
		panel.add(frq);
		
		c2.gridwidth =1; 
		c2.anchor = GridBagConstraints.LINE_START;
		JPanel frqPanel  = createPanelCounter();
		gridbag2.setConstraints(frqPanel , c2);
		panel.add(frqPanel);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		
		JLabel blank = new JLabel();
		gridbag2.setConstraints(blank, c2);
		panel.add(blank);
				
		c2.gridwidth = 1;
		JLabel week = new JLabel(" Semaine :     De : ");
		gridbag2.setConstraints(week, c2);
		panel.add(week);
		
		JPanel dPanel  = createPanelCounter();
		gridbag2.setConstraints(dPanel, c2);
		panel.add(dPanel);
		
		c2.anchor = GridBagConstraints.CENTER;
		JLabel a = new JLabel("   à : ");
		gridbag2.setConstraints(a, c2);
		panel.add(a);
		
		c2.anchor = GridBagConstraints.LINE_START;
		JPanel aPanel  = createPanelCounter();
		gridbag2.setConstraints(aPanel, c2);
		panel.add(aPanel);
		
		JCheckBox dBox = new JCheckBox("D", true);
		gridbag2.setConstraints(dBox, c2);
		panel.add(dBox);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		JCheckBox iBox = new JCheckBox("I", true);
		gridbag2.setConstraints(iBox, c2);
		panel.add(iBox);
		
		c2.gridwidth = 1;
		JLabel hour = new JLabel("  Horaire :      De : ");
		gridbag2.setConstraints(hour, c2);
		panel.add(hour);
		
		c2.anchor = GridBagConstraints.CENTER;
		JPanel firstInter = createPanelInterval("h");
		gridbag2.setConstraints(firstInter, c2);
		panel.add(firstInter);
	
		c2.anchor = GridBagConstraints.CENTER;
		JLabel a2 = new JLabel("   à : ");
		gridbag2.setConstraints(a2, c2);
		panel.add(a2);
		
		JPanel secondInter = createPanelInterval("h");
		gridbag2.setConstraints(secondInter, c2);
		panel.add(secondInter);
		
		JButton add = new JButton("Ajouter");
		//add.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(add, c2);
		panel.add(add);
		
		return panel;
	}

	/**
	 * This method cretaes the panel witch permit to
	 * modify or delete a ressource.
	 * 
	 * @param obj object
	 * @param title the title of the panel
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelRessource(Object[] obj, String title){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" " + title + " : "));
		
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
	
		panel.setLayout(gridbag2);
	
		c2.weightx = 1;
		c2.weighty = 1;
		c2.insets =new Insets(5,5,5,5);
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.FIRST_LINE_START;
		c2.anchor = GridBagConstraints.LINE_START;
		
		JLabel label = new JLabel(" Sélection : ");
		gridbag2.setConstraints(label, c2);
		panel.add(label);
		
		c2.fill = GridBagConstraints.HORIZONTAL;
		JComboBox combo = new JComboBox(((ArrayList)obj[0]).toArray());
		gridbag2.setConstraints(combo, c2);
		panel.add(combo);
	
		c2.anchor= GridBagConstraints.LINE_END;
		JButton delete = new JButton("Supprimer");
		//delete.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(delete, c2);
		panel.add(delete);
		
		c2.anchor = GridBagConstraints.LINE_START;
		JButton modify = new JButton("Modifier");
		//modify.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(modify, c2);
		panel.add(modify);
		
		return panel;
	}



	/**
	 * This method create a section panel.
	 * 
	 * @param refName the reference name
	 * @param listTeacher the list of the teacher
	 * @return the new panel created
	 */
	  public static JPanel createPanelSection(String refName, ArrayList listTeacher){
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
		JLabel refNameLabel = new JLabel(refName);
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
		JTextField nameTextField = new JTextField();
		gridbag2.setConstraints(nameTextField, c2);
		sectionPanel.add(nameTextField);
		
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		JLabel infoLabel = new JLabel("Informations : ");
		gridbag2.setConstraints(infoLabel, c2);
		sectionPanel.add(infoLabel);
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JTextArea infoList= new JTextArea("\n\n");
		
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
		
		JComboBox responsableBox = new JComboBox(listTeacher.toArray());
		gridbag2.setConstraints(responsableBox, c2);
		sectionPanel.add(responsableBox);
		
		return sectionPanel;
	}

	/**
	 * This method creates the panel witch contains the teachers
	 * associated to a subject.
	 * 
	 * @param listTeacher the list of the teacher
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelTeacher(ArrayList listTeacher){		
		JPanel teacherPanel = new JPanel(null);		
		teacherPanel.setBorder(BorderFactory.createTitledBorder(" Enseignants associés : "));
	
		GridBagLayout gridbag3 = new GridBagLayout();
		GridBagConstraints c3 = new GridBagConstraints();
	
		teacherPanel.setLayout(gridbag3);
		
		//Teacher
		int i;
		ArrayList typeCourse = new ArrayList();
		typeCourse.add("c3");
		typeCourse.add("TD");
		typeCourse.add("TP");
	
		c3.anchor = GridBagConstraints.LINE_END;
		
		c3.gridwidth = 10;
		JLabel cLabel = new JLabel("c3    ");
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
		
		for(i =0; i<listTeacher.size(); i++){
			c3.weightx = 1; 
			c3.weighty = 1; 
			c3.insets =new Insets(5,4,5,5);
			c3.gridwidth = 3; 
			c3.fill = GridBagConstraints.HORIZONTAL;
			c3.anchor = GridBagConstraints.LINE_START;
	
			JLabel nameLabel = new JLabel(" Nom ");			
			nameLabel.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
			
			gridbag3.setConstraints(nameLabel, c3);
			teacherPanel.add(nameLabel);
			
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
		JComboBox combo = new JComboBox(listTeacher.toArray());
		combo.setSize(new Dimension(100, combo.getHeight()));
		gridbag3.setConstraints(combo, c3);
		teacherPanel.add(combo);
		
		c3.gridwidth = GridBagConstraints.REMAINDER;
		c3.fill = GridBagConstraints.HORIZONTAL;
		JButton addButton = new JButton("Ajouter");
		//addButton.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag3.setConstraints(addButton, c3);
		teacherPanel.add(addButton);
				
		return teacherPanel;
	}

	/**
	 * This method creates the panel witch contains the text to write
	 * and the logo correspopnding to the type of the message. 
	 * 
	 * @param icon the logo of Day bY Day
	 * @param message the message to write
	 * @return
	 */
	  public static JPanel createPanelText(String message, ImageIcon icon){	
		
		JPanel panel = new JPanel(null);
		panel.setLayout(new BorderLayout());	
		panel.setBorder(BorderFactory.createLineBorder(DBDColor.getColor("DARK_GRAY")));
	
		JLabel msg = new JLabel("    " +message);	
		msg.setIcon(icon);
		panel.add(msg, BorderLayout.CENTER);
		
		return panel;
	}

	/**
	 * This method creates a panel witch all informations
	 * necessary to create a new subject. 
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createPanelType() {
		JPanel typePanel = new JPanel(null);
		
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
	
		typePanel.setLayout(gridbag2);
				
		c2.weightx = 1; 
		c2.weighty = 1; 
		
		c2.anchor = GridBagConstraints.LINE_START;
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;		
		JPanel coursePanel = createPanelTypeSubject(" Cours : ");
		gridbag2.setConstraints(coursePanel, c2);
		typePanel.add(coursePanel);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
	
		c2.anchor = GridBagConstraints.LINE_START;
	
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;		
		JPanel tdPanel = createPanelTypeSubject(" TD : ");
		gridbag2.setConstraints(tdPanel, c2);
		typePanel.add(tdPanel);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
	
		c2.anchor = GridBagConstraints.LINE_START;
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;		
		JPanel tpPanel = createPanelTypeSubject(" TP : ");
		gridbag2.setConstraints(tpPanel, c2);
		typePanel.add(tpPanel);
				
		return typePanel;
	}

	/**
	 * This method creates the panel witch contains informations 
	 * about a type and a group of course. 
	 * 
	 * @return the new penal created.
	 */
	  public static JPanel createPanelTypeAndGroupe(){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" Type et Groupe :  "));
	
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
	
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1; 
	
		c2.insets =new Insets(5,15,5,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel type = new JLabel("Type :");
		gridbag2.setConstraints(type, c2);
		panel.add(type);
		
		JCheckBox course = new JCheckBox("Cours");
		JCheckBox td = new JCheckBox("TD");
		JCheckBox tp = new JCheckBox("TP");
			
		ButtonGroup group = new ButtonGroup();
		group.add(course);
		group.add(tp);
		group.add(td);
		
		gridbag2.setConstraints(course, c2);
		panel.add(course);
		gridbag2.setConstraints(td, c2);
		panel.add(td);
		c2.gridwidth = GridBagConstraints.REMAINDER; 
		gridbag2.setConstraints(tp, c2);
		panel.add(tp);
		
		c2.gridwidth = 1;
		JLabel groupe = new JLabel("Groupe :");
		gridbag2.setConstraints(groupe, c2);
		panel.add(groupe);
		
		//TODO faire par rapport au nbe de groupe
		String[] numberGroupe = new String[3];
		c2.gridwidth = GridBagConstraints.REMAINDER;	
		for (int i=0; i<3; i++) {
			numberGroupe[i] = "groupe ".concat((new Integer(i)).toString()); 
		}
		
		JList list = new JList(numberGroupe);
		list.setVisibleRowCount(3);
		JScrollPane scrollpane = new JScrollPane(list);	
		gridbag2.setConstraints(scrollpane, c2);
		panel.add(scrollpane);
		
		return panel;
	}

	/**
	 * This method creates the panel corresponding to the type 
	 * of the course of the subject. 
	 * 
	 * @param type the name type
	 * 
	 * @return the new panel created
	 */
	  public static JPanel createPanelTypeSubject(String type){
		JPanel typePanel = new JPanel(null);
		typePanel.setBorder(BorderFactory.createTitledBorder(type));
	
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
	
		typePanel.setLayout(gridbag2);
	
		//type de la matiere cours td ou tp
		c2.weightx = 1; 
		c2.weighty = 1; 
		
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		c2.anchor = GridBagConstraints.LINE_START;
		JLabel groupLabel = new JLabel("  Groupe :");
		gridbag2.setConstraints(groupLabel, c2);
		typePanel.add(groupLabel);
		
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.HORIZONTAL;
		JTextField groupeText = new JTextField("");
		gridbag2.setConstraints(groupeText, c2);
		typePanel.add(groupeText);
		
		//hour
		c2.gridwidth = 1; 
		c2.fill = GridBagConstraints.CENTER;
		JLabel hourLabel = new JLabel("    Heure : ");
		gridbag2.setConstraints(hourLabel, c2);
		typePanel.add(hourLabel);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.HORIZONTAL;
		JTextField hourText = new JTextField();
		gridbag2.setConstraints(hourText, c2);
		typePanel.add(hourText);
		
		if (type.compareTo(" Cours : ") == 0){
			WindowCreateSubject.coursgroupeText = groupeText;
			WindowCreateSubject.courshourText = hourText;
		}
		if (type.compareTo(" TD : ") == 0){
			WindowCreateSubject.TDgroupeText = groupeText;
			WindowCreateSubject.TDhourText = hourText;
		}
		if (type.compareTo(" TP : ") == 0){
			WindowCreateSubject.TPgroupeText = groupeText;
			WindowCreateSubject.TPhourText = hourText;
		}
		return typePanel;
	}

	/**
	 * The method create the panel witch contians the list of the 
	 *  login and password of all the users.
	 * 
	 * @param obj the list of the login and password of
	 * all the users.
	 * 
	 * @return the new panel created.
	 */
	  public static JPanel createPanelUser(Object[] obj){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder(" Compte utilisateur : "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = GridBagConstraints.REMAINDER; 
		
		c2.insets =new Insets(2,5,2,5);
		c2.anchor = GridBagConstraints.LINE_START;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel currentAccount = createPanelCurrentAccount(obj);
		JScrollPane currentAccountScrollpane = new JScrollPane(currentAccount);	
		gridbag2.setConstraints(currentAccountScrollpane, c2);
		panel.add(currentAccountScrollpane);
		
		JPanel newAccount = createPanelNewAccount(obj);
		gridbag2.setConstraints(newAccount, c2);
		panel.add(newAccount);
		
		return panel;
	}

	/**
	 * This method create the panel witch permit to adjust
	 * the time interval to display the timetable.  
	 * 
	 * @return the new panel created 
	 */
	  public static JPanel createPanelWeek(){
		JPanel panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder("  Semaines :  "));
		GridBagLayout gridbag2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		panel.setLayout(gridbag2);
		
		c2.weightx = 1; 
		c2.weighty = 1; 
		c2.gridwidth = 1;
		
		c2.insets =new Insets(2,5,2,5);
		c2.anchor = GridBagConstraints.LINE_END;
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel deLabel = new JLabel("    De :");
		gridbag2.setConstraints(deLabel, c2);
		panel.add(deLabel);	
		
		c2.anchor = GridBagConstraints.LINE_START;
		JPanel dePanel = createPanelCounter();
		gridbag2.setConstraints(dePanel, c2);
		panel.add(dePanel);	
		
		c2.anchor = GridBagConstraints.LINE_END;
		JLabel aLabel = new JLabel("        à :");
		gridbag2.setConstraints(aLabel, c2);
		panel.add(aLabel);	
		
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.anchor = GridBagConstraints.LINE_START;	
		JPanel aPanel = createPanelCounter();
		gridbag2.setConstraints(aPanel, c2);
		panel.add(aPanel);	
	
		c2.fill = GridBagConstraints.NONE;
		c2.anchor = GridBagConstraints.CENTER;
		JButton groupButton = new JButton("  Grouper  ");
		//groupButton.setBackground(DBDColor.getColor("DARK_GRAY"));
		gridbag2.setConstraints(groupButton, c2);
		panel.add(groupButton);	
		
		return panel;		
	}

	/**
	 * This method creates the button ok and the button 
	 * Annuler in the container with gridbagconstraint.
	 * 
	 * @param contentPane the Container
	 * @param c2 the GridBagConstraint
	 * @param gridbag2 the gridbag2
	 */
	public static void addButtonValidation(Container contentPane,GridBagConstraints c2, GridBagLayout gridbag2 )
	{
		// Button Ok and Annuler
		c2.fill = GridBagConstraints.CENTER; 
		c2.gridwidth = 1;
		c2.anchor = GridBagConstraints.EAST;
		JButton okButton = new JButton("OK");
		gridbag2.setConstraints(okButton, c2);
		contentPane.add(okButton);
		
		c2.gridwidth = 1;
		c2.anchor = GridBagConstraints.WEST; 
		JButton cancelButton = new JButton("Annuler");
		gridbag2.setConstraints(cancelButton, c2);
		contentPane.add(cancelButton);	
	}

}