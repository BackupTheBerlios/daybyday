/*
 * Created on 23 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TimeTableTableModel implements TableModel{

	private int nbcolums;
	private int nblines;
	
	private String [] columstitles;
	private String [] linesstitles;	
	private String [][] values;
	
	public TimeTableTableModel (String [] columstitles, String [] linestitles, int nbcolums, int nblines,String [][] values){
			this.columstitles = columstitles;
			this.linesstitles = linestitles;
			this.nbcolums = nbcolums;
			this.nblines = nblines;
			this.values = values; 
	}	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		
		// TODO Auto-generated method stub
		return nblines;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return nbcolums;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return columstitles[arg0];
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	public Class getColumnClass(int arg0) {
		try {
			// TODO Auto-generated method stub
			return Class.forName("String");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return values[arg0][arg1];
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#addTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

}
