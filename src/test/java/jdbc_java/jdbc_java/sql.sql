
/*insert*/
insert into userjdbcjava (nome, email) values (?,?);

/* Busca por Id*/
select * from userjdbcjava where id =;

/* Atualizar*/
update userjdbcjava set nome = ? where id = ;

/* Deletar*/
delete from userjdbcjava where id = ;

/*Sequenciador*/
create SEQUENCE usersequence
increment 1
minvalue 1
maxvalue 9223372036854775807
start  7;

/*Selecionar tabela*/
select * from userjdbcjava

/*Implementando a sequencia na coluna id*/
alter table userjdbcjava ALTER column id set default nextval('usersequence'::regclass);