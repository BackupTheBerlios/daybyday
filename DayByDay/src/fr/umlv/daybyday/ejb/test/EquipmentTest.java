package fr.umlv.daybyday.ejb.test;

import fr.umlv.daybyday.ejb.facade.*;

import fr.umlv.daybyday.ejb.resource.equipment.*;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;
import fr.umlv.daybyday.ejb.timetable.course.*;
import java.util.Iterator;
import fr.umlv.daybyday.ejb.resource.teacher.*;
import java.util.ArrayList;

public class EquipmentTest {
    public static void main(String[] args) {
       try {
           DaybydayHome DaybydayHome = (DaybydayHome)DaybydayHomeCache.getHome();
           Daybyday myDaybyday = DaybydayHome.create();

           //Création d'un enseignant
           //EquipmentDto myEquipmentDto = new EquipmentDto("retro3","copernic","mlv","",new Boolean(true));
           //myDaybyday.createEquipment(myEquipmentDto);

           //Modification d'un enseignant

           EquipmentDto myEquipmentDto1 = myDaybyday.getEquipment(new EquipmentPK("retro3","copernic","mlv"));
           System.out.println(myEquipmentDto1.toString());
           myEquipmentDto1.setDescription("Un joli retro-projecteur!");
           //System.in.read();

           myDaybyday.updateEquipment(myEquipmentDto1);


           ArrayList courses = myDaybyday.getCoursesOfEquipment(new EquipmentPK("retro3","copernic","mlv"));
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
