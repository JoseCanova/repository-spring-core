package test.org.nanotek;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.nanotek.BaseInstantiationException;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.repository.jpa.ArtistRepository;
import org.nanotek.repository.jpa.projections.NameBaseProjection;

import com.google.common.base.Optional;

public class TestSomeShit {

	public static void main(String[] args) {
		AnnotatedType[] aT = ArtistRepository.class.getAnnotatedInterfaces();
		if (Optional.of(aT).isPresent()) {
			System.out.println("there are a anotated type");
			for (AnnotatedType annotatedType : aT) { 
				Type type = annotatedType.getType();
	            System.out.println("Type 1:" + type);
	            System.out.println(Arrays.toString(annotatedType.getDeclaredAnnotations()));	
	            Annotation[] annotations = annotatedType.getAnnotations();
	            System.out.println(Arrays.toString(annotations));

	            Annotation[] declaredAnnotations = annotatedType.getDeclaredAnnotations();
	            System.out.println(Arrays.toString(declaredAnnotations));
			}
		}
		main2();
		main3();
		main4();
		try {
			new ArtistCredit().newInstance();
		} catch (BaseInstantiationException e) {
			e.printStackTrace();
		}
	}

	
	 public static void main2(String... args) {
	        AnnotatedType[] ais = ArtistCredit.class.getAnnotatedInterfaces();
	        for (AnnotatedType annotatedType : ais) {
	            Type type = annotatedType.getType();
	            System.out.println("Type 2:" + type);

	            System.out.println("-- type annotations --");
	            Annotation[] annotations = annotatedType.getAnnotations();
	            System.out.println(Arrays.toString(annotations));
	            Annotation[] declaredAnnotations = annotatedType.getDeclaredAnnotations();
	            System.out.println(Arrays.toString(declaredAnnotations));
	        }
	    }

	 public static void main3() {
	        Optional<AnnotatedType> optAnnotatedType = Optional.fromNullable(ArtistRepository.class.getAnnotatedSuperclass());
	        if (optAnnotatedType.isPresent()) {
	        	AnnotatedType annotatedType = optAnnotatedType.get();
		        System.out.println("Type3: " + annotatedType.getType().getTypeName());
		        System.out.println("Annotations: " +
		                Arrays.toString(annotatedType.getAnnotations()));
		        System.out.println("Declared Annotations: " +
		                Arrays.toString(annotatedType.getDeclaredAnnotations()));
		        AnnotatedType annotatedOwnerType = annotatedType.getAnnotatedOwnerType();//Java 9
		        System.out.println("Annotated owner type: " + annotatedOwnerType);
		        System.out.println("AnnotatedType class: " + annotatedType.getClass().getName());
		        System.out.println("AnnotatedType class implementing interfaces: " +
		                Arrays.toString(annotatedType.getClass().getInterfaces()));
	        }else { 
	        	System.out.println("not a annotaed type super class " + ArtistRepository.class);
	        }
	    }
	 
	 public static void main4() {
	        Annotation[] annotations = NameBaseProjection.class.getAnnotations();
	        String s = Arrays.toString(annotations);
	        System.out.println(s);
	 }
}
