create table if not exists Movies
(
  MovieID   serial         not null
    constraint movies_pk
    primary key,
  Rating   double precision,
  Title     varchar(255),
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
  Movie        integer not null
    constraint "Screening_fk0"
    references Movies,
  ScreeningTime         timestamp    not null,
  Format       varchar(255) not null,
  ScreeningLanguage     varchar(255) not null,
  Theatre      varchar(255) not null,
  Availability integer,
  constraint screening_pk
  primary key (Showroom, Movie, ScreeningTime, Format, ScreeningLanguage, Theatre)
);

create table if not exists Food
(
  FoodID serial not null
    constraint food_pk
    primary key,
  FoodName   varchar(255) not null,
  Price  integer      not null,
  Stock  integer      not null,
  Image bytea
);
create table if not exists Users
(
  UserID serial not null
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

create table if not exists Theatre
(
  TheatreName    varchar(255) not null
    constraint theatre_pk
    primary key,
  Address varchar(255) not null,
  City    varchar(255) not null
);

create table if not exists Transactions
(
  TransID      serial not null
    constraint transid_pk
    primary key,
  UserDni         integer          not null
    constraint transaction_user_dni_fk
    references Users (UserID),
  ScreeningID integer               not null
    constraint transaction_screening_screeningid_fk
    references Screening (ScreeningID),
  Seat         varchar(255)          not null,
  FoodDetails varchar(1024),
  Price        double precision      not null,
  TransactionDate         timestamp             not null,
  Paid         boolean default false not null
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
