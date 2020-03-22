package org.nanotek;

import java.util.Optional;

public interface MaskBase {

	static String DATEMASK(Integer year , Integer month , Integer day) {
		StringBuilder sb = new StringBuilder();
		String resultYear = Optional.ofNullable(year).map(y -> String.valueOf(y)).orElse("00");
		String resultMont = Optional.ofNullable(month).map(m -> String.valueOf(m)).orElse("01");
		String resultDay = Optional.ofNullable(day).map(m -> String.valueOf(m)).orElse("01");
		return sb.append(resultYear).append('/').append(resultMont).append('/').append(resultDay).toString();
	}
}
