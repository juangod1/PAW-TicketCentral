create table if not exists Movies
(
  IMDb   INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) not null
    constraint movies_pk
    primary key,
  Rating   double precision,
  Title     varchar(255),
  ReleaseDate     date,
  Runtime  integer,
  Genres   varchar(255),
  Image blob
);

