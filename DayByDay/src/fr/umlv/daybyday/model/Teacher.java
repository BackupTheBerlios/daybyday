/*
 * Created on 28 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherPK;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionPK;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Teacher implements FormationElement {


	ArrayList formation;
	ArrayList filieres;
	ArrayList matiere;
	private ArrayList courslist;
	TeacherDto teacherDto;
	String name;
	private String room;
	
	public Teacher (TeacherDto dto){
		teacherDto = dto;
		name = dto.getName();
		
		ArrayList matieretmp = null;
		ArrayList filieres = new ArrayList();
		ArrayList matiere = new ArrayList();
		ArrayList courslist = new ArrayList();
		
		try {
			matieretmp = MainFrame.myDaybyday.getSubjectsOfTeacher(new TeacherPK(dto.getName(),dto.getFirstname()));
			for (int i = 0; i < matieretmp.size(); ++i)
				matiere.add(new Subject((SubjectDto)matieretmp.get(i),this)); 
		} catch (RemoteException e1) {

		}
		
	
	}
	

	
	public ArrayList getCourseList(){
		ArrayList courslist = new ArrayList();
		ArrayList courslisttmp = null;
		try {
			courslisttmp = MainFrame.myDaybyday.getCoursesOfTeacher(new TeacherPK(teacherDto.getName(),teacherDto.getFirstname()));
			for (int i = 0; i < courslisttmp.size(); ++i)
				courslist.add(new Course((CourseDto)courslisttmp.get(i))); 
		} catch (RemoteException e2) {

		}
		return courslist;
	}
	
	public String getName(){
		return name;
	}
	public void addFiliere (Section file){
		filieres.add(file);
	}

	public void addMatiere (Subject mat){
		//filieres.add(mat);
		matiere.add(mat);
	}
	
	public Object getIndex(int i){
				return matiere.get(i);
	}
	
	public String toString (){
		return name;
	}
	
	public boolean isMatiere(int i){
			return true;
	}

	/**
	 * @return
	 */
	public int getElementNombre() {
		// TODO Auto-generated method stub
		return matiere.size();
	}

	public int getFiliereCount() {
		// TODO Auto-generated method stub
		return filieres.size();
	}

	public int getMatiereCount() {
		// TODO Auto-generated method stub
		return  matiere.size();
	}
	
	/**
	 * @param arg1
	 * @return
	 */
	public int getIndex(Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getNamePath()
	 */
	public String getNamePath() {
		return getName();
	}
	
	public String getRoom(){
		return room;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#removeFiliere(fr.umlv.daybyday.model.Section)
	 */
	public void removeFiliere(Section file) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#removeMatiere(fr.umlv.daybyday.model.Subject)
	 */
	public void removeMatiere(Subject mat) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	public int getIndex(TreeNode arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#children()
	 */
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getYear()
	 */
	public String getYear() {
		return "";
	}



	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getDto()
	 */
	public Object getDto() {
		return teacherDto;
	}



	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getFather()
	 */
	public FormationElement getFather() {
		// TODO Auto-generated method stub
		return null;
	}
}
