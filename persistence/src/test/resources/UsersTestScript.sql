create table if not exists Users
(
  UserId identity not null
    constraint user_pk
    primary key,
  DNI         varchar(255) not null,
  FirstName        varchar(255) not null,
  Surname     varchar(255) not null,
  Username    varchar(255) not null,
  Password    varchar(255) not null,
  MobilePhone varchar(255),
  Email       varchar(255) not null,
  isAdmin boolean not null
);

insert into users(userid, dni, firstname, surname, username, password, mobilephone, email, isadmin)
values (1,'12345678','TESTNAME','TESTSURNAME','TESTUSERNAME','TESTPASSWORD','1522334455','test@mail.com',true)
