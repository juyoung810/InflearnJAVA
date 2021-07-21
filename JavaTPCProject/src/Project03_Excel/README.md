# Java Naver Search API 활용 Excel 프로젝트
> 엑셀의 책 정보 읽어서 가져온 후 Naver Search API 와 연결해서 추가 정보 얻어 새로 Excel에 정보를 기입한다.

## Excel 다루기
### 1. Excel 파일 Reading 하기
> Excel API 사용
> 1. poi-4.1.0.jar
> 2. commons-codec-1.12jar 
> 3. commons-collections4-4.2jar

- excel의 Data를 읽어오기 위해 `HSSFWorkbook` 을 통해 가상의 메모리에 excel 올린다.
- 메모리에 올린 excel에서 sheet를 가져온다 `HSSFSheet`
- 해당 sheet의 모든 cell의 정보를 가져오기 위해
```java
    Iterator rows = sheet.rowIterator();
    while(rows.hasNext()){
        HSSFROW row = (HSSFRow) rows.next();
        Iterator cells = row.cellIterator();
        while(cells.hasNext()){
            HSSFCell cell = (HSSFCEll) cells.next();
        }
        }
```
### 2. Excel에 image 저장하기
> 메모리에 가상의 Excel 생성해서, 한 cell에 이미지 저장한 후 excel file 에 write 한다.
- 가상의 Excel 생성 후, 이미지 읽어와서 저장한다.
```java
            //  가상의 excel를 만든다.
            Workbook wb = new HSSFWorkbook();
            // 가상의 excel sheet를 만든다.
            Sheet sheet = wb.createSheet("My Sample Excel");
            // 이미지를 is로 읽어온다.
            InputStream is = new FileInputStream("pic.jpg");
            // 이미지를 바이트 단위로 읽어서 메모리에 바이트 배열로 저장한다.
            byte[] bytes = IOUtils.toByteArray(is);
            // 바이트 단위로 저장된 이미지 정보를 메모리에 올리기 위해서
            // 생성된 이미지 주소를 저장한다.
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
```
- 이미지를 저장할 Excel의 cell의 위치를 지정한다 `anchor`
```java
            // 이미지를 그리기 위해 anchor를 잡는다.
            CreationHelper helper = wb.getCreationHelper();
            // sheet에 이미지를 그리기 위해
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();

            // 이미지 시작 위치
            anchor.setCol1(1);
            anchor.setRow1(2);
            // 이미지 끝나는 위치
            anchor.setCol2(2);
            anchor.setRow2(3);

           Picture picture = drawing.createPicture(anchor,pictureIdx);
```
- 이미지를 잘 보이게 하기 위해 cell의 크기를 조정한다.
```java
            // excel의 cell의 크기를 조정해서 cell을 만든다.
            Cell cell = sheet.createRow(2).createCell(1);
            int w = 20 * 256; // 이미지 width 는 1이 1/256 크기 이기 때문에
            sheet.setColumnWidth(1,w);
            short h = 120 * 20; // 이미지 높이는 1이 1/20 크기이기 때문에
            cell.getRow().setHeight(h);
```
- 이미지를 실제 excel  파일에 write
```java 
            // 실제 Excel 파일로 저장한다.
            FileOutputStream fileOut = new FileOutputStream("myfile.xls");
            wb.write(fileOut); // wb안의 내용을 FileoutputStream에 쓴다.
            fileOut.close();
            System.out.println("이미지 생성 성공");
```

### 3. Excel에 cell DataType 알아보기 
> 데이터의 종류에 따라 cell에 있는 정보 읽을 때 사용하는 함수가 다르다.

```java
// cell의 type 정보를 먼저 가져온다.
CellType type = cell.getCellType();
```

1. CellType.STRING -> getRichStringCellValue().toString()
2. CellType.NUMERIC -> getNumericCellValue()
3. CellType.BOOLEAN -> getBooleanCellValue()
4. CellType.BLANK -> 비어있는 cell

---
## Naver Book OpenAPI 사용하기
> https://developers.naver.com/docs/serviceapi/search/book/
- 입력한 정보를 서버로 넘긴다.
``` java
        String openApi = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl"
                +URLEncoder.encode(vo.getTitle(),"UTF-8")
                +"&d_auth="+URLEncoder.encode(vo.getAuthor(),"UTF-8")
                +"&d_publ="+ URLEncoder.encode(vo.getCompany(),"UTF-8");
        URL url = new URL(openApi);

        // 서버와 연결
       HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id",dotenv.get("book_id"));
        con.setRequestProperty("X-Naver-Client-Secret",dotenv.get("book_key"));
        // 연결 성공했는지 확인
```
- 서버의 return 값인 XML를 받는다.
```java
    // 연결 성공했으면 정보 읽어서 와야하니까
    BufferedReader brl;
    if(responseCode == 200){
        brl = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
    }else{
        brl = new BufferedReader(new InputStreamReader(con.getErrorStream()));
    }
    String inputLine;
    // XML 정보 한줄 한줄 읽어서 저장하기 위한 buffer
    StringBuffer response = new StringBuffer();
    while((inputLine = brl.readLine())!= null){
        // response 에 xml 정보 전부 들어온다.
        response.append(inputLine);
    }
```
- JSOUP API 를 통해 결과를 parsing 해서 원하는 정보를 뽑아낸다.
```java
     // Jsoup api 이용해 xml data로 바꿔주기
    Document doc = Jsoup.parse(response.toString());
    //System.out.println(doc.toString());
    // 전체 total 정보 찾아서 검색이 잘 됐는지 판별 -> 가장 첫번째 item만 사용
    Element total = doc.select("total").first();
    System.out.println(total.text());
    if(!total.text().equals("0")){
        //isbn 정보 찾는다.
        Element isbn = doc.select("isbn").first();
        String isbnStr = isbn.text();
        System.out.println(isbnStr);
        String isbn_find = isbnStr.split(" ")[1];
```