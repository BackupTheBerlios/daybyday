package fr.umlv.daybyday.test.jms.listeners;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.gui.TimeTableTable;


public class CourseFormationListener implements MessageListener {

    FormationDto formationDto;
    Daybyday daybyday;
    TimeTableTable formationView;


    public CourseFormationListener(FormationDto formationDto, TimeTableTable table, Daybyday daybyday) {
        this.formationDto = formationDto;
        this.daybyday = daybyday;
        this.formationView = table;
    }
    
    public void setViewReference (FormationDto formationDto, TimeTableTable formationView){
        this.formationDto = formationDto;
        this.formationView = formationView;
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
