package com.socash.scgame.entity;

import com.socash.scgame.enumeration.CardNumberEnum;
import com.socash.scgame.enumeration.CartTypeEnum;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Card implements Comparable<Card> {
    private final CardNumberEnum number;
    private final CartTypeEnum type;

    public Card(final CardNumberEnum number, final CartTypeEnum type) {
        this.number = number;
        this.type = type;
    }

    public CardNumberEnum getNumber() {
        return number;
    }

    public CartTypeEnum getType() {
        return type;
    }

    public static Stack<Card> getPackOfCards() {
        Stack<Card> cards = new Stack<>();
        for (CartTypeEnum type : CartTypeEnum.values()) {
            for (CardNumberEnum number : CardNumberEnum.values()) {
                Card card = new Card(number, type);
                cards.add(card);
            }
        }
        return cards;
    }

    public static void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    @Override
    public int compareTo(Card o) {
        if (this.getNumber() == o.getNumber()) {
            return 0;
        }

        if (this.getNumber().getCardOrder() > o.getNumber().getCardOrder()) {
            return -1;
        }

        return 1;
    }

    @Override
    public String toString() {
        return "CARD [ Number=" + number + "]";
    }
}
