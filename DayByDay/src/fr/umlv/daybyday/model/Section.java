/*
 * Created on 20 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationPK;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionPK;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Section implements FormationElement{

	ArrayList filieres;
	ArrayList matiere;
	String name;
	ArrayList courslist;
	FormationElement father;
	SectionDto sectionDto;
	
	public Section (String name, FormationElement father){
		this.name = name;
		
		filieres = new ArrayList();
		matiere = new ArrayList();
		courslist = new ArrayList();
		this.father = father;
		
	}
	
	public Section (String name, FormationElement father, ArrayList courslist){
		this.name = name;
		
		filieres = new ArrayList();
		matiere = new ArrayList();
		this.courslist = courslist;
		this.father = father;
	}
	
	public SectionDto getDTO(){
		return sectionDto;
	}
	/**
	 * @param dto
	 * @throws RemoteException
	 */
	public Section(SectionDto dto, FormationElement father) {
		sectionDto = dto;
		this.name = dto.getName();
		this.father = father;
		//ArrayList filieretmp = MainFrame.myDaybyday.getSectionsOfFormation(new SectionPK(name));
		filieres = new ArrayList();
		

		ArrayList filieretmp;
		try {
			filieretmp = MainFrame.myDaybyday.getChildrenOfSection(dto.getSectionPK());
			for (int i = 0; i < filieretmp.size(); ++i)
				filieres.add(new Section((SectionDto)filieretmp.get(i),this)); 
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	
		matiere =  new ArrayList();
		ArrayList matieretmp;
		try {
			matieretmp = MainFrame.myDaybyday.getSubjectsOfSection(new SectionPK(name,father.getName(),father.getYear()));

			for (int i = 0; i < matieretmp.size(); ++i)
				matiere.add(new Subject((SubjectDto)matieretmp.get(i),this)); 
		} catch (RemoteException e) {

		}


		
		ArrayList courslisttmp;
		courslist =  new ArrayList();
		try {
			courslisttmp = MainFrame.myDaybyday.getCoursesOfSection(new SectionPK(name,father.getName(),father.getYear()));
			for (int i = 0; i < courslisttmp.size(); ++i)
				courslist.add(new Course((CourseDto)courslisttmp.get(i))); 
		} catch (RemoteException e1) {

		}
		if (father instanceof Formation){
			try {
				courslisttmp = MainFrame.myDaybyday.getCoursesOfSection(new SectionPK("GENERALE",father.getName(),father.getYear()));
				for (int i = 0; i < courslisttmp.size(); ++i)
					courslist.add(new Course((CourseDto)courslisttmp.get(i))); 
			} catch (RemoteException e1) {

			}
		}

	}

	public ArrayList getCourseList(){
		ArrayList courslisttmp = new ArrayList();;
		
		try {
		SectionDto dto = MainFrame.myDaybyday.getSection(new SectionPK(sectionDto.getName(),sectionDto.getFormationName(),sectionDto.getFormationYear()));

		if (sectionDto.getVersion().intValue() != dto.getVersion().intValue()){
			sectionDto = dto;
			try {
				filieres = new ArrayList();
				
				ArrayList filieretmp = MainFrame.myDaybyday.getChildrenOfSection(sectionDto.getSectionPK());
		
			
			for (int i = 0; i < filieretmp.size(); ++i)
				filieres.add(new Section((SectionDto)filieretmp.get(i),this)); 
			
			} catch (RemoteException e) {
		
			}
			
			ArrayList matieretmp = null;
			try {
				//sectionDto = MainFrame.myDaybyday.getSection(new SectionPK("GENERALE",name,year));
				matiere = new ArrayList();
				matieretmp = MainFrame.myDaybyday.getSubjectsOfSection(sectionDto.getSectionPK());
				for (int i = 0; i < matieretmp.size(); ++i)
					matiere.add(new Subject((SubjectDto)matieretmp.get(i),this)); 
			} catch (RemoteException e1) {
		
			}
		}
		} catch (RemoteException e1) {

		}
		 courslisttmp.clear(); courslisttmp.addAll(courslist);courslisttmp.addAll(father.getCourseList());
		 return courslisttmp;

	}
	
	public void upDateDto (SectionDto dto){
		ArrayList courslisttmp = new ArrayList();;
		
		try {	
			if (sectionDto.getVersion().intValue() != dto.getVersion().intValue()){
				courslist =  new ArrayList();
				sectionDto = dto;
				courslisttmp = MainFrame.myDaybyday.getCoursesOfSection(sectionDto.getSectionPK());
			for (int i = 0; i < courslisttmp.size(); ++i)
				courslist.add(new Course((CourseDto)courslisttmp.get(i))); 
			}
		} catch (RemoteException e1) {

		}
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
		if (i >= filieres.size()){
			int j = i - filieres.size();
			if (j < matiere.size())
				return matiere.get(j);
		}
		else
			return filieres.get(i);
		return null;
	}
	
	public int getElementNombre(){
		return filieres.size() + matiere.size();
	}
	
	public int getFiliereCount() {
		return filieres.size();
	}

	public int getMatiereCount() {
		return  matiere.size();
	}
	
	public boolean isMatiere(int i){
		if (i > filieres.size()){
			return true;
		}
		return false;
	}
	
	public boolean isEqual (Object comp){
		if (comp instanceof Section){
			if (((Section) comp).getName().compareTo(name) == 0)
				return true;
		}
		return false;
	}

	/**
	 * @param arg1
	 * @return
	 */
	public int getIndex(Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString (){
		return name;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getNamePath()
	 */
	public String getNamePath() {
		return father.getNamePath() + "\\" + name;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getNamePath()
	 */
	public FormationElement getFather() {
		return father;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#removeFiliere(fr.umlv.daybyday.model.Section)
	 */
	public void removeFiliere(Section file) {
		filieres.remove(file);
		
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#removeMatiere(fr.umlv.daybyday.model.Subject)
	 */
	public void removeMatiere(Subject mat) {
		matiere.remove(mat);		
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int arg0) {
		return (TreeNode) getIndex(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		// TODO Auto-generated method stub
		return getElementNombre();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		return (TreeNode)father;
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
		return (getElementNombre() == 0);
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
		return sectionDto;
	}

	
}
