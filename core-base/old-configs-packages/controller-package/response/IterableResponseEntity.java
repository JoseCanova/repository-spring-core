package org.nanotek.controller.response;

import org.nanotek.Base;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class IterableResponseEntity<T extends Iterable<K> , K extends Base<?>> extends ResponseEntity<T> {

	public IterableResponseEntity(HttpStatus status) {
		super(status);
	}

	public IterableResponseEntity(T body, HttpStatus status) {
		super(body, status);
	}

	public IterableResponseEntity(MultiValueMap<String, String> headers, HttpStatus status) {
		super(headers, status);
	}

	public IterableResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, headers, status);
	}

	public static <T extends Iterable<K>, K extends Base<?>> IterableResponseEntity<T,K> fromIterable(T e, HttpStatus status){ 
		return new IterableResponseEntity<>(e,status);
	}
}
