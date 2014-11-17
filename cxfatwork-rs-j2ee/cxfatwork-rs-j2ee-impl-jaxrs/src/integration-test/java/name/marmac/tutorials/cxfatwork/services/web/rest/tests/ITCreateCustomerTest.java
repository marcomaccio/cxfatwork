package name.marmac.tutorials.cxfatwork.services.web.rest.tests;

import name.marmac.tutorials.cxfatwork.model.to.customers.CustomerTOType;
import name.marmac.tutorials.cxfatwork.services.web.rest.clients.webclient.CustomerWebClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by marcomaccio on 06/11/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ITCreateCustomerTest {

    @Resource
    public CustomerWebClient cpWebClient;

    /**
     *
     * @param cpWebClient
     */
    public void setWebClient(CustomerWebClient cpWebClient) {
        this.cpWebClient = cpWebClient;
    }

    /**
     *
     */
    @Test
    public void testCreateCustomerJSON() {
        //Create the Customer based on the following data:
        String customerId   = "00001";
        String firstName    = "Marco";
        String lastName     = "Maccio";

        Response response = cpWebClient.createCustomer(customerId, firstName, lastName,
                MediaType.APPLICATION_JSON_TYPE,
                MediaType.APPLICATION_JSON_TYPE);

        //Check that the response status code is 201
        Assert.assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());
        //Check that the Accept header is the expected one

        //Check that the passed value for create the customer are correctly returned
        Assert.assertEquals(customerId, response.readEntity(CustomerTOType.class).getCustomerId());
        Assert.assertEquals(firstName,  response.readEntity(CustomerTOType.class).getFirstname());
        Assert.assertEquals(lastName,   response.readEntity(CustomerTOType.class).getLastname());

    }


}
