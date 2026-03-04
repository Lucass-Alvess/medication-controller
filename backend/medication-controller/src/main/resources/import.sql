-- Garanta que o comando não tenha quebras de linha no meio do bloco
INSERT INTO tb_user (name, email, password, date_birth, blood_type, health_plan, weight, height, phone_number) VALUES ('Maria Silva', 'maria@email.com', 'pass123', '1995-05-20', 'O+', 'Premium Health', 75.5, 1.80, '11999999999');

INSERT INTO tb_allergy (name, category) VALUES ('Dipyrone', 'Medicine');

INSERT INTO tb_user_allergy (user_id, allergy_id, observation) VALUES (1, 1, 'Severe skin reaction');

INSERT INTO tb_medicine (name, dosage, frequency, stock, date, application_type, user_id) VALUES ('Paracetamol', '500mg', 8, 20, '2026-02-23T10:00:00Z', 'Oral', 1);

INSERT INTO tb_medication_record (prescribed_schedule, time_taken, status, medication_id) VALUES ('2026-02-23T08:00:00Z', '2026-02-23T08:05:00Z', 'TAKEN', 1);

INSERT INTO tb_consultation (doctor, specialty, date, status, user_id) VALUES ('Dr. Arnaldo', 'Cardiologist', '2026-03-10T14:30:00Z', 'SCHEDULED', 1);