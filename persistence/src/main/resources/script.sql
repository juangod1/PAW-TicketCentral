create table if not exists Movies
(
  IMDb   varchar(255)          not null
    constraint movies_pk
    primary key,
  Rating   double precision,
  Name     varchar(255),
  ReleaseDate     date,
  Runtime  integer,
  Genres   varchar(255),
  Image bytea
);

create table if not exists Screening
(
  ScreeningID  serial       not null
    constraint "Screening_ScreeningID_key"
    unique,
  Showroom     varchar(255) not null,
  Movie        varchar(255) not null
    constraint "Screening_fk0"
    references Movies,
  Time         timestamp    not null,
  Format       varchar(255) not null,
  Language     varchar(255) not null,
  Theatre      varchar(255) not null,
  Availability integer,
  constraint screening_pk
  primary key (Showroom, Movie, Time, Format, Language, Theatre)
);

create table if not exists Food
(
  FoodID varchar(255) not null
    constraint food_pk
    primary key,
  Name   varchar(255) not null,
  Price  integer      not null,
  Stock  integer      not null,
  Image bytea
);

create table if not exists User
(
  DNI         varchar(255) not null
    constraint user_pk
    primary key,
  Name        varchar(255) not null,
  Surname     varchar(255) not null,
  MobilePhone varchar(255),
  Email       varchar(255) not null
);

create table if not exists Theatre
(
  Name    varchar(255) not null
    constraint theatre_pk
    primary key,
  Address varchar(255) not null,
  City    varchar(255) not null
);

create table if not exists Transaction
(
  TransID      serial,
  User         varchar(255)          not null
    constraint transaction_user_dni_fk
    references User,
  ScreeningID integer               not null
    constraint transaction_screening_screeningid_fk
    references Screening (ScreeningID),
  Seat         varchar(255)          not null,
  Price        double precision      not null,
  Date         timestamp             not null,
  Paid         boolean default false not null,
  constraint transaction_pk
  primary key (ScreeningID, Seat)
);

create table if not exists Showrooms
(
  Theatre      char(255)           not null
    constraint Showrooms_Theatre_fkey
    references Theatre,
  ShowroomName char(255)           not null,
  Capacity     integer default 100 not null,
  Layout       text                not null,
  constraint Showrooms_pkey
  primary key (Theatre, ShowroomName)
);
