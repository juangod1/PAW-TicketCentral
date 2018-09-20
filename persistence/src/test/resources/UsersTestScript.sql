create table if not exists Users
(
  DNI         varchar(255) not null
    constraint user_pk
    primary key,
  FirstName        varchar(255) not null,
  Surname     varchar(255) not null,
  Username    varchar(255) not null,
  Password    varchar(255) not null,
  MobilePhone varchar(255),
  Email       varchar(255) not null,
  isAdmin boolean not null
);

insert into users values ('12345678','TESTNAME','TESTSURNAME','TESTUSERNAME','TESTPASSWORD','1522334455','test@mail.com',true)
