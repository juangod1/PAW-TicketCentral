create table if not exists Theatre
(
  TheatreName    varchar(255) not null
    constraint theatre_pk
    primary key,
  Address varchar(255) not null,
  City    varchar(255) not null
);

insert into theatre values ('ATLAS TEST', 'Test 100', 'TestCity');