package org.nanotek.repository.jpa;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

class CdiConfig {

	  @Produces
	  @Dependent
	  @PersistenceContext
	  public EntityManager entityManager;
	}