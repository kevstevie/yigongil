Feature: 스터디 목록 조회 기능

  Scenario: 지원한 스터디를 조회한다.
    Given "jinwoo"의 깃허브 아이디로 회원가입을 한다.
    Given "jinwoo"가 제목-"자바1", 정원-"6"명, 최소 주차-"7"주, 주당 진행 횟수-"3"회, 소개-"스터디소개1"로 스터디를 개설한다.
    Given "noiman"의 깃허브 아이디로 회원가입을 한다.
    Given 깃허브 아이디가 "noiman"인 멤버가 이름이 "자바1"스터디에 신청한다.

    When "noiman"이 지원한 스터디 목록을 조회한다.

    Then 1 개의 스터디를 확인할 수 있다.

  Scenario: 지원한 스터디를 검색을 통해 조회한다.
    Given "jinwoo"의 깃허브 아이디로 회원가입을 한다.
    Given "jinwoo"가 제목-"자바1", 정원-"6"명, 최소 주차-"7"주, 주당 진행 횟수-"3"회, 소개-"스터디소개1"로 스터디를 개설한다.
    Given "jinwoo"가 제목-"자바2", 정원-"6"명, 최소 주차-"7"주, 주당 진행 횟수-"3"회, 소개-"스터디소개1"로 스터디를 개설한다.
    Given "jinwoo"가 제목-"자스", 정원-"6"명, 최소 주차-"7"주, 주당 진행 횟수-"3"회, 소개-"스터디소개1"로 스터디를 개설한다.
    Given "noiman"의 깃허브 아이디로 회원가입을 한다.
    Given 깃허브 아이디가 "noiman"인 멤버가 이름이 "자바1"스터디에 신청한다.
    Given 깃허브 아이디가 "noiman"인 멤버가 이름이 "자바2"스터디에 신청한다.
    Given 깃허브 아이디가 "noiman"인 멤버가 이름이 "자스"스터디에 신청한다.

    When "noiman"이 지원한 스터디 목록을 "자바" 검색어와 조회한다.

    Then 2 개의 스터디를 확인할 수 있다.

  Scenario: 스터디 목록을 검색한다.
    Given "jinwoo"의 깃허브 아이디로 회원가입을 한다.
    Given "jinwoo"가 제목-"자바1", 정원-"6"명, 최소 주차-"7"주, 주당 진행 횟수-"3"회, 소개-"스터디소개1"로 스터디를 개설한다.
    Given "jinwoo"가 제목-"자바2", 정원-"6"명, 최소 주차-"7"주, 주당 진행 횟수-"3"회, 소개-"스터디소개1"로 스터디를 개설한다.
    Given "jinwoo"가 제목-"자스", 정원-"6"명, 최소 주차-"7"주, 주당 진행 횟수-"3"회, 소개-"스터디소개1"로 스터디를 개설한다.

    When 스터디 목록을 "자바" 검색어와 조회한다.

    Then 2 개의 스터디를 확인할 수 있다.
