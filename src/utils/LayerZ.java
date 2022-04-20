package utils;

import enums.ELayerZ;
import utils.Interfaces.IUpdateAble;

public enum LayerZ implements IUpdateAble {

	INSTANCE;

	private HashMap<ELayerZ, ArrayList<ImageView>> layerZ = new HashMap<>();
	private HashMap<ImageView, javafx.scene.image.ImageView> listImageViewsFX = new HashMap<>();
	private ArrayList<TextIndicator> textIndicators = new ArrayList<>();

	private LayerZ() {
		addLayers();
	}

	private void addLayers() {

		for (ELayerZ eLayerZ : ELayerZ.values())
			this.layerZ.put(eLayerZ, new ArrayList<ImageView>());

	}

	public void addImageViewAbleToLayer(ImageView imageView, ELayerZ eLayerZ,
			javafx.scene.image.ImageView imageViewFX) {

		this.layerZ.getValue(eLayerZ).addLast(imageView);
		this.listImageViewsFX.put(imageView, imageViewFX);

	}

	public void addTextIndicator(TextIndicator textIndicator) {
		this.textIndicators.addLast(textIndicator);
	}

	public void toFrontImageview(ImageView imageView) {

		ArrayList<ImageView> list = getListContainingImageViewAble(imageView);
		list.remove(imageView);
		list.addLast(imageView);
		toFront();

	}

	public void toBackImageview(ImageView imageView) {

		ArrayList<ImageView> list = getListContainingImageViewAble(imageView);
		list.remove(imageView);
		list.addFirst(imageView);
		toFront();

	}

	private ArrayList<ImageView> getListContainingImageViewAble(ImageView imageView) {

		for (ELayerZ eLayerZ : ELayerZ.values()) {

			if (eLayerZ.equals(ELayerZ.VISIBILITY_FALSE))
				continue;

			ArrayList<ImageView> list = this.layerZ.getValue(eLayerZ);

			if (!list.contains(imageView))
				continue;

			return list;

		}

		ShutDown.INSTANCE.execute();

		return null;

	}

	private void toFront() {
		AnimationTimerFX.INSTANCE.updateNextFrame(this);
	}

	public void setVisible(boolean value, ImageView imageView) {

		ArrayList<ImageView> list = this.layerZ.getValue(ELayerZ.VISIBILITY_FALSE);

		if (value)
			if (list.contains(imageView))
				list.remove(imageView);

		if (!value)
			if (!list.contains(imageView))
				list.addFirst(imageView);

		toFront();

	}

	@Override
	public void update() {

		for (ELayerZ eLayerZ : ELayerZ.values())
			for (ImageView imageView : this.layerZ.getValue(eLayerZ)) {

				if (this.layerZ.getValue(ELayerZ.VISIBILITY_FALSE).contains(imageView))
					continue;

				this.listImageViewsFX.getValue(imageView).toFront();

			}

		for (TextIndicator textIndicator : this.textIndicators)
			textIndicator.toFront();

	}

}
