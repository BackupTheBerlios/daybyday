/*
 * Créé le 5 févr. 2004
 */
package fr.umlv.daybyday.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import javax.swing.AbstractAction;


/**
 * @author Sukender
 * @version 0.1
 */
public class InstancesActions {
//	private static MainModel mainModel = null;
//	private static MainFrame mainFrame = null;

	public static void init(/*MainModel mainModel, MainFrame mainFrame*/) {
		//InstancesActions.mainFrame = mainFrame;
		//InstancesActions.mainModel = mainModel;
	}

	/**
	 * Returns the instance of the action desired, creating it if needed.
	 * @param strActionClass
	 * @return
	 */
	public static AbstractAction getAction(String strActionClass, Object [] refs) {
		//if (mainFrame==null || mainModel==null) throw new InternalError("You must initialize InstancesActions before calling this method.");
		Object o;
		// Existe ?
		if (listeActions.containsKey(strActionClass))
			return (AbstractAction)listeActions.get(strActionClass);

		// N'existe pas, on va essayer de la charger
		AbstractAction a = null;

		try {
			a = (AbstractAction) Class.forName("fr.umlv.daybyday.actions." + strActionClass).getConstructor(
					new Class[] { refs.getClass()}).newInstance(
							new Object[] { refs });
			//(null).newInstance(null);
		} catch (ClassNotFoundException e) {
			
			throw new InternalError("Request for a wrong class.");
		} catch (InstantiationException e) {
			throw new InternalError("Cannot instanciate action.");
		} catch (IllegalAccessException e) {
			throw new InternalError("Illegal access.");
		} catch (IllegalArgumentException e) {
			throw new InternalError("Illegal argument.");
		} catch (SecurityException e) {
			throw new InternalError("Security exception.");
		} catch (InvocationTargetException e) {
			throw new InternalError("Invocation target exception.");
		} catch (NoSuchMethodException e) {
			throw new InternalError("No such constructor.");
		}
		if (strActionClass.compareTo("ActionCopy") == 0 || 
			strActionClass.compareTo("ActionCut") == 0 ||
			strActionClass.compareTo("ActionPaste") == 0||
			strActionClass.compareTo("ActionAdmin") == 0)
		listeActions.put(strActionClass, a);
		return a;
	}

	private static HashMap listeActions = new HashMap();
	// Instances d'actions à récupérer par les autres classes
}
