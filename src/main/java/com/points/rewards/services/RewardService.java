package com.points.rewards.services;

import com.points.rewards.dtos.RewardResponse;
import com.points.rewards.entities.Transaction;
import com.points.rewards.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardService {
    private final TransactionRepository transactionRepository;

    public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    //calculate rewards
    public List<RewardResponse> calculateRewards(){
        LocalDate now = LocalDate.now();
        LocalDate threeMonthsAgo = now.minusMonths(3);

        //fetch all transaction in the past 3 months
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(threeMonthsAgo,now);

        //Group transactions by customer
        Map<Long, List<Transaction>> byCustomer = transactions.stream().collect(Collectors.groupingBy(Transaction::getCustomerId));

        List<RewardResponse> rewardResponses = new ArrayList<>();

        for(Map.Entry<Long, List<Transaction>> entry :byCustomer.entrySet()){
            Long customerId = entry.getKey();
            String customerName = entry.getValue().get(0).getCustomerName();

            Map<String, Integer> monthlyRewards = new HashMap<>();
            int totalRewards = 0;

            for(Transaction tx: entry.getValue()){
                int points = calculatePointS(tx.getAmount());
                String month = tx.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy-MM"));
                monthlyRewards.put(month,monthlyRewards.getOrDefault(month,0)+points);
                totalRewards += points;
            }
            rewardResponses.add(new RewardResponse(customerId,customerName,monthlyRewards,totalRewards));
        }


        return  rewardResponses;
    }

    //calculate points
    private int calculatePointS(double amount) {

        if(amount<50) return 0;
        if (amount<=100) {
            return (int) (amount - 50);
        }
        return 50+(int) ((amount-100)*2);
    }
}
