# CodeSentinel

공개 GitHub 저장소의 Java 소스코드를 정적 분석하고, 탐지 결과의 원인과 개선 방향을 AI로 설명하는 웹서비스를 목표로 합니다. 개인 포트폴리오로 시작하여 실제 배포와 운영으로 확장할 계획입니다.

## 현재 상태

2026-10-28 기준 **Spring Boot 기본 프로젝트를 생성했습니다.** Java 21, Spring Boot 4.1.2, Gradle Wrapper 9.8.2를 사용합니다. 기본 기동과 테스트 결과는 [진행 상태](docs/PROGRESS.md)에 기록합니다. 입력 화면, 저장소 수집, 데이터베이스, 정적 분석, AI 연동은 아직 구현하지 않았습니다.

## 계획된 기술 스택

| 영역 | 계획 |
| --- | --- |
| Backend | Java 21, Spring Boot, Gradle |
| Frontend | Thymeleaf, HTML, CSS, JavaScript |
| Database | PostgreSQL |
| AI | OpenAI API |
| 정적 분석 | Semgrep |
| 버전 관리 | Git, GitHub |
| 배포 | Docker, 클라우드 서버 |
| 개발 환경 | Windows, IntelliJ IDEA, Codex |

기본 의존성은 Web MVC, Thymeleaf, Validation과 각 테스트 지원입니다. 스택 변경은 이유와 장단점을 설명하고 사용자 승인을 받습니다.

## MVP 범위 — 모두 계획

1. 공개 GitHub 저장소 URL 입력 및 검증
2. 제한된 범위에서 안전하게 소스코드 수집
3. Java 소스코드 Semgrep 분석
4. OpenAI API로 탐지 결과의 원인과 개선 방향 설명
5. 결과 대시보드 표시
6. 분석 결과 및 작업 상태 PostgreSQL 저장

회원가입, 사용자별 이력, 결과 비교, 언어 확대, GitHub API 고도화, 리포트, 비동기 작업 및 알림은 향후 확장입니다.

## 문서 구조

```text
CodeSentinel/
├── README.md
├── AGENTS.md
├── build.gradle
├── settings.gradle
├── gradlew / gradlew.bat
├── gradle/wrapper/
├── src/main/java/com/codesentinel/CodeSentinelApplication.java
├── src/main/resources/application.properties
├── src/test/java/com/codesentinel/CodeSentinelApplicationTests.java
└── docs/
    ├── ARCHITECTURE.md
    ├── DATABASE.md
    ├── DECISIONS.md
    └── PROGRESS.md
```

- [개발 규칙](AGENTS.md): 작업 전 확인, 승인, 검증, 문서 동기화
- [시스템 구조](docs/ARCHITECTURE.md): 목표 흐름과 보안 검토 사항
- [데이터베이스](docs/DATABASE.md): 저장 요구사항과 미확정 모델
- [설계 결정](docs/DECISIONS.md): 합의한 내용과 미결정 사항
- [진행 상태](docs/PROGRESS.md): 완료, 미구현, 검증 결과, 다음 단계

## 개발 환경과 실행

JDK 21과 Git이 필요합니다. Gradle은 따로 설치하지 않고 저장소의 Wrapper를 사용합니다. 첫 빌드에는 의존성 다운로드를 위한 인터넷 연결이 필요합니다.

IntelliJ IDEA에서는 이 폴더를 Gradle 프로젝트로 열고 Project SDK와 Gradle JVM을 JDK 21로 지정합니다. JDK가 없다면 IDE의 Download JDK 기능으로 21을 준비할 수 있습니다. IntelliJ 설치와 설정은 이번 작업에서 확인하지 않았습니다.

Windows PowerShell에서 JDK 설치 폴더를 지정한 후 프로젝트 루트에서 실행합니다. 아래 JDK 경로는 실제 설치 경로로 바꿉니다. 이 설정은 현재 터미널에만 적용됩니다.

```powershell
$env:JAVA_HOME = 'C:\path\to\jdk-21'
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
java -version
javac -version
.\gradlew.bat test
.\gradlew.bat bootRun
```

서버는 기본 포트 8080에서 시작하며 Ctrl+C로 종료합니다. 아직 화면과 컨트롤러가 없으므로 `http://localhost:8080/`의 HTTP 404 응답은 정상입니다. 이는 분석 기능 완료를 뜻하지 않습니다.

실행 가능한 JAR 생성: `.\gradlew.bat bootJar`. 생성한 JAR 실행: `java -jar build/libs/codesentinel-0.0.1-SNAPSHOT.jar`.

macOS/Linux에서는 JDK 21 환경에서 `sh ./gradlew test`, `sh ./gradlew bootRun`을 사용합니다. 해당 운영체제에서는 아직 검증하지 않았습니다.

Windows에서 컨텍스트 테스트 1개와 JAR 빌드·기동을 확인했습니다. OneDrive 폴더에서 빌드 결과 삭제 오류가 발생하면 다른 실행이 종료됐는지와 build 디렉터리의 읽기 전용 속성을 확인합니다.

이번 검증에는 임시 작업 폴더의 Temurin JDK 21을 사용했습니다. 시스템 JAVA_HOME이나 PATH를 영구 변경하지 않았으며 JDK 자체는 저장소에 포함하지 않습니다. 다른 컴퓨터에서도 JDK 21을 별도로 준비해야 합니다.

PostgreSQL, Semgrep, OpenAI 키, Docker는 연동 단계에서 준비합니다. 비밀정보는 코드에 작성하지 않으며 로컬 환경 파일과 설정은 Git에서 제외합니다.

## 다음 단계

기본 화면과 GitHub URL 입력·검증을 작은 단위로 구현합니다. 분석 기능과 외부 연동은 이후 단계입니다.

## 보안 원칙

공개 GitHub 저장소만 지원하며 수집한 코드를 실행하지 않습니다. URL 검증 및 SSRF 방어, 자원 제한, 도구 격리, 임시 파일 정리, API 비용 제한을 구현 전에 구체화합니다. 외부 코드·README·주석 및 도구 출력은 신뢰하지 않습니다. AI 설명은 검토가 필요한 분석 의견으로 표시합니다. 현재 이 보안 기능들은 구현되지 않았습니다.

## 협업

GitHub 저장소의 소스코드와 문서를 공식 기준으로 사용하며 개별 대화 기록에 의존하지 않습니다. 커밋·푸시·병합·배포는 사용자 승인 후 수행합니다. 다른 컴퓨터에서는 `git clone https://github.com/Limpos1/CodeSentinel.git`으로 내려받고 AGENTS.md와 docs/PROGRESS.md부터 읽습니다.
