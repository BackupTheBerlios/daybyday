/*
 * Created on 20 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Subject implements FormationElement{

	private String name;
	FormationElement father;
	SubjectDto dto;
	public Subject (String name,FormationElement father){
		this.name = name;
		this.father = father;
	}
	


	/**
	 * @param dto
	 * @param formation
	 */
	public Subject(SubjectDto dto, FormationElement father) {
		this.name = dto.getName();
		this.dto = dto;
		this.father = father;
	}



	public FormationElement getFather(){
		return father;
	}
	
	public String getNamePath() {
		return father.getNamePath() + "\\" + name;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString (){
		return name;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#addFiliere(fr.umlv.daybyday.model.Section)
	 */
	public void addFiliere(Section file) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#addMatiere(fr.umlv.daybyday.model.Subject)
	 */
	public void addMatiere(Subject mat) {
		// TODO Auto-generated method stub
		
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
	 * @see fr.umlv.daybyday.model.FormationElement#getIndex(int)
	 */
	public Object getIndex(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#isMatiere(int)
	 */
	public boolean isMatiere(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getElementNombre()
	 */
	public int getElementNombre() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getIndex(java.lang.Object)
	 */
	public int getIndex(Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getCourseList()
	 */
	public ArrayList getCourseList() {
		// TODO Auto-generated method stub
		return null;
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
		return (TreeNode) father;
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
		return true;
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
		// TODO Auto-generated method stub
		return father.getYear();
	}



	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getDto()
	 */
	public Object getDto() {
			return dto;
	}
}
