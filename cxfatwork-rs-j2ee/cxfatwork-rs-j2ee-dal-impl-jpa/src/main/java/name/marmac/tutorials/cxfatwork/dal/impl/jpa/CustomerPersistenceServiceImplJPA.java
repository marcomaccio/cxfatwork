package name.marmac.tutorials.cxfatwork.dal.impl.jpa;

import name.marmac.tutorials.cxfatwork.model.impl.jpa.Customer;
import name.marmac.tutorials.cxfatwork.model.impl.jpa.CustomerPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by marcomaccio on 21/11/2014.
 */
@Transactional
@Repository("CustomerPersistenceService")
public class CustomerPersistenceServiceImplJPA implements CustomerPersistenceService{

    private static final transient Logger LOGGER = LoggerFactory.getLogger(CustomerPersistenceServiceImplJPA.class);

    @PersistenceContext(unitName = "customers-punit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     *
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *
     * @return
     */
    public EntityManager getEntityManager(){
        return this.entityManager;
    }


    /**
     * Create a new Empty Customer
     */
    public CustomerPO createNewCustomer(){
        LOGGER.debug("Method new Customer has been called ");
        Customer newCustomer = new Customer();
        newCustomer.setCreateDate(new Date());
        newCustomer.setLastUpdate(new Date());

        LOGGER.debug("The new empty Customerhas been created " + newCustomer.toString());
        return newCustomer;
    }

    /**
     * Save the customer.
     * if the customer.getId is not null it will perform an update
     * if the customer.getId is null it will persist it for the first time (create in db)
     */
    public CustomerPO save(CustomerPO customer){
        LOGGER.debug("method save Customer has been called ");

        if (customer.getId() != null ) {
            LOGGER.debug("the method is performing an update into the db ");
            customer.setLastUpdate(new Date());
            entityManager.merge(customer);
            entityManager.flush();
        } else {
            LOGGER.debug("the method is performing a first persistence into the db ");
            customer.setLastUpdate(new Date());
            entityManager.persist(customer);
            LOGGER.debug("The customer has the new assigned id: " + customer.getId());
        }
        return customer;
    }

    /**
     *
     * @param nativeId
     * @return
     */
    public CustomerPO getCustomerByNativeId(String nativeId) {

        LOGGER.debug("Method getCustomerByNativeId has been called ");

        Customer customer = null;

        Query query = entityManager.createNamedQuery("Customers.findByCustomerId", Customer.class);
        query.setParameter("customerId", nativeId);

        List<Customer> resultList = query.getResultList();
        LOGGER.debug("The PL has found N. " + resultList.size());
        if (resultList.size() > 0)
        {
            customer = resultList.get(0);
        }
        return customer;
    }

    /**
     *
     * @return
     */
    public List<CustomerPO> getAll() {

        LOGGER.debug("Method getAll has been called ");
        List<CustomerPO> customerList = entityManager.createNamedQuery("Customers.findAll").getResultList();

        LOGGER.debug("The PL has found N. " + customerList.size() + " customers");
        return customerList;
    }

    /**
     * This method retrieve the total number of customers present in the db
     * @return
     */
    public int getCustomerCount() {
        String jpnlQuery = "SELECT COUNT(c) FROM Customer c";
        int totalCount = entityManager.createNamedQuery("Customers.Count").getResultList().size();
        //Number count = (Number)query.getResultList().size();
        return totalCount;
    }
}
