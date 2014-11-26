CREATE TABLE Customers (
  pkid            int(10) NOT NULL AUTO_INCREMENT,
  firstname     varchar(255) NOT NULL,
  lastname      varchar(255) NOT NULL,
  customerId    int(10) NOT NULL,
  createDate    datetime DEFAULT NULL,
  lastUpdate    datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY customerId (customerId)
);

