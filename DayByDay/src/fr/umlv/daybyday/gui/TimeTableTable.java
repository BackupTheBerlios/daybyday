/*
 * Created on 23 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;




import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.jms.JMSException;
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
import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fr.umlv.daybyday.actions.ActionCourseAdd;
import fr.umlv.daybyday.actions.ActionPaste;
import fr.umlv.daybyday.actions.InstancesActions;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.model.Course;
import fr.umlv.daybyday.model.CourseDetail;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.Grid;
import fr.umlv.daybyday.model.Section;
import fr.umlv.daybyday.model.Teacher;
import fr.umlv.daybyday.test.jms.listeners.CourseFormationListener;
import fr.umlv.daybyday.test.jms.listeners.CourseSectionListener;
import fr.umlv.daybyday.test.jms.listeners.CourseTeacherListener;



//import fr.umlv.daybyday.model.TimeTableTableModel;


/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TimeTableTable {


	private JTable timetable [];

	private int nbday;
	private int nbhours;
	private int nbhoursslice;
	protected JPanel pane;
	private int columheigth;
	private int tableheigth;
	private int bghour, endhour;
	int columsize = 80/Grid.gridSlice;
	private Object[] refs;
	//private ArrayList daytags;
	private final JTextPane tag = new JTextPane();
	private final ArrayList taglist = new ArrayList();
	private final GregorianCalendar cal = new GregorianCalendar();
	private final TimeTableTable me = this;
	
	private FormationElement cours;
	final static String []  daytitles = {"","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
	final static String [] hourstitle = {"0h","1h","2h","3h","4h","5h","6h","7h", "8h","9h","10h","11h","12h","13h","14h","15h","16h","17h","18h","19h","20h","21h","22h","23h"};
	
	MessageListener changerListerner;
	 TopicSubscriber subscriber;
	 
	public TimeTableTable(
			final int bghour,
			final int endhour,
			final int nbday,
			final int nbhours,
			final int nbhoursslice,
			final FormationElement cours,
			final Object [] refs
	) {
		this.cours = cours;
		this.endhour = endhour;
		this.bghour = bghour;
		this.nbday = nbday;
		this.nbhours = nbhours;
		this.nbhoursslice = nbhoursslice;
		this.tableheigth = nbday * 400/nbday;
		this.columheigth = 400/nbday + 10;
		this.refs = refs;
		timetable = new JTable[7 + 1];

		
		tag.setMargin(new Insets(4, 4, 4, 4));
		
					
		timetable[0] = new JTable();
		timetable[0].setRowHeight(20);
		timetable[0].setDragEnabled(false);
		
		timetable[0].setModel(new AbstractTableModel() {
		    public String getColumnName(int col) {
		        return TimeTableTable.hourstitle[col+bghour].toString();
		    }
		    public int getRowCount() { return 1; }
		    public int getColumnCount() {return nbhours; }
		    public Object getValueAt(int row, int col) {
		        return col + "h";//hourstitle[col+bghour].toString();//cours[col];
		    }
		    public boolean isCellEditable(int row, int col)
		        { return false; }
		});
	
		 TableColumnModel tableColumnModelDetail = new DefaultTableColumnModel();
		 
		for (int j = bghour; j < endhour; ++j ){
			TableColumn tableColumn = new TableColumn(j, columsize * nbhoursslice);
			tableColumn.setMaxWidth(columsize * nbhoursslice);
			tableColumn.setMinWidth(columsize * nbhoursslice);
			tableColumn.setPreferredWidth(columsize * nbhoursslice);
			tableColumn.setHeaderValue(TimeTableTable.hourstitle[j].toString());
			tableColumn.setCellRenderer(new DefaultTableCellRenderer());
			tableColumnModelDetail.addColumn(tableColumn);
			tableColumn.setCellRenderer(new TableCellRenderer(){

				public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
					JButton jb = new JButton((String)arg1);
					jb.setPreferredSize(new Dimension(columsize * nbhoursslice,20));
					return jb;
				}
				
			});
			
			}

		timetable[0].setColumnModel(tableColumnModelDetail);
		
		
		for (int i = 1; i < 7 + 1; ++ i){
			timetable[i] = new JTable();
			timetable[i].setBorder(BorderFactory.createBevelBorder(1));
			timetable[i].setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			timetable[i].setColumnSelectionAllowed(true);
			timetable[i].setRowSelectionAllowed(false);
			

			timetable[i].addMouseListener(new TimeTableMouseListenere(timetable,i));
		
			int [] sizeRow = new int [nbhours*nbhoursslice];
			int [] coursRow = new int [nbhours*nbhoursslice];
			
			timetable[i].setRowHeight(columheigth);
			
			for (int k = 0; k < sizeRow.length; ++k ){
				sizeRow[k] = 1;
				coursRow[k] = -1;
			}
			
			int nbRow = nbhours*nbhoursslice;
			
			coursRow = computeTimeSlice(i,coursRow,sizeRow,nbRow,cours.getCourseList().toArray());
			nbRow = coursRow.length;
			final int finalNbRow = nbRow;
			final int [] finalSizeRow = coursRow;

			timetable[i].setModel(new TimeTableTableModel(finalSizeRow,nbRow,cours));
			 tableColumnModelDetail = new DefaultTableColumnModel();
			 
			for (int j = 0,colind = 0; j < nbRow; ++j ){
				while (sizeRow[colind] == 0) colind++;
				int columsizecalc = columsize * (sizeRow[colind]);
				if (sizeRow[colind] > 1)
					columsizecalc = columsize * (sizeRow[colind] + 1);
				
				TableColumn tableColumn = new TableColumn(j);
				tableColumn.setMaxWidth(columsizecalc);
				tableColumn.setMinWidth(columsizecalc);
				//tableColumn.setPreferredWidth(columsizecalc);
				colind++;
				
				
				
				tableColumn.setCellRenderer(new TimeTableCellRenderer());
				tableColumnModelDetail.addColumn(tableColumn);
				}

			tableColumnModelDetail.setColumnSelectionAllowed(true);
			timetable[i].setColumnModel(tableColumnModelDetail);
			timetable[i].setPreferredScrollableViewportSize(new Dimension(800,600));
		}

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

		pane  = new JPanel(gridbag);
		
		pane.setPreferredSize(new Dimension(columsize*(nbhours + 1)*nbhoursslice + 50 ,tableheigth));
		
		taglist.clear();
		
		cal.setTimeInMillis(Grid.calendar.getTimeInMillis());
		for (int i = 0; i < 7 + 1; ++ i){

			JButton jb = new JButton(daytitles[i] + " " + cal.get(Calendar.DAY_OF_MONTH) +"/"+(cal.get(Calendar.MONTH)+1));
			if (i != 0)
			cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR) + 1);
			else jb.setText("");
			taglist.add(jb);
			if (i != 0){
			jb.setMaximumSize(new Dimension(120,10));
			jb.setMinimumSize(new Dimension(120,10));
			jb.setPreferredSize(new Dimension(120,10));
			}
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.RELATIVE ;
			if (i == 0) c.weighty = 0.0;
	        else
			c.weighty = 10.0;
	        c.weightx = 1.0;
	        //c.gridwidth = 10;
	        gridbag.setConstraints(jb, c);

				pane.add(jb);
				
				if (i == 0) c.weighty = 0.0;
		        else c.weighty = 20.0;
				
		    c.weightx = 12.0;
			c.gridwidth = GridBagConstraints.REMAINDER; 
			c.fill = GridBagConstraints.BOTH;
	        gridbag.setConstraints(timetable[i], c);

			pane.add(timetable[i]);

		}	

		 if (7 >  nbday){
		 	for (int i = 7 ; i > this.nbday; --i){
		 		((JButton)taglist.get(i)).setVisible(false);
		 		timetable[i].setVisible(false); 		
		 		}
		 }
		 
         if (cours instanceof Formation){
            changerListerner= new CourseFormationListener((FormationDto)cours.getDto(), this, MainFrame.myDaybyday);
         }
         else if (cours instanceof Section){
            changerListerner = new CourseSectionListener((SectionDto)cours.getDto(), this, MainFrame.myDaybyday);
         }
         else if (cours instanceof Teacher){
            changerListerner = new CourseTeacherListener((TeacherDto)cours.getDto(), this, MainFrame.myDaybyday);
         }
		 listenToCourses();
	}
	
	public JPanel getPane() {
		return pane;
	}

	private int []computeTimeSlice (int day, int [] coursRow,int [] sizeRow, int nbRow, Object [] cours){
	
		int nbRowSave;
		
		int daycmp;
		int deb;
		try{
		if (cours != null){
			nbRowSave = nbRow;
			for (int i = 0; i < cours.length; ++i){
				CourseDto coursdto = (CourseDto) ((Course)cours[i]).getDto();
				cal.setTimeInMillis(coursdto.getStartDate().getTime());
//System.out.println(gc.get(Calendar.WEEK_OF_YEAR) +"=="+ Grid.calendar.get(Calendar.WEEK_OF_YEAR)+ "&&"+ gc.get(Calendar.DAY_OF_WEEK) +"=="+ (day+1));
				daycmp = cal.get(Calendar.DAY_OF_WEEK); 
				daycmp -= 1;
				if (daycmp == 0) daycmp = 7;
//System.out.println(gc.get(Calendar.HOUR_OF_DAY)+">="+ Grid.gridBgHour);
				if (cal.get(Calendar.HOUR_OF_DAY) >= Grid.gridBgHour && cal.get(Calendar.WEEK_OF_YEAR) == Grid.calendar.get(Calendar.WEEK_OF_YEAR) && daycmp == ((day))){
					deb = -1;
					for (int j = 0; j < nbhours; ++j){
						for (int k = 0; k < nbhoursslice; ++k){
								if (j*nbhoursslice + k >= ((Course)cours[i]).getBgHour()*nbhoursslice + ((Course)cours[i]).getBgMin() && 
								    j*nbhoursslice + k < ((Course)cours[i]).getEndHour()*nbhoursslice + ((Course)cours[i]).getEndMin()	){
									if (deb == -1){
											deb = j*nbhoursslice + k ;
											sizeRow[deb] = 0;
											coursRow[deb] = i;
									}
									else {
										sizeRow[j*nbhoursslice + k] = 0;
										sizeRow[deb]++;
									}
								}
									
						}
					}
				}
			}
		}

	
		int j = 0;
		for (int i = 0 ; i < sizeRow.length ; ++i){
			if (sizeRow[i] == 0)
				nbRow--;
		}
		int [] coursRowtmp = new int[nbRow];
		
		for (int i = 0 ; i < sizeRow.length ; ++i){
			if (sizeRow[i] == 0){
			}
			else {
				coursRowtmp[j] = coursRow[i];
				j++;
			}
		}
		
		return coursRowtmp;
		}catch(NullPointerException e ){ return null;}
		
	}
	
	public class PerspectiveListSelectionModel extends DefaultListSelectionModel {
		
		JTable ref;
		
		public PerspectiveListSelectionModel (JTable ref){
			this.ref = ref;
		}
		public void setSelectionInterval(int index0, int index1) {
			System.out.println(index0 + " " + index1 + " " + ref.getSelectedRows());
			int [] refs =  ref.getSelectedRows();
			for (int i = 0 ; i < refs.length; ++i){
				System.out.print(" " + refs[i]);
			}
			if (index0 != index1 || !super.isSelectedIndex(index0))
				super.setSelectionInterval(index0, index1);
		}
	}
	

	private class TimeTableMouseListenere extends MouseAdapter {
		
		JTable [] table;
		int index;
		int rowdeb = -1;

		
		public TimeTableMouseListenere (JTable [] table, int index ){
		 this.table = table;	
		 this.index = index;
		}
		

		public void mousePressed (MouseEvent me ){
			if ( SwingUtilities.isRightMouseButton( me ) ){
				int x = me.getX();
	             int y = me.getY(); 
	             rowdeb = table[index].columnAtPoint(me.getPoint());
				//System.out.println("tu clic du droit la?");
			}
		}
		public void mouseReleased (MouseEvent me ) {
			
		
				int x = me.getX();
	            int y = me.getY(); 
	            int row = table[index].columnAtPoint(me.getPoint());
				if (row == -1) return;
				for (int i = 0; i < table.length; ++i){
					if (i != index){
					table[i].removeColumnSelectionInterval(0, table[i].getColumnCount()-1);
					table[i].setRowSelectionInterval(0,0);
					}
				}
	            if (rowdeb != -1){

	            	if (rowdeb != row)
	            	table[index].setColumnSelectionInterval(rowdeb,row-1);
	            	else
	            	table[index].setColumnSelectionInterval(rowdeb,row);
	            	//
	            
	            
	            Object clickedref = table[index].getValueAt(0,row);
				
				
	             //Create the popup menu.
	             JPopupMenu popup = new JPopupMenu();
	             TableColumnModel model =  table[index].getColumnModel();

	             int realrow = 0;
	             for (int i = 0; i < model.getColumnCount(); ++i){
	             	if (i == row) break;
	             	realrow += model.getColumn(i).getPreferredWidth()/columsize;
	             	
	             	
	             }
	             int realrowdeb = 0;
	             for (int i = 0; i < model.getColumnCount(); ++i){
	             	if (i == rowdeb) break;
	             	realrowdeb += model.getColumn(i).getPreferredWidth()/columsize;
	             }
	             if (realrow < realrowdeb) {
	             	int tmp = realrow;
	             	realrow = realrowdeb;
	             	realrowdeb = tmp;
	             }
	            // System.out.print(realrow + " ");
	             //properties item
	             Object [] refsplus = new Object [refs.length + 7];
	             refsplus[0] = refs[0];
	             refsplus[1] =  TimeTableTable.this.me;
	             refsplus[2] = new Integer(Grid.gridBgHour + realrowdeb/Grid.gridSlice);
	             refsplus[3] = clickedref;
	             refsplus[4] = new Integer((realrowdeb%Grid.gridSlice) * (60/Grid.gridSlice));
	             refsplus[5] = new Integer(index);
	             refsplus[6] = new Integer(Grid.gridBgHour + realrow/Grid.gridSlice);
	             refsplus[7] = new Integer((realrow%Grid.gridSlice) * (60/Grid.gridSlice));
	             
	             
	 			if (clickedref instanceof Course){
	 				MainFrame.setSelectedCourse(clickedref);
	 				MainFrame.setModelSelectedCourse(this);
	 				InstancesActions.getAction("ActionCourseAdd",null).setEnabled(false);
				}
	 			else {
	 				InstancesActions.getAction("ActionCut",null).setEnabled(false);
	 				InstancesActions.getAction("ActionCopy",null).setEnabled(false);
	 				ActionPaste action = (ActionPaste)InstancesActions.getAction("ActionPaste",null);
	 				action.setEnabled(true);
	 				action.setRefs(refsplus);
	 				ActionCourseAdd action2 = (ActionCourseAdd)InstancesActions.getAction("ActionCourseAdd",null);action2.setEnabled(true);action2.setRefs(refsplus);
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseAdd",refsplus));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionPrint",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionExport",refs));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionPaste",refsplus));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionGridConfig",refs));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionInvertSelect",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionSelectAll",refs));
	 				//popup.add( MenuBarFactory.CreateMenuItem("ActionUnselect",refs));
	 				if ( SwingUtilities.isRightMouseButton( me ) ){
	 				popup.show( table[index], me.getX(), me.getY() );
	 				}
	 				//popup.add( MenuBarFactory.CreateMenuItem("ActionCreateSubject"));
	 				//popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation"));
				}
	 			rowdeb = -1;
	            
	         }
		}
	      public void mouseClicked( MouseEvent me ) {
	       	
	             int x = me.getX();
	             int y = me.getY();
	             int row = table[index].columnAtPoint(me.getPoint());
	             

				
					
				if (row == -1) return;
				for (int i = 0; i < table.length; ++i){
					if (i != index){
					table[i].removeColumnSelectionInterval(0, table[i].getColumnCount()-1);
					table[i].setRowSelectionInterval(0,0);
					}
				}
	            
				//table[index].setRowSelectionInterval(0,0);
	            table[index].setColumnSelectionInterval(row,row);

				Object clickedref = table[index].getValueAt(0,row);
				
				
	             //Create the popup menu.
	             JPopupMenu popup = new JPopupMenu();
	             TableColumnModel model =  table[index].getColumnModel();

	             int realrow = 0;
	             for (int i = 0; i < model.getColumnCount(); ++i){
	             	if (i == row) break;
	             	realrow += model.getColumn(i).getPreferredWidth()/columsize;
	             	
	             	
	             }
	            // System.out.print(realrow + " ");
	             //properties item
	             Object [] refsplus = new Object [refs.length + 7];
	             refsplus[0] = refs[0];
	             refsplus[1] =  TimeTableTable.this.me;
	             refsplus[2] = new Integer(Grid.gridBgHour + realrow/Grid.gridSlice);
	             refsplus[3] = clickedref;
	             refsplus[4] = new Integer((realrow%Grid.gridSlice) * (60/Grid.gridSlice));
	             refsplus[5] = new Integer(index);
	             refsplus[6] = new Integer(Grid.gridBgHour + realrow/Grid.gridSlice);
	             refsplus[7] = new Integer((realrow%Grid.gridSlice) * (60/Grid.gridSlice));
	             
	 			if (clickedref instanceof Course){
	 				MainFrame.setSelectedCourse(clickedref);
	 				MainFrame.setModelSelectedCourse(this);
	 				InstancesActions.getAction("ActionCut",null).setEnabled(true);
	 				InstancesActions.getAction("ActionCopy",null).setEnabled(true);
	 				InstancesActions.getAction("ActionPaste",null).setEnabled(false);
	 				InstancesActions.getAction("ActionCourseAdd",null).setEnabled(false);
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCut",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCopy",refs));
	 				
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionDelete",refsplus));
	 				popup.add(new JSeparator());
	 				
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseMove",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseModify",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseCancel",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseUncancel",refs));
				}
	 			else {
	 				InstancesActions.getAction("ActionCut",null).setEnabled(false);
	 				InstancesActions.getAction("ActionCopy",null).setEnabled(false);
	 				ActionPaste action = (ActionPaste)InstancesActions.getAction("ActionPaste",null);action.setEnabled(true);action.setRefs(refsplus);
	 				ActionCourseAdd action2 = (ActionCourseAdd)InstancesActions.getAction("ActionCourseAdd",null);action2.setEnabled(true);action2.setRefs(refsplus);
	 				
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseAdd",refsplus));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionPrint",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionExport",refs));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionPaste",refsplus));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionGridConfig",refs));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionInvertSelect",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionSelectAll",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionUnselect",refs));
	 				
	 				//popup.add( MenuBarFactory.CreateMenuItem("ActionCreateSubject"));
	 				//popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation"));
				}

	 			if ( SwingUtilities.isRightMouseButton( me ) ) {
	             popup.show( table[index], me.getX(), me.getY() );
	 			}
	          
	       }
	    };
	    
	private class TimeTableTableModel extends DefaultTableModel {
		
		
	    private int[] finalSizeRow;
		private Object[] cours;
		private FormationElement src;
		private int finalNbRow;

		public TimeTableTableModel (int [] finalSizeRow,int finalNbRow, FormationElement src ){
			this.finalSizeRow = finalSizeRow;
			
			this.finalNbRow = finalNbRow;
			this.src = src;
			this.cours = src.getCourseList().toArray();
		}
		
		public void changeSource(int [] finalSizeRow, Object [] cours){
			this.finalSizeRow = finalSizeRow;
			this.cours = cours;
			fireTableDataChanged();
		}
		public String getColumnName(int col) {
	        return "";
	    }
	    public int getRowCount() { return 1; }
	    public int getColumnCount() { return finalNbRow; }
	    public Object getValueAt(int row, int col) {
	    	if (finalSizeRow[col] != -1)
	    		return cours[finalSizeRow[col]];
	    	else
	    		return "";//cours[col];
	    }

	    public boolean isCellEditable(int row, int col)
	        { return false; }
	}

	public void refresh(){
		changeSource(cours);
	}
	public void changeSource(FormationElement cours){
		 changeSource( bghour, endhour, nbday,nbhours, nbhoursslice,cours);
	}
	
	public void changeSource(
			final int bghour,
			final int endhour,
			final int nbday,
			final int nbhours,
			final int nbhoursslice,FormationElement cours){
		this.cours = cours;
		this.endhour = endhour;
		this.bghour = bghour;
		
		this.nbhours = nbhours;
		this.nbhoursslice = nbhoursslice;
		try {
		 TableColumnModel tableColumnModelDetail = new DefaultTableColumnModel();
		 this.cours = cours;
		 
		 cours.upDateDto(cours.getDto());
//System.out.println(this.nbday +">"+  nbday);
		 if (this.nbday >  nbday){
		 	for (; this.nbday > nbday; --this.nbday){
		 		((JButton)taglist.get(this.nbday)).setVisible(false);
		 		timetable[this.nbday].setVisible(false); 		
		 		}
		 }
		 else if (this.nbday <  nbday){
		 	for (; this.nbday < nbday; ++this.nbday){
		 		((JButton)taglist.get(this.nbday+1)).setVisible(true);
		 		timetable[this.nbday+1].setVisible(true);
		 		}
		 }
		// pane.repaint();

			final String []  daytitles = {"","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
			//GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis(Grid.calendar.getTimeInMillis());
		for (int i = 1; i < taglist.size(); ++i){	
			
		 ((JButton)taglist.get(i)).setText(daytitles[i] + " " + cal.get(Calendar.DAY_OF_MONTH) +"/"+
						(cal.get(Calendar.MONTH)+1));
		 cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR) + 1);
		}
		

		for (int i = 1; i < nbday + 1; ++ i){	
			
		/*
			timetable[i].setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			timetable[i].setColumnSelectionAllowed(true);
			timetable[i].setRowSelectionAllowed(false);
			*/
