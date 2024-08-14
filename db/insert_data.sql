USE vote_db;

INSERT INTO voting_items (name) VALUES ('電腦'), ('滑鼠');

INSERT INTO voting_records (voter, item_id) VALUES ('Leo', 1);
INSERT INTO voting_records (voter, item_id) VALUES ('Sandy', 1);
INSERT INTO voting_records (voter, item_id) VALUES ('Sandy', 2);
INSERT INTO voting_records (voter, item_id) VALUES ('Randy', 2);
INSERT INTO voting_records (voter, item_id) VALUES ('RSY', 2);
