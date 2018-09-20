create table if not exists Showrooms
(
  Theatre      varchar(255)           not null,
  ShowroomName varchar(255)           not null,
  Capacity     integer default 100 not null,
  Layout       varchar(255)   not null,
  constraint Showrooms_pkey
  primary key (Theatre, ShowroomName)
);

insert into showrooms values ('ATLAS NORTE','ATLAS 1',112,'00111100n00111100n00111100n00111100n');
