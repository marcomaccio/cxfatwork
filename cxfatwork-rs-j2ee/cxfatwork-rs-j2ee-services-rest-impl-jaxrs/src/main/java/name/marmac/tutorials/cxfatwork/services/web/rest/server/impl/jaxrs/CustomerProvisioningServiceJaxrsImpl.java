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
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.GregorianCalendar;

/**
 * Created by marcomaccio on 15/08/2014.
 */
@Api(value = "/customerprovisioning", description = "Customer Provisioning Operations - CRUD")
@Path("/customerprovisioning")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class CustomerProvisioningServiceJaxrsImpl implements CustomerProvisioningService {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(CustomerProvisioningServiceJaxrsImpl.class);

    private static final String QUERY_PARAM_LIMIT           = "limit";
    private static final String QUERY_PARAM_ID              = "id";
    private static final String QUERY_PARAM_CUSTOMERID      = "customerId";
    private static final String QUERY_PARAM_FIRSTNAME       = "firstname";
    private static final String QUERY_PARAM_LASTNAME        = "lastname";
    private static final String QUERY_PARAM_CREATEDATE      = "createDate";
    private static final String QUERY_PARAM_LASTUPDATEDATE  = "lastUpdate";

    private static final String PATH_PARAM_ID			    = "id";

    //JAX-RS and JAX-WS context
    @javax.ws.rs.core.Context
    private MessageContext  response                         = null;

    @Autowired
    private CustomerProvisioningServiceProperties   mProvisioningServiceProperties;
    @Autowired
    private ObjectFactory                           mCustomersObjectFactory;
    @Autowired
    private CustomerPersistenceService              customerPersistenceService;


    private CustomersTOType                         mCustomersTOType;  //will be gone after the link to the persistence layer

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
     */
    public void setCustomerPersistenceService(CustomerPersistenceService persistenceService){
        this.customerPersistenceService = persistenceService;
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
        int size = mCustomersTOType.getCustomers().size();

        //Reference a CustomerPO object
        CustomerPO customerPO = customerPersistenceService.createNewCustomer();
        //Mapping the properties from the request to the persistence object
        customerPO.setCustomerId(customerToType.getCustomerId());
        customerPO.setFirstName(customerToType.getFirstname());
        customerPO.setLastName(customerToType.getLastname());

        LOGGER.debug("Calling the persistence layer to save the customer " + customerPO.toString());
        //Call the persistence manager to periste the object
        customerPersistenceService.save(customerPO);
        //Remap the id assigned by the DB to the customerTO
        customerToType.setId(customerPO.getId());
        //Set Location Header
        response.getHttpServletResponse().setHeader("Location", "/customers/" + customerToType.getId());
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
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

        for (CustomerPO customer : customerPersistenceService.getAll())
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
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
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
        CustomerPO customerPO = customerPersistenceService.createNewCustomer();
        if (customerstotype.getTotalRecords() == 1) {
            CustomerTOType customerTO = customerstotype.getCustomers().get(0);
            customerPO.setId(customerTO.getId());
            customerPO.setFirstName(customerTO.getFirstname());
            customerPO.setLastName(customerTO.getLastname());
            customerPO.setCustomerId(customerTO.getCustomerId());

            customerPersistenceService.save(customerPO);
            //customerstotype.getCustomers().get(0).setCreateDate(new GregorianCalendar());
            customerstotype.getCustomers().get(0).setId(customerPO.getId());
        }
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
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
        response.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
        return null;
    }

    /**
     *
     */
    public void initializeSampleData(){

        LOGGER.info("Initializing some sample data ...");
        //Create a customer to add to the Customer List
        CustomerTOType customerA = mCustomersObjectFactory.createCustomerTOType();
        customerA.setFirstname("Firstname 01");
        customerA.setLastname("Lastname 01");
        customerA.setCustomerId("SMPL-001");
        customerA.setCreateDate(new GregorianCalendar());
        customerA.setLastUpdate(new GregorianCalendar());

        LOGGER.info("Customer A has been created " + customerA.getCustomerId() + "/" + customerA.getFirstname() + "/" +
                customerA.getLastname() );
        //Add the CustomerA to the Customer List
        mCustomersTOType.getCustomers().add(customerA);
        mCustomersTOType.setTotalRecords(1L);

        LOGGER.info("Customers list has size:" + mCustomersTOType.getCustomers().size()
                +" and Total N. Records: " + mCustomersTOType.getTotalRecords());
    }

    private void setHTTPResponseCode(int statusCode) {

        // HttpServletResponse.SC_OK            --> Status code (201)
        // HttpServletResponse.SC_CREATED       --> Status code (201)
        // HttpServletResponse.SC_NO_CONTENT    --> Status code (204)
        // HttpServletResponse.SC_BAD_REQUEST   --> Status code (400)
        // HttpServletResponse.SC_UNAUTHORIZED  --> Status code (401)
        // HttpServletResponse.SC_NOT_FOUND     --> Status code (404)
        // HttpServletResponse.SC_CONFLICT      --> Status code (409)
        HttpServletResponse httpServletResponse = response.getHttpServletResponse();
        httpServletResponse.setStatus(statusCode);
    }
}
