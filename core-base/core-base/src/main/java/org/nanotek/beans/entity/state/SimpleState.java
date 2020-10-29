package org.nanotek.beans.entity.state;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.nanotek.entities.MutableTransitionsEntity;
import org.nanotek.entities.SimpleStateEntity;

@Entity
public class SimpleState 
extends BaseState<SimpleState>
implements SimpleStateEntity<SimpleState>,
MutableTransitionsEntity<Set<Transition>>{

	private static final long serialVersionUID = -9111970391275924842L;
	
	@OneToMany
	public Set<Transition> transitions;
	
	public SimpleState() {
	}

	public Set<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}

}
