# Project 6 : Java Socket(소켓) Multi-Chatting 프로그래밍
> client 소켓과 server 소켓을 연결해, 데이터를 주고 받는다.

## Socket(소켓)이 만들어 지는 과정(TCP 3-way HandShake)
#### [server]
##### 1. `ServerSocket` 열기
- client의 접속을 대기하는 역할
- 안내원 역할을 한다. 목적에 맞는 다른 port로 연결할 수 있도록 client를 연결해준다.
- client 접속 이전에 서버를 `blocking` 상태로 대기할 수 있도록 해준다.
##### 2. `Socket` 열기 - 상대방 client 의 정보를 알고 있어야 한다.
- 통신하기 위한 포트, 서버가 열어준 내부 Port이다.
- __client의 소켓 정보를 가지고 있다.__

#### [client]
##### 1. `Socekt` 열기 - 서버의 정보를 알고 있어야 한다.
- 서버의 정보를 가지고, 접속을 시도한다.
- 이때, 서버로 접속하기 위해 열리는 내부 port 이다.

#### [3-way HandShake] 과정
> 3번의 악수(절차) 를 통해 Data 주고 받는 관계 된다.
> 
> 소켓과 소켓 만들어지면, 상대방의 정보를 알 수 있다. 이 정보를 알고 ,I/O를 통해 Data를 주고 받을 수 있다.
1. server가 소켓 서버를 열어 , 접속을 대기한다.
2. client가 내부 서버를 열어, server의 정보를 가지고 접속 시도를 한다.
3. server가 client를 정보를 가진 통신 포트를 열어, server의 정보를 client에게 넘긴다.
4. 연결 완료
5. client에서 I/O 를 생성해 server로 메세지를 보낸다.
6. server에서 I/O를 생성해 client로 부터 메세지를 받고, 응답한다.

## Java 에서 Socket 만들기 
> ServerSocket 객체 생성
> 
> Socket 객체 생성
> 
> import java.net.*

1. 서버에서 `ServerSocket` 객체를 생성한다.
- 소켓을 하나의 메모리로 생각
- 임의의 포트를 만들어 연다.
```java
ServerSocket ss = new ServerSocket(9000);
```
2. 서버소켓을 `accept()` 메서드를 통해 접속 대기 상태로 만든다.
__[accept() 메서드 역할]__
   1. 접속 대기 -> `blocking`
   2. 클라이언트 식별
   3. 수락
   4. 바인딩(소켓 생성) : 클라이언트의 정보를 가진다.
   5. 생성된 소켓정보 전송 
    
```java
Socket sock = ss.accept() 
```
접속 대기 상태로 기다리다가, client의 접속 요청이 들어오면 승낙해서 만든 새 소켓이 생성된다.
해당 정보를 `Socket` 으로 받아 객체 생성
3. 클라이언트가 서버의 정보를 가지는 포트를 만들어 접속 요청
```java
Socket sock = new Socket("ip주소",9000);
```
서버의 정보를 가지는 새로운 소켓 객체를 만들어 요청을 보내고, 요청 결과 받은 서버의 정보를 `Socket`으로 받아 
객체를 생성한다.
4. 각각 I/O를 생성해 메세지를 주고 받는다.
- 일종의 정보를 받기 위한 빨대를 서로에게 꼽는다고 생각

5. 채팅하는 것을 실행하기 위해 cmd 창에서 실행하는 것 추천
- [intellij 로 작성한 파일 cmd에서 실행시키기] (https://no-cheese.tistory.com/10) 

### Java Socket 활용한 ECHO 프로그램 만들기
- client가 보낸 메세지 server가 그대로 반환하는 프로그램
- 소스코드 참고 `/JavaTPCProject/src/Project06_Socket/ProjectA...`
---

## Socket을 이용한 Multi-Chatting 만들기
#### [server]
1. 여러 client의 정보 저장 위해 HashMap 사용
- key : client 이름, value: client에 연결된 outputStream
2. client 접속하면 대기하다가 소켓 만들어지면 thread를 실행해서 해당 소켓에 대한 in,out stream을 만들 수 있도록 한다.
3. thread가 실행되면 client의 이름이 hashmap에 존재하지 않을 시, hashmap에 이름 ,outstream을 저장하고
data 를 계속 읽어서, 연결된 모든 client에게 broadcasting 한다.
   
#### [client]
1. 서버의 정보를 가지고, 이름을 보내 연결한다.
2. 보내는 thread 에서는, quit을 입력하기 전까지 계속해서 서버에 데이터 보낸다.
3. 받는 thread 에서는 , data를 서버로 부터 계속 받는다.