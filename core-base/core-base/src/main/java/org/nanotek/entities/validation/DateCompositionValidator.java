package org.nanotek.entities.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.nanotek.BaseEntity;
import org.nanotek.proxy.map.bean.ForwardMapBean;

import static org.nanotek.entities.validation.DateCompositionValidator.FieldEnum.*;

import java.util.Optional;

public class DateCompositionValidator 
implements ConstraintValidator<DateComposition,BaseEntity<?,?>>{

	enum FieldEnum { 
		BEGIN_YEAR("beginYear"),
		BEGIN_MONTH("beginMonth"),
		BEGIN_DAY("beginDay"),
		END_YEAR("endYear"),
		END_MONTH("endMonth"),
		END_DAY("endDay");
		
		String value; 
		
		private FieldEnum(String val) { 
			this.value = val;
		}
		
		public String getValue() { 
			return this.value;
		}
	}
	
	public DateCompositionValidator() {
	}

	@Override
	public boolean isValid(BaseEntity<?, ?> value, ConstraintValidatorContext context) {
		ForwardMapBean<?> dm = new ForwardMapBean<>(value.getClass(),value);
		if (hasField(dm , BEGIN_YEAR) && hasField(dm , BEGIN_YEAR) && hasField(dm , BEGIN_YEAR)){ 
			Integer year = value(dm,BEGIN_YEAR);
			if(year > 0) { 
				Integer month = value(dm,BEGIN_MONTH);
				if (month == 0) {
					dm.write(BEGIN_MONTH.getValue(), 1);
				}
				Integer day = value (dm,BEGIN_DAY);
				if(day == 0) {
					dm.write(BEGIN_DAY.getValue(), 1);
				}
				return true;
			}
			return false;
		}else if (hasField(dm , END_YEAR) && hasField(dm , END_YEAR) && hasField(dm , END_YEAR)){ 
			Integer year = value(dm,END_YEAR);
			if(year > 0) { 
				Integer month = value(dm,END_MONTH);
				if (month == 0) {
					dm.write(END_MONTH.getValue(), 1);
				}
				Integer day = value (dm,END_DAY);
				if(day == 0) {
					dm.write(END_DAY.getValue(), 1);
				}
				return true;
			}
			return false;
		}
		return false;
	}

	private Integer value(ForwardMapBean<?> dm,  FieldEnum type) {
		Optional<Integer> opt = dm.read(type.getValue());
		return opt.orElse(0);
	}
	
	private boolean hasField(ForwardMapBean<?> dm, FieldEnum type) {
		return dm.toWhere(type.getValue())!=null && Integer.class.equals(dm.toWhere(type.getValue()).getPropertyType());
	}


}
