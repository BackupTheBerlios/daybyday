package fr.umlv.daybyday.test.jms.listeners;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.gui.FormationTree;


public class CourseFormationTreeListener implements MessageListener {

    FormationDto formationDto;
    Daybyday daybyday;
    FormationTree formationView;


    public CourseFormationTreeListener(FormationDto formationDto, FormationTree table, Daybyday daybyday) {
        this.formationDto = formationDto;
        this.daybyday = daybyday;
        this.formationView = table;
    }

    public void onMessage(Message message) {
        ObjectMessage objecMessage = (ObjectMessage)message;
        try {
            CourseDto courseDto = (CourseDto) objecMessage.getObject();

            System.out.println("JE SUIS UNE FORMATION ET J AI RECU UN MESSAGE");
            
            String SubjectId = courseDto.getSubjectId();
            SubjectDto subjecDto = daybyday.getSubject(SubjectId);

            String SectionId = subjecDto.getSectionId();
            SectionDto sectionDto = daybyday.getSection(SectionId);

            String FormationId = sectionDto.getFormationId();
            FormationDto formationDto = daybyday.getFormation(FormationId);

            //System.out.println(this.formationDto + " " + formationDto);
            if (this.formationDto.equals((FormationDto)formationDto)){
            	System.out.println("c'est ok");
            	formationView.refresh();
                /*if(courseDto.getOperation().equals("create")){
                	
                    System.out.println("formationView.addToView(courseDto);");
                }
                else if (courseDto.getOperation().equals("remove")){
                	System.out.println("formationView.removeFromView(courseDto);");
                }*/
            }

            else System.out.println("Le message ne m'interesse pas");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
