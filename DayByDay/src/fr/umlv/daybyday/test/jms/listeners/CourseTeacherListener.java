package fr.umlv.daybyday.test.jms.listeners;

import javax.jms.*;
import fr.umlv.daybyday.ejb.resource.teacher.TeacherDto;
import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.test.jms.views.TeacherView;
import fr.umlv.daybyday.ejb.timetable.course.CourseTeacherPK;
import fr.umlv.daybyday.ejb.timetable.course.*;

public class CourseTeacherListener implements MessageListener {

    TeacherDto teacherDto;
    Daybyday daybyday;
    TeacherView teacherView;

    public CourseTeacherListener(TeacherDto teacherDto, TeacherView teacherView,  Daybyday daybyday) {
        this.teacherDto = teacherDto;
        this.daybyday = daybyday;
        this.teacherView = teacherView;
    }
    public void onMessage(Message message) {
        ObjectMessage objecMessage = (ObjectMessage)message;
        try {
            CourseTeacherPK courseTeacherPK = (CourseTeacherPK) objecMessage.getObject();

            String teacherId = courseTeacherPK.getTeacherId();

            if (teacherDto.getTeacherId().equals(teacherId)){
                String courseId = courseTeacherPK.getCourseId();
                CourseDto courseDto = daybyday.getCourse(courseId);
                if (courseTeacherPK.getOperation().equals("add")){
                    teacherView.addToView(courseDto);
                }
                else if (courseTeacherPK.getOperation().equals("remove")){
                    teacherView.removeFromView(courseDto);
                }
            }

            else System.out.println("Le message ne m'interesse pas");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
