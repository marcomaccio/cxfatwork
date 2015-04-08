package name.marmac.tutorials.cxfatwork.model.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by marcomaccio on 21/11/2014.
 */
public interface CustomerPO extends Serializable {

    /**
     *
     * @return
     */
    public Long getId();

    /**
     *
     * @param id
     */
    public void setId(Long id);

    /**
     *
     * @return
     */
    public Long getVersion();

    /**
     *
     * @param version
     */
    public void setVersion(Long version);

    /**
     *
     * @return
     */
    public String getFirstName();

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName);

    /**
     *
     * @return
     */
    public String getLastName();

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName);

    /**
     *
     * @return
     */
    public String getCustomerId();

    /**
     *
     * @param customerId
     */
    public void setCustomerId(String customerId);

    /**
     *
     * @return
     */
    public Date getCreateDate();

    /**
     *
     * @param createDate
     */
    public void setCreateDate(Date createDate);

    /**
     *
     * @return
     */
    public Date getLastUpdate();

    /**
     *
     * @param lastUpdate
     */
    public void setLastUpdate(Date lastUpdate);

    /**
     *
     * @return
     */
    public String getUser();

    /**
     *
     * @param user
     */
    public void setUser(String user);
}
