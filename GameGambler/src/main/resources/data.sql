
CREATE TABLE User(userid IDENTITY NOT NULL PRIMARY KEY auto_increment, first_name VARCHAR(255), last_name VARCHAR(255), username VARCHAR(255), password VARCHAR(255));


CREATE TABLE SCOREDETAILS(userid IDENTITY NOT NULL, score INT, timeOfPlay TIMESTAMP);
