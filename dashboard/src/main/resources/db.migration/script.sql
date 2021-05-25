-- Training table
create table training_counter (
   id varchar(50) not null,
   total integer not null,
   trainings_ids jsonb not null
);

insert into training_counter(id, total, trainings_ids) values('1', 0, '{"trainingIds":["2","4"]}');
