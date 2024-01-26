## 🗒 커머스 프로젝트

판매자와 구매자를 중개해 주는 커머스 서버입니다.

### 사용 기술

Spring, JPA, Mysql, Redis, Docker, AWS

## 프로젝트 기능 및 설계

- 회원가입
    - 회원 가입에는 이메일 인증이 필요하다.
    - 회원가입시 아이디와 패스워드를 입력받으며, 아이디는 unique 해야한다.
    - 회원의 종류는 구매자와 판매자가 있다.
- 로그인 기능
    - 사용자는 로그인을 할 수 있다. 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야한다.
    - 로그인 토큰을 통해 제어한다.
- 구매자
    - 구매자는 예치금을 보유한다.
    - Redis를 이용한 장바구니를 갖는다.
        - 장바구니에 물건을 추가하거나 삭제 할 수 있다.
        - 장바구니 내용을 조회 할 수있다.
    - 장바구니로 주문 할 수 있다.
- 판매자
    - 상품을 등록, 수정 할 수 있다.
    - 상품을 삭제 할 수 있다.
 
  
## ERD 
![image](https://github.com/dev-Seonghwan/cms/assets/91909986/d28d6f45-8282-465c-89f7-62be62c004c2)
