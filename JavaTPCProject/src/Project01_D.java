import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;

public class Project01_D {
    public static void main(String[] args) {
        //String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
        String client_id = "{env.map_id}";
        String client_secret = "{env.map_key}";
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
            StringBuffer response = new StringBuffer();
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
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
