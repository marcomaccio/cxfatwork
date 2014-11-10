package name.marmac.tutorials.cxfatwork.services.web.rest.clients.webclient;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import name.marmac.tutorials.cxfatwork.model.to.customers.CustomerTOType;
import name.marmac.tutorials.cxfatwork.model.to.customers.ObjectFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marcomaccio on 06/11/2014.
 */
public class CustomerProvisioningWebClient {

    public static final transient Logger LOGGER = LoggerFactory.getLogger(CustomerProvisioningWebClient.class);

    private WebClient       mCustomerProvisioningClient;
    private ObjectFactory   mCustomerObjectFactory;
    private List<Object>    mProviderArrayList;
    private String          mCustomerProvisioningBaseUrl = "http://localhost:9191/cxf/crm/provisioning/v1/customerprovisioning";

    /**
     * Default Constructor
     */
    public CustomerProvisioningWebClient() {
        //Initialize the Object Factory
        mCustomerObjectFactory = new ObjectFactory();
        //Initialize the providerArrayList
        mProviderArrayList = new ArrayList<Object>();
        //Add the Jacksonjaxb Provider
        mProviderArrayList.add(new JacksonJaxbJsonProvider());
        //Add the Jaxb Provider
        //mProviderArrayList.add(new JAXBElementProvider<CustomersTOType>());
        mProviderArrayList.add(new JAXBElementProvider<CustomerTOType>());
        //Create the CXFWebClient
        mCustomerProvisioningClient = WebClient.create(mCustomerProvisioningBaseUrl, mProviderArrayList);
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
        WebClient.getConfig(mCustomerProvisioningClient).getHttpConduit().getClient().setReceiveTimeout(10000000);
        Response response = mCustomerProvisioningClient.type(requestType).accept(responseType).path("/customers").post(customerTOType);

        LOGGER.info("RESPONSE HTTP STATUS: " + response.getStatus() );
        LOGGER.info("RESPONSE HTTP HEADERS size: " + response.getHeaders().size());
        for (Map.Entry<String, List<Object>> header : response.getHeaders().entrySet()) {
            System.out.print("\t Header Key = " + header.getKey() + " :");

            for (Object value : header.getValue()) {
                System.out.print(" value = " + value.toString() + ", ");
            }
            System.out.print("\n");
        }
        LOGGER.info("media-type: " + response.getMediaType().getType());
        LOGGER.info("Entity " + response.readEntity(String.class));
        return response;
    }
}
