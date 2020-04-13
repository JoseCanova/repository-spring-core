package org.nanotek.beans;

/**
 * This is a support class to make it easier for people to provide
 * BeanInfo classes.
 * <p>
 * It defaults to providing "noop" information, and can be selectively
 * overriden to provide more explicit information on chosen topics.
 * When the introspector sees the "noop" values, it will apply low
 * level introspection and design patterns to automatically analyze
 * the target bean.
 *
 * @since 1.1
 */
public class SimpleBeanInfo implements BeanInfo {

    /**
     * Deny knowledge about the class and customizer of the bean.
     * You can override this if you wish to provide explicit info.
     */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        return null;
    }

    /**
     * Deny knowledge of properties. You can override this
     * if you wish to provide explicit property info.
     */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return null;
    }

    /**
     * Deny knowledge of a default property. You can override this
     * if you wish to define a default property for the bean.
     */
    @Override
    public int getDefaultPropertyIndex() {
        return -1;
    }

    /**
     * Deny knowledge of event sets. You can override this
     * if you wish to provide explicit event set info.
     */
    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        return null;
    }

    /**
     * Deny knowledge of a default event. You can override this
     * if you wish to define a default event for the bean.
     */
    @Override
    public int getDefaultEventIndex() {
        return -1;
    }

    /**
     * Deny knowledge of methods. You can override this
     * if you wish to provide explicit method info.
     */
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return null;
    }

    /**
     * Claim there are no other relevant BeanInfo objects.  You
     * may override this if you want to (for example) return a
     * BeanInfo for a base class.
     */
    @Override
    public BeanInfo[] getAdditionalBeanInfo() {
        return null;
    }






}

