Feature: 회차를 조회한다.

  Scenario: 스터디를 정상 생성하고 회차를 조회한다.
    Given "jinwoo"의 깃허브 아이디로 회원가입을 한다.
    Given "jinwoo"가 제목-"자바1", 정원-"6"명, 예상시작일-"1"일 뒤, 총 회차-"2"회, 주기-"1w", 소개-"스터디소개1"로 스터디를 개설한다.
    When "jinwoo"가 이름이 "자바1"인 스터디의 1 회차를 찾는다.
    Then 스터디 장이 "jinwoo"이고 해당 회차 인것을 확인할 수 있다.

  Scenario Outline: 회차 구성원들의 투두 진행률을 조회한다.

    Given "jinwoo"의 깃허브 아이디로 회원가입을 한다.
    Given "yujamint"의 깃허브 아이디로 회원가입을 한다.
    Given "jinwoo"가 제목-"자바1", 정원-"6"명, 예상시작일-"1"일 뒤, 총 회차-"2"회, 주기-"1w", 소개-"스터디소개1"로 스터디를 개설한다.
    Given 깃허브 아이디가 "yujamint"인 멤버가 이름이 "자바1"스터디에 신청한다.
    Given "jinwoo"가 "yujamint"의 "자바1" 스터디 신청을 수락한다.
    Given "jinwoo"가 이름이 "자바1"인 스터디를 "MONDAY"에 진행되도록 하여 시작한다.
    Given "jinwoo"가 이름이 "자바1"인 스터디의 1 회차를 찾는다.
    Given "jinwoo"가 찾은 회차에 "<투두 내용>"로 필수 투두를 추가한다.

    When "jinwoo"가 "자바1"스터디 피드에 "내용"의 인증 글을 작성한다.
    When 이름이 "자바1"인 스터디의 찾은 회차 투두 진행률을 조회한다.

    Then 진행률이 "<기대 진행률>"이다.

    Examples:
      | 투두 내용  | 기대 진행률 |  |
      | 새로운 투두 | 50     |  |
