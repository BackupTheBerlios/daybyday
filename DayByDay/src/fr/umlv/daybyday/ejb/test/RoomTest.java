package fr.umlv.daybyday.ejb.test;

import fr.umlv.daybyday.ejb.facade.DaybydayHomeCache;
import fr.umlv.daybyday.ejb.facade.DaybydayHome;
import fr.umlv.daybyday.ejb.facade.Daybyday;

import fr.umlv.daybyday.ejb.*;
import fr.umlv.daybyday.ejb.resource.room.*;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import java.util.Iterator;
import fr.umlv.daybyday.ejb.resource.equipment.EquipmentPK;
import java.util.ArrayList;

public class RoomTest {

    public static void main(String[] args) {
        try {
            DaybydayHome DaybydayHome = (DaybydayHome)DaybydayHomeCache.getHome();
            Daybyday myDaybyday = DaybydayHome.create();

            //Création d'une salle
            //RoomDto myRoomDto1 = new RoomDto("1A001","copernic","mlv","description....", new Integer(50), new Boolean(true));
            //myDaybyday.createRoom(myRoomDto1);

            //Consultation d'un élément existant
            RoomDto myRoomDto = myDaybyday.getRoom(new RoomPK("1A001","copernic","mlv"));
            System.out.println(myRoomDto.toString());

            //Modification
            myRoomDto.setDescription("Salle contient vidéo projecteur");

            //Util lorsqu'on lance de clients de tests pour voir si ils peuvent mettre à jour le même enregistrement
            //System.in.read();

            //Mise à jour
            myDaybyday.updateRoom(myRoomDto);


            ArrayList courses = myDaybyday.getCoursesOfRoom(new RoomPK("1A001","copernic","mlv"));
            Iterator iterator = courses.iterator();
            while (iterator.hasNext()) {
                CourseDto courseDto = (CourseDto) iterator.next();
                System.out.println(courseDto.toString());

            }


            System.exit(0);

        } catch (Exception ex) {
            System.err.println("Caught an exception:");
            ex.printStackTrace();
        }
    } // main

} // class
