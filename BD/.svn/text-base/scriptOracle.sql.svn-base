/* CRIANDO AS TABELAS */
CREATE OR REPLACE table "CHEFE" (
    "UNIDADE_CODIGO" NUMBER(2) NOT NULL,
    "MATRICULA"      VARCHAR2(8) NOT NULL,
    "FUNCAO_NOME"    VARCHAR2(60),
    constraint  "CHEFE_PK" primary key ("UNIDADE_CODIGO")
)
/
CREATE OR REPLACE table "LOTACAO" (
    "MATRICULA" VARCHAR2(8) NOT NULL,
	"UNIDADE_CODIGO" NUMBER(2) NOT NULL,
	"NOME" VARCHAR2(60) NOT NULL,
    "DT_INI_LOTACAO" DATE DEFAULT NULL,
	"DT_FIM_LOTACAO" DATE DEFAULT NULL,
	"SALA" VARCHAR2(4) DEFAULT NULL,
	"ANDAR" VARCHAR2(5) DEFAULT NULL,
	"FONE" VARCHAR2(20) DEFAULT NULL,        
    CONSTRAINT  "LOTACAO_PK" PRIMARY KEY ("MATRICULA")
)
/
CREATE OR REPLACE TABLE  "UNIDADE" (
  "CODIGO" NUMBER(5) NOT NULL,
  "DESCRICAO" VARCHAR2(200) NOT NULL,
  "SIGLA" VARCHAR2(10) NOT NULL,
  "UNIDADE_SUP_CODIGO" NUMBER(5) DEFAULT NULL,
  "NUM_ZONA" NUMBER(3) DEFAULT NULL,
  PRIMARY KEY  ("CODIGO")
)

/*POPULANDO AS TABELAS*/
insert into "CHEFE"(unidade_codigo, matricula) values (5,'danusio')
/
insert into "CHEFE"(unidade_codigo, matricula) values (4,'dr.jegue')
/
insert into "CHEFE"(unidade_codigo, matricula) values (1,'guga')
/

insert into "UNIDADE"(codigo, descricao, sigla) values (1,'natt','natt')
/
insert into "UNIDADE"(codigo, descricao, sigla) values (2,'zon7','zon7')
/
insert into "UNIDADE"(codigo, descricao, sigla) values (3,'zon8','zon8')
/
insert into "UNIDADE"(codigo, descricao, sigla) values (4,'mental','mental')
/
insert into "UNIDADE"(codigo, descricao, sigla) values (5,'eletrico','eletrico')
/
insert into "LOTACAO"(matricula, unidade_codigo, nome) values ('lauricio',5,'Lauricio de Sousa')
/
insert into "LOTACAO"(matricula, unidade_codigo, nome) values ('danusio',5,'Danusio lopa')
/
insert into "LOTACAO"(matricula, unidade_codigo, nome) values ('erick',5,'Erick Moreno Queimado')
/
insert into "LOTACAO"(matricula, unidade_codigo, nome) values ('dr.jegue',4,'Dr. Jegue')
/
insert into "LOTACAO"(matricula, unidade_codigo, nome) values ('dr.bode',4,'Dr. Bode')
/
insert into "LOTACAO"(matricula, unidade_codigo, nome) values ('guga',1,'Ze de Guga')
/
insert into "LOTACAO"(matricula, unidade_codigo, nome) values ('nelson',1,'Nelson')
/


/* CRIANDO AS VIEWS */
create or replace view "UNIDADE" as
SELECT * FROM UNIDADE
/
create or replace view "LOTACAO" as
SELECT * FROM LOTACAO
/   
create or replace view "VWPB_CHEFE" as
SELECT * FROM CHEFE
/




