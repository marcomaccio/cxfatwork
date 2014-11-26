package name.marmac.tutorials.cxfatwork.dal.impl.jpa.tests.create;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import name.marmac.tutorials.cxfatwork.dal.impl.jpa.CustomerPersistenceServiceImplJPA;
import name.marmac.tutorials.cxfatwork.dal.impl.properties.DataStoreProperties;
import name.marmac.tutorials.cxfatwork.model.impl.jpa.CustomerPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by marcomaccio on 06/11/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/dal-impl-jpa-integrationtest-context.xml"})
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class ITCreateCustomerTest {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(ITCreateCustomerTest.class);

    @Resource
    private DataStoreProperties jdbcProperties;
    @Autowired
    private CustomerPersistenceServiceImplJPA customerPersistenceService;

    /**
     *
     * @param jdbcProperties
     */
    public void setJdbcProperties(DataStoreProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }


    /**
     *
     */
    @Test
    @ExpectedDatabase("created-customer.xml")
    public void testCreateCustomer() {

        LOGGER.info("Method under test: save(Customer) ...");

        LOGGER.info("JDBC Connection Properties: " + jdbcProperties.getJdbcDriverName() + ", " +
                                        jdbcProperties.getJdbcUserName() + ", " +
                                        jdbcProperties.getJdbcPassword() + ", " +
                                        jdbcProperties.getSchemaFileName());

        //Create the Customer Object
        CustomerPO customer = customerPersistenceService.createNewCustomer();
        //set its mandatory properties
        customer.setFirstName("Marco");
        customer.setLastName("Maccio");
        customer.setCustomerId("001");
        customer.setUser("mm");
        customer.setVersion(1L);
        customer.setCreateDate(new Date());
        customer.setLastUpdate(new Date());

        //Call the persistence layer with save(Customer) method
        customerPersistenceService.save(customer);

        //Verify the values

        //Assert.assertTrue(true);
    }







}
