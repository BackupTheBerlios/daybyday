

/*
 * Created on 20 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday;



import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import fr.umlv.daybyday.gui.MainFrame;
import fr.umlv.daybyday.gui.Windows;
import fr.umlv.daybyday.model.Grid;


 


/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DaYbYDaY {

	public static void main (String [] args) throws RemoteException, NamingException, CreateException{
		
		//setUIFont (new javax.swing.plaf.FontUIResource("Arial",0,12));
		UIManager.put("Button.font", new javax.swing.plaf.FontUIResource("Arial",0,12)); 
		//UIManager.put("Button.font", new javax.swing.plaf.FontUIResource("Serif",Font.ITALIC,12));
		MainFrame mainframe = new MainFrame();
		Grid.initCalendar();
		mainframe.setEnable(false);
		Object [] obj = new Object [] {mainframe};
		Windows.createWindow("WindowLogin",obj);
		
       // 
            
	}
	
	public static void setUIFont (FontUIResource f){
	    //
	    // sets the default font for all Swing components.
	    // ex. 
	    //  setUIFont (new javax.swing.plaf.FontUIResource("Serif",Font.ITALIC,12));
	    //
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 
	
}
