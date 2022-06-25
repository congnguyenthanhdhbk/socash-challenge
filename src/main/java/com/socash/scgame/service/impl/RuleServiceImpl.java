package com.socash.scgame.service.impl;

import com.socash.scgame.entity.Card;
import com.socash.scgame.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Override
    public boolean isAllCardSame(final List<Card> card) {
        if (card.size() <= 1)
            return false;

        int numberSame = Math.min(card.size(), 4);
        Collections.sort(card);

        Card c = card.get(0);
        boolean isAllMatch = true;
        for (int i = 1; i < numberSame; i++) {
            if (!c.getNumber().equals(card.get(i).getNumber())) {
                isAllMatch = false;
                break;
            }
        }
        return isAllMatch;
    }

    @Override
    public boolean isCardInSequence(final List<Card> card) {
        if (card.size() <= 1)
            return false;

        Collections.sort(card);
        boolean isAllMatch = true;
        int cardOrder = card.get(0).getNumber().getCardOrder();
        for (int k = 1; k < card.size(); k++) {
            int p = card.get(k).getNumber().getCardOrder();
            if (cardOrder == 14) {
                if (p == 4 || p == 13) {
                    cardOrder = p;
                } else {
                    isAllMatch = false;
                    break;
                }
            } else if ((cardOrder - 1) != p) {
                isAllMatch = false;
                break;

            } else {
                cardOrder = p;
            }
        }
        return isAllMatch;
    }

    @Override
    public boolean twoCardSame(final List<Card> card) {
        if (card.size() <= 1)
            return false;
        Collections.sort(card);
        boolean isAllMatch = false;
        for (int k = 1; k < card.size(); k++) {
            int cardOrder = card.get(k - 1).getNumber().getCardOrder();
            int p = card.get(k).getNumber().getCardOrder();
            if (cardOrder == p) {
                isAllMatch = true;
                break;
            }

        }
        return isAllMatch;
    }
}
