package framework;

import org.apache.commons.io.IOUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUtils {

    private HttpURLConnection getConnection(String link)  {
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url
                    .openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setDoOutput(true);

        return  connection;
    }

    public long getSizeOfContent(String link,String path)  {
        HttpURLConnection connection = getConnection(link);
        long size = connection.getContentLength();
        downLoadFile(connection,path);
        return size;
    }

    private void downLoadFile(HttpURLConnection connection, String path) {
        InputStream content = null;
        try {
            content = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            IOUtils.copy(content, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            content.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
