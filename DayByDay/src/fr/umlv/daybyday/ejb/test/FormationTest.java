package fr.umlv.daybyday.ejb.test;

import java.util.ArrayList;
import java.util.Iterator;

import fr.umlv.daybyday.ejb.facade.Daybyday;
import fr.umlv.daybyday.ejb.facade.DaybydayHome;
import fr.umlv.daybyday.ejb.facade.DaybydayHomeCache;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationPK;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.util.exception.StaleUpdateException;

public class FormationTest {
    public static void main(String[] args) {
        try {
            DaybydayHome DaybydayHome = (DaybydayHome) DaybydayHomeCache.
                                        getHome();
            Daybyday myDaybyday = DaybydayHome.create();

            //Cr�ation d'une formation

            //FormationDto myFormationDto = new FormationDto("DESS CRI","2007","","Laporte","Eric","Duprat","Marie-Helene",new Boolean(true));
            //myDaybyday.createFormation(myFormationDto);


            //Modification d'une formation

            FormationDto myFormationtDto1 = myDaybyday.getFormation(new
                    FormationPK("DESS CRI", "2004"));
            System.out.println(myFormationtDto1.toString());
            myFormationtDto1.setDescription("La formation des gagnants 2004!");

            //Pour pouvoir tester le call-back decommenter la ligne suivante et lancer deux instances de ce programme
            System.in.read();
            myDaybyday.updateFormation(myFormationtDto1);

            //R�cup�ration des fili�res d'une formation

            System.out.println("\n==Les sections qui appartiennent � la formation 'DESS CRI 2004' sont : \n");
            ArrayList sections = myDaybyday.getSectionsOfFormation(new FormationPK("DESS CRI", "2004"));

            Iterator iterator = sections.iterator();
            while (iterator.hasNext()) {
                SectionDto sectionDto = (SectionDto) iterator.next();
                System.out.println(sectionDto.toString());

            }

        } catch (StaleUpdateException suex) {
            System.out.println(suex.getMessage());
            String msg =
                    "Quelqu'un d'autres a modifi� l'enregistrement que vous voulez mettre � jours";
            msg += "\nVoulez-vous recharger les donn�es ?";
            System.out.println(msg);

        }

        catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
