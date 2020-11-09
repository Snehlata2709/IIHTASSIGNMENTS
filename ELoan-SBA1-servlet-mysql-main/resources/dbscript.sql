DROP DATABASE IF EXITS test;

CREATE DATABASE test;

USE test;

DROP TABLE users;
DROP TABLE LOANINFO;
DROP TABLE LOANEMIINFO;

CREATE TABLE users(	
	username varchar(250) NOT NULL,
	password VARCHAR(20) NOT NULL,
	role VARCHAR(10) NOT NUll,
	PRIMARY KEY(username)
);


insert into users values('admin','admin','Admin');
insert into users values('Sneh','Lata','User');


CREATE TABLE LOANINFO(		
	Username VARCHAR(50) NOT NULL,
	loanapplno VARCHAR(50) NOT NULL,
	loanpurpose VARCHAR(50) NOT NULL,
	loanamtrequest INTEGER(50) NOT NULL,
	loanbstructure varchar(50) NOT NULL,
	loanbindicator VARCHAR(50) NOT NULL,
	loantindicator VARCHAR(50) NOT NULL,
	loanaddress VARCHAR(250) NOT NULL,
	loanemail VARCHAR(50) NOT NULL,
	loanmobile VARCHAR(12) NOT NULL,
	loanstaus VARCHAR(50) NOT NULL,
	loandoa DATE NOT NULL,
	loanupdatedao DATE,
	admincomment VARCHAR(50),	
	PRIMARY KEY(loanapplno)
);

CREATE TABLE LOANEMIINFO(		
	applno VARCHAR(50) NOT NULL,
	amotsanctioned INTERGER NOT NULL,
	interestRate DECIMAl(10,2) NOT NULL,
	loanterm INTEGER NOT NULL,
	psd DATE(50) NOT NULL,
	lcd DATE(50) NOT NULL,
	emi DECIMAL(10000000,2)) NOT NULL,
	PRIMARY KEY(loanapplno)
	)
	
	
	
	
	






		