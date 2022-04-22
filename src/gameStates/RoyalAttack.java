package gameStates;

import card.ACard;
import controllers.IconsNumbers;
import controllers.Model;
import enums.EText;
import utils.ArrayList;
import utils.Flow;
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

	@Override
	protected void executeTextOption(EText eText) {

		if (SelectImageViewManager.INSTANCE.sizeSelectImageViewAbles() > 0)
			Model.INSTANCE.discardHandCardsSelected();

		Flow.INSTANCE.proceed();

	}

	private void showText() {

		concealText();

		EText.ROYAL_ATTACK.show();

		int royalAttack = IconsNumbers.ATTACK.getValue();

		if (royalAttack == 0) {

			EText.CONTINUE.show();
			return;

		}

		EText.DISCARD_CARDS.show();

		ArrayList<ACard> list = new ArrayList<>();

		for (IImageViewAble imageViewAble : SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles())
			list.addLast((ACard) imageViewAble);

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
