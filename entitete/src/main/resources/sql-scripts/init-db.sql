INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Petra', 'Kos', 'petrakos', 'petra.kos@hotmail.com');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Miha', 'Novak', 'mihanovak', 'miha.novak@gmail.com');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime1', 'Priimek1', 'imePriimek1', 'email1');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime2', 'Priimek2', 'imePriimek2', 'email2');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime3', 'Priimek3', 'imePriimek3', 'email3');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime4', 'Priimek4', 'imePriimek4', 'email4');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime5', 'Priimek5', 'imePriimek5', 'email5');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime6', 'Priimek6', 'imePriimek6', 'email6');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime7', 'Priimek7', 'imePriimek7', 'email7');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Ime8', 'Priimek8', 'imePriimek8', 'email8');
INSERT INTO postaja (ime, specifikacije, lokacija, obratovalni_cas_zacetek, obratovalni_cas_konec, cena ) VALUES ('postaja1', 'polnilna postaja1', 'Ljubljana', '8:00', '20:00', '5');
INSERT INTO postaja (ime, specifikacije, lokacija, obratovalni_cas_zacetek, obratovalni_cas_konec, cena ) VALUES ('postaja2', 'polnilna postaja2', 'Novo mesto', '9:00', '21:00', '15');
INSERT INTO postaja (ime, specifikacije, lokacija, obratovalni_cas_zacetek, obratovalni_cas_konec, cena ) VALUES ('postaja3', 'polnilna postaja3', 'Šentjernej', '0:00', '23:00', '6');
INSERT INTO postaja (ime, specifikacije, lokacija, obratovalni_cas_zacetek, obratovalni_cas_konec, cena ) VALUES ('postaja4', 'polnilna postaja4', 'Ljubljana', '7:00', '23:00', '8');
INSERT INTO postaja (ime, specifikacije, lokacija, obratovalni_cas_zacetek, obratovalni_cas_konec, cena ) VALUES ('postaja5', 'polnilna postaja5', 'Novo mesto', '6:00', '22:00', '10');
INSERT INTO lastnistvo (postaja_id, uporabnik_id) VALUES (1, 1);
INSERT INTO lastnistvo (postaja_id, uporabnik_id) VALUES (2, 2);
INSERT INTO lastnistvo (postaja_id, uporabnik_id) VALUES (3, 4);
INSERT INTO lastnistvo (postaja_id, uporabnik_id) VALUES (4, 3);
INSERT INTO lastnistvo (postaja_id, uporabnik_id) VALUES (5, 5);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (2,1,5);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (1,2,2);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (3,3,15);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (4,4,5);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (8,4,5);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (3,2,5);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (5,4,5);
INSERT INTO najem (postaja_id, uporabnik_id, cas_polnjenja) VALUES (6,4,5);
INSERT INTO rezervacija (postaja_id, uporabnik_id, zacetek_rezervacije, konec_rezervacije) VALUES (2,1,'15:00','17:00');
INSERT INTO rezervacija (postaja_id, uporabnik_id, zacetek_rezervacije, konec_rezervacije) VALUES (3,1,'7:00','17:00');
INSERT INTO rezervacija (postaja_id, uporabnik_id, zacetek_rezervacije, konec_rezervacije) VALUES (3,2,'7:00','9:00');
INSERT INTO rezervacija (postaja_id, uporabnik_id, zacetek_rezervacije, konec_rezervacije) VALUES (3,1,'9:00','11:00');
INSERT INTO rezervacija (postaja_id, uporabnik_id, zacetek_rezervacije, konec_rezervacije) VALUES (5,4,'11:00','13:00');
INSERT INTO rezervacija (postaja_id, uporabnik_id, zacetek_rezervacije, konec_rezervacije) VALUES (8,3,'7:00','15:00');