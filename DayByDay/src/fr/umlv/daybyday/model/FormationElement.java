/*
 * Created on 26 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import java.util.ArrayList;

import javax.swing.tree.TreeNode;

import fr.umlv.daybyday.ejb.timetable.section.SectionDto;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface FormationElement extends TreeNode {
	
	public String getName();
	public String getNamePath();
	public void addFiliere (Section file);
	public void addMatiere (Subject mat);
	public void removeFiliere (Section file);
	public void removeMatiere (Subject mat);
	public Object getIndex(int i);
	public String toString ();
	public Object getDto();
	public FormationElement getFather();
	public boolean isMatiere(int i);
	public int getElementNombre();
	public String getYear();
	public int getIndex(Object arg1);
	public ArrayList getCourseList();
	public void upDateDto (Object dto);
}
