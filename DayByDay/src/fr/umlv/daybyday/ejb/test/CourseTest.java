package fr.umlv.daybyday.ejb.test;

import fr.umlv.daybyday.ejb.facade.*;

//import fr.umlv.daybyday.ejb.util.exception.*;
import fr.umlv.daybyday.ejb.facade.DaybydayHomeCache;
import fr.umlv.daybyday.ejb.timetable.course.*;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionPK;

import java.sql.*;
import java.util.*;


public class CourseTest {
    public static void main(String[] args) {
       try {
           DaybydayHome DaybydayHome = (DaybydayHome)DaybydayHomeCache.getHome();
           Daybyday myDaybyday = DaybydayHome.create();

           //Création d'un cours
          // 1999-01-05 12:05:06
           Timestamp startDate = toTimeStamp(1999, Calendar.JANUARY,5,10,05,06);
           Timestamp endDate = toTimeStamp(1999, Calendar.JANUARY,8,11,00,00);

         //
     //      CourseDto myCourseDto = myDaybyday.getCourse(new CoursePK("Admin reseaux","reseau","DESS CRI","2004","cours",startDate,"groupe1"));
      //     myCourseDto.setStartDate(endDate);
          // myCourseDto.setStartDate(endDate);
           SectionDto sec = myDaybyday.getSection(new SectionPK("reseau","DESS CRI","2004"));
           
           CourseDto myCourseDto = new CourseDto("GENIE LOGICIEL","GENERALE","DESS CRI","2004","cours",startDate,endDate,"groupe1","Chabert","Samuel","2B114","copernic","mlv","retro1","copernic","mlv","cours de réseau",new Integer (250),new Boolean(true));
          myDaybyday.createCourse(myCourseDto);

           //Récupération d'un cours existant

           //CourseDto myCoursetDto1 = myDaybyday.getCourse(new CoursePK("Admin reseaux","reseau","DESS CRI","2004","cours",startDate));
           //System.out.println(myCoursetDto1.toString());


           //Modification de quelques propriétés
           //myCoursetDto1.setDescription("Le cours des gagnants!");
           //System.in.read();

           //Mise à jour
           //myDaybyday.updateCourse(myCoursetDto1);

           /*ArrayList courses = myDaybyday.getCoursesOfSubject(new SubjectPK("Admin reseaux","reseau","DESS CRI","2004"));

           Iterator iterator = courses.iterator();
           while (iterator.hasNext()){
               CourseDto courseDto = (CourseDto) iterator.next();
               System.out.println(courseDto.toString());

           }*/

           /*ArrayList courses = myDaybyday.getCoursesOfSection(new SectionPK("reseau", "DESS CRI", "2004"));

           Iterator sectionIterator = courses.iterator();
           while (sectionIterator.hasNext()){
               CourseDto courseDto = (CourseDto) sectionIterator.next();
               //System.out.println(sectionIterator.next().getClass().getName());
               System.out.println(courseDto.toString());

           }*/




      /* }  catch (StaleUpdateException suex) {
           System.out.println(suex.getMessage());
           String msg = "Quelqu'un d'autres a modifié l'enregistrement que vous voulez mettre à jours";
           msg += "\nVoulez-vous recharger les données ?";
           System.out.println(msg);*/


           }

       catch (Exception ex) {
           ex.printStackTrace();

       }
    }


    public static void VerifieConfusion(){

        //Verification d'un cours avec un cours de la même filière

        //Vérification d'un cours avec les cours de la filière parente




    }

    public static Timestamp toTimeStamp(int year, int month, int day, int hour, int minute, int second){

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        calendar.set(Calendar.MILLISECOND,0);



        return new Timestamp(calendar.getTimeInMillis());


    }
}
