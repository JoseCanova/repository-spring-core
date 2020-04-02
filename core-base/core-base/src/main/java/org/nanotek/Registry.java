package org.nanotek;

import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorManager;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextMembershipListener;
import java.beans.beancontext.BeanContextSupport;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import org.nanotek.beans.sun.editors.UUIDEditor;

/**
 * Registry class for JavaBeans/Application Support. 
 * will use an application context to keep with spring on managing application bean services.(in future)
 * 
 * @author jose
 *
 */
public class Registry<K extends Base<?>> implements Base<K> {
 
	private static final long serialVersionUID = -7933039363471741747L;

	private BeanContextSupport beanContextSupport;
	
	public Registry() {
	}

	public Registry(BeanContextSupport beanContextSupport2) {
		this.beanContextSupport = beanContextSupport2;
		PropertyEditorManager.registerEditor(UUID.class, UUIDEditor.class);
	}

	public Registry<K> registar() {
		return Registry.class.cast(ThreadLocal.withInitial(this).get());
	}
	
	public BeanContextSupport getBeanContextSupport() {
		return beanContextSupport;
	}

	public boolean add(Base<?> arg0) {
		return beanContextSupport.add(arg0);
	}

	public boolean addAll(Collection<?> c) {
		return beanContextSupport.addAll(c);
	}

	public void addBeanContextMembershipListener(BeanContextMembershipListener bcml) {
		beanContextSupport.addBeanContextMembershipListener(bcml);
	}

	public void addPropertyChangeListener(String name, PropertyChangeListener pcl) {
		beanContextSupport.addPropertyChangeListener(name, pcl);
	}

	public void addVetoableChangeListener(String name, VetoableChangeListener vcl) {
		beanContextSupport.addVetoableChangeListener(name, vcl);
	}

	public void firePropertyChange(String name, Object oldValue, Object newValue) {
		beanContextSupport.firePropertyChange(name, oldValue, newValue);
	}

	public void fireVetoableChange(String name, Object oldValue, Object newValue) throws PropertyVetoException {
		beanContextSupport.fireVetoableChange(name, oldValue, newValue);
	}

	public void forEach(Consumer<?> arg0) {
		beanContextSupport.forEach(arg0);
	}

	public BeanContext getBeanContext() {
		return beanContextSupport.getBeanContext();
	}

	public BeanContext getBeanContextPeer() {
		return beanContextSupport.getBeanContextPeer();
	}

	public Locale getLocale() {
		return beanContextSupport.getLocale();
	}

	public URL getResource(String arg0, BeanContextChild arg1) {
		return beanContextSupport.getResource(arg0, arg1);
	}

	public InputStream getResourceAsStream(String arg0, BeanContextChild arg1) {
		return beanContextSupport.getResourceAsStream(arg0, arg1);
	}

	public Iterator<?> iterator() {
		return beanContextSupport.iterator();
	}

	public boolean remove(Object targetChild) {
		return beanContextSupport.remove(targetChild);
	}

	public boolean removeIf(Predicate<?> filter) {
		return beanContextSupport.removeIf(filter);
	}

	public Object[] toArray(IntFunction<?> generator) {
		return beanContextSupport.toArray(generator);
	}

	public Object[] toArray(Object[] arry) {
		return beanContextSupport.toArray(arry);
	}

	public final void writeChildren(ObjectOutputStream arg0) throws IOException {
		beanContextSupport.writeChildren(arg0);
	}
	
}
