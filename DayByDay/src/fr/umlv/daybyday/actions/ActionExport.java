/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

//import fr.umlv.daybyday.ejb.facade.Daybyday;
import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.resource.room.RoomDto;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.util.exception.CreationException;
import fr.umlv.daybyday.ejb.util.exception.EntityNotFoundException;
import fr.umlv.daybyday.gui.Images;
import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.model.Formation;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionExport extends AbstractAction {

	Object [] refs;
	
	public ActionExport(Object [] refs) {
		super("Exporter",Images.getImageIcon("export"));
		this.refs = refs;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */

	
	//Creation of the Root Element
	static Element root = new Element("EDT");
	//Creation of a new JDom Document with the root Element just created
	static org.jdom.Document document = new Document(root);
	

/**
 * Method that displays the XML file created
 */
	static void display()
	{
	   try
	   {
	      XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
	      out.output(document, System.out);
	   }
	   catch (java.io.IOException e){}
	}

/**
 * Method which allows to save the JDom document in a XML file
 * 
 * @param file String
 */
	static void save(String file)
	{
	   try
	   {
	   	  //store the date with the getPrettyFormat Format 
	      XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
	      //We simply create an FileOutput instance with the XML file name
	      //to store the XML date of the JDom document
	      out.output(document, new FileOutputStream(file));
	   }
	   catch (java.io.IOException e){}
	}
	

	/**
	 * Create an element with the parameter String and add it to the Element given in parameter and add the value
	 * 
	 * @param name String
	 * @param elem Element
	 * @param childelem String 
	 */
	static void AddElementWithText(String name, Element elem, String childelem)
	{
		Element child = new Element(childelem);
		child.setText(name);
		elem.addContent(child);
	}
		
		

	/**
	 * Create an element with the parameter String and add it to the Element given in parameter
	 * @param elem Element
	 * @param childelem String 
	 */
	static void AddElement(Element elem, String childelem)
	{
		Element child = new Element(childelem);
		elem.addContent(child);
	}
	
	
/**
 * Method which allows to create a HTML file thanks to a XSL stylesheet
 * 
 * @param XMLFile String
 * @param XSLFile String
 * @param HTMLFile String
 */
	static void outputXSLT(String XMLFile, String XSLFile, String HTMLFile)
	{
	   try
	   {
	   	  //Creation of the transformer with a XSL source which will 
	   	  // allows the transformation from XML to HTML
	      TransformerFactory factory = TransformerFactory.newInstance();
	      Transformer transformer = factory.newTransformer(new StreamSource(XSLFile));
	      
	      //Transforms the XML file in a HTML file thanks to the transformer
	      transformer.transform(new StreamSource(XMLFile), new StreamResult(HTMLFile));
	   }
	   catch(Exception e){
	   	System.out.println("Problem during the XSL transformation");
	   }
	}
	
	
	
	/**
	 * Add a course to the Element "elem" (which should be a Course Element) in the XML file
	 * 
	 * @param elem Element
	 * @param datacourse CourseDto
	 */
	 
	static void CreateCourse(Element elem, CourseDto datacourse)
	{
		   //Add a CourseType Attribute
		   Attribute course_type = new Attribute("CourseType", datacourse.getSubjectType());
		   elem.setAttribute(course_type);
		   
		   //Add all the course attribute
		   Element name_course = new Element("NAME");
		   SubjectDto sub = null;
		try {
			sub = MainFrame.myDaybyday.getSubject(datacourse.getSubjectId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		name_course.setText(sub.getName());
		   elem.addContent(name_course);

		   Element teacher = new Element("TEACHER");
		   elem.addContent(teacher);
		   
		   TeacherDto t = null;
		try {
			t = (TeacherDto)MainFrame.myDaybyday.getTeachersOfCourse(datacourse.getCourseId()).get(0);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EntityNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AddElementWithText(t.getName() +" "+ t.getFirstname(), teacher, "TEACHERNAME");
		   	   
		   //Add a room Element to the XML File
		   Element room = new Element("ROOM");
		   RoomDto r=null;
		try {
			r = (RoomDto)MainFrame.myDaybyday.getRoomsOfCourse(datacourse.getCourseId()).get(0);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (EntityNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		room.setText(r.getName());
		   elem.addContent(room);
		   
		   //Add a building attribute to a room
		   String roombuilding = r.getBuilding();
		   if (roombuilding == null)
		   {
		   	   Attribute building = new Attribute("Building", "");
			   room.setAttribute(building);
		   }
		   else
		   {
			   Attribute building = new Attribute("Building", roombuilding);
			   room.setAttribute(building);		   	
		   }
		   
		   //Add an area attribute to a room
		   String roomarea = r.getArea();
		   if (roomarea == null)
		   {
		   	   Attribute area = new Attribute("Area", "");
			   room.setAttribute(area);
		   }
		   else
		   {
		   	   Attribute area = new Attribute("Area", roomarea);
			   room.setAttribute(area);		   	
		   }
		   
		   //The date and hours informations are in a timestamp object
		   //We must extract them from it
		   GregorianCalendar gc = new GregorianCalendar();
		   
		   gc.setTimeInMillis(datacourse.getStartDate().getTime());
		   String startD = gc.get(Calendar.DAY_OF_MONTH)+"/"+gc.get(Calendar.MONTH)+"/"+gc.get(Calendar.YEAR);
		   String startH = gc.get(Calendar.HOUR_OF_DAY)+":"+gc.get(Calendar.MINUTE);
		   
		   gc.setTimeInMillis(datacourse.getEndDate().getTime());
		   String endD = gc.get(Calendar.DAY_OF_MONTH)+"/"+gc.get(Calendar.MONTH)+"/"+gc.get(Calendar.YEAR);
		   String endH = gc.get(Calendar.HOUR_OF_DAY)+":"+gc.get(Calendar.MINUTE);
		   
		   Element starthour = new Element("STARTHOUR");
		   starthour.setText(startH);
		   elem.addContent(starthour);
		   
		   Element endhour = new Element("ENDHOUR");
		   endhour.setText(endH);
		   elem.addContent(endhour);

		   Element startdate = new Element("STARTDATE");
		   startdate.setText(startD);
		   elem.addContent(startdate);

		   Element enddate = new Element("ENDDATE");
		   enddate.setText(endD);
		   elem.addContent(enddate);

	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser filechouser = new JFileChooser();
		//filechouser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = -1;
		filechouser.setFileFilter(new FileFilter(){

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				    }

				    String extension = Utils.getExtension(f);
				    if (extension != null) {
					if (extension.equals(Utils.xml)) {
					        return true;
					} else {
					    return false;
					}
				    }

				    return false;

			}

			public String getDescription() {
				return "Fichier XML";
			}
		}
		);
		while (result != JFileChooser.APPROVE_OPTION){
			result = filechouser.showSaveDialog(null);
			if (result == JFileChooser.CANCEL_OPTION) return;
		}

		//filechouser.setVisible(true);
		
		MainFrame mainFrame = (MainFrame) refs[0];
	    	     
		
        Daybyday myDaybyday = MainFrame.myDaybyday;

        Object object = mainFrame.getSelectedObject();
        
        //contains all the Formation informations
        FormationDto form;
        
        if (object instanceof Formation)
        	form = (FormationDto) ((Formation) object).getDto();
        else return;
        
       //Creation of the name and the year of the formation  
	   String currentformationname = form.getName();
	   String currentformationyear = form.getFormationYear();
        
	   // Creation of a Formation Element that is added to the root Element
	   Element formation = new Element("FORMATION");
	   root.addContent(formation);
	   
	   //Add a Name Attribute to the Formation Element
	   Attribute name_formation = new Attribute("Name", currentformationname);
	   formation.setAttribute(name_formation);

	   //Add a Year Attribute to the Formation Element
	   Attribute year_formation = new Attribute("Year", currentformationyear);
	   formation.setAttribute(year_formation);

	   
	    // a list that contains all the Section of the Formation
        ArrayList sectionList = null;
		try {
			
			sectionList = myDaybyday.getSectionsOfFormation(form.getFormationId());
		} catch (RemoteException e1) {
			e1.printStackTrace();
			return;
		} catch (EntityNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// contains all the Section information
        SectionDto sec;
        
        //loop to parse the list of Section
        //the loop will create the courses of each section in the XML file
        for (int i=0;i< sectionList.size();i++)
        {
            sec = (SectionDto)sectionList.get(i);
            
    	    String currentsection = sec.getName();
    	    
            System.out.println(currentsection);
            System.out.println("");
            
            Element section = new Element("SECTION");
			formation.addContent(section);
            
			// Add a Name attribute to a Section (<SECTION Name = ...>)
			Attribute name_section = new Attribute("Name", currentsection);
			section.setAttribute(name_section);
            
            // a list that contains all the courses of a Section (not only for one week)
            ArrayList courseList = null;
			try {
				
				courseList = myDaybyday.getCoursesOfSection(sec.getSectionId());
			} catch (RemoteException e2) {
				e2.printStackTrace();
				return;
			} catch (EntityNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// contains all the Courses information
            CourseDto sectioncourse;
            
            //Creation of a Courses Element that will contain all the courses of a section
			Element courses = new Element("COURSES");
			section.addContent(courses);
			   
            //loop to parse the list of Courses to add all the courses of a section to the XML file
            for (int j=0;j< courseList.size();j++)
            {
                sectioncourse = (CourseDto)courseList.get(j);

 			   //Creation of a Course Element that will contain all the information of a course
 			   Element course = new Element("COURSE");
 			   courses.addContent(course);
 			   
 			   //Add the information of a course to the course Element
 			   CreateCourse(course, sectioncourse);
                
            }
        }
	       
	   
	   //display();
	   save(filechouser.getSelectedFile()+"");
	   //outputXSLT("Exercice1.xml", "xml2html.xsl", "Resultat.htm");
	   
	}
	public static class Utils {

	    public final static String xml = "xml";

	    /*
	     * Get the extension of a file.
	     */  
	    public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }
	    
	    public static String getTypeDescription(File f) {
	        String extension = Utils.getExtension(f);
	        String type = null;

	        if (extension != null) {
	            if (extension.equals(Utils.xml)) {
	                type = "Fichier XML";
	            } 
	        }
	        return type;
	    }

	}


}
