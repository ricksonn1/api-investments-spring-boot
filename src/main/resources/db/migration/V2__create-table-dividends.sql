CREATE TABLE dividends (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    datePaidAmount DATE NOT NULL,
    amountPaid DECIMAL(10,2) NOT NULL,
    id_enterprise BIGINT NOT NULL,
    FOREIGN KEY (id_enterprise) REFERENCES enterprises(id)
);
