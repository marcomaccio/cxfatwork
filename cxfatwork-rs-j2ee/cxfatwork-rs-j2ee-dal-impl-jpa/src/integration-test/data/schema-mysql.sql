DROP TABLE IF EXISTS gestimmo.CUSTOMERS;

DROP TABLE IF EXISTS gestimmo.SEQUENCE;

CREATE TABLE gestimmo.SEQUENCE (
  SEQ_NAME varchar(50) NOT NULL,
  SEQ_COUNT decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (SEQ_NAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE gestimmo.CUSTOMERS (
  pkId          bigint(20)    NOT NULL,
  customerId    varchar(255)  NOT NULL,
  firstname     varchar(255)  NOT NULL,
  lastname      varchar(255)  NOT NULL,
  createDate    datetime      DEFAULT NULL,
  lastUpdate    datetime      DEFAULT NULL,
  appUser       varchar(255)  DEFAULT NULL,
  version       bigint(20)    DEFAULT NULL,
  PRIMARY KEY (pkId),
  UNIQUE KEY customerId (customerId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

