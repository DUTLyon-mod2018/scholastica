/*==============================================================*/
/* Nom de SGBD :  Sybase SQL Anywhere 12                        */
/* Date de création :  03/05/2018 18:57:17                      */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_ADULTE_ASSOCIATI_COMPTE') then
    alter table ADULTE
       delete foreign key FK_ADULTE_ASSOCIATI_COMPTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_BENEFICI_BENEFICIE_RASED') then
    alter table BENEFICIER_DE
       delete foreign key FK_BENEFICI_BENEFICIE_RASED
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_BENEFICI_BENEFICIE_ENFANT') then
    alter table BENEFICIER_DE
       delete foreign key FK_BENEFICI_BENEFICIE_ENFANT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMPTE_ASSOCIATI_ADULTE') then
    alter table COMPTE
       delete foreign key FK_COMPTE_ASSOCIATI_ADULTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ENFANT_ASSOCIATI_CENTRE_S') then
    alter table ENFANT
       delete foreign key FK_ENFANT_ASSOCIATI_CENTRE_S
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ENFANT_ASSOCIATI_MEDECIN_') then
    alter table ENFANT
       delete foreign key FK_ENFANT_ASSOCIATI_MEDECIN_
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ENFANT_AVOIR_POU_NATIONAL') then
    alter table ENFANT
       delete foreign key FK_ENFANT_AVOIR_POU_NATIONAL
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ENFANT_ETRE_ASSU_ASSURANC') then
    alter table ENFANT
       delete foreign key FK_ENFANT_ETRE_ASSU_ASSURANC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ENFANT_ETRE_NE_A_VILLE') then
    alter table ENFANT
       delete foreign key FK_ENFANT_ETRE_NE_A_VILLE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETRE_AFF_ETRE_AFFE_CLASSE') then
    alter table ETRE_AFFECTE
       delete foreign key FK_ETRE_AFF_ETRE_AFFE_CLASSE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETRE_AFF_ETRE_AFFE_ADULTE') then
    alter table ETRE_AFFECTE
       delete foreign key FK_ETRE_AFF_ETRE_AFFE_ADULTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETRE_DAN_ETRE_DANS_CLASSE') then
    alter table ETRE_DANS
       delete foreign key FK_ETRE_DAN_ETRE_DANS_CLASSE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETRE_DAN_ETRE_DANS_ENFANT') then
    alter table ETRE_DANS
       delete foreign key FK_ETRE_DAN_ETRE_DANS_ENFANT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETRE_RES_ETRE_RESP_ADULTE') then
    alter table ETRE_RESPONSABLE
       delete foreign key FK_ETRE_RES_ETRE_RESP_ADULTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETRE_RES_ETRE_RESP_ENFANT') then
    alter table ETRE_RESPONSABLE
       delete foreign key FK_ETRE_RES_ETRE_RESP_ENFANT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LOG_ASSOCIATI_COMPTE') then
    alter table LOG
       delete foreign key FK_LOG_ASSOCIATI_COMPTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LOG_ASSOCIATI_ADULTE') then
    alter table LOG
       delete foreign key FK_LOG_ASSOCIATI_ADULTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LOG_ASSOCIATI_ENFANT') then
    alter table LOG
       delete foreign key FK_LOG_ASSOCIATI_ENFANT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LOG_ASSOCIATI_CLASSE') then
    alter table LOG
       delete foreign key FK_LOG_ASSOCIATI_CLASSE
end if;

drop index if exists ADULTE.ASSOCIATION_13_FK;

drop index if exists ADULTE.ADULTE_PK;

drop table if exists ADULTE;

drop index if exists ASSURANCE_SCOLAIRE.ASSURANCE_SCOLAIRE_PK;

drop table if exists ASSURANCE_SCOLAIRE;

drop index if exists BENEFICIER_DE.BENEFICIER_DE_FK;

drop index if exists BENEFICIER_DE.BENEFICIER_DE2_FK;

drop index if exists BENEFICIER_DE.BENEFICIER_DE_PK;

drop table if exists BENEFICIER_DE;

