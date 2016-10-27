package com.jelf.service;

public interface AppService {

	<T> boolean isPropertyValueRepeated(Class<T> target, Long id,
			String property, Object value);

}