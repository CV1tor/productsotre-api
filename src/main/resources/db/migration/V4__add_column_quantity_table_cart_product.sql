ALTER TABLE cart_product
    ADD COLUMN id       UUID PRIMARY KEY DEFAULT (UUID()),
    ADD COLUMN quantity INT UNSIGNED NOT NULL;

