USE vote_db;

DELIMITER $$

CREATE PROCEDURE GetAllVotingItems()
BEGIN
    SELECT * FROM voting_items;
END$$


CREATE PROCEDURE AddVotingItem(IN name VARCHAR(255))
BEGIN
    INSERT INTO voting_items (name) VALUES (name);
END$$


CREATE PROCEDURE LogicalDeleteVotingItem(IN itemId  BIGINT)
BEGIN
    UPDATE voting_items
    SET deleted  = 1
    WHERE id = itemId;
END$$


CREATE PROCEDURE GetVotingItemsWithVoteCount()
BEGIN
    SELECT 
        i.id,
        i.name,
        COUNT(r.id) AS vote_count
    FROM voting_items i
    LEFT JOIN voting_records r ON i.id = r.item_id
    WHERE i.deleted = FALSE
    GROUP BY i.id, i.name;
END$$


CREATE PROCEDURE GetVotingRecords()
BEGIN
    SELECT vr.id as id, vr.voter as voter, vi.name as item_id
    FROM voting_records vr
    JOIN voting_items vi ON vr.item_id = vi.id;
END $$


CREATE PROCEDURE AddVotingRecord(IN itemId INT, IN voterName VARCHAR(255))
BEGIN
    INSERT INTO voting_records (item_id, voter) 
    VALUES (itemId, voterName);
END $$