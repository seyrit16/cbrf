INSERT INTO role (id, role)
VALUES
        (1, 'ADMIN'),
        (2, 'USER');

INSERT INTO cbrf_user (login, password, role_id, is_active, is_deleted)
VALUES
        ('matveika', '$2a$06$xEXq0qDW7nnRaQAHF5uPKumhyVuK03w6b5C7s0IKtDCGoeepzoRgK', 1, true, false),
        ('romka', '$2a$06$omB6AfPOZ5gC./z8YDqaWen6XJqJ1pheA7lGRmVNsIxM58jHKXeHe', 2, true, false);