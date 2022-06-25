package com.socash.scgame.helper;

import com.socash.scgame.entity.Card;
import com.socash.scgame.entity.Player;
import com.socash.scgame.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service()
public class SetupGameHelper {
    private static final Logger logger = LoggerFactory.getLogger(SetupGameHelper.class);
    @Value("${numberOfCardsPerPlayer}")
    private final int numberOfCardsPerPlayer = 3;
    private final RuleService ruleService;
    public SetupGameHelper(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    public List<Player> allAreSame(final Map<Player, List<Card>> cardsPlayers) {
        List<Player> winners = new ArrayList<>();
        for (Map.Entry<Player, List<Card>> player : cardsPlayers.entrySet()) {

            Player p = player.getKey();
            List<Card> cards = player.getValue();
            if (ruleService.isAllCardSame(cards)) {
                p.setResult("W");
                winners.add(p);
            }
        }
        return winners;
    }

    public List<Player> isSequence(final Map<Player, List<Card>> cardsPlayers) {
        List<Player> winners = new ArrayList<>();
        for (Map.Entry<Player, List<Card>> player : cardsPlayers.entrySet()) {
            Player p = player.getKey();
            List<Card> cards = player.getValue();
            if (ruleService.isCardInSequence(cards)) {
                p.setResult("W");
                winners.add(p);
            }
        }

        return winners;
    }

    public List<Player> isTwoCardSame(final Map<Player, List<Card>> cardsPlayers) {
        List<Player> winners = new ArrayList<>();
        for (Map.Entry<Player, List<Card>> player : cardsPlayers.entrySet()) {
            Player p = player.getKey();
            List<Card> cards = player.getValue();
            if (ruleService.twoCardSame(cards)) {
                p.setResult("W");
                winners.add(p);
            }

        }

        return winners;
    }

    public Player topOrderCard(Map<Player, List<Card>> cardsPlayers) {
        int highValue = -1;
        Player highPlayer = null;

        for (int i = 0; i < numberOfCardsPerPlayer; i++) {
            for (Map.Entry<Player, List<Card>> player : cardsPlayers.entrySet()) {
                Player p = player.getKey();
                List<Card> cards = player.getValue();
                int value = cards.get(i).getNumber().getCardOrder();
                if (value > highValue) {
                    highValue = value;
                    highPlayer = p;
                }
                for (Map.Entry<Player, List<Card>> inPlayer : cardsPlayers.entrySet()) {
                    Player p1 = inPlayer.getKey();
                    List<Card> incards = player.getValue();
                    if (p1.equals(p)) {
                        int value2 = incards.get(i).getNumber().getCardOrder();
                        if (value == value2 && highValue < value2) {
                            highValue = value;
                            highPlayer = p1;
                        } else if (value < value2) {
                            highValue = value2;
                            highPlayer = p1;
                        }
                    }
                }
            }
        }
        return highPlayer;
    }
}
