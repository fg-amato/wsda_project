-- Inserimento di 10 utenti

INSERT INTO amato_wsda.user (id, username, password, user_role, enabled)
VALUES
  ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'admin', 'admin123', 'ROLE_ADMIN', true),
  ('0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'merchant1', 'merchant1', 'ROLE_MERCHANT', true),
  ('fba2e190-21d3-4c81-b262-536e78d0a3c9', 'merchant2', 'merchant2', 'ROLE_MERCHANT', true),
  ('a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', 'merchant3', 'merchant3', 'ROLE_MERCHANT', true),
  ('6dd8f88d-5a9e-4de1-8c7a-9d65a9b4f990', 'utente1', 'utente01', 'ROLE_CLASSIC_USER', true),
  ('30b75614-3bd9-4c7b-b07a-7a68e4c530d9', 'utente2', 'utente02', 'ROLE_CLASSIC_USER', true),
  ('1a731e8b-18fc-4374-942d-51b22b0be252', 'utente3', 'utente03', 'ROLE_CLASSIC_USER', true),
  ('e14d28e4-47e3-4d29-8bea-ef4df59d5ad2', 'utente4', 'utente04', 'ROLE_CLASSIC_USER', true),
  ('ba88ef3c-ee33-4f1f-a42f-7c1190ca9f50', 'utente5', 'utente05', 'ROLE_CLASSIC_USER', true),
  ('0b61ef27-98b5-44f9-bf5d-0f65a6a4d5a1', 'utente6', 'utente06', 'ROLE_CLASSIC_USER', true);
  
  
-- Inserimento di 6 carte

INSERT INTO amato_wsda.credit_card (id, card_number, balance, enabled, ref_card_holder)
VALUES
  ('d5a6e1ad-5f62-4b1a-8b5c-9a437a2e8fc1', '1111111111111111', 0.00, true ,'6dd8f88d-5a9e-4de1-8c7a-9d65a9b4f990'),
  ('2183edaf-9264-4d39-8e36-5b151f8998b4', '2222222222222222', 0.00, true,'30b75614-3bd9-4c7b-b07a-7a68e4c530d9'),
  ('b99bc9c7-4e6a-487f-8c0e-cc0a01d017b5', '3333333333333333', 0.00, true,'1a731e8b-18fc-4374-942d-51b22b0be252'),
  ('f4d7b7e5-7116-4ca0-9b20-2e3ee555aaba', '4444444444444444', 0.00, true,'e14d28e4-47e3-4d29-8bea-ef4df59d5ad2'),
  ('78664f4a-4d35-42e8-9332-9e9fb6ca2d19', '5555555555555555', 0.00, true,'ba88ef3c-ee33-4f1f-a42f-7c1190ca9f50'),
  ('a5664h9a-2d38-90e8-9312-1x3vb6za2c19', '6666666666666666', 0.00, true,'0b61ef27-98b5-44f9-bf5d-0f65a6a4d5a1');

-- Inserimento di 30 record transaction
INSERT INTO transaction (id, amount, ref_merchant, ref_credit_card, date_transaction, transaction_state)
VALUES
    ('0a6cf31e-4e0f-4a12-b16c-276c848de10c', 100.50, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('1dbf1e66-6d42-4b3b-9846-e2d31f00b439', 75.25, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', '78664f4a-4d35-42e8-9332-9e9fb6ca2d19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('2cbea26b-7f0a-4815-8750-4a7bdc74373e', -200.00, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('3f14970d-833f-4c1f-ae92-e746d7c89233', 50.75, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'b99bc9c7-4e6a-487f-8c0e-cc0a01d017b5', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('4d82a3ea-95e3-4b2d-8eb5-91a38f4de7f2', 300.00, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', 'd5a6e1ad-5f62-4b1a-8b5c-9a437a2e8fc1', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('548a26bc-a09b-4f47-8e07-cd6e2d9fe0a9', -45.60, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('6197684f-bb86-4b78-9c1a-d4773b1b92ce', 150.25, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', '78664f4a-4d35-42e8-9332-9e9fb6ca2d19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('7529f9ef-cb4c-4774-9ee8-193bb7c1f8d2', -80.30, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('8e5ea4f7-f5e2-4bcf-9e49-314ef4b6230d', 120.75, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', 'b99bc9c7-4e6a-487f-8c0e-cc0a01d017b5', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('9a86f246-98b7-48a4-b8db-c0f5a9843e44', -60.20, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'd5a6e1ad-5f62-4b1a-8b5c-9a437a2e8fc1', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('a1b3a289-ecbb-4e80-98ce-8a9d257c3c2f', 110.50, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('b2e7c4a3-3dc5-45e9-9b2a-631cb6d13e9a', -50.75, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', '78664f4a-4d35-42e8-9332-9e9fb6ca2d19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('c03a61fd-16e-45db-b9ca-57adde0c6b7f', 170.00, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('d43c2ef4-4d80-4f82-b9b5-0f86c18c72c9', -140.75, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('e16f81be-8d8e-46ac-bac5-8a4bb1d7eab2', 210.60, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('f18b21e9-23cf-4bd9-bb73-9deccbe2a1e5', -90.30, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', '78664f4a-4d35-42e8-9332-9e9fb6ca2d19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('01cfa29b-3ca9-4bf0-b8c0-2b94e8b5f6fc', 130.75, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('1ea74c6d-6a91-4be5-9b70-74cefb1b3c91', -110.50, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', 'b99bc9c7-4e6a-487f-8c0e-cc0a01d017b5', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('2cf4a8f7-86bd-4f9a-8c1e-1a69a5d25d7a', 120.00, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'd5a6e1ad-5f62-4b1a-8b5c-9a437a2e8fc1', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('3bfc01a2-9c8f-4c71-85d7-6977c8b3e16f', -40.25, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('4d80b9c4-b7cf-4c98-9a74-2bcebae8a0c8', 95.00, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', '78664f4a-4d35-42e8-9332-9e9fb6ca2d19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('5a8d4b71-dc59-4d65-ba2d-8a1e01f54db6', -70.10, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('6f8a24e9-a3e4-4a50-b92c-91f2c6b8a1b2', 160.90, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3', 'd5a6e1ad-5f62-4b1a-8b5c-9a437a2e8fc1', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('7e19d6ca-f214-4a48-9b84-26c7d9a6e5d5', -75.75, 'fba2e190-21d3-4c81-b262-536e78d0a3c9', '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('8b76a9d0-abc7-4b3e-88b4-1e6d53c0f3cd', 145.60, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('9c72e6f3-7d14-40ca-8f2d-6d50b8e9c41b', 20.25, 'a88eb0e6-5c4c-4c5a-9a29-3a20b8fcb9f3' ,'f4d7b7e5-7116-4ca0-9b20-2e3ee555aaba', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('d64fa23b-3eb1-4f56-87e0-23c7aeb15b7d', 18.5, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304' ,'f4d7b7e5-7116-4ca0-9b20-2e3ee555aaba', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('a2b5c8f9-4e23-47d6-9cfa-1a6e8cd3b84c', -15, 'fba2e190-21d3-4c81-b262-536e78d0a3c9' , 'f4d7b7e5-7116-4ca0-9b20-2e3ee555aaba', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CONFIRMED'),
    ('6a4d18e7-8b3c-48a9-9e5d-2a4dcf6b0e1f', 199.9, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304',  '2183edaf-9264-4d39-8e36-5b151f8998b4', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CREATED'),
    ('8c6f29a1-9a4e-41d5-8c9b-5b8da1e6f2cf', -50, '0c1b4c2f-3c1c-4859-9b4c-287e1707c304', 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19', NOW() - INTERVAL FLOOR(RAND() * 730) DAY, 'STATE_CREATED');

-- Aggiornamento saldo carte

UPDATE credit_card
SET balance = 95.35
WHERE id = 'a5664h9a-2d38-90e8-9312-1x3vb6za2c19';

UPDATE credit_card
SET balance = 259.95
WHERE id = '2183edaf-9264-4d39-8e36-5b151f8998b4';

UPDATE credit_card
SET balance = 179.45
WHERE id = '78664f4a-4d35-42e8-9332-9e9fb6ca2d19';

UPDATE credit_card
SET balance = 141.25
WHERE id = 'b99bc9c7-4e6a-487f-8c0e-cc0a01d017b5';

UPDATE credit_card
SET balance = 520.7
WHERE id = 'd5a6e1ad-5f62-4b1a-8b5c-9a437a2e8fc1';

UPDATE credit_card
SET balance = 23.75
WHERE id = 'f4d7b7e5-7116-4ca0-9b20-2e3ee555aaba';
