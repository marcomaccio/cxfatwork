package name.marmac.tutorials.cxfatwork.services.web.rest.server.exceptions;

import javax.ws.rs.WebApplicationException;

/**
 * This exception is used by the server REST API Implementation to propagate exception to the client side.
 * It extends WebApplicationException that is a runtime exception so it shouldn't be present in the method signature
 * that throws the exception. In this way doesn't violate the Contract First approach.
 */
public class ProvisioningException extends WebApplicationException {

    private static final long serialVersionUID = 7457075754459224946L;
	private String errorCode;
	private String errorMsg;

    /**
     * Default constructor
     */
    public ProvisioningException() {super();}

    /**
     *
     * @param ex
     */
    public ProvisioningException(Exception ex) {super(ex);}

    /**
     *
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     *
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *
     * @return
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     *
     * @param errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
