/*
Created		02.3.2012 ã.
Modified		05.3.2012 ã.
Model		
Company		
Author		
Version		
Database		Oracle 10g 
*/
Drop table Renter cascade constraints
/
Drop table Car cascade constraints
/
Drop table Rent cascade constraints


-- Create Types section
-- Create Tables section
/
Create table Renter (
	id Integer NOT NULL ,
	first_name Varchar2 (50 CHAR) NOT NULL ,
	last_name Varchar2 (50 CHAR) NOT NULL ,
	egn Varchar2 (10 CHAR) NOT NULL ,
	address Varchar2 (50 CHAR) NOT NULL ,
	card_number Varchar2 (16 CHAR) NOT NULL ,
primary key (id) 
) 
/

Create table Car (
	id Integer NOT NULL ,
	producer Varchar2 (30 CHAR) NOT NULL ,
	modification Varchar2 (30 CHAR) NOT NULL ,
	manifacture_date Date NOT NULL ,
	registration_number Varchar2 (30 CHAR) NOT NULL ,
	color Varchar2 (30 CHAR) NOT NULL ,
	price_for_day Number(10,2) NOT NULL ,
primary key (id) 
) 
/

Create table Rent (
	id Integer NOT NULL ,
	car_id Integer NOT NULL ,
	renter_id Integer NOT NULL ,
	rent_date Date,
  from_date Date,
  to_date Date,
  cencelled Number(1) NOT NULL, 
primary key (id) 
) 
/


-- Create Alternate keys section


-- Create Indexes section


-- Create Foreign keys section

Alter table Rent add  foreign key (renter_id) references Renter (id) 
/

Alter table Rent add  foreign key (car_id) references Car (id) 
/
drop sequence Car_SEQ
/
drop sequence Renter_SEQ
/
drop sequence Rent_SEQ
/
Create  sequence Car_SEQ
/
Create  sequence Renter_SEQ
/
Create  sequence Rent_SEQ
/


-- Create Object Tables section


-- Create XMLType Tables section


-- Create Procedures section


-- Create Functions section


-- Create Views section


-- Create Sequences section


-- Create Triggers from referential integrity section


-- Create user Triggers section


-- Create Packages section


-- Create Synonyms section


-- Create Roles section


-- Users Permissions to roles section


-- Roles Permissions section

/* Roles permissions */


-- User Permissions section

/* Users permissions */


-- Create Table comments section


-- Create Attribute comments section


-- After section


