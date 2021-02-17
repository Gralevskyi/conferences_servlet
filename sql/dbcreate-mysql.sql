-- ==============================================================
-- DB creation script for MySQL
-- ==============================================================

SET NAMES utf8;

DROP DATABASE IF EXISTS conferencedb;
CREATE DATABASE conferencedb CHARACTER SET utf8 COLLATE utf8_bin;

USE conferencedb;
-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------
CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(10) NOT NULL UNIQUE
);

-- this two commands insert data into roles table
-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Role entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO roles VALUES(0, 'moderator');
INSERT INTO roles VALUES(1, 'speaker');
INSERT INTO roles VALUES(2, 'user');

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------
CREATE TABLE users(

	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	
-- 'UNIQUE' means logins values should not be repeated in username column of table	
	username VARCHAR(20) NOT NULL UNIQUE,
	
-- not null string columns	
	password VARCHAR(25) NOT NULL,

	
-- this declaration contains the foreign key constraint	
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id) 

-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ROLES_ID=ID
		ON DELETE CASCADE 

-- the same as previous but updating is used insted deleting
		ON UPDATE RESTRICT
);

-- id = 1
INSERT INTO users VALUES(DEFAULT, 'moderator@mail.com', 'names', 0);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'speaker@mail.com', 'names', 1);
-- id = 3
INSERT INTO users VALUES(DEFAULT, 'client@mail.com', 'names', 2);

-- -------------------------------------------------------------
-- EVENTS
-- -------------------------------------------------------------
CREATE TABLE events(

	id INTEGER NOT NULL auto_increment PRIMARY KEY,
    -- event name in English
	name_en VARCHAR(100) NOT NULL,
    -- event name in Ukraine
    name_uk VARCHAR(100) NOT NULL,
	-- event place in English
	place_en VARCHAR(100) NOT NULL,
    -- event c in Ukraine
    place_uk VARCHAR(100) NOT NULL,
    
    date DATE NOT NULL,
	
	time TIME NOT NULL
);

-- -------------------------------------------------------------
-- EVENT_REPORTS
-- -------------------------------------------------------------

CREATE TABLE event_reports(

	event_id INTEGER NOT NULL,
	report_id INTEGER NOT NULL

);

-- -------------------------------------------------------------
-- EVENT_SUBSCRIBERS
-- -------------------------------------------------------------

CREATE TABLE event_subscribers(
	event_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL
);

-- -------------------------------------------------------------
-- EVENT_visitors
-- -------------------------------------------------------------

CREATE TABLE event_visitors(
	event_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL
);

-- -------------------------------------------------------------
-- REPORTS
-- -------------------------------------------------------------
CREATE TABLE reports(

	id INTEGER NOT NULL auto_increment PRIMARY KEY,
    -- report topic name in English
	topic_en VARCHAR(100) NOT NULL,
    -- report topic name in Ukraine
    topic_uk VARCHAR(100) NOT NULL,
	-- author of report
    author INTEGER NOT NULL,
    -- boolean is accepted by speaker
    accepted BOOLEAN, 
    -- marker of suggestion from speaker
    suggested BOOLEAN

);

-- -------------------------------------------------------------
-- SPEAKERS
-- -------------------------------------------------------------
CREATE TABLE speakers(
	id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE RESTRICT,
    -- speaker name in English
	name_en VARCHAR(100) NOT NULL,
    -- speaker name in Ukraine
    name_uk VARCHAR(100) NOT NULL
);

-- -------------------------------------------------------------
-- SPEAKER_REPORTS
-- -------------------------------------------------------------
CREATE TABLE speaker_reports(
	speaker_id INTEGER NOT NULL,
    report_id INTEGER NOT NULL
);


 