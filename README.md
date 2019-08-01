Spring Boot + JPA 프로젝트
=========================

##프로젝트 기본 구성
- Spring Boot
- JPA (기본 + QueryDSL)
- thymeleaf
- H2 DB

##프로젝트 기본 구조

  springboot-example
  
    ├─.idea
    │  ├─codeStyles
    │  ├─libraries
    │  └─modules
    ├─basic-example
    │  └─src
    │      ├─main
    │      │  ├─generated
    │      │  │  └─com
    │      │  │      └─example
    │      │  │          └─basic
    │      │  │              └─model
    │      │  ├─java
    │      │  │  └─com
    │      │  │      └─example
    │      │  │          └─basic
    │      │  │              ├─config
    │      │  │              ├─controller
    │      │  │              ├─model
    │      │  │              ├─repository
    │      │  │              └─service
    │      │  └─resources
    │      │      └─templates
    │      └─test
    │          └─java
    │              └─com
    │                  └─example
    │                      └─basic
    │                          ├─controller
    │                          └─service