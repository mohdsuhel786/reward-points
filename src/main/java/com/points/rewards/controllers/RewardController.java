package com.points.rewards.controllers;

import com.points.rewards.dtos.RewardResponse;
import com.points.rewards.services.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Reward Controller and endpoint "/rewards"
@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    //get mapping for get List of rewards
    @GetMapping
    public List<RewardResponse> getRewards(){
        return rewardService.calculateRewards();
    }
}
