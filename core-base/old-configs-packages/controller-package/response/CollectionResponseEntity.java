package org.nanotek.controller.response;

import java.util.Collection;

import org.nanotek.Base;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class CollectionResponseEntity<T extends Collection<K>, K extends Base<?>> extends ResponseEntity<T> {

	public CollectionResponseEntity(HttpStatus status) {
		super(status);
	}

	public CollectionResponseEntity(T body, HttpStatus status) {
		super(body, status);
	}

	public CollectionResponseEntity(MultiValueMap<String, String> headers, HttpStatus status) {
		super(headers, status);
	}

	public CollectionResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, headers, status);
	}

	public static <T extends Collection<K>, K extends Base<?>> CollectionResponseEntity<T,K> fromCollection(T e, HttpStatus status){ 
		return new CollectionResponseEntity<>(e,status);
	}
	
}
