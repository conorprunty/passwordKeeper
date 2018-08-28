create table USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE PASSWORDS(
    id INT NOT NULL auto_increment, 
    system VARCHAR(50) NOT NULL,
    user VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);