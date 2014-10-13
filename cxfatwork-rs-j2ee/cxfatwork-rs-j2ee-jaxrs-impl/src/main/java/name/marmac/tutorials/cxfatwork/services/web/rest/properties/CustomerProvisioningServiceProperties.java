package name.marmac.tutorials.cxfatwork.services.web.rest.properties;

/**
 * Created by marcomaccio on 15/08/2014.
 */
public class CustomerProvisioningServiceProperties {

    private String propertyA = null;
    private String propertyB = null;

    public String getPropertyA() {
        return propertyA;
    }

    public void setPropertyA(String propertyA) {
        this.propertyA = propertyA;
    }

    public String getPropertyB() {
        return propertyB;
    }

    public void setPropertyB(String propertyB) {
        this.propertyB = propertyB;
    }

    @Override
    public String toString() {
        return "CustomerProvisioningServiceProperties{" +
                "propertyA='" + propertyA + '\'' +
                ", propertyB='" + propertyB + '\'' +
                '}';
    }
}
