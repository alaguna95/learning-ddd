create schema input_data;

--Question_form table
create table input_data.question_form (
   id varchar(50) not null,
   type varchar(50) not null,
   active smallint not null
);

alter table input_data.question_form add
constraint question_form_id_pk primary key(id);

insert into input_data.question_form (id,type,active) values('1','PRE_COMPETITION',1);
insert into input_data.question_form (id,type,active) values('2','POST_COMPETITION',1);


--Question table
create table input_data.question (
   id varchar(50) not null,
   question_form_id varchar(50) not null,
   name varchar(500) not null,
   sentence varchar(500) not null
);

alter table input_data.question add
constraint question_id_pk primary key(id);

alter table input_data.question add
constraint question_form_id_fk foreign key(question_form_id)
references question(id);

create index question_form_id_i on input_data.question(question_form_id);

insert into input_data.question (id,question_form_id,name,sentence) values('1','1','Happiness','Between 1-10, what was you happiness state before start the training?');
insert into input_data.question (id,question_form_id,name,sentence) values('2','1','Motivation','Between 1-10, what was you motivation state before start the training?');
insert into input_data.question (id,question_form_id,name,sentence) values('3','1','Attention','Between 1-10, what was you attention state before start the training?');

insert into input_data.question (id,question_form_id,name,sentence) values('4','2','Happiness','Between 1-10, what was you happiness state after end the training?');
insert into input_data.question (id,question_form_id,name,sentence) values('5','2','Motivation','Between 1-10, what was you motivation state after end  the training?');
insert into input_data.question (id,question_form_id,name,sentence) values('6','2','Attention','Between 1-10, what was you attention state after end the training?');
insert into input_data.question (id,question_form_id,name,sentence) values('7','2','Performance','Between 1-10, what was you performance in the training?');


-- Training table
create table input_data.training (
   id varchar(50) not null,
   start timestamp not null,
   finish timestamp not null
);

alter table input_data.training add
constraint training_id_pk primary key(id);


-- Answer Form
create table input_data.answer_form (
    id varchar(50) not null,
    training_id varchar(50) not null,
    question_form_id varchar(50) not null,
    type varchar(50) not null
);

alter table input_data.answer_form add
constraint answer_form_id_pk primary key(id);

alter table input_data.answer_form add
constraint answer_form_question_form_id_fk foreign key(question_form_id)
references question_form(id);

alter table input_data.answer_form add
constraint answer_form_training_id_fk foreign key(training_id)
references training(id);

create index answer_form_question_form_id_i on input_data.answer_form(question_form_id);
create index answer_form_training_id_i on input_data.answer_form(training_id);


create table input_data.answer(
    id varchar(50) not null,
    answer_form_id varchar(50) not null,
    question_id varchar(50) not null,
    value smallint not null
);

alter table input_data.answer add
constraint answer_id_pk primary key(id);


alter table input_data.answer add
constraint answer_question_id_fk foreign key(question_id)
references question(id);

alter table input_data.answer add
constraint answer_answer_form_id_fk foreign key(answer_form_id)
references answer_form(id);

create index answer_question_id_i on input_data.answer(question_id);
create index answer_answer_form_id_i on input_data.answer(answer_form_id);