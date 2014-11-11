CREATE OR REPLACE VIEW vw_chart_of_accounts AS
SELECT *
FROM ss_chart_of_account
UNION
SELECT *
FROM cw_chart_of_account;
