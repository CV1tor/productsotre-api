CREATE TABLE product (
    id UUID PRIMARY KEY DEFAULT(UUID()),
    name VARCHAR(150) NOT NULL,
    description VARCHAR(250),
    price INT UNSIGNED NOT NULL,
    quantity INT UNSIGNED NOT NULL,
    category VARCHAR(50) NOT NULL
)