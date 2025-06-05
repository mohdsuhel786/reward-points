package com.points.rewards;

import com.points.rewards.dtos.RewardResponse;
import com.points.rewards.entities.Transaction;
import com.points.rewards.repositories.TransactionRepository;
import com.points.rewards.services.RewardService;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class RewardsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private RewardService rewardService;

	//test for valid reward calculation
	@Test
	public void testRewardCalculation(){

		Transaction tx1 = new Transaction(1L,1L,"John Wick",120.0,LocalDate.now().minusMonths(1));
		Transaction tx2 = new Transaction(2L,1L,"John Wick",75.0,LocalDate.now().minusMonths(2));
		Transaction tx3 = new Transaction(3L,1L,"John Wick",55.0,LocalDate.now().minusMonths(3));

		when(transactionRepository.findByTransactionDateBetween(any(),any())).thenReturn(List.of(tx1,tx2,tx3));

		List<RewardResponse> result = rewardService.calculateRewards();

		assertEquals(1,result.size());
		assertEquals(3,result.get(0).getMonthlyRewards().size());
		assertEquals(120,result.get(0).getTotalRewards());

	}



}
