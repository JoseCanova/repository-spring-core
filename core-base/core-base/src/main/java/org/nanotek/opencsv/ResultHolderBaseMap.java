package org.nanotek.opencsv;

import java.util.Optional;
import java.util.Set;

import org.nanotek.AnyBase;
import org.nanotek.BaseException;
import org.nanotek.WrappedEntityBase;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.beans.csv.CsvBaseBean;
import org.nanotek.collections.BaseMap;

/**
 * this bean will god to spring. it holds the reference of the configuration of the beans 
 * to load the CSV line.
 *
 * @param <K>
 * @param <ID>
 * @param <I>
 */
public class ResultHolderBaseMap<T  extends AnyBase<T,C> , C extends AnyBase<T,I> ,  I extends CsvBaseBean<?>> 
extends BaseMap<T , C, I> implements ColumnMapHolder<I> {

	private static final long serialVersionUID = -1565015302783229070L;

	private String[] columnMapping;

	private Class<K> clazz;
	
	public ResultHolderBaseMap(K immutable) {
		super(immutable);
	}
	
	public ResultHolderBaseMap(K immutable,Class<K> clazz) {
		super(immutable);
		this.clazz = clazz;
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
		Optional<Set<B>> x = size() == 0 ? Optional.empty():Optional.of(keySet());
		x.ifPresent(s -> {
			columnMapping  = new String[size()];
			s.stream().forEach(k ->{
				B position = get(AnyBase.of(k).asBase());
				assert (position !=null);
				columnMapping[position.get()] = k;
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
