package name.marmac.tutorials.cxfatwork.services.web.rest.server.mappers;


import name.marmac.tutorials.cxfatwork.model.to.exceptions.ObjectFactory;
import name.marmac.tutorials.cxfatwork.services.web.rest.server.exceptions.ProvisioningException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This class maps the Exception thrown in the server implementation class
 * with the XML/JSON Exception defined in the xsd.
 */
@Provider
public class ProvisioningExceptionMapper implements ExceptionMapper<ProvisioningException> {

    @Override
    public Response toResponse(ProvisioningException exception) {

        /// /1 Initialize the Response.Status variable
        Response.Status status = null;

        //2 Initialize the ObjectFactory to create the ProvisioningExceptionTO object
        //          that will be serialized in XML or json
        ObjectFactory exObjectFactory = new ObjectFactory();

        //3 Verify the errorCode in the caught exception and
        //          define the Response.Status and ProvisioningExceptionTO status code accordingly
        switch (exception.getErrorCode()) {

        }
        //TODO: 4 Set the exception error message in the exceptionTO status message property

        //TODO: 5 send the response back setting:
        //          - the status code
        //          - passing the exceptionTO as entity
        return null;
    }
}
