package com.socash.scgame.service;

import com.socash.scgame.entity.Card;
import com.socash.scgame.entity.Player;

import java.util.List;
import java.util.Map;

public interface SetupGameService {
    List<Player> addPlayer();
    Map<Player, List<Card>> distributeCardToPlayer(List<Player> players);
    Player checkAllRule(final Map<Player, List<Card>> cardsPlayers);
}
