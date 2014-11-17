package name.marmac.tutorials.cxfatwork.services.web.rest.server.impl.jaxrs;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
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

    private CustomerProvisioningServiceProperties   mProvisioningServiceProperties;
    private ObjectFactory                           mCustomersObjectFactory;

    private CustomersTOType                         mCustomersTOType;

    /**
     *
     * @param customersObjectFactory
     */
    public CustomerProvisioningServiceJaxrsImpl(ObjectFactory customersObjectFactory) {

        LOGGER.info(customersObjectFactory.toString() + " has been paased as constructor param ...");

        if (customersObjectFactory != null) {
            this.mCustomersObjectFactory = customersObjectFactory;
            LOGGER.info("Initializing the CustomerList");
            mCustomersTOType = mCustomersObjectFactory.createCustomersTOType();
            LOGGER.info("CustomerList size=" + mCustomersTOType.getCustomers().size());
            this.initializeSampleData();
        } else {
            LOGGER.info(customersObjectFactory + " is null");
        }
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

        mCustomersTOType.getCustomers().add(customerToType);
        mCustomersTOType.setTotalRecords(mCustomersTOType.getCustomers().size());

        //Set Location Header
        response.getHttpServletResponse().setHeader("Location", "/customers/" + customerToType.getCustomerId());
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
