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
import java.util.List;

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

import fr.umlv.daybyday.ejb.facade.Daybyday;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
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

	
//	Creation of the Root Element
	static Element racine = new Element("EDT");
	//On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	static org.jdom.Document document = new Document(racine);
	

/**
 * Method that displays the XML file created
 */
	static void display()
	{
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document, System.out);
	   }
	   catch (java.io.IOException e){}
	}

/**
 * 
 * @param file
 */
	static void enregistre(String file)
	{
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
	      //avec en argument le nom du fichier pour effectuer la sérialisation.
	      sortie.output(document, new FileOutputStream(file));
	      //System.out.println("Enregistrement du fichier "+ file +" termine!" );
	   }
	   catch (java.io.IOException e){}
	}
	
		
	/**
	 * Create an element with the parameter String and add it to the Element given in parameter and add the value 
	 */
	static void AddElementWithText(String name, Element elem, String balise)
	{
		Element bal = new Element(balise);
		bal.setText(name);
		elem.addContent(bal);
	}
		
		

	/**
	 * Create an element with the parameter String and add it to the Element given in parameter
	 */
	static void AddElement(Element elem, String balise)
	{
		Element bal = new Element(balise);
		elem.addContent(bal);
	}
	
		
	static void outputXSLT(String fichierXML, String fichierXSL, String fichierHTML)
	{
	   try
	   {
	      //On définit un transformer avec la source XSL
	      //qui va permettre la transformation du XML vers le HTML
	      TransformerFactory factory = TransformerFactory.newInstance();
	      Transformer transformer = factory.newTransformer(new StreamSource(fichierXSL));
	      
	      //On transforme le fichier XML fourni en entree grace a notre transformer.
	      //La méthoded transform() prend en argument le document d'entree associé au transformer
	      //et un document JDOMResult, résultat de la transformation TraX
	      transformer.transform(new StreamSource(fichierXML), new StreamResult(fichierHTML));
	      
	      System.out.println("Transformation XSL terminee!");
	   }
	   catch(Exception e){
	   	System.out.println("Probleme dans la transformation XSL :(");
	   }
	}
	
	
	static Element findSection(String sectionname, List al)
	{
		int index = 0;
		int length = al.size();
		System.out.println("taille="+length);
		while (index < length)
		{
			Element tmp = (Element)al.get(index);
			System.out.println("--> "+tmp.getAttributeValue("Name"));
			if ( sectionname == tmp.getAttributeValue("Name"))
			{
				System.out.println("Une section a ete trouvee");
				return tmp;
			}
			index++;
			System.out.println("index++ dans findSection()");
		}
		System.out.println("on retourne NULL");
		return null;
	}
	
	
	/**
	 * Add a course to the Element "elemt" (which should be a CoursesElement) in the XML file
	 */
	 
	static void CreateCourse(Element elem, CourseDto datacourse)
	{
		   //On ajoute un attribut Type a un Cours
		   Attribute course_type = new Attribute("CourseType", datacourse.getSubjectType());
		   elem.setAttribute(course_type);
		   
		   //Add all the course attribute
		   Element name_course = new Element("NAME");
		   name_course.setText(datacourse.getSubjectName());
		   elem.addContent(name_course);

		   Element teacher = new Element("TEACHER");
		   elem.addContent(teacher);
		   
		   AddElementWithText(datacourse.getTeacherName() +" "+ datacourse.getTeacherFirstname(), teacher, "TEACHERNAME");
		   	   
		   //Add a room Element to the XML File
		   Element room = new Element("ROOM");
		   room.setText(datacourse.getRoomName());
		   elem.addContent(room);
		   
		   //Add a building attribute to a room
		   Attribute building = new Attribute("Building", datacourse.getRoomBuilding());
		   room.setAttribute(building);
		   
		   //Add an area attribute to a room
		   Attribute area = new Attribute("Area", datacourse.getRoomArea());
		   room.setAttribute(area);
		   
		   GregorianCalendar gc = new GregorianCalendar();
		   
		   gc.setTimeInMillis(datacourse.getStartDate().getTime());
		   String startD = gc.get(Calendar.DAY_OF_MONTH)+"/"+gc.get(Calendar.MONTH)+"/"+gc.get(Calendar.YEAR);
		   String startH = gc.get(Calendar.HOUR)+":"+gc.get(Calendar.MINUTE);
		   
		   gc.setTimeInMillis(datacourse.getEndDate().getTime());
		   String endD = gc.get(Calendar.DAY_OF_MONTH)+"/"+gc.get(Calendar.MONTH)+"/"+gc.get(Calendar.YEAR);
		   String endH = gc.get(Calendar.HOUR)+":"+gc.get(Calendar.MINUTE);
		   
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
		
		System.out.println("FileChouser");
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

		// contains all the Formation informations
        //FormationDto form = myDaybyday.getFormationOfSection(new SectionPK("reseau","DESS CRI","2004"));
        
        Object object = mainFrame.getSelectedObject();
        
        FormationDto form;
        
        if (object instanceof Formation)
        	form = (FormationDto) ((Formation) object).getDto();
        else return;
        
        //Creation of the name and the year of the formation  
	    String currentformationname = form.getName();
	    String currentformationyear = form.getFormationYear();
        
		
	   // Creation of a Formation Element that is added to the root Element
	   Element formation = new Element("FORMATION");
	   racine.addContent(formation);
	   
	   	      
	   //On crée un nouvel Attribut classe et on l'ajoute à la formation
	   //grâce à la méthode setAttribute pour specifier le nom de la formation
	   Attribute name_formation = new Attribute("Name", currentformationname);
	   formation.setAttribute(name_formation);
	   //On ajoute un attribut Year a une formation
	   Attribute year_formation = new Attribute("Year", currentformationyear);
	   formation.setAttribute(year_formation);

	   
	    // a list that contains all the Section of the Formation
        ArrayList sectionList;
		try {
			sectionList = myDaybyday.getSectionsOfFormation(form.getFormationPK());
		} catch (RemoteException e1) {
			e1.printStackTrace();
			return;
		}
		// contains all the Section information
        SectionDto sec;
        
        //loop to parse the list of Section
        //the loop will create the courses of each section in the XML file
        for (int i=0;i< sectionList.size();i++)
        {
            sec = (SectionDto)sectionList.get(i);
            
            //
    	    String currentsection = sec.getName();
    	    
            System.out.println(currentsection);
            System.out.println("");
            
            Element section = new Element("SECTION");
			formation.addContent(section);
            
			// Add a Name attribute to a Section (<SECTION Name = ...>)
			Attribute name_section = new Attribute("Name", currentsection);
			section.setAttribute(name_section);
            
            // a list that contains all the courses of a Section (not only for one week)
            ArrayList courseList;
			try {
				courseList = myDaybyday.getCoursesOfSection(sec.getSectionPK());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				return;
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
	   enregistre(filechouser.getSelectedFile()+"");
	   //System.out.println("Debut de la transformation XSLT...\n");
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
