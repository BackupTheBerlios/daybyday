package fr.umlv.daybyday.test.jms.views;

import java.util.Hashtable;

import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.umlv.daybyday.ejb.facade.daybyday.Daybyday;
import fr.umlv.daybyday.ejb.facade.daybyday.DaybydayHome;
import fr.umlv.daybyday.ejb.timetable.course.CourseDto;
import fr.umlv.daybyday.ejb.timetable.section.SectionDto;
import fr.umlv.daybyday.test.ejb.DaybydayHomeCache;

public class SectionView {

    SectionDto sectionDto;
    static Daybyday daybyday = null;
    public SectionView(SectionDto sectionDto) {
        this.sectionDto = sectionDto;
        listenToCourses();
    }

 /*   public static void main(String [] args) throws Exception {
        init();
        TeacherDto teacherDto = daybyday.getTeacher(new TeacherBusinessPK("Forax", "Rémi"));
        new TeacherView(teacherDto);
    }
*/
    public void addToView(CourseDto courseDto){
        System.out.println("Un nouveau cours vien d'être ajouté : " + courseDto.toString());
    }

    public void removeFromView(CourseDto courseDto){
        System.out.println("Un cours vien d'être supprimé : " + courseDto.toString());
    }


    public void listenToCourses(){
       // listenToTopic("topic/CourseTopic",new CourseSectionListener(sectionDto, this, daybyday));
    }

    private void listenToTopic(String topicName, MessageListener messageListener){
        try{
            Context jndiContext = getInitialContext();
            TopicConnectionFactory factory = (TopicConnectionFactory) jndiContext.lookup("ConnectionFactory");
            Topic topic = (Topic) jndiContext.lookup(topicName);
            TopicConnection connect = factory.createTopicConnection();
            TopicSession session = connect.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber subscriber = session.createSubscriber(topic);
            subscriber.setMessageListener(messageListener);
            System.out.println("J'ecoute sur la topic : " + topicName);
            connect.start();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public static void init(){
        DaybydayHome DaybydayHome = null;
        try {
            DaybydayHome = (DaybydayHome) DaybydayHomeCache.getHome();
            daybyday = DaybydayHome.create();
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
    }

    public static Context getInitialContext() throws NamingException {
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        environment.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        return new InitialContext(environment);
    }

}
