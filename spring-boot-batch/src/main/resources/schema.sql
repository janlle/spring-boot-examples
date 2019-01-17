create table if not exists t_person (
  id      int primary key auto_increment,
  name    varchar(48),
  age     int,
  nation  varchar(48),
  address varchar(48)
);
