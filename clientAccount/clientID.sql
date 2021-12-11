ALTER TABLE treatment
DROP FOREIGN KEY fk_client;
ALTER TABLE purchase
DROP FOREIGN KEY fk_client_purchase;
ALTER TABLE treatment
DROP FOREIGN KEY fk_procedure;
ALTER TABLE purchase
DROP FOREIGN KEY fk_proc_purchase;
/*
alter table reserved
drop foreign key fk_clientID;

alter table client_login
drop foreign key fk_clientIDlogin;

ALTER TABLE client
change column IDclient IDclient int;

ALTER TABLE treatment
change column ClientID ClientID int;

alter table reserved
change column ClientID ClientID int;

alter table client_login
change column ClientID ClientID int;

ALTER TABLE treatment
ADD CONSTRAINT fk_client FOREIGN KEY(ClientID) REFERENCES `client`(IDclient);

alter table reserved
ADD CONSTRAINT fk_clientID FOREIGN KEY(ClientID) REFERENCES `client`(IDclient);

alter table client_login
ADD CONSTRAINT fk_clientIDlogin FOREIGN KEY(ClientID) REFERENCES `client`(IDclient);
 */