/*
			timetable[i].addMouseListener(new TimeTableMouseListenere(timetable,i));
	*/	
			int [] sizeRow = new int [nbhours*nbhoursslice];
			int [] coursRow = new int [nbhours*nbhoursslice];
			
			timetable[i].setRowHeight(columheigth);
			for (int k = 0; k < sizeRow.length; ++k ){
				sizeRow[k] = 1;
				coursRow[k] = -1;
			}
			
			int nbRow = nbhours*nbhoursslice;

			
			coursRow = computeTimeSlice(i,coursRow,sizeRow,nbRow,cours.getCourseList().toArray());
			nbRow = coursRow.length;

			TimeTableTableModel tttm = new TimeTableTableModel(coursRow,nbRow,cours);
			timetable[i].setModel(tttm);
			 tableColumnModelDetail = new DefaultTableColumnModel();

			 int size = 0;
			 
			for (int j = 0,colind = 0; j < nbRow; ++j ){
				while (sizeRow[colind] == 0) colind++;
				int columsizecalc = columsize * (sizeRow[colind]);
				if (sizeRow[colind] > 1)
					columsizecalc = columsize * (sizeRow[colind] + 1);
				size += columsizecalc;
				TableColumn tableColumn = new TableColumn(j);
				tableColumn.setMaxWidth(columsizecalc);
				tableColumn.setMinWidth(columsizecalc);
				tableColumn.setPreferredWidth(columsizecalc);
				colind++;
				
				
				
				tableColumn.setCellRenderer(new TimeTableCellRenderer());
				tableColumnModelDetail.addColumn(tableColumn);
				}
