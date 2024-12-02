# SpringConfigurableSampleDemo 프로젝트

Spring Configurable과 Spring AOP를 활용하여 의존성 주입과 성능 로깅을 학습하는 프로젝트입니다.

---

## 프로젝트 개요

이 프로젝트는 Spring AOP와 Configurable을 사용하여 다음 기능을 구현합니다:

1. **Spring AOP**:
   - AOP를 활용한 성능 모니터링(Profiling) 기능.
2. **Spring Configurable**:
   - Spring에서 Java 객체에 의존성을 주입하는 기능 실습.
3. **다양한 레이어**:
   - 서비스 계층, DAO, 그리고 모델 객체를 통해 계층화된 구조 구현.

---

## 프로젝트 구조

```
SpringConfigurableSampleDemo/
├── src/main/java/com/configurableOne/
│   ├── Main.java                            # 메인 실행 클래스
│   ├── aspect/ProfilingAspect.java          # 성능 분석을 위한 AOP Aspect
│   ├── config/Appconfigrable.java           # Spring Configurable 설정
│   ├── dao/AccountSpringConfigurable.java   # DAO 구현
│   ├── service/
│   │   ├── EntitlementCalculationService.java    # 자격 계산 서비스
│   │   ├── MyServiceSpringConfigurable.java      # Spring Configurable 서비스
│   │   └── StubEntitlementCalculationService.java # 더미 구현 서비스
├── src/main/resources/META-INF/aop.xml      # AOP 설정 파일
└── pom.xml                                  # Maven 프로젝트 설정
```

---

## 주요 기능

### 1. **Spring AOP**
- `ProfilingAspect`를 사용하여 메서드 실행 시간 측정.
- `aop.xml` 파일에서 Pointcut 설정을 통해 성능 로깅 적용.

### 2. **Spring Configurable**
- `Appconfigrable.java`를 사용하여 Configurable 객체에 의존성을 주입.
- `AccountSpringConfigurable`에서 Spring Configurable을 통해 DAO 객체 생성.

### 3. **서비스 계층**
- `EntitlementCalculationService`와 `StubEntitlementCalculationService`로 비즈니스 로직 처리.

---

## 실행 방법

1. **필수 요건**
   - Java 17 이상
   - Maven 설치

2. **빌드 및 실행**
   - `Main.java`를 실행하여 AOP 및 Configurable 기능을 확인.
   - 콘솔에서 성능 로그 확인 가능.

3. **테스트**
   - `aop.xml`에서 설정을 변경하여 Pointcut 동작을 테스트.

