

CREATE TABLE medecin (
	idMedecin VARCHAR(20) PRIMARY KEY,
	username VARCHAR(20),
	password VARCHAR(20),
	specialisation VARCHAR(20)
);

CREATE TABLE patient (
	idPatient VARCHAR(20) PRIMARY KEY,
	username VARCHAR(20),
	password VARCHAR(20),
	dateDeNaissance DATE
);	