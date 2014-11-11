CREATE OR REPLACE VIEW vw_individual AS
SELECT 
	cw_individual.individual_id AS cw_individual_id,
	ss_individual.individual_id AS ss_individual_id,
	IF(ss_individual.individual_Id IS NOT NULL, ss_individual.individual_id, cw_individual.individual_id + 100000) AS individual_id
FROM cw_individual
INNER JOIN ss_individual
	ON cw_individual.first_name IN (ss_individual.first_name, ss_individual.legal_name)
	AND cw_individual.last_name IN (ss_individual.last_name, ss_individual.maiden_name);
