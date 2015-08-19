create table if not exists Customers (
  ID int identity primary key,
  firstname     varchar,
  lastname      varchar,
  customerId    long,
  createDate    datetime,
  lastUpdate    datetime
)