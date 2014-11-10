A) into apache-karaf-2.3.3   
    Via Maven Repo  (local or corporate Nexus)
    
    features:addurl mvn:io.hawt/hawtio-karaf/1.4.17/xml/features
    features:addurl mvn:org.apache.cxf.karaf/apache-cxf/2.7.8/xml/features
    features:addurl mvn:name.marmac.tutorials/cxfatwork-rs-osgi-features/1.0.1444.BUILD-SNAPSHOT/xml/features
    
    features:install hawtio
    features:install cxfatwork-rest-osgi-modules
    
    
B) into apache-karaf-2.4.0

    features:install jndi transaction jpa
    
    osgi:install -s mvn:org.apache.geronimo.specs/geronimo-j2ee-connector_1.5_spec/2.0.0
    osgi:install -s mvn:org.apache.geronimo.components/geronimo-transaction/2.2.2
    osgi:install -s mvn:org.apache.geronimo.components/geronimo-connector/2.2.2
    
    features:addurl mvn:org.apache.cxf.karaf/apache-cxf/2.7.8/xml/features
    features:addurl mvn:name.marmac.tutorials/cxfatwork-rs-osgi-features/1.0.1444.BUILD-SNAPSHOT/xml/features
    
    features:install spring/3.1.4.RELEASE
    features:install cxfatwork-rest-osgi-modules
    
    