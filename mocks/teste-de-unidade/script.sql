create database mocks;
use mocks;
create table LEILAO(
ID int not null auto_increment primary key,
DESCRICAO text,
DATA date,
ENCERRADO boolean
);
create table LANCES(
ID int not null auto_increment primary key,
LEILAO_ID integer,
USUARIO_ID integer,
VALOR double
);
create table USUARIO( ID int not null auto_increment primary key, NOME text );