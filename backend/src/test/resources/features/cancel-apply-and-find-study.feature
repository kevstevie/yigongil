Feature: 스터디 신청 취소

  Scenario: 스터디 신청자는 신청을 취소할 수 있다.
    Given "jinwoo"의 깃허브 아이디로 회원가입을 한다.
    Given "jinwoo"가 제목-"자바1", 정원-"6"명, 예상시작일-"1"일 뒤, 총 회차-"2"회, 주기-"1w", 소개-"스터디소개1"로 스터디를 개설한다.

    Given "noiman"의 깃허브 아이디로 회원가입을 한다.
    Given 깃허브 아이디가 "noiman"인 멤버가 이름이 "자바1"스터디에 신청할 수 있다.

    When "noiman"이 "자바1" 스터디 신청을 취소한다.
    When "jinwoo"가 이름이 "자바1"인 스터디의 신청자를 조회한다.
    Then 스터디의 신청자는 0명이다.
