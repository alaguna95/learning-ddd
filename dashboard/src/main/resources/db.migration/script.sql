create schema dashboard;

-- Training table
create table dashboard.training_counter (
   id varchar(50) not null,
   total integer not null,
   trainings_ids JSON not null
);

insert into dashboard.training_counter(id, total, trainings_ids) values('1', 0, '{"trainingIds":["2","4"]}');
