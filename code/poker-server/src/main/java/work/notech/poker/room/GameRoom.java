package work.notech.poker.room;

import work.notech.poker.logic.Cards;

import java.util.List;

public class GameRoom {
    private Integer roomIds;
    private List<Integer> clientIds;
    private Cards cards;

    public Integer getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(Integer roomIds) {
        this.roomIds = roomIds;
    }

    public List<Integer> getClientIds() {
        return clientIds;
    }

    public void setClientIds(List<Integer> clientIds) {
        this.clientIds = clientIds;
    }

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }
}
