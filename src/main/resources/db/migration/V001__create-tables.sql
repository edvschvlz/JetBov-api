create table animals
(
    earring varchar(255)   not null primary key,
    weigth  decimal(10, 2) not null
);

create table areas
(
    name        varchar(255)   not null primary key,
    maxQuantity int            not null,
    gmd         decimal(10, 2) not null
);

create table movements
(
    id         bigint       not null auto_increment primary key,
    areas_name varchar(255) not null,
    days       int          not null,

    constraint fk_movements_areas
        foreign key (areas_name)
            references areas (name)
);

create table movements_has_animals
(
    movements_id    bigint       not null,
    animals_earring varchar(255) not null,
    primary key (movements_id, animals_earring),

    constraint fk_movements_has_animals_movements
        foreign key (movements_id)
            references movements (id),
    constraint fk_movements_has_animals_animals
        foreign key (animals_earring)
            references animals (earring)
);