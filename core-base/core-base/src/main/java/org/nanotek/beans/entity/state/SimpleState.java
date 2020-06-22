package org.nanotek.beans.entity.state;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.nanotek.entities.MutableTransitionsEntity;

@Entity
public class SimpleState 
extends BaseState
implements 
MutableTransitionsEntity<Set<Transition>>{

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
