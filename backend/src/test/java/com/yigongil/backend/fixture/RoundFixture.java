package com.yigongil.backend.fixture;

import com.yigongil.backend.domain.meetingdayoftheweek.MeetingDayOfTheWeek;
import com.yigongil.backend.domain.member.Member;
import com.yigongil.backend.domain.round.Round;
import com.yigongil.backend.domain.roundofmember.RoundOfMember;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RoundFixture {

    아이디_삼_머스트두없는_라운드(3L, 2, null, MemberFixture.김진우.toMember()),
    아이디_사_머스트두없는_라운드(4L, 3, null, MemberFixture.김진우.toMember()),
    아이디_오_머스트두없는_라운드(5L, 4, null, MemberFixture.김진우.toMember()),
    아이디없는_라운드(null, 1, "내용", MemberFixture.김진우.toMember()),
    아이디없는_라운드2(null, 2, "내용", MemberFixture.김진우.toMember()),
    아이디없는_라운드3(null, 3, "내용", MemberFixture.김진우.toMember()),
    아이디없는_라운드4(null, 4, "내용", MemberFixture.김진우.toMember()),
    ;

    private final Long id;
    private final Integer roundNumber;
    private final String content;
    private final Member master;

    RoundFixture(Long id, Integer roundNumber, String content, Member master) {
        this.id = id;
        this.roundNumber = roundNumber;
        this.content = content;
        this.master = master;
    }

    public Round toRound() {
        return Round.builder()
                    .id(id)
                    .mustDo(content)
                    .master(master)
                    .roundOfMembers(new ArrayList<>(
                        List.of(RoundOfMemberFixture.김진우_라운드_삼.toRoundOfMember(),
                            RoundOfMemberFixture.노이만_라오멤.toRoundOfMember())))
                    .meetingDayOfTheWeek(MeetingDayOfTheWeek.builder().dayOfWeek(DayOfWeek.THURSDAY).build())
                    .build();
    }

    public Round toRoundWithCustomDayOfWeek(DayOfWeek dayOfWeek) {
        return Round.builder()
                    .id(id)
                    .mustDo(content)
                    .master(master)
                    .roundOfMembers(new ArrayList<>(
                        List.of(RoundOfMemberFixture.김진우_라운드_삼.toRoundOfMember(),
                            RoundOfMemberFixture.노이만_라오멤.toRoundOfMember())))
                    .meetingDayOfTheWeek(MeetingDayOfTheWeek.builder().dayOfWeek(dayOfWeek).build())
                    .build();
    }

    public Round toRoundWithRoundOfMember(RoundOfMemberFixture... roundOfMemberFixtures) {
        List<RoundOfMember> roundOfMembers = Arrays.stream(roundOfMemberFixtures)
                                                   .map(RoundOfMemberFixture::toRoundOfMember)
                                                   .toList();
        return Round.builder()
                    .id(id)
                    .mustDo(content)
                    .master(master)
                    .roundOfMembers(roundOfMembers)
                    .build();
    }
}
