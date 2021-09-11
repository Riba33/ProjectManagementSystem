INSERT INTO skils VALUES ('1', 'Java', 'Junior');
INSERT INTO skils VALUES ('2', 'Java', 'Middle');
INSERT INTO skils VALUES ('3', 'Java', 'Senior');
INSERT INTO skils VALUES ('4', 'C++', 'Junior');
INSERT INTO skils VALUES ('5', 'C#', 'Junior');
INSERT INTO skils VALUES ('6', 'JS', 'Junior');
INSERT INTO skils VALUES ('7', 'C++', 'Middle');
INSERT INTO skils VALUES ('8', 'C++', 'Senior');
INSERT INTO skils VALUES ('9', 'C#', 'Middle');
INSERT INTO skils VALUES ('10', 'JS', 'Middle');
INSERT INTO skils VALUES ('11', 'C#', 'Senior');
INSERT INTO skils VALUES ('12', 'JS', 'Senior');

INSERT INTO developers VALUES ('1', 'Vitaliy', 'Tkachuk', '36', 'M', '500');
INSERT INTO developers VALUES ('2', 'Oleksiy', 'Krutko', '42', 'M', '550');
INSERT INTO developers VALUES ('3', 'Alex', 'Dorosh', '32', 'M', '600');
INSERT INTO developers VALUES ('4', 'Vova', 'Lysenko', '28', 'M', '800');
INSERT INTO developers VALUES ('5', 'Olga', 'Kurylenko', '33', 'W', '900');

INSERT INTO companies VALUES ('1', 'SoftServe', 'Software');
INSERT INTO companies VALUES ('2', 'GoIt', 'Software');
INSERT INTO companies VALUES ('3', 'Epam', 'Software');

INSERT INTO customers VALUES ('1', 'ATB', 'trade');
INSERT INTO customers VALUES ('2', 'Silpo', 'trade');
INSERT INTO customers VALUES ('3', 'Varus', 'trade');
INSERT INTO customers VALUES ('4', 'Interpipe', 'metallurgy');

INSERT INTO projects VALUES ('1', 'Project1');
INSERT INTO projects VALUES ('2', 'Project2');
INSERT INTO projects VALUES ('3', 'Project3');

INSERT INTO developers_skils VALUES ('1', '1');
INSERT INTO developers_skils VALUES ('1', '7');
INSERT INTO developers_skils VALUES ('2', '2');
INSERT INTO developers_skils VALUES ('2', '10');
INSERT INTO developers_skils VALUES ('3', '3');
INSERT INTO developers_skils VALUES ('3', '5');
INSERT INTO developers_skils VALUES ('4', '12');
INSERT INTO developers_skils VALUES ('5', '9');
INSERT INTO developers_skils VALUES ('5', '3');
INSERT INTO developers_skils VALUES ('4', '8');

INSERT INTO developers_projects VALUES ('1', '3');
INSERT INTO developers_projects VALUES ('1', '2');
INSERT INTO developers_projects VALUES ('2', '1');
INSERT INTO developers_projects VALUES ('2', '2');
INSERT INTO developers_projects VALUES ('3', '3');
INSERT INTO developers_projects VALUES ('3', '1');
INSERT INTO developers_projects VALUES ('4', '2');
INSERT INTO developers_projects VALUES ('4', '3');
INSERT INTO developers_projects VALUES ('5', '3');
INSERT INTO developers_projects VALUES ('5', '1');

INSERT INTO customers_projects VALUES ('1', '3');
INSERT INTO customers_projects VALUES ('2', '4');
INSERT INTO customers_projects VALUES ('3', '1');
INSERT INTO customers_projects VALUES ('3', '2');
INSERT INTO customers_projects VALUES ('2', '2');

INSERT INTO companies_projects VALUES ('1', '2');
INSERT INTO companies_projects VALUES ('1', '3');
INSERT INTO companies_projects VALUES ('2', '1');
INSERT INTO companies_projects VALUES ('2', '3');
INSERT INTO companies_projects VALUES ('3', '1');
INSERT INTO companies_projects VALUES ('3', '2');
