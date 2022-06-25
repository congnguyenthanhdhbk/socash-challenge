package com.socash.scgame.service;

import com.socash.scgame.entity.Card;

import java.util.List;

public interface RuleService {
    boolean isAllCardSame(List<Card> card);
    boolean isCardInSequence(List<Card> card);
    boolean twoCardSame(List<Card> card);
}
