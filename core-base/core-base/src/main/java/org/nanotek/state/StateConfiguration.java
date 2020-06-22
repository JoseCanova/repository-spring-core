package org.nanotek.state;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;

@Configuration
@EnableStateMachine(contextEvents = true)
public class StateConfiguration {

	public StateConfiguration() {
	}

}
