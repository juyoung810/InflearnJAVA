# Java iText API 활용 PDF 프로젝트
> java itext api를 활용해서 pdf 를 생성한다.

## 1. iText API를 이용한 PDF table 만들기
> itextpdf-5.5.13.jar 사용
### 1) iText API 이용한 PDF table 만들기
- 메모리에 PDF 파일을 임시로 만들어서 그 안에 data 만든 다음, 실제 pdf 에쓴다.
- 이떄 임시 파일을 `document` 라고 부른다. + 사이즈 지정 가능
    ```java
    Document doc = new Document(PageSize.A4);
    ```
- doc를 `FileOutputStream` 에 연결해 PDF 파일로 만든다.
- pdf 에 한글로 작성할 떄 폰트를 지정한다. 이때, 외부 폰트를 지정할 수 있고, 수평으로 적을지 수직으로 적을지 설정할 수 있다.
- 각각 font 별로 사이즈를 지정할 수 있다.  
    ```java
  // 문서를 Pdf 로 바꾼다.
    PdfWriter.getInstance(doc,new FileOutputStream(new File("book.pdf"))); // 이 document를 해당 출력 스트림에 연결해서 pdf 파일로 만든다. (연결만 한것)
    // document에 내용 입력 위해 연다.
    doc.open();
    // 한글 폰트
    BaseFont bf = BaseFont.createFont("NGULIM.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED) ; // 폰트이름, 수평 작성, 폰트는 외부 폰트이다.
    // title 폰트, 실제 데이터에 사용할 폰트 다르게 설정한다.
    Font fontTitle = new Font(bf,12); // 크기 지정
    Font fontrows = new Font(bf,10);
    ```

- table를 만든다. 이때, table를 만들면서, column의 갯수를 지정해준다.
- table자체의 width를 설정할 수 있다.
- 각각의 column 마다 width 를 지정할 수 있다.
  ```java
    // table를 만든다.
    PdfPTable table = new PdfPTable(title.length); // column 개수 지정 -> title 길이 만큼
    table.setWidthPercentage(100); // table 의 폭 지정
    float[] colwidth = new float[]{20f,15f,15f,30f};// cell의 폭 지정 (column의 폭)
    table.setWidths(colwidth);
  ```
- 문장을 쓰기 위해, 지정한 문자열을 문장으로 만들어 준다.
  ```java
    // cell안의 실제 글자는 문장으로 만들어 줘야한다.
    cell.setPhrase(new Phrase(header,fontTitle)); // 내용, 폰트 지정
  ```
- cell를 생성하고, 수평 정렬, 수직 정렬을 설정하고, 상하좌우 공백을 지정한 다음 해당 cell를 table에 붙여준다.
- 한 row를 완성할 때 마다, `table.completeRow()` 통해 명시한다.
  ```java
    PdfPCell cell = new PdfPCell(); // cell 만든다.
    cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 수평 정렬 지정
    // 수직 정렬
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    //cell의 상하좌우 공백 설정
    cell.setPadding(10);
    // 상하좌우 각각 padding(여백) 설정한다.
    cell.setPaddingTop(20);
    cell.setPaddingRight(30);
    cell.setPaddingBottom(20);
    cell.setPaddingLeft(30);
    // cell의 배경색 회색 음영으로 지정
    cell.setGrayFill(0.9f);
    
    //cell를 table에 부착시킨다.
    table.addCell(cell);
    ```
           
- table의 column 합치는 방법
  ```java
    cell4.setColspan(2); // 두개의 column 합친다.
  ```
- 문서의 이름을 지정한다
- 생성한 table를 pdf 파일에 부착한다.
- __생성한 문서를 닫은 후 작성해야, 내용이 update 될 수 있다.__  
    ```java
    doc.addTitle("PDF Table Demo");
    doc.add(table); // 문서에 table 부착한다.
    ```  

![resultbookpdfimg](https://github.com/juyoung810/InflearnJAVA/blob/3d039ced8397115da7fb914cafb3190044b67bf8/JavaTPCProject/img/bookpdfresult.JPG)
