/*
 * Created on 20 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * @author Stéphane MOREAU
 */
public class Images {
	public static final int SMALL = 16;
	public static final int BIG = 32;
	public static final String[] typesImage = {"formation","filiere","matiere"};

	private static JLabel label = new JLabel();
	private static HashMap images = new HashMap();
	
	public static ImageIcon getImageIcon(String name) {
		if (images.containsKey(name))
			return (ImageIcon)images.get(name);
			
		if (!name.matches(".*\\..*"))
			name += ".png";

		ImageIcon image = new ImageIcon(label.getClass().getResource("/images/"+name));
		images.put(name,image);
		return image;
	}
	
	public static ImageIcon getImageIcon(int type, int size) {
		return getImageIcon(typesImage[type]+"_"+size+"X"+size);
	}
	
	public static ImageIcon getImageIcon(File file) {
		ImageIcon image = new ImageIcon(file.getPath());
		if (image.getImageLoadStatus() == MediaTracker.COMPLETE) {
			int width = image.getIconWidth();
			int height = image.getIconHeight();
			if (width > height) {
				height = (BIG * height) / width;
				width = BIG;
			} else if (width < height) {
				width = (BIG * width) / height;
				height = BIG;
			} else {
				width = BIG;
				height = BIG;
			}
			if (width == 0) {
				++width;
			}
			if (height == 0) {
				++height;
			}
			return new ImageIcon(image.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		}
		return null;
	}
	
	public static boolean isImageIcon(File file) {
		ImageIcon image = new ImageIcon(file.getPath());
		if (image.getImageLoadStatus() == MediaTracker.COMPLETE) {
			return true;
		}
		return false;
	}
	
	public static Image getImage(String name) {
		return getImageIcon(name).getImage();
	}
}

