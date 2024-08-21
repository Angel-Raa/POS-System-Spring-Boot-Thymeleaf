CREATE TABLE categories (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL UNIQUE,
    tel VARCHAR(12) NOT NULL,
    address VARCHAR(80) NOT NULL
);
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    category_id INTEGER NOT NULL REFERENCES Category(category_id),
    name VARCHAR(40) NOT NULL,
    price DECIMAL(8, 2) NOT NULL CHECK (price > 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    description TEXT
);
CREATE TABLE purchases (
    purchase_id SERIAL PRIMARY KEY,
    fk_customer_id INTEGER NOT NULL REFERENCES Customer(customer_id),
    fk_product_id INTEGER NOT NULL REFERENCES Product(product_id),
    quantity INTEGER NOT NULL CHECK (quantity >= 0),
    price_per_unit DECIMAL(8, 2) NOT NULL CHECK (price_per_unit > 0),
    total_price DECIMAL(10, 2) NOT NULL CHECK (total_price > 0),
    shipping_address VARCHAR(100) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    note TEXT NOT NULL,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
INSERT INTO categories (name, description)
VALUES (
        'Electrodomésticos',
        'Categoría para todos los aparatos eléctricos del hogar'
    ),
    (
        'Tecnología',
        'Artículos relacionados con la informática y la tecnología'
    ),
    (
        'Muebles',
        'Categoría de muebles para el hogar y oficina'
    ),
    (
        'Ropa',
        'Vestimenta para hombres, mujeres y niños'
    ),
    (
        'Calzado',
        'Todo tipo de calzado, desde deportivos hasta elegantes'
    ),
    (
        'Hogar',
        'Artículos para el mantenimiento y decoración del hogar'
    ),
    (
        'Juguetes',
        'Juguetes y juegos para todas las edades'
    ),
    (
        'Libros',
        'Categoría para libros y material de lectura'
    ),
    ('Alimentos', 'Productos alimenticios y bebidas'),
    ('Deportes', 'Equipos y ropa deportiva');
INSERT INTO customers (first_name, last_name, email, tel, address)
VALUES (
        'Juan',
        'Pérez',
        'juan.perez@example.com',
        '8095551234',
        'Calle 1, Santo Domingo'
    ),
    (
        'María',
        'Gómez',
        'maria.gomez@example.com',
        '8095555678',
        'Calle 2, Santiago'
    ),
    (
        'Pedro',
        'Martínez',
        'pedro.martinez@example.com',
        '8095558765',
        'Calle 3, La Vega'
    ),
    (
        'Ana',
        'Rodríguez',
        'ana.rodrniguez@example.com',
        '8095554321',
        'Calle 4, Punta Cana'
    ),
    (
        'Luis',
        'Sánchez',
        'luis.sanchez@example.com',
        '8095556789',
        'Calle 5, Puerto Plata'
    ),
    (
        'Carmen',
        'López',
        'carmen.lopez@example.com',
        '8095551111',
        'Calle 6, La Romana'
    ),
    (
        'José',
        'Fernández',
        'jose.fernandez@example.com',
        '8095552222',
        'Calle 7, Higüey'
    ),
    (
        'Laura',
        'García',
        'laura.garcia@example.com',
        '8095553333',
        'Calle 8, Moca'
    ),
    (
        'Manuel',
        'Ramírez',
        'manuel.ramirez@example.com',
        '8095554444',
        'Calle 9, San Cristóbal'
    ),
    (
        'Carolina',
        'Reyes',
        'carolina.reyes@example.com',
        '8095555555',
        'Calle 10, Baní'
    );
INSERT INTO products (category_id, name, price, stock, description)
VALUES (
        1,
        'Lavadora',
        12000.00,
        15,
        'Lavadora automática de alta eficiencia'
    ),
    (
        1,
        'Refrigerador',
        18000.00,
        10,
        'Refrigerador con congelador dual'
    ),
    (
        2,
        'Laptop',
        35000.00,
        8,
        'Laptop de última generación con 16GB RAM'
    ),
    (
        2,
        'Smartphone',
        20000.00,
        20,
        'Smartphone con pantalla AMOLED y 128GB de almacenamiento'
    ),
    (
        3,
        'Sofá',
        15000.00,
        5,
        'Sofá de 3 plazas cómodo y moderno'
    ),
    (
        4,
        'Camiseta',
        500.00,
        50,
        'Camiseta de algodón 100%'
    ),
    (
        5,
        'Zapatillas Deportivas',
        2500.00,
        25,
        'Zapatillas para correr de alta resistencia'
    ),
    (
        6,
        'Lámpara de Mesa',
        1200.00,
        30,
        'Lámpara de mesa con diseño moderno'
    ),
    (
        7,
        'Muñeca Barbie',
        800.00,
        40,
        'Muñeca Barbie con vestuario de moda'
    ),
    (
        8,
        'Novela "Cien años de soledad"',
        600.00,
        12,
        'Edición especial de la novela clásica de Gabriel García Márquez'
    );
INSERT INTO purchases (
        fk_customer_id,
        fk_product_id,
        quantity,
        price_per_unit,
        total_price,
        shipping_address,
        payment_method,
        note
    )
VALUES (
        1,
        1,
        1,
        12000.00,
        12000.00,
        'Calle 1, Santo Domingo',
        'Tarjeta de Crédito',
        'Entregar entre 9 AM y 5 PM'
    ),
    (
        2,
        3,
        1,
        35000.00,
        35000.00,
        'Calle 2, Santiago',
        'Efectivo',
        'Cliente requiere instalación del producto'
    ),
    (
        3,
        4,
        2,
        20000.00,
        40000.00,
        'Calle 3, La Vega',
        'Tarjeta de Crédito',
        'Cliente quiere la factura en digital'
    ),
    (
        4,
        5,
        1,
        15000.00,
        15000.00,
        'Calle 4, Punta Cana',
        'PayPal',
        'Entregar después de las 2 PM'
    ),
    (
        5,
        2,
        1,
        18000.00,
        18000.00,
        'Calle 5, Puerto Plata',
        'Transferencia Bancaria',
        'Confirmar entrega 30 minutos antes'
    ),
    (
        6,
        6,
        3,
        500.00,
        1500.00,
        'Calle 6, La Romana',
        'Tarjeta de Crédito',
        'Paquete frágil, manejar con cuidado'
    ),
    (
        7,
        7,
        1,
        2500.00,
        2500.00,
        'Calle 7, Higüey',
        'Efectivo',
        'Dejar en la puerta principal'
    ),
    (
        8,
        8,
        2,
        1200.00,
        2400.00,
        'Calle 8, Moca',
        'PayPal',
        'Llamar antes de entregar'
    ),
    (
        9,
        9,
        1,
        800.00,
        800.00,
        'Calle 9, San Cristóbal',
        'Tarjeta de Crédito',
        'Entregar por la tarde'
    ),
    (
        10,
        10,
        2,
        600.00,
        1200.00,
        'Calle 10, Baní',
        'Transferencia Bancaria',
        'Incluye envoltura de regalo'
    );