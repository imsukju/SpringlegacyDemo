# MyDemo 프로젝트

Spring AOP의 Pointcut, Advice, Proxy Creator를 학습하기 위한 프로젝트입니다.

---

## 프로젝트 개요

1. **Pointcut & Advice**:
   - 정적/동적 포인트컷 및 다양한 어드바이스 구현.
2. **Proxy Creator**:
   - Spring에서 Proxy 객체를 생성하여 인터셉션 구현.

---

## 프로젝트 구조

```
MyDemo/
├── SpringDemoOne/
│   ├── src/main/java/com/sj/
│   │   ├── OperationsonPointcuts1/
│   │   │   ├── Main.java                   # 메인 클래스
│   │   │   ├── config/Appconfig.java       # AOP 설정
│   │   │   └── service/SimpleService.java  # 간단한 서비스 구현
│   │   ├── pointcut1/
│   │   │   ├── advice/LoggingAdvice.java   # 로깅 어드바이스
│   │   │   ├── config/AppConfig.java       # Spring 설정
│   │   │   └── service/Myservice.java      # 서비스 구현
└── pom.xml                                  # Maven 프로젝트 설정
```

---

## 주요 기능

### 1. **Pointcut 구현**
- 정적 포인트컷과 동적 포인트컷을 각각 구현.
- `LoggingAdvice`를 사용하여 실행 전/후 로깅.

### 2. **Advice 적용**
- Before, After, Around Advice를 통해 실행 흐름 제어.
- 로깅과 실행 시간 측정 기능 구현.

---

## 실행 방법

1. **필수 요건**
   - Java 17 이상
   - Maven 설치

2. **빌드 및 실행**
   - `OperationsonPointcuts1/Main.java`를 실행하여 Pointcut 및 Advice 테스트.

3. **테스트**
   - `Appconfig.java`에서 설정을 변경하여 로깅 범위를 조정.
