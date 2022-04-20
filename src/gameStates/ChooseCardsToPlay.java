package gameStates;

import card.ACard;
import enums.EText;
import utils.ArrayList;
import utils.Flow;
import utils.Interfaces.IImageViewAble;
import utils.SelectImageViewManager;

public class ChooseCardsToPlay extends AGameState {

	@Override
	public void execute() {
		showText();
	}

	@Override
	protected void handleCardPressedHand(ACard card) {

		concealText();

		if (card.isSelected())
			handleCardIsSelected(card);
		else
			handleCardIsNotSelected(card);

	}

	@Override
	protected void executeTextOption(EText eText) {

		ArrayList<IImageViewAble> listImageViewAbles = SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles();

		ArrayList<ACard> list = new ArrayList<>();

		for (IImageViewAble imageViewAble : listImageViewAbles)
			list.addLast((ACard) imageViewAble);

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		Flow.INSTANCE.proceed();

	}

	private void handleCardIsNotSelected(ACard card) {

		ArrayList<IImageViewAble> listImageViewAble = SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles();

		// if non selected, select

		if (listImageViewAble.isEmpty()) {

			card.reverseSelectImageView();
			showText();
			return;

		}

		ArrayList<ACard> listCards = new ArrayList<>();

		for (IImageViewAble imageViewAble : listImageViewAble)
			listCards.addLast((ACard) imageViewAble);

		// if selected = 1, try to pair with ace

		if (listCards.size() == 1)
			if (listCards.getFirst().getAttack() == 1 || card.getAttack() == 1) {

				card.reverseSelectImageView();
				showText();
				return;

			}

		// if selected = 2 one of them is ace and the other is not

		if (listCards.size() == 2)
			if (listCards.getFirst().getAttack() != listCards.getLast().getAttack()) {
				showText();
				return;
			}

		// all selected same number. If the new is not, return

		if (card.getAttack() != listCards.getFirst().getAttack()) {
			showText();
			return;
		}

		// if sum > 10 return

		int sumValue = card.getAttack();

		for (ACard aCard : listCards)
			sumValue += aCard.getAttack();

		if (sumValue > 10) {
			showText();
			return;
		}

		// select card

		card.reverseSelectImageView();
		showText();

	}

	private void handleCardIsSelected(ACard card) {
		card.reverseSelectImageView();
		showText();
	}

	private void showText() {

		EText.CHOOSE_CARDS_TO_PLAY.show();

		if (SelectImageViewManager.INSTANCE.sizeSelectImageViewAbles() == 0)
			return;

		EText.CONTINUE.show();

	}

}
