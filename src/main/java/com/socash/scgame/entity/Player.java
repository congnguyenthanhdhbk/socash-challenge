package com.socash.scgame.entity;

public class Player implements Comparable<Player>{
    private int playerId;
    private String playerName;
    private int points;
    private String result;

    public Player() {
    }

    public Player(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return points;
    }

    public String getResult() {
        return result;
    }

    @Override
    public int compareTo(Player o) {
        if (this.getPoints() == o.getPoints()) {
            return 0;
        }
        if (this.getPoints() > o.getPoints()) {
            return 1;
        }
        return -1;
    }
    @Override
    public String toString() {
        return "Player [playerId=" + playerId + ", playerName=" + playerName
                +  ", result=" + result + "]";
    }
}
