 
 ## RecyclerView를 사용한 List 표현
 
 * RecyclerView만으로는 List를 표현하기가 다소 불편
 * RecyclerView의 요소를 표현할 Adapter를 생성하고
 * adapter 내부에 Holder를 생성하여 Inlfater를 수행하여 모양을 만든다
 
 1) Holder에 사용할 item view 페이지를 생성
 
 ## DB 반영하기
 
   * Android OS에는 SQL Lite라는 소형 DBMS가 내장되어있다
   * SQL Lite 범용으로 사용하는 최소한의 기능을 가진 DBMS이다
   * 일반적인 SQL을 사용해서 DB 핸들링이 가능
   * SQL Lite를 직접 핸들링 하지 않고 
     room이라는 ORM을 사용해서 추상화하고 DB핸들링을 수행
     
 ## ROOM DB 사용하기
   1. table로 사용할 vo를 entity로 선언
   2. dao로 사용할 interface를 정의
   3. MemoDataBase DB연결과 dao Imp를 생성할 class 정의
   4. Repository(service) DB에 접근하는 연결 클래스 정의