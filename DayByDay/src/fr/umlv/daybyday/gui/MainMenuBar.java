/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;

/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fr.umlv.daybyday.actions.InstancesActions;



/**
 * 
 * @author Stéphane MOREAU
 * @author Sukender
 */
public class MainMenuBar {
	private JMenuBar menuBar;
	private JMenuItem[] view;
	private MainFrame mainFrame;
	private final JMenuItem filter;

	/**
	 * Constructs the <tt>MainMenuBar</tt> of the main window of the application.
	 */
	public MainMenuBar(Object [] refs) {
//		this.mainFrame = mainFrame;
		menuBar = new JMenuBar();

//		model.addGXpLoRListener(new MenuBarListener());

		//menu File
		JMenu menu = new JMenu("Fichier");
		menu.setIcon(Images.getImageIcon("dos_16X16"));
		JMenuItem item = new JMenuItem(InstancesActions.getAction("ActionPrint",refs));
		menu.add(item);
		//		JMenuItem item =
		//			new JMenuItem(InstancesActions.getAction("ActionExecute"));
		//menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionSearch",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionShowProperties",refs));
		menu.add(item);
		item = new JMenuItem("Paramétrer...");
		//menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionQuit",refs));
		menu.add(item);

		menuBar.add(menu);

		//menu Edit
		menu = new JMenu("Editer");
		menu.setIcon(Images.getImageIcon("edit"));
		item = new JMenuItem(InstancesActions.getAction("ActionUndo",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCut",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCopy",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionPaste",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionModifyPermission",refs));
		menu.add(item);
		menu.add(new JPopupMenu.Separator());
		item = new JMenuItem(InstancesActions.getAction("ActionPutGarbage",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionRestorGarbage",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionEmptyGarbage",refs));
		menu.add(item);
		menu.add(new JPopupMenu.Separator());
		item = new JMenuItem(InstancesActions.getAction("ActionExecute",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionMoveTo",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCopyTo",refs));
		menu.add(item);
		//item = new JMenuItem("Coller le raccourci"); item.setEnabled(false);
		//menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionSelectAll",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionInvertSelection",refs));
		menu.add(item);

		menuBar.add(menu);

		//menu View
		menu = new JMenu("Affichage");
		menu.setIcon(Images.getImageIcon("aff"));

		item = new JMenuItem(InstancesActions.getAction("ActionRefreshOne",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionRefreshAll",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionRemovePerspective",refs));
		menu.add(item);
		filter = new JMenuItem(InstancesActions.getAction("ActionFilter",refs));
		menu.add(filter);
/*
		view = new JMenuItem[4];
		ButtonGroup group = new ButtonGroup();
		JMenu sousMenu = new JMenu("Mode d'affichage");
		view[Perspective.LIST] = new JRadioButtonMenuItem("Liste");
		view[Perspective.LIST].addActionListener(new ActionVisualListPerspective(model, mainFrame));
		sousMenu.add(view[Perspective.LIST]);
		view[Perspective.LIST].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view[Perspective.LIST].isSelected())
					filter.setEnabled(false);
			}

		});
		group.add(view[Perspective.LIST]);
		view[Perspective.DETAIL] = new JRadioButtonMenuItem("Détails");
		view[Perspective.DETAIL].addActionListener(new ActionVisualDetailPerspective(model, mainFrame));
		view[Perspective.DETAIL].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view[Perspective.DETAIL].isSelected())
					filter.setEnabled(true);
			}

		});
		sousMenu.add(view[Perspective.DETAIL]);
		group.add(view[Perspective.DETAIL]);
		view[Perspective.ICON] = new JRadioButtonMenuItem("Icônes");
		view[Perspective.ICON].addActionListener(new ActionVisualIconPerspective(model, mainFrame));
		view[Perspective.ICON].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view[Perspective.ICON].isSelected())
					filter.setEnabled(false);
			}

		});
		sousMenu.add(view[Perspective.ICON]);
		group.add(view[Perspective.ICON]);
		view[Perspective.MINIATURE] = new JRadioButtonMenuItem("Miniatures");
		view[Perspective.MINIATURE].addActionListener(new ActionVisualMiniaturePerspective(model, mainFrame));
		view[Perspective.MINIATURE].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view[Perspective.MINIATURE].isSelected())
					filter.setEnabled(false);
			}

		});
		sousMenu.add(view[Perspective.MINIATURE]);
		group.add(view[Perspective.MINIATURE]);
		view[Perspective.DEFAULT].setSelected(true);
		menu.add(sousMenu);*/
		// fin du todo

		menuBar.add(menu);

		//menu Utils
		menu = new JMenu("Outils");
		menu.setIcon(Images.getImageIcon("par"));
		item = new JMenuItem(InstancesActions.getAction("ActionCreateFileAssoc",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCreateEmptyElement",refs));
		menu.add(item);
		//item = new JMenuItem("Créer à partir d'un template"); item.setEnabled(false);
		//menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionRename",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionDestroy",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCrush",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCompare",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionConvertASCII",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCrypt",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionDecrypt",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionCompress",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionDecompress",refs));
		menu.add(item);
		item = new JMenuItem(InstancesActions.getAction("ActionPutGarbage",refs));
		menuBar.add(menu);

		//menu Help
		menu = new JMenu("Aide");
		menu.setIcon(Images.getImageIcon("unk_16X16"));
		item = new JMenuItem("Aide complète");
		item.setEnabled(false);
		menu.add(item);
		item = new JMenuItem("Aide sur un élément");
		item.setEnabled(false);
		menu.add(item);
/*		item = new JMenuItem("A propos de " + Dico.programName);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Object[] params = new Object[1];

				params[0] = mainFrame.getFrame();

				try {
					final AboutWindow pw = (AboutWindow)DialogWindows.createWindow("fr.umlv.gxplor.graphics.window.AboutWindow", params, mainFrame);
				} catch (UnknownedWindowException e1) {
					e1.printStackTrace();
				}
			}
		});
		menu.add(item);
*/
		menuBar.add(menu);
	}

	/**
	 * Returns the menu bar.
	 * @return the menu bar.
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}
/*
	private class MenuBarListener extends DefaultGXpLoRListener {
		public void selectPerspective(GXpLoREvent e) {
			int n = mainFrame.getPerspectives().getFocusedPerspective().getViewType();
			view[n].setSelected(true);
			if (n == Perspective.DETAIL) {
				filter.setEnabled(true);
			} else {
				filter.setEnabled(false);
			}
		}
	}*/
}

