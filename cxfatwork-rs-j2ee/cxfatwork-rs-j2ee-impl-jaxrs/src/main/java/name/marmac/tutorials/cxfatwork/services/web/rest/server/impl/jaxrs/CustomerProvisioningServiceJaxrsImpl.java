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

    private CustomerProvisioningServiceProperties provisioningServiceProperties;
    private ObjectFactory mCustomersObjectFactory;
    private CustomersTOType mCustomersTOType;

    /**
     * Constructor method
     * It inizialize the objectFactory to create Customer(s)TOType objects
     */
    public CustomerProvisioningServiceJaxrsImpl(){
        LOGGER.info(this.getClass().getName() + " has been initialized ...");
        //initialization of the CustomersTOType ObjectFactory
            mCustomersObjectFactory = new ObjectFactory();
        //Create the CustomersTOType that contains a list of CustomerTOType objects
            mCustomersTOType = mCustomersObjectFactory.createCustomersTOType();
        //Initializing a Sample data
        //this.initializeSampleData();
    }
    /**
     *
     * @param provisioningServiceProperties
     */
    public void setProvisioningServiceProperties(CustomerProvisioningServiceProperties provisioningServiceProperties) {
        this.provisioningServiceProperties = provisioningServiceProperties;
    }

    /**
     *
     * @param customerstotype   the xml or json representation of the CustomersTOType
     * @return                  the xml or json representation of the CustomersTOType
     * @see name.marmac.tutorials.cxfatwork.model.to.customers.CustomersTOType
     */
    @Override
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/customers")
    @ApiOperation(value = "Create Customers",
                    notes = "It allows creating one or more Customer. In this version is enabled only the creation of One Customer per request",
                    response = CustomersTOType.class)
    public CustomersTOType createCustomers(@ApiParam(value = "CustomersTOType", required = true, allowMultiple = true)
                                           CustomersTOType customerstotype) {
        //TODO: Implement the method
        LOGGER.info("The createCustomers has been called ...");
        return null;
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
    public CustomersTOType getCustomerByQuery(@ApiParam(value = QUERY_PARAM_LIMIT, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LIMIT) Integer limit,
                                              @ApiParam(value = QUERY_PARAM_CUSTOMERID, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_CUSTOMERID) String customerId,
                                              @ApiParam(value = QUERY_PARAM_FIRSTNAME, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_FIRSTNAME) String firstname,
                                              @ApiParam(value = QUERY_PARAM_CREATEDATE, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_CREATEDATE) String createDate,
                                              @ApiParam(value = QUERY_PARAM_LASTUPDATEDATE, required = false, allowMultiple = true) @QueryParam(QUERY_PARAM_LASTUPDATEDATE) String lastUpdate) {
        //TODO: Implement the filter query
        LOGGER.info("The getCustomerByQuery has been called ...");
        LOGGER.info("Customers list returned: " + mCustomersTOType.getCustomers().size());
        LOGGER.info("Customer : " + mCustomersTOType.getCustomers().get(0).getCustomerId() + " "
                                  + mCustomersTOType.getCustomers().get(0).getFirstname()  + " "
                                  + mCustomersTOType.getCustomers().get(0).getLastname()  );

        return mCustomersTOType;
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
        return null;
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
}
