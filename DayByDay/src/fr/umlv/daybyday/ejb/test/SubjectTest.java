package fr.umlv.daybyday.ejb.test;

import fr.umlv.daybyday.ejb.facade.DaybydayHomeCache;
import fr.umlv.daybyday.ejb.facade.DaybydayHome;
import fr.umlv.daybyday.ejb.facade.Daybyday;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.timetable.*;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import java.util.Iterator;
import fr.umlv.daybyday.ejb.timetable.subject.*;
import java.util.ArrayList;

public class SubjectTest {
    public static void main(String[] args) {
       try {
           DaybydayHome DaybydayHome = (DaybydayHome)DaybydayHomeCache.getHome();
           Daybyday myDaybyday = DaybydayHome.create();

           //Création d'une subject
           //SubjectDto mySubjectDto = new SubjectDto("Systèmes distribués","GENERALE","DESS CRI","2004","Roussel","Gilles",new Integer(20),new Integer(20),new Integer(20),new Integer(1),new Integer(2),new Integer(2),"Description Corba",new Boolean(true));
           //myDaybyday.createSubject(mySubjectDto);

           //Modification d'une subject

           SubjectDto mySubjecttDto1 = myDaybyday.getSubject(new SubjectPK("GENIE LOGICIEL","GENERALE","DESS CRI","2004"));
           System.out.println(mySubjecttDto1.toString());
           mySubjecttDto1.setDescription("la matière qui tue!");
           //System.in.read();

           myDaybyday.updateSubject(mySubjecttDto1);

           ArrayList courses = myDaybyday.getCoursesOfSubject(new SubjectPK("GENIE LOGICIEL","GENERALE","DESS CRI","2004"));

           Iterator iterator = courses.iterator();
           while (iterator.hasNext()) {
               CourseDto courseDto = (CourseDto) iterator.next();
               System.out.println(courseDto.toString());

           }



       }  catch (StaleUpdateException suex) {
           System.out.println(suex.getMessage());
           String msg = "Quelqu'un d'autres a modifié l'enregistrement que vous voulez mettre à jours";
           msg += "\nVoulez-vous recharger les données ?";
           System.out.println(msg);


           }

       catch (Exception ex) {
           ex.printStackTrace();

       }
    }
}
