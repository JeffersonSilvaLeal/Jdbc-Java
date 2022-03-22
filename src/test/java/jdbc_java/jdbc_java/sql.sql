
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

/* Cria um Id unico*/
alter table userjdbcjava add unique(id);

/* criando a tabela telefone*/
create table telefoneuser
(
    id bigint not null,
    numero character varying (255) not null,
	tipo character varying (255) not null,
	userpessoa bigint not null,
	constraint telefone_id primary key (id)
)

/* Referenciando telefoneuser para pessoasuser*/
  alter table telefoneuser add foreign key (userpessoa) references userjdbcjava(id)
  
  /* Sequenciação da tabela telefoneuser*/
  
CREATE SEQUENCE IF NOT EXISTS public.user_telefone_seq
    INCREMENT 1
    START 7
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.user_telefone_seq
    OWNER TO postgres;
    
    /* Implementando sequencia no id telefoneuser*/
    ALTER TABLE IF EXISTS public.telefoneuser
   ALTER column id SET DEFAULT nextval('user_telefone_seq'::regclass);