package org.nanotek;

/**
 * The terminator interface. 
 * R Resource 
 * C Cause 
 * S Anything related to status related execution.
 * 
 * @author josecanovamauger
 *
 * @param <R> 
 * @param <C>
 * @param <S>
 */

public interface Terminator<R,C,S>
{

 public S terminate (R resource , C cause);
}
