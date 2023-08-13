package com.yigongil.backend.ui;

import com.yigongil.backend.application.OauthService;
import com.yigongil.backend.response.TokenResponse;
import com.yigongil.backend.ui.doc.LoginApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/login")
@RestController
public class LoginController implements LoginApi {

    private final OauthService oauthService;

    public LoginController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/github/tokens")
    public ResponseEntity<TokenResponse> createMemberToken(@RequestParam String code) {
        TokenResponse response = oauthService.login(code);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/github/renew")
    public ResponseEntity<TokenResponse> renewMemberToken(@RequestParam String accessToken) {
        TokenResponse response = oauthService.renew(accessToken);
        return ResponseEntity.ok(response);
    }
}
