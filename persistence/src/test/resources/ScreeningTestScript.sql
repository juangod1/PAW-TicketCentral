create table if not exists Screening
(
  ScreeningID  IDENTITY,
  Showroom     varchar(255) not null,
  Movie        integer not null,
  ScreeningTime         timestamp    not null,
  Format       varchar(255) not null,
  ScreeningLanguage     varchar(255) not null,
  Theatre      varchar(255) not null,
  Availability integer
);
