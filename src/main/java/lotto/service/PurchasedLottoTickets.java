package lotto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.readUserInput.PurchaseAmount;

public class PurchasedLottoTickets {
    private List<Lotto> tickets;

    public PurchasedLottoTickets() {
        this.tickets = new ArrayList<>();
        register();
    }

    public void register() {
        while (tickets.size() < PurchaseAmount.lottoQuantity) {
            RandomNumbers randomNumbers = new RandomNumbers();
            Lotto lotto = new Lotto(randomNumbers.draw());
            tickets.add(lotto);
        }
    }

    public Map<String, Integer> eachRankCount() {
        Map<String, Integer> counts = new HashMap<>();
        setZero(counts);
        tickets.stream()
                .map(CompareWinningNumbers::new)
                .forEach((winningNumbers) -> {
                    String rank = winningNumbers.checkThisTicketRank();
                    counts.put(rank, counts.get(rank) + 1);
                });
        return counts;
    }

    public void setZero(Map<String, Integer> counts) {
        counts.put("1st", 0);
        counts.put("2nd", 0);
        counts.put("3rd", 0);
        counts.put("4th", 0);
        counts.put("5th", 0);
        counts.put("lose", 0);
    }

    public void textForTicketsPrint() {
        for (Lotto lotto : tickets) {
            lotto.textForLottoPrint();
        }
    }
}
