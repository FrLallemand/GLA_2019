CREATE TABLE UTILISATEURS (
       ID INTEGER not null primary key,
       LOGIN VARCHAR(20) not null,
       MDP VARCHAR(20) not null,
       NOM VARCHAR(20),
       PRENOM VARCHAR(20)
       );
CREATE TABLE ARTICLES (
       ID INTEGER not null primary key,
       NOM VARCHAR(20) not null,
       PRIX DOUBLE not null,
       VENDEUR INTEGER not null,
       ACHETEUR INTEGER,
       DATELIM DATE not null,
       FOREIGN KEY (VENDEUR) REFERENCES UTILISATEURS(ID),
       FOREIGN KEY (ACHETEUR) REFERENCES UTILISATEURS(ID) 
       );
CREATE TABLE LIVRAISONS (
       ID INTEGER not null primary key,
       ADRESSE VARCHAR(100)    
);
CREATE TABLE FACTURATIONS (
       ID INTEGER not null primary key,
       ACHETEUR INTEGER not null,
       FOREIGN KEY (ACHETEUR) REFERENCES UTILISATEURS(ID)
)
