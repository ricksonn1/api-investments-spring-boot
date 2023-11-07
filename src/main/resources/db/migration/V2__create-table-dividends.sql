CREATE TABLE dividends (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_amount_paid DATE NOT NULL,
    amount_paid DECIMAL(10,2) NOT NULL,
    enterprise_id BIGINT NOT NULL,
    CONSTRAINT fk_dividend_enterprise FOREIGN KEY (enterprise_id) REFERENCES enterprises (id)
);