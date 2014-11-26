package name.marmac.tutorials.cxfatwork.model.impl.jpa;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by marcomaccio on 21/11/2014.
 */
@Entity
@Table(name = "Customers")
@NamedQueries({
        @NamedQuery(name = "Customers.findAll",             query = "SELECT c from Customer c "),
        @NamedQuery(name = "Customers.Count",               query = "SELECT COUNT(c) FROM Customer c"),
        @NamedQuery(name = "Customers.findByPkId",          query = "SELECT c FROM Customer c WHERE c.id = :id"),
        @NamedQuery(name = "Customers.findByCustomerId",    query = "SELECT c FROM Customer c WHERE c.customerId = :customerId")
})
public class Customer implements CustomerPO {

    private static final long serialVersionUID = 1L;

    private Long    id;
    private Long    version;
    private String  firstName;
    private String  lastName;
    private String  customerId;
    private Date    createDate;
    private Date    lastUpdate;
    private String  user = "";

    /**
     *
     * @return
     */
    @Id
    @Column(name = "pkId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Version
    @Column(name = "version", nullable = true)
    public Long getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    @Column(name = "firstname", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    @Column(name = "lastname", nullable = false)
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    @Column(name = "customerId", nullable = false)
    public String getCustomerId() { return customerId; }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    /**
     *
     * @return
     */
    @Column(name = "createDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @return
     */
    @Column(name = "lastUpdate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     *
     * @param lastUpdate
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     *
     * @return
     */
    @Column(name = "appUser", nullable = true)
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) { this.user = user; }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                ", user='" + user + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!customerId.equals(customer.customerId)) return false;
        if (!firstName.equals(customer.firstName)) return false;
        if (!lastName.equals(customer.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }
}
