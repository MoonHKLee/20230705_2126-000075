## 1. 지원자 정보 

2126-000075_이문혁_서버 개발자(HIGHLIGHT 채용)

## 2. 프로젝트 설명 

블로그 글을 검색하고, 검색 순위 및 검색 수를 확인할 수 있습니다.

### 환경
- Java 11
- Spring Boot 2.7.13
- Gradle
- H2 Database
- JPA

### 사용 라이브러리
- Lombok
  - 코드의 가독성, 코드 중복 제거를 위해 사용했습니다.
- AssertJ
  - 테스트코드의 가독성을 위해 사용했습니다.

### API 명세
### 블로그 검색

| 메서드  | URL                         |
|------|-----------------------------|
| GET  | http://localhost:8080/blogs |
#### 쿼리 파라미터

| 이름    | 타입    | 설명                          | 필수  | 기본값      |
|-------|---------|-----------------------------|-----|----------|
| query | String  | 검색어                         | O   | -        |
| size  | Integer | 검색 결과 수 (1 - 50)            | X   | 10       |
| page  | Integer | 페이지 (1 - 50)                | X   | 1        |
| sort  | String  | 정렬 순서 (accuracy / recency)  | X   | accuracy |

#### 응답   

본문

| 이름    | 타입      | 설명                         |
|-------|---------|----------------------------|
| blogs | blog[]  | 블로그 검색 결과                  |

blogs

| 이름      | 타입     | 설명         |
|----------|----------|------------|
| title    | String   | 게시글 제목  |
| content  | String   | 게시글 본문  |
| url      | String   | 게시글 링크  |
| blogName | String   | 블로그 이름  |

### 랭킹 호출   

| 메서드  | URL                           |
|------|-------------------------------|
| GET  | http://localhost:8080/ranking |

#### 응답

본문

| 이름      | 타입        | 설명       |
|---------|-----------|----------|
| ranking | ranking[] | 랭킹 호출 결과 |

ranking

| 이름      | 타입      | 설명     |
|---------|---------|--------|
| keyword | String  | 검색된 단어 |
| hit     | Integer | 검색된 횟수 |


### 설명



## 3.빌드 결과물
- [[다운로드]](https://github.com/MoonHKLee/20230705_2126-000075/blob/master/lib/blog-search-0.0.1-SNAPSHOT.jar)
```shell
$ java -jar blog-search-0.0.1-SNAPSHOT.jar
```