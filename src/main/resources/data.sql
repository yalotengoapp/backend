INSERT INTO users (username, email, password, role, fruit) VALUES
('Mufasa', 'mufasa@lionking.com', 'Forest#54321@', 'ADMIN', '3'),
('Pumba', 'pumba@lionking.com', 'Forest#54321@', 'USER', '3'),
('Zazu', 'zazuuu@lionking.com', 'Forest#54321@', 'USER', '3'),
('Scar', 'scar@badass.com', 'Forest#54321@', 'USER', '3');

INSERT INTO items (id, user_id, title, description, item_condition, type, fruits_required, img_url) VALUES
('1', '2', 'Pressure Washer', 'Powerful backyard cleaning water jet.', 'new', 'garden', '3', 'https://m.media-amazon.com/images/I/51lEUPA554L._AC_SL1000_.jpg'),
('2', '3', 'Megaphone', 'For important announcements and reminding everyone of the rules. Loud enough to be heard across the savannah.', 'used', 'electronics', '2', 'https://m.media-amazon.com/images/I/71KVrpNnqqL._AC_SL1500_.jpg'),
('3', '4', 'Spyglass', 'Keep an eye on everything… and everyone. Ideal for strategic planning and subtle scheming.', 'used', 'electronics', '5', 'https://m.media-amazon.com/images/I/71+kYPB-bcL._AC_SL1500_.jpg');