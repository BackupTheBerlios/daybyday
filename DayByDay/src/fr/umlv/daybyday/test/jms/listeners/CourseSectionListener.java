package fr.umlv.daybyday.test.jms.listeners;

import javax.jms.*;
import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.test.jms.views.SectionView;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;

public class CourseSectionListener implements MessageListener {

    SectionDto sectionDto;
    Daybyday daybyday;
    SectionView sectionView;

    public CourseSectionListener(SectionDto sectionDto, SectionView sectionView, Daybyday daybyday) {
        this.sectionDto = sectionDto;
        this.daybyday = daybyday;
        this.sectionView = sectionView;
    }

    public void onMessage(Message message) {
        ObjectMessage objecMessage = (ObjectMessage)message;
        try {
            CourseDto courseDto = (CourseDto) objecMessage.getObject();

            String SubjectId = courseDto.getSubjectId();
            SubjectDto subjecDto = daybyday.getSubject(SubjectId);

            String SectionId = subjecDto.getSectionId();
            SectionDto sectionDto = daybyday.getSection(SectionId);



            if (this.sectionDto.equals((SectionDto)sectionDto)){
                if(courseDto.getOperation().equals("create")){
                    sectionView.addToView(courseDto);
                }
                else if (courseDto.getOperation().equals("remove")){
                    sectionView.removeFromView(courseDto);
                }
            }

            else System.out.println("Le message ne m'interesse pas");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
