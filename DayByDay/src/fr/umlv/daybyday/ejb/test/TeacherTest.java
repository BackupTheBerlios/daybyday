package fr.umlv.daybyday.ejb.test;

import fr.umlv.daybyday.ejb.facade.*;

import fr.umlv.daybyday.ejb.resource.teacher.*;
import java.util.*;
import fr.umlv.daybyday.ejb.timetable.course.*;

public class TeacherTest {
    public static void main(String[] args) {
       try {
           DaybydayHome DaybydayHome = (DaybydayHome)DaybydayHomeCache.getHome();
           Daybyday myDaybyday = DaybydayHome.create();

           //Création d'un enseignant
           TeacherDto myTeacherDto = new TeacherDto("Zipstein","zipo","zip@univ-mlv.fr","06 66 66 66 66","0001","interne","prof de crypto et d'algorithmes",new Boolean(true));
           myDaybyday.createTeacher(myTeacherDto);

           //Modification d'un enseignant

           TeacherDto myTeacherDto1 = myDaybyday.getTeacher(new TeacherPK("Zipstein","zipo"));
           System.out.println(myTeacherDto1.toString());
           myTeacherDto1.setDescription("La crypto c'est magnifique");
           //System.in.read();
           myDaybyday.updateTeacher(myTeacherDto1);

           //Récupérer l'emploi du temps de Remi Forax
           ArrayList courses = myDaybyday.getCoursesOfTeacher(new TeacherPK("FORAX", "REMI"));

           Iterator iterator = courses.iterator();
           while (iterator.hasNext()) {
               CourseDto courseDto = (CourseDto) iterator.next();
               System.out.println(courseDto.toString());

           }
           //Récupérer la liste de tous les enseignants
           ArrayList teachers = myDaybyday.getAllTeachers();
           Iterator teachersIterator = teachers.iterator();
           while (teachersIterator.hasNext()) {
               TeacherDto teacherDto = (TeacherDto) teachersIterator.next();
               System.out.println(teacherDto.toString());

           }



       } catch (Exception ex) {
           ex.printStackTrace();

       }





    }

}
