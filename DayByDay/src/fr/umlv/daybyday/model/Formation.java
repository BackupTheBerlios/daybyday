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

import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherBusinessPK;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationBusinessPK;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionBusinessPK;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.MainFrame;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Formation implements FormationElement{

	ArrayList filieres;
	ArrayList matiere;
	ArrayList courslist;
	FormationDto formationdto;
	SectionDto sectionDto;
	
	String name;
	private String responsable;
	private String room;
	private String year;
	
	public Formation (String name,String responsable,String room,String year){
		
		this.name = name;
		this.responsable = responsable;
		this.room = room;
		this.year = year;
		filieres = new ArrayList();
		matiere = new ArrayList();
		courslist = new ArrayList();
	}
	
	public Formation (FormationDto formationDto) {
		
		this.name = formationDto.getName();
		TeacherDto teacher = null;
		RoomDto room = null;
		try {
			teacher = MainFrame.myDaybyday.getTeacher(formationDto.getTeacherId());
			room = MainFrame.myDaybyday.getRoom(formationDto.getRoomId());
		} catch (RemoteException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (EntityNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		this.responsable = teacher.getName();
		this.room = room.getName();
		this.year = formationDto.getFormationYear();
		this.formationdto = formationDto;
		
		filieres =  new ArrayList();
		courslist =  new ArrayList();
		matiere =  new ArrayList();
		
		try {
			
			SectionBusinessPK sec = new SectionBusinessPK("GENERALE",formationDto.getFormationId());
			SectionDto defaults =  MainFrame.myDaybyday.getSection(sec);
			ArrayList filieretmp = MainFrame.myDaybyday.getChildrenOfSection(sec);

		
		for (int i = 0; i < filieretmp.size(); ++i)
			filieres.add(new Section((SectionDto)filieretmp.get(i),this)); 
		
		} catch (RemoteException e) {
			e.printStackTrace();

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList matieretmp = null;
		try {
			
			SectionBusinessPK sec = new SectionBusinessPK("GENERALE",formationDto.getFormationId());
			sectionDto = MainFrame.myDaybyday.getSection(sec);
			matieretmp = MainFrame.myDaybyday.getSubjectsOfSection(sec);
			for (int i = 0; i < matieretmp.size(); ++i)
				matiere.add(new Subject((SubjectDto)matieretmp.get(i),this)); 
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		ArrayList courslisttmp = null;
		try {
			
			SectionDto sec = MainFrame.myDaybyday.getSection(new SectionBusinessPK("GENERALE",formationDto.getFormationId()));
			
			courslisttmp = MainFrame.myDaybyday.getCoursesOfSection(sec.getSectionId());
			
			for (int i = 0; i < courslisttmp.size(); ++i)
				courslist.add(new Course((CourseDto)courslisttmp.get(i)));
			
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	public Formation (String name, ArrayList courslist,String responsable,String room,String year){
		
		this.name = name;
		this.responsable = responsable;
		this.room = room;
		this.year = year;		
		filieres = new ArrayList();
		matiere = new ArrayList();
		this.courslist = courslist;
	}
	

	public ArrayList getCourseList(){
		
		ArrayList courslisttmp = null;
		try {
			FormationDto newfdto =  MainFrame.myDaybyday.getFormation(new FormationBusinessPK(name,year));
			SectionDto newdto =  MainFrame.myDaybyday.getSection(new SectionBusinessPK("GENERALE",newfdto.getFormationId()));
			
			if (sectionDto.getVersion().intValue() != newdto.getVersion().intValue() || 
					formationdto.getVersion().intValue() != newfdto.getVersion().intValue()){
				courslist =  new ArrayList();
				sectionDto = newdto;
				formationdto = newfdto;
				courslist = new ArrayList();
				courslisttmp = MainFrame.myDaybyday.getCoursesOfSection(new SectionBusinessPK("GENERALE",formationdto.getFormationId()));
				for (int i = 0; i < courslisttmp.size(); ++i)
					courslist.add(new Course((CourseDto)courslisttmp.get(i))); 
			}
		} catch (RemoteException e2) {

		} catch (EntityNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
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
		if (i >= filieres.size()){
			int j = i - filieres.size();
			if (j < matiere.size()){
				return matiere.get(j);
			}
		}
		else {
			return filieres.get(i);
		}
		
		return null;
	}
	
	public String toString (){
		return name;
	}
	
	public boolean isMatiere(int i){
		if (i > filieres.size()){
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public int getElementNombre() {
		try {
			// TODO Auto-generated method stub
			FormationDto dto = MainFrame.myDaybyday.getFormation(new FormationBusinessPK(name,year));
			if (formationdto.getVersion().intValue() != dto.getVersion().intValue()){
				formationdto = dto;
			
			SectionBusinessPK sec = new SectionBusinessPK("GENERALE",dto.getFormationId());
			try {
			filieres = new ArrayList();
			
			
			SectionDto defaults =  MainFrame.myDaybyday.getSection(sec);
			ArrayList filieretmp = MainFrame.myDaybyday.getChildrenOfSection(sec);

		
		for (int i = 0; i < filieretmp.size(); ++i)
			filieres.add(new Section((SectionDto)filieretmp.get(i),this)); 
		
		} catch (RemoteException e) {

		}
		
		ArrayList matieretmp = null;
		try {
			//sectionDto = MainFrame.myDaybyday.getSection(new SectionPK("GENERALE",name,year));
			matiere = new ArrayList();
			matieretmp = MainFrame.myDaybyday.getSubjectsOfSection(sec);
			for (int i = 0; i < matieretmp.size(); ++i)
				matiere.add(new Subject((SubjectDto)matieretmp.get(i),this)); 
		} catch (RemoteException e1) {

		}
}
		} catch (RemoteException e2) {

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filieres.size() + matiere.size();
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
	
	public String getResponsable(){
		return responsable;
	}
	public String getRoom(){
		return room;
	}
	public String getYear(){
		return year;
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return getFather();
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
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf() {
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
	 * @see fr.umlv.daybyday.model.FormationElement#getDto()
	 */
	public Object getDto() {
		return formationdto;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.daybyday.model.FormationElement#getFather()
	 */
	public FormationElement getFather() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void upDateDto (SectionDto dto){
		getElementNombre();
	}
	
}
