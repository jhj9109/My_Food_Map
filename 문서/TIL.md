# :pencil2: ​Today I Learned



## 2020-07-24 :sun_behind_rain_cloud:

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



## 2020-07-26 :sunny:

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



### 좋아요 기능 구현

> 참고
> [https://kwakkwakkwak.github.io/spring/2017/12/18/Sprng-%EC%A2%8B%EC%95%84%EC%9A%94%EA%B8%B0%EB%8A%A5/](https://kwakkwakkwak.github.io/spring/2017/12/18/Sprng-좋아요기능/)



#### 1. DB 모델링

- table : review_like - 좋아요 기능을 위한 테이블
  - 좋아요를 누른 사용자와 해당 게시글 각각의 id를 저장
  - reviewLikeId - PK
  - userId(email) - FK
  - reviewId - FK

#### 2. Mapper

```java
// 좋아요 데이터 추가
<insert id="createReviewLike">
     INSERT INTO review_like (review_id, email)
     VALUES (#{reviewId}, #{email})
</insert>

// 요청 보낸 사용자의 해당 리뷰 좋아요 데이터 삭제
<delete id="deleteReviewLike">
     DELETE FROM review_like
     WHERE review_id = #{reviewId} AND email = #{email}
</delete>

// 해당 리뷰의 좋아요 개수 갱신
<select id="updateReviewLike">
    UPDATE review
    SET
    review_like_cnt = (SELECT COUNT(*)
                     FROM review_like
                     WHERE review_id = #{reviewId})
    WHERE review_id = #{reviewId}
</select>

// 해당 리뷰의 좋아요 개수 반환
<select id="getReviewLike" resultType="int">
    SELECT COUNT(review_like_id) FROM review_like WHERE review_id = #{reviewId} AND email = #{email}
</select>
```

#### 3. Dao

- session을 이용해 데이터를 조작하는 함수를 정의

#### 4. Service

- 함수를 정의하고, 그 함수에서 실행할 dao 함수 실행
- 좋아요, 좋아요 취소 함수는 그에 해당하는 함수도 실행하고, review의 like 개수를 갱신하는 함수도 실행해야 함

#### 5. Controller

- 함수를 실행할 url과 요청 받을 method를 mapping
- 좋아요 돼 있으면 delete, 안 돼 있으면 insert
- 좋아요 여부를 나타내는 변수(heart)의 값도 변경



## 2020-07-27 :sun_behind_rain_cloud:

### swagger-ui

localhost:`server port`/swagger-ui.html



### Follow 기능 구현

> 참고
> https://yeahajeong.tistory.com/102



#### Controller

```java
@Autowired
private IFollowService followService;

//personal-list 요청 : 개인 게시물 보이는 페이지
@RequestMapping(value="/{id}", method=RequestMethod.GET)
public String personalList(@PathVariable String id, Model model, HttpSession session) throws Exception {
	
	//개인 페이지 주인 정보 담기
	UsersVO user = usersService.inquiryOfUserById(id);
	//로그인한 회원 (=나) 정보 담기
	Object object = session.getAttribute("login");
	UsersVO loginUser = (UsersVO)object;
	
	//개인페이지의 유저 번호 가져오기
	int userNo = user.getUserNo();
	//로그인 회원 유저 번호 가져오기
	int loginUserNo = loginUser.getUserNo();
	logger.info("현재 페이지 주인의 번호 : " + userNo + ", 로그인 유저의 번호 : " + loginUserNo);
	
	//팔로우 객체 생성
	FollowVO follow = new FollowVO();
	follow.setActiveUser(loginUserNo); 	//하는놈
	follow.setPassiveUser(userNo);		//당하는놈
	
	//팔로우 유무 체크
	int followCheck = followService.isFollow(follow);
	logger.info("팔로우 유무 체크 : " + followCheck);

	//팔로워 리스트 -> (개인페이지에서)나를 팔로우하는 놈들 목록
	List<FollowVO> followerList = followService.selectPassiveUserList(userNo);
	//팔로잉리스트 -> (개인페이지에서) 내가 팔로우 하는 놈들 목록
	List<FollowVO> followingList = followService.selectActiveUserList(userNo);
	
	//사용자 아이디로 사용자 번호(pk)를 조회해서 그 번호로 게시물 가져오기
	model.addAttribute("post", postsService.selectPostListById(id));
	//사용자 아이디로 회원의 모든 정보 조회하기
	model.addAttribute("user", user);
	//팔로우 체크 유무
	model.addAttribute("followCheck", followCheck);
	//팔로워 리스트
	model.addAttribute("followerList", followerList);
	//팔로잉 리스트
	model.addAttribute("followingList", followingList);
	
	return "post/personal-list";
}
//게시글 작성 페이지 personal-write.jsp 요청
@RequestMapping(value="/{id}/personal-write", method=RequestMethod.GET)
public String personalWrite(@PathVariable String id, Model model, HttpSession session) throws Exception {

	//id로 회원의 모든 정보 조회
	UsersVO user = usersService.inquiryOfUserById(id);
	
	//로그인한 회원 (=나) 정보 담기
	Object object = session.getAttribute("login");
	UsersVO loginUser = (UsersVO)object;
	
	//개인페이지의 유저 번호 가져오기
	int userNo = user.getUserNo();
	//로그인 회원 유저 번호 가져오기
	int loginUserNo = loginUser.getUserNo();
	logger.info("현재 페이지 주인의 번호 : " + userNo + ", 로그인 유저의 번호 : " + loginUserNo);
	
	//팔로우 객체 생성
	FollowVO follow = new FollowVO();
	follow.setActiveUser(loginUserNo); 	//하는놈
	follow.setPassiveUser(userNo);		//당하는놈
	
	//팔로우 유무 체크
	int followCheck = followService.isFollow(follow);
	logger.info("팔로우 유무 체크 : " + followCheck);
	
	//팔로워 리스트 -> (개인페이지에서)나를 팔로우하는 놈들 목록
	List<FollowVO> followerList = followService.selectPassiveUserList(userNo);
	//팔로잉리스트 -> (개인페이지에서) 내가 팔로우 하는 놈들 목록
	List<FollowVO> followingList = followService.selectActiveUserList(userNo);
	
	//사용자 아이디로 사용자 번호(pk)를 조회해서 그 번호로 게시물 가져오기
	model.addAttribute("post", postsService.selectPostListById(id));
	//회원의 정보
	model.addAttribute("user", user);
	//팔로우 체크 유무
	model.addAttribute("followCheck", followCheck);
	//팔로워 리스트
	model.addAttribute("followerList", followerList);
	//팔로잉 리스트
	model.addAttribute("followingList", followingList);	
	
	return "post/personal-write";
}
```



## 2020-07-29 :cloud_with_rain:

### Follow

- Review List 페이지에서 리뷰 작성자를 조회할 수 있어야 함
  - review 테이블에는 작성자 nickname이 없어서 user 테이블도 참조해야 함
    - back에서 같이 넘겨줘야 하는 건가?
  - 상세 페이지 url에 들어갈 userid를 가져오기 위해 작성자 데이터 필요
  - profile page url로 넘어갈 때 프론트에서 작성자와 관련된 특정 데이터(nickname?)를 백으로 넘겨주고, 백에서 그걸 이용해서 작성자와 관련된 데이터를 가져와 다시 프론트로 넘겨주면서 profile page 나오게? 하는 건가?? 후...
  - 팔로우 상태에 따라서 버튼 다르게 보여줘야 하니까 팔로우 상태를 갱신하기 위한 그런 함수.. 필요하겠지...

#### Modeling

- `follow` 테이블
- no(pk), followerId(fk: user-userId), followingId(fk: user-userId)

#### 함수

- follow 테이블에 데이터를 insert, delete
- follower, following 몇 명인지 세는 함수?
- follower list, following list 조회하는 함수?
  - 이게 있으면 몇 명인지 세는 함수가 따로 필요 없을까?



### Like

- 아이콘 색깔을 바로바로 바꿔줄 수 있도록, 현재 로그인 된 사용자가 해당 리뷰에 좋아요를 했는지 판단하는 함수? 가 필요할까?



### Error

- Optional int parameter 'reviewid' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type."
  - ~~int 타입은 null 값이 들어갈 수 없어서 나는 거 같은데 해결 방법을 못 찾겠다 :cry:~~
  - userId가 String 타입으로 들어가 있어서, int 대신 String으로 받도록 해주었고, `like` table이 SQL의 like와 중복되어 에러나, table 이름을 `heart`로 바꾸니 해결됨!



### Git 폴더와 sts 연동

1. sts의 Git repositories에 깃 폴더 추가
   - Git 폴더가 이미 존재하면 Add ~
   - 아닐 땐 Clone ~
2. 해당 repository를 Import



## 2020-07-30 :cloud:

### Error

- SQL 문법에서 사용하는 단어는 Table 이름으로 사용하면 안 됨



### Like

- 하트 색을 바로바로 바꾸기 위해서 어떻게 해야 할까?
  - 프론트에서 리뷰에 관한 정보들만 리턴해줬음
  - Review Dto에 해당 사용자의 좋아요 여부를 판단하는 변수 isLike 추가
  - 흐름
    1. 리뷰 리스트를 가져옴
    2. 모든 리뷰들을 포문을 돌려서 userid reviewid를 heart 테이블을 통해 비교
    3. 비교한 결과값을 변수에 저장
-  해당 리뷰의 좋아요 개수를 보여주자!
  1. `review` table에 like_cnt 필드 추가
  2. 좋아요 기능에서 like_cnt +-1
     - Update review set like_cnt=like_cnt +-1 where reviewId=#{reviewId}
- 해결해야 할 부분
  - 좋아요를 눌러도 like_cnt가 바로 바뀌지 않고 새로고침해야 함
  - 좋아요 여부에 따라 하트 색을 바꾸는 걸 아직 구현하지 않음



## 2020-08-04 :cloud:

-  Follow 구현할 때 자꾸 Null 값을 참조한다는 에러 발생

  - 코드 곳곳에 print문을 넣어 확인하고, dao의 함수를 실행할 때 문제가 발생한다는 것을 알아냄

  - 하지만 문제는 다른 곳에 있었음...

    ```java
    @Autowired
    FollowDao followDao;
    ```

    - Dao를 선언할 때 `@Autowired`를 안 붙여놓고 있었음
    - `@Autowired` 붙여주니까 바로 해결!

  - 코치님 정말 감사합니다... :pray:



# :honey_pot: Tip

- `Ctrl + Shift + O` : 자동으로 import
- `Ctrl + Space bar` : 자동완성
- `Ctrl + y` : 실행 앞으로 (**Crtl + z**와 반대)