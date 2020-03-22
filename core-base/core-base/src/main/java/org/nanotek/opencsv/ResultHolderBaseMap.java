package org.nanotek.opencsv;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import org.nanotek.Base;
import org.nanotek.BaseException;
import org.nanotek.WrappedEntityBase;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.beans.entity.Area;

import com.sun.xml.bind.v2.model.core.ID;

/**
 * this bean will god to spring. it holds the reference of the configuration of the beans 
 * to load the CSV line.
 *
 * @param <K>
 * @param <ID>
 * @param <I>
 */
public class ResultHolderBaseMap<I extends BaseBean<?,?>, K extends WrappedEntityBase<I>> 
extends HolderBaseMap<I> implements ColumnMapHolder<I> {

	private static final long serialVersionUID = -1565015302783229070L;

	private String[] columnMapping;

	private Class<I> clazz;
	
	private Class<K> wrapperClass;
	

	public ResultHolderBaseMap(I immutable) {
		super(immutable);
	}
	
	public ResultHolderBaseMap(I immutable,Class<I> clazz) {
		super(immutable);
		this.clazz = clazz;
	}

	public ResultHolderBaseMap(Class<I> clazz,Optional<I> wrappedBasedClass) {
		super(wrappedBasedClass.get());
		this.clazz = clazz;
		this.wrapperClass = wrapperClass;
	}
	
	public ResultHolderBaseMap() {
		super(null);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		verifyConfiguration();
	}
	
	@Override
	public Optional<I> verifyConfiguration() {
		Optional<Set<String>> x = size() == 0 ? Optional.empty():Optional.of(keySet());
		x.ifPresent(s -> {
			columnMapping  = new String[size()];
			s.stream().forEach(k ->{
				Integer position = get(k);
				assert (position !=null && position <= columnMapping.length);
				columnMapping[get(k)] = k;
			});
		});
		return BaseBean.newBaseBeanInstance(Optional.ofNullable(clazz).orElseThrow(BaseException::new));
	}

	public Optional<String[]> getColumnMapping() {
		return Optional.ofNullable(columnMapping);
	}

	public static void main(String[] args) throws Exception {
//		Optional<WrappedBaseClass> wbc = Base.newInstance(WrappedBaseClass.class, new Serializable[] {AreaBean.class}, Class.class);
//		ResultHolderBaseMap map = new ResultHolderBaseMap(Area.class,wbc);
//		map.afterPropertiesSet();
	}
	
}
