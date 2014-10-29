package name.marmac.tutorials.cxfatwork.services.web.rest.server.impl.jaxrs;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import name.marmac.tutorials.cxfatwork.model.to.customers.CustomerTOType;
import name.marmac.tutorials.cxfatwork.model.to.customers.CustomersTOType;
import name.marmac.tutorials.cxfatwork.model.to.customers.ObjectFactory;
import name.marmac.tutorials.cxfatwork.services.web.rest.api.customerservice.CustomerProvisioningService;
import name.marmac.tutorials.cxfatwork.services.web.rest.properties.CustomerProvisioningServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.GregorianCalendar;

/**
 * Created by marcomaccio on 15/08/2014.
 */
@Api(value = "/customerprovisioning", description = "Customer Provisioning Operations - CRUD")
@Path("/customerprovisioning")
@Produces({"application/xml","application/json"})
public class CustomerProvisioningServiceJaxrsImpl implements CustomerProvisioningService {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(CustomerProvisioningServiceJaxrsImpl.class);

    private static final String QUERY_PARAM_LIMIT           = "limit";
    private static final String QUERY_PARAM_ID              = "id";
    private static final String QUERY_PARAM_CUSTOMERID      = "customerId";
    private static final String QUERY_PARAM_FIRSTNAME       = "firstname";
    private static final String QUERY_PARAM_LASTNAME        = "lastname";
    private static final String QUERY_PARAM_CREATEDATE      = "createDate";
    private static final String QUERY_PARAM_LASTUPDATEDATE  = "lastUpdate";

    private static final String PATH_RESOURCE               = "/customers";
    private static final String PATH_PARAM_ID			    = "id";

    private CustomerProvisioningServiceProperties   mProvisioningServiceProperties;
    private ObjectFactory                           mCustomersObjectFactory;
    private CustomersTOType                         mCustomersTOType;

    /**
     * Constructor method
     * It inizialize the objectFactory to create Customer(s)TOType objects
     */
    public CustomerProvisioningServiceJaxrsImpl(){

        //initialization of the CustomersTOType ObjectFactory
            mCustomersObjectFactory = new ObjectFactory();
        //Create the CustomersTOType that contains a list of CustomerTOType objects
            mCustomersTOType = mCustomersObjectFactory.createCustomersTOType();

    }
    /**
     *
     * @param provisioningServiceProperties
     */
    public void setProvisioningServiceProperties(CustomerProvisioningServiceProperties provisioningServiceProperties) {
        this.mProvisioningServiceProperties = provisioningServiceProperties;
    }

    /**
     *
     * @param customerstotype
     * @return
     */
    @Override
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path(PATH_RESOURCE)
    @ApiOperation(value = "Create Customers",
                    notes = "It allows creating one or more Customer. In this version is enabled only the creation of One Customer per request",
                    response = CustomersTOType.class)
    public CustomersTOType createCustomers(@ApiParam(value = "CustomersTOType", required = true, allowMultiple = true)
                                           CustomersTOType customerstotype) {
        //get the current total record.
        long totalrecord = mCustomersTOType.getTotalRecords();
        long newNumberId = totalrecord + 1;

        //take the customer from the customer list in the request
        CustomerTOType customerToBeCreated = customerstotype.getCustomer().get(0);
        //create the new id as an url to retrive the customer
        // http://host:port/webContext/servicePath/resourcePath
        String newId = "http://" + mProvisioningServiceProperties.getHost() + ":" + mProvisioningServiceProperties.getPort()
                + mProvisioningServiceProperties.getWebContext()
                + mProvisioningServiceProperties.getServicePath()
                + mProvisioningServiceProperties.getServiceInterface()
                + mProvisioningServiceProperties.getResourcePath()
                + "/"+newNumberId;

        customerToBeCreated.setId(newId);
        mCustomersTOType.getCustomer().add(customerToBeCreated);
        mCustomersTOType.setTotalRecords(mCustomersTOType.getTotalRecords()+1);
        return mCustomersTOType;
    }

