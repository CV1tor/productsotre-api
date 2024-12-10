CREATE TABLE transaction
(
    id        UUID PRIMARY KEY DEFAULT (UUID()),
    user_id UUID         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    cart_id   UUID         NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    value     INT UNSIGNED NOT NULL
);

CREATE TABLE wallet
(
    id      UUID PRIMARY KEY DEFAULT (UUID()),
    user_id UUID         NOT NULL,
    balance INT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

