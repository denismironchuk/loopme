create sequence group_seq;

create table users(
    id integer default group_seq.nextval not null,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    primary key(id)
);

create table apps(
    id integer default group_seq.nextval not null,
    name varchar(255) not null,
    type varchar(255) not null,
    user integer,
    primary key(id),

    foreign key (user) references users(id)
);

create table apps_content_type(
    app_id integer not null,
    content_type varchar(255) not null,
    primary key(app_Id, content_Type),

    foreign key (app_id) references apps(id)
);