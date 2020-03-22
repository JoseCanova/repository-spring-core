package org.nanotek.controller.response;

import java.util.Optional;

import org.nanotek.Base;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseBase<T extends Base<?>> extends ResponseEntity<T> {

	public ResponseBase(HttpStatus status) {
		super(status);
	}

	public ResponseBase(T body, HttpStatus status) {
		super(body, status);
	}

	public ResponseBase(MultiValueMap<String, String> headers, HttpStatus status) {
		super(headers, status);
	}

	public ResponseBase(T body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, headers, status);
	}
	
	public ResponseBase(Optional<T> body, HttpStatus status) {
		this(body.get(), status);
	}

	public static <B extends Base<?>>  ResponseBase<B> fromEntity(B t , HttpStatus s) {
		return new ResponseBase<B>(t, s);
	}
	
	public static  <B extends Base<?>> ResponseBase<B>  fromEntity(Optional<B> t , HttpStatus s) {
		return new ResponseBase<B>(t, s);
	}
}
