package fr.umlv.daybyday.test.jms.listeners;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.gui.TimeTableTable;

public class CourseSectionListener implements MessageListener {

    SectionDto sectionDto;
    Daybyday daybyday;
    TimeTableTable sectionView;

    public CourseSectionListener(SectionDto sectionDto, TimeTableTable sectionView, Daybyday daybyday) {
        this.sectionDto = sectionDto;
        this.daybyday = daybyday;
        this.sectionView = sectionView;
    }

    public void setViewReference (SectionDto sectionDto, TimeTableTable sectionView){
        this.sectionDto = sectionDto;
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

           
            if (this.sectionDto.equals((SectionDto)sectionDto) || this.sectionDto.getSectionParentId().equalsIgnoreCase(sectionDto.getSectionId())){
            	sectionView.refresh();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
