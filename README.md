# 댕네 한 바퀴
>반려동물들 간의 친목 도모 및 교류 목적 웹서비스입니다.<br>
 유저들이 반려 동물의 프로필을 올리고 채팅을 걸어 함께 산책, 모임을 갖는 것을 주 컨텐츠로 합니다.
 

</br>

## 1. 제작 기간 & 참여 인원
- 2022년 11월 21일 ~ 12월 5일
- 팀 프로젝트(3명)

</br>

## 2. 사용 기술
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


## 4. 주요 기능
  - 회원가입
  - 로그인
  - 프로필 등록/삭제/조회
  - 프로필 상세 검색
  - 상세페이지 댓글

    
</br>

## 5. 핵심 트러블 슈팅
### 5.1. 프로필 등록 문제
- 마이페이지에서 프로필 등록을 할 시에, 데이터 구조 상 '유저', '개', '프로필'의 데이터가 함께 저장되어야 합니다.

- 하지만 db의 대한 이해도 부족으로 '프로필'과 '유저'의 데이터만 저장되도록 처리했다가,
 
- 화면에 구현한 후에 db를 잘못 이해한 것을 깨닫고 '유저', '개', '프로필' 데이터를 모두 함께 저장하는 것으로 코드를 바꿨습니다.

- 프로필 등록 폼에서 '프로필'과 '개'의 데이터를 함께 저장시키기 위해, '프로필'의 '하트'속성을 통해 '프로필' 데이터를 생성시키도록 하였습니다.

<details>
<summary><b>개선된 코드</b></summary>																					
	
	
	
		@PostMapping("/add_ok")
		public String addOk(@Validated Profile profile, BindingResult result, Authentication loginUser, Model model) { 																									       if (result.hasErrors()) {
			return addProfile(profile, loginUser, model);
		}
		DogUser user = dogUserRepository.findByUsername(loginUser.getName()).get(); // get은 옵셔널이라 무조건 붙임
		profile.setDoguser(user);
		profileRepository.save(profile);
		petRepository.save(profile.getPet());
		return "redirect:/";
	}
</div>
</details>



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
