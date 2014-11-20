CREATE OR REPLACE VIEW vw_family AS
SELECT DISTINCT
	cw_individual.family_id AS cw_family_id,
	ss_individual.family_id AS ss_family_id,
	IF(ss_individual.family_id IS NOT NULL, ss_individual.family_id, cw_individual.family_id + 1000000) AS family_id
FROM cw_individual
INNER JOIN ss_individual
	ON cw_individual.first_name IN (ss_individual.first_name, ss_individual.legal_name)
	AND cw_individual.last_name IN (ss_individual.last_name, ss_individual.maiden_name)
WHERE ss_individual.family_id IS NOT NULL;
