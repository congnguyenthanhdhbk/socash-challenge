package com.socash.scgame;

import com.socash.scgame.entity.Card;
import com.socash.scgame.entity.Player;
import com.socash.scgame.enumeration.CardNumberEnum;
import com.socash.scgame.enumeration.CartTypeEnum;
import com.socash.scgame.service.RuleService;
import com.socash.scgame.service.SetupGameService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ScGameApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ScGameApplicationTests.class);
    @Autowired
    private RuleService ruleService;

    @Autowired
    private SetupGameService setupGameService;

    @Test
    void contextLoads() {
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class CardGameSetup {
        @Test
        @Order(3)
        @DisplayName("check same number of card ")
        void checkAnySameNumberCard() {
            List<Player> players = setupGameService.addPlayer();
            players.forEach(player -> logger.info(player.toString()));
            Map<Player, List<Card>> playerListMap = setupGameService.distributeCardToPlayer(players);
            playerListMap.entrySet().forEach(playerListEntry -> logger.info(playerListEntry.toString()));
            Player winner = setupGameService.checkAllRule(playerListMap);
            logger.info("Winner: " + winner);
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class twoCardSame {

        @Test
        @DisplayName("check 4 card 5,5,3,2 ")
        void test1() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.THREE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.FOUR, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.twoCardSame(card));
        }

        @Test
        @DisplayName("check 4 card 1,3,2,1 ")
        void test2() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.TWO, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.THREE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.twoCardSame(card));
        }

        @Test
        @DisplayName("check 4 card 1,13,13,1 ")
        void test3() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.KING, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.KING, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.twoCardSame(card));
        }

        @Test
        @DisplayName("check 4 card 1,8,12,11 ")
        void test4() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.EIGHT, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.QUEEN, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertFalse(ruleService.twoCardSame(card));
        }


        @Test
        @DisplayName("check 3 card 11,12,11 ")
        void test5() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.QUEEN, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.twoCardSame(card));
        }

        @Test
        @DisplayName("check 3 card 5,5,11 ")
        void test6() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.twoCardSame(card));
        }

        @Test
        @DisplayName("check 3 card 8,5,11 ")
        void test7() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.EIGHT, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertFalse(ruleService.twoCardSame(card));
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class checkCardSequence {
        @Test
        @DisplayName("check 4 card 5,4,3,2 ")
        void test1() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.TWO, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.THREE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.FOUR, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.isCardInSequence(card));
        }

        @Test
        @DisplayName("check 4 card 4,3,2,1 ")
        void test2() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.TWO, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.THREE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.FOUR, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.isCardInSequence(card));
        }

        @Test
        @DisplayName("check 4 card 1,13,12,11 ")
        void test3() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.KING, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.QUEEN, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.isCardInSequence(card));
        }

        @Test
        @DisplayName("check 4 card 1,1,12,11 ")
        void test4() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.QUEEN, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertFalse(ruleService.isCardInSequence(card));
        }


        @Test
        @DisplayName("check 3 card 1,12,11 ")
        void test5() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.QUEEN, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertFalse(ruleService.isCardInSequence(card));
        }

        @Test
        @DisplayName("check 3 card 13,11,11 ")
        void test6() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.KING, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertFalse(ruleService.isCardInSequence(card));
        }

        @Test
        @DisplayName("check 3 card 13,12,11 ")
        void test7() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.KING, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.QUEEN, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.isCardInSequence(card));
        }

        @Test
        @DisplayName("check 3 card 5,6,7 ")
        void test8() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.SEVEN, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.SIX, CartTypeEnum.DIAMOND));
            assertTrue(ruleService.isCardInSequence(card));
        }

    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class AllCardsSame {

        @Test
        @DisplayName("check 0 card is same")
        void checzeroCardSame() {
            List<Card> card = new ArrayList<>();
            ruleService.isAllCardSame(card);
            assertFalse(ruleService.isAllCardSame(card));
        }

        @Test
        @DisplayName("check 1 card is same")
        void checkOneCardSame() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            ruleService.isAllCardSame(card);
            assertFalse(ruleService.isAllCardSame(card));
        }

        @Test
        @DisplayName("check 2 card is same")
        void checkTwoCardSame() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.SPADE));
            ruleService.isAllCardSame(card);
            assertTrue(ruleService.isAllCardSame(card));
        }

        @Test
        @DisplayName("check 3 card is same")
        void checkThreeCardSame() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.HEARTS));
            ruleService.isAllCardSame(card);
            assertTrue(ruleService.isAllCardSame(card));
        }

        @Test
        @DisplayName("check 4 card is same")
        void checkFourCardSame() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.DIAMOND));
            ruleService.isAllCardSame(card);
            assertTrue(ruleService.isAllCardSame(card));
        }

        @Test
        @DisplayName("check 4 card is same out of 6")
        void check4CardOFof6CardSame() {
            List<Card> card = new ArrayList<>();
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.CLUB));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.SPADE));
            card.add(new Card(CardNumberEnum.FIVE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.JACK, CartTypeEnum.HEARTS));
            card.add(new Card(CardNumberEnum.ACE, CartTypeEnum.DIAMOND));
            ruleService.isAllCardSame(card);
            assertTrue(ruleService.isAllCardSame(card));
        }

    }

}
