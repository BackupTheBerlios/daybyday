package fr.umlv.daybyday.ejb.facade;



//import fr.umlv.daybyday.ejb.facade.daybyday.*;
import javax.ejb.*;
import javax.rmi.*;
import java.util.*;
import javax.naming.*;

import fr.umlv.daybyday.ejb.facade.DaybydayHome;

public class DaybydayHomeCache {

    private static Hashtable homes = new Hashtable();
    private static Context ctx;

    public DaybydayHomeCache() throws NamingException {

    }

    public static synchronized EJBHome getHome() throws NamingException {

        EJBHome home = (EJBHome)homes.get(DaybydayHome.class);
        if (home == null) {
            ctx = getInitialContext();
            Object objref = ctx.lookup("Daybyday");
            home = (EJBHome) PortableRemoteObject.narrow(objref, DaybydayHome.class);
            homes.put(DaybydayHome.class, home);
        }
        return home;
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        environment.put(Context.PROVIDER_URL, "jnp://pccop2b104-05:1099");
        return new InitialContext(environment);
    }

}
