package name.marmac.tutorials.cxfatwork.services.web.rest.clients.properties;

/**
 * Created by marcomaccio on 07/11/2014.
 */
public class CustomerProvisioningClientProperties {

    private String host;
    private String port;
    private String servletContext;
    private String servicePath;
    private String version;
    private String serviceInterface;
    private String resourcePath;

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
    public String getServletContext() {
        return servletContext;
    }

    /**
     *
     * @param servletContext
     */
    public void setServletContext(String servletContext) {
        this.servletContext = servletContext;
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
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(String version) { this.version = version; }

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
    @Override
    public String toString() {
        return "CustomerProvisioningClientProperties{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", servletContext='" + servletContext + '\'' +
                ", servicePath='" + servicePath + '\'' +
                ", version='" + version + '\'' +
                ", serviceInterface='" + serviceInterface + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                '}';
    }
}
