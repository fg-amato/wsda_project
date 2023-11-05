-- Inserimento di 10 utenti

INSERT INTO user (id, username, password, user_role, enabled)
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
  ('0b61ef27-98b5-44f9-bf5d-0f65a6a4d5a1', 'utente6', 'utente06', 'ROLE_CLASSIC_USER', true),
  
  
-- Inserimento di 6 carte

INSERT INTO credit_card (id, card_number, balance, ref_card_holder)
VALUES
  ('d5a6e1ad-5f62-4b1a-8b5c-9a437a2e8fc1', '1111111111111111', 0.00, '6dd8f88d-5a9e-4de1-8c7a-9d65a9b4f990'),
  ('2183edaf-9264-4d39-8e36-5b151f8998b4', '2222222222222222', 0.00, '30b75614-3bd9-4c7b-b07a-7a68e4c530d9'),
  ('b99bc9c7-4e6a-487f-8c0e-cc0a01d017b5', '3333333333333333', 0.00, '1a731e8b-18fc-4374-942d-51b22b0be252'),
  ('f4d7b7e5-7116-4ca0-9b20-2e3ee555aaba', '4444444444444444', 0.00, 'e14d28e4-47e3-4d29-8bea-ef4df59d5ad2'),
  ('78664f4a-4d35-42e8-9332-9e9fb6ca2d19', '5555555555555555', 0.00, 'ba88ef3c-ee33-4f1f-a42f-7c1190ca9f50');
  ('a5664h9a-2d38-90e8-9312-1x3vb6za2c19', '6666666666666666', 0.00, '0b61ef27-98b5-44f9-bf5d-0f65a6a4d5a1');