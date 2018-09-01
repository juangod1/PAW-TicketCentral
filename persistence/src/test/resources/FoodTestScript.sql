CREATE TABLE IF NOT EXISTS Food ( /*el tipo dijo que se tiene que llamar food no foodtest*/
  foodId VARCHAR(255) NOT NULL PRIMARY KEY,
  name VARCHAR (255) NOT NULL,
  price INTEGER NOT NULL ,
  stock INTEGER NOT NULL
);