drop index if exists CENTRE_SECURITE_SOCIALE.CENTRE_SECURITE_SOCIALE_PK;

drop table if exists CENTRE_SECURITE_SOCIALE;

drop index if exists CLASSE.CLASSE_PK;

drop table if exists CLASSE;

drop index if exists COMPTE.ASSOCIATION_14_FK;

drop index if exists COMPTE.COMPTE_PK;

drop table if exists COMPTE;

drop index if exists ENFANT.ASSOCIATION_12_FK;

drop index if exists ENFANT.ASSOCIATION_11_FK;

drop index if exists ENFANT.ETRE_NE_A_FK;

drop index if exists ENFANT.ETRE_ASSURE_FK;

drop index if exists ENFANT.AVOIR_POUR_FK;

drop index if exists ENFANT.ENFANT_PK;

drop table if exists ENFANT;

drop index if exists ETRE_AFFECTE.ETRE_AFFECTE_FK;

drop index if exists ETRE_AFFECTE.ETRE_AFFECTE2_FK;

drop index if exists ETRE_AFFECTE.ETRE_AFFECTE_PK;

drop table if exists ETRE_AFFECTE;

drop index if exists ETRE_DANS.ETRE_DANS_FK;

drop index if exists ETRE_DANS.ETRE_DANS2_FK;

drop index if exists ETRE_DANS.ETRE_DANS_PK;

drop table if exists ETRE_DANS;

drop index if exists ETRE_RESPONSABLE.ETRE_RESPONSABLE_FK;

drop index if exists ETRE_RESPONSABLE.ETRE_RESPONSABLE2_FK;

drop index if exists ETRE_RESPONSABLE.ETRE_RESPONSABLE_PK;

drop table if exists ETRE_RESPONSABLE;

drop index if exists LOG.ASSOCIATION_18_FK;

drop index if exists LOG.ASSOCIATION_17_FK;

drop index if exists LOG.ASSOCIATION_16_FK;

drop index if exists LOG.ASSOCIATION_15_FK;

drop index if exists LOG.LOG_PK;

drop table if exists LOG;

drop index if exists MEDECIN_TRAITANT.MEDECIN_TRAITANT_PK;

drop table if exists MEDECIN_TRAITANT;

drop index if exists NATIONALITE.NATIONALITE_PK;

drop table if exists NATIONALITE;

drop index if exists RASED.RASED_PK;

drop table if exists RASED;

drop index if exists VILLE.VILLE_PK;

drop table if exists VILLE;

/*==============================================================*/
/* Table : ADULTE                                               */
/*==============================================================*/
create table ADULTE 
(
   ID_ADULTE            integer                        not null,
   ID_UTILISATEUR       long varchar                   not null,
   NOM_AD               long varchar                   not null,
   PRENOM_AD            long varchar                   not null,
   PROFESSION           long varchar                   null,
   ADRESSE_AD           long varchar                   null,
   TEL                  long varchar                   null,
   ADRESSE_TR           long varchar                   null,
   TEL_TR               long varchar                   null,
   HORAIRES             long varchar                   null,
   DECEDE               smallint                       null default false,
   ENSEIGNANT           smallint                       null,
   DATE_DEBUT           date                           null,
   DATE_FIN             date                           null,
   constraint PK_ADULTE primary key (ID_ADULTE)
);

