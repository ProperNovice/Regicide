package gameStates;

import card.ACard;
import controllers.IconsNumbers;
import controllers.Lists;
import controllers.Model;
import enums.EText;
import utils.ArrayList;
import utils.Flow;
import utils.Interfaces.IImageViewAble;
import utils.SelectImageViewManager;

public class RoyalAttack extends AGameState {

	private boolean skipAttack = false;

	@Override
	public void execute() {

		if (gameIsLost()) {

			EText.ROYAL_ATTACK.show();
			Flow.INSTANCE.getFlow().clear();
			Flow.INSTANCE.getFlow().addFirst(EndGameLost.class);
			Flow.INSTANCE.proceed();

		} else
			showText();

	}

	@Override
	protected void handleCardPressedHand(ACard card) {

		if (this.skipAttack)
			return;

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

			EText.SKIP.show();
			this.skipAttack = true;
			return;

		}

		this.skipAttack = false;

		if (IconsNumbers.ATTACK.getValue() <= Model.INSTANCE.getTotalValueInHand())
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

	private boolean gameIsLost() {

		if (!Lists.INSTANCE.jesters.getArrayList().isEmpty())
			return false;
		else if (IconsNumbers.ATTACK.getValue() <= Model.INSTANCE.getTotalValueInHand())
			return false;
		else if (!Lists.INSTANCE.jesters.getArrayList().isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public void handleJesterPressed() {

		concealText();
		Model.INSTANCE.playJester();
		showText();

	}

}
