import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;




/**
 * This Class is for download images and assign the drawable to an ImageView.
 * 
 */
public class DrawableBackgroundDownloader {    

private final Map<String, Drawable> mCache = new HashMap<String, Drawable>();   
private final LinkedList <Drawable> mChacheController = new LinkedList <Drawable> ();
private ExecutorService mThreadPool;  
private final Map<ImageView, String> mImageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());  
public static int MAX_CACHE_SIZE = 80; 
public int THREAD_POOL_SIZE = 3;



/**
 * Constructor
 */
public DrawableBackgroundDownloader() {  
    mThreadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);  
}  


/**
 * Clears all instance data and stops running threads
*/ 
public void Reset() {
    ExecutorService oldThreadPool = mThreadPool;
    mThreadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    oldThreadPool.shutdownNow();

    mChacheController.clear();
    mCache.clear();
    mImageViews.clear();
}  

/**
 * Load the drawable associated to a url and assign it to an image, you can set a placeholder to replace this drawable.
 * @param url Is the url of the image.
 * @param imageView The image to assign the drawable.
 * @param placeholder A drawable that is show during the image is downloading.
 */
public void loadDrawable(final String url, final ImageView imageView,Drawable placeholder) {  
    if(!mImageViews.containsKey(url))
        mImageViews.put(imageView, url);  
    Drawable drawable = getDrawableFromCache(url);  

    // check in UI thread, so no concurrency issues  
    if (drawable != null) {  
        //Log.d(null, "Item loaded from mCache: " + url);  
        imageView.setImageDrawable(drawable);  
    } else {  
        imageView.setImageDrawable(placeholder);  
        queueJob(url, imageView, placeholder);  
    }  
} 


/**
 * Return a drawable from the cache.
 * @param url url of the image.
 * @return a Drawable in case that the image exist in the cache, else returns null.
 */
public Drawable getDrawableFromCache(String url) {  
    if (mCache.containsKey(url)) {  
        return mCache.get(url);  
    }  

    return null;  
}


/**
 * Save the image to cache memory.
 * @param url The image url
 * @param drawable The drawable to save.
 */
private synchronized void putDrawableInCache(String url,Drawable drawable) {  
    int chacheControllerSize = mChacheController.size();
    if (chacheControllerSize > MAX_CACHE_SIZE) 
        mChacheController.subList(0, MAX_CACHE_SIZE/2).clear();

    mChacheController.addLast(drawable);
    mCache.put(url, drawable);

}  

/**
 * Queue the job to download the image.
 * @param url Image url.
 * @param imageView The ImageView where is assigned the drawable.
 * @param placeholder The drawable that is show during the image is downloading. 
 */
private void queueJob(final String url, final ImageView imageView,final Drawable placeholder) {  
    /* Create handler in UI thread.  */
    final Handler handler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            String tag = mImageViews.get(imageView);  
            if (tag != null && tag.equals(url)) {
                if (imageView.isShown())
                    if (msg.obj != null) {
                        imageView.setImageDrawable((Drawable) msg.obj);  
                    } else {  
                        imageView.setImageDrawable(placeholder);  
                        //Log.d(null, "fail " + url);  
                    } 
            }  
        }  
    };  

    mThreadPool.submit(new Runnable() {  
        public void run() {  
            final Drawable bmp = downloadDrawable(url);
            // if the view is not visible anymore, the image will be ready for next time in cache
            if (imageView.isShown())
            {
                Message message = Message.obtain();  
                message.obj = bmp;
                //Log.d(null, "Item downloaded: " + url);  

                handler.sendMessage(message);
            }
        }  
    });  
}  


/**
 * Method that download the image
 * @param url The url image.
 * @return Returns the drawable associated to this image.
 */
private Drawable downloadDrawable(String url) {  
    try {  
        InputStream is = getInputStream(url);

        Drawable drawable = Drawable.createFromStream(is, url);
        putDrawableInCache(url,drawable);  
        return drawable;  

    } catch (MalformedURLException e) {  
        e.printStackTrace();  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  

    return null;  
}  

/**
 * This method manage the connection to download the image.
 * @param urlString url of the image.
 * @return Returns an InputStream associated with the url image.
 * @throws MalformedURLException
 * @throws IOException
 */
private InputStream getInputStream(String urlString) throws MalformedURLException, IOException {
    URL url = new URL(urlString);
    URLConnection connection;
    connection = url.openConnection();
    connection.setUseCaches(true); 
    connection.connect();
    InputStream response = connection.getInputStream();

    return response;
}
}