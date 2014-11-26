package name.marmac.tutorials.cxfatwork.services.web.rest.tests;

import name.marmac.tutorials.cxfatwork.model.to.customers.CustomerTOType;
import name.marmac.tutorials.cxfatwork.model.to.customers.ObjectFactory;
import name.marmac.tutorials.cxfatwork.services.web.rest.clients.properties.CustomerProvisioningClientProperties;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by marcomaccio on 18/11/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/rest-service-jaxrs-integrationtest-context.xml"})
public class ITCreateCustomerDirectTest {

    public static final transient Logger LOGGER = LoggerFactory.getLogger(ITCreateCustomerDirectTest.class);

    @Resource
    private WebClient mCustomerWebClient;
    @Resource
    private ObjectFactory mCustomerObjectFactory;
    @Resource
    private CustomerProvisioningClientProperties clientProperties;

    /**
     *
     * @param customerWebClient
     */
    public void setCustomerWebClient(WebClient customerWebClient) {
        this.mCustomerWebClient = customerWebClient;
    }

    /**
     *
     * @param customerObjectFactory
     */
    public void setCustomerObjectFactory(ObjectFactory customerObjectFactory) {
        this.mCustomerObjectFactory = customerObjectFactory;
    }

    /**
     *
     */
    @Test
    public void testCreateCustomerJSON() {

        LOGGER.info("ClientProperties: " + clientProperties.toString());

        String customerId   = "00001";
        String firstName    = "Marco";
        String lastName     = "Maccio";

        //Create the CustomerTOType (Transfer Object) to be serialized and passed to the server
        CustomerTOType customerTOType = mCustomerObjectFactory.createCustomerTOType();
        customerTOType.setCustomerId(customerId);
        customerTOType.setFirstname(firstName);
        customerTOType.setLastname(lastName);
        //call the cxf web client
        WebClient.getConfig(mCustomerWebClient).getHttpConduit().getClient().setReceiveTimeout(10000000);
        mCustomerWebClient.reset();
        Response response = mCustomerWebClient.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).path("/customers").post(customerTOType);

        LOGGER.info("RESPONSE HTTP STATUS: " + response.getStatus() );
        LOGGER.info("RESPONSE HTTP HEADERS size: " + response.getHeaders().size());

        Assert.assertTrue(true);
    }
}
