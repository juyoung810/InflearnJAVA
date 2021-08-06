# Java MQTT Client 만들기
> MQTT: 메세지 중개 해주는 프로토콜


## MQTT
- MQTT 서버는 IP주소, Port 번호`1883`를 가지고, 여러개의 수신 sub 서버를 가진다. 각각의 `sub` 는 `topic`를 가진다.
- client가 MQTT 서의 ip주소, port 번호, sub의  topic 이름을 알고, 데이터를 보낸다. : `pub`
- 수신자 sub는 topic 에 맞게 온 pub의 데이터를 저장하고 , client가 해당 메세지를 읽어갈 수 있다.
- __pub로 데이터 발행, sub로 수신__
- 원래는 하드웨어가 pub 역할을 해서 데이터를 보내지만 해당 실습에서는 임시로 pub서버를 열어 메세지를 보낸다.

#### MQTT 서버 여는법
1. mosquitto MQTT broker를 설치한다.
2. cmd(관리자 권한으로 실행) 에서 서버를 구동한다. ->  1883 서버 열린다.
   ![mosquitto.jpg](https://github.com/juyoung810/InflearnJAVA/blob/487cf92d1c5bbb23df4cd0866a63ba0fca832fca/JavaTPCProject/img/mosquitto.JPG)
3. subscriber(구독자) 실행한다 -> 수신 대기 창
   
    `mosquitto_sub -t iot -p 1883` : topic이름,port 번호 지정
   
   ![mosquitto_sub.jpg](https://github.com/juyoung810/InflearnJAVA/blob/487cf92d1c5bbb23df4cd0866a63ba0fca832fca/JavaTPCProject/img/mosquitto_sub.JPG)
4. publisher(발행자) 실행 -> 메세지(토픽) 발행창

    `mosquitto_pub -t iot -p 1883 -m "메세지, 제이슨 데이터.."` : topic 번호, port 번호에 맞게 데이터 보낸다.
   
   ![mosquitto_pub.jpg](https://github.com/juyoung810/InflearnJAVA/blob/487cf92d1c5bbb23df4cd0866a63ba0fca832fca/JavaTPCProject/img/mosquitto_pub.JPG)

## 2. JAVA MQTT Client
> paho API 
1. MQTT 서버와 연동하기 -> paho API 필요
2. pub에서 json 데이터 보내기
3. sub에 들어온 데이터 읽어서 JAVA와 연동 후 처리
4. 연동 결과 sub에 쓰기
   
