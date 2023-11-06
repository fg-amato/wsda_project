CREATE SCHEMA amato_wsda;

-- Usa lo schema
USE amato_wsda;

-- Crea la tabella "user"
CREATE TABLE user (
  id VARCHAR(255) PRIMARY KEY,
  username VARCHAR(36) UNIQUE NOT NULL,
  password VARCHAR(36) NOT NULL,
  user_role VARCHAR(36) NOT NULL,
  enabled BOOLEAN NOT NULL
);

-- Crea la tabella "credit_card"
CREATE TABLE credit_card (
  id VARCHAR(255) PRIMARY KEY,
  card_number VARCHAR(16) NOT NULL UNIQUE,
  balance FLOAT(10, 2) NOT NULL,
  ref_card_holder VARCHAR(36) NOT NULL,
  enabled BOOLEAN NOT NULL,
  FOREIGN KEY (ref_card_holder) REFERENCES user(id)
);

-- Crea la tabella "transaction"
CREATE TABLE transaction (
  id VARCHAR(255) PRIMARY KEY,
  amount FLOAT(10, 2) NOT NULL,
  ref_merchant VARCHAR(36) NOT NULL,
  ref_credit_card VARCHAR(36) NOT NULL,
  date_transaction DATETIME(6) NOT NULL,
  transaction_state VARCHAR(36) NOT NULL,
  FOREIGN KEY (ref_merchant) REFERENCES user(id),
  FOREIGN KEY (ref_credit_card) REFERENCES credit_card(id)
);