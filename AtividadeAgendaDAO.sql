create database Agenda;
use Agenda;

create table Contato (
	id int NOT NULL auto_increment,
    nome varchar(45),
    telefone char(10),
    email varchar(45),
    endereco varchar(50),
    PRIMARY KEY(id)
);

INSERT INTO Contato VALUES
	(1, "Lúcio", "0123456789", "1@l", "Bairro do Limoeiro");
    
INSERT INTO Contato VALUES
    (2, "Ana", "9876543210", "2@a", "Bairro Julieta"),
    (3, "Simão", "1042536987", "3@s", "Bairro Bela Vista"),
    (4, "Fernanda", "9510378426", "4@F", "Bairro das Oliveiras");

select * from Contato;
