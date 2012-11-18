package com.piotrnowicki.emf.web;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.piotrnowicki.emf.boundary.BeanABoundary;
import com.piotrnowicki.emf.boundary.CustomerBoundary;
import com.piotrnowicki.emf.entity.Customer;

/**
 * Defines RESTful resource. Allows for reading data and invoking exemplary business methods of the EJBs.
 * 
 * @author Piotr Nowicki
 * 
 */
@Path("/")
public class Entities {

    @EJB
    private CustomerBoundary customerBoundary;

    @EJB
    private BeanABoundary beanABoundary;

    /*
     * This omits the CDI Producer method as we're using @PersistceContext and not @Inject
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Simply uncomment the method you want to test. After server reload, the database should be cleaned (take a look at
     * {@code persistence.xml}) and you should see the result of the database after invocation of the method.
     * 
     * <strong>This is a CDI bean that is not aware of any transactions at all.</strong>
     * 
     * @return
     * @throws Exception
     */
    @Path("execute")
    @GET
    public String add() throws Exception {
        // customerBoundary.executeWithoutTx("John", "Doe");
        customerBoundary.executeWithTx("John", "Doe");
        // customerBoundary.executeWithTxStaredBeforeEntityManager("John", "Doe");
        // customerBoundary.executeWithTxStaredBeforeEntityManagerWithJoin("John", "Doe");
        // customerBoundary.executeWithTxMultipleTransactions("John", "Doe");

        return getAll();
    }

    /**
     * Invoke this method if you want to see the results of CDI RequestScoped injected application-managed EntityManager. If the
     * bean was properly executed we should return the new customer named 'CDITest1'.
     * 
     * @return
     * @throws Exception
     */
    @Path("cdi")
    @GET
    public String injectUsingCdi() throws Exception {
        beanABoundary.invoke();

        return getAll();
    }

    private String getAll() {
        List<Customer> customers = em.createNamedQuery(Customer.FIND_ALL, Customer.class).getResultList();

        return customers.toString();
    }
}
