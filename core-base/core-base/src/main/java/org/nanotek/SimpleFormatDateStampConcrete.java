package org.nanotek;

public class SimpleFormatDateStampConcrete implements SimpleFormatDateStamp {
	
	public SimpleFormatDateStampConcrete() {
	}
	
	public static void main(String[] args) {
		SimpleFormatDateStampConcrete c = new SimpleFormatDateStampConcrete();
		SimpleFormatDateStampConcrete c1 = new SimpleFormatDateStampConcrete();
		SimpleFormatDateStamp d = SimpleFormatDateStamp.newStamp("2013-04-17 11:24:48.435178+00");
		c.setStamp("2013-04-17 11:24:48.435178+00");
		c1.setStamp("2013-04-18 11:24:48.435178+00");
		System.out.println(c1.getStamp());
		System.out.println(c.getStamp());
		System.out.println(d.getStamp());
	}
}
