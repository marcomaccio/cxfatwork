package name.marmac.tutorials.cxfatwork.model.impl.jpa;

import name.marmac.tutorials.cxfatwork.model.api.CustomerPO;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by marcomaccio on 21/11/2014.
 */
@Entity
@Table(name = "Customers", uniqueConstraints=@UniqueConstraint(columnNames="customerId"))
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
    @Override
    @Id
    @Column(name = "pkId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    @Version
    @Column(name = "version", nullable = true)
    public Long getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    @Override
    @Column(name = "firstname", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    @Override
    @Column(name = "lastname", nullable = false)
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    @Override
    @Column(name = "customerId", nullable = false)
    public String getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    @Override
    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }

    /**
     *
     * @return
     */
    @Override
    @Column(name = "createDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     * @param createDate
     */
    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @return
     */
    @Override
    @Column(name = "lastUpdate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     *
     * @param lastUpdate
     */
    @Override
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     *
     * @return
     */
    @Override
    @Column(name = "appUser", nullable = true)
    public String getUser(){
        return user;
    }

    /**
     *
     * @param user
     */
    @Override
    public void setUser(String user){
        this.user = user;
    }

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
