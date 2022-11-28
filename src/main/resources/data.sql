INSERT INTO Users (user_name, email) VALUES ('John Smith', 'john_smith@hotmail.com');
INSERT INTO Users (user_name, email) VALUES ('Fede Valverde', 'fede15@gmail.com');
INSERT INTO Users (user_name, email) VALUES ('Neymar', 'neymar@gmail.com');
INSERT INTO Users (user_name, email) VALUES ('Neymar', 'neymar1@gmail.com');


INSERT INTO Account (account_name, account_number, fi_id) values ('credit card', '123456789','bank of america');
INSERT INTO Account (account_name, account_number, fi_id) values ('checking account', '55556666','bank of america');
INSERT INTO Account (account_name, account_number, fi_id) values ('credit card', '321456987', 'BBVA');
INSERT INTO Account (account_name, account_number, fi_id) values ('savings account', '99887766','BBVA');
INSERT INTO Account (account_name, account_number, fi_id) values ('savings account1', '99887767','DBS BANK');

INSERT INTO User_Accounts (user_id, account_id) values (Select user_id from users where id=1, select account_id from account where id=1);
INSERT INTO User_Accounts (user_id, account_id) values (Select user_id from users where id=1, select account_id from account where id=2);
INSERT INTO User_Accounts (user_id, account_id) values (Select user_id from users where id=2, select account_id from account where id=3);
INSERT INTO User_Accounts (user_id, account_id) values (Select user_id from users where id=3, select account_id from account where id=4);
INSERT INTO User_Accounts (user_id, account_id) values (Select user_id from users where id=3, select account_id from account where id=5);