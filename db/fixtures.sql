INSERT INTO site (name, description, location, postcode, latitude, longitude, user_account_id, departement_code, region_id) VALUES
  ('Vieux chateau', 'Paroi du vieux château à Yeu', E'Ile d\'Yeu', 85350, 46.7252, -2.3494, 1, 85, 12),
  (E'Mur d\'escalade du Luat', E'Ce mur d\'escalade est présent dans le stade du Luat à Eaubonne', 'Eaubonne', 95600, 49, 2.2833, 1, 95, 8);

INSERT INTO comment (content, author_id, parent_id) VALUES
  ('Ceci est un commentaire de test', 1, null),
  ('Un commentaire enfant de test', 1, 1),
  ('Voici un autre commentaire de test', 1, null);

INSERT INTO comment_site (comment_id, site_id) VALUES
  (1,1),
  (2,1),
  (3,1);