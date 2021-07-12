
import org.json.JSONArray;
import org.json.JSONObject;

public class Project01_B {
    public static void main(String[] args) {
        // JSON-JAVA(org.json)
        // 객체 만들 필요 없이 JSON 만들 수 있다.

        JSONArray students = new JSONArray();
        JSONObject student = new JSONObject();
        student.put("name","홍길동");
        student.put("phone","010-1111-1111");
        student.put("address","서울"); //{"address":"서울","phone":"010-1111-1111","name":"홍길동"}
        System.out.println(student);
        students.put(student);

        student = new JSONObject();
        student.put("name","나길동");
        student.put("phone","010-2222-2222");
        student.put("address","광주");
        System.out.println(student);

        students.put(student);
        // [ {},{} ] JSONArray 만들어진다 -> JSONObject하나에 담아야한다.
        JSONObject object = new JSONObject();
        object.put("students", students);
        System.out.println(object.toString(2)); //보기 좋게 들여쓰기할 수 있도록

        // 하나의 JSONObject -> JsonArray -> 각각의 JSONObject
    }
}
