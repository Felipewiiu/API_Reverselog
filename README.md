# ReverseLog

## Documantação Swagger

Para que vc tenha acesso aos andpoints da API basta acessa o link logo abaixo:

- [Swagger](http://localhost:8080/swagger-ui.html)

# Sistema de Gerenciamento de Devoluções

## Resumo

O Sistema de Gerenciamento de Devoluções foi desenvolvido para automatizar o processo de devolução de produtos em uma empresa de varejo. Este README descreve a motivação por trás do sistema, sua justificativa, a arquitetura proposta, o domínio e subdomínios envolvidos, eventos, comandos e agregados, além do fluxo do processo.

## História

O Sistema de Gerenciamento de Devoluções foi desenvolvido para atender à necessidade de automatizar o processo de devolução de produtos em uma empresa de varejo. O sistema anterior era manual e ineficiente, causando atrasos no processamento das devoluções e insatisfação dos clientes.

## Motivação

O principal fator que motivou o desenvolvimento do Sistema de Gerenciamento de Devoluções foi a necessidade de:

- Aumentar a eficiência do processo de devolução de produtos: O sistema manual era lento e sujeito a erros, resultando em atrasos no processamento das devoluções e insatisfação dos clientes. O novo sistema automatizado permite que as devoluções sejam processadas mais rapidamente e com mais precisão.
- Melhorar a experiência do cliente: O novo sistema oferece aos clientes uma maneira rápida e fácil de solicitar a devolução de produtos. Além disso, fornece informações sobre o status das devoluções, reduzindo a frustração.
- Reduzir custos: O sistema automatizado ajuda a reduzir custos eliminando a necessidade de trabalho manual e prevenindo fraudes e erros.

## Justificativa

O Sistema de Gerenciamento de Devoluções oferece vários benefícios para a empresa, incluindo:

- Maior eficiência: Automatiza o processo de devolução de produtos, permitindo que as devoluções sejam processadas mais rapidamente e com mais precisão.
- Melhor experiência do cliente: Oferece aos clientes uma maneira rápida e fácil de solicitar a devolução de produtos e fornece informações sobre o status das devoluções, reduzindo a frustração.
- Menores custos: Ajuda a reduzir custos eliminando a necessidade de trabalho manual e prevenindo fraudes e erros.

## Arquitetura Proposta

A arquitetura DDD proposta para o Sistema de Gerenciamento de Devoluções é composta pelos seguintes componentes:

- Agregação de produtos: Gerencia informações sobre produtos, incluindo nome, descrição, preço e estoque.
- Agregação de pedidos: Gerencia informações sobre pedidos, incluindo data do pedido, valor do pedido e itens do pedido.
- Agregação de devoluções: Gerencia informações sobre devoluções, incluindo data da devolução, motivo da devolução e itens da devolução.