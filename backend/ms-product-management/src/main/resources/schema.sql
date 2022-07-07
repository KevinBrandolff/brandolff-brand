create table if not exists category_entity (
        id int not null primary key AUTO_INCREMENT,
        category varchar(255) unique not null,
)

create table if not exists product_entity (
        id int not null primary key AUTO_INCREMENT,
        description varchar(255) not null,
        name varchar(255) unique not null,
        price double not null,
        size enum('SMALL', 'MEDIUM', 'LARGE', 'XLARGE', 'XXLARGE'),
)

create table if not exists product_entity_category (
        product_id integer not null,
        category_id integer not null,
        FOREIGN KEY (product_id) REFERENCES product_entity(id),
        FOREIGN KEY (category_id) REFERENCES category_entity(id)
)

create table if not exists product_image_entity (
        id int not null primary key AUTO_INCREMENT,
        image_url varchar(255),
        product_id integer,
        FOREIGN KEY (product_id) REFERENCES product_entity(id)
)