package work.notech.poker.logic.pattern;

import java.util.List;

import work.notech.poker.logic.Card;

public abstract class BasePattern{
	protected List<Card> cards;
	protected int type;

	public BasePattern(List<Card> cards) {
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getType() {
		return type;
	}

	public int getCardNumber() {
		return cards.size();
	}
	
	public abstract boolean validate(BasePattern o);
}
