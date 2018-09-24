create table if not exists Screening
(
  ScreeningID  identity,
  Showroom     varchar(255) not null,
  Movie        integer not null,
  ScreeningTime         timestamp    not null,
  Format       varchar(255) not null,
  ScreeningLanguage     varchar(255) not null,
  Theatre      varchar(255) not null,
  Availability integer,
  Price integer
);

insert into screening(screeningid, showroom, movie, screeningtime, format, screeninglanguage, theatre, availability,price)
  values (5,'ATLAS 1',1,TIMESTAMP '2100-9-20 20:00:00','2D','SUBTITULADO','ATLAS NORTE',100,120);

insert into screening(screeningid, showroom, movie, screeningtime, format, screeninglanguage, theatre, availability,price)
  values (6,'ATLAS 1',1,TIMESTAMP '2100-9-20 22:00:00','2D','SUBTITULADO','ATLAS NORTE',100,120);
