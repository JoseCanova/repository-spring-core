package org.nanotek.controller.response;

import java.util.Optional;

import org.nanotek.IdBase;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class IdBaseResponseBase <T extends IdBase<?,?>> extends ResponseBase<T> {
	
	public IdBaseResponseBase(HttpStatus status) {
		super(status);
	}

	public IdBaseResponseBase(T body, HttpStatus status) {
		super(body, status);
	}

	public IdBaseResponseBase(MultiValueMap<String, String> headers, HttpStatus status) {
		super(headers, status);
	}

	public IdBaseResponseBase(T body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, headers, status);
	}
	
	public static <B extends IdBase<?,?>>  IdBaseResponseBase<B> fromEntity(B t , HttpStatus s) {
		return new IdBaseResponseBase<B>(t, s);
	}
	
	public static <B extends IdBase<?,?>>  IdBaseResponseBase<B> fromEntityBase(Optional<B> t , HttpStatus s) {
		return new IdBaseResponseBase<B>(t.isPresent() ? t.get() : null, s);
	}
	
}
