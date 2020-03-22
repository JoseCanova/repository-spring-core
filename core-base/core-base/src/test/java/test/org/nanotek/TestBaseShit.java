package test.org.nanotek;

import java.util.Optional;

import org.nanotek.ImmutableBase;

public class TestBaseShit {

	public TestBaseShit() {
	}

	public static void main(String[] args) {
		Optional<BaseShit> artist = ImmutableBase.newImmutableBase(BaseShit.class);
		artist.ifPresent(a -> System.out.println(a.getId()));
	}
	
}
