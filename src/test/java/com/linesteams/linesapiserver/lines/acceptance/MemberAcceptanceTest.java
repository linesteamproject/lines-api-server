package com.linesteams.linesapiserver.lines.acceptance;

import com.linesteams.linesapiserver.AcceptanceTest;
import com.linesteams.linesapiserver.member.domain.OAuthType;
import com.linesteams.linesapiserver.member.dto.LoginRequest;
import com.linesteams.linesapiserver.member.dto.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.linesteams.linesapiserver.lines.acceptance.LinesAcceptanceTest.문구_리스트_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@DisplayName("멤버 관련 기능")
public class MemberAcceptanceTest extends AcceptanceTest {
    public static final LoginRequest 네이버_로그인 = new LoginRequest("msmasd@naver.com", "oauthId", OAuthType.NAVER);
    private static final String ACCESS_TOKEN_PREFIX = "bearer ";

    @DisplayName("회원가입과 로그인처리를 한번에 한다")
    @Test
    void login() {
        // when
        ExtractableResponse<Response> response = 로그인_요청(네이버_로그인);

        토큰_검증(response);
    }

    private void 토큰_검증(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        LoginResponse result = response.jsonPath().getObject("responseData", LoginResponse.class);
        assertThat(result.getAccessToken()).isNotBlank();
        assertThat(result.getRefreshToken()).isNotBlank();
    }

    @DisplayName("리프래시 토큰으로 엑세스 토큰을 갱신할 수 있다")
    @Test
    void refresh() {
        // when
        LoginResponse loginResponse = 로그인_성공(네이버_로그인);

        // then
        ExtractableResponse<Response> refreshResponse = 토큰_리프래시_요청(loginResponse);

        토큰_검증(refreshResponse);
    }

    @DisplayName("로그아웃 된 토큰은 사용할 수 없다")
    @Test
    void logout() {
        // when
        LoginResponse loginResponse = 로그인_성공(네이버_로그인);

        토큰_로그아웃(loginResponse);

        ExtractableResponse<Response> response = 문구_리스트_요청(PageRequest.of(0, 10), convertHeaderAccessToken(loginResponse.getAccessToken()));
        // TODO HttpStatus.UNAUTHORIZED.value 로 바꿔야함
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private static ExtractableResponse<Response> 로그인_요청(LoginRequest request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/v1/member/login")
                .then().log().all()
                .extract();
    }

    private static LoginResponse 로그인_성공(LoginRequest request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/v1/member/login")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("responseData", LoginResponse.class);
    }

    public static String 로그인_엑세스_토큰_획득() {
        String accessToken = RestAssured
                .given().log().all()
                .body(네이버_로그인)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/v1/member/login")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("responseData.accessToken", String.class);

        return convertHeaderAccessToken(accessToken);
    }

    private static String convertHeaderAccessToken(String accessToken) {
        return ACCESS_TOKEN_PREFIX + accessToken;
    }

    private ExtractableResponse<Response> 토큰_리프래시_요청(LoginResponse loginResponse) {
        return RestAssured
                .given().log().all()
                .header(AUTHORIZATION, ACCESS_TOKEN_PREFIX + loginResponse.getAccessToken())
                .header("X-AUTH-REFRESH-TOKEN", loginResponse.getRefreshToken())
                .when().post("/v1/member/login/actions/refresh")
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 토큰_로그아웃(LoginResponse loginResponse) {
        return RestAssured
                .given().log().all()
                .header(AUTHORIZATION, ACCESS_TOKEN_PREFIX + loginResponse.getAccessToken())
                .when().put("/v1/member/logout")
                .then().log().all()
                .extract();
    }
}
