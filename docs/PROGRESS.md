# 진행 상태

기준일: 2026-10-28 (Asia/Seoul)

## 현재 단계

1단계: Spring Boot 기본 프로젝트 생성. 기본 빌드·기동과 테스트 1개 통과를 확인했습니다. 서비스 기능은 아직 구현하지 않았습니다.

## 완료 사항

- 초기 문서 6개 생성 및 바탕화면 CodeSentinel 폴더 이동.
- GitHub 원격 저장소: https://github.com/Limpos1/CodeSentinel
- 문서 첫 커밋 `39f07d1`이 로컬과 원격 main에 동일하게 존재함을 확인했습니다.
- 이번 작업 시작 시 미커밋 변경사항이 없었습니다.
- Git 2.53.0.windows.3 설치 확인.
- 사용자 지정 package/group `com.codesentinel`, artifact `codesentinel` 적용.
- Spring Initializr 기반 Java 21 / Spring Boot 4.1.2 / Gradle Wrapper 9.8.2 프로젝트 생성.
- Web MVC, Thymeleaf, Validation 및 테스트 지원 추가.
- 시작 클래스, 애플리케이션 이름 설정, Spring 컨텍스트 기본 테스트 추가.
- 빌드 결과·IDE 파일·로컬 비밀 설정을 제외하는 .gitignore 추가.
- 주요 코드 설명 주석과 README 실행 방법 작성, 구조·결정 문서 동기화.

## 개발 단계 상태

| 단계 | 상태 |
| --- | --- |
| 1. 프로젝트 초기화 | 기본 프로젝트 생성 및 빌드·기동·컨텍스트 테스트 확인 완료 |
| 2. 기본 웹서비스 및 입력 검증 | 계획 / 미구현 |
| 3. 공개 GitHub 저장소 수집 | 계획 / 미구현 |
| 4. Semgrep Java 분석 | 계획 / 미구현 |
| 5. OpenAI API 설명 | 계획 / 미구현 |
| 6. PostgreSQL 저장 및 대시보드 | 계획 / 미구현 |
| 7. 기능·통합 테스트·보안 검증·Docker·배포 | 계획 / 미구현 |

## 환경과 검증

- 초기 문서의 파일 및 내부 링크 존재 확인 완료.
- 시스템에서 java/javac 명령을 찾지 못했고 JAVA_HOME이 비어 있었습니다. 일반적인 JDK 설치 경로에서도 JDK를 찾지 못했습니다.
- 검증용 Temurin JDK 21.0.12.1+1을 저장소 밖 임시 작업 폴더에 다운로드하고 공식 API의 SHA-256과 대조했습니다. java와 javac 실행 확인 완료.
- 시스템 환경변수는 영구 변경하지 않았습니다. 다른 터미널과 IntelliJ에서는 JDK 21을 별도로 지정해야 합니다.
- Gradle 캐시도 저장소 밖 임시 폴더를 사용했습니다. gradlew.bat --version으로 Gradle 9.8.2와 Java 21 조합 확인 완료.
- Wrapper 배포 파일에 공식 SHA-256 검증값을 추가했습니다.
- gradlew.bat test bootJar 첫 실행에서 컴파일과 bootJar가 완료됐지만, 테스트 중 JVM attach 오류와 정체가 발생했습니다.
- Mockito 공식 Java 21 안내에 따라 테스트 시작 시 javaagent로 로드하도록 수정했습니다. 수정 후 Spring 컨텍스트 테스트 1개가 통과했습니다(실패 0, 오류 0, 건너뜀 0).
- 재시도 중 기존 build 하위 디렉터리 삭제 오류가 발생했습니다. 별도 임시 출력 폴더에서 test bootJar 전체 성공을 먼저 확인한 뒤, 기존 build 디렉터리의 읽기 전용 속성을 해제했습니다. 그 후 기본 명령 `gradlew.bat test bootJar --no-daemon --console=plain`도 BUILD SUCCESSFUL로 완료됐습니다. 소스 파일이나 프로젝트 출력 경로는 변경하지 않았습니다.
- 테스트 결과 XML에서도 tests=1, failures=0, errors=0, skipped=0을 확인했습니다. Windows 검증이며 다른 운영체제 테스트는 수행하지 않았습니다.
- 생성된 JAR을 localhost의 임의 포트에서 실행해 Tomcat 및 애플리케이션 시작을 확인했습니다. GET / 요청이 예상대로 HTTP 404를 반환했습니다. 아직 컨트롤러가 없어 404이며, 검증 서버는 종료했습니다.
- 문서 내부 링크 및 build/, .gradle/, .env, application-local.properties의 Git 제외 확인 완료.
- DB 연결, Semgrep, OpenAI, 기능 및 보안 검증은 수행하지 않았습니다.
- 사용자가 IntelliJ의 JDK 다운로드 화면에서 Temurin 21을 선택한 것을 첨부 화면으로 확인했습니다. 다운로드 완료, IDE의 Gradle 동기화 및 직접 실행은 아직 확인하지 않았습니다. PostgreSQL, Semgrep, Docker 설치 여부는 확인하지 않았습니다.

## 다음 작업

1. IntelliJ에서 JDK 21 다운로드와 Gradle 동기화를 완료하고 직접 실행을 확인합니다. Codex의 테스트와 기동 검증은 완료했습니다.
2. 사용자가 다른 컴퓨터에서 이어가기 위한 기본 프로젝트의 커밋·푸시 진행에 동의했습니다. 이번 커밋에는 기본 구성과 관련 문서를 포함하며, 실제 반영 여부는 Git 이력과 원격 main으로 확인합니다. 후속 커밋·푸시·병합·배포는 사용자 승인 후 진행합니다.
3. 2단계에서 기본 화면과 GitHub URL 입력·검증을 구현합니다.
4. 다른 컴퓨터에서는 저장소를 clone한 뒤 JDK 21을 준비하고 AGENTS.md와 이 문서부터 읽습니다. 작업 전 브랜치·변경사항·원격 최신 상태를 확인합니다.
