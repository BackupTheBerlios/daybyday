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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.rtf.RTFEditorKit;




import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.model.Course;
import fr.umlv.daybyday.model.FormationElement;
import fr.umlv.daybyday.model.Grid;



//import fr.umlv.daybyday.model.TimeTableTableModel;


/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TimeTableTable {


	private JTable timetable [];
	private String [] hourstitle;
	private final int nbday;
	private final int nbhours;
	private final int nbhoursslice;
	protected JPanel pane;
	private int columheigth;
	private int tableheigth;
	private int bghour, endhour;
	int columsize = 20;
	private Object[] refs;
	private ArrayList daytags;
	private final JButton tag = new JButton();
	private final ArrayList taglist = new ArrayList();
	private final TimeTableTable me = this;
	
	private FormationElement cours;
	
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
		timetable = new JTable[nbday + 1];
		daytags = new ArrayList();
		
		//tag.setBorderPainted(false);
		//tag.setSize(30, 30);
		//tag.setPreferredSize(new Dimension(30, 30));
		//tag.setHorizontalAlignment(SwingConstants.LEADING);
		tag.setMargin(new Insets(0, 0, 0, 0));
		
		
		final String []  daytitles = {"","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
		final String [] hourstitle = {"0h","1h","2h","3h","4h","5h","6h","7h", "8h","9h","10h","11h","12h","13h","14h","15h","16h","17h","18h","19h","20h","21h","22h","23h"};
			
		/*try{
		timetable.setModel(new TimeTableTableModel(columstitles, linestitles, nbcolums, nblines, values));
		}
		catch (Exception e){
			//e.printStackTrace();
		}*/
		
		timetable[0] = new JTable();
		timetable[0].setRowHeight(20);
		timetable[0].setDragEnabled(false);
		
		timetable[0].setModel(new AbstractTableModel() {
		    public String getColumnName(int col) {
		        return hourstitle[col+bghour].toString();
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
			tableColumn.setHeaderValue(hourstitle[j].toString());
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
//System.out.println("");
		timetable[0].setColumnModel(tableColumnModelDetail);
		
		
		for (int i = 1; i < nbday + 1; ++ i){
			timetable[i] = new JTable();
			
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
//System.out.println("Jour " + i + " nombre : " + finalNbRow);
			timetable[i].setModel(new TimeTableTableModel(finalSizeRow,nbRow,cours));
			 tableColumnModelDetail = new DefaultTableColumnModel();
//System.out.print(" " + nbRow);
			 int size = 0;
			 
			for (int j = 0,colind = 0; j < nbRow; ++j ){
				while (sizeRow[colind] == 0) colind++;
//System.out.print(" " + j + ":" + sizeRow[j] + ":" + sizeRow[colind]+ ":" + columsize);
				int columsizecalc = columsize * (sizeRow[colind]);
				if (sizeRow[colind] > 1)
					columsizecalc = columsize * (sizeRow[colind] + 1);
				size += columsizecalc;
				TableColumn tableColumn = new TableColumn(j);
				tableColumn.setMaxWidth(columsizecalc);
				tableColumn.setMinWidth(columsizecalc);
				//tableColumn.setPreferredWidth(columsizecalc);
				colind++;
				
				
				
				tableColumn.setCellRenderer(new TableCellRenderer(){

					public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
						tag.removeAll();
						tag.setBackground(new Color(255, 255, 255));
						tag.setIcon(null);
						tag.setText(null);
						if (arg1 instanceof Course){
							//tag.setIcon(Images.getImageIcon("formation"));
							tag.setText(((Course)arg1).text);
							tag.setBackground(new Color(((Course)arg1).colorR, ((Course)arg1).colorG, ((Course)arg1).colorB));
							tag.setForeground(new Color(0, 0, 0));

						}
						if (arg2 || arg3){
							tag.setBackground(new Color(183, 207, 225));
							tag.setForeground(new Color(255, 255, 255));
						}
						// TODO Auto-generated method stub
						int type = 0;
				        if (MainFrame.fontbold) type += Font.BOLD;
				        if (MainFrame.fontitalic) type += Font.ITALIC;
				        if (MainFrame.fontunderline) {
				    		RTFEditorKit rtfEK = new RTFEditorKit(); 
				    		MutableAttributeSet attr = rtfEK.getInputAttributes(); 
				    		StyleConstants.FontConstants.setUnderline(attr,MainFrame.fontunderline);
				    		//tag..setCharacterAttributes(attr, true); 
				    		//tag.add(jtp);
				        }
						tag.setFont(new Font(MainFrame.font,type,MainFrame.fontsize));
						return tag;
					}
					
				});
				tableColumnModelDetail.addColumn(tableColumn);
				}
//System.out.println("size " + size + " nb Row " + nbRow);
			tableColumnModelDetail.setColumnSelectionAllowed(true);
			timetable[i].setColumnModel(tableColumnModelDetail);
			timetable[i].setPreferredScrollableViewportSize(new Dimension(800,600));
		}

		GridLayout glayout = new GridLayout(nbday + 1, 2);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

       glayout.setVgap(0);
       glayout.setHgap(0);
		pane  = new JPanel(gridbag);
		pane.setPreferredSize(new Dimension(columsize*(nbhours + 1)*nbhoursslice + 50 ,tableheigth));
		
		taglist.clear();

		for (int i = 0; i < nbday + 1; ++ i){

			JButton jb = new JButton(daytitles[i]);
			daytags.add(jb);
			taglist.add(jb);
			if (i != 0){
			jb.setMaximumSize(new Dimension(120,10));
			jb.setMinimumSize(new Dimension(120,10));
			jb.setPreferredSize(new Dimension(120,10));
			}
			c.fill = GridBagConstraints.BOTH;
			//c.gridwidth = GridBagConstraints.REMAINDER; 
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
	}
	
	public JPanel getPane() {
		return pane;
	}

	private int []computeTimeSlice (int day, int [] coursRow,int [] sizeRow, int nbRow, Object [] cours){
	
		GregorianCalendar gc = new GregorianCalendar(Locale.FRENCH);
		
		try{
		if (cours != null){
			int nbRowSave = nbRow;
			for (int i = 0; i < cours.length; ++i){
				CourseDto coursdto = (CourseDto) ((Course)cours[i]).getDto();
				gc.setTimeInMillis(coursdto.getStartDate().getTime());
//System.out.println(gc.get(Calendar.WEEK_OF_YEAR) +"=="+ Grid.calendar.get(Calendar.WEEK_OF_YEAR)+ "&&"+ gc.get(Calendar.DAY_OF_WEEK) +"=="+ (day+1));
				int daycmp = gc.get(Calendar.DAY_OF_WEEK); 
				daycmp -= 1;
				if (daycmp == 0) daycmp = 7;
				if (gc.get(Calendar.WEEK_OF_YEAR) == Grid.calendar.get(Calendar.WEEK_OF_YEAR) && daycmp == ((day))){
					int deb = -1;
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

		
		public TimeTableMouseListenere (JTable [] table, int index ){
		 this.table = table;	
		 this.index = index;
		}
	       public void mouseClicked( MouseEvent me ) {
	        if ( SwingUtilities.isLeftMouseButton( me ) ) {
	             int x = me.getX();
	             int y = me.getY();
	             int row = table[index].columnAtPoint(me.getPoint());
	             					
				if (row == -1) return;
				for (int i = 0; i < table.length; ++i){
					table[i].removeColumnSelectionInterval(0, table[i].getColumnCount()-1);
				}
	            
				table[index].setRowSelectionInterval(0,0);
	             table[index].setColumnSelectionInterval(row,row);
	        }
	          if ( SwingUtilities.isRightMouseButton( me ) ) {
	             int x = me.getX();
	             int y = me.getY();
	             int row = table[index].columnAtPoint(me.getPoint());
	             

				
					
				if (row == -1) return;
				for (int i = 0; i < table.length; ++i){
					table[i].removeColumnSelectionInterval(0, table[i].getColumnCount()-1);
				}
	            
				table[index].setRowSelectionInterval(0,0);
	             table[index].setColumnSelectionInterval(row,row);

				Object clickedref = table[index].getValueAt(0,row);
				
				
	             //Create the popup menu.
	             JPopupMenu popup = new JPopupMenu();
	             
	             
	             
	             //properties item
	             Object [] refsplus = new Object [refs.length + 3];
	             refsplus[0] = refs[0];
	             refsplus[1] =  TimeTableTable.this.me;
	             refsplus[2] = new Integer(Grid.gridBgHour);
	             refsplus[3] = clickedref;
	             
	             
	             
	 			if (clickedref instanceof Course){
	 				
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCut",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCopy",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionPaste",refs));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionDelete",refsplus));
	 				popup.add(new JSeparator());
	 				
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseMove",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseModify",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseCancel",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseUncancel",refs));
				}
	 			else {
	 				
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionCourseAdd",refsplus));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionPrint",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionExport",refs));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionGridConfig",refs));
	 				popup.add(new JSeparator());
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionInvertSelect",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionSelectAll",refs));
	 				popup.add( MenuBarFactory.CreateMenuItem("ActionUnselect",refs));
	 				
	 				//popup.add( MenuBarFactory.CreateMenuItem("ActionCreateSubject"));
	 				//popup.add( MenuBarFactory.CreateMenuItem("ActionModifyFormation"));
				}

	 			
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
			
	public void changeSource(FormationElement cours){
	
		try {
		 TableColumnModel tableColumnModelDetail = new DefaultTableColumnModel();
		 this.cours = cours;
		 
			final String []  daytitles = {"","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis(Grid.calendar.getTimeInMillis());
		for (int i = 1; i < taglist.size(); ++i){	
			
		 ((JButton)taglist.get(i)).setText(daytitles[i] + " " + cal.get(Calendar.DAY_OF_MONTH) +"/"+
						(cal.get(Calendar.MONTH)+1));
		 cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR) + 1);
		}
		
		for (int i = 1; i < nbday + 1; ++ i){	
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
//System.out.println("Jour " + i + " nombre : " + finalNbRow);
			TimeTableTableModel tttm = new TimeTableTableModel(finalSizeRow,nbRow,cours);
			timetable[i].setModel(tttm);
			 tableColumnModelDetail = new DefaultTableColumnModel();
//System.out.print(" " + nbRow);
			 int size = 0;
			 
			for (int j = 0,colind = 0; j < nbRow; ++j ){
				while (sizeRow[colind] == 0) colind++;
//System.out.print(" " + j + ":" + sizeRow[j] + ":" + sizeRow[colind]+ ":" + columsize);
				int columsizecalc = columsize * (sizeRow[colind]);
				if (sizeRow[colind] > 1)
					columsizecalc = columsize * (sizeRow[colind] + 1);
				size += columsizecalc;
				TableColumn tableColumn = new TableColumn(j);
				tableColumn.setMaxWidth(columsizecalc);
				tableColumn.setMinWidth(columsizecalc);
				tableColumn.setPreferredWidth(columsizecalc);
				colind++;
				
				
				
				tableColumn.setCellRenderer(new TableCellRenderer(){

					public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
						tag.removeAll();
						tag.setBackground(new Color(255, 255, 255));
						tag.setIcon(null);
						tag.setText(null);
						if (arg1 instanceof Course){
							//tag.setIcon(Images.getImageIcon("formation"));
							tag.setText(((Course)arg1).text);
							tag.setBackground(new Color(((Course)arg1).colorR, ((Course)arg1).colorG, ((Course)arg1).colorB));
							tag.setForeground(new Color(0, 0, 0));

						}
						if (arg2 || arg3){
							tag.setBackground(new Color(183, 207, 225));
							tag.setForeground(new Color(255, 255, 255));
						}
						// TODO Auto-generated method stub
						int type = 0;
				        if (MainFrame.fontbold) type += Font.BOLD;
				        if (MainFrame.fontitalic) type += Font.ITALIC;
				        if (MainFrame.fontunderline) type += Font.CENTER_BASELINE;
				        
						tag.setFont(new Font(MainFrame.font,type,MainFrame.fontsize));
						return tag;
					}
					
				});
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
	}

	public void changeSize(int size, int height){
		//System.out.println(size);
		this.tableheigth = nbday * height/nbday;
		this.columheigth = height/nbday + 10;
		
		int newcolumsize = size/58;
		try {
			for (int i = 1; i < daytags.size(); ++i){
				JButton jb = (JButton)daytags.get(i);
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
				
				
				tableColumn.setCellRenderer(new TableCellRenderer(){

					public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
						tag.removeAll();
						tag.setBackground(new Color(255, 255, 255));
						tag.setIcon(null);
						tag.setText(null);
						if (arg1 instanceof Course){
							//tag.setIcon(Images.getImageIcon("formation"));
							tag.setText(((Course)arg1).text);
							tag.setBackground(new Color(((Course)arg1).colorR, ((Course)arg1).colorG, ((Course)arg1).colorB));
							tag.setForeground(new Color(0, 0, 0));

						}
						if (arg2 || arg3){
							tag.setBackground(new Color(183, 207, 225));
							tag.setForeground(new Color(255, 255, 255));
						}
						tag.setPreferredSize(new Dimension(columsizecalc, columheigth));
						// TODO Auto-generated method stub
						return tag;
					}
					
				});
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
}
