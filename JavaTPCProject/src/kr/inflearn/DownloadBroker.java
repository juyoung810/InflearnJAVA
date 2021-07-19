package kr.inflearn;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadBroker implements Runnable{
    String address;
    String fileName;

    public DownloadBroker(String address, String fileName) {
        this.address = address;
        this.fileName = fileName;
    }
    @Override
    public void run()
    {
        try{
            // 쓰기를 위한 Stream을 연다.
            FileOutputStream fos = new FileOutputStream(fileName);
            // 속도 빠르게 하기 위해
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            //받아온 URL 을 다운로드 하기 위해 연결한다.
            URL url = new URL(address);
            // 읽어오기 위한 stream을 연다.
            InputStream is = url.openStream();
            BufferedInputStream input = new BufferedInputStream(is);
            int data; // byte 단위로 파일을 읽어드린다.
            while((data = input.read())!= -1)
            {
                bos.write(data);
            }
            bos.close();
            input.close();
            System.out.println("download complete...");
            System.out.println(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
