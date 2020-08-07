# WhistleOn



## 1. 프로젝트 소개

---

### ⚽️ WHISTLE ON



### 👨‍👨‍👦 Developers

> 팀명 : HALA Guys
>
> 팀원 :
>
> ​	강 상 우 (Backend Developer, Team Leader) 🐼
>
> ​	권	 민 (Backend Developer) 🐨
>
> ​	유 대 상 (Frontend Developer) 🐸




### ⚽ 서비스 소개

` 이젠 경기전에 휘슬온 하세요!`

* 

* 

* 




### ⚽️ 기술 스택

* 사용 언어 : Java, JavaScript, TypeScript, HTML, CSS
* Framework : Spring Boot, Vue.js
* DataBase : MySQL
* Server : AWS
* IDE : Visual Studio Code, IntelliJ IDEA, WebStorm
* Test : JUnit, Jest




## 2. Install

___






## 3. 서비스 설명

___






## 4. Contributing

___



### ⚽️ 개발 규칙



### 1. Commit Message

* 영어로 작성

* 커밋 명령어(ex. ADD, UPDATE)는 전부 대문자

* 고유명사나 파일 이름은 해당 이름 그대로 작성

* JIRA 이슈 번호 등록 시, `커밋 명령어 커밋 메세지, (띄어쓰기) + 코드번호`

  ```shell
  # 예시
  
  $ CREATE LoginView.vue, https://hallaguys.atlassian.net/browse/WHIS-3
  ```




### 2. FrontEnd

* VS code 사용 시 __Indenting Space 2__ 로 설정

* Vue SFC 네이밍 __PascalCase __로 작성

  ` ex) LoginView.vue`

* Function / Variable , Vue Store의 state, getters 네이밍 __camelCase__ 로 작성

  ```javascript
  // 예시
  const userName = 'WhistleOn';
  
  function getUserName(){
    return 'WhistleOn';
  }
  ```

* Vue Store의 mutations, actions 함수 네이밍 __UPPER_CASE_SNAKE_CASE__ 로 작성

  ```javascript
  // 예시
  const mutations = {
    SET_USER(state, payload) {
      // ...
    }
  }
  
  const actions = {
    FETCH_DATA({ commit }, payload){
      // ...
    }
  }
  ```

* 컴포넌트 부착 시 , __kebab-case__ 로 작성

* HTML 요소의 클래스 네이밍 __BEM 방법론__ 으로 작성

  ```html
  <!-- 예시 -->
  
  <template>
  	<div class="root">
      <my-component></my-component>
      <p class="root__description">
      	example
      </p>
    </div>
  </template>
  ```

* JSON 키 네이밍 __camelCase __ 로 작성

  ```json
  // 예시
  
  {
    "userName": "WhistleOn",
    "userAge": 19
  }
  ```




### 3. Backend

* Function / Variable 네이밍 __camelCase__ 로 작성

  ```java
  // 예시
  
  String userName = "WhistleOn";
  
  public void getUserName() {
    return;
  } 
  ```

* Package 네이밍 소문자 작성

  `ex) com.hala.whistleon.controller`

* Class 네이밍 __PascalCase__ 로 작성

  ```java
  // 예시
  
  class WhistleOn{
    // ...
  }
  ```

* 배열, 리스트, 기타 컬렉션 프레임워크 사용 시 복수형 사용

  ```java
  // 예시
  
  List<Integer> users = new ArrayList<>();
  ```

* for each 문 사용 시, 단수 변수명 사용

  ```java
  // 예시
  
  for(int user : users) {
    // ...
  }
  ```

* 인스턴스 네이밍은  클래스 이름 그대로 __camelCase__ 로 작성

  ```java
  // 예시
  
  WhistleOn whistleOn = new WhistleOn();
  ```

* Enum(열거형)과 상수 네이밍 __UPPER_CASE_SNAKE_CASE__ 로 작성

  ```java
  // 예시
  
  public static final String USER_NAME = "WhistleON";
  ```




## 5. License

___






## 6. 기타

___



### ⚽️ Branch

```shell
master
 └── develop
     ├── frontend
     │		├── frontend_feature/function_1
     │		└── frontend_feature/function_2
     │
     └── backend
     			├── backend_feature/function_1
     			└── backend_feature/function_2
```

