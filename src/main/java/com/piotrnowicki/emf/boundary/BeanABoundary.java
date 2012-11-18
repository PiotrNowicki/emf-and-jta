package com.piotrnowicki.emf.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
public class BeanABoundary {

    @Inject
    private EntityManager em;

    @EJB
    BeanB beanB;

    public void invoke() {

        /**
         * This operation makes CDI to inject (create) EntityManager. As this bean is non-transaction, the EntityManager won't
         * be transactional as well.
         * 
         * Because the CDI implementor can lazy-load the injected resource - without invoking any operation on the resource it
         * won't be injected. So the bottom line is: without this invocation (or any other on "em") we will be safe as the
         * EntityManager won't be created in non-transactional bean.
         * 
         * However, if this operation is uncommented, we won't get any exceptions but we will work on non-transactional EntityManager for the whole request. 
         */
        em.getProperties();

        beanB.invoke();
    }
}
