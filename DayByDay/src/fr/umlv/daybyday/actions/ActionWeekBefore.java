/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.TimeTableTable;
import fr.umlv.daybyday.gui.Windows;
import fr.umlv.daybyday.model.Grid;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionWeekBefore extends AbstractAction {

	Object [] refs;

	
	public ActionWeekBefore(Object [] refs) {
		super("Précédente",Images.getImageIcon("precedent"));
		this.refs = refs;

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JLabel week = (JLabel) refs[1]; 
		Grid.changeWeek(-1);
		TimeTableTable tt = (TimeTableTable) refs[2]; 
		tt.changeSource(tt.getSource());

		week.setText(	Grid.calendar.get(Calendar.DAY_OF_MONTH)+"/"+
				(Grid.calendar.get(Calendar.MONTH)+1) + " "+
				Grid.calendar.get(Calendar.WEEK_OF_YEAR) + " "+
				Grid.calendar.get(Calendar.YEAR) );
	}
}
