
# SpringAspectJDemo 프로젝트

Spring AspectJ를 활용하여 선언적 AOP와 포인트컷 선언을 학습하는 프로젝트입니다.

---

## 프로젝트 개요

1. **AspectJ 기반 AOP**:
   - 선언적 AOP를 활용하여 포인트컷 및 어드바이스 구현.
2. **서비스 계층 캡슐화**:
   - 서비스 계층과 DAO 계층에서 Aspect 적용.

---

## 프로젝트 구조

```
SpringAspectJDemo/
├── src/main/java/com/AspectjDemo/One/
│   ├── combinedpointcut/
│   │   ├── MainCombine.java                # 메인 클래스
│   │   ├── aspect/LoggingAspectCombine.java # 로깅 애스펙트
│   │   ├── config/AppConfigCombine.java     # AspectJ 설정
│   │   └── service/MyServiceCombine.java    # 서비스 구현
│   ├── declaringpointcut/
│   │   ├── Main.java                        # 포인트컷 선언 클래스
│   │   ├── aspect/LoggingAspect.java        # 로깅 Aspect
│   │   └── config/AppConfigOne.java         # Spring 설정
└── pom.xml                                  # Maven 프로젝트 설정
```

---

## 주요 기능

### 1. **AspectJ 기반 선언적 AOP**
- `LoggingAspectCombine`를 사용하여 로깅 기능 구현.
- 포인트컷 선언 및 애노테이션 기반 설정.

### 2. **서비스 계층 캡슐화**
- `MyServiceCombine`에서 주요 비즈니스 로직 수행.
- DAO 계층과의 연동 및 로깅 적용.

---

## 실행 방법

1. **필수 요건**
   - Java 17 이상
   - Maven 설치

2. **빌드 및 실행**
   - `MainCombine.java` 실행 후 AspectJ 동작 확인.