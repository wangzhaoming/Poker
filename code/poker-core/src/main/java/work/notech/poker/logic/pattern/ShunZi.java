package work.notech.poker.logic.pattern;

import java.util.List;

import work.notech.poker.logic.Card;

public class ShunZi extends BasePattern {

	public ShunZi(List<Card> cards) {
		super(cards);
		type = 7;
	}

	@Override
	public boolean validate(BasePattern o) {
		if (o.getType() == 10) {
			return true;
		}
		if (o.getType() == 4) {
			return true;
		}

		if (o.getType() == type && o.getCardNumber() == cards.size()) {
			if (cards.get(0).getValue() < o.getCards().get(0).getValue()) {
				return true;
			}
		}

		return false;
	}

}
