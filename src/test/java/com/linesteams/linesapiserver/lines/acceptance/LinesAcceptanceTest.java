package com.linesteams.linesapiserver.lines.acceptance;

import com.linesteams.linesapiserver.AcceptanceTest;
import com.linesteams.linesapiserver.lines.domain.Ratio;
import com.linesteams.linesapiserver.lines.dto.LinesRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("문구 관련 기능")
class LinesAcceptanceTest extends AcceptanceTest {
    public LinesRequest 노인과_바다 = new LinesRequest("노인과 바다는 좋다", "9788937462788", Ratio.ONE_BY_ONE, "#111111");

    @DisplayName("문구를 생성한다")
    @Test
    void createLines() {
        // when
        ExtractableResponse<Response> response = 문구_생성_요청(노인과_바다);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        LinesResponse result = response.as(LinesResponse.class);
        assertThat(result.id).isNotNull();
        assertThat(result.content).isEqualTo(노인과_바다.getContent());
        assertThat(result.bookResponse).isNotNull();
    }

    private ExtractableResponse<Response> 문구_생성_요청(LinesRequest request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/v1/lines")
                .then().log().all()
                .extract();
    }

}
