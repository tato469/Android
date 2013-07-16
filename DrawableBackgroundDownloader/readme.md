DrawableBackgroundDownloader
=======

This Class helps to download images and assign the drawable to an ImageView.

Is easy to use just declare:
	DrawableBackgroundDownloader drawableDownloader = new DrawableBackgroundDownloader();
And where do you want to use:
	drawableDownloader.loadDrawable(String urlImage, ImageView iView,Drawable drawable);
Where drawable is a Drawable that is showing during image download.