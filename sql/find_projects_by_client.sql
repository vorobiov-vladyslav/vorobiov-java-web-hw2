SELECT p.id, p.start_date, p.finish_date
FROM project p
JOIN client c ON c.id = p.client_id
WHERE c.name = ?;
