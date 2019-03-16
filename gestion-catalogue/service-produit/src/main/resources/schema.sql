create table famille (
    id INTEGER not null AUTO_INCREMENT ,
    nom varchar(25) not null,
    primary key(id)
);

create table produit (
    id INTEGER not null AUTO_INCREMENT ,
    nom varchar(25) not null,
    description varchar(255),
    famille_id INTEGER not null references famille(id),
    primary key(id)
);

create table declinaison(
    id INTEGER not null AUTO_INCREMENT,
    nom varchar(25) not null,
    produit_id INTEGER not null references produit(id),
    primary key(id)
);

create table prix(
    id INTEGER not null AUTO_INCREMENT,
    valeur FLOAT not null,
    declinaison_id INTEGER not null references declinaison(id),
    debut DATE not null,
    fin DATE,
    primary key(id)
);