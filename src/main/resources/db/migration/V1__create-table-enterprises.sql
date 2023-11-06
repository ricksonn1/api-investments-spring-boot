create table enterprises(

    id bigint not null auto_increment,
    name varchar(100) not null,
    ticker varchar(100) not null,
    sector varchar(250) not null,

    primary key(id)

);