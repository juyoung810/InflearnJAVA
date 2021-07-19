import io.github.cdimascio.dotenv.Dotenv;
import kr.inflearn.AddressVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

// 이벤트 버튼 클릭하면 실행된다.
public class NaverMap implements ActionListener {
    Project01_F naverMap;

    public NaverMap(Project01_F naverMap) {
        this.naverMap = naverMap;
    }

    @Override
    // 주소를 서버로 받아서 경도 위도 가져와야한다.
    public void actionPerformed(ActionEvent e)
    {
        Dotenv dotenv = Dotenv.load();
        String client_id = dotenv.get("map_id");
        String client_secret = dotenv.get("map_key");
        AddressVO vo = null;
        try{
            String address = naverMap.address.getText(); // 우리가 입력한 주소 객체가 넘어온다.
            String addr = URLEncoder.encode(address, "UTF-8");
            String apiUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);

            int responseCode = con.getResponseCode(); // 200
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // JSON 안 정보 가져온다.
            JSONTokener tokener = new JSONTokener(response.toString());
            JSONObject object = new JSONObject(tokener);
            System.out.println(object.toString(2));

            JSONArray arr = object.getJSONArray("addresses");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject temp = (JSONObject) arr.get(i);
                // AddressVO 객체에 주소 정보를 저장
                vo = new AddressVO();
                vo.setRoadAddress((String)temp.get("roadAddress"));
                vo.setJibunAddress((String)temp.get("jibunAddress") );
                vo.setX((String)temp.get("x"));
                vo.setY((String) temp.get("y"));
            }
            // -> vo 정보를 가지고 map을 만들기 위해서
            map_service(vo,client_id,client_secret);
        }catch (Exception err)
        {
            System.out.println(err);
        }
    }

    private void map_service(AddressVO vo,String client_id,String client_secret) {
        String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
        try {
            String pos = URLEncoder.encode(vo.getX()+" "+vo.getY(),"UTF-8");
            String url = URL_STATICMAP;
            // center에 위도,경도 로 들어간다.
            url += "center=" + vo.getX() + ","+vo.getY();
            url += "&level=16&w=700&h=500";
            // t: tooltip, label: 마커 위에 글자 띄운다. pos: 마커에 띄울 글씨 (위도와 경도 사이에 공백(%20) 들어있음을 유의
            url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(vo.getRoadAddress(),"UTF-8");
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID",client_id );
            con.setRequestProperty("X-NCP-APIGW-API-KEY",client_secret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode == 200) // 정상 호출
            {
                InputStream is = con.getInputStream();
                int read = 0;
                // 이미지 파일이므로 바이트 파일 만들기
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 파일 생성
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(tempname + ".jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read = is.read(bytes)) != -1) {
                    // 바이트 읽어 드린 만큼 파일에 쓴다.
                    outputStream.write(bytes, 0, read);
                }
                is.close();
                // GUI 에 이미지 뿌리기 위해서
                ImageIcon img = new ImageIcon(f.getName());
                naverMap.imageLabel.setIcon(img);
                naverMap.resAddress.setText(vo.getRoadAddress());
                naverMap.jibunAddress.setText(vo.getJibunAddress());
                naverMap.resX.setText(vo.getX());
                naverMap.resY.setText(vo.getY());
            }else{ //에러 발생
                System.out.println(responseCode);
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

