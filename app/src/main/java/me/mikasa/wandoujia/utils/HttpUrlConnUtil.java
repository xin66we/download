package me.mikasa.wandoujia.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlConnUtil {
    public static String doGet(String url,String encode){
        String string=null;
        HttpURLConnection connection=null;
        InputStream is=null;
        try {
            URL u=new URL(url);
            connection=(HttpURLConnection)u.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            if (connection.getResponseCode()==200){
                is=connection.getInputStream();
                string=stream2string(is,encode);
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (is!=null){
                    is.close();
                }
                if (connection!=null){
                    connection.disconnect();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return string;
    }
    private static String stream2string(InputStream is,String encode){
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] b=new byte[1024];
        int len=0;
        try {
            if (encode==null||encode.equals("")){
                encode="utf-8";
            }
            while ((len = is.read(b)) > 0){
                out.write(b,0,len);
            }
            return out.toString(encode);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
