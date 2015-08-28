package name.marmac.tutorials.cxfatwork.services.web.rest.server.impl.jaxrs;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import name.marmac.tutorials.cxfatwork.dal.api.CustomerPersistenceService;
import name.marmac.tutorials.cxfatwork.model.api.CustomerPO;
import name.marmac.tutorials.cxfatwork.model.to.customers.CustomerTOType;
import name.marmac.tutorials.cxfatwork.model.to.customers.CustomersTOType;
import name.marmac.tutorials.cxfatwork.model.to.customers.ObjectFactory;
import name.marmac.tutorials.cxfatwork.services.web.rest.api.customerservice.CustomerProvisioningService;
import name.marmac.tutorials.cxfatwork.services.web.rest.server.properties.CustomerProvisioningServiceProperties;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by marcomaccio on 15/08/2014.
 */
@Api(value = "/customerprovisioning", description = "Customer Provisioning Operations - CRUD")
@Path("/customerprovisioning")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class CustomerProvisioningServiceJaxrsImpl implements CustomerProvisioningService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProvisioningServiceJaxrsImpl.class);

    private static final String QUERY_PARAM_LIMIT           = "limit";
    private static final String QUERY_PARAM_ID              = "id";
    private static final String QUERY_PARAM_CUSTOMERID      = "customerId";
    private static final String QUERY_PARAM_FIRSTNAME       = "firstname";
    private static final String QUERY_PARAM_LASTNAME        = "lastname";
    private static final String QUERY_PARAM_CREATEDATE      = "createDate";
    private static final String QUERY_PARAM_LASTUPDATEDATE  = "lastUpdate";

    private static final String PATH_PARAM_ID			    = "id";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN_ALL = "*";

    //JAX-RS and JAX-WS context
    @javax.ws.rs.core.Context
    private MessageContext  response                         = null;

    //@Autowired
    private CustomerProvisioningServiceProperties   mProvisioningServiceProperties;
    //@Autowired
    private ObjectFactory                           mCustomersObjectFactory;
    //@Autowired
    private CustomerPersistenceService              mCustomerPersistenceService;

    /**
     *
     *
     */
    public CustomerProvisioningServiceJaxrsImpl() {

        LOGGER.info("Initializing the CustomerList");

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
     * @return
     */
    public CustomerProvisioningServiceProperties getmProvisioningServiceProperties() {
        return mProvisioningServiceProperties;
    }

    /**
     *
     */
    public void setCustomerPersistenceService(CustomerPersistenceService persistenceService){
        this.mCustomerPersistenceService = persistenceService;
    }

    /**
     *
     * @param customersObjectFactory
     */
    public void setCustomersObjectFactory(ObjectFactory customersObjectFactory) {
        this.mCustomersObjectFactory=customersObjectFactory;
    }

    /**
     *
     * @param customerToType   the xml or json representation of the CustomersTOType
     * @return                  the xml or json representation of the CustomersTOType
     * @see name.marmac.tutorials.cxfatwork.model.to.customers.CustomersTOType
     */
    @Override
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/customers")
    @ApiOperation(value = "Create Customer",
                    notes = "It allows creating one Customer",
                    response = CustomerTOType.class)
    public CustomerTOType createCustomer(@ApiParam(value = "CustomerTOType", required = true) CustomerTOType customerToType) {

        LOGGER.info("The createCustomer has been called ...");
        //Read the Customer from the request CustomerResource

        //Reference a CustomerPO object
        CustomerPO customerPO = mCustomerPersistenceService.createNewCustomer();
        //Mapping the properties from the request to the persistence object
        customerPO.setCustomerId(customerToType.getCustomerId());
        customerPO.setFirstName(customerToType.getFirstname());
        customerPO.setLastName(customerToType.getLastname());

        LOGGER.debug("Calling the persistence layer to save the customer " + customerPO.toString());
        //Call the persistence manager to periste the object
        mCustomerPersistenceService.save(customerPO);
        //Remap the id assigned by the DB to the customerTO
        customerToType.setId(customerPO.getId());
        //Set Location Header
        response.getHttpServletResponse().setHeader("Location", "/customers/" + customerToType.getId());
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
        //Set the HTTP Status Code to 201
        response.getHttpServletResponse().setStatus(HttpServletResponse.SC_CREATED);

        //return the CustomerToType to be serialized by the provider as requested by the client
        return customerToType;
    }

    /**
     *
     * @param limit
     * @param customerId
     * @param firstname
     * @param createDate
     * @param lastUpdate
     * @return                  the xml or json representation of the CustomersTOType
     * @see name.marmac.tutorials.cxfatwork.model.to.customers.CustomersTOType
     */
    @Override
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/customers")
    @ApiOperation(value = "Get Customers filtering them by some parameters in AND condition",
            notes = "Retrieve method",
            response = CustomersTOType.class)
    public CustomersTOType getCustomersByQuery(@ApiParam(value = QUERY_PARAM_LIMIT, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LIMIT) Integer limit,
                                              @ApiParam(value = QUERY_PARAM_CUSTOMERID, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_CUSTOMERID) String customerId,
                                              @ApiParam(value = QUERY_PARAM_FIRSTNAME, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_FIRSTNAME) String firstname,
                                              @ApiParam(value = QUERY_PARAM_LASTNAME, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LASTNAME) String lastname,
                                              @ApiParam(value = QUERY_PARAM_CREATEDATE, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_CREATEDATE) String createDate,
                                              @ApiParam(value = QUERY_PARAM_LASTUPDATEDATE, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LASTUPDATEDATE) String lastUpdate) {
        //TODO: Implement the filter query
        LOGGER.info("The getCustomerByQuery has been called ...");

        CustomersTOType customersTOType = mCustomersObjectFactory.createCustomersTOType();

        for (CustomerPO customer : mCustomerPersistenceService.getAll())
        {
            LOGGER.debug("Customer found in the DB " + customer.toString());
            //Create the CustomerTOType
            CustomerTOType customerTO = mCustomersObjectFactory.createCustomerTOType();
            customerTO.setId(customer.getId());
            customerTO.setFirstname(customer.getFirstName());
            customerTO.setLastname(customer.getLastName());
            customerTO.setCustomerId(customer.getCustomerId());
            customersTOType.getCustomers().add(customerTO);
            customersTOType.setTotalRecords(+1);
        }
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
        return customersTOType;
    }

    /**
     *
     * @param customerstotype
     * @return                  the xml or json representation of the CustomersTOType
     */
    @Override
    @PUT
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/customers")
    @ApiOperation(value = "Modify Customers method",
                notes = "Update method",
                response = CustomersTOType.class)
    public CustomersTOType updateCustomers(@ApiParam(value = "CustomersTOType", required = true, allowMultiple = true)
                                               CustomersTOType customerstotype) {

        LOGGER.info("The updateCustomers has been called ...");
        CustomerPO customerPO = mCustomerPersistenceService.createNewCustomer();
        if (customerstotype.getTotalRecords() == 1) {
            CustomerTOType customerTO = customerstotype.getCustomers().get(0);
            customerPO.setId(customerTO.getId());
            customerPO.setFirstName(customerTO.getFirstname());
            customerPO.setLastName(customerTO.getLastname());
            customerPO.setCustomerId(customerTO.getCustomerId());

            mCustomerPersistenceService.save(customerPO);
            customerstotype.getCustomers().get(0).setId(customerPO.getId());
        }
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
        return customerstotype;
    }

    /**
     *
     * @param customerstotype
     * @return                  the xml or json representation of the CustomersTOType
     */
    @Override
    @DELETE
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/customers")
    @ApiOperation(value = "Delete Customers method",
                notes = "Delete method",
                response = CustomersTOType.class)
    public CustomersTOType deleteCustomers(@ApiParam(value = "CustomersTOType", required = true, allowMultiple = true)
                                               CustomersTOType customerstotype) {

        LOGGER.info("The deleteCustomers has been called ...");
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
        return null;
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/customers/{id}")
    @ApiOperation(value = "Get a single Customer by its internal Id",
                    notes = "The requested id is the customerId",
                    response = CustomerTOType.class)
    public CustomerTOType getCustomerByNativeId(@ApiParam(value = PATH_PARAM_ID, required = true) @PathParam("id") String id) {
        LOGGER.debug("getCustomerById Method: search for Customer with ID=" + id);
        CustomerPO customerPO = mCustomerPersistenceService.getCustomerByNativeId(id);
        if (customerPO != null) {
            CustomerTOType customerTOType = this.toCustomerTO(customerPO);
            response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
            return customerTOType;
        }else {
            response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
            return null;
        }
    }

    @Override
    @PUT
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        @Path("/customers/{id}")
        @ApiOperation(value = "Update a single Customer by its internal Id",
                        notes = "The requested id is the customerId",
                        response = CustomerTOType.class)
    public CustomerTOType updateCustomerByNativeId(@ApiParam(value = PATH_PARAM_ID, required = true) @PathParam("customerId") String customerId, CustomerTOType customertotype) {
        CustomerPO customerPO = this.toCustomerPO(customertotype);
        CustomerPO updatedCustomerPO = mCustomerPersistenceService.save(customerPO);
        CustomerTOType updatedCustomerTO = this.toCustomerTO(updatedCustomerPO);
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
        return updatedCustomerTO;
    }

    @Override
    public CustomerTOType deleteCustomerByNativeId(@ApiParam(value = PATH_PARAM_ID, required = true) @PathParam("customerId") String customerId) {

        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN_ALL);
        return null;
    }


    /**
     *
     * @param customerPO
     * @return
     */
    private CustomerTOType toCustomerTO(CustomerPO customerPO){
        CustomerTOType customerTOType = mCustomersObjectFactory.createCustomerTOType();

        customerTOType.setId(customerPO.getId());
        customerTOType.setFirstname(customerPO.getFirstName());
        customerTOType.setLastname(customerPO.getLastName());
        customerTOType.setCustomerId(customerPO.getCustomerId());
        //TODO: transform java.util.date to Calendar
        // customerTOType.setCreateDate(customerPO.getCreateDate());
        // customerTOType.setLastUpdate(customerPO.getLastUpdate());
        return customerTOType;
    }

    /**
     *
     * @param customerTOType
     * @return
     */
    private CustomerPO toCustomerPO(CustomerTOType customerTOType){
        CustomerPO customerPO = mCustomerPersistenceService.createNewCustomer();
        customerPO.setId(customerTOType.getId());
        customerPO.setFirstName(customerTOType.getFirstname());
        customerPO.setLastName(customerTOType.getLastname());
        customerPO.setCustomerId(customerTOType.getCustomerId());
        //TODO: transform java.util.date to Calendar
        // customerTOType.setCreateDate(customerPO.getCreateDate());
        // customerTOType.setLastUpdate(customerPO.getLastUpdate());
        return customerPO;
    }
}
