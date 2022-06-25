package com.socash.scgame.service.impl;

import com.socash.scgame.entity.Card;
import com.socash.scgame.entity.Player;
import com.socash.scgame.helper.SetupGameHelper;
import com.socash.scgame.service.SetupGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service()
public class SetupGameServiceImpl implements SetupGameService {
    private static final Logger logger = LoggerFactory.getLogger(SetupGameServiceImpl.class);
    @Value("${numberOfPlayers}")
    private final int numberOfPlayers = 4;
    @Value("${numberOfCardsPerPlayer}")
    private final int numberOfCardsPerPlayer = 3;

    private final SetupGameHelper gameHelper;

    public SetupGameServiceImpl(SetupGameHelper gameHelper) {
        this.gameHelper = gameHelper;
    }

    @Override
    public List<Player> addPlayer() {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            Player user = new Player(i);
            user.setPlayerName("P" + i);
            players.add(user);
        }
        return players;
    }

    @Override
    public Map<Player, List<Card>> distributeCardToPlayer(List<Player> players) {
        Map<Player, List<Card>> cardsPlayers = new HashMap<>();
        Stack<Card> cards = Card.getPackOfCards();
        Card.shuffleCards(cards);

        for (Player player : players) {
            List<Card> temp = new ArrayList<>();
            for (int i = 1; i <= numberOfCardsPerPlayer; i++) {
                temp.add(cards.pop());
            }
            Collections.sort(temp);
            cardsPlayers.put(player, temp);
        }
        return cardsPlayers;
    }

    @Override
    public Player checkAllRule(Map<Player, List<Card>> cardsPlayers) {
        List<Player> winners = gameHelper.allAreSame(cardsPlayers);
        if (winners.size() == 0) {
            logger.info("Winner not decide yet same card");
            winners = gameHelper.isSequence(cardsPlayers);
            if (winners.size() == 0) {
                logger.info("Winner not decide yet in isSequence");
                winners = gameHelper.isTwoCardSame(cardsPlayers);
                if (winners.size() == 0) {
                    logger.info("Winner not decide yet in isTwoCardSame ");
                    logger.info("in top order car " + gameHelper.topOrderCard(cardsPlayers));
                    return new Player();
                } else {
                    logger.info("Winner in isTwoCardSame ");
                    logger.info(winners.get(0).toString());
                    return winners.get(0);
                }
            } else {
                logger.info("Winner in isSequence ");
                logger.info(winners.get(0).toString());
                return winners.get(0);
            }
        } else {
            logger.info("Winner in same card ");
            logger.info(winners.get(0).toString());
            return winners.get(0);
        }
    }
}
