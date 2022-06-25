package com.socash.scgame;

import com.socash.scgame.entity.Card;
import com.socash.scgame.entity.Player;
import com.socash.scgame.enumeration.CardNumberEnum;
import com.socash.scgame.service.SetupGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class ScGameApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ScGameApplication.class);
    private final SetupGameService setupGameService;

    public ScGameApplication(SetupGameService setupGameService) {
        this.setupGameService = setupGameService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScGameApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<Player> players = setupGameService.addPlayer();
        players.forEach(player -> logger.info(player.toString()));
        Map<Player, List<Card>> playerListMap = setupGameService.distributeCardToPlayer(players);
        playerListMap.entrySet().forEach(playerListEntry -> logger.info(playerListEntry.toString()));
        Player winner = setupGameService.checkAllRule(playerListMap);
        logger.info("Winner: " + winner);
    }
}
