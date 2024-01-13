ALTER SESSION SET CURRENT_SCHEMA = VS_13_EQUIPE_6;

-- Script que deleta todas as tabelas e sequences do schema atual
DROP TABLE USUARIO CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_USUARIO;

DROP TABLE CLIENTE CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_CLIENTE;

DROP TABLE ESTUDANTE CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_ESTUDANTE;

DROP TABLE PCD CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_PCD;

DROP TABLE PROFISSIONAL_MENTOR CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_PROFISSIONAL_MENTOR;

DROP TABLE PROFISSIONAL_REALOCACAO CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_PROFISSIONAL_REALOCACAO;

DROP TABLE CERTIFICADO CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_CERTIFICADO;

DROP TABLE ENDERECO CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_ENDERECO;

DROP TABLE CONTATO CASCADE CONSTRAINTS PURGE;
DROP SEQUENCE SEQ_CONTATO;

DROP TABLE USUARIO_CONTATO CASCADE CONSTRAINTS PURGE;
DROP TABLE USUARIO_ENDERECO CASCADE CONSTRAINTS PURGE;