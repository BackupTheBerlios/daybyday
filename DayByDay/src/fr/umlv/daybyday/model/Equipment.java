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

import fr.umlv.daybyday.ejb.resource.equipment.EquipmentBusinessPK;
import fr.umlv.daybyday.ejb.resource.equipment.EquipmentDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Equipment implements FormationElement {



	ArrayList courslist;
	EquipmentDto roomdto;
	String name;
	String area;
	String building;
	
	public Equipment (String name){
		
		this.name = name;
		courslist = new ArrayList();
	}
	
	public Equipment (String name, ArrayList courslist){
		
		this.name = name;
		this.courslist = courslist;
		
	}
	
	/**
	 * @param dto
	 */
	public Equipment(EquipmentDto dto) {
		roomdto = dto;
		name = dto.getName();
		building = dto.getBuilding();
		area = dto.getArea();
		
		this.courslist = new ArrayList();
		ArrayList courslisttmp = null;
		try {
			courslisttmp = MainFrame.myDaybyday.getCoursesOfEquipment(new EquipmentBusinessPK(dto.getName(),dto.getBuilding(),dto.getArea()));
			for (int i = 0; i < courslisttmp.size(); ++i)
				courslist.add(new Course((CourseDto)courslisttmp.get(i))); 
		} catch (RemoteException e2) {

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public ArrayList getCourseList(){

		ArrayList courslisttmp = null;
		try {
			EquipmentDto newdto =  MainFrame.myDaybyday.getEquipment(new EquipmentBusinessPK(name,building,area));
			if (newdto.getVersion().longValue() != roomdto.getVersion().longValue()){
				roomdto = newdto;
				courslist = new ArrayList();
				courslisttmp = MainFrame.myDaybyday.getCoursesOfEquipment(new EquipmentBusinessPK(roomdto.getName(),roomdto.getBuilding(),roomdto.getArea()));
				for (int i = 0; i < courslisttmp.size(); ++i)
					courslist.add(new Course((CourseDto)courslisttmp.get(i))); 
			}
		} catch (RemoteException e2) {

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return courslist;
	}
	
	public String getName(){
		return name;
	}
	public void addFiliere (Section file){
	}

	public void addMatiere (Subject mat){

	}
	
	public Object getIndex(int i){
				return null;
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
		return 0;
	}

	public int getFiliereCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMatiereCount() {
		// TODO Auto-generated method stub
		return  0;
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
	return roomdto;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getFather()
	 */
	public FormationElement getFather() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void upDateDto (Object dto){}
}
