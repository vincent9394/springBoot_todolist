create table todo_list(
    id int not null auto_increment primary key,
    text varchar(255) not null,
    done BOOLEAN
)