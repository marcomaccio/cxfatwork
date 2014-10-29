A) Via Maven Repo  (local or corporate Nexus)
    
    features:addurl mvn:io.hawt/hawtio-karaf/1.4.17/xml/features
    features:addurl mvn:org.apache.cxf.karaf/apache-cxf/2.7.8/xml/features
    features:addurl mvn:name.marmac.tutorials/cxfatwork-rs-osgi-features/1.0.1444.BUILD-SNAPSHOT/xml/features
    
    features:install hawtio
    features:install cxfatwork-rest-osgi-modules