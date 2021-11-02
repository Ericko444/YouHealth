
CREATE TABLE medicament(
	idMedicament VARCHAR(20) PRIMARY KEY,
	nomMedicament VARCHAR(20),
	typeMedicament VARCHAR(20)
);

CREATE TABLE medicamentParMaladie (
	idMedicament VARCHAR(20),
	idMaladie VARCHAR(20),
	FOREIGN KEY(idMedicament) REFERENCES medicament(idMedicament)
	ON DELETE CASCADE,
	FOREIGN KEY(idMaladie) REFERENCES maladie(idMaladie)
	ON DELETE CASCADE
);

CREATE TABLE RendezVous (
	idRdv VARCHAR(20) PRIMARY KEY,
	idMedecin VARCHAR(20),
	idPatient VARCHAR(20),
	dateRdv DATE,
	FOREIGN KEY(idMedecin) REFERENCES medecin(idMedecin)
	ON DELETE CASCADE,
	FOREIGN KEY(idPatient) REFERENCES patient(idPatient)
	ON DELETE CASCADE
);


CREATE TABLE traitement (
	idTraitement VARCHAR(20) PRIMARY KEY,
	idMedecin VARCHAR(20),
	idPatient VARCHAR(20),
	dateTraitement DATE,
	FOREIGN KEY(idMedecin) REFERENCES medecin(idMedecin)
	ON DELETE CASCADE,
	FOREIGN KEY(idPatient) REFERENCES patient(idPatient)
	ON DELETE CASCADE
);

CREATE TABLE traitementParMedic(
	idTraitement VARCHAR(20),
	idMedicament VARCHAR(20),
	FOREIGN KEY(idTraitement) REFERENCES traitement(idTraitement)
	ON DELETE CASCADE,
	FOREIGN KEY(idMedicament) REFERENCES medicament(idMedicament)
	ON DELETE CASCADE
);

