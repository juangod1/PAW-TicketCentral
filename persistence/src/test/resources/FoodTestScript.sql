create table if not exists Food
(
  FoodID varchar(255) not null
    constraint food_pk
    primary key,
  FoodName   varchar(255) not null,
  Price  integer      not null,
  Stock  integer      not null,
  Image blob
);

insert into Food(foodid, foodname,price,stock) values('f1','Popcorn',100,1000);