package test.org.nanotek;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistBeginDate;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.beans.entity.DatableBase;
import org.nanotek.entities.MutableAliasIdEntity;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.MutableBeginDateDayEntity;
import org.nanotek.entities.MutableBeginDateMonthEntity;
import org.nanotek.entities.MutableBeginDateYearEntity;
import org.nanotek.entities.MutableDateEntity;
import org.nanotek.entities.immutables.AliasIdEntity;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.proxy.Mixin;
import org.springframework.cglib.reflect.MulticastDelegate;
import org.springframework.core.convert.Property;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.ClassUtils;

public class TestTypeDescriptor {

	public TestTypeDescriptor() {
		// TODO Auto-generated constructor stub
	}


	@Test
	public void testBeanGenerator() throws Exception {
		BeanGenerator beanGenerator = new BeanGenerator();
		beanGenerator.addProperty("value", String.class);
		Object myBean = beanGenerator.create();

		Method setter = myBean.getClass().getMethod("setValue", String.class);
		setter.invoke(myBean, "Hello cglib!");
		Method getter = myBean.getClass().getMethod("getValue");
		System.out.println(getter.invoke(myBean));
		//	  assertEquals("Hello cglib!", getter.invoke(myBean));
	}


	public interface MixinInterface extends MutableArtistIdEntity<Long> { 
		/* empty */
	}

	@Test
	public void testMixin() throws Exception {


		
		Mixin mixin = Mixin.create(new Class[]{MutableDateEntity.class,MutableBeginDateYearEntity.class,
				MutableBeginDateMonthEntity.class,
				MutableBeginDateDayEntity.class,
				MixinInterface.class}, new Object[]{new ArtistBean<>(), new Artist<>()});
		MixinInterface mixinDelegate = (MixinInterface) mixin;
		//	  assertEquals("first", mixinDelegate.first());
		//	  assertEquals("second", mixinDelegate.second());
		mixinDelegate.setArtistId(1000L);
		System.out.println("a");
	}

	public void testBeanGenerator2() throws Exception {
		AreaBean areaBean = new AreaBean();
		areaBean.setAreaId(1000l);
		areaBean.setComment("this is a comment");
		ModelMapper modelMapper = new ModelMapper();
		Area area = modelMapper.map(areaBean, Area.class);
		System.out.println("" + area.withUUID().toString());
	}

	public <K extends ArtistBeginDate<K> , S extends K> void   testMulticastDelegate() throws Exception {
		MulticastDelegate multicastDelegate = MulticastDelegate.create(
				MutableDateEntity.class);
		ArtistBean<?,?> first = new ArtistBean<>();
		ArtistBeginDate<?> second = new ArtistBeginDate<>();
		multicastDelegate = multicastDelegate.add(first);
		multicastDelegate = multicastDelegate.add(second);
		ArtistBeginDate<?> provider = ArtistBeginDate.class.cast(multicastDelegate);
		first.setBeginDateDay(2000);
		//		  assertEquals("Hello world!", first.getValue());
		//		  assertEquals("Hello world!", second.getValue());
		System.out.println("teste");
	}

	public static void main(String[] args) throws Exception {
		Class<?> clzz = MutableAliasIdEntity.class;
		Class<?> proxyClass = 
				ClassUtils.createCompositeInterface
				(new Class[] {MutableAliasIdEntity.class, AliasIdEntity.class}, MutableAliasIdEntity.class.getClassLoader());
		BeanInfo beanInfo = Introspector.getBeanInfo(proxyClass);
		PropertyDescriptor[] desc = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor d : desc) {

			System.out.println(d.getName());
			System.out.println(d.getReadMethod());
			System.out.println(d.getWriteMethod());
			Property p = new Property(d.getClass(), d.getReadMethod() , d.getWriteMethod());
			TypeDescriptor td = new TypeDescriptor(p);
			System.out.println(td.toString());
			;    	}
		new TestTypeDescriptor().testBeanGenerator2();
		//		for (Field f : clzz. {
		//			TypeDescriptor tp = new TypeDescriptor(f);
		//			System.out.println(tp.toString());
		//		}
	}

}
