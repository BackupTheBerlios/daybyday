package fr.umlv.daybyday.test.jms.listeners;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.ejb.timetable.subject.SubjectDto;
import fr.umlv.daybyday.gui.FormationTree;

public class CourseSectionTreeListener implements MessageListener {

    SectionDto sectionDto;
    Daybyday daybyday;
    FormationTree sectionView;

    public CourseSectionTreeListener(SectionDto sectionDto, FormationTree sectionView, Daybyday daybyday) {
        this.sectionDto = sectionDto;
        this.daybyday = daybyday;
        this.sectionView = sectionView;
    }

    public void setViewReference (SectionDto sectionDto, FormationTree sectionView){
        this.sectionDto = sectionDto;
        this.sectionView = sectionView;
    }
    
    public void onMessage(Message message) {
        ObjectMessage objecMessage = (ObjectMessage)message;
        try {
            CourseDto courseDto = (CourseDto) objecMessage.getObject();
            System.out.println("JE SUIS UNE SECTION ET J AI RECU UN MESSAGE");
            
            String SubjectId = courseDto.getSubjectId();
            SubjectDto subjecDto = daybyday.getSubject(SubjectId);

            String SectionId = subjecDto.getSectionId();
            SectionDto sectionDto = daybyday.getSection(SectionId);

            
            System.out.println( this.sectionDto.getSectionId() + " " + this.sectionDto.getSectionParentId());
              
           System.out.println( sectionDto.getSectionId() + " " + sectionDto.getSectionId());
            if (this.sectionDto.equals((SectionDto)sectionDto) || this.sectionDto.getSectionParentId().equalsIgnoreCase(sectionDto.getSectionId())){
            	//MainFrame.myDaybyday.updateSection(MainFrame.myDaybyday.getSection(this.sectionDto.getSectionId()));
            	System.out.println("je refresh section");
            	sectionView.refresh();
                /*if(courseDto.getOperation().equals("create")){
                    sectionView.addToView(courseDto);
                }
                else if (courseDto.getOperation().equals("remove")){
                    sectionView.removeFromView(courseDto);
                }*/
            }

            else System.out.println("Le message ne m'interesse pas");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
