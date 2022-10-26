CREATE DATABASE compromissos;

use compromissos;
SET SQL_SAFE_UPDATES = 0;

# TABELA CADASTRO
CREATE TABLE cadastro(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(90) NOT NULL,
    data_nasc DATE NOT NULL,
    endereco VARCHAR(90) NOT NULL,
    telefone VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id) references cadastrogrupo(cadastroid)
);

CREATE TABLE cadastrogrupo(
	cadastroid INT NOT NULL,
    grupoid INT NOT NULL,
    
    FOREIGN KEY(cadastroid) REFERENCES cadastro(id),
    FOREIGN KEY(grupoid) REFERENCES grupo(id)
);

CREATE TABLE grupo(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES cadastrogrupo(grupoid)
);

/*
INSERTS
	INSERT INTO cadastro (nome,  data_nasc,   endereco, telefone, email, senha) VALUES("Wagner", "2001/01/17", "AVENIDA SEI LÁ", "123456", "email@email.com", "1233455");
	INSERT INTO cadastrogrupo(cadastroid, grupoid) VALUES(1, 1);
	INSERT INTO grupo (nome) VALUES("Grupo da faculdade");
*/
	
SELECT * FROM cadastrogrupo;

# VENDO CADASTROS E SEUS GRUPOS
SELECT c.nome, g.nome FROM cadastro c INNER JOIN cadastrogrupo cg INNER JOIN grupo g WHERE cg.cadastroid = c.id AND cg.grupoid = g.id;


