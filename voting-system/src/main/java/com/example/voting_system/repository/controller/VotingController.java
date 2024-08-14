package com.example.voting_system.repository.controller;

import com.example.voting_system.model.VotingItem;
import com.example.voting_system.model.VotingRecord;
import com.example.voting_system.repository.VotingItemRepository;
import com.example.voting_system.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VotingController {
    @Autowired
    private VotingService votingService;

    @GetMapping
    public List<VotingItem> getAllVotingItems() {
        return votingService.getAllVotingItems();
    }

    @GetMapping("/voting-items-with-vote-count")
    public List<VotingItemRepository.VotingItemWithVoteCount> getAllVotingItemsWithVoteCount() {
        return votingService.getAllVotingItemsWithVoteCount();
    }

    @PostMapping
    public void addVotingItem(@RequestBody VotingItem votingItem) {
        votingService.addVotingItem(votingItem.getName());
    }


    @DeleteMapping("/delete-voting-items/{id}")
    public void deleteVotingItem(@PathVariable Long id) {
        votingService.deleteVotingItem(id);
    }


    @PostMapping("/submit-votes")
    public void submitVotes(@RequestBody VotingRecord votingRecord) {
        votingService.submitVotes(votingRecord.getItemIds(), votingRecord.getVoter());
    }

    @GetMapping("/voting-records")
    public List<VotingItemRepository.VotingRecord> getVotingRecords() {
        return votingService.getVotingRecords();
    }
}
