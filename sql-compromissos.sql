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