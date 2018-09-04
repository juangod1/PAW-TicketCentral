create table if not exists Screening
(
  ScreeningID  INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1)
    unique,
  Showroom     varchar(255) not null,
  Movie        varchar(255) not null,
  ScreeningTime         timestamp    not null,
  Format       varchar(255) not null,
  ScreeningLanguage     varchar(255) not null,
  Theatre      varchar(255) not null,
  Availability integer,
  constraint screening_pk
  primary key (Showroom, Movie, ScreeningTime, Format, ScreeningLanguage, Theatre)
);