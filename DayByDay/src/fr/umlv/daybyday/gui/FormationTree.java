/*
 * Created on 20 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;

import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.model.Equipment;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.FormationTreeModel;
import fr.umlv.daybyday.model.Room;
import fr.umlv.daybyday.model.Section;
import fr.umlv.daybyday.model.Subject;
import fr.umlv.daybyday.model.Teacher;
import fr.umlv.daybyday.test.jms.listeners.CourseFormationTreeListener;
import fr.umlv.daybyday.test.jms.listeners.CourseSectionTreeListener;


/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FormationTree {
	
	private JTree tree;
	private JPanel pane;
	private final JButton tag = new JButton();
	private FormationTreeModel treemodel;
	private TimeTableTable table;
	private JLabel timetabletabletitle;
	private Object[] refs;
	
	MessageListener changerListerner;
	 TopicSubscriber subscriber;
	 
	public FormationTree (FormationElement form, TimeTableTable table,JLabel timetabletabletitle,
			final Object [] refs){
		tree = new JTree();
		treemodel=  new FormationTreeModel (form);
		tree.setModel(treemodel);
		tree.setCellRenderer(new TreeRenderer());
		tree.setRowHeight(20);

		//tree.setEditable(true);

		pane  = new JPanel(new GridLayout(1, 0));
		JScrollPane scrollPane = new JScrollPane(tree);
		tree.setMinimumSize(new Dimension(150, 18));
		tree.setMaximumSize(new Dimension(150, 18));
		tree.setPreferredSize(new Dimension(150, 18));
		pane.add(scrollPane);
	    tree.addMouseListener(new FormationTreeMouseListenere() );
		// Set for the tree renderer
		tag.setBorderPainted(false);
		tag.setSize(200, 18);
		tag.setPreferredSize(new Dimension(150, 18));
		tag.setHorizontalAlignment(SwingConstants.LEADING);
		tag.setMargin(new Insets(0, 0, 0, 0));
		this.table = table;
		this.refs = refs;
		this.timetabletabletitle = timetabletabletitle;
		
        if (form instanceof Formation){
            changerListerner= new CourseFormationTreeListener((FormationDto)form.getDto(), this, MainFrame.myDaybyday);
         }
         else if (form instanceof Section){
            changerListerner = new CourseSectionTreeListener((SectionDto)form.getDto(), this, MainFrame.myDaybyday);
         }
		 listenToCourses();
	}	
	public JPanel getPane() {
		return pane;
	}
	
	public void refresh() {
		
	}
	
    
    /** MouseListener for jtree */
    public class FormationTreeMouseListenere extends MouseAdapter {
       public void mouseClicked( MouseEvent me ) {
       	
       	if ( SwingUtilities.isLeftMouseButton( me ) ) {
            int x = me.getX();
            int y = me.getY();
            int row = tree.getRowForLocation(x,y);
				tree.setSelectionRow(row);
			
			MainFrame mainFrame = (MainFrame) refs[0];
			
			if (row == -1) return;
			Object clickedref = tree.getPathForRow(row).getLastPathComponent();
			

            //Create the popup menu.
            JPopupMenu popup = new JPopupMenu();
            
            
            MainFrame mainframe = (MainFrame)refs[0];
			mainframe.setSelectedObject(clickedref);
            //properties item
            
            
			if (clickedref instanceof Formation){
				
				table.changeSource((FormationElement)clickedref);
				timetabletabletitle.setIcon(Images.getImageIcon("formation"));
				timetabletabletitle.setText("  " +((FormationElement)clickedref).getNamePath());
				mainframe.setTaskBarText(0, ((Formation)clickedref).getFiliereCount() + " filière(s)");
				mainframe.setTaskBarText(1, ((Formation)clickedref).getMatiereCount() + " matière(s)");
				mainframe.setTaskBarText(2,"Source : " + ((FormationElement)clickedref).getNamePath());

			}
			
			if (clickedref instanceof Section){
				table.changeSource((FormationElement)clickedref);
				timetabletabletitle.setIcon(Images.getImageIcon("filiere"));
				timetabletabletitle.setText("  " +((FormationElement)clickedref).getNamePath());
				mainframe.setTaskBarText(0, ((Section)clickedref).getFiliereCount() + " filière(s)");
				mainframe.setTaskBarText(1, ((Section)clickedref).getMatiereCount() + " matière(s)");
				mainframe.setTaskBarText(2,"Source : " + ((FormationElement)clickedref).getNamePath());
			}
			
			if (clickedref instanceof Subject){
				table.changeSource(((Subject)clickedref).getFather());
				if (((Subject)clickedref).getFather() instanceof Section)
				timetabletabletitle.setIcon(Images.getImageIcon("filiere"));
				else if (((Subject)clickedref).getFather() instanceof Formation)
					timetabletabletitle.setIcon(Images.getImageIcon("formation"));
				timetabletabletitle.setText("  " +(((Subject)clickedref).getFather()).getNamePath());
				mainframe.setTaskBarText(0, "Matière");
				mainframe.setTaskBarText(1, "");
				mainframe.setTaskBarText(2,"Source : " + ((Subject)clickedref).getNamePath());
			}
			
         }
          if ( SwingUtilities.isRightMouseButton( me ) ) {
             int x = me.getX();
             int y = me.getY();
             int row = tree.getRowForLocation(x,y);
				tree.setSelectionRow(row);
			
				
			if (row == -1) return;
			Object clickedref = tree.getPathForRow(row).getLastPathComponent();
			

             //Create the popup menu.
             JPopupMenu popup = new JPopupMenu();
             
             MainFrame mainframe = (MainFrame)refs[0];
 			mainframe.setSelectedObject(clickedref);
 			mainframe.setModelSelectedObject(treemodel);
             
             //properties item
             
 			if (clickedref instanceof Formation){
 				popup.add( MenuBarFactory.CreateMenuItem("ActionCreateFiliere",refs));
 				popup.add( MenuBarFactory.CreateMenuItem("ActionCreateSubject",refs));
 				popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation",refs));
			}
 			
 			if (clickedref instanceof Section){
 				popup.add( MenuBarFactory.CreateMenuItem("ActionCreateFiliere",refs));
 				popup.add( MenuBarFactory.CreateMenuItem("ActionCreateSubject",refs));
 				popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation",refs));
 				popup.add( MenuBarFactory.CreateMenuItem("ActionDelete",refs));
			}
 			
 			if (clickedref instanceof Subject){
 				popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation",refs));
 				popup.add( MenuBarFactory.CreateMenuItem("ActionDelete",refs));
			}
 			if (clickedref instanceof Teacher){
 				popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation",refs));
			}
 			if (clickedref instanceof Room){
 				popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation",refs));
			}
 			if (clickedref instanceof Equipment){
 				popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation",refs));
			}
 			
 			
 			
             popup.show( tree, me.getX(), me.getY() );
          }
       }
    };



    
	private class TreeRenderer extends DefaultTreeCellRenderer {
		/**
		 * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
		 */
		public Component getTreeCellRendererComponent(
			JTree tree,
			Object value,
			boolean selected,
			boolean expanded,
			boolean leaf,
			int row,
			boolean hasFocus) {
			
			if (value instanceof Formation){
				Formation element = (Formation)value;
				tag.setIcon(Images.getImageIcon("formation"));
				tag.setText(element.getName());
				
			}
			
			if (value instanceof Section){
				Section element = (Section)value;
				tag.setIcon(Images.getImageIcon("filiere"));
				tag.setText(element.getName());
			}
			
			if (value instanceof Subject){
				Subject element = (Subject)value;
				tag.setIcon(Images.getImageIcon("matiere"));
				tag.setText(element.getName());
			}
			
			if (value instanceof Teacher){
				Teacher element = (Teacher)value;
				tag.setIcon(Images.getImageIcon("enseignant"));
				tag.setText(element.getName());
			}
			
			if (value instanceof Room){
				Room element = (Room)value;
				tag.setIcon(Images.getImageIcon("salle"));
				tag.setText(element.getName());
			}
			
			if (value instanceof Equipment){
				Equipment element = (Equipment)value;
				tag.setIcon(Images.getImageIcon("materiel"));
				tag.setText(element.getName());
			}
			
			boolean many = false;
			tag.setBorderPainted(false);

			tag.removeAll();
			tag.setBackground(new Color(255, 255, 255));
			tag.setForeground(new Color(0, 0, 0));
			String tmp = "pas de tool";
	/*		
			if (element.getParentFile() == null) {
				tmp = element.getPath();
				tag.setIcon(Images.getImageIcon(((Element) value).getType(), Images.SMALL));
				tag.setText(element.getPath());
				
			} else {
				tmp = element.getName();
	
				tag.setIcon(Images.getImageIcon(((Element) value).getType(), Images.SMALL));
				tag.setText(element.getName());
			}
		*/	this.setToolTipText(tmp);
	
			if (selected){
				tag.setBackground(new Color(183, 207, 225));
				tag.setForeground(new Color(255, 255, 255));
			}
	      /*  int type = 0; 
	        if (MainFrame.fontbold) type += Font.BOLD;
	        if (MainFrame.fontitalic) type += Font.ITALIC;
	        if (MainFrame.fontunderline) type += Font.CENTER_BASELINE;
	        
			tag.setFont(new Font(MainFrame.font,type,MainFrame.fontsize));
			*/return tag;
		}
	}

	 public void listenToCourses(){
        try{
            Context jndiContext = getInitialContext();
            TopicConnectionFactory factory = (TopicConnectionFactory)
                                             jndiContext.lookup(
                    "ConnectionFactory");
            Topic topic = (Topic) jndiContext.lookup(
                    "topic/CourseTopic");
            TopicConnection connect = factory.createTopicConnection();
            TopicSession session = connect.createTopicSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            subscriber = session.createSubscriber(topic);

            subscriber.setMessageListener(changerListerner);
            connect.start();
            System.out.println(
                    "Attend l'arrivee de messages sur topic/CourseTopic...");
        }
        catch(Exception ex){
            //ex.printStackTrace();

        }
    }

    public static Context getInitialContext() throws NamingException {
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        environment.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        return new InitialContext(environment);
    }
}
