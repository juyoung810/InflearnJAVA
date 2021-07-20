import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

// api 참조서 : static map, geocoding 참고!
public class Project01_E {

    // 지도 이미지 생성 메서드
    public static void map_services(String point_x, String point_y, String address,String client_id,String client_secret) {
        // static map 주소 URL -> 이미지 반환해준다.
        String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
        try {
            String pos = URLEncoder.encode(point_x+" "+point_y,"UTF-8");
            String url = URL_STATICMAP;
            // center에 위도,경도 로 들어간다.
            url += "center=" + point_x + ","+point_y;
            url += "&level=16&w=700&h=500";
            // t: tooltip, label: 마커 위에 글자 띄운다. pos: 마커에 띄울 글씨 (위도와 경도 사이에 공백(%20) 들어있음을 유의
            url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(address,"UTF-8");
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
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
                while((read = is.read(bytes))!= -1){
                    // 바이트 읽어 드린 만큼 파일에 쓴다.
                    outputStream.write(bytes,0,read);
                }
                is.close();
            }else{
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while((inputLine = br.readLine())!= null){
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {

        //String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
        Dotenv dotenv = Dotenv.load();
        String client_id = dotenv.get("map_id");
        String client_secret = dotenv.get("map_key");

        // System.in은 Byte code 이기 때문에 InputStreamReader를 거친 후 문자로 바꿔 한줄로 연결할 수 있다.
        BufferedReader io = new BufferedReader((new InputStreamReader(System.in)));
        try {
            System.out.print("주소를 입력하세요: ");
            String address = io.readLine();
            // url이 유효한지 확인하기 위해서
            String addr = URLEncoder.encode(address, "UTF-8");
            String reqUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;

            URL url = new URL(reqUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);

            BufferedReader br;
            int responseCode = con.getResponseCode(); // 200
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String line;
            StringBuffer response = new StringBuffer(); // JSON

            String x = ""; String y = ""; String z = "";
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            JSONTokener tokener = new JSONTokener(response.toString());
            JSONObject object = new JSONObject(tokener);
            System.out.println(object.toString(2));

            JSONArray arr = object.getJSONArray("addresses");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject temp = (JSONObject) arr.get(i);
                System.out.println("address:" + temp.get("roadAddress"));
                System.out.println("jibunAddress" + temp.get("jibunAddress"));
                System.out.println("경도" + temp.get("x"));
                System.out.println("위도" + temp.get("y"));


                x = (String) temp.get("x"); // Object -> String
                y = (String) temp.get("y");
                z = (String) temp.get("roadAddress");
            }

            map_services(x,y,z,client_id,client_secret);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
