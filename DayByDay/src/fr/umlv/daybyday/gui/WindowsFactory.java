package fr.umlv.daybyday.gui;

import javax.swing.JFrame;

/**
 * @author Emmanuelle EMOND
 * 
 * Created the  22 févr. 2005
 *
 * This class is a factory of windows.
 * 
 */
public interface WindowsFactory {

	/**
	 * This method init the window with a title and his size.
	 * 
	 * @param name the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public void initWindow(String name, int width, int height);
	

	/**
	 * This method center the windows to the screen.
	 * 
	 * @param f the window to center
	 */
	public  void centerWindow(JFrame f);

	
	/**
	 * This method create a window knowing is identfier. 
	 * It calls the appropriate mathod to construct the window.
	 * 
	 * @param identifier The identifier to cre	te the appropriate window 
	 * @param obj All the parameter neccessary to construct the window.
	 */
	public  void createWindow(String identifier, Object[] obj);
}
