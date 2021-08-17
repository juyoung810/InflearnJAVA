package Project06_Socket;
import java.net.*;
import java.io.*;
public class Project06A_Server {
    public static void main(String[] args) {
        // 서버 소켓 임의의 포트 번호로 생성
        ServerSocket ss = null;
        try{
            ss = new ServerSocket(9999);
            System.out.println("Server ready....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // client 의 요청 계속 대기 후 응답 위해 while 문
        while(true){
            try {
                Socket socket =  ss.accept(); // client의 정보를 대기 (blocking 상태로)
                // 접속 성공되면 client의 정보 가지는 소켓 생성된다.
                System.out.println("client connect success!");
                //client의 메세지 읽고, client에 보내기 위해 I/O 생성(입력 빨대)
                InputStream in = socket.getInputStream(); //client의 메세지 읽는다. (바이트 스트림이므로 한글 깨짐)
                DataInputStream dis = new DataInputStream(in); //브릿지해서 한글 정보 읽을 수 있도록
                String message = dis.readUTF(); //메세지 넘어올 때 까지 기다린다.
                // client에 output 빨대를 하나 만든다.
                OutputStream out = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(out);
                dos.writeUTF("[ECHO]" + message + "(from Server!)");
                // 서버가 할 일 끝남
                dos.close();
                dis.close();
                socket.close();
                System.out.println("client socket colse...."); // 서버 할 일 끝났으므로 연결 끊고 다시 대기 상태로

            } catch (Exception e) {
                e.printStackTrace();
            }
        }//while

    }//main
}//class
