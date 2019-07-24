CREATE TABLE recordings (
    filename varchar(1000) not null,
    date date not null,
    tempo int,
    beat varchar(50),
    style varchar(1000),
    primary key (filename)
)
