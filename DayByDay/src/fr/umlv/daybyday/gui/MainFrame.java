/*
 * Created on 20 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.swing.*;



import fr.umlv.daybyday.actions.InstancesActions;

import fr.umlv.daybyday.model.Course;
import fr.umlv.daybyday.model.Equipment;
import fr.umlv.daybyday.model.Grid;
import fr.umlv.daybyday.model.Room;
import fr.umlv.daybyday.model.Section;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.Subject;
import fr.umlv.daybyday.model.Teacher;

import fr.umlv.daybyday.ejb.facade.Daybyday;
import fr.umlv.daybyday.ejb.facade.DaybydayHome;
import fr.umlv.daybyday.ejb.facade.DaybydayHomeCache;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationPK;
import fr.umlv.daybyday.gui.MenuBarFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MainFrame {
	private JFrame frame;
	private Container container;
	private JLayeredPane layerPane;
	private FormationTree tree;
	private ArrayList TimeTable;
	private HashMap tabbepanelist;
	private JTabbedPane tabepane;
	private ArrayList tabbepanelistname;
	private Object [] refs;
	private JMenuBar menubar;
	private JToolBar toolbar;
	private MainTaskBar taskbar;
	private ArrayList selectedTable;
	private ArrayList treesrc;
	private Object selectedObject;
	private Object selectedModelObject;
	
	public static String font = "Arial";
	public static int fontsize = 12;
	public static boolean fontbold = false;
	public static boolean fontitalic = false;
	public static boolean fontunderline = false;
	
	public static DaybydayHome DaybydayHome;
	public static Daybyday myDaybyday;
	
	public MainFrame() throws NamingException, RemoteException, CreateException {
		
		DaybydayHome = (DaybydayHome)DaybydayHomeCache.getHome();
        myDaybyday = DaybydayHome.create();
        
        treesrc = new ArrayList();
        
		frame = new JFrame("DaYbYDaY");
		frame.setIconImage(Images.getImage("daybyday"));
		frame.setSize(850, 600);
		layerPane = new JLayeredPane();
		frame.setLayeredPane(layerPane);
		//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tabbepanelist = new HashMap();
		tabbepanelistname = new ArrayList();
		container = frame.getContentPane();
		
		final Object [] refs = new Object [1];
		refs[0] = this;
		this.refs = refs;
		selectedTable = new ArrayList();
        tabepane = new JTabbedPane();
        
        selectedObject = null;
        selectedModelObject = null;
        tabepane.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent me) {
	        	if ( SwingUtilities.isRightMouseButton( me ) ) {
	                int x = me.getX();
	                int y = me.getY();
	    							
	                //Create the popup menu.
	                JPopupMenu popup = new JPopupMenu();
	                Object [] refsTab = new Object[3];
	                refsTab[0] = refs[0];
	                refsTab[1] = new Integer(tabepane.getSelectedIndex());    
	                refsTab[2] = tabepane;
	                

	                popup.add( MenuBarFactory.CreateMenuItem("ActionCloseTabbePane",refsTab));
	                popup.add( MenuBarFactory.CreateMenuItem("ActionCloseAllTabbePane",refsTab));
	                popup.add( MenuBarFactory.CreateMenuItem("ActionCloseInverseTabbePane",refsTab));
	                
	                popup.show( tabepane, me.getX(), me.getY() );
	              }
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
       
        tabepane.setMinimumSize(new Dimension(800,500));
        tabepane.setPreferredSize(new Dimension(800,500));
        
        tabepane.setTabPlacement(3);

		container.add(tabepane,BorderLayout.CENTER);
		String [] task = {"","","                        "};
		taskbar = new MainTaskBar(task);
		container.add(taskbar,BorderLayout.SOUTH);
		
		frame.setContentPane(container);
		

		
		menubar = createMenuBar ();
		toolbar = CreateToolBar(refs);
		toolbar.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent me) {
	        	if ( SwingUtilities.isRightMouseButton( me ) ) {
	                int x = me.getX();
	                int y = me.getY() + 25;
	                //Create the popup menu.
	                JPopupMenu popup = new JPopupMenu();
	                popup.add( MenuBarFactory.CreateMenuItem("ActionToolBarConfig",refs));
	                popup.add( MenuBarFactory.CreateMenuItem("ActionToolBarShowHide",refs));                
	                popup.show( menubar, x, y);
	              }
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.setJMenuBar(menubar);

		frame.add(toolbar, BorderLayout.PAGE_START);
		
		
	//	frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponentListener(new  ComponentListener() {

			public void componentResized(ComponentEvent arg0) {
				tabepane.setSize(new Dimension(frame.getSize().width - 10,frame.getSize().height - (80 + 25)));
				taskbar.setPositionLabels(frame.getSize().width);
				for (int i = 0; i < selectedTable.size();++i)
					((TimeTableTable) selectedTable.get(i)).changeSize(frame.getSize().width,frame.getSize().height - (100 + 40 + 40 + 20 + 30));
			}

			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		frame.pack();
		frame.setVisible(true);
	}
	
	private JMenuBar createMenuBar (){
			JMenuBar menuBar = new JMenuBar();

//		model.addGXpLoRListener(new MenuBarListener());

		//menu File
		JMenu menu = new JMenu("Fichier");
		
		ArrayList actionlist = new ArrayList();
		actionlist.clear(); actionlist.add("ActionCreateFormation"); actionlist.add("ActionCreateTeacher");actionlist.add("ActionCreateRoom"); actionlist.add("ActionCreateStuf");
		menu.add(MenuBarFactory.CreateMultiMenu ("Nouveau", "CreateFormation", actionlist, refs));
		
		actionlist.clear(); actionlist.add("ActionOpenFormation"); actionlist.add("ActionOpenTeacher");actionlist.add("ActionOpenRoom"); actionlist.add("ActionOpenStuf");
		menu.add(MenuBarFactory.CreateMultiMenu ("Ouvrir", "OpenFormation", actionlist, refs));
		
		menu.add(MenuBarFactory.CreateMenuItem ("ActionClose", refs));
		menu.add(new JSeparator());
		menu.add(MenuBarFactory.CreateMenuItem ("ActionDelete", refs));
		menu.add(new JSeparator());
		menu.add(MenuBarFactory.CreateMenuItem ("ActionExport", refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionPrint", refs));
		menu.add(new JSeparator());
		menu.add(MenuBarFactory.CreateMenuItem ("ActionUnlog", refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionQuit", refs));
		menuBar.add(menu);
		
		menu = new JMenu("Edition");
		menu.add(MenuBarFactory.CreateMenuItem ("ActionCancel", refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionRedo", refs));
		menu.add(new JSeparator());
		menu.add(MenuBarFactory.CreateMenuItem ("ActionCut", refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionCopy", refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionPaste", refs));
		menu.add(new JSeparator());
		menu.add(MenuBarFactory.CreateMenuItem ("ActionSelectAll", refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionUnselect", refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionInvertSelect", refs));
			
		menuBar.add(menu);
		
		menu = new JMenu("Affichage");
		actionlist.clear(); actionlist.add("ActionFontBold"); actionlist.add("ActionFontItalic");actionlist.add("ActionFontUnderline");
		menu.add(MenuBarFactory.CreateMultiMenu ("Style", "close", actionlist, refs));
		actionlist.clear(); actionlist.add("ActionToolBarShowHide"); actionlist.add("ActionToolBarConfig");
		menu.add(MenuBarFactory.CreateMultiMenu ("Barre d'outil", "close", actionlist, refs));
		actionlist.clear(); actionlist.add("ActionGridDetailPerso");  actionlist.add("ActionGridDetailStudent");  actionlist.add("ActionGridDetailTeacher");  actionlist.add("ActionGridDetailCreate");
		menu.add(MenuBarFactory.CreateMultiMenu ("Détail", "close", actionlist, refs));
		actionlist.clear(); actionlist.add("ActionGridVisualWeek");  actionlist.add("ActionGridVisualMultiWeek");
		menu.add(MenuBarFactory.CreateMultiMenu ("Visualisation", "close", actionlist, refs));
		menu.add(MenuBarFactory.CreateMenuItem ("ActionGridConfig", refs));
		
		
		menuBar.add(menu);
		
		menu = new JMenu("Gestion");
		actionlist.clear();actionlist.add("ActionCourseAdd"); 
		actionlist.add("ActionCourseModify"); 
		actionlist.add("ActionCourseCancel"); 
		actionlist.add("ActionCourseUncancel");
		menu.add(MenuBarFactory.CreateMultiMenu ("Cours", "cours", actionlist, refs));
		actionlist.clear();actionlist.add("ActionDispoTeacher");  
		actionlist.add("ActionDispoStuf"); 
		actionlist.add("ActionDispoRoom"); 
		actionlist.add("ActionDispoGeneral");
		menu.add(MenuBarFactory.CreateMultiMenu ("Disponibilités", "dispo", actionlist, refs));
		menuBar.add(menu);
		
		menu = new JMenu("Administrer");
		menu.add(MenuBarFactory.CreateMenuItem ("ActionAdmin", refs));
		//TODO
		menuBar.add(menu);
		
		menu = new JMenu("Aide");
		menu.add(MenuBarFactory.CreateMenuItem ("ActionHelp", refs));
		//TODO
		menuBar.add(menu);
		
		return menuBar;
	
	}
	
	private void ChangeIndexTimeTable (int index, Formation form){
		
	}
	
	public void addFormationTabbePane(FormationElement form){
		
		JSplitPane tmp = createFormationTabbePane(form);
		tabbepanelist.put(form.getName(),tmp);
		tabbepanelistname.add(form.getName());
		  tabepane.addTab(form.getName(),tmp);
		 
	}
	
	private JSplitPane createFormationTabbePane(FormationElement form){
		JSplitPane splitpane;
		
		splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		container.add(splitpane, BorderLayout.CENTER);
		

		
		// Test de l'arbre
		
		
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

      //  c.gridwidth = 10;
        JPanel rightpane  = new JPanel(gridbag);
        JLabel titlelab = new JLabel();
        JLabel lab = new JLabel();
        
        JScrollPane scrollPane;
        
        if (form instanceof Teacher){
        	Teacher ens = (Teacher)form;
        c.gridwidth = GridBagConstraints.REMAINDER ;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
        c.gridwidth = 4;

        lab = new JLabel("   Enseignant : ",JLabel.LEADING);
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		titlelab = new JLabel(ens.getNamePath());
		titlelab.setIcon(Images.getImageIcon("enseignant"));	
		titlelab.setBackground(new Color (255,255,255));
		titlelab.setBorder(BorderFactory.createBevelBorder(1));
        gridbag.setConstraints(titlelab, c);
		rightpane.add(titlelab);

        lab = new JLabel("         Bureau : ");
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		lab = new JLabel("     " + ens.getRoom());
		lab.setIcon(Images.getImageIcon("salle"));	
		lab.setBackground(new Color (255,255,255));
		lab.setBorder(BorderFactory.createBevelBorder(1));
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
        }
        if (form instanceof Formation){
        	Formation formation = (Formation)form;
        
        c.gridwidth = GridBagConstraints.REMAINDER ;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
        c.gridwidth = 4;

        lab = new JLabel("   Source : ",JLabel.LEADING);
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		titlelab = new JLabel(form.getNamePath());
		titlelab.setIcon(Images.getImageIcon("filiere"));	
		titlelab.setBackground(new Color (255,255,255));
		titlelab.setBorder(BorderFactory.createBevelBorder(1));
        gridbag.setConstraints(titlelab, c);
		rightpane.add(titlelab);

        lab = new JLabel("         Responsable : ");
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		lab = new JLabel("     " + formation.getResponsable());
		lab.setIcon(Images.getImageIcon("enseignant"));	
		lab.setBackground(new Color (255,255,255));
		lab.setBorder(BorderFactory.createBevelBorder(1));
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		
		lab = new JLabel("          Salle : ");
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		lab = new JLabel("   "+ formation.getRoom()+"  "  , Images.getImageIcon("salle"), JLabel.LEADING );
		lab.setBackground(new Color (255,255,255));
		lab.setBorder(BorderFactory.createBevelBorder(1));
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		
		lab = new JLabel("           Année : ");
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
		lab = new JLabel("  " + formation.getYear() + "  ");
		lab.setBackground(new Color (255,255,255));
		lab.setBorder(BorderFactory.createBevelBorder(1));
        gridbag.setConstraints(lab, c);
		rightpane.add(lab);
        }
		
		
		c.gridwidth = GridBagConstraints.RELATIVE; 

		TimeTableTable tttt = new TimeTableTable(Grid.gridBgHour,Grid.gridEndHour,Grid.gridNbDays, Grid.gridEndHour - Grid.gridBgHour, Grid.gridSlice, form, refs);
		JPanel ttt = tttt.getPane();
		selectedTable.add(tttt);
		JPanel rightpaneall = new JPanel(new BorderLayout());
		JScrollPane jscr1 = new JScrollPane(ttt);
		//jscr1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		rightpaneall.add(jscr1, BorderLayout.CENTER);

		rightpaneall.add(rightpane, BorderLayout.NORTH);
		rightpaneall.setPreferredSize(new Dimension(700,150));
		rightpaneall.repaint();
		JScrollPane jscr = new JScrollPane(rightpaneall);
		//jscr.setMinimumSize(new Dimension(400,450));
		jscr.getViewport().setSize(new Dimension(1000,600));
		splitpane.setRightComponent(rightpaneall);
		

		
		JPanel leftpane = new JPanel(new BorderLayout());
		if (form instanceof Formation){
		lab = new JLabel("  Formation  ");
		leftpane.add(lab, BorderLayout.NORTH);
		}
		if (form instanceof Teacher){
			lab = new JLabel("  Enseignant  ");
			leftpane.add(lab, BorderLayout.NORTH);
			}
		if (form instanceof Room){
			lab = new JLabel("  Salle  ");
			leftpane.add(lab, BorderLayout.NORTH);
			}
		if (form instanceof Equipment){
			lab = new JLabel("  Matériel  ");
			leftpane.add(lab, BorderLayout.NORTH);
			}
		FormationTree ft = new FormationTree(form,tttt,titlelab, refs);
		leftpane.add(ft.getPane(), BorderLayout.CENTER);
		
		JLabel semaine = new JLabel();
		Object [] week = new Object [refs.length + 2];
		week[0] = refs[0];
		week[1] = semaine;
		week[2] = tttt;
		JButton next = new JButton(InstancesActions.getAction("ActionWeekNext",week));next.setText("");next.setToolTipText(MnemonicMap.getToolTip("ActionWeekNext"));next.setPreferredSize(new Dimension(30,30));
		JButton prec = new JButton(InstancesActions.getAction("ActionWeekBefore",week));prec.setText("");prec.setToolTipText(MnemonicMap.getToolTip("ActionWeekBefore"));prec.setPreferredSize(new Dimension(30,30));

		JPanel leftpanesouth = new JPanel(new FlowLayout());
		leftpanesouth.setBorder(BorderFactory.createTitledBorder("Semaine"));
		leftpanesouth.add(prec);
		leftpanesouth.add(semaine);
		leftpanesouth.add(next);
		
		leftpane.add(leftpanesouth, BorderLayout.SOUTH);
		leftpane.setPreferredSize(new Dimension(200,450));
		leftpane.setMinimumSize(new Dimension(200,450));
		//leftpane.setMaximumSize(new Dimension(200,150));
		leftpane.setSize(0,150);
		splitpane.setLeftComponent(leftpane);
		
		splitpane.setDividerSize(3);
		return splitpane;
	}
	
	private JButton createToolBarButton (String Actionref, Object [] refs){
		JButton button = null;
		button = new JButton(InstancesActions.getAction(Actionref,refs));
		button.setToolTipText(MnemonicMap.getToolTip(Actionref));
		button.setText("");
		button.setBorderPainted(false);
		button.setBackground(new Color(227,227,227));
		return button;
	}
	
	
	private JToolBar CreateToolBar (final Object [] refs){
		JToolBar toolBar = new JToolBar("Standard");

		toolBar.add(createToolBarButton("ActionCut",refs));
		toolBar.add(createToolBarButton("ActionCopy",refs));
		toolBar.add(createToolBarButton("ActionPaste",refs));
		toolBar.add(new JToolBar.Separator());
		toolBar.add(createToolBarButton("ActionCancel",refs));
		toolBar.add(createToolBarButton("ActionRedo",refs));
		toolBar.add(new JToolBar.Separator());
		toolBar.add(createToolBarButton("ActionExport",refs));
		toolBar.add(createToolBarButton("ActionPrint",refs));
		toolBar.add(new JToolBar.Separator());
		
		
		final JComboBox fontcombo = new JComboBox();
		Font [] allfont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		
		for (int i = 0; i < allfont.length; ++i)
		fontcombo.addItem(allfont[i].getFontName());

		final JComboBox fontsizecombo = new JComboBox();
		fontsizecombo.addItem("8");
		fontsizecombo.addItem("10");
		fontsizecombo.addItem("12");
		fontsizecombo.addItem("14");
		fontsizecombo.addItem("16");
		fontsizecombo.setSelectedIndex(2);

		fontsizecombo.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
			
		        MainFrame.fontsize = 	Integer.parseInt((String)fontsizecombo.getSelectedItem());
				frame.repaint();
			}
		});
		
		fontcombo.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
		        MainFrame.font = (String)fontcombo.getSelectedItem();
				frame.repaint();
			}
		});




		fontcombo.setSize(new Dimension(100,20));
		fontcombo.setPreferredSize(new Dimension(200,20));
		fontcombo.setMaximumSize(new Dimension(200,20));
		fontcombo.setMinimumSize(new Dimension(200,20));
		toolBar.add(fontcombo);
		toolBar.add(new JToolBar.Separator());
		fontsizecombo.setSize(new Dimension(50,20));
		fontsizecombo.setPreferredSize(new Dimension(50,20));
		fontsizecombo.setMaximumSize(new Dimension(50,20));
		fontsizecombo.setMinimumSize(new Dimension(50,20));
		toolBar.add(fontsizecombo);
		toolBar.add(new JToolBar.Separator());
		toolBar.add(createToolBarButton("ActionFontBold",refs));
		toolBar.add(createToolBarButton("ActionFontItalic",refs));
		toolBar.add(createToolBarButton("ActionFontUnderline",refs));
		return toolBar;
	}
	
	
	public void removeTabbePane (int i){
		selectedTable.remove(i);
		tabepane.removeTabAt(i);
	}
	
	public void removeAllTabbePane (){
		System.out.println(tabepane.getTabCount());
		int size = tabepane.getTabCount();
		for (int i = 0; i < size; ++i ){
		tabepane.removeTabAt(0);
		selectedTable.remove(0);
		}
	}
	
	public void removeAllExecptTabbePane (int index){
		int size = tabepane.getTabCount();
		int j = 0;
		for (int i = 0; i < size; ++i )
			if (i != index){ tabepane.removeTabAt(j);
			selectedTable.remove(j);
			}
			else j++;
			
	}
	
	public void showHideToolBar(){
			toolbar.setVisible(!toolbar.isVisible());	
	}

	public void setTaskBarText(int i, String string) {
		taskbar.setTextofLabel(i,string);
		
	}

	public void closeApp() {
		frame.dispose();
		
	}
	
	public void showError (JFrame frame, String message){
		JOptionPane.showMessageDialog(frame,
				message,
			    "Erreur",
			    JOptionPane.ERROR_MESSAGE,
			    Images.getImageIcon("logo 190X129"));

	}
	public void showApropos (){
		JOptionPane.showMessageDialog(frame,
			    "      Day by Day - 2004/2005 \n Université de Marne la Vallée\n\nEquipe DaYbYDaY : \n\n" +
			    "             V. Verriere\n" +
			    "             E. Emond\n" +
			    "             M. Abdennebi\n" +
			    "             I. Boudigou\n" +
			    "             M. Meynier",
			    "A propos ... ",
			    JOptionPane.INFORMATION_MESSAGE,
			    Images.getImageIcon("logo 190X129"));

	}
	
	public void setEnable (boolean state){
		frame.setEnabled(state);
	}
	

	
	
	/**
	 * 
	 */
	public void reLog() {
		removeAllTabbePane();
		setEnable (false);
		Windows.createWindow("WindowLogin",refs);
		
	}
	
	public void fontChange(){
					frame.repaint();
	}
	
	public void setSelectedObject (Object ref){
		selectedObject = ref;
	}
	public void setModelSelectedObject (Object ref){
		selectedModelObject = ref;
	}
	
	public Object getSelectedObject (){
		return selectedObject;
	}
	
	public Object getModelSelectedObject (){
		return selectedModelObject;
	}

	/**
	 * @return
	 */
	public String userName() {
		// TODO Auto-generated method stub
		return "VERRIERE";
	}

	/**
	 * @return
	 */
	public String userFirstname() {
		// TODO Auto-generated method stub
		return "Victor";
	}
	
}
