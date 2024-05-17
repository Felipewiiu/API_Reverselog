CREATE TABLE produtos(
 id bigint not null auto_increment,
 nome varchar(100) not null,
 modelo varchar(100) not null unique,
 numero_de_serie char(20) not null unique,
 ncm char(20) not null unique,
 ativo bit(1),
 solicitacao_id bigint,

 primary key(id),
 constraint produto_solicitacao_id foreign key (solicitacao_id) references solicitacoes (id)
);