# Qual a diferença de getReferenceById para o findById ?

Os métodos getReferenceById e findById são utilizados para buscar entidades no banco de dados, mas possuem diferenças
importantes na forma como funcionam e nos casos de uso.

## getReferenceById

- Lazy Loading: O método getReferenceById (ou getReference no JPA) retorna uma instância proxy da entidade. A entidade
  real não é carregada imediatamente do banco de dados. A carga ocorre apenas quando um campo ou método da entidade é
  acessado. Este comportamento é conhecido como lazy loading.

- Proxy: Retorna um proxy da entidade, o que significa que o objeto retornado pode ser usado como um placeholder até que
  a
  entidade real seja carregada.

- Uso: É útil quando você só precisa de uma referência à entidade, por exemplo, para associá-la a outra entidade, mas
  não
  precisa imediatamente dos dados completos da entidade.

## findById:

- Eager Loading: O método findById (ou find no JPA) executa uma consulta imediata no banco de dados e retorna a entidade
  completa. Este comportamento é conhecido como eager loading.

- Consulta Imediata: A entidade é carregada do banco de dados assim que o método é chamado.

- Uso: É usado quando você precisa dos dados completos da entidade imediatamente.