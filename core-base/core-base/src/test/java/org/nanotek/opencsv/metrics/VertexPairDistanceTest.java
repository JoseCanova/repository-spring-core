package org.nanotek.opencsv.metrics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.ArtistCredit;

public class VertexPairDistanceTest {

	
	private Set< VertexDistance<Class<?>,Class<?>>> vertexDistanceSet = new HashSet<>();


	@BeforeEach
	void setUp() throws Exception {
		
	VertexPair<Class<?>,Class<?>> vp = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
	VertexPair<Class<?>,Class<?>> vp1 = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
	VertexPair<Class<?>,Class<?>> vp2 = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);

	VertexDistance<Class<?>,Class<?>> vd = new VertexDistance<Class<?>,Class<?>>(Double.valueOf(3.0) , vp);
	VertexDistance<Class<?>,Class<?>> vd1 = new VertexDistance<Class<?>,Class<?>>(Double.valueOf(3.0) , vp1);
	VertexDistance<Class<?>,Class<?>> vd2 = new VertexDistance<Class<?>,Class<?>>(Double.valueOf(3.0) , vp2);

	vertexDistanceSet.add(vd);
	vertexDistanceSet.add(vd1);
	vertexDistanceSet.add(vd2);
	}
	
	@Test
	void test() {
		assertTrue(vertexDistanceSet.size()==1);
	}
	
	@Test
	void testInequalityOnDistance() {
		
		VertexPair<Class<?>,Class<?>> vp = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
		VertexPair<Class<?>,Class<?>> vp1 = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
		
		VertexDistance<Class<?>,Class<?>> vd = new VertexDistance<Class<?>,Class<?>>(Double.valueOf(3.0) , vp);
		VertexDistance<Class<?>,Class<?>> vd1 = new VertexDistance<Class<?>,Class<?>>(Double.valueOf(3.2) , vp1);
		
		assertFalse(vd.equals(vd1));
		
	}
	
	

}
