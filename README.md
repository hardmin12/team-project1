# 댕네 한 바퀴
>반려동물들 간의 친목 도모 및 교류 목적 웹서비스입니다.<br>
 유저들이 반려 동물의 프로필을 올리고 채팅을 걸어  산책,모임을 갖는 것을 주 컨텐츠로 합니다.
 

</br>

## 1. 제작 기간 & 참여 인원
- 2022년 11월 21일 ~ 12월 5일
- 팀 프로젝트(3명)

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 8
  - Spring Boot 2.7.5
  - Maven
  - Spring Data JPA
  - Oracle
  - Spring Security
  - Tymeleaf

</br>

## 3. ERD 설계
![Copy of Copy of DogProject](https://user-images.githubusercontent.com/95213209/208612227-916e6194-8faa-44f8-a2e2-f73b59adf961.png)


## 4. 핵심 기능
이 서비스의 핵심 기능은 프로필 등록 기능과 프로필 상세 검색 기능입니다.<br>  

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 프로필 등록

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_controller.png)

- **요청 처리** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b2c5e60761b6308f14eebe98ccdb1949de6c4b99/src/main/java/goQuality/integerous/controller/PostRestController.java#L55)
  - Controller에서는 요청을 화면단에서 넘어온 요청을 받고, Service 계층에 로직 처리를 위임합니다.

- **결과 응답** :pushpin: [코드 확인]()
  - Service 계층에서 넘어온 로직 처리 결과(메세지)를 화면단에 응답해줍니다.

### 4.2. 프로필 상세 검색

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service1.png)

- **Http 프로토콜 추가 및 trim()** :pushpin: [코드 확인]()
  - 사용자가 URL 입력 시 Http 프로토콜을 생략하거나 공백을 넣은 경우,  
  올바른 URL이 될 수 있도록 Http 프로토콜을 추가해주고, 공백을 제거해줍니다.

- **URL 접속 확인** :pushpin: [코드 확인]()
  - 화면단에서 모양새만 확인한 URL이 실제 리소스로 연결되는지 HttpUrlConnection으로 테스트합니다.
  - 이 때, 빠른 응답을 위해 Request Method를 GET이 아닌 HEAD를 사용했습니다.
  - (HEAD 메소드는 GET 메소드의 응답 결과의 Body는 가져오지 않고, Header만 확인하기 때문에 GET 메소드에 비해 응답속도가 빠릅니다.)

  ![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service2.png)

- **Jsoup 이미지, 제목 파싱** :pushpin: [코드 확인]()
  - URL 접속 확인결과 유효하면 Jsoup을 사용해서 입력된 URL의 이미지와 제목을 파싱합니다.
  - 이미지는 Open Graphic Tag를 우선적으로 파싱하고, 없을 경우 첫 번째 이미지와 제목을 파싱합니다.
  - 컨텐츠에 이미지가 없을 경우, 미리 설정해둔 기본 이미지를 사용하고, 제목이 없을 경우 생략합니다.



</div>
</details>

</br>

## 5. 핵심 트러블 슈팅
### 5.1. 프로필 등록 문제
- 마이페이지에서 프로필 등록을 할 시에,'개'와 '프로필'의 데이터가 함께 올라가야 했습니다

- 하지만 데이터에 대한 이해도 부족으로 '프로필' 데이터

- 하지만 [무한스크롤, 페이징 혹은 “더보기” 버튼? 어떤 걸 써야할까](https://cyberx.tistory.com/82) 라는 글을 읽고 무한 스크롤의 단점들을 알게 되었고,  

- 그런데 게시물이 필터링 된 상태에서 무한 스크롤이 동작하면,  
필터링 된 게시물들만 DB에 요청해야 하기 때문에 아래의 **기존 코드** 처럼 각 필터별로 다른 Query를 날려야 했습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 Top10 (기준: 댓글 수 + 좋아요 수)
 * @return 인기순 상위 10개 게시물
 */
public Page<PostResponseDto> listTopTen() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "rankPoint", "likeCnt");
    return postRepository.findAll(pageRequest).map(PostResponseDto::new);
}

/**
 * 게시물 필터 (Tag Name)
 * @param tagName 게시물 박스에서 클릭한 태그 이름
 * @param pageable 페이징 처리를 위한 객체
 * @return 해당 태그가 포함된 게시물 목록
 */
public Page<PostResponseDto> listFilteredByTagName(String tagName, Pageable pageable) {

    return postRepository.findAllByTagName(tagName, pageable).map(PostResponseDto::new);
}

// ... 게시물 필터 (Member) 생략 

/**
 * 게시물 필터 (Date)
 * @param createdDate 게시물 박스에서 클릭한 날짜
 * @return 해당 날짜에 등록된 게시물 목록
 */
public List<PostResponseDto> listFilteredByDate(String createdDate) {

    // 등록일 00시부터 24시까지
    LocalDateTime start = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MIN);
    LocalDateTime end = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MAX);

    return postRepository
                    .findAllByCreatedAtBetween(start, end)
                    .stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
    }
~~~

</div>
</details>

- 이 때 카테고리(tag)로 게시물을 필터링 하는 경우,  
각 게시물은 최대 3개까지의 카테고리(tag)를 가질 수 있어 해당 카테고리를 포함하는 모든 게시물을 질의해야 했기 때문에  
- 아래 **개선된 코드**와 같이 QueryDSL을 사용하여 다소 복잡한 Query를 작성하면서도 페이징 처리를 할 수 있었습니다.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 필터 (Tag Name)
 */
@Override
public Page<Post> findAllByTagName(String tagName, Pageable pageable) {

    QueryResults<Post> results = queryFactory
            .selectFrom(post)
            .innerJoin(postTag)
                .on(post.idx.eq(postTag.post.idx))
            .innerJoin(tag)
                .on(tag.idx.eq(postTag.tag.idx))
            .where(tag.name.eq(tagName))
            .orderBy(post.idx.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
            .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
}
~~~

</div>
</details>

    
</br>

## 6. 회고 / 느낀점
- 프로젝트를 진행하며 가장 크게 느낀 점은 소통의 중요성입니다. <br>
 소통 오류로 팀원과 같은 기능을 구현하여 시간을 크게 낭비했던 일이 있습니다.<br>
 각자 맡은 파트를 해내는 것도 중요하지만 선행되어야 할 것은 충분한 소통이라는 것을 깨닫게 되었습니다.
 
- 두 번째로, 공유저장소를 아예 사용해 본 적이 없는 팀원들이 있어 공유저장소를 사용하지 않았는데, 큰 불편을 야기했습니다. <br>
 시간이 조금 더 소요되는 일이 있더라도 공유저장소를 사용해서 프로젝트를 진행했어야 했다는 생각을 했습니다.
 
- 마지막으로, 프로젝트 주제의 참신함보다는 프로젝트의 완성도에 신경 썼어야 했습니다. <br>
 좋은 주제라고 생각했지만 프로젝트에 참여하면서 저를 포함한 팀원들의 역량 부족을 느꼈습니다.<br>
 '채팅' 기능과 '상세 페이지' 기능은 개별적으로만 구현되고 프로젝트에 합쳐지지 못했습니다.<br> 
 결론적으로 프로젝트는 미완성으로 남게되었기 때문에 후회하는 지점입니다.
 <br> 
