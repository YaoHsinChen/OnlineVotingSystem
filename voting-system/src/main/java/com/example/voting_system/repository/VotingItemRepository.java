package com.example.voting_system.repository;

import com.example.voting_system.model.VotingItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotingItemRepository extends JpaRepository<VotingItem, Long> {

    @Query(value = "CALL GetVotingItemsWithVoteCount()", nativeQuery = true)
    List<VotingItemWithVoteCount> getVotingItemsWithVoteCount();

    @Modifying
    @Transactional
    @Query(value = "CALL AddVotingItem(:name)", nativeQuery = true)
    void addVotingItem(String name);

    @Modifying
    @Transactional
    @Query(value = "CALL LogicalDeleteVotingItem(:id)", nativeQuery = true)
    void deleteVotingItem(Long id);

    @Modifying
    @Transactional
    @Query(value = "CALL AddVotingRecord(:itemId, :voter)", nativeQuery = true)
    void addVotingRecord(@Param("itemId") Long itemId, @Param("voter") String voter);
    @Query(value = "CALL GetVotingRecords()", nativeQuery = true)
    List<VotingRecord> getVotingRecords();

    public interface VotingItemWithVoteCount {
        Long getId();
        String getName();
        Long getVoteCount();
    }

    public interface VotingRecord {
        Long getId();
        String getVoter();
        String getItemId();
    }
}
