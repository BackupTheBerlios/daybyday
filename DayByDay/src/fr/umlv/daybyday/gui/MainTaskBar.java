/*
 * Created on 27 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;


/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MainTaskBar extends JLayeredPane{

	private JButton [] labels;
	private int width;
	
	public MainTaskBar (String [] labelvalue){
		labels = new JButton[labelvalue.length];
		setBorder(BorderFactory.createBevelBorder(1));
		setSize(new Dimension(800,25));
		setPreferredSize(new Dimension(800,25));
		setMinimumSize(new Dimension(800,25));
		setMaximumSize(new Dimension(800,25));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
		width = 0;
		for (int i = 0; i < labelvalue.length; ++i){
			labels[i] =new JButton(labelvalue[i]);
			labels[i].setEnabled(false);
			labels[i].setMargin(new Insets(0,0,0,0));
			if (i == labelvalue.length - 1)labels[i].setBounds(getSize().width - 4 + i * 105,3 ,100,20);
			else labels[i].setBounds(4 + i * 105,3 ,100,20);
			add(labels[i]);
		}		
	}
	
	public void setPositionLabels(int width){
		String text = labels[labels.length - 1].getText();
		this.width = width;
		labels[labels.length - 1].setBounds(width - (9 + 8*text.length()),3 ,8*text.length(),20);
	}
	
	public void setTextofLabel(int index, String text){
		if (index >= 0 && index < labels.length){
			labels[index].setText(text);
			if (index == labels.length - 1)
			labels[index].setBounds(width - (9 + 8*text.length()),3 ,8*text.length(),20);
		}
	}
}
