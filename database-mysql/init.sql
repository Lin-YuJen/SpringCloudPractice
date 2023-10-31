create table payment
(
    id     bigint       auto_increment,
    serial varchar(100) null,
    constraint payment_pk
        primary key (id)
)
;

# drop table payment
# ;

insert into payment(serial) values('ABCC');