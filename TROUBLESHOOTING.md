# 문제 해결 기록

## 2026-03-10 (1) - Entity 기본 생성자 누락으로 인한 데이터 저장 실패

###  문제 상황
#### Spring Boot에서 게시글 저장 API 호출 시 DB에 id만 저장되고 title, content 값이 null로 저장되는 문제가 발생했다.

### 발생 환경
- Spring Boot
- JPA
- MySQL
- Postman 요청

### 발생 코드
```java
@PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }
```

### 원인 분석
Spring의 HTTP Message Converter는 JSON 데이터를 객체로 변환할 때

1. 기본 생성자로 객체를 생성하고
2. Setter 또는 필드를 통해 값을 주입한다.

하지만 Domain 객체에
- 기본 생성자
- Getter / Setter

가 존재하지 않아 JSON 값이 매핑되지 않았다.
#### 참고 JPA 객체 생성 방식
```java
Post post = new Post();   // 기본 생성자
post.setId(1L);
post.setTitle("첫 글");
post.setContent("백엔드 공부");
```

### 해결 방법
domain 객체에 @NoargsContructor @Getter @Setter 어노테이션을 주입했다.

## 2026-03-10 (2) - Optional<Post> 반환 타입 문제
###  문제 상황
```commandline
필요 타입:
Post

제공된 타입:
Optional
<com. junhee. spring_board_api. domain. post. Post>
```

### 발생 환경
- Spring Boot
- JPA
- MySQL
- Postman 요청

### 발생 코드
```java
@GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Long id){
        return postRepository.findById(id);
    }
```

### 원인 분석
Post 객체를 반환해야하는데 Optional<Post> 객체를 반환한다.
#### 참고
Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.
출처: https://mangkyu.tistory.com/70 [MangKyu's Diary:티스토리]

### 해결 방법
```java
return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
```
반환하는 값이 없다면 에러를 반환하도록 .orElseThrow를 작성했다.




















###  문제 상황
#### 

### 발생 환경
- Spring Boot
- JPA
- MySQL
- Postman 요청

### 발생 코드
```java

```

### 원인 분석


