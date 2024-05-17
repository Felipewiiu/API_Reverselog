create table solicitacoes(

    id    bigint       not null auto_increment,
    nf_compra         varchar(10)  not null,
    produto           varchar(100) not null,
    descricao_defeito varchar(200) not null,
    data              date         not null,
    ativo             bit(1),
    status            enum('ANALISE_DE_GARANTIA','AGUARDANDO_NOTA_FISCAL','EM_TRANSITO_EM_MANUTENCO','RETORNANDO_AO_CLIENTE') not null,
    cliente_id        bigint,

    primary key (id),
    constraint solicitacao_cliente_id foreign key (cliente_id) references clientes (id)
);
