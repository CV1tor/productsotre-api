CREATE TABLE cart
(
    id UUID PRIMARY KEY DEFAULT (UUID())
);

CREATE TABLE cart_product
(
    cart_id    UUID NOT NULL,
    product_id UUID NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
)