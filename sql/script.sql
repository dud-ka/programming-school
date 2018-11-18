create table exercise
(
  id          int auto_increment,
  title       varchar(255) null,
  description text         null,
  constraint exercise_id_uindex
  unique (id)
);

alter table exercise
  add primary key (id);

create table user_group
(
  id   int auto_increment,
  name varchar(255) null,
  constraint user_group_id_uindex
  unique (id)
);

alter table user_group
  add primary key (id);

create table users
(
  id            bigint auto_increment,
  username      varchar(255) null,
  email         varchar(255) null,
  password      varchar(245) null,
  user_group_id int          null,
  constraint users_id_uindex
  unique (id),
  constraint users_user_group_id_fk
  foreign key (user_group_id) references user_group (id)
);

alter table users
  add primary key (id);

create table solution
(
  id          int auto_increment,
  created     datetime null,
  updated     datetime null,
  description text     null,
  exercise_id int      null,
  user_id     bigint   null,
  constraint solution_id_uindex
  unique (id),
  constraint solution_exercise_id_fk
  foreign key (exercise_id) references exercise (id),
  constraint solution_users_id_fk
  foreign key (user_id) references users (id)
);

alter table solution
  add primary key (id);


