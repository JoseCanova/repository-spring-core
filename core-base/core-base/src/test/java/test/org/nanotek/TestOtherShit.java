package test.org.nanotek;

import java.util.Optional;

public class TestOtherShit {

	public TestOtherShit() {
	}

	public static void main(String[] args) {
		Integer var = null; 
		Object result = Optional.ofNullable(var).map(t -> String.valueOf(t)).orElse("");
		System.out.println(result);
	}

}
