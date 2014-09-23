A) Via Maven Repo 
    
    features:addurl mvn:io.hawt/hawtio-karaf/1.4.17/xml/features
    features:addurl mvn:org.apache.cxf.karaf/apache-cxf/2.7.8/xml/features
    features:addurl mvn:name.marmac.tutorials/cxfatwork-rs-osgi-features/1.0.0-SNAPSHOT/xml/features
    
    features:install hawtio
    osgi:install mvn:io.hawt/hawtio-web/1.4.17
    features:install cxfatwork-rest-osgi-modules