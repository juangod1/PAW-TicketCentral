create table "Movies"
(
  "IMDb"   varchar(255)          not null
    constraint movies_pk
    primary key,
  rating   double precision,
  name     varchar(255),
  year     integer,
  runtime  integer,
  genres   varchar(255),
  premiere boolean default false not null
);

alter table "Movies"
  owner to postgres;

create table "Screening"
(
  "ScreeningID"  serial       not null
    constraint "Screening_ScreeningID_key"
    unique,
  "Showroom"     varchar(255) not null,
  "Movie"        varchar(255) not null
    constraint "Screening_fk0"
    references "Movies",
  "Time"         timestamp    not null,
  "Format"       varchar(255) not null,
  "Language"     varchar(255) not null,
  "Theatre"      varchar(255) not null,
  "Availability" integer,
  constraint screening_pk
  primary key ("Showroom", "Movie", "Time", "Format", "Language", "Theatre")
);

alter table "Screening"
  owner to postgres;

create table "Food"
(
  "FoodID" varchar(255) not null
    constraint food_pk
    primary key,
  "Name"   varchar(255) not null,
  "Price"  integer      not null,
  "Stock"  integer      not null
);

alter table "Food"
  owner to postgres;

create table "User"
(
  "DNI"         varchar(255) not null
    constraint user_pk
    primary key,
  "Name"        varchar(255) not null,
  "Surname"     varchar(255) not null,
  "MobilePhone" varchar(255),
  "Email"       varchar(255) not null
);

alter table "User"
  owner to postgres;

create table "Theatre"
(
  "Name"    varchar(255) not null
    constraint theatre_pk
    primary key,
  "Address" varchar(255) not null,
  "City"    varchar(255) not null
);

alter table "Theatre"
  owner to postgres;

create table "Transaction"
(
  "TransID"      serial,
  "User"         varchar(255)          not null
    constraint transaction_user_dni_fk
    references "User",
  "Screening ID" integer               not null
    constraint transaction_screening_screeningid_fk
    references "Screening" ("ScreeningID"),
  "Seat"         varchar(255)          not null,
  "Price"        double precision      not null,
  "Date"         timestamp             not null,
  "Paid"         boolean default false not null,
  constraint transaction_pk
  primary key ("Screening ID", "Seat")
);

alter table "Transaction"
  owner to postgres;

create table "Showrooms"
(
  "Theatre"      char(255)           not null
    constraint "Showrooms_Theatre_fkey"
    references "Theatre",
  "ShowroomName" char(255)           not null,
  "Capacity"     integer default 100 not null,
  "Layout"       text                not null,
  constraint "Showrooms_pkey"
  primary key ("Theatre", "ShowroomName")
);

alter table "Showrooms"
  owner to postgres;


