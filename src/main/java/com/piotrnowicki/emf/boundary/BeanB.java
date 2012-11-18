package com.piotrnowicki.emf.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.piotrnowicki.emf.entity.Customer;

@Stateless
public class BeanB {

    // This is the same EntityManager as in BeanABoundary thanks to the CDI Request Scoped. 
    @Inject
    private EntityManager em;
    
    public void invoke() {
        em.persist(new Customer("CDITest1", "CDITest2"));
    }
}