    /**
     *
     * @param limit
     * @param customerId
     * @param firstname
     * @param createDate
     * @param lastUpdate
     * @return
     */
    @Override
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path(PATH_RESOURCE)
    @ApiOperation(value = "Get Customers filtering them by some parameters in AND condition",
            notes = "More notes about this method",
            response = CustomersTOType.class)
    public CustomersTOType getCustomerByQuery(@ApiParam(value = QUERY_PARAM_LIMIT, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LIMIT) Integer limit,
                                              @ApiParam(value = QUERY_PARAM_ID, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_ID) String id,
                                              @ApiParam(value = QUERY_PARAM_CUSTOMERID, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_CUSTOMERID) String customerId,
                                              @ApiParam(value = QUERY_PARAM_FIRSTNAME, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_FIRSTNAME) String firstname,
                                              @ApiParam(value = QUERY_PARAM_LASTNAME, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LASTNAME) String lastname,
                                              @ApiParam(value = QUERY_PARAM_CREATEDATE, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_CREATEDATE) String createDate,
                                              @ApiParam(value = QUERY_PARAM_LASTUPDATEDATE, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LASTUPDATEDATE) String lastUpdate) {
        //TODO: Implement the filter query
        LOGGER.info("Customers list returned: " + mCustomersTOType.getCustomer().size());
        LOGGER.info("Customer : " + mCustomersTOType.getCustomer().get(0).getCustomerId() + " "
                                  + mCustomersTOType.getCustomer().get(0).getFirstname()  + " "
                                  + mCustomersTOType.getCustomer().get(0).getLastname()  );

        return mCustomersTOType;
    }

    @Override
    @GET
    @Path("/customers/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Get a Customers by its system id",
                notes = "More notes about this method",
                response = CustomersTOType.class)
    public CustomersTOType getCustomerById(@ApiParam(value = PATH_PARAM_ID, required = true) @PathParam("id") String id) {

        LOGGER.info("Requested id :" + id);
        CustomerTOType retrievedCustomer = null;
        LOGGER.info("Customer List size: " + mCustomersTOType.getCustomer().size());

        for (CustomerTOType customerTO : mCustomersTOType.getCustomer()) {
            if (customerTO != null) {
                LOGGER.info("Customer's full id: " + customerTO.getId());
                String idValue = customerTO.getId().substring(customerTO.getId().lastIndexOf("/") + 1);
                if (idValue != null) {
                    LOGGER.info("Customer's full id: " + customerTO.getId() + " Customer's idValue: " + idValue);
                    if (idValue.equals(id)) {
                        retrievedCustomer = customerTO;
                    }
                }
            }
            else {
                LOGGER.info("The customer is null");
            }
        }

        CustomersTOType customersTOType = mCustomersObjectFactory.createCustomersTOType();
        customersTOType.getCustomer().add(retrievedCustomer);
        return customersTOType;
    }

    public void initializeSampleData(){

        this.mProvisioningServiceProperties.toString();

        //Create a customer to add to the Customer List
        CustomerTOType customerA = mCustomersObjectFactory.createCustomerTOType();
        long totalrecord = mCustomersTOType.getTotalRecords();
        long newNumberId = totalrecord + 1;

        String newId = "http://" + mProvisioningServiceProperties.getHost() + ":" + mProvisioningServiceProperties.getPort()
                        + mProvisioningServiceProperties.getWebContext()
                        + mProvisioningServiceProperties.getServicePath()
                        + mProvisioningServiceProperties.getServiceInterface()
                        + mProvisioningServiceProperties.getResourcePath()
                        + "/"+newNumberId;
        customerA.setId(newId);
        customerA.setFirstname("Firstname 01");
        customerA.setLastname("Lastname 01");
        customerA.setCustomerId("SMPL-001");
        customerA.setCreateDate(new GregorianCalendar());
        customerA.setLastUpdate(new GregorianCalendar());

        //Add the CustomerA to the Customer List
        mCustomersTOType.getCustomer().add(customerA);
        mCustomersTOType.setTotalRecords(1L);

    }
}
