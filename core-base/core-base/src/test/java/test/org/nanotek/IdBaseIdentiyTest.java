package test.org.nanotek;

import java.util.Optional;

import org.nanotek.BaseEntity;
import org.nanotek.Id;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.csv.ArtistCreditBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.ArtistAlias;
import org.nanotek.beans.entity.SequenceLongBase;

public class IdBaseIdentiyTest {

	public void setUp() throws Exception {
	}

	public void test() {
	}
	
	public void testSequenceLongBaseId() {
		SequenceLongBase seq = new SequenceLongBase(); 
		Optional<SequenceLongBase> opt = BaseEntity.newBaseEntityInstance(SequenceLongBase.class , Long.TYPE);
		System.out.println(opt);
		Id<?> t = opt.get().asId();
		print(t.getId());
	}

	private void print(Object o) {
		System.out.println(o);
	}
	
	public void testOrderArtistArtistCredit() { 
		ArtistBean ab = new ArtistBean();
		ArtistCreditBean abc = new ArtistCreditBean(); 
		int result = ab.compareTo(abc);
		System.out.println(result);
	}

	public void testOrderArtistArea() { 
		ArtistBean ab = new ArtistBean();
		AreaBean abc = new AreaBean(); 
		int result = ab.compareTo(abc);
		System.out.println(result);
	}
	
	public void testOrderAreaArtistCredit() { 
		ArtistCreditBean ab = new ArtistCreditBean();
		AreaBean abc = new AreaBean(); 
		int result = ab.compareTo(abc);
		System.out.println(result);
	}

	public void testOrderAreaArtistAlias() { 
		ArtistAlias ab = new ArtistAlias();
		AreaBean abc = new AreaBean(); 
		int result = ab.compareTo(abc);
		System.out.println(result);
	}

	public void testOrderArtistAliasArea() { 
		ArtistAlias ab = new ArtistAlias();
		AreaBean abc = new AreaBean(); 
		abc.set("areaId", 123L);
		int result = abc.compareTo(ab);
		System.out.println(result);
	}
	
	public void testAreaArea() { 
		AreaBean ab = new AreaBean();
		AreaBean abc = new AreaBean(); 
		abc.set("areaId", 123L);
		ab.set("areaId", 234L);
		int result = abc.compareTo(ab);
		System.out.println(result);
	}

	public void testAreaAreaInverse() { 
		AreaBean abc = new AreaBean(); 
		AreaBean ab = new AreaBean();
		abc.set("areaId", 123L);
		ab.set("areaId", 234L);
		int result = ab.compareTo(abc);
		System.out.println(result);
	}

	
	public void testAreaAreaInverse123345() { 
		AreaBean abc = new AreaBean(); 
		AreaBean ab = new AreaBean();
		abc.set("areaId", 123L);
		ab.set("areaId", 345L);
		int result = ab.compareTo(abc);
		System.out.println(result);
	}
	
	public void testAreaAreaInverse345456() { 
		AreaBean abc = new AreaBean(); 
		AreaBean ab = new AreaBean();
		abc.set("areaId", 456L);
		ab.set("areaId", 345L);
		int result = ab.compareTo(abc);
		System.out.println(result);
	}
	
	public void testAreaAreaInverse345456Name() { 
		AreaBean abc = new AreaBean(); 
		AreaBean ab = new AreaBean();
		abc.set("areaId", 456L);
		ab.set("areaId", 345L);
		ab.set("name", "JOHN DOWN");
		int result = ab.compareTo(abc);
		System.out.println(result);
	}
	
	public void testAreaAreaInverse345456NameGID() { 
		AreaBean ab = new AreaBean();
		AreaBean abc = new AreaBean(); 
		ab.set("areaId", 456L);
		System.out.println(ab.md5Digest());
		abc.set("name", "JOHN DOWN");
		abc.set("gid", "JOHN DOWN");
		abc.set("areaId", 752L);
		int result = ab.compareTo(abc);
		System.out.println(result);
	}
	
	public void test1AreaAreaInverse345456NameGID() { 
		Area ab = new Area();
		Area abc = new Area(); 
		ab.setId(100000);
		ab.setAreaId(752L);
		abc.setName("JOHN DOWN");
		abc.setGid("JOHN DOWN");
		abc.setAreaId(456L);
		AreaType type = new AreaType(); 
		type.setTypeId(123L);
		type.setName("Type Name");
		abc.setType(type);  
		int result = ab.compareTo(abc);
		System.out.println(ab.md5Digest());
		System.out.println(abc.md5Digest());
		System.out.println(result);
	}
	
	public static void main(String[] args) { 
		IdBaseIdentiyTest t = new IdBaseIdentiyTest();
		t.testSequenceLongBaseId();
		t.testOrderArtistArtistCredit();
		t.testOrderArtistArea();
		t.testOrderAreaArtistCredit();
		t.testOrderAreaArtistAlias();
		t.testOrderArtistAliasArea();
		t.testAreaArea();
		t.testAreaAreaInverse();
		t.testAreaAreaInverse123345();
		t.testAreaAreaInverse345456();
		t.testAreaAreaInverse345456Name();
		t.testAreaAreaInverse345456NameGID();
		t.test1AreaAreaInverse345456NameGID();
	}
	
}
