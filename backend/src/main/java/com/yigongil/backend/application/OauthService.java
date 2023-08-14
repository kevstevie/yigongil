package com.yigongil.backend.application;

import com.yigongil.backend.config.oauth.GithubAccessToken;
import com.yigongil.backend.config.oauth.GithubClient;
import com.yigongil.backend.config.oauth.GithubProfileResponse;
import com.yigongil.backend.config.oauth.GithubProvider;
import com.yigongil.backend.config.oauth.GithubTokenRequest;
import com.yigongil.backend.config.oauth.JwtTokenProvider;
import com.yigongil.backend.domain.member.Member;
import com.yigongil.backend.domain.member.MemberRepository;
import com.yigongil.backend.response.TokenResponse;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.Optional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OauthService {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final GithubProvider githubProvider;
    private final GithubClient githubClient;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public OauthService(GithubProvider githubProvider, GithubClient githubClient, MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.githubProvider = githubProvider;
        this.githubClient = githubClient;
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public TokenResponse login(String code) {
        GithubAccessToken accessToken = requestGithubAccessToken(code);

        GithubProfileResponse profileResponse = requestGithubProfile(accessToken);

        Optional<Member> member = memberRepository.findByGithubId(profileResponse.githubId());

        Long id = member.orElseGet(
                () -> memberRepository.save(
                        Member.builder()
                              .githubId(profileResponse.githubId())
                              .profileImageUrl(profileResponse.profileImageUrl())
                              .build()
                )
        ).getId();

        return new TokenResponse(jwtTokenProvider.createToken(id));
    }

    private GithubAccessToken requestGithubAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        GithubTokenRequest githubTokenRequest = new GithubTokenRequest(
                githubClient.getId(),
                githubClient.getSecret(),
                code
        );
        HttpEntity<GithubTokenRequest> httpEntity = new HttpEntity<>(githubTokenRequest, httpHeaders);
        return restTemplate.exchange(
                githubProvider.getTokenUrl(),
                HttpMethod.POST,
                httpEntity,
                GithubAccessToken.class
        ).getBody();
    }

    private GithubProfileResponse requestGithubProfile(GithubAccessToken accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + accessToken.accessToken());

        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(
                githubProvider.getUserInfoUrl(),
                HttpMethod.GET,
                httpEntity,
                GithubProfileResponse.class
        ).getBody();
    }

    public TokenResponse renew(String accessToken) {
        try {
            jwtTokenProvider.parseToken(accessToken);
            return new TokenResponse(accessToken);
        } catch (ExpiredJwtException e) {
            return new TokenResponse(jwtTokenProvider.renewToken(accessToken));
        }
    }
}
