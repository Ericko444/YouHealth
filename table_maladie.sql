
CREATE TABLE maladie (
	idMaladie VARCHAR(20) PRIMARY KEY,
	nomMaladie VARCHAR(20)
);

CREATE TABLE symptomesMaladies (
	idMaladie VARCHAR(20),
	symptome VARCHAR (20),
	FOREIGN KEY(idMaladie) REFERENCES maladie(idMaladie) 
	ON DELETE CASCADE
);