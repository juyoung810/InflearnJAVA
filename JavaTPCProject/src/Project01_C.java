import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Project01_C {
    public static void main(String[] args) {
        // 파일에서 데이터 읽어온다.
        String src = "info.json";
        // IO -> Stream(스트림)
        // class가 있는 경로에서 file를 연결해서 객체를 가져와라 (스트림으로)
        InputStream is = Project01_C.class.getResourceAsStream(src);
        if(is == null)
        {
            throw  new NullPointerException("Cannot find resource file");
        }
        //tokener 이용해서 스트림으로 받아온 것 메모리에 올린다.
        JSONTokener tokener = new JSONTokener(is);
        // 하나의 JSONObject -> JsonArray -> 각각의 JSONObject
        JSONObject object = new JSONObject(tokener);
        JSONArray students = object.getJSONArray("students"); // key 값 넣어준다.
        for(int i = 0; i< students.length();i++)
        {
            JSONObject student = (JSONObject) students.get(i); // downCasting
            System.out.print(student.get("name") + "\t");
            System.out.print(student.get("address") + "\t");
            System.out.print(student.get("phone") + "\n");
        }

    }
}
