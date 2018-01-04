PRAGMA foreign_keys = OFF;

CREATE TABLE Contacts_Aux (
             id        INTEGER PRIMARY KEY
            ,name      TEXT    NOT NULL
            ,address   TEXT
            ,phone     TEXT
            ,webSite   TEXT);

INSERT INTO Contacts_Aux (
            name
           ,address
           ,phone
           ,webSite
   ) SELECT name
           ,address
           ,phone
           ,webSite
       FROM Contacts;

DROP TABLE Contacts;

ALTER TABLE Contacts_Aux
  RENAME TO Contacts;

PRAGMA foreign_keys = ON;