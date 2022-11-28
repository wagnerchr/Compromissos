CREATE DATABASE compromissos;
USE compromissos;
# DROP DATABASE compromissos;

# ---------- CADASTRO ----------

CREATE TABLE pessoa (
# CHAVE
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 
  nome varchar(255),
  data_nasc DATE,
  endereco varchar(255),
  telefone varchar(255) UNIQUE,
  email varchar(255) UNIQUE,
  
/* -- Só quando for Cadastro -- */
  login VARCHAR(255) UNIQUE,
  senha varchar(255),
  ativo boolean
);

CREATE TABLE pessoacontato (
	id_pessoa int NOT NULL,
	id_contato int NOT NULL,
	
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);

CREATE TABLE pessoagrupo (
	id_pessoa int NOT NULL,
    id_grupo int NOT NULL,
    
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id),
    FOREIGN KEY (id_grupo) REFERENCES grupo(id)
);
 
CREATE TABLE grupo (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255)
);

CREATE TABLE compromisso (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    localc VARCHAR(255),
    data_inicio DATETIME,
    data_fim DATETIME
);

CREATE TABLE pessoacompromisso (
	id_pessoa int NOT NULL,
    id_compromisso int NOT NULL,
    
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id),
	FOREIGN KEY (id_compromisso) REFERENCES compromisso(id)
);


/*
SELECT * from compromisso;
Describe grupo;
SELECT * FROM grupo;
SELECT * FROM pessoagrupo WHERE pessoagrupo.id_pessoa = 1;

SELECT * FROM pessoa p WHERE p.id IN (SELECT id_contato FROM pessoacontato pc WHERE pc.id_pessoa = 1);

SELECT * FROM pessoagrupo;
SELECT * FROM grupo g WHERE g.id IN ( SELECT id_grupo FROM pessoagrupo pc WHERE pc.id_pessoa = 1);

DESCRIBE pessoa;
Select * from pessoa where pessoa.nome = "eweqeqw";

SELECT * FROM pessoa p WHERE p.id IN ( SELECT id_contato FROM pessoacontato pc WHERE pc.id_pessoa = 1);
*/
