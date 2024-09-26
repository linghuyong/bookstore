create table Author
(
    id   INTEGER
        constraint author_pk
            primary key autoincrement,
    name varchar(255) not null
);

create table Book
(
    id             INTEGER
        constraint book_pk
            primary key autoincrement,
    title          varchar(255)  not null,
    author_id      bigint        not null,
    description    varchar(1024) not null,
    enable         bool          not null,
    price          double        not null,
    stock          integer       not null,
    classification integer       not null
);

create table OrderT
(
    id           INTEGER
        constraint order_pk
            primary key autoincrement,
    book_id      bigint    not null,
    user_id      bigint    not null,
    price        double    not null,
    status       integer   not null,
    created_time timestamp not null,
    updated_time timestamp not null
);

create index OrderT_user_id_book_id_index
    on OrderT (user_id, book_id);

create table User
(
    id       INTEGER
        constraint user_pk
            primary key autoincrement,
    username varchar(255)
        unique,
    email    varchar(255)
        unique,
    password varchar(255) not null,
    salt     varchar(255) not null,
    enable   bool         not null
);