//System.out.println("size " + size + " nb Row " + nbRow);
			tableColumnModelDetail.setColumnSelectionAllowed(true);
			timetable[i].setColumnModel(tableColumnModelDetail);
			tttm.fireTableDataChanged();
		//	timetable[i].setSize(0,8*5*10);
		}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("c'est la");
		}
		pane.setPreferredSize(new Dimension(columsize*(nbhours + 1)*nbhoursslice + 50 ,tableheigth));
        if (cours instanceof Formation){
            changerListerner= new CourseFormationListener((FormationDto)cours.getDto(), this, MainFrame.myDaybyday);
         }
         else if (cours instanceof Section){
            changerListerner = new CourseSectionListener((SectionDto)cours.getDto(), this, MainFrame.myDaybyday);
         }
         else if (cours instanceof Teacher){
            changerListerner = new CourseTeacherListener((TeacherDto)cours.getDto(), this, MainFrame.myDaybyday);
         }
        try {
			subscriber.setMessageListener(changerListerner);
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
	}

	public void changeSize(int size, int height){
		//System.out.println(size);
		this.tableheigth = nbday * height/nbday;
		this.columheigth = height/nbday + 10;
		
		int newcolumsize = ((size/58) * 4) /Grid.gridSlice;
		try {
			for (int i = 1; i < taglist.size(); ++i){
				JButton jb = (JButton)taglist.get(i);
				jb.setSize(new Dimension(120,columheigth));
				jb.setPreferredSize(new Dimension(120,columheigth-100));
				jb.setMinimumSize(new Dimension(120,columheigth-100));
				jb.setMaximumSize(new Dimension(120,columheigth-100));
			}

		
			 TableColumnModel tableColumnModelDetail = new DefaultTableColumnModel();
			 
			for (int j = bghour; j < endhour; ++j ){
				TableColumn tableColumn = new TableColumn(j, newcolumsize * nbhoursslice);
				tableColumn.setMaxWidth(newcolumsize * nbhoursslice);
				tableColumn.setMinWidth(newcolumsize * nbhoursslice);
				tableColumn.setPreferredWidth(newcolumsize * nbhoursslice);
				//tableColumn.setHeaderValue(hourstitle[j].toString());
				tableColumn.setCellRenderer(new DefaultTableCellRenderer());
				tableColumnModelDetail.addColumn(tableColumn);
				tableColumn.setCellRenderer(new TableCellRenderer(){

					public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
						JButton jb = new JButton((String)arg1);
						return jb;
					}
					
				});
				
				}
			timetable[0].setColumnModel(tableColumnModelDetail);
			
		for (int i = 1; i < nbday + 1; ++ i){
		
			timetable[i].setRowHeight(columheigth);
			
			TimeTableTableModel tttm = (TimeTableTableModel)timetable[i].getModel();
			TableColumnModel oldtableColumnModelDetail = timetable[i].getColumnModel();
			tableColumnModelDetail = new DefaultTableColumnModel();
			for (int j = 0; j < timetable[i].getColumnCount(); ++j ){
				
				//System.out.println(oldtableColumnModelDetail.getColumnCount() + " " + j + " " + timetable[i].getColumnCount());
				if (j == oldtableColumnModelDetail.getColumnCount()) break;
				TableColumn oldtableColumn = oldtableColumnModelDetail.getColumn(j);
				
				int nbRow = oldtableColumn.getMinWidth()/this.columsize;
				//System.out.println(nbRow);
				final int columsizecalc = newcolumsize * (nbRow);
				
				//if (nbRow > 1)
				//	columsizecalc = newcolumsize * (nbRow + 1);
				//size += columsizecalc;
				TableColumn tableColumn = new TableColumn(j);
				tableColumn.setMaxWidth(columsizecalc);
				tableColumn.setMinWidth(columsizecalc);
				tableColumn.setPreferredWidth(columsizecalc);
				
				
				tableColumn.setCellRenderer(new TimeTableCellRenderer());
				tableColumnModelDetail.addColumn(tableColumn);
				}
//System.out.println("size " + size + " nb Row " + nbRow);
			tableColumnModelDetail.setColumnSelectionAllowed(true);
			timetable[i].setColumnModel(tableColumnModelDetail);
			timetable[i].setPreferredSize(new Dimension(200,20));
			tttm.fireTableDataChanged();
		//	timetable[i].setSize(0,8*5*10);
		}
		this.columsize = newcolumsize;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("mais c'est que ca plante");
		}
		pane.setPreferredSize(new Dimension(columsize*(nbhours + 1)*nbhoursslice + 50 ,tableheigth));
	}
	
	public FormationElement getSource(){
		return cours;
	}
	
	public void reLoad(){
		changeSource(cours);
	}
	
	
	public class TimeTableCellRenderer implements TableCellRenderer{

		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
			try{tag.removeAll();
			tag.setBackground(new Color(255, 255, 255));
			//tag.setIcon(null);
			tag.setText(null);
			if (arg1 instanceof Course){
				Course cours = (Course)arg1;
				
				//tag.setIcon(Images.getImageIcon("formation"));
				
				
				tag.setText(((Course)arg1).getRepresentation());
				if (CourseDetail.coursColor)
				tag.setBackground(new Color(((Course)arg1).colorR, ((Course)arg1).colorG, ((Course)arg1).colorB));
				tag.setForeground(new Color(0, 0, 0));
				Style  style = null;
				StyledDocument doc = tag.getStyledDocument();
				
				style = tag.addStyle("Style", null);
				StyleConstants.setForeground(style, new Color(0, 0, 0));
				StyleConstants.setFontFamily(style,MainFrame.font);
				StyleConstants.setFontSize(style,MainFrame.fontsize);
				StyleConstants.setAlignment(style,MainFrame.fontaling);
		        if (MainFrame.fontbold){
		        	StyleConstants.setBold(style, true);
		        }
		        if (MainFrame.fontitalic){
		        	StyleConstants.setItalic(style, true);
		        }
		        if (MainFrame.fontunderline) {
		        	
			        StyleConstants.setUnderline(style, true);
		        }
		        doc.setCharacterAttributes(0,tag.getText().length() , tag.getStyle("Style"), true);
		        doc.setParagraphAttributes(0,tag.getText().length() , tag.getStyle("Style"), true);

			}
			if (arg2 || arg3){
				StyledDocument doc = tag.getStyledDocument();
				Style  style = tag.addStyle("Style", null);
				StyleConstants.setForeground(style, new Color(255, 255, 255));

				tag.setBackground(new Color(183, 207, 225));
				tag.setForeground(new Color(255, 255, 255));
				doc.setCharacterAttributes(0,tag.getText().length() , tag.getStyle("Style"), true);
			   
			}
			// TODO Auto-generated method stub
			tag.setFont(new Font(MainFrame.font, 0, MainFrame.fontsize));
			
			}catch (Exception e){
				System.err.println("c la que ca plante");
			}
			return tag;
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


