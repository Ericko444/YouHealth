INSERT INTO medecin VALUES 
('medecin-' || nextval('medecin_seq'), 'Jack', 'jack', 'generaliste'),
('medecin-' || nextval('medecin_seq'), 'Marc', 'lavoanjo', 'cardiologue'),
('medecin-' || nextval('medecin_seq'), 'Jean', 'marc', 'neurologue'),
('medecin-' || nextval('medecin_seq'), 'Sakamanga', 'mangasaka', 'pneumologue'),
('medecin-' || nextval('medecin_seq'), 'Kollo', 'kolotv', 'psychlogue'),
('medecin-' || nextval('medecin_seq'), 'Emile', 'paredes', 'urologue'),
('medecin-' || nextval('medecin_seq'), 'Paul', 'rudd', 'ophtalmologue'),
('medecin-' || nextval('medecin_seq'), 'Tony Stark', 'brialy', 'cardiologue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'generaliste'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'pathologue'),

('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'generaliste'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'cardiologue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'neurologue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'pneumologue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'psychlogue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'urologue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'ophtalmologue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'cardiologue'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'generaliste'),
('medecin-' || nextval('medecin_seq'), 'Jack', 'brialy', 'pathologue');

INSERT INTO patient VALUES
('patient-' || nextval('patient_seq'), 'Marco Veratti', 'psg', '2000-03-23'),
('patient-' || nextval('patient_seq'), 'Fabian', 'napoli', '1992-08-23'),
('patient-' || nextval('patient_seq'), 'Kaby Lhame', 'sakila', '2000-11-27'),
('patient-' || nextval('patient_seq'), 'Jacky', 'bartez22', '1990-10-09'),
('patient-' || nextval('patient_seq'), 'Mykonos', 'greek', '2000-03-23');


CREATE VIEW detailsRdv AS SELECT rendezvous.idrdv,rendezvous.daterdv,rendezvous.idpatient,patient.username AS patientName,EXTRACT(year FROM age(current_date,patient.dateDeNaissance)) :: int as age,rendezvous.idmedecin,medecin.username AS medecinName FROM rendezvous JOIN patient ON rendezvous.idpatient=patient.idpatient JOIN medecin ON rendezvous.idmedecin=medecin.idmedecin

-- View: public.detailsrdv

-- DROP VIEW public.detailsrdv;

CREATE OR REPLACE VIEW public.detailsrdv
 AS
 SELECT rendezvous.idrdv,
    rendezvous.daterdv,
    rendezvous.idpatient,
    patient.username AS patientname,
    date_part('year'::text, age(CURRENT_DATE::timestamp with time zone, patient.datedenaissance::timestamp with time zone))::integer AS age,
    rendezvous.idmedecin,
    medecin.username AS medecinname,
    medecin.specialisation
   FROM rendezvous
     JOIN patient ON rendezvous.idpatient::text = patient.idpatient::text
     JOIN medecin ON rendezvous.idmedecin::text = medecin.idmedecin::text;

ALTER TABLE public.detailsrdv
    OWNER TO postgres;

-- View: public.patientv

-- DROP VIEW public.patientv;

CREATE OR REPLACE VIEW public.patientv
 AS
 SELECT patient.idpatient,
    patient.username,
    patient.password,
    date_part('year'::text, age(CURRENT_DATE::timestamp with time zone, patient.datedenaissance::timestamp with time zone))::integer AS age
   FROM patient;

ALTER TABLE public.patientv
    OWNER TO postgres;

