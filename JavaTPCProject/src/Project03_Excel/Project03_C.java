package Project03_Excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.util.Iterator;

public class Project03_C {
    public static void main(String[] args) {
        // 주어진 엑셀에서 정보 가지고 온다.
        String fileName = "cellDataType.xls";
        try(FileInputStream fis = new FileInputStream(fileName)){
            // 메모리에 workbook 이라는 이름으로 엑셀 파일 만들었다.
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            // workbook 에서 sheet 가져온다.
            HSSFSheet sheet =workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            while(rows.hasNext()){
                HSSFRow row =(HSSFRow) rows.next();
                // row내의 cell를 하나씩 가져온다.
                Iterator<Cell> cells = row.cellIterator();
                while(cells.hasNext()){
                    HSSFCell cell = (HSSFCell) cells.next();
                    // cell 에 있는 type을 확인해서 type에 맞게 함수를 사용해서 data를 가져온다.
                    CellType type = cell.getCellType();
                    if (type == CellType.STRING) {
                        // 행렬의 위치 정보 출력
                        System.out.println("["+cell.getRowIndex()+","
                                +cell.getColumnIndex() + "] = STRING; Value = "
                                + cell.getRichStringCellValue().toString() );

                    }else if(type == CellType.NUMERIC){
                        System.out.println("["+cell.getRowIndex()+","
                                +cell.getColumnIndex() + "] = NUMERIC; Value = "
                                + cell.getNumericCellValue());
                    }else if(type == CellType.BOOLEAN){
                        System.out.println("["+cell.getRowIndex()+","
                                +cell.getColumnIndex() + "] = BOOLEAN; Value = "
                                + cell.getBooleanCellValue() );
                    }else if(type == CellType.BLANK){ // 아무것도 없는 type
                        System.out.println("["+cell.getRowIndex()+","
                                +cell.getColumnIndex() + "] = BLANK CELL");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
