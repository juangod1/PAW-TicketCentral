create table if not exists Movies
(
  MovieID   IDENTITY
    constraint movies_pk
    primary key,
  Rating   double precision,
  Title     varchar(255),
  ReleaseDate     date,
  Runtime  integer,
  Genres   varchar(255),
  Image blob,
  Active boolean
);
insert into movies(movieid,rating,title,releasedate,runtime,genres,image) values(2,8.9,'FARGO',CURRENT_DATE , 121, 'Drama', null);
insert into movies(movieid,rating,title,releasedate,runtime,genres,image) values(3,8.9,'CASABLANCA',date'1999-9-13', 121, 'Drama', null);


