# MySpringAop2 프로젝트

Spring AOP를 활용하여 트랜잭션 관리와 인터셉션 기능을 학습하는 프로젝트입니다.

---

## 프로젝트 개요

1. **AOP 기반 트랜잭션 관리**:
   - AOP를 활용하여 트랜잭션 처리를 수행.
2. **Before/After Advice**:
   - 서비스 메서드 실행 전후에 로직 추가.

---

## 프로젝트 구조

```
MySpringAop2/
├── src/main/java/com/sj0829/UsingAutoProxy/
│   ├── Main.java                            # 메인 실행 클래스
│   ├── config/AppconfigUsingAutoProxy.java  # AOP 설정
│   ├── bean/BussinessObject1.java           # 비즈니스 로직 1
│   └── service/BusinessService.java         # 비즈니스 서비스
└── pom.xml                                  # Maven 프로젝트 설정
```

---

## 주요 기능

### 1. **AOP를 통한 트랜잭션 관리**
- `BusinessService`의 메서드 호출 시 트랜잭션 처리.
- `AppconfigUsingAutoProxy`에서 트랜잭션 매니저 설정.

### 2. **Before/After Advice**
- 메서드 실행 전후에 로깅 및 트랜잭션 상태 출력.

---

## 실행 방법

1. **필수 요건**
   - Java 17 이상
   - Maven 설치

2. **빌드 및 실행**
   - `UsingAutoProxy/Main.java`를 실행하여 트랜잭션 및 AOP 테스트.

3. **테스트**
   - 설정 파일에서 트랜잭션 범위를 조정하여 동작 확인.
