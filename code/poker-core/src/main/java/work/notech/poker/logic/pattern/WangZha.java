package work.notech.poker.logic.pattern;

import java.util.List;

import work.notech.poker.logic.Card;

public class WangZha extends BasePattern {

	public WangZha(List<Card> cards) {
		super(cards);
		type = 10;
	}

	@Override
	public boolean validate(BasePattern o) {
		return false;
	}
}
