package fr.umlv.daybyday.test.jms.views;

import java.util.Hashtable;

import javax.jms.JMSException;
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
import fr.umlv.daybyday.ejb.timetable.formation.FormationDto;
import fr.umlv.daybyday.test.ejb.DaybydayHomeCache;

public class FormationView{

    FormationDto formationDto;
    static Daybyday daybyday = null;

    public FormationView(FormationDto formationDto) throws NamingException, JMSException {
        this.formationDto = formationDto;
        listenToCourses();
      }

   /*   public static void main(String [] args) throws Exception {
          init();
          FormationDto formationDto = daybyday.getFormation(new FormationBusinessPK("DESS CRI", "2005"));
          new FormationView(formationDto);
      }

*/
      public void addToView(CourseDto courseDto){
          System.out.println("Un nouveau cours vien d'être ajouté : " + courseDto.toString());
      }

      public void removeFromView(CourseDto courseDto){
          System.out.println("Un cours vien d'être supprimé : " + courseDto.toString());
      }

      public void listenToCourses(){
          try{
              Context jndiContext = getInitialContext();
              TopicConnectionFactory factory = (TopicConnectionFactory)
                                               jndiContext.lookup(
                      "ConnectionFactory");
              Topic topic = (Topic) jndiContext.lookup(
                      "topic/CourseTopic");
              TopicConnection connect = factory.createTopicConnection();
              TopicSession session = connect.createTopicSession(false,
                      Session.AUTO_ACKNOWLEDGE);
              TopicSubscriber subscriber = session.createSubscriber(topic);
             // subscriber.setMessageListener(new CourseFormationListener(
               //       formationDto, this, daybyday));
              connect.start();
              System.out.println(
                      "Attend l'arrivee de messages sur topic/CourseTopic...");
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
