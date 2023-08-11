Feature: 스터디 참여 신청을 허가한다.

  Scenario: 스터디 참여 신청을 정상적으로 허가한다.
    Given "jinwoo"의 깃허브 아이디로 회원가입을 한다.
    Given "jinwoo"가 제목-"자바1", 정원-"6"명, 예상시작일-"1"일 뒤, 총 회차-"2"회, 주기-"1w", 소개-"스터디소개1"로 스터디를 개설한다.

    Given "yujamint"의 깃허브 아이디로 회원가입을 한다.
    Given 깃허브 아이디가 "yujamint"인 멤버가 이름이 "자바1"스터디에 신청한다.

    When "jinwoo"가 "yujamint"의 "자바1" 스터디 신청을 수락한다.
    When "yujamint"가 스터디 상세 조회에서 이름이 "자바1"인 스터디를 조회한다.
    Then "yujamint"는 "자바1" 스터디의 스터디원으로 추가되어 있다.