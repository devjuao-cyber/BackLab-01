
-- Tabela de produtos
CREATE TABLE IF NOT EXISTS products (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        name TEXT NOT NULL,
                                        description TEXT,
                                        price_cents INTEGER NOT NULL CHECK (price_cents >= 0),
    stock INTEGER NOT NULL DEFAULT 0 CHECK (stock >= 0)
    );

-- Tabela de itens do carrinho
CREATE TABLE IF NOT EXISTS cart_items (
                                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                                          product_id INTEGER NOT NULL,
                                          quantity INTEGER NOT NULL CHECK (quantity > 0),

    FOREIGN KEY (product_id)
    REFERENCES products(id),

    UNIQUE(product_id)
    );

-- Tabela de pedidos
CREATE TABLE IF NOT EXISTS orders (
                                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                                      total_cents INTEGER NOT NULL,
                                      status TEXT NOT NULL DEFAULT 'PENDING',
                                      created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Produtos que pertencem a cada pedido
CREATE TABLE IF NOT EXISTS order_items (
                                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                                           order_id INTEGER NOT NULL,
                                           product_id INTEGER NOT NULL,
                                           quantity INTEGER NOT NULL,
                                           unit_price_cents INTEGER NOT NULL,

                                           FOREIGN KEY (order_id)
    REFERENCES orders(id),

    FOREIGN KEY (product_id)
    REFERENCES products(id)
    );
