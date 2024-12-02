
# ch1 프로젝트

### 개요
Spring의 기본 설정 및 간단한 애플리케이션 구성을 다룹니다. IoC 컨테이너의 동작 원리와 기본적인 Bean 정의/관리 실습 프로젝트입니다.
‘토비의 스프링’ 예제를 Spring Framework 6에 맞게 구현하며 IoC, DI, AOP 등 Spring의 주요 개념을 실습했습니다
### 프로젝트 구조
```
ch1/
├── src/main/java/com/example/
│   ├── Main.java               # 메인 클래스
│   ├── config/AppConfig.java   # Spring 컨테이너 설정
│   ├── service/SimpleService.java  # 단순 서비스 구현
├── src/main/resources/
│   └── application.properties  # 애플리케이션 설정 파일
└── pom.xml                     # Maven 설정 파일
```

### 주요 기능
1. Spring IoC 컨테이너를 사용한 Bean 정의 및 주입.
2. 간단한 서비스 로직을 통해 Spring의 기본 동작을 학습.

### 실행 방법
1. IDE에서 `Main.java`를 실행하여 IoC 컨테이너 동작 확인.
2. Spring 컨텍스트가 서비스 인스턴스를 생성하는 로그를 확인.

---

# ch2 프로젝트

### 개요
DI(Dependency Injection)와 애플리케이션 계층 설계를 실습합니다.

### 프로젝트 구조
```
ch2/
├── src/main/java/com/example/
│   ├── Main.java                   # 메인 클래스
│   ├── service/
│   │   ├── MyService.java          # 핵심 서비스 구현
│   │   └── HelperService.java      # 보조 서비스 구현
│   ├── config/AppConfig.java       # DI 설정 클래스
└── pom.xml                         # Maven 설정 파일
```

### 주요 기능
1. DI를 활용한 서비스 계층 분리.
2. 의존성 주입 방식(생성자 및 Setter) 비교.

### 실행 방법
1. `AppConfig.java`에서 Spring DI 설정 확인.
2. `Main.java`를 실행하여 서비스 간의 의존성 확인.

---

# ch3 프로젝트

### 개요
AOP(Aspect-Oriented Programming)의 기본 원리와 활용 방식을 학습합니다.

### 프로젝트 구조
```
ch3/
├── src/main/java/com/example/
│   ├── Main.java                   # 메인 실행 클래스
│   ├── aspect/LoggingAspect.java   # 로깅 Aspect 구현
│   ├── service/
│   │   ├── MyService.java          # 비즈니스 로직
│   │   └── AnotherService.java     # 추가 서비스 로직
└── pom.xml                         # Maven 설정 파일
```

### 주요 기능
1. AOP를 사용하여 메서드 실행 전/후 로깅.
2. Pointcut 및 Advice 정의 실습.

### 실행 방법
1. `LoggingAspect.java`에서 Pointcut 설정 확인.
2. `Main.java` 실행 후 로깅 동작 확인.

---

# ch4 프로젝트

### 개요
Spring MVC를 활용한 웹 애플리케이션 기본 구조 설계 및 구현.

### 프로젝트 구조
```
ch4/
├── src/main/java/com/example/
│   ├── MainController.java         # 메인 컨트롤러
│   ├── config/WebConfig.java       # Spring MVC 설정
└── src/main/resources/templates/
    ├── home.html                   # 홈 페이지 템플릿
└── pom.xml                         # Maven 설정 파일
```

### 주요 기능
1. Spring MVC 컨트롤러를 통한 요청 처리.
2. HTML 템플릿을 사용한 간단한 뷰 렌더링.

### 실행 방법
1. `MainController.java`를 실행하여 기본 웹 서버 시작.
2. 브라우저에서 `http://localhost:8080` 확인.

---

# ch5 프로젝트

### 개요
Spring Data JPA를 활용한 데이터베이스 연동 실습.

### 프로젝트 구조
```
ch5/
├── src/main/java/com/example/
│   ├── Main.java                   # 메인 실행 클래스
│   ├── entity/User.java            # 사용자 엔티티
│   ├── repository/UserRepository.java # JPA 리포지토리
└── pom.xml                         # Maven 설정 파일
```

### 주요 기능
1. JPA를 사용한 데이터베이스 CRUD 작업.
2. Spring Data JPA 리포지토리를 통한 간단한 데이터 접근.

### 실행 방법
1. 데이터베이스 설정 확인 후 `Main.java` 실행.
2. CRUD 작업 결과를 콘솔에서 확인.

---

# ch6 프로젝트

### 개요
Spring AOP와 Proxy를 활용한 트랜잭션 관리 및 로깅 기능 구현.

### 프로젝트 구조
```
ch6/
├── src/main/java/com/example/
│   ├── Main.java                   # 메인 실행 클래스
│   ├── aspect/TransactionAspect.java # 트랜잭션 관리 Aspect
│   ├── service/MyService.java      # 비즈니스 로직
└── pom.xml                         # Maven 설정 파일
```

### 주요 기능
1. 트랜잭션 시작/종료를 AOP로 관리.
2. 메서드 실행 시간 로깅.

### 실행 방법
1. `TransactionAspect.java`에서 AOP 설정 확인.
2. `Main.java`를 실행하여 트랜잭션 동작 확인.

---

# ch7 프로젝트

### 개요
Spring Security와 인증/인가 로직 설계.

### 프로젝트 구조
```
ch7/
├── src/main/java/com/example/
│   ├── SecurityConfig.java         # Spring Security 설정
│   ├── UserService.java            # 사용자 인증 서비스
│   ├── WebController.java          # 보안이 적용된 웹 컨트롤러
└── pom.xml                         # Maven 설정 파일
```

### 주요 기능
1. Spring Security를 통한 기본 인증/인가 구현.
2. 사용자 역할(Role)에 따른 접근 제어.

### 실행 방법
1. `SecurityConfig.java`에서 사용자 정보 확인.
2. 브라우저에서 로그인 기능 테스트.

