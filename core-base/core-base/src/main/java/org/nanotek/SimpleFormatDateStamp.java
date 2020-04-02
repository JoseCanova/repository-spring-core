package org.nanotek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQueries;
import java.util.Optional;
import java.util.function.Supplier;


public interface SimpleFormatDateStamp extends Supplier<SimpleFormatDateStamp>, MutableDateStamp<String> , DateStamp<LocalDate> {
	
    
	@Override
	default LocalDate getStamp() {
		SimpleFormatDateStamp t = StampHolderFormatter.stampHolder.get();
		return t.getStamp();
	}

	//TODO: put a pattern matching for the current format
	@Override
	 default void setStamp(String t) {
		if (StampHolderFormatter.stampHolder == null) {
				StampHolderFormatter.stampHolder = ThreadLocal.withInitial(getInstance(t));
		}else {
			StampHolderFormatter.stampHolder.set(getInstance(t));
		}
		System.out.println(this.get());
	}
	
	static SimpleFormatDateStamp newStamp(String t ){
		return  getInstance(t);
	}
	
	default SimpleFormatDateStamp get() {
		return this;
	};
	
	static <T>  SimpleFormatDateStamp getInstance(String t) {
		
		
		return new SimpleFormatDateStamp() {
            
			private String stampHolder = t;
			
			@Override
			public LocalDate getStamp() {
				try {
				return stampHolder !=null? StampHolderFormatter.formatter.parse(stampHolder).query(TemporalQueries.localDate()) : null;
				}catch(Exception  ex) { 
					ex.printStackTrace();
				}
				return null;
			}

			//TODO: put a pattern matching for the current format
			@Override
			public void setStamp(String t) {
				this.stampHolder = t;
			}

			@Override
			public SimpleFormatDateStamp get() {
				return SimpleFormatDateStamp.getInstance(stampHolder);
			}
			
			
		};
	}
	
	static class StampHolderFormatter{
		private static DateTimeFormatter formatter =  Optional.ofNullable("").map(m->{
			DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
			DateTimeFormatter zoneFormatter = DateTimeFormatter.ofPattern("X");
			DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
													.append(yearFormatter)
													.appendLiteral(' ')
													.append(timeFormatter)
													.appendFraction(ChronoField.NANO_OF_SECOND, 6, 6, true)
													.append(zoneFormatter);
			return builder.toFormatter();
		}).get(); 
		static ThreadLocal<SimpleFormatDateStamp>  stampHolder;
	}
	
	
}
