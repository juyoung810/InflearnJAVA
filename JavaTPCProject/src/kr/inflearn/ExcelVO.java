package kr.inflearn;

public class ExcelVO {
    private String title;
    private String author;
    private String company;
    private String isbn;
    private String imageUrl;

    public ExcelVO() {
    }

    public ExcelVO(String title, String author, String company, String isbn, String imageUrl) {
        this.title = title;
        this.author = author;
        this.company = company;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
    }

    public ExcelVO(String title, String author, String company) {
        this.title = title;
        this.author = author;
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ExcelVO{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", company='" + company + '\'' +
                ", isbn='" + isbn + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
