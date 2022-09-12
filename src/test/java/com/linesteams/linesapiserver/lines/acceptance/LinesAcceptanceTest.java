package com.linesteams.linesapiserver.lines.acceptance;

import com.linesteams.linesapiserver.AcceptanceTest;
import com.linesteams.linesapiserver.book.dto.BookRequest;
import com.linesteams.linesapiserver.lines.domain.Ratio;
import com.linesteams.linesapiserver.lines.dto.LinesRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
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

@DisplayName("문구 관련 기능")
class LinesAcceptanceTest extends AcceptanceTest {
    private BookRequest 노인과_바다_책 = new BookRequest("노인과 바다", "해밍웨이", "9788937462788");
    private BookRequest 데미안_책 = new BookRequest("데미안_책", "헤르만헤세", "9788937460449");
    public LinesRequest 노인과_바다 = new LinesRequest("노인과 바다는 좋다", 노인과_바다_책, Ratio.ONE_BY_ONE, "#111111");
    public LinesRequest 데미안 = new LinesRequest("데미안은 좋다", 데미안_책, Ratio.THREE_BY_FOUR, "#ffffff");

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

    @DisplayName("저장된 문구들을 조회할 수 있다")
    @Test
    void getLinesList() {
        // when
        문구_생성_요청(노인과_바다);
        문구_생성_요청(데미안);

        // then
        ExtractableResponse<Response> response = 문구_리스트_요청(PageRequest.of(0, 10));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<LinesResponse> list = response.jsonPath().getList("content", LinesResponse.class);
        assertThat(list.get(0).content).isEqualTo(노인과_바다.getContent());
        assertThat(list.get(1).content).isEqualTo(데미안.getContent());
    }

    private ExtractableResponse<Response> 문구_리스트_요청(PageRequest pageRequest) {
        return RestAssured
                .given().log().all()
                .when().get("/v1/lines?page={page}&size={size}", pageRequest.getPageNumber(), pageRequest.getPageSize())
                .then().log().all()
                .extract();
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
