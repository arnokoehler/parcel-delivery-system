create table receipient
(
    id                 uuid primary key,
    name               text           not null,
    street             text           not null,
    house_number       numeric(14, 2) not null,
    postalode          text           not null,
    city               text           not null
);

create table parcel_table
(
    id                 uuid primary key,
    receipient_id      uuid           not null references receipient (id) on delete cascade,
    weight             numeric(11, 2) not null,
    value              numeric(11, 2) not null
);