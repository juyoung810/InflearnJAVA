package Project02_Crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project02_F extends JFrame implements ActionListener, ItemListener {
    private Choice chyear,chmonth;
    private JLabel yLabel,mLabel;
    private JTextArea area;
    GregorianCalendar gc;
    private int year,month;
    private JLabel[] dayLabel = new JLabel[7];
    private String[] day = {"일","월","화","수","목","금","토"};
    private JButton[] days = new JButton[42]; // 7일이 6주이므로 42개의 버튼 필요
    private JPanel selectPanel = new JPanel();
    private GridLayout grid = new GridLayout(7,7,5,5);
    private Calendar ca = Calendar.getInstance();
    private Dimension dimen, dimen1;
    private int xpos,ypos;
    public Project02_F()
    {
        setTitle("오늘의 톡: " + ca.get(Calendar.YEAR)+"/"+(ca.get(Calendar.MONTH)+1) + "/" + ca.get(Calendar.DATE));
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dimen = Toolkit.getDefaultToolkit().getScreenSize();
        dimen1 = this.getSize();
        xpos = (int)(dimen.getWidth()/2 - dimen1.getWidth()/2);
        ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
        setLocation(xpos,ypos);
        setResizable(false);
        setVisible(true);
        chyear = new Choice();
        chmonth = new Choice();
        yLabel = new JLabel("년");
        mLabel = new JLabel("월");
        init();
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        area.setText("");
        String year = chyear.getSelectedItem();
        String month = chmonth.getSelectedItem();
        JButton btn = (JButton) arg0.getSource();
        String day = btn.getText();
        System.out.println(year + "," + month+","+day);
        if(month.length() < 2) month = '0' + month;
        if(day.length() < 2) day = '0' +day;
        String talk = year + month + day;
        String url = "https://m.pann.nate.com/talk/enter/" + talk;
        try {
            Document doc = Jsoup.connect(url).post();
            Element pan_text = doc.select(".depth3_tab_wrap").first();
            // eq : 해당 태그 중 index를 선택한다.
            System.out.println(pan_text.select("li").eq(1).text());

            Element pan_date = doc.select(".date-sort2 strong").first();
            System.out.println(pan_date.text());

            area.append(pan_text.select("li").eq(1).text()+"\n");
            area.append(pan_date.text()+"\n");
            Elements liList = doc.select(".list.list_type2 > li");
            for(Element li : liList)
            {
                if(li != liList.select(".wrap_ad").first()) {
                    String line = (li.select(".tit").first().text());
                    if(line.length() > 65){
                        line = line.substring(0,36) + "\n" + line.substring(36,66) + "\n" + line.substring(66) +"\n";
                        area.append(line +  li.select(".sub").first().text()+"\n");
                    }else if(line.length() > 35){
                        line = line.substring(0,36) + "\n" + line.substring(36) + "\n";
                        area.append(line +  li.select(".sub").first().text()+"\n");
                    }else{
                        area.append(line +  li.select(".sub").first().text()+"\n");
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void init()
    {
        select();
        calendar();
    }

    public void select(){
        JPanel panel = new JPanel(grid);//7행 7열의 그리드레이아웃
        for(int i=2020; i>=2000;i--){
            chyear.add(String.valueOf(i));
        }
        for(int i=1; i <=12; i++){
            chmonth.add(String.valueOf(i));
        }
        for(int i = 0; i < day.length;i++){//요일 이름을 레이블에 출력
            dayLabel[i] = new JLabel(day[i],JLabel.CENTER);
            panel.add(dayLabel[i]);
            dayLabel[i].setBackground(Color.GRAY);//사실상 의미가 없슴. 바뀌지 않음.
        }
        dayLabel[6].setForeground(Color.BLUE);//토요일 색상
        dayLabel[0].setForeground(Color.RED);//일요일 색상
        for(int i = 0; i < 42;i++){//모두 42개의 버튼을 생성
            days[i] = new JButton("");//제목이 없는 버튼 생성
            if(i % 7 == 0)
                days[i].setForeground(Color.RED);//일요일 버튼의 색
            else if(i % 7 == 6)
                days[i].setForeground(Color.BLUE);//토요일 버튼의 색
            else
                days[i].setForeground(Color.BLACK);
            days[i].addActionListener(this);
            panel.add(days[i]);
        }
        selectPanel.add(chyear);
        selectPanel.add(yLabel);
        selectPanel.add(chmonth);
        selectPanel.add(mLabel);
        //JPanel bib=new JPanel();
        area=new JTextArea(60, 40);
        area.setCaretPosition(area.getDocument().getLength());
        JScrollPane scrollPane = new JScrollPane(area);
        this.add(selectPanel,"North");//연도와 월을 선택할 수 있는 화면읠 상단에 출력
        this.add(panel, "Center");
        this.add(scrollPane,"East");

        String m = (ca.get(Calendar.MONTH)+1)+"";
        String y = ca.get(Calendar.YEAR)+"";
        chyear.select(y);
        chmonth.select(m);
        chyear.addItemListener(this);
        chmonth.addItemListener(this);
    }
    public void calendar(){
        year = Integer.parseInt(chyear.getSelectedItem());
        month=Integer.parseInt(chmonth.getSelectedItem());
        gc = new GregorianCalendar(year, month-1, 1);
        int max = gc.getActualMaximum(gc.DAY_OF_MONTH);//해당 달의 최대 일 수 획득
        int week = gc.get(gc.DAY_OF_WEEK);//해당 달의 시작 요일
//		System.out.println("DAY_OF_WEEK:"+week);
        String today = Integer.toString(ca.get(Calendar.DATE));//오늘 날짜 획득
        String today_month = Integer.toString(ca.get(Calendar.MONTH)+1);//오늘의 달 획득
//		System.out.println("today:"+today);
        for(int i = 0; i < days.length; i++){
            days[i].setEnabled(true);
        }
        for(int i = 0; i < week-1; i++){//시작일 이전의 버튼을 비활성화
            days[i].setEnabled(false);
        }
        for(int i = week; i< max+week; i++){
            days[i-1].setText((String.valueOf(i-week+1)));
            days[i-1].setBackground(Color.WHITE);
            if(today_month.equals(String.valueOf(month))){//오늘이 속한 달과 같은 달인 경우
                if(today.equals(days[i-1].getText())){//버튼의 날짜와 오늘날짜가 일치하는 경우
                    days[i-1].setBackground(Color.CYAN);//버튼의 배경색 지정
                }
            }
        }
        for(int i = (max+week-1); i < days.length; i++){//날짜가 없는 버튼을 비활성화
            days[i].setEnabled(false);
        }
//		System.out.println("max+week:"+(max+week)+",week:"+week);
    }
    @Override
    public void itemStateChanged(ItemEvent arg0) {
        Color color = this.getBackground();
        if(arg0.getStateChange()==ItemEvent.SELECTED){
            for(int i = 0; i < 42; i++){//년이나 월이 선택되면 기존의 달력을 지우고 새로 그린다.
                if( !days[i].getText().equals("")){
                    days[i].setText("");//기존의 날짜를 지움
                    days[i].setBackground(color);//달력의 배경색과 동일한 색으로 버튼의 배경색을 설정함.
                }
            }
            calendar();
        }
    }
    public static void main(String[] args) {
        new Project02_F();
    }
}
