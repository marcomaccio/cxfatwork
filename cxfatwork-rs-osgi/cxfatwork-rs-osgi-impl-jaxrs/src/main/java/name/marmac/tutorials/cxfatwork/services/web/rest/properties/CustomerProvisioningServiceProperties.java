package name.marmac.tutorials.cxfatwork.services.web.rest.properties;

/**
 * Created by marcomaccio on 15/08/2014.
 */
public class CustomerProvisioningServiceProperties {

    private String host             = null;
    private String port             = null;
    private String webContext       = null;
    private String servicePath      = null;
    private String serviceInterface = null;
    private String resourcePath     = null;

    private String propertyA = null;
    private String propertyB = null;

    /**
     *
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     *
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     *
     * @return
     */
    public String getPort() {
        return port;
    }

    /**
     *
     * @param port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     *
     * @return
     */
    public String getWebContext() {
        return webContext;
    }

    /**
     *
     * @param webContext
     */
    public void setWebContext(String webContext) {
        this.webContext = webContext;
    }

    /**
     *
     * @return
     */
    public String getServicePath() {
        return servicePath;
    }

    /**
     *
     * @param servicePath
     */
    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    /**
     *
     * @return
     */
    public String getServiceInterface() {
        return serviceInterface;
    }

    /**
     *
     * @param serviceInterface
     */
    public void setServiceInterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    /**
     *
     * @return
     */
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     *
     * @param resourcePath
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     *
     * @return
     */
    public String getPropertyA() {
        return propertyA;
    }

    /**
     *
     * @param propertyA
     */
    public void setPropertyA(String propertyA) {
        this.propertyA = propertyA;
    }

    /**
     *
     * @return
     */
    public String getPropertyB() {
        return propertyB;
    }

    /**
     *
     * @param propertyB
     */
    public void setPropertyB(String propertyB) {
        this.propertyB = propertyB;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "CustomerProvisioningServiceProperties{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", webContext='" + webContext + '\'' +
                ", servicePath='" + servicePath + '\'' +
                ", serviceInterface='" + serviceInterface + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                '}';
    }
}
