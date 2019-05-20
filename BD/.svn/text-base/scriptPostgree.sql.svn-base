DROP SCHEMA IF EXISTS helpdesk CASCADE;
CREATE SCHEMA helpdesk
  AUTHORIZATION root;

DROP TABLE IF EXISTS helpdesk.chamado;
CREATE TABLE  helpdesk.chamado (
  id_chamado serial NOT NULL,
  data_de_abertura timestamp,
  estado varchar(20) NOT NULL,
  descricao text NOT NULL,
  tipo varchar(45) default NULL,
  subtipo varchar(45) default NULL,
  id_patrimonio varchar(45) NOT NULL,
  PRIMARY KEY  (id_chamado)
) ;
ALTER TABLE helpdesk.chamado OWNER TO root;

DROP TABLE IF EXISTS helpdesk.visita;
CREATE TABLE  helpdesk.visita (
  id_visita int(10) serial NOT NULL, 
  PRIMARY KEY  (id_visita)
);
ALTER TABLE helpdesk.visita OWNER TO root;

DROP TABLE IF EXISTS helpdesk.chamados_repassados;
CREATE TABLE  helpdesk.chamados_repassados (
  id_transacao integer NOT NULL,
  id_chamado integer NOT NULL,
  id_usuario varchar(45) NOT NULL,
  PRIMARY KEY  (id_transacao)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS helpdesk.transacao;
CREATE TABLE  helpdesk.transacao (
  id_transacao serial NOT NULL,
  data timestamp NOT NULL default CURRENT_TIMESTAMP,
  visivel boolean  NOT NULL,
  tipo varchar(45) NOT NULL,
  PRIMARY KEY  (id_transacao)
) ;
ALTER TABLE helpdesk.transacao OWNER TO root;

DROP TABLE IF EXISTS helpdesk.usuario;
CREATE TABLE  helpdesk.usuario (
  id_usuario varchar(45) NOT NULL,
  tipo_usuario varchar(45) NOT NULL,
  nome varchar(45) NOT NULL,
  PRIMARY KEY  (id_usuario)
) ;
ALTER TABLE helpdesk.usuario OWNER TO root;

insert into helpdesk.usuario(id_usuario, tipo_usuario,nome) values ('admin@tre.br','administrador','Administrador');

DROP TABLE IF EXISTS helpdesk.unidade;
CREATE TABLE  helpdesk.unidade (
  id_usuario varchar(45) NOT NULL,
  suporte boolean NOT NULL,
  gerencia varchar(45) NOT NULL,
  PRIMARY KEY  (id_usuario),
  CONSTRAINT FK_unidade_1 FOREIGN KEY (id_usuario) REFERENCES helpdesk.usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
) ;
ALTER TABLE helpdesk.unidade OWNER TO root;

DROP TABLE IF EXISTS helpdesk.tecnico;
CREATE TABLE  helpdesk.tecnico (
  id_usuario varchar(45) NOT NULL,
  PRIMARY KEY  (id_usuario),
  CONSTRAINT FK_tecnico_1 FOREIGN KEY (id_usuario) REFERENCES helpdesk.usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
) ;
ALTER TABLE helpdesk.tecnico OWNER TO root;


DROP TABLE IF EXISTS helpdesk.responsavel_do_chamado;
CREATE TABLE  helpdesk.responsavel_do_chamado (
  id_chamado integer NOT NULL,
  id_tecnico varchar(80) NOT NULL,
  PRIMARY KEY  (id_chamado)
) ;
ALTER TABLE helpdesk.responsavel_do_chamado OWNER TO root;


DROP TABLE IF EXISTS helpdesk.atendimento;
CREATE TABLE  helpdesk.atendimento (
  id_atendimento serial NOT NULL,
  idUnidadeSolicitante varchar(45) NOT NULL,
  idUnidadeSuporte varchar(45) NOT NULL,
  PRIMARY KEY  (id_atendimento),  
  CONSTRAINT atendimento_unidade_solicitante FOREIGN KEY (idUnidadeSolicitante) REFERENCES helpdesk.usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT atendimento_unidade_suporte FOREIGN KEY (idUnidadeSuporte) REFERENCES helpdesk.usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
) ;
ALTER TABLE helpdesk.atendimento OWNER TO root;

DROP TABLE IF EXISTS helpdesk.tecnico_de_unidade;
CREATE TABLE  helpdesk.tecnico_de_unidade (
  tecnico varchar(80) NOT NULL,
  unidade varchar(80) NOT NULL,
  PRIMARY KEY  (tecnico)  
);
ALTER TABLE helpdesk.tecnico_de_unidade OWNER TO root;

DROP TABLE IF EXISTS helpdesk.gerente_de_unidade;
CREATE TABLE  helpdesk.gerente_de_unidade (
  id_unidade varchar(80) NOT NULL,
  id_tecnico varchar(80) NOT NULL,
  PRIMARY KEY  (id_unidade)
) ;
ALTER TABLE helpdesk.gerente_de_unidade OWNER TO root;




DROP TABLE IF EXISTS helpdesk.tipo;
CREATE TABLE  helpdesk.tipo (
  id_unidade varchar(45) NOT NULL,
  nome_tipo varchar(45) NOT NULL,
  PRIMARY KEY  (id_unidade,nome_tipo),
  CONSTRAINT FK_Unidade FOREIGN KEY (id_unidade) REFERENCES helpdesk.usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
) ;
ALTER TABLE helpdesk.tipo OWNER TO root;

DROP TABLE IF EXISTS helpdesk.subtipo;
CREATE TABLE  helpdesk.subtipo (
  id_unidade varchar(45) NOT NULL,
  nome_tipo varchar(45) NOT NULL,
  nome_subtipo varchar(45) NOT NULL,
  tem_patrimonio boolean NOT NULL,
  prazo_atendimento integer NOT NULL,
  PRIMARY KEY (id_unidade,nome_tipo,nome_subtipo),
  CONSTRAINT FK_tipo_unidade FOREIGN KEY (id_unidade, nome_tipo) REFERENCES helpdesk.tipo (id_unidade, nome_tipo) ON DELETE CASCADE ON UPDATE CASCADE
) ;
ALTER TABLE helpdesk.subtipo OWNER TO root;

DROP TABLE IF EXISTS helpdesk.solucao;
CREATE TABLE  helpdesk.solucao (
  id_solucao integer NOT NULL,
  descricao text,
  PRIMARY KEY  (id_solucao),
  FOREIGN KEY (id_solucao) REFERENCES helpdesk.chamado (id_chamado)
) ;
ALTER TABLE helpdesk.solucao OWNER TO root;

DROP TABLE IF EXISTS helpdesk.faq;
CREATE TABLE  helpdesk.faq (
  id_faq serial NOT NULL,
  pergunta text NOT NULL,
  resposta text NOT NULL,
  tipo varchar(40) NOT NULL,
  subtipo varchar(40) NOT NULL,
  PRIMARY KEY  (id_faq)
) ;
ALTER TABLE helpdesk.faq OWNER TO root;

DROP TABLE IF EXISTS helpdesk.artigo;
CREATE TABLE  helpdesk.artigo (
  id_artigo  serial NOT NULL,
  titulo varchar(200) NOT NULL,
  texto text NOT NULL,
  tipo varchar(40) NOT NULL,
  subtipo varchar(40) NOT NULL,
  PRIMARY KEY  (id_artigo)
) ;
ALTER TABLE helpdesk.artigo OWNER TO root;

DROP TABLE IF EXISTS helpdesk.config;
CREATE TABLE  helpdesk.config (
  id varchar(45) NOT NULL,
  valor text NOT NULL,
  PRIMARY KEY  (id)
) ;
ALTER TABLE helpdesk.config OWNER TO root;


DROP TABLE IF EXISTS helpdesk.apropriacao;
CREATE TABLE  helpdesk.apropriacao (
  id_transacao serial NOT NULL,
  tecnico_responsavel varchar(80) NOT NULL,
  PRIMARY KEY  (id_transacao)
) ;
ALTER TABLE helpdesk.apropriacao OWNER TO root;

DROP TABLE IF EXISTS helpdesk.fechar;
CREATE TABLE  helpdesk.fechar (
  id_transacao serial NOT NULL,
  tecnico_responsavel varchar(80) NOT NULL,
  PRIMARY KEY  (id_transacao)
) ;
ALTER TABLE helpdesk.apropriacao OWNER TO root;


DROP TABLE IF EXISTS helpdesk.chamado_atendido;
CREATE TABLE  helpdesk.chamado_atendido (
  id_chamado serial NOT NULL,
  unidade_solicitante varchar(80) NOT NULL,
  unidade_atendente varchar(80) NOT NULL,
  PRIMARY KEY  (id_chamado)
 ); 
ALTER TABLE helpdesk.chamado_atendido OWNER TO root;

DROP TABLE IF EXISTS helpdesk.delegacao;
CREATE TABLE  helpdesk.delegacao (
  id_transacao serial NOT NULL,
  tecnico_origem varchar(80) NOT NULL,
  tecnico_destino varchar(80) NOT NULL,
  PRIMARY KEY  (id_transacao)
 );
ALTER TABLE helpdesk.delegacao OWNER TO root;

DROP TABLE IF EXISTS helpdesk.encaminhamento;
CREATE TABLE  helpdesk.encaminhamento (
  id_transacao serial NOT NULL,
  tecnico_origem varchar(80) NOT NULL,
  tecnico_destino varchar(80) NOT NULL,
  PRIMARY KEY  (id_transacao)
) ;
ALTER TABLE helpdesk.encaminhamento OWNER TO root;

DROP TABLE IF EXISTS helpdesk.inter_encaminhamento;
CREATE TABLE  helpdesk.inter_encaminhamento (
  id_transacao serial NOT NULL,
  unidade_origem varchar(80) NOT NULL,
  unidade_destino varchar(80) NOT NULL,
  tecnico_responsavel varchar(80) NOT NULL,
  PRIMARY KEY  (id_transacao)
) ;
ALTER TABLE helpdesk.inter_encaminhamento OWNER TO root;



DROP TABLE IF EXISTS helpdesk.intervencao;
CREATE TABLE  helpdesk.intervencao (
  id_transacao serial NOT NULL,
  tecnico_responsavel varchar(80) NOT NULL,
  descricao text NOT NULL,
  PRIMARY KEY  (id_transacao)
) ;
ALTER TABLE helpdesk.intervencao OWNER TO root;


DROP TABLE IF EXISTS helpdesk.transacao_de_chamado;
CREATE TABLE  helpdesk.transacao_de_chamado (
  id serial NOT NULL,
  id_chamado integer NOT NULL,
  id_transacao integer NOT NULL,
  PRIMARY KEY  (id)
);
ALTER TABLE helpdesk.transacao_de_chamado OWNER TO root;

DROP TABLE IF EXISTS helpdesk.visita_de_chamado;
CREATE TABLE  helpdesk.visita_de_chamado (
  id int(10) serial NOT NULL,
  id_visita int(10) integer NOT NULL,
  id_chamado int(10) integer NOT NULL,
  PRIMARY KEY  (id)
);
ALTER TABLE helpdesk.visita_de_chamado OWNER TO root;

insert into helpdesk.config(id, valor) values ('DOMAIN','@tre.br');
insert into helpdesk.config(id, valor) values ('USERNAME','helpnatt');
insert into helpdesk.config(id, valor) values ('SMTP_HOSTNAME','mail.tre.br');
insert into helpdesk.config(id, valor) values ('PASSWORD','12345678');
insert into helpdesk.config(id, valor) values ('URL_LDAP','ldap://10.12.1.15:389');
insert into helpdesk.config(id, valor) values ('EMAIL_NOTIFICACOES','suporte@tre.br');
insert into helpdesk.config(id, valor) values ('NOTIFICACAO','false');
insert into helpdesk.config(id, valor) values ('USAR_LOGIN_LDAP','false');
insert into helpdesk.config(id, valor) values ('TESTAR_EXISTENCIA_PATRIMONIO','false');
insert into helpdesk.config(id, valor) values ('SENHA_ADMINISTRADOR','123mudar');
insert into helpdesk.config(id, valor) values ('CRIAR_SUBTIPO_AUTOMATICAMENTE','true');
insert into helpdesk.config(id, valor) values ('PRAZO_DO_SUBTIPO_DEFAULT','10');

/* SIMULAÇÃO BDTRE */
/*
DROP TABLE IF EXISTS helpdesk.vwpb_chefe;
CREATE TABLE  helpdesk.vwpb_chefe (
  unidade_codigo int NOT NULL,
  sigla varchar(10) NOT NULL,
  matricula varchar(8) NOT NULL,
  funcao_nome varchar(60) default NULL,
  PRIMARY KEY  (unidade_codigo)
);

DROP TABLE IF EXISTS helpdesk.vwpb_lotacao;
CREATE TABLE  helpdesk.vwpb_lotacao (
  matricula varchar(8) NOT NULL,
  unidade_codigo int NOT NULL,
  sigla varchar(10) NOT NULL,
  nome varchar(60) NOT NULL,
  dt_ini_lotacao date default NULL,
  dt_fim_lotacao date default NULL,
  sala varchar(4) default NULL,
  andar varchar(5) default NULL,
  fone varchar(20) default NULL,
  PRIMARY KEY  (matricula)
);

DROP TABLE IF EXISTS helpdesk.vwpb_unidade;
CREATE TABLE  helpdesk.vwpb_unidade (
  codigo int NOT NULL,
  descricao varchar(200) NOT NULL,
  sigla varchar(10) NOT NULL,
  unidade_sup_codigo int default NULL,
  num_zona int default NULL,
  PRIMARY KEY  (codigo)
);


insert into helpdesk.vwpb_unidade(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (1,'natt','natt','0','0');
insert into helpdesk.vwpb_unidade(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (2,'zon7','zon7','0','0');
insert into helpdesk.vwpb_unidade(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (3,'zon8','zon8','0','0');
insert into helpdesk.vwpb_unidade(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (4,'mental','mental','0','0');
insert into helpdesk.vwpb_unidade(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (5,'eletrico','eletrico','0','0');

insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('lauricio','eletrico',5,'Lauricio de Sousa');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('danusio','eletrico',5,'Danusio lopa');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('erick','eletrico',5,'Erick Moreno Queimado');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('dr.jegue','mental',4,'Dr. Jegue');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('dr.bode','mental',4,'Dr. Bode');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('guga','natt',1,'Ze de Guga');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('nelson','natt',1,'Nelson');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('danilo','zon7',2,'Gloc');
insert into helpdesk.vwpb_lotacao(matricula, sigla,unidade_codigo ,nome) values ('andre','zon8',3,'Alfa');


insert into helpdesk.vwpb_chefe(unidade_codigo, sigla, matricula) values (5,'eletrico','danusio');
insert into helpdesk.vwpb_chefe(unidade_codigo, sigla, matricula) values (4,'mental','dr.jegue');
insert into helpdesk.vwpb_chefe(unidade_codigo, sigla, matricula) values (1,'natt','guga');
insert into helpdesk.vwpb_chefe(unidade_codigo, sigla, matricula) values (2,'zon7','danilo');
insert into helpdesk.vwpb_chefe(unidade_codigo, sigla, matricula) values (3,'zon8','andre');
*/
