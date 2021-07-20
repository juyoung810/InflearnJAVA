import kr.inflearn.ExcelVO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project03_A {
    public static void main(String[] args) {
        // 이 폴더 내에 존재하기 때문에 이름만 넣어도 된다
        String fileName = "bookList.xls";
        List<ExcelVO> data = new ArrayList<ExcelVO>();
        try(FileInputStream fis = new FileInputStream(fileName)){
            // excel 파일을 읽어서 메모리에 올려준다 -> 이 메모리에 올라간 것(가상의 excel)을 workbook 이라고 한다.
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            // excel에서 첫번째 sheet를 빼온다
            HSSFSheet sheet = workbook.getSheetAt(0);
            // sheet에서 row를 다 가져온다. (열거형으로 가져온다)
            Iterator<Row> rows = sheet.rowIterator();
            // 첫번째는 column 이름이름이므로 하나 이동
            rows.next();
            // 각 row의 정보를 받을 임시 배열
            String[] imsi = new String[5];
            // row 정보를 다 가져온다.
            // row 있으면 다 가져온다,
            while(rows.hasNext()){
                HSSFRow row = (HSSFRow) rows.next(); // Row type으로 return 하기 때문에 downCasting 해준다.
                // row 내의 cell을 다 가져온다.
                Iterator<Cell> cells = row.cellIterator();
                int i = 0;
                while(cells.hasNext()){
                    HSSFCell cell = (HSSFCell) cells.next();
                    imsi[i] = cell.toString();
                    i++;
                }
                // 한번에 데이터를 묶는다.
                ExcelVO vo = new ExcelVO(imsi[0],imsi[1],imsi[2], imsi[3],imsi[4]);
                // arrayList에 넣는다.
                data.add(vo);
            }
            showExcelData(data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void showExcelData(List<ExcelVO> data) {
        for(ExcelVO excelVo : data){
            System.out.println(excelVo);
        }
    }

}
