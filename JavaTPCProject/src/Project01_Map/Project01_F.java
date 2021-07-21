package Project01_Map;

import javax.swing.*;
import java.awt.*;

public class Project01_F {
    JTextField address;
    JLabel resAddress, resX, resY, jibunAddress, imageLabel;
    public void initGUI()
    {
        JFrame frm = new JFrame("Map view"); // 프레임생성
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X 버튼 누르면 프레임 종료
        Container c = frm.getContentPane(); // 프레임 안 컴포넌트 부착할 수 있는 부분 : container
        imageLabel = new JLabel("지도보기");

        // 상담 주소 [주소 입력] 버튼
        JPanel pan = new JPanel();
        JLabel addressLb1 = new JLabel("주소입력");
        address = new JTextField(50);
        JButton btn = new JButton("클릭");
        pan.add(addressLb1);
        pan.add(address);
        pan.add(btn);

        btn.addActionListener(new NaverMap(this));

        // 4:1 구조 layout 만든다.
        JPanel pan1 = new JPanel();
        pan1.setLayout(new GridLayout(4,1));
        resAddress = new JLabel("도로명");
        jibunAddress = new JLabel("지번주소");
        resX = new JLabel("경도") ;
        resY = new JLabel("위도");

        pan1.add(resAddress);
        pan1.add(jibunAddress);
        pan1.add(resX);
        pan1.add(resY);

        // 다 갖다 붙인다.
        c.add(BorderLayout.NORTH,pan);
        c.add(BorderLayout.CENTER,imageLabel);
        c.add(BorderLayout.SOUTH,pan1);
        frm.setSize(730,660);
        frm.setVisible(true);
    }
    public static void main(String[] args){
        new Project01_F().initGUI();
    }
}
