/*   create table if not exists Movies
(
  IMDb   varchar(255)          not null
    constraint movies_pk
    primary key,
  Rating   double precision,
  Name     varchar(255),
  ReleaseDate     date,
  Runtime  integer,
  Genres   varchar(255),
  Image blob
);*/