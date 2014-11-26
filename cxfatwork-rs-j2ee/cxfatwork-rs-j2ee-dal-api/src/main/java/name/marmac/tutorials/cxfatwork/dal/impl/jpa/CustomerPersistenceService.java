package name.marmac.tutorials.cxfatwork.dal.impl.jpa;

import name.marmac.tutorials.cxfatwork.model.impl.jpa.CustomerPO;

import java.util.List;

/**
 * Created by marcomaccio on 21/11/2014.
 */

public interface CustomerPersistenceService {

    /**
     * Create a new Empty Customer
     */
    public CustomerPO createNewCustomer();

    /**
     * Save the customer.
     * if the customer.getId is not null it will perform an update
     * if the customer.getId is null it will persist it for the first time (create in db)
     */
    public CustomerPO save(CustomerPO customerPO);

    /**
     *
     * @param nativeId
     * @return
     */
    public CustomerPO getCustomerByNativeId(String nativeId);

    /**
     *
     * @return
     */
    public List<CustomerPO> getAll();

    /**
     * This method retrieve the total number of customers present in the db
     * @return
     */
    public int getCustomerCount() ;
}
