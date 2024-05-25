<div align="center">
  <h5>Coconet</h5>
</div>

---

<br/>

## 프로젝트 소개

<p style="text-align:center;">
    <img src="https://github.com/B0X12/coconet/assets/86587863/292fc956-dbfd-4731-b07f-6f5c6433a44b" width=100% />
</p>

<br/>

<div align="center" style="margin-top: 1em; margin-bottom: 3em;">
    MDM 기술을 기반으로 하는 사내 보안 인트라넷,<br/>
    <i>Coconet.</i>
</div>

<br/>
<br/>

    [소개글]

    원격 근무 문화가 한국에서도 널리 확산되면서
    원격 환경에서 중요 정보나 기술이 유출될 수 있다는 우려가 증가하고있으나,*
    막상 이를 보호할 수 있는 정책인 BYOD를 도입하는 것은 불필요하거나
    비용이 효율적이지 않다고 여기는 기업이 다수입니다.**

    𝘾𝙊𝘾𝙊𝙉𝙀𝙏은 다음의 기능을 하나의 인트라넷에 통합하는 것으로 위 문제점을
    일부 해결할 수 있다는 의견으로부터 시작하게 된 사내 보안 인트라넷 프로젝트입니다.
    - 페이지에 접근할 수 있는 자격을 제한하는 Authorization
    - 등록된 기기만 사용할 수 있도록 하는 BYOD 정책
    - 모바일 장치의 기능을 제한하는 Mobile Device Management

    출퇴근 현황 관리, 근태 관련 문서 결재와 같은 기본적인 인사 서비스부터,
    기업의 기밀 유출 방지를 위한 모바일 단말 관리(MDM) 서비스를
    모두 하나의 인트라넷에서 관리할 수 있습니다.

    *[1] 머니투데이 Fireside 출시 관련 기사 ('21)
    **[2] CIO코리아 기업 모바일 보안 관련 칼럼 ('21)

[🔗 프로젝트 상세 소개 페이지 ➔](https://box0.notion.site/45948e4e1bdd462885e3de6571d9593d?pvs=4)

<br/>

## 📄 목차

- [🖼 실제 화면](#-실제-화면)
    - [👨‍💼 사용자](#-사용자)
    - [👨‍💻 관리자](#-관리자)
- [🛠 사용 기술](#-사용-기술)
- [🏛 아키텍처](#-아키텍처)
- [📑 발표 자료](#-발표-자료)
- [💿 소개 영상](#-소개-영상)

<br/>

## 🖼 실제 화면

### 👨‍💼 사용자

| ![login](https://github.com/B0X12/coconet/assets/86587863/c9219a88-6c91-455b-95a9-77a4920e0aee) | ![password-change](https://github.com/B0X12/coconet/assets/86587863/afff5c41-1403-4771-a662-e8adea2dadc6) |
|:-----------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------:|
| **⬜ 로그인**                                                                                    | **⬜ 비밀번호 변경**<br/> 휴대폰 인증, 비밀번호 변경 (초기화)                                                     |

| ![main-1](https://github.com/B0X12/coconet/assets/86587863/ecf4350a-c7fc-4e31-b68e-6aff7eb5b585) |
|:------------------------------------------------------------------------------------------------:|
| **⬜ 메인화면** <br/> 프로필 관리, 투두 리스트, 메시지(미구현), 공지사항, 최근 알림                         |

| ![main-2](https://github.com/B0X12/coconet/assets/86587863/7ccb83dc-7d1a-4af5-a1f6-db23ef09afc1) |
|:------------------------------------------------------------------------------------------------:|
| **⬜ 근무 현황** <br/>근무 현황 차트, 결재 현황, 출퇴근 현황                                             |

|  ![main-3](https://github.com/B0X12/coconet/assets/86587863/8eba3afd-5faf-4685-b7a9-d3634717209f)  |
|:--------------------------------------------------------------------------------------------------:|
| **⬜ 기기 관리** <br/> 현재 등록된 기기 제어 현황 관리, 기기 알림 [(차후 cocomo 서비스와 연동 예정)](https://github.com/B0X12/cocomo) |

| ![mypage-1](https://github.com/B0X12/coconet/assets/86587863/4c63f9e4-cb89-46f2-b8ee-a5bd798e4400) |
|:--------------------------------------------------------------------------------------------------:|
| **⬜ 마이페이지 - 기본 정보** <br/> 프로필 이미지 변경, 사용자 이름, 전화번호, 이메일, 생년월일 조회 및 변경       |

| ![mypage-2](https://github.com/B0X12/coconet/assets/86587863/58cb83d7-0f4d-4d97-8634-0980bba30e0b) | ![mypage-3](https://github.com/B0X12/coconet/assets/86587863/8b89f31b-7e2a-4320-bda4-6df26fc9b955) |
|:--------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------:|
| **⬜ 마이페이지 - 조직 정보** <br/> 소속 부서 및 직급 조회                                                | **⬜ 마이페이지 - 비밀번호 변경**                                                                      |

<br/>

### 👨‍💻 관리자

| ![admin-1](https://github.com/B0X12/coconet/assets/86587863/92a63da7-456e-4742-8042-49fca2b940de) | ![admin-log](https://github.com/B0X12/coconet/assets/86587863/cdb66e47-fe42-4997-a97e-bd6ed69768f8) |
|:-------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------:|
| **⬜ 출퇴근 시간 관리** <br/> 근무일, 출퇴근시간, 점심시간, 심야 퇴근 시간 입력 및 수정                       | **⬜ 관리자 로그 조회** <br/> 관리자 레벨의 로그 조회                                                      |

| ![admin-user-log](https://github.com/B0X12/coconet/assets/86587863/d4d95249-ec1c-40cb-827b-b0c3b5760445) | ![admin-manage-device](https://github.com/B0X12/coconet/assets/86587863/e21b9e31-962f-4fba-9a26-7970965e849c) |
|:--------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------:|
| **⬜ 사용자 로그 조회** <br/> 사용자 레벨의 로그를 검색 필터 기반으로 조회                                          | **⬜ 기기 제어** <br/> 특정 사용자의 기기를 제어                                                                   |

| ![admin-manage-notice](https://github.com/B0X12/coconet/assets/86587863/d2cd4ddb-d149-4ea7-9853-57a7bad8a5c5) |
|:-------------------------------------------------------------------------------------------------------------:|
| **⬜ 공지사항 관리** <br/>공지사항 작성, 수정, 삭제, 연도별 조회                                                      |

<br/>

## 🛠 사용 기술

| **분류**           | **기술 스택**    |
|-------------------|-----------------|
| **공통 개발환경**    | ![Windows 10](https://img.shields.io/badge/Windows%2010-0078D6?style=flat-square&logo=windows&logoColor=white) ![Android 11](https://img.shields.io/badge/Android%2011-3DDC84?style=flat-square&logo=android&logoColor=white)<br/> ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) ![H2 Database](https://img.shields.io/badge/H2%20Database-4479A1?style=flat-square&logo=h2&logoColor=white) |
| **백엔드**          | ![Java 11](https://img.shields.io/badge/Java%2011-007396?style=flat-square&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white) ![Spring Security](https://img.shields.io/badge/Spring%20Security-5DB75F?style=flat-square&logo=spring-security&logoColor=white)<br/> ![jjwt](https://img.shields.io/badge/jjwt-000000?style=flat-square&logoColor=white) ![logback](https://img.shields.io/badge/logback-1A1A1A?style=flat-square&logo=logback&logoColor=white) |
| **프론트엔드**      | ![React](https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=white) ![Vite](https://img.shields.io/badge/Vite-646CFF?style=flat-square&logo=vite&logoColor=white) ![Redux](https://img.shields.io/badge/Redux-764ABC?style=flat-square&logo=redux&logoColor=white) ![Axios](https://img.shields.io/badge/Axios-0076C1?style=flat-square&logo=axios&logoColor=white) |


<br/>

## 🏛 아키텍처

![아키텍처](https://github.com/B0X12/coconet/assets/86587863/640fa830-9617-44f8-a545-be002d7bdbf4)

<br/>

## 📑 발표 자료

🥉 교내 프로젝트 경진대회 장려상 수상 (26개 팀 중 3위)

| ![표지](https://github.com/B0X12/coconet/assets/86587863/b8cba785-b8dc-4a7b-98ee-30b81d248760)           | ![소개](https://github.com/B0X12/coconet/assets/86587863/14e99c59-4791-484b-b890-f10e80189929)         |
|:-------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------:|
| ![개요](https://github.com/B0X12/coconet/assets/86587863/0824c6e0-2983-46d5-8182-84ed42d4e5a4)           | ![기술스택](https://github.com/B0X12/coconet/assets/86587863/b2174aff-0e4a-443c-944e-a2c815042888)      |
| ![ERD](https://github.com/B0X12/coconet/assets/86587863/8e9f28d5-a2e6-40fe-8bfa-f94526851cd1)           | ![회원가입-소개](https://github.com/B0X12/coconet/assets/86587863/b3050262-ac1f-4ecf-a0ac-d6a741a2d401)  |
| ![로그인-소개](https://github.com/B0X12/coconet/assets/86587863/f08a17d0-cc17-4fb1-a0f9-dce1f4f90a03)     | ![jwt-기술-구현](https://github.com/B0X12/coconet/assets/86587863/2eee9811-79a5-4bbe-a539-5d6a31c7bf76) |
| ![비밀번호-초기화](https://github.com/B0X12/coconet/assets/86587863/77452d6f-c784-4190-bd72-4fab13faff5b)  | ![메인화면](https://github.com/B0X12/coconet/assets/86587863/78b63164-379e-4c9b-8077-d4a263a76d93)      |
| ![출퇴근-시간-관리](https://github.com/B0X12/coconet/assets/86587863/4d134a52-156f-47c7-8a92-9e74b1184542) |

<br/>

## 💿 소개 영상
🔗 클릭하면 유튜브 링크로 연결됩니다.

[![소개 영상](https://github.com/B0X12/coconet/assets/86587863/cae3d35e-933f-4875-bbbc-8090af420791)](https://youtu.be/RMLZz-xziFQ?si=FNNQ5Lx2xLA89ExJ)

<br/>

---

<br/>

<img src="https://github.com/B0X12/coconet/assets/86587863/c351defd-5fde-4fd4-b882-699c70e7410f" width=10% />

<br/>
<br/>

> 사용 언어 및 라이브러리, 역할 등의 자세한 내용은 여기에 <br/>
[coconet notion ➔](https://box0.notion.site/45948e4e1bdd462885e3de6571d9593d?pvs=4)
