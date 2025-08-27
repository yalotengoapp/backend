INSERT INTO users (username, email, password, role, fruit) VALUES
('Mufasa', 'mufasa@lionking.com', '$2a$10$KKp2sVIIzbVqJddSN5PPVe7b1oNFlw6Gt9yN1lmlMOTyK19wPvFOG', 'ROLE_ADMIN', '3'),
('Pumba', 'pumba@lionking.com', '$2a$10$KKp2sVIIzbVqJddSN5PPVe7b1oNFlw6Gt9yN1lmlMOTyK19wPvFOG', 'ROLE_USER', '3'),
('Zazu', 'zazuuu@lionking.com', '$2a$10$KKp2sVIIzbVqJddSN5PPVe7b1oNFlw6Gt9yN1lmlMOTyK19wPvFOG', 'ROLE_USER', '3'),
('Scar', 'scar@badass.com', '$2a$10$KKp2sVIIzbVqJddSN5PPVe7b1oNFlw6Gt9yN1lmlMOTyK19wPvFOG', 'ROLE_USER', '3');

INSERT INTO items (id, user_id, title, description, item_condition, type, fruits_required, img_url) VALUES
('1', '2', 'Pressure Washer', 'Powerful backyard cleaning water jet.', 'new', 'garden', '3', 'https://m.media-amazon.com/images/I/51lEUPA554L._AC_SL1000_.jpg'),
('2', '3', 'Megaphone', 'For important announcements and reminding everyone of the rules. Loud enough to be heard across the savannah.', 'used', 'electronics', '2', 'https://m.media-amazon.com/images/I/71KVrpNnqqL._AC_SL1500_.jpg'),
('3', '4', 'Spyglass', 'Keep an eye on everything… and everyone. Ideal for strategic planning and subtle scheming.', 'used', 'electronics', '5', 'https://m.media-amazon.com/images/I/71+kYPB-bcL._AC_SL1500_.jpg');