DROP SCHEMA IF EXISTS `helpdesk`;

CREATE DATABASE `helpdesk` /*!40100 DEFAULT CHARACTER SET latin1 */;

DROP TABLE IF EXISTS `helpdesk`.`chamado`;
CREATE TABLE  `helpdesk`.`chamado` (
  `id_chamado` int(10) unsigned NOT NULL auto_increment,
  `data_de_abertura` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `estado` varchar(20) NOT NULL,
  `descricao` text NOT NULL,
  `tipo` varchar(45) default NULL,
  `subtipo` varchar(45) default NULL,
  `id_patrimonio` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_chamado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`visita`;
CREATE TABLE  `helpdesk`.`visita` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `id_tecnico` varchar(45),
  `intinerario` text NOT NULL,
  `imprevistos` text NOT NULL,  
  `cabeamento_estruturado` tinyint(1) default '0',
  `layout_do_ambiente` tinyint(1) default '0',
  `tensao_dos_estabilizadores` tinyint(1) default '0',
  `instalacoes_eletricas` tinyint(1) default '0',
  `limpeza_dos_micros` tinyint(1) default '0',
  `tensao_dos_no_breaks` tinyint(1) default '0',
  `instalacoes_fisicas` tinyint(1) default '0',
  `limpeza_das_impressoras` tinyint(1) default '0',
  `organizacao_dos_cabos` tinyint(1) default '0',
  `imagens_dos_micros` tinyint(1) default '0',
  `outros_string` text NOT NULL,
  `outros` tinyint(1) default '0',
  `pendencias` text NOT NULL,  
  `servicos_realizados` text NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`chamados_repassados`;
CREATE TABLE  `helpdesk`.`chamados_repassados` (
  `id_transacao` int(10) unsigned NOT NULL,
  `id_chamado` int(10) NOT NULL ,
  `id_usuario` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`transacao`;
CREATE TABLE  `helpdesk`.`transacao` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `data` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `visivel` tinyint(3) unsigned NOT NULL,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`usuario`;
CREATE TABLE  `helpdesk`.`usuario` (
  `id_usuario` varchar(45) NOT NULL,
  `tipo_usuario` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `helpdesk`.`usuario`(id_usuario, tipo_usuario,nome) values ('admin@tre.br','administrador','Administrador');

DROP TABLE IF EXISTS `helpdesk`.`unidade`;
CREATE TABLE  `helpdesk`.`unidade` (
  `id_usuario` varchar(45) NOT NULL,
  `suporte` tinyint(1) NOT NULL,
  `gerencia` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_usuario`),
  CONSTRAINT `FK_unidade_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`tecnico`;
CREATE TABLE  `helpdesk`.`tecnico` (
  `id_usuario` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_usuario`),
  CONSTRAINT `FK_tecnico_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`responsavel_do_chamado`;
CREATE TABLE  `helpdesk`.`responsavel_do_chamado` (
  `id_chamado` int(10) unsigned NOT NULL,
  `id_tecnico` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_chamado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`atendimento`;
CREATE TABLE  `helpdesk`.`atendimento` (
  `id_atendimento` int(10) unsigned NOT NULL auto_increment,
  `idUnidadeSolicitante` varchar(45) NOT NULL,
  `idUnidadeSuporte` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_atendimento`),
  KEY `atendimento_unidade_solicitante` (`idUnidadeSolicitante`),
  KEY `atendimento_unidade_suporte` (`idUnidadeSuporte`),
  CONSTRAINT `atendimento_unidade_solicitante` FOREIGN KEY (`idUnidadeSolicitante`) REFERENCES `unidade` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `atendimento_unidade_suporte` FOREIGN KEY (`idUnidadeSuporte`) REFERENCES `unidade` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`tecnico_de_unidade`;
CREATE TABLE  `helpdesk`.`tecnico_de_unidade` (
  `tecnico` varchar(80) NOT NULL,
  `unidade` varchar(80) NOT NULL,
  PRIMARY KEY  (`tecnico`),
  KEY `FK_com_unidade` (`unidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`gerente_de_unidade`;
CREATE TABLE  `helpdesk`.`gerente_de_unidade` (
  `id_unidade` varchar(80) NOT NULL,
  `id_tecnico` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_unidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `helpdesk`.`tipo`;
CREATE TABLE  `helpdesk`.`tipo` (
  `id_unidade` varchar(45) NOT NULL default '',
  `nome_tipo` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`id_unidade`,`nome_tipo`),
  CONSTRAINT `FK_Unidade` FOREIGN KEY (`id_unidade`) REFERENCES `unidade` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `helpdesk`.`subtipo`;
CREATE TABLE  `helpdesk`.`subtipo` (
  `id_unidade` varchar(45) NOT NULL,
  `nome_tipo` varchar(45) NOT NULL,
  `nome_subtipo` varchar(45) NOT NULL,
  `tem_patrimonio` tinyint(1) NOT NULL,
  `prazo_atendimento` int(10) NOT NULL,
  PRIMARY KEY  USING BTREE (`id_unidade`,`nome_tipo`,`nome_subtipo`),
  CONSTRAINT `FK_tipo_unidade` FOREIGN KEY (`id_unidade`, `nome_tipo`) REFERENCES `tipo` (`id_unidade`, `nome_tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `helpdesk`.`solucao`;
CREATE TABLE  `helpdesk`.`solucao` (
  `id_solucao` int(10) unsigned NOT NULL,
  `descricao` text,
  PRIMARY KEY  (`id_solucao`),
  FOREIGN KEY (`id_solucao`) REFERENCES `chamado` (`id_chamado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`faq`;
CREATE TABLE  `helpdesk`.`faq` (
  `id_faq` int(10) unsigned NOT NULL auto_increment,
  `pergunta` text NOT NULL,
  `resposta` text NOT NULL,
  `tipo` varchar(40) NOT NULL,
  `subtipo` varchar(40) NOT NULL,
  PRIMARY KEY  (`id_faq`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`artigo`;
CREATE TABLE  `helpdesk`.`artigo` (
  `id_artigo` int(10) unsigned NOT NULL auto_increment,
  `titulo` varchar(200) NOT NULL,
  `texto` text NOT NULL,
  `tipo` varchar(40) NOT NULL,
  `subtipo` varchar(40) NOT NULL,
  PRIMARY KEY  (`id_artigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`config`;
CREATE TABLE  `helpdesk`.`config` (
  `id` varchar(45) NOT NULL,
  `valor` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`apropriacao`;
CREATE TABLE  `helpdesk`.`apropriacao` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `tecnico_responsavel` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`fechar`;
CREATE TABLE  `helpdesk`.`fechar` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `tecnico_responsavel` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`chamado_atendido`;
CREATE TABLE  `helpdesk`.`chamado_atendido` (
  `id_chamado` int(10) unsigned NOT NULL auto_increment,
  `unidade_solicitante` varchar(80) NOT NULL,
  `unidade_atendente` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_chamado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`delegacao`;
CREATE TABLE  `helpdesk`.`delegacao` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `tecnico_origem` varchar(80) NOT NULL,
  `tecnico_destino` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`encaminhamento`;
CREATE TABLE  `helpdesk`.`encaminhamento` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `tecnico_origem` varchar(80) NOT NULL,
  `tecnico_destino` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`inter_encaminhamento`;
CREATE TABLE  `helpdesk`.`inter_encaminhamento` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `unidade_origem` varchar(80) NOT NULL,
  `unidade_destino` varchar(80) NOT NULL,
  `tecnico_responsavel` varchar(80) NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`intervencao`;
CREATE TABLE  `helpdesk`.`intervencao` (
  `id_transacao` int(10) unsigned NOT NULL auto_increment,
  `tecnico_responsavel` varchar(80) NOT NULL,
  `descricao` text NOT NULL,
  PRIMARY KEY  (`id_transacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`transacao_de_chamado`;
CREATE TABLE  `helpdesk`.`transacao_de_chamado` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `id_chamado` int(10) unsigned NOT NULL,
  `id_transacao` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `helpdesk`.`config`(id, valor) values ('DOMAIN','@tre.br');
insert into `helpdesk`.`config`(id, valor) values ('USERNAME','helpnatt');
insert into `helpdesk`.`config`(id, valor) values ('SMTP_HOSTNAME','mail.tre.br');
insert into `helpdesk`.`config`(id, valor) values ('PASSWORD','12345678');
insert into `helpdesk`.`config`(id, valor) values ('URL_LDAP','ldap://10.12.1.15:389');
insert into `helpdesk`.`config`(id, valor) values ('EMAIL_NOTIFICACOES','suporte@tre.br');
insert into `helpdesk`.`config`(id, valor) values ('NOTIFICACAO','false');
insert into `helpdesk`.`config`(id, valor) values ('USAR_LOGIN_LDAP','false');
insert into `helpdesk`.`config`(id, valor) values ('TESTAR_EXISTENCIA_PATRIMONIO','false');
insert into `helpdesk`.`config`(id, valor) values ('SENHA_ADMINISTRADOR','123mudar');
insert into `helpdesk`.`config`(id, valor) values ('CRIAR_SUBTIPO_AUTOMATICAMENTE','true');
insert into `helpdesk`.`config`(id, valor) values ('PRAZO_DO_SUBTIPO_DEFAULT','10');

/* SIMULAÇÃO BDTRE */

DROP TABLE IF EXISTS `helpdesk`.`vwpb_chefe`;
CREATE TABLE  `helpdesk`.`vwpb_chefe` (
  `unidade_codigo` int(5) unsigned NOT NULL,
  `sigla` varchar(10) NOT NULL,
  `matricula` varchar(8) NOT NULL,
  `funcao_nome` varchar(60) default NULL,
  PRIMARY KEY  (`unidade_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`vwpb_lotacao`;
CREATE TABLE  `helpdesk`.`vwpb_lotacao` (
  `matricula` varchar(8) NOT NULL,
  `unidade_codigo` int(5) unsigned NOT NULL,
  `nome` varchar(60) NOT NULL,
  `sigla` varchar(10) NOT NULL,
  `dt_ini_lotacao` date default NULL,
  `dt_fim_lotacao` date default NULL,
  `sala` varchar(4) default NULL,
  `andar` varchar(5) default NULL,
  `fone` varchar(20) default NULL,
  PRIMARY KEY  (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `helpdesk`.`vwpb_unidade`;
CREATE TABLE  `helpdesk`.`vwpb_unidade` (
  `codigo` int(5) unsigned NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `sigla` varchar(10) NOT NULL,
  `unidade_sup_codigo` int(5) unsigned default NULL,
  `num_zona` int(3) unsigned default NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `helpdesk`.`vwpb_unidade`(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (1,'natt','natt','0','0');
insert into `helpdesk`.`vwpb_unidade`(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (2,'zon7','zon7','0','0');
insert into `helpdesk`.`vwpb_unidade`(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (3,'zon8','zon8','0','0');
insert into `helpdesk`.`vwpb_unidade`(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (4,'mental','mental','0','0');
insert into `helpdesk`.`vwpb_unidade`(codigo, descricao, sigla, unidade_sup_codigo, num_zona) values (5,'eletrico','eletrico','0','0');

insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('lauricio','eletrico',5,'Lauricio de Sousa');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('danusio','eletrico',5,'Danusio lopa');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('erick','eletrico',5,'Erick Moreno Queimado');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('dr.jegue','mental',4,'Dr. Jegue');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('dr.bode','mental',4,'Dr. Bode');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('guga','natt',1,'Ze de Guga');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('nelson','natt',1,'Nelson');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('danilo','zon7',2,'Gloc');
insert into `helpdesk`.`vwpb_lotacao`(matricula, sigla,unidade_codigo ,nome) values ('andre','zon8',3,'Alfa');


insert into `helpdesk`.`vwpb_chefe`(unidade_codigo, sigla, matricula) values (5,'eletrico','danusio');
insert into `helpdesk`.`vwpb_chefe`(unidade_codigo, sigla, matricula) values (4,'mental','dr.jegue');
insert into `helpdesk`.`vwpb_chefe`(unidade_codigo, sigla, matricula) values (1,'natt','guga');
insert into `helpdesk`.`vwpb_chefe`(unidade_codigo, sigla, matricula) values (2,'zon7','danilo');
insert into `helpdesk`.`vwpb_chefe`(unidade_codigo, sigla, matricula) values (3,'zon8','andre');


