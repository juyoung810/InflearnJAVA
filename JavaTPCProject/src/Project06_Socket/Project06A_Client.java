package Project06_Socket;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Project06A_Client {
    public static void main(String[] args) {
        try {
            // 서버 정보 가지는 socket 생성
            Socket socket =  new Socket("127.0.0.1",9999); // --------------->accept()로 넘어간다.
            System.out.println("Connection Success");
            //메세지 보내기 위해 입력
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            // 메세지 서버에 보내기 위한 빨대 생성
            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeUTF(message);

            // 서버의 메세지 받기 위한 빨대
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            System.out.println("Receive:"+dis.readUTF());

            dis.close();
            dos.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
