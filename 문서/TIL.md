# Today I Learned



## :honey_pot: Tip

- `Ctrl + Shift + O` : 자동으로 import
- `Ctrl + Space bar` : 자동완성



## 2020-07-24

### 흐름

1. url로 요청 들어오면

2. controller에서 service 호출
3. service에서 repository 호출
4. repository에서 db 조작할 일이 있을 때 mapper 호출
5. mapper에서 쿼리로 db 조작
6. 다시 repository - service - controller
7. 프론트로 넘겨줌 !



### Dto

1. 어떤 값을 넣어야 할지

2. 어떤 타입인지

3. getter, setter 생성

   - 오른쪽 눌러서 source - generate getter, setter

4. 생성자 만들기

   1. **기본 생성자** : 오른쪽 눌러서 source - generate constructor from superclass

   2. 인자 값을 갖는 생성자 : 오른쪽 눌러서 source - generate constructor using fields

5. toString 함수 만들기

   - 어떤 형태로 출력할지 정의

   - generate toString



## 2020-07-26

### 스프링 코드 짜는 흐름

1. mapper에서 쿼리 작성
2. repository
   1. **Dao**에서 인터페이스 작성
   2. Impl 파일에서 함수 작성 - mapper 쿼리 리턴
3. service
   1. 인터페이스 작성
   2. Impl 파일에서 함수 작성 - Dao의 함수 리턴
4. contoller에서 함수 작성
   1. @ApiOperation(value="`무슨 함수인지?`")
   2. @RequestMapping(value="`url`", method=RequestMethod.`요청방식`)
   3. ResponseEntity 타입으로 return



#### url에 인자가 들어갈 때

- controller에서 호출하는 함수의 인자로 넣어주기
  - `@PathVariable` String email
- 나머지 함수들에선 인자로 넣을 때 @Path~ 쓰지 않음!



### Spring Layer

#### 1. Controller layer

1. 클라이언트가 이용할 End point
2. 요청을 어떻게 처리할지, 어떻게 응답할지 결정

#### 2. Service layer

1. Controller와 Dao의 중간 영역에서 사용됨
2. 비즈니스 로직
   - 핵심 업무 로직 구현, 그에 관련된 데이터의 적합성 검증, 트랜잭션 처리, 다른 계층과의 통신을 위한 인터페이스 제공, 해당 계층의 객체 간 관계 관리 등

#### 3. Repository layer

1. Dao(Data Access Object)
2. Strorage에 접근해 데이터를 조작