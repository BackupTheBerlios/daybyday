package fr.umlv.daybyday.ejb.test;

import java.util.*;

import fr.umlv.daybyday.ejb.*;
import fr.umlv.daybyday.ejb.timetable.section.*;
import fr.umlv.daybyday.ejb.timetable.subject.*;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.util.exception.CourseConfusionException;
import fr.umlv.daybyday.ejb.facade.*;

public class SectionTest {
    public static void main(String[] args) {
        try {
            DaybydayHome DaybydayHome = (DaybydayHome) DaybydayHomeCache.
                                        getHome();
            Daybyday myDaybyday = DaybydayHome.create();

            //Cr�ation d'une section
            //
            SectionDto mySectionDto = new SectionDto("Communication","DESS CRI","2005","GENERALE","Laporte","Eric","",new Boolean(true));
            //myDaybyday.createSection(mySectionDto);

            //Modification d'une section

            //SectionDto mySectiontDto1 = myDaybyday.getSection(new SectionPK("Communication", "DESS CRI", "2004"));
            //System.out.println(mySectiontDto1.toString());
            //mySectiontDto1.setDescription("La fili�re communication");

            //System.in.read();

            //myDaybyday.updateSection(mySectiontDto1);

            //r�cup�rer les fili�res filles d'une fili�re

            /*System.out.println("\n=====Les fili�res filles de la fili�re 'GENERALE' sont : \n");
            ArrayList sectionChildren = myDaybyday.getChildrenOfSection(new SectionPK("GENERALE", "DESS CRI", "2004"));

            Iterator iterator1 = sectionChildren.iterator();
            while (iterator1.hasNext()) {
                SectionDto sectionDto = (SectionDto) iterator1.next();
                System.out.println(sectionDto.toString());

            }*/



            //R�cup�rer les mati�res de la fili�re
            System.out.println("\n=====Les mati�re de la fili�re r�seau sont : \n");
            ArrayList subjects = myDaybyday.getSubjectsOfSection(new SectionPK(
                    "reseau", "DESS CRI", "2004"));

            Iterator iterator = subjects.iterator();
            while (iterator.hasNext()) {
                SubjectDto subjectDto = (SubjectDto) iterator.next();
                System.out.println(subjectDto.toString());

            }

            /*System.out.println("\n=====Les cours de la fili�re r�seau sont : \n");

            ArrayList courses = myDaybyday.getCoursesOfSection(new SectionPK("reseau", "DESS CRI", "2004"));

            Iterator iterator2 = courses.iterator();
            while (iterator.hasNext()){
                CourseDto courseDto = (CourseDto) iterator2.next();
                System.out.println(courseDto.toString());

            }*/


        } /*catch (StaleUpdateException suex) {
            System.out.println(suex.getMessage());
            String msg =
                    "Quelqu'un d'autres a modifi� l'enregistrement que vous voulez mettre � jours";
            msg += "\nVoulez-vous recharger les donn�es ?";
            System.out.println(msg);

        }*/

        catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
