package name.marmac.tutorials.cxfatwork.services.web.rest.clients.webclient;

import name.marmac.tutorials.cxfatwork.model.to.customers.CustomerTOType;
import name.marmac.tutorials.cxfatwork.model.to.customers.ObjectFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by marcomaccio on 10/11/2014.
 */
public class CustomerWebClient {

    public static final transient Logger LOGGER = LoggerFactory.getLogger(CustomerWebClient.class);

    private WebClient       mCustomerWebClient;
    private ObjectFactory   mCustomerObjectFactory;

    public void setCustomerWebClient(WebClient customerWebClient) {
        this.mCustomerWebClient = customerWebClient;
    }

    public WebClient getCustomerWebClient() {
        return this.mCustomerWebClient;
    }

    public void setCustomerObjectFactory(ObjectFactory customerObjectFactory) {
        this.mCustomerObjectFactory = customerObjectFactory;
    }

    public Response createCustomer(String customerId,
                                   String firstName,
                                   String lastName,
                                   MediaType requestType,
                                   MediaType responseType) {

        //Create the CustomerTOType (Transfer Object) to be serialized and passed to the server
        CustomerTOType customerTOType = mCustomerObjectFactory.createCustomerTOType();
        customerTOType.setCustomerId(customerId);
        customerTOType.setFirstname(firstName);
        customerTOType.setLastname(lastName);

        //call the cxf web client
        WebClient.getConfig(mCustomerWebClient).getHttpConduit().getClient().setReceiveTimeout(10000000);
        mCustomerWebClient.reset();
        Response response = mCustomerWebClient.type(requestType).accept(responseType).path("/customers").post(customerTOType);

        LOGGER.info("RESPONSE HTTP STATUS: " + response.getStatus() );
        LOGGER.info("RESPONSE HTTP HEADERS size: " + response.getHeaders().size());
        for (Map.Entry<String, List<Object>> header : response.getHeaders().entrySet()) {
            System.out.print("\t Header (Key:value) = " + header.getKey() + ": ");

            for (Object value : header.getValue()) {
                System.out.print(value.toString() + ", ");
            }
            System.out.print("\n");
        }
        LOGGER.info("media-type: " + response.getMediaType().getType());
        LOGGER.info("Entity " + response.readEntity(CustomerTOType.class));
        return response;
    }

}
