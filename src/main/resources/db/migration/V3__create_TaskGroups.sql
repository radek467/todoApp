create table task_groups(
    id int not null auto_increment primary key,
    description varchar(100) not null,
    done bit
);

alter table TASK
    add column task_group_id int;
alter table TASK
    add foreign key (task_group_id) references task_groups(id);