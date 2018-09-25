create table if not exists Transactions
(
  TransID      identity not null
    constraint transid_pk
    primary key,
  UserId         integer          not null,
  ScreeningID integer               not null,
  Seat         varchar(255)          not null,
  FoodDetails varchar(1024),
  Price        double precision      not null,
  TransactionDate         timestamp             not null,
  Paid         boolean default false not null
);

insert into transactions(transid, userid, screeningid, seat, fooddetails, price, transactiondate, paid)
values (2,1,3,'A1',null ,400,TIMESTAMP '2018-09-20 16:47:20',false);
