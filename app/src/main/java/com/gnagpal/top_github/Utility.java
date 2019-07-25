package com.gnagpal.top_github;

import android.graphics.Bitmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Utility {

     static Bitmap getBitmap(String urlString){
        URL url = null;
        Bitmap bitmap = null;
        try {
            url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setUseCaches(true);
            Object response = connection.getContent();
            if (response instanceof Bitmap) {
                bitmap = (Bitmap)response;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {

            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                //Read byte from input stream

                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;

                //Write byte from output stream
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
}
