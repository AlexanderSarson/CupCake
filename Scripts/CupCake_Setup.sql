DROP SCHEMA if exists CupCake;
CREATE SCHEMA CupCake DEFAULT CHARACTER SET utf8 COLLATE utf8_danish_ci;
USE CupCake;
CREATE TABLE Users(
user_id INT AUTO_INCREMENT,
user_name VARCHAR(50) NOT NULL,
user_role VARCHAR(50) NOT NULL,
PRIMARY KEY(user_id)
);
CREATE TABLE Logins(
login_id INT AUTO_INCREMENT,
user_id INT,
login_mail VARCHAR(50) NOT NULL,
login_password VARCHAR(128) NOT NULL,
login_salt VARCHAR(100) NOT NULL,
PRIMARY KEY (login_id),
FOREIGN KEY (user_id)
	REFERENCES Users (user_id)
);
CREATE TABLE Accounts(
account_id INT AUTO_INCREMENT,
user_id INT,
user_balance int NOT NULL,
PRIMARY KEY (account_id),
FOREIGN KEY (user_id)
	REFERENCES Users (user_id)
);
CREATE TABLE Orders(
order_id INT AUTO_INCREMENT,
user_id INT,
order_date DATE,
PRIMARY KEY (order_id),
FOREIGN KEY (user_id)
	REFERENCES Users (user_id)
);
CREATE TABLE Toppings(
topping_id INT AUTO_INCREMENT,
topping_name VARCHAR(50) UNIQUE NOT NULL,
topping_price FLOAT NOT NULL,
topping_picture VARCHAR(100),
PRIMARY KEY (topping_id)
);
CREATE TABLE Bottoms(
bottom_id INT AUTO_INCREMENT,
bottom_name VARCHAR(50) UNIQUE NOT NULL,
bottom_price FLOAT NOT NULL,
bottom_picture VARCHAR(100),
PRIMARY KEY (bottom_id)
);
CREATE TABLE Cupcakes(
cupcake_id INT AUTO_INCREMENT,
topping_id INT,
bottom_id INT,
PRIMARY KEY (cupcake_id),
FOREIGN KEY (topping_id)
	REFERENCES Toppings (topping_id),
FOREIGN KEY (bottom_id)
	REFERENCES Bottoms (bottom_id)
);
CREATE TABLE LineItems(
cupcake_id INT,
order_id INT,
lineitem_qty INT NOT NULL,
FOREIGN KEY (cupcake_id)
	REFERENCES Cupcakes (cupcake_id),
FOREIGN KEY (order_id)
	REFERENCES Orders (order_id)
);
CREATE TABLE PreMadeCupcakes(
premadecupcake_id INT AUTO_INCREMENT,
topping_id INT,
bottom_id INT,
PRIMARY KEY (premadecupcake_id),
FOREIGN KEY (topping_id)
	REFERENCES Toppings (topping_id),
FOREIGN KEY (bottom_id)
	REFERENCES Bottoms (bottom_id)
);

INSERT INTO Users (user_name, user_role) VALUES ('userNameTest', 'CUSTOMER'); 
INSERT INTO Users (user_name, user_role) VALUES ('userNameTest2', 'ADMIN');
INSERT INTO Users (user_name, user_role) VALUES ('asdf', 'CUSTOMER');
INSERT INTO Logins (user_id, login_mail, login_password, login_salt) VALUES (1, 'loginMailTest@example.com', 'loginPasswordTest', '2384');
INSERT INTO Logins (user_id, login_mail, login_password, login_salt) VALUES (2, 'loginMailTest2@example.com', 'loginPasswordTest2', '2384');
INSERT INTO Logins (user_id, login_mail, login_password, login_salt) VALUES (3, 'lognValidation@example.com', '73941847d9611927275d93139981ee78316de50bc51bf398f8ccdd778c7723f370cb252c5293c085ec3c6a3d185246837ed71d651a679cb680793581ad77ac24', '2384');
INSERT INTO Accounts (user_id, user_balance) VALUES (1, 1000);
INSERT INTO Accounts (user_id, user_balance) VALUES (2, 1000);
INSERT INTO Accounts (user_id, user_balance) VALUES (3, 1000);
INSERT INTO Orders (user_id, order_date) VALUES (1, '2019-05-05');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Chocolate', 5.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Blueberry', 5.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Rasberry', 5.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Crispy', 6.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Strawberry', 6.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Rum/Rasin', 7.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Orange', 8.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Lemon', 8.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Blue cheese', 9.0, 'PictureStringHere');
INSERT INTO Toppings (topping_name, topping_price, topping_picture) VALUES ('Stinky Socks', 10.0, 'PictureStringHere');
INSERT INTO Bottoms (bottom_name, bottom_price, bottom_picture) VALUES ('Chocolate', 5.0, 'PictureStringHere');
INSERT INTO Bottoms (bottom_name, bottom_price, bottom_picture) VALUES ('Vanilla', 5.0, 'PictureStringHere');
INSERT INTO Bottoms (bottom_name, bottom_price, bottom_picture) VALUES ('Nutmeg', 5.0, 'PictureStringHere');
INSERT INTO Bottoms (bottom_name, bottom_price, bottom_picture) VALUES ('Pistacio', 6.0, 'PictureStringHere');
INSERT INTO Bottoms (bottom_name, bottom_price, bottom_picture) VALUES ('Almond', 7.0, 'PictureStringHere');
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (1, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (1, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (1, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (1, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (1, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (2, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (2, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (2, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (2, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (2, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (3, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (3, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (3, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (3, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (3, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (4, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (4, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (4, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (4, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (4, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (5, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (5, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (5, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (5, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (5, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (6, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (6, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (6, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (6, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (6, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (7, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (7, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (7, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (7, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (7, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (8, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (8, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (8, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (8, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (8, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (9, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (9, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (9, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (9, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (9, 5);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (10, 1);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (10, 2);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (10, 3);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (10, 4);
INSERT INTO Cupcakes (topping_id, bottom_id) VALUES (10, 5);
INSERT INTO LineItems (cupcake_id, order_id, lineitem_qty) VALUES (48, 1, 5);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (1,1);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (2,2);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (3,3);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (4,4);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (5,5);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (2,1);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (3,2);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (5,3);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (6,4);
INSERT INTO PreMadeCupcakes (topping_id,bottom_id) VALUES (7,5);


