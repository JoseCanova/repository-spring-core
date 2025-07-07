package org.nanotek.opencsv.metrics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.beans.entity.Release;

class VertexPairIdentityTest {

	private Set< VertexPair<Class<?>,Class<?>>> vertexPairSet = new HashSet<>();
	
	
	@BeforeEach
	void setUp() throws Exception {
	
	VertexPair<Class<?>,Class<?>> vp = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
	VertexPair<Class<?>,Class<?>> vp1 = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
	VertexPair<Class<?>,Class<?>> vp2 = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
	vertexPairSet.add(vp);
	vertexPairSet.add(vp1);
	vertexPairSet.add(vp2);
	}
	

	@Test
	void test() {
		VertexPair<Class<?>,Class<?>> vp = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
		VertexPair<Class<?>,Class<?>> vp1 = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
		assertTrue(vp.equals(vp1));
		assertTrue(vertexPairSet.size()==1);
	}

	@Test
	void testInequality() {
		VertexPair<Class<?>,Class<?>> vp = new VertexPair<Class<?>,Class<?>>(Area.class, ArtistCredit.class);
		VertexPair<Class<?>,Class<?>> vp1 = new VertexPair<Class<?>,Class<?>>(Area.class, Release.class);
		assertFalse(vp.equals(vp1));
	}
}
