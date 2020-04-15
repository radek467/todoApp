drop table if exists TASK;
create table TASK(
    id int not null auto_increment primary key,
    description varchar(100) not null,
    done bit
)
