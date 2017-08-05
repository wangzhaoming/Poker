package poker.logic.pattern;

import java.util.List;

import poker.logic.Card;

public class One extends BasePattern {

	public One(List<Card> cards) {
		super(cards);
		type = 1;
	}

	@Override
	public boolean validate(BasePattern o) {
		if (o.getType() == 10) {
			return true;
		}
		if (o.getType() == 4) {
			return true;
		}

		if (o.getType() == type) {
			if (cards.get(0).getValue() < o.getCards().get(0).getValue()) {
				return true;
			}
		}

		return false;
	}
}
