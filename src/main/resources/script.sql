--Question_form table
create table question_form (
   id varchar(50) not null,
   type varchar(50) not null,
   active smallint not null
);

alter table question_form add
constraint question_form_id_pk primary key(id);

insert into question_form (id,type,active) values('1','PRE_COMPETITION',1);
insert into question_form (id,type,active) values('2','POST_COMPETITION',1);


--Question table
create table question (
   id varchar(50) not null,
   question_form_id varchar(50) not null,
   name varchar(500) not null,
   sentence varchar(500) not null
);

alter table question add
constraint question_id_pk primary key(id);

alter table question add
constraint question_form_id_fk foreign key(question_form_id)
references question(id);

create index question_form_id_i on question(question_form_id);

insert into question (id,question_form_id,name,sentence) values('1','1','Happiness','Between 1-10, what was you happiness state before start the training?');
insert into question (id,question_form_id,name,sentence) values('2','1','Motivation','Between 1-10, what was you motivation state before start the training?');
insert into question (id,question_form_id,name,sentence) values('3','1','Attention','Between 1-10, what was you attention state before start the training?');

insert into question (id,question_form_id,name,sentence) values('4','2','Happiness','Between 1-10, what was you happiness state after end the training?');
insert into question (id,question_form_id,name,sentence) values('5','2','Motivation','Between 1-10, what was you motivation state after end  the training?');
insert into question (id,question_form_id,name,sentence) values('6','2','Attention','Between 1-10, what was you attention state after end the training?');
insert into question (id,question_form_id,name,sentence) values('7','2','Performance','Between 1-10, what was you performance in the training?');