/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.gui;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;


/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MnemonicMap {

	public static HashMap mnemomap = new HashMap();
	public static HashMap maskmap = new HashMap();
	public static HashMap tooltipmap = new HashMap();
	
	static {
		mnemomap.put("ActionOpenFormation",new Integer(KeyEvent.VK_F));
		maskmap.put("ActionOpenFormation",new Integer(ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK ));
		tooltipmap.put("ActionOpenFormation","Ouvrir une formation");
		
		mnemomap.put("ActionOpenTeacher",new Integer(KeyEvent.VK_E));
		maskmap.put("ActionOpenTeacher",new Integer(ActionEvent.CTRL_MASK  + ActionEvent.ALT_MASK));
		tooltipmap.put("ActionOpenTeacher","Ouvrir un enseignant");
		
		mnemomap.put("ActionOpenRoom",new Integer(KeyEvent.VK_S));
		maskmap.put("ActionOpenRoom",new Integer(ActionEvent.CTRL_MASK  + ActionEvent.ALT_MASK));
		tooltipmap.put("ActionOpenRoom","Ouvrir une salle");
		
		mnemomap.put("ActionOpenStuf",new Integer(KeyEvent.VK_M));
		maskmap.put("ActionOpenStuf",new Integer(ActionEvent.CTRL_MASK  + ActionEvent.ALT_MASK));
		tooltipmap.put("ActionOpenStuf","Ouvrir un matériel");
		
		mnemomap.put("ActionCreateFormation",new Integer(KeyEvent.VK_F));
		maskmap.put("ActionCreateFormation",new Integer(ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		tooltipmap.put("ActionCreateFormation","Créer une formation");
		
		mnemomap.put("ActionCreateTeacher",new Integer(KeyEvent.VK_E));
		maskmap.put("ActionCreateTeacher",new Integer(ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		tooltipmap.put("ActionCreateTeacher","Créer un enseignant");
		
		mnemomap.put("ActionCreateStuf",new Integer(KeyEvent.VK_M));
		maskmap.put("ActionCreateStuf",new Integer(ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		tooltipmap.put("ActionCreateStuf","Créer un matériel");
		
		mnemomap.put("ActionCreateRoom",new Integer(KeyEvent.VK_S));
		maskmap.put("ActionCreateRoom",new Integer(ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		tooltipmap.put("ActionCreateRoom","Créer une salle");
		
		mnemomap.put("ActionDelete",new Integer(KeyEvent.VK_DELETE));
		maskmap.put("ActionDelete",new Integer(0));
		tooltipmap.put("ActionDelete","Supprimer");
		
		mnemomap.put("ActionClose",new Integer(KeyEvent.VK_F));
		maskmap.put("ActionClose",new Integer(ActionEvent.CTRL_MASK));
		tooltipmap.put("ActionClose","Ouvrir une formation");
		
		mnemomap.put("ActionExport",new Integer(KeyEvent.VK_E));
		maskmap.put("ActionExport",new Integer(ActionEvent.CTRL_MASK));		
		tooltipmap.put("ActionExport","Exporter");
		
		mnemomap.put("ActionPrint",new Integer(KeyEvent.VK_P));
		maskmap.put("ActionPrint",new Integer(ActionEvent.CTRL_MASK));
		tooltipmap.put("ActionPrint","Imprimer");
		
		mnemomap.put("ActionUnlog",new Integer(KeyEvent.VK_F3));
		maskmap.put("ActionUnlog",new Integer(ActionEvent.ALT_MASK));
		tooltipmap.put("ActionUnlog","Se reloguer");

		
		mnemomap.put("ActionQuit",new Integer(KeyEvent.VK_F4));
		maskmap.put("ActionQuit",new Integer(ActionEvent.ALT_MASK));
		tooltipmap.put("ActionQuit","Quitter");
		
		mnemomap.put("ActionCancel",new Integer(KeyEvent.VK_Z));
		maskmap.put("ActionCancel",new Integer(ActionEvent.CTRL_MASK));		
		tooltipmap.put("ActionCancel","Annuler");
		
		mnemomap.put("ActionRedo",new Integer(KeyEvent.VK_Z));
		maskmap.put("ActionRedo",new Integer(ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		tooltipmap.put("ActionRedo","Refaire");
		
		mnemomap.put("ActionCut",new Integer(KeyEvent.VK_X));
		maskmap.put("ActionCut",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCut","Couper");
		
		mnemomap.put("ActionCopy",new Integer(KeyEvent.VK_C));
		maskmap.put("ActionCopy",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCopy","Copier");
		
		mnemomap.put("ActionPaste",new Integer(KeyEvent.VK_Z));
		maskmap.put("ActionPaste",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionPaste","Coller");
		
		mnemomap.put("ActionSelectAll",new Integer(KeyEvent.VK_A));
		maskmap.put("ActionSelectAll",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionSelectAll","Tout sélectionner");
		
		mnemomap.put("ActionUnselect",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionUnselect",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionUnselect","Désélectionner");
		
		mnemomap.put("ActionInvertSelect",new Integer(KeyEvent.VK_I));
		maskmap.put("ActionInvertSelect",new Integer(ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK ));	
		tooltipmap.put("ActionInvertSelect","Inverser la sélection");
		
		
		
		mnemomap.put("ActionToolBarShowHide",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionToolBarShowHide",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionToolBarShowHide","Masquer/Montrer la barre d'outil");
		
		mnemomap.put("ActionToolBarConfig",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionToolBarConfig",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionToolBarConfig","Configurer la barre d'outil");
		
		mnemomap.put("ActionGridDetailPerso",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionGridDetailPerso",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionGridDetailPerso","Détails des cours personnalisé");
		
		mnemomap.put("ActionGridDetailStudent",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionGridDetailStudent",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionGridDetailStudent","Détails des cours  pour étudiant");
		
		mnemomap.put("ActionGridDetailTeacher",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionGridDetailTeacher",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionGridDetailTeacher","Détails des cours  pour enseignant");
		
		mnemomap.put("ActionGridDetailCreate",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionGridDetailCreate",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionGridDetailCreate","Modifier détails des cours");
		
		mnemomap.put("ActionGridVisualWeek",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionGridVisualWeek",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionGridVisualWeek","Visualisation par semaine");		
		
		mnemomap.put("ActionGridVisualMultiWeek",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionGridVisualMultiWeek",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionGridVisualMultiWeek","Visualisation par plusieurs semaines");		
		
		mnemomap.put("ActionGridConfig",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionGridConfig",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionGridConfig","Configurer la grille");				
		
		mnemomap.put("ActionCourseAdd",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionCourseAdd",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCourseAdd","Ajouter un cours");		
		
		mnemomap.put("ActionCourseModify",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionCourseModify",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCourseModify","Modifier un cours");	

		mnemomap.put("ActionCourseMove",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionCourseMove",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCourseMove","Déplacer un cours");	
		
		mnemomap.put("ActionCourseCancel",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionCourseCancel",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCourseCancel","Annuler un cours");	
		
		mnemomap.put("ActionCourseUncancel",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionCourseUncancel",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCourseUncancel","Retablir un cours");	
		
		mnemomap.put("ActionDispoTeacher",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionDispoTeacher",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionDispoTeacher","Gérer les disponibilités d'un enseignant");	
		
		mnemomap.put("ActionDispoStuf",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionDispoStuf",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionDispoStuf","Gérer les disponibilités d'un matériel");	

		mnemomap.put("ActionDispoRoom",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionDispoRoom",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionDispoRoom","Gérer les disponibilités d'une salle");
		
		mnemomap.put("ActionDispoGeneral",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionDispoGeneral",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionDispoGeneral","Gérer les disponibilités général");		
		
		mnemomap.put("ActionAdmin",new Integer(KeyEvent.VK_F1));
		maskmap.put("ActionAdmin",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionAdmin","Administrer les paramètres généraux");
		
		mnemomap.put("ActionHelp",new Integer(KeyEvent.VK_F1));
		maskmap.put("ActionHelp",new Integer(0));	
		tooltipmap.put("ActionHelp","A propos");			
		// POP UP MENU ELEMENT
		
		mnemomap.put("ActionCreateFiliere",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionCreateFiliere",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCreateFiliere","Ajouter une nouvelle filière");
		
		mnemomap.put("ActionCreateSubject",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionCreateSubject",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCreateSubject","Ajouter une nouvelle matière");
		
		mnemomap.put("ActionModifyFormation",new Integer(KeyEvent.VK_D));
		maskmap.put("ActionModifyFormation",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionModifyFormation","Modifer");	
		
		// TABBEPANE POP UP MENU
		
		mnemomap.put("ActionCloseTabbePane",new Integer(KeyEvent.VK_F4));
		maskmap.put("ActionCloseTabbePane",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCloseTabbePane","Fermer la formation");	
		
		mnemomap.put("ActionCloseAllTabbePane",new Integer(KeyEvent.VK_F3));
		maskmap.put("ActionCloseAllTabbePane",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCloseAllTabbePane","Fermer toutes les formations");	
		
		mnemomap.put("ActionCloseInverseTabbePane",new Integer(KeyEvent.VK_F2));
		maskmap.put("ActionCloseInverseTabbePane",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionCloseInverseTabbePane","Fermer les autres formations");	

		mnemomap.put("ActionWeekBefore",new Integer(KeyEvent.VK_F2));
		maskmap.put("ActionWeekBefore",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionWeekBefore","Semaine précédente");	

		mnemomap.put("ActionWeekNext",new Integer(KeyEvent.VK_F2));
		maskmap.put("ActionWeekNext",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionWeekNext","Semaine suivante");	

		mnemomap.put("ActionFontBold",new Integer(KeyEvent.VK_G));
		maskmap.put("ActionFontBold",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionFontBold","Gras");

		mnemomap.put("ActionFontItalic",new Integer(KeyEvent.VK_I));
		maskmap.put("ActionFontItalic",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionFontItalic","Italic");
		
		mnemomap.put("ActionFontUnderline",new Integer(KeyEvent.VK_U));
		maskmap.put("ActionFontUnderline",new Integer(ActionEvent.CTRL_MASK));	
		tooltipmap.put("ActionFontUnderline","Souligné");
		
		
	}
	public static int getMnemonic(String mnemo){
		return ((Integer)mnemomap.get(mnemo)).intValue();
	}
	
	public static int getMask(String mnemo){
		return ((Integer)maskmap.get(mnemo)).intValue();
	}
	public static String getToolTip(String mnemo){
		return (String)tooltipmap.get(mnemo);
	}
	
}
