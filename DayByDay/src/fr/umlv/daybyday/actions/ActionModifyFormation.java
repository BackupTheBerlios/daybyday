/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.AbstractAction;

import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.Windows;
import fr.umlv.daybyday.model.Equipment;
import fr.umlv.daybyday.model.Formation;
import fr.umlv.daybyday.model.Room;
import fr.umlv.daybyday.model.Section;
import fr.umlv.daybyday.model.Subject;
import fr.umlv.daybyday.model.Teacher;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionModifyFormation extends AbstractAction {

	Object [] refs;
	
	public ActionModifyFormation(Object [] refs) {
		super("Modifier",Images.getImageIcon("modify"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		MainFrame mainframe = (MainFrame) refs[0];
		Object obj = mainframe.getSelectedObject();
		try {

			if (obj instanceof Formation){
				Windows.createWindow("WindowModifyFormation",new Object[]{
					refs[0],
					MainFrame.myDaybyday.getAllFormations().toArray(),
					MainFrame.myDaybyday.getAllTeachers().toArray(),
					MainFrame.myDaybyday.getAllRooms().toArray()});
			}
			else if  (obj instanceof Section){
				Windows.createWindow("WindowModifySection",new Object[]{
							refs[0],
							MainFrame.myDaybyday.getAllTeachers().toArray()	
					});
			}
			else if  (obj instanceof Subject){
				Windows.createWindow("WindowModifySubject",new Object[]{
						refs[0],
						MainFrame.myDaybyday.getAllTeachers().toArray()	
				});
			}
			else if  (obj instanceof Teacher){
				Windows.createWindow("WindowModifyTeacher",refs);
				
			}
			else if  (obj instanceof Room){
				Windows.createWindow("WindowModifyMaterial",new Object[] {refs[0], new Integer(1)});
			}
			else if  (obj instanceof Equipment){
				Windows.createWindow("WindowModifyMaterial",new Object[] {refs[0], new Integer(0)});	
			}
			
			
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