/*==============================================================*/
/* Index : ADULTE_PK                                            */
/*==============================================================*/
create unique index ADULTE_PK on ADULTE (
ID_ADULTE ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_13_FK                                    */
/*==============================================================*/
create index ASSOCIATION_13_FK on ADULTE (
ID_UTILISATEUR ASC
);

/*==============================================================*/
/* Table : ASSURANCE_SCOLAIRE                                   */
/*==============================================================*/
create table ASSURANCE_SCOLAIRE 
(
   ID_ASSURANCE         integer                        not null,
   NOM_ASSURANCE        long varchar                   not null,
   ADRESSE_ASSURANCE    long varchar                   not null,
   TEL_ASSURANCE        long varchar                   null,
   constraint PK_ASSURANCE_SCOLAIRE primary key (ID_ASSURANCE)
);

/*==============================================================*/
/* Index : ASSURANCE_SCOLAIRE_PK                                */
/*==============================================================*/
create unique index ASSURANCE_SCOLAIRE_PK on ASSURANCE_SCOLAIRE (
ID_ASSURANCE ASC
);

/*==============================================================*/
/* Table : BENEFICIER_DE                                        */
/*==============================================================*/
create table BENEFICIER_DE 
(
   ID_ENFANT            integer                        not null,
   ID_RASED             integer                        not null,
   constraint PK_BENEFICIER_DE primary key (ID_ENFANT, ID_RASED)
);

/*==============================================================*/
/* Index : BENEFICIER_DE_PK                                     */
/*==============================================================*/
create unique index BENEFICIER_DE_PK on BENEFICIER_DE (
ID_ENFANT ASC,
ID_RASED ASC
);

/*==============================================================*/
/* Index : BENEFICIER_DE2_FK                                    */
/*==============================================================*/
create index BENEFICIER_DE2_FK on BENEFICIER_DE (
ID_ENFANT ASC
);

/*==============================================================*/
/* Index : BENEFICIER_DE_FK                                     */
/*==============================================================*/
create index BENEFICIER_DE_FK on BENEFICIER_DE (
ID_RASED ASC
);

/*==============================================================*/
/* Table : CENTRE_SECURITE_SOCIALE                              */
/*==============================================================*/
create table CENTRE_SECURITE_SOCIALE 
(
   ID_CENTRE            integer                        not null,
   NOM_CENTRE           long varchar                   not null,
   ADRESSE_CENTRE       long varchar                   not null,
   constraint PK_CENTRE_SECURITE_SOCIALE primary key (ID_CENTRE)
);

/*==============================================================*/
/* Index : CENTRE_SECURITE_SOCIALE_PK                           */
/*==============================================================*/
create unique index CENTRE_SECURITE_SOCIALE_PK on CENTRE_SECURITE_SOCIALE (
ID_CENTRE ASC
);

/*==============================================================*/
/* Table : CLASSE                                               */
/*==============================================================*/
create table CLASSE 
(
   ID_CLASSE            integer                        not null,
   NOM_CLASSE           long varchar                   not null,
   SALLE                long varchar                   not null,
   NIVEAU               long varchar                   not null,
   ANNEE                integer                        not null,
   constraint PK_CLASSE primary key (ID_CLASSE)
);

/*==============================================================*/
/* Index : CLASSE_PK                                            */
/*==============================================================*/
create unique index CLASSE_PK on CLASSE (
ID_CLASSE ASC
);

/*==============================================================*/
/* Table : COMPTE                                               */
/*==============================================================*/
create table COMPTE 
(
   ID_UTILISATEUR       long varchar                   not null,
   ID_ADULTE            integer                        not null,
   MDP                  long varchar                   null,
   MODIF                smallint                       null,
   constraint PK_COMPTE primary key (ID_UTILISATEUR)
);

/*==============================================================*/
/* Index : COMPTE_PK                                            */
/*==============================================================*/
create unique index COMPTE_PK on COMPTE (
ID_UTILISATEUR ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_14_FK                                    */
/*==============================================================*/
create index ASSOCIATION_14_FK on COMPTE (
ID_ADULTE ASC
);

/*==============================================================*/
/* Table : ENFANT                                               */
/*==============================================================*/
create table ENFANT 
(
   ID_ENFANT            integer                        not null,
   ID_NATIONALITE       integer                        not null,
   ID_ASSURANCE         integer                        not null,
   ID_VILLE             integer                        not null,
   ID_CENTRE            integer                        not null,
   ID_MEDECIN           integer                        null,
   PHOTO                long varchar                   null,
   NOM_ENFANT           long varchar                   not null,
   PRENOM_ENFANT        long varchar                   not null,
   DATE_NAISSANCE_ENFANT date                           null,
   ADRESSE_ENFANT       long varchar                   null,
   LIEU_NAISSANCE       long varchar                   null,
   DATE_INSCRIPTION     date                           null,
   DATE_RADIATION       date                           null,
   PORT_LUNETTES        smallint                       null,
   SITUATION_FAMILIALE  long varchar                   null,
   INFOS_MEDICALES      long varchar                   null,
   DATE_VACCIN          date                           null,
   PAI                  smallint                       null,
   AVS                  integer                        null,
   EVS                  integer                        null,
   constraint PK_ENFANT primary key (ID_ENFANT)
);

/*==============================================================*/
/* Index : ENFANT_PK                                            */
/*==============================================================*/
create unique index ENFANT_PK on ENFANT (
ID_ENFANT ASC
);

/*==============================================================*/
/* Index : AVOIR_POUR_FK                                        */
/*==============================================================*/
create index AVOIR_POUR_FK on ENFANT (
ID_NATIONALITE ASC
);

/*==============================================================*/
/* Index : ETRE_ASSURE_FK                                       */
/*==============================================================*/
create index ETRE_ASSURE_FK on ENFANT (
ID_ASSURANCE ASC
);

/*==============================================================*/
/* Index : ETRE_NE_A_FK                                         */
/*==============================================================*/
create index ETRE_NE_A_FK on ENFANT (
ID_VILLE ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_11_FK                                    */
/*==============================================================*/
create index ASSOCIATION_11_FK on ENFANT (
ID_CENTRE ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_12_FK                                    */
/*==============================================================*/
create index ASSOCIATION_12_FK on ENFANT (
ID_MEDECIN ASC
);

/*==============================================================*/
/* Table : ETRE_AFFECTE                                         */
/*==============================================================*/
create table ETRE_AFFECTE 
(
   ID_ADULTE            integer                        not null,
   ID_CLASSE            integer                        not null,
   constraint PK_ETRE_AFFECTE primary key (ID_ADULTE, ID_CLASSE)
);

/*==============================================================*/
/* Index : ETRE_AFFECTE_PK                                      */
/*==============================================================*/
create unique index ETRE_AFFECTE_PK on ETRE_AFFECTE (
ID_ADULTE ASC,
ID_CLASSE ASC
);

/*==============================================================*/
/* Index : ETRE_AFFECTE2_FK                                     */
/*==============================================================*/
create index ETRE_AFFECTE2_FK on ETRE_AFFECTE (
ID_ADULTE ASC
);

/*==============================================================*/
/* Index : ETRE_AFFECTE_FK                                      */
/*==============================================================*/
create index ETRE_AFFECTE_FK on ETRE_AFFECTE (
ID_CLASSE ASC
);

/*==============================================================*/
/* Table : ETRE_DANS                                            */
/*==============================================================*/
create table ETRE_DANS 
(
   ID_ENFANT            integer                        not null,
   ID_CLASSE            integer                        not null,
   NIVEAU_ELEVE         long varchar                   not null,
   REDOUBLANT           smallint                       not null,
   constraint PK_ETRE_DANS primary key (ID_ENFANT, ID_CLASSE)
);

/*==============================================================*/
/* Index : ETRE_DANS_PK                                         */
/*==============================================================*/
create unique index ETRE_DANS_PK on ETRE_DANS (
ID_ENFANT ASC,
ID_CLASSE ASC
);

/*==============================================================*/
/* Index : ETRE_DANS2_FK                                        */
/*==============================================================*/
create index ETRE_DANS2_FK on ETRE_DANS (
ID_ENFANT ASC
);

/*==============================================================*/
/* Index : ETRE_DANS_FK                                         */
/*==============================================================*/
create index ETRE_DANS_FK on ETRE_DANS (
ID_CLASSE ASC
);

/*==============================================================*/
/* Table : ETRE_RESPONSABLE                                     */
/*==============================================================*/
create table ETRE_RESPONSABLE 
(
   ID_ENFANT            integer                        not null,
   ID_ADULTE            integer                        not null,
   LIEN                 long varchar                   not null,
   AUTORISER            smallint                       not null,
   constraint PK_ETRE_RESPONSABLE primary key (ID_ENFANT, ID_ADULTE)
);

/*==============================================================*/
/* Index : ETRE_RESPONSABLE_PK                                  */
/*==============================================================*/
create unique index ETRE_RESPONSABLE_PK on ETRE_RESPONSABLE (
ID_ENFANT ASC,
ID_ADULTE ASC
);

/*==============================================================*/
/* Index : ETRE_RESPONSABLE2_FK                                 */
/*==============================================================*/
create index ETRE_RESPONSABLE2_FK on ETRE_RESPONSABLE (
ID_ENFANT ASC
);

/*==============================================================*/
/* Index : ETRE_RESPONSABLE_FK                                  */
/*==============================================================*/
create index ETRE_RESPONSABLE_FK on ETRE_RESPONSABLE (
ID_ADULTE ASC
);

/*==============================================================*/
/* Table : LOG                                                  */
/*==============================================================*/
create table LOG 
(
   ID                   integer                        not null,
   ID_UTILISATEUR       long varchar                   not null,
   ID_ADULTE            integer                        null,
   ID_ENFANT            integer                        null,
   ID_CLASSE            integer                        null,
   HORODATAGE           timestamp                      not null,
   constraint PK_LOG primary key (ID)
);

/*==============================================================*/
/* Index : LOG_PK                                               */
/*==============================================================*/
create unique index LOG_PK on LOG (
ID ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_15_FK                                    */
/*==============================================================*/
create index ASSOCIATION_15_FK on LOG (
ID_UTILISATEUR ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_16_FK                                    */
/*==============================================================*/
create index ASSOCIATION_16_FK on LOG (
ID_ADULTE ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_17_FK                                    */
/*==============================================================*/
create index ASSOCIATION_17_FK on LOG (
ID_ENFANT ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_18_FK                                    */
/*==============================================================*/
create index ASSOCIATION_18_FK on LOG (
ID_CLASSE ASC
);

/*==============================================================*/
/* Table : MEDECIN_TRAITANT                                     */
/*==============================================================*/
create table MEDECIN_TRAITANT 
(
   ID_MEDECIN           integer                        not null,
   NOM_MEDECIN          long varchar                   not null,
   PRENOM_MEDECIN       long varchar                   not null,
   ADRESSE_MEDECIN      long varchar                   null,
   constraint PK_MEDECIN_TRAITANT primary key (ID_MEDECIN)
);

/*==============================================================*/
/* Index : MEDECIN_TRAITANT_PK                                  */
/*==============================================================*/
create unique index MEDECIN_TRAITANT_PK on MEDECIN_TRAITANT (
ID_MEDECIN ASC
);

/*==============================================================*/
/* Table : NATIONALITE                                          */
/*==============================================================*/
create table NATIONALITE 
(
   ID_NATIONALITE       integer                        not null,
   PAYS                 long varchar                   not null,
   constraint PK_NATIONALITE primary key (ID_NATIONALITE)
);

/*==============================================================*/
/* Index : NATIONALITE_PK                                       */
/*==============================================================*/
create unique index NATIONALITE_PK on NATIONALITE (
ID_NATIONALITE ASC
);

/*==============================================================*/
/* Table : RASED                                                */
/*==============================================================*/
create table RASED 
(
   ID_RASED             integer                        not null,
   LIBELLE_RASED        long varchar                   not null,
   constraint PK_RASED primary key (ID_RASED)
);

/*==============================================================*/
/* Index : RASED_PK                                             */
/*==============================================================*/
create unique index RASED_PK on RASED (
ID_RASED ASC
);

/*==============================================================*/
/* Table : VILLE                                                */
/*==============================================================*/
create table VILLE 
(
   ID_VILLE             integer                        not null,
   NOM_VILLE            long varchar                   not null,
   constraint PK_VILLE primary key (ID_VILLE)
);

/*==============================================================*/
/* Index : VILLE_PK                                             */
/*==============================================================*/
create unique index VILLE_PK on VILLE (
ID_VILLE ASC
);

alter table ADULTE
   add constraint FK_ADULTE_ASSOCIATI_COMPTE foreign key (ID_UTILISATEUR)
      references COMPTE (ID_UTILISATEUR)
      on update restrict
      on delete restrict;

alter table BENEFICIER_DE
   add constraint FK_BENEFICI_BENEFICIE_RASED foreign key (ID_RASED)
      references RASED (ID_RASED)
      on update restrict
      on delete restrict;

alter table BENEFICIER_DE
   add constraint FK_BENEFICI_BENEFICIE_ENFANT foreign key (ID_ENFANT)
      references ENFANT (ID_ENFANT)
      on update restrict
      on delete restrict;

alter table COMPTE
   add constraint FK_COMPTE_ASSOCIATI_ADULTE foreign key (ID_ADULTE)
      references ADULTE (ID_ADULTE)
      on update restrict
      on delete restrict;

alter table ENFANT
   add constraint FK_ENFANT_ASSOCIATI_CENTRE_S foreign key (ID_CENTRE)
      references CENTRE_SECURITE_SOCIALE (ID_CENTRE)
      on update restrict
      on delete restrict;

alter table ENFANT
   add constraint FK_ENFANT_ASSOCIATI_MEDECIN_ foreign key (ID_MEDECIN)
      references MEDECIN_TRAITANT (ID_MEDECIN)
      on update restrict
      on delete restrict;

alter table ENFANT
   add constraint FK_ENFANT_AVOIR_POU_NATIONAL foreign key (ID_NATIONALITE)
      references NATIONALITE (ID_NATIONALITE)
      on update restrict
      on delete restrict;

alter table ENFANT
   add constraint FK_ENFANT_ETRE_ASSU_ASSURANC foreign key (ID_ASSURANCE)
      references ASSURANCE_SCOLAIRE (ID_ASSURANCE)
      on update restrict
      on delete restrict;

alter table ENFANT
   add constraint FK_ENFANT_ETRE_NE_A_VILLE foreign key (ID_VILLE)
      references VILLE (ID_VILLE)
      on update restrict
      on delete restrict;

alter table ETRE_AFFECTE
   add constraint FK_ETRE_AFF_ETRE_AFFE_CLASSE foreign key (ID_CLASSE)
      references CLASSE (ID_CLASSE)
      on update restrict
      on delete restrict;

alter table ETRE_AFFECTE
   add constraint FK_ETRE_AFF_ETRE_AFFE_ADULTE foreign key (ID_ADULTE)
      references ADULTE (ID_ADULTE)
      on update restrict
      on delete restrict;

alter table ETRE_DANS
   add constraint FK_ETRE_DAN_ETRE_DANS_CLASSE foreign key (ID_CLASSE)
      references CLASSE (ID_CLASSE)
      on update restrict
      on delete restrict;

alter table ETRE_DANS
   add constraint FK_ETRE_DAN_ETRE_DANS_ENFANT foreign key (ID_ENFANT)
      references ENFANT (ID_ENFANT)
      on update restrict
      on delete restrict;

alter table ETRE_RESPONSABLE
   add constraint FK_ETRE_RES_ETRE_RESP_ADULTE foreign key (ID_ADULTE)
      references ADULTE (ID_ADULTE)
      on update restrict
      on delete restrict;

alter table ETRE_RESPONSABLE
   add constraint FK_ETRE_RES_ETRE_RESP_ENFANT foreign key (ID_ENFANT)
      references ENFANT (ID_ENFANT)
      on update restrict
      on delete restrict;

alter table LOG
   add constraint FK_LOG_ASSOCIATI_COMPTE foreign key (ID_UTILISATEUR)
      references COMPTE (ID_UTILISATEUR)
      on update restrict
      on delete restrict;

alter table LOG
   add constraint FK_LOG_ASSOCIATI_ADULTE foreign key (ID_ADULTE)
      references ADULTE (ID_ADULTE)
      on update restrict
      on delete restrict;

alter table LOG
   add constraint FK_LOG_ASSOCIATI_ENFANT foreign key (ID_ENFANT)
      references ENFANT (ID_ENFANT)
      on update restrict
      on delete restrict;

alter table LOG
   add constraint FK_LOG_ASSOCIATI_CLASSE foreign key (ID_CLASSE)
      references CLASSE (ID_CLASSE)
      on update restrict
      on delete restrict;

