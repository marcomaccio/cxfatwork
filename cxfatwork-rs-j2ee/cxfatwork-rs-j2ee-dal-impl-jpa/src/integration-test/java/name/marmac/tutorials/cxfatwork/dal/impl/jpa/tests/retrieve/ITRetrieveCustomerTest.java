package name.marmac.tutorials.cxfatwork.dal.impl.jpa.tests.retrieve;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import name.marmac.tutorials.cxfatwork.dal.impl.jpa.CustomerPersistenceServiceImplJPA;
import name.marmac.tutorials.cxfatwork.dal.impl.properties.DataStoreProperties;
import name.marmac.tutorials.cxfatwork.model.api.CustomerPO;
import org.junit.Assert;
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

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by marcomaccio on 24/11/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/dal-impl-jpa-integrationtest-context.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class ITRetrieveCustomerTest {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(ITRetrieveCustomerTest.class);

    @Resource
    private DataStoreProperties                 jdbcProperties;
    @Autowired
    private CustomerPersistenceServiceImplJPA   customerPersistenceService;

    /**
     *
     */
    @Test
    @DatabaseSetup("retrieved-customers.xml")
    public void testFindAllCustomers(){

        int EXPECTED_SIZE = 3;

        LOGGER.info("Method under test: FindAll ...");
        LOGGER.info("JDBC Connection Properties: " + jdbcProperties.getJdbcDriverName() + ", " +
                                                jdbcProperties.getJdbcUserName() + ", " +
                                                jdbcProperties.getJdbcPassword() + ", " +
                                                jdbcProperties.getSchemaFileName());

        List<CustomerPO> customerList = customerPersistenceService.getAll();

        LOGGER.info("Customer retrieved by the Persistence Layer: " + customerList.size());

        Assert.assertEquals("It was expected " + EXPECTED_SIZE + " but retrieved: " + customerList.size(), EXPECTED_SIZE, customerList.size());
    }

    /**
     * It tests the getCustomerByNativeId of the CustomerPersistenceServiceImplJPA
     */
    @Test
    @DatabaseSetup("retrieved-customers.xml")
    public void testFindCustomerByNativeId(){
        LOGGER.info("Method under test: FindAll ...");
        LOGGER.info("JDBC Connection Properties: " + jdbcProperties.getJdbcDriverName() + ", " +
                                                jdbcProperties.getJdbcUserName() + ", " +
                                                jdbcProperties.getJdbcPassword() + ", " +
                                                jdbcProperties.getSchemaFileName());

        CustomerPO customerRetrieved = customerPersistenceService.getCustomerByNativeId("001");

        Assert.assertEquals("001", customerRetrieved.getCustomerId());
        Assert.assertEquals("Marco", customerRetrieved.getFirstName());
        Assert.assertEquals("Maccio", customerRetrieved.getLastName());
    }

    /**
     * Test the getCustomerCount method
     */
    /*@Test
    @DatabaseSetup("retrieved-customers-totalCount.xml")
    public void testCustomerCount(){

        int EXPECTED_SIZE = 7;

        LOGGER.info("Method under test: getCustomerCount ...");
        LOGGER.info("JDBC Connection Properties: " + jdbcProperties.getJdbcDriverName() + ", " +
                                                jdbcProperties.getJdbcUserName() + ", " +
                                                jdbcProperties.getJdbcPassword() + ", " +
                                                jdbcProperties.getSchemaFileName());

        int totalCustomer = customerPersistenceService.getCustomerCount();

        Assert.assertEquals("It was expected " + EXPECTED_SIZE + " but retrieved: " + totalCustomer, EXPECTED_SIZE, totalCustomer);

    }*/

}
