create table if not exists Food
(
  FoodID IDENTITY
    constraint food_pk
    primary key,
  FoodName   varchar(255) not null,
  Price  integer      not null,
  Stock  integer      not null,
  Image blob
);
insert into Food(foodid, foodname,price,stock) values(2,'Popcorn',100,1000);