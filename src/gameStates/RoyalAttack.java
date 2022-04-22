package gameStates;

import card.ACard;
import controllers.IconsNumbers;
import enums.EText;
import utils.ArrayList;
import utils.Interfaces.IImageViewAble;
import utils.SelectImageViewManager;

public class RoyalAttack extends AGameState {

	@Override
	public void execute() {
		showText();
	}

	@Override
	protected void handleCardPressedHand(ACard card) {
		card.reverseSelectImageView();
		showText();
	}

	private void showText() {

		concealText();

		EText.ROYAL_PHASE.show();
		EText.ATTACK.showAdditionally(IconsNumbers.ATTACK.getValue());
		EText.DISCARD_CARDS.show();

		ArrayList<ACard> list = new ArrayList<>();

		for (IImageViewAble imageViewAble : SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles())
			list.addLast((ACard) imageViewAble);

		int royalAttack = IconsNumbers.ATTACK.getValue();
		int playerDefence = 0;

		for (ACard card : list)
			playerDefence += card.getValue();

		EText eText = null;

		if (royalAttack > playerDefence)
			eText = EText.VOID;
		else
			eText = EText.CONTINUE;

		eText.show();

	}

}
