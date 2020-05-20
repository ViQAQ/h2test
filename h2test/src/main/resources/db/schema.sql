DROP TABLE IF EXISTS Transactions;
CREATE TABLE Transactions(
	TransactionID INT PRIMARY KEY,
	TradeID NUMBER(3),
	Version NUMBER(3),
	SecurityCode VARCHAR(50),
	Quantity NUMBER(3),
	Action VARCHAR(50),
	BUYSELL VARCHAR(50)
);