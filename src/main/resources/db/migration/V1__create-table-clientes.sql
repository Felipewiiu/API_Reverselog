CREATE TABLE clientes (
  id bigint NOT NULL AUTO_INCREMENT,
  cnpj varchar(20) NOT NULL,
  cpf varchar(20) NOT NULL UNIQUE,
  email varchar(100) NOT NULL,
  bairro varchar(50),
  cep varchar(20) NOT NULL,
  cidade varchar(50) NOT NULL,
  complemento varchar(100),
  logradouro varchar(100),
  numero varchar(20),
  uf varchar(5) NOT NULL,
  telefone varchar(20) NOT NULL,
  solicitacoes bigint,
  ativo bit(1),

  PRIMARY KEY (id)
);

