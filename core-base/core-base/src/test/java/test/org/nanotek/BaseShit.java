package test.org.nanotek;

import org.nanotek.ImmutableBase;

public class BaseShit implements ImmutableBase<BaseShit,Long>{

	private static final long serialVersionUID = -657347851191792407L; 
	
	Long id;
	
	public BaseShit() {
		super();
	}
	
	public BaseShit(Long id) {
		this.id = id;
	}
	
	@Override
	public Long getId() {
		return null;
	}
}
