package com.linesteams.linesapiserver.lines.acceptance;

import com.linesteams.linesapiserver.AcceptanceTest;
import com.linesteams.linesapiserver.book.dto.BookRequest;
import com.linesteams.linesapiserver.lines.domain.Ratio;
import com.linesteams.linesapiserver.lines.dto.LinesCreateRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.dto.LinesUpdateRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@DisplayName("문구 관련 기능")
class LinesAcceptanceTest extends AcceptanceTest {
    private final BookRequest 노인과_바다_책 = new BookRequest("노인과 바다", "해밍웨이", "9788937462788");
    private final BookRequest 데미안_책 = new BookRequest("데미안_책", "헤르만헤세", "9788937460449");
    public LinesCreateRequest 노인과_바다 = new LinesCreateRequest("노인과 바다는 좋다", 노인과_바다_책, Ratio.ONE_BY_ONE, "#111111", "sanslif", "right");
    public LinesCreateRequest 데미안 = new LinesCreateRequest("데미안은 좋다", 데미안_책, Ratio.THREE_BY_FOUR, "#ffffff", "godic", "left");
    private String accessToken;

    @DisplayName("문구를 생성한다")
    @Test
    void createLines() {
        accessToken = MemberAcceptanceTest.로그인_엑세스_토큰_획득();
        // when
        ExtractableResponse<Response> response = 문구_생성_요청(노인과_바다);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        LinesResponse result = response.jsonPath().getObject("responseData", LinesResponse.class);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo(노인과_바다.getContent());
        assertThat(result.getFont()).isEqualTo(노인과_바다.getFont());
        assertThat(result.getTextAlignment()).isEqualTo(노인과_바다.getTextAlignment());
        assertThat(result.getBookResponse()).isNotNull();
    }

    @DisplayName("문구를 수정한다")
    @Test
    void updateLines() {
        accessToken = MemberAcceptanceTest.로그인_엑세스_토큰_획득();
        // when
        LinesResponse linesResponse = 문구_생성_요청_결과(노인과_바다);
        ExtractableResponse<Response> response = 문구_수정_요청(new LinesUpdateRequest(데미안.getContent(), 데미안.getRatio(), 데미안.getBackground(), 데미안.getFont(), 데미안.getTextAlignment()), linesResponse.getId());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        LinesResponse result = response.jsonPath().getObject("responseData", LinesResponse.class);
        assertThat(result.getContent()).isEqualTo(데미안.getContent());
        assertThat(result.getRatio()).isEqualTo(데미안.getRatio());
        assertThat(result.getBackground()).isEqualTo(데미안.getBackground());
        assertThat(result.getFont()).isEqualTo(데미안.getFont());
        assertThat(result.getTextAlignment()).isEqualTo(데미안.getTextAlignment());
    }

    @DisplayName("문구를 지울 수 있다")
    @Test
    void deleteLines() {
        accessToken = MemberAcceptanceTest.로그인_엑세스_토큰_획득();
        // when
        LinesResponse linesResponse = 문구_생성_요청_결과(노인과_바다);

        // then
        ExtractableResponse<Response> deleteResponse = 문구_제거_요청(linesResponse.getId());
        assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.OK.value());

        ExtractableResponse<Response> response = 문구_리스트_요청(PageRequest.of(0, 10));
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<LinesResponse> list = response.jsonPath().getList("responseData.content", LinesResponse.class);
        assertThat(list).isEmpty();
    }

    @DisplayName("저장된 문구들을 조회할 수 있다")
    @Test
    void getLinesList() {
        accessToken = MemberAcceptanceTest.로그인_엑세스_토큰_획득();
        // when
        문구_생성_요청(노인과_바다);
        문구_생성_요청(데미안);

        // then
        ExtractableResponse<Response> response = 문구_리스트_요청(PageRequest.of(0, 10));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<LinesResponse> list = response.jsonPath().getList("responseData.content", LinesResponse.class);
        assertThat(list.get(0).getContent()).isEqualTo(노인과_바다.getContent());
        assertThat(list.get(1).getContent()).isEqualTo(데미안.getContent());
    }

    @DisplayName("문구 공유 로그를 생성할 수 있다")
    @Test
    void createShareLog() {
        accessToken = MemberAcceptanceTest.로그인_엑세스_토큰_획득();
        // when
        LinesResponse linesResponse = 문구_생성_요청_결과(노인과_바다);

        // then
        ExtractableResponse<Response> response = 문구_공유_로그_생성_요청(linesResponse.getId());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> 문구_리스트_요청(PageRequest pageRequest) {
        return 문구_리스트_요청(pageRequest, accessToken);
    }

    public static ExtractableResponse<Response> 문구_리스트_요청(PageRequest pageRequest, String accessToken) {
        return RestAssured
                .given().log().all()
                .header(AUTHORIZATION, accessToken)
                .when().get("/v1/lines?page={page}&size={size}", pageRequest.getPageNumber(), pageRequest.getPageSize())
                .then().log().all()
                .extract();
    }

    private LinesResponse 문구_생성_요청_결과(LinesCreateRequest request) {
        return 문구_생성_요청(request)
                .jsonPath()
                .getObject("responseData", LinesResponse.class);
    }

    private ExtractableResponse<Response> 문구_생성_요청(LinesCreateRequest request) {
        return RestAssured
                .given().log().all()
                .header(AUTHORIZATION, accessToken)
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/v1/lines")
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 문구_수정_요청(LinesUpdateRequest request, Long linesId) {
        return RestAssured
                .given().log().all()
                .header(AUTHORIZATION, accessToken)
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/v1/lines/{id}", linesId)
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 문구_제거_요청(Long linesId) {
        return RestAssured
                .given().log().all()
                .header(AUTHORIZATION, accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/v1/lines/{id}", linesId)
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 문구_공유_로그_생성_요청(Long linesId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/v1/lines/{id}/share-log", linesId)
                .then().log().all()
                .extract();
    }

}
