package org.nanotek.controller;

@FunctionalInterface
public interface  BaseController<K, I> extends Controller<I>{
   I get(K id) ;
}
