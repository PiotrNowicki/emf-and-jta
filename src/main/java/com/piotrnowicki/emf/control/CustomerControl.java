package com.piotrnowicki.emf.control;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.piotrnowicki.emf.entity.Customer;

@Stateless
public class CustomerControl {

    @Inject
    private Logger logger;

    @Inject
    EntityManager em;

    public void executeMethod() {
        
        em.persist(new Customer("ttt1222", "ttt2111"));
        
        logger.info("Inside method!");
    }
}
