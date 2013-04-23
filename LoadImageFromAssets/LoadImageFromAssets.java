
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

/**
 * Class for load easily images stored in assets
 * @author Fernando Valle
 * 
 */
public class LoadImageFromAssets {
	
	Context context;
	int l_height=-1, l_width=-1;
	int p_height=-1, p_width=-1;
	boolean landscape = false;
	boolean portrait = false;
	
	/**
	 * 
	 * @param c Context
	 */
	public LoadImageFromAssets(Context c)
	{
		context = c;
	}
	
	/**
	 * load the image source from assets to iv, you can load from a folder in assets, 
	 * example /assets/images/image1.jpg => imgPath = "images/image1.jpg" 
	 * @param iv ImageView where show the image
	 * @param imgPath
	 */
	public  void loadImage(ImageView iv, String imgPath)
	{
		iv.setImageDrawable(getDrawable(imgPath));
	}
	
	/**
	 * Resize the image in the case that in the original image width > height 
	 * @param w width of the image that is showing
	 * @param h height of the image that is showing
	 */
	public  void resizeIfLandscape(int w,int h)
	{
		l_height = h;
		l_width = w;
		landscape = true;
	}
	
	/**
	 * Resize the image in the case that in the original image width < height 
	 * @param w width of the image that is showing
	 * @param h height of the image that is showing
	 */
	public  void resizeIfPortrait(int w,int h)
	{
		p_height = h;
		p_width = w;
		portrait = true;
	}
			
	/**
	 * For obtain the Drawable
	 * @param url The url image.
	 * @return Returns the drawable associated to this image.
	 */
	private  Drawable getDrawable(String path) {  
	        InputStream is = getInputStream(path);
	        Drawable drawable = Drawable.createFromStream(is, path);
	        
	        Bitmap d = ((BitmapDrawable)drawable).getBitmap();
	        Bitmap bitmapOrig = d;
	        if(landscape)
	        {
	        	if(d.getHeight() < d.getWidth())
	        	{
		        	bitmapOrig = Bitmap.createScaledBitmap(d, l_width, l_height, false);
		        }
	        }
	        else if(portrait)
	        {
	        	if(drawable.getBounds().height() > drawable.getBounds().width())
	        	{
		        	bitmapOrig = Bitmap.createScaledBitmap(d, p_width, p_height, false);
		        }	        	
	        }
	        
	       return new BitmapDrawable(context.getResources(),bitmapOrig);  
	}  
	
	


	/**
	 * Read the file and get the InputStream
	 * @param path
	 * @return
	 */
	private  InputStream getInputStream(String path) {
		
		InputStream is = null;
		try {
			is = context.getAssets().open(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return is;
	}

}
