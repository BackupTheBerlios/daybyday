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

            
            String SubjectId = courseDto.getSubjectId();
            SubjectDto subjecDto = daybyday.getSubject(SubjectId);

            String SectionId = subjecDto.getSectionId();
            SectionDto sectionDto = daybyday.getSection(SectionId);

            String FormationId = sectionDto.getFormationId();
            FormationDto formationDto = daybyday.getFormation(FormationId);

            if (this.formationDto.equals((FormationDto)formationDto)){

            	formationView.refresh();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
