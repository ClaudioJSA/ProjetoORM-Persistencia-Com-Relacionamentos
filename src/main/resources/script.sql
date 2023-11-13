/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 * Created: 12 de nov. de 2023
 */

CREATE TABLE credential(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    lastAccess DATE,
    enabled BOOLEAN,
    user BIGINT
);

CREATE TABLE user(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthdate DATE,
    role BIGINT    
);

CREATE TABLE role(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE reader(
    id BIGINT PRIMARY KEY
);

CREATE TABLE librarian(
    id BIGINT PRIMARY KEY
);

ALTER TABLE credential ADD FOREIGN KEY (user) REFERENCES user(id);
ALTER TABLE user ADD FOREIGN KEY (role) REFERENCES role(id);
ALTER TABLE reader ADD FOREIGN KEY (id) REFERENCES user(id);
ALTER TABLE librarian ADD FOREIGN KEY (id) REFERENCES user(id);

