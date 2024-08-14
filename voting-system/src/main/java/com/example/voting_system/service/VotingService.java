package com.example.voting_system.service;

import com.example.voting_system.model.VotingItem;
import com.example.voting_system.repository.VotingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VotingService {
    @Autowired
    private VotingItemRepository votingItemRepository;

    public List<VotingItem> getAllVotingItems() {
        return votingItemRepository.findAll().stream()
                .filter(item -> !item.isDeleted())
                .toList();
    }

    public void addVotingItem(String name) {
        votingItemRepository.addVotingItem(name);
    }

    public void deleteVotingItem(Long id) {
        votingItemRepository.deleteVotingItem(id);
    }

    public List<VotingItemRepository.VotingItemWithVoteCount> getAllVotingItemsWithVoteCount() {
        return votingItemRepository.getVotingItemsWithVoteCount();
    }

    public List<VotingItemRepository.VotingRecord> getVotingRecords() {
        return votingItemRepository.getVotingRecords();
    }

    @Transactional
    public void submitVotes(List<Long> itemIds, String voter) {
        if (itemIds == null || itemIds.isEmpty() || voter.isEmpty()) {
            throw new IllegalArgumentException("投票項目列表不能为空");
        }
        for (Long itemId : itemIds) {
            votingItemRepository.addVotingRecord(itemId, voter);
        }
    }
}
