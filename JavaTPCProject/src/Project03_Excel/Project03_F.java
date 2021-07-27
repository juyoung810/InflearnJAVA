package Project03_Excel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Project03_F {
    public static void main(String[] args) {
        // 데이터 입력 여부 받는다.
        ExcelDAO dao = new ExcelDAO();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("입력처리(I)/종료(E): ");
            String sw = br.readLine();
            switch (sw){
                case "I":
                    dao.excel_input();
                    break;
                case "E":
                    System.out.println("프로그램 종료");
                    System.exit(0);
                    break;
                default:
                    System.out.print("I or E input");


            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
