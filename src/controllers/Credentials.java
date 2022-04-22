package controllers;

import utils.ArrayList;
import utils.Background;
import utils.Enums.RearrangeTypeEnum;
import utils.SelectImageView;
import utils.Vector2;

public enum Credentials {

	INSTANCE;

	public final String primaryStageTitle = "Regicide", numbersImageViewColor = "black";
	public final boolean colliderVisibility = true;
	public final double gapBetweenBorders = 25, textHeight = 50, selectEventHandlerAbleWidth = 100,
			listQuantityRatioDimensions = 0.5, animationStep = 4;
	public ArrayList<Class<?>> lineCastExcludeList = new ArrayList<Class<?>>();
	public RearrangeTypeEnum rearrangeTypeEnumText = RearrangeTypeEnum.PIVOT;
	public Vector2 dFrame, dGapBetweenComponents, dGapBetweenComponentsLineCast;
	public Vector2 cTextPanel;

	public Vector2 dCard, dIconIndicator, dIconNumber;
	public Vector2 cHand, cDeckTavern, cDiscardPileTavern, cDeckCastle, cAttack, cLife,
			cCardsPlayed, cJesters;

	private Credentials() {

		this.lineCastExcludeList.addLast(SelectImageView.class);
		this.lineCastExcludeList.addLast(Background.class);

		double x = 0, y = 0;

		this.dFrame = new Vector2(1920, 1368);
		this.dGapBetweenComponents = new Vector2(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		this.cTextPanel = new Vector2(x, y);

		// card

		y = 320;
		x = 410 * y / 574;
		this.dCard = new Vector2(x, y);

		// frame

		x = 8 * this.dCard.x;
		x += 7 * this.dGapBetweenComponents.x;
		x += 2 * this.gapBetweenBorders;
		y = 2 * this.gapBetweenBorders;
		y += 2 * this.dCard.y;
		y += this.dGapBetweenComponents.y;
		this.dFrame = new Vector2(x, y);

		// hand

		x = this.dFrame.x / 2;
		y = this.dFrame.y;
		y -= this.gapBetweenBorders;
		y -= this.dCard.y / 2;
		this.cHand = new Vector2(x, y);

		// discard pile tavern

		x = this.gapBetweenBorders;
		x += this.dCard.x / 2;
		y = this.gapBetweenBorders;
		y += this.dCard.y / 2;
		this.cDiscardPileTavern = new Vector2(x, y);

		// deck tavern

		x = this.cDiscardPileTavern.x;
		x += this.dCard.x;
		x += this.dGapBetweenComponents.x;
		y = this.cDiscardPileTavern.y;
		this.cDeckTavern = new Vector2(x, y);

		// deckCastle

		x = this.dFrame.x / 2;
		y = this.gapBetweenBorders;
		y += this.dCard.y / 2;
		this.cDeckCastle = new Vector2(x, y);

		// icon indicator

		y = this.dCard.y * 0.4;
		this.dIconIndicator = new Vector2(y, y);

		// icon number

		x = this.dIconIndicator.x / 2;
		y = this.dIconIndicator.y / 2;
		this.dIconNumber = new Vector2(x, y);

		// attack

		x = this.cDeckCastle.x;
		x += this.dCard.x / 2;
		x += 2 * this.dGapBetweenComponents.x;
		x += this.dIconIndicator.x / 2;
		y = this.gapBetweenBorders;
		y += (this.dCard.y - 2 * this.dIconIndicator.y) / 3 + this.dIconIndicator.x / 2;
		this.cAttack = new Vector2(x, y);

		// life

		x = this.cAttack.x;
		y = this.gapBetweenBorders;
		y += 2 * (this.dCard.y - 2 * this.dIconIndicator.y) / 3 + 3 * this.dIconIndicator.x / 2;
		this.cLife = new Vector2(x, y);

		// text

		x = this.cAttack.x;
		x += 2 * this.dGapBetweenComponents.x;
		x += this.dIconIndicator.x / 2;
		x += 2 * this.dIconNumber.x;
		x += 2 * this.dGapBetweenComponents.x;
		y = this.cDeckCastle.y;
		y -= this.textHeight / 2;
		this.cTextPanel = new Vector2(x, y);

		// cards played

		x = this.dFrame.x;
		x -= this.gapBetweenBorders;
		x -= this.dCard.x / 2;
		y = this.gapBetweenBorders;
		y += this.dCard.y / 2;
		this.cCardsPlayed = new Vector2(x, y);

		// jester

		x = (this.cDeckCastle.x + this.cDeckTavern.x) / 2;
		y = this.cDiscardPileTavern.y;
		this.cJesters = new Vector2(x, y);

	}

}
