package name.marmac.tutorials.cxfatwork.dal.impl.properties;

/**
 * Created by marcomaccio on 21/11/2014.
 */
public class DataStoreProperties {

    private String  jdbcDriverName;
    private String  jdbcUrl;
    private String jdbcUserName;
    private String  jdbcPassword;
    private String  schemaFileName;
    private String  jdbcEncoding;

    public String getJdbcDriverName() {
        return jdbcDriverName;
    }

    public void setJdbcDriverName(String jdbcDriverName) {
        this.jdbcDriverName = jdbcDriverName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUserName() { return jdbcUserName;}

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcEncoding() {
        return jdbcEncoding;
    }

    public void setJdbcEncoding(String jdbcEncoding) {
        this.jdbcEncoding = jdbcEncoding;
    }

    public String getSchemaFileName() {
        return schemaFileName;
    }

    public void setSchemaFileName(String schemaFileName) {
        this.schemaFileName = schemaFileName;
    }

    @Override
    public String toString() {
        return "JdbcConnectionProperties{" +
                "jdbcDriverName='" + jdbcDriverName + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", jdbcUserName='" + jdbcUserName + '\'' +
                ", jdbcPassword='" + jdbcPassword + '\'' +
                ", jdbcEncoding='" + jdbcEncoding + '\'' +
                ", schemaFileName='" + schemaFileName + '\'' +
                '}';
    }
}
