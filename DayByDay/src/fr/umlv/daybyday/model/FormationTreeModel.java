/*
 * Created on 20 févr. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.umlv.daybyday.model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;


/**
 * @author Marc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FormationTreeModel extends DefaultTreeModel implements TreeModel {
	
	FormationElement form;
	
	
	public FormationTreeModel (FormationElement form){
		super(form);
		this.form =form;
	}
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
/*	public Object getRoot() {
		// TODO Auto-generated method stub
		return form;
	}
*/
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
/*	public int getChildCount(Object arg0) {
		System.out.println((FormationElement) arg0 + " " + ((FormationElement) arg0).getElementNombre());
		if (arg0 instanceof FormationElement)
			return ((FormationElement) arg0).getElementNombre();
	//	if (arg0 instanceof Filiere)
		//	return ((Filiere)arg0).getElementNombre();
		
		return 0;
		
	}
*/
	/**
	 * fdgfdgdgfdgfdgfd
	 * @param ard
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
/*	public boolean isLeaf(Object arg0) {
		if (arg0 instanceof FormationElement)
			return (((FormationElement) arg0).getElementNombre() == 0);
	//	if (arg0 instanceof Filiere)
		//	return (((Filiere)arg0).getElementNombre() == 0);
		return true;
	}
*/
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
/*	public void addTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub
		
	}
*/
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
/*	public void removeTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub
		
	}
*/
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
/*	public Object getChild(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		if (arg0 instanceof FormationElement)
		return ((FormationElement)arg0).getIndex(arg1);
		//if (arg0 instanceof Filiere)
		//	return ((Filiere)arg0).getIndex(arg1);
		return null;
	}
*/
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
/*	public int getIndexOfChild(Object arg0, Object arg1) {
		if (arg0 instanceof FormationElement)
			return ((FormationElement)arg0).getIndex(arg1);
		//	if (arg0 instanceof Filiere)
		//		return ((Filiere)arg0).getIndex(arg1);
		return -1;
	}
*/
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
/*	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
*/
}
