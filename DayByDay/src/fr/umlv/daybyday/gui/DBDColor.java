package fr.umlv.daybyday.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Emmanuelle Emond
 * Créé le 19 févr. 2005
 * 
 * This class permit to get the code color knowing the name of the color.
 * Colors are put in a hashmap. The key is the name of the color and the
 * value is the code.
 * 
 */
public class DBDColor {

	/**
	 * The hashmap witch contains the name's color 
	 * and their codes. 
	 */
	private static HashMap mapColor = new HashMap();
	
	/**
	 * The list witch associate the name 
	 * of the color with a number.
	 */
	private static ArrayList color = new ArrayList();
	
	
	static {
		mapColor.put("WHITE_GRAY", "#D3D0C9");
		mapColor.put("WHITE", "#FFFFFF");
		mapColor.put("BLUE_GRAY", "#B6BDD2");
		mapColor.put("PASTEL_GREEN", "#C2FF99");
		mapColor.put("PASTEL_PURPLE", "#D2B1E0");
		mapColor.put("PASTEL_RED", "#FF9999");
		mapColor.put("PASTEL_BLUE", "#AF99FF");
		mapColor.put("SAFRAN", "#FFCC67");
		mapColor.put("DARK_GRAY", "#808080");
		mapColor.put("DARK", "#000000");
		mapColor.put("NIGHT_BLUE", "#0A246A");
	}

	
	/**
	 * Getter of the code of the color.
	 * This method search the code color in the 
	 * hashmap witch is the key and return his code.
	 * 
	 * @param s the name of the color
	 * @return the code of the color
	 */
	public static Color getColor(String s) {
		return Color.decode((String) mapColor.get(s));
	}

	private static void associateColorNumber(){
		color.add("WHITE_GRAY");
		color.add("WHITE");
		color.add("BLUE_GRAY");
		color.add("PASTEL_GREEN");
		color.add("PASTEL_PURPLE");
		color.add("PASTEL_RED");
		color.add("PASTEL_BLUE");
		color.add("SAFRAN");
		color.add("DARK_GRAY");
		color.add("DARK");
		color.add("NIGHT_BLUE");		
	}


	/**
	 * Getter of map color to refernece the color in the map
	 * 
	 * @return the list of color
	 */
	public static ArrayList getListColor() {
		associateColorNumber();
		return color;
	}
}