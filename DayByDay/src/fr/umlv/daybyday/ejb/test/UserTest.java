package fr.umlv.daybyday.ejb.test;

import fr.umlv.daybyday.ejb.admin.user.UserDto;
import fr.umlv.daybyday.ejb.admin.user.UserPK;
import fr.umlv.daybyday.ejb.facade.Daybyday;
import fr.umlv.daybyday.ejb.facade.DaybydayHome;
import fr.umlv.daybyday.ejb.facade.DaybydayHomeCache;

public class UserTest {

    public static void main(String[] args) {
        try {

            DaybydayHome DaybydayHome = (DaybydayHome)DaybydayHomeCache.getHome();
            Daybyday myDaybyday = DaybydayHome.create();


            //Création d'un utilisateur
            //UserDto myUserDto1 = new UserDto("Stéphanie","Martinez","stephanie.martin@univ-mlv.fr","06 21 23 24 25","3x100","stephpass","user",new Boolean(true));
            //myDaybyday.createUser(myUserDto1);


            //Consultation d'un élément existant
            //UserDto myUserDto = myDaybyday.getUser(new UserPK("Duprat","Marie-Helene"));
            UserDto myUserDto = myDaybyday.getUser(new UserPK("Stéphanie","Martinez"));
            System.out.println(myUserDto.toString());
            System.in.read();
            //myUserDto.setOffice("123");
            //myDaybyday.updateUser(myUserDto);


            //insertInfo(myDaybyday);
            //getSomeInfo(myDaybyday);
            //getMoreInfo(myDaybyday);

            System.exit(0);

        } catch (Exception ex) {
            System.err.println("Caught an exception:");
            ex.printStackTrace();
        }
    } // main
} // class
