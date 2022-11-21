package com.youkeda;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youkeda.comment.model.Comment;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YkdTest {

    static String sessionId;
    @LocalServerPort
    int randomServerPort;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ObjectMapper mapper;

    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    void contextLoads() throws Exception {

        Result<List<Comment>> result = post("api/comment/query?refId=2050001", new TypeReference<Result<List<Comment>>>() {
        });


        Comment comment = result.getData().get(0);

        if (comment.getChildren() == null || comment.getChildren().get(0) == null) {
            error("没有完成树结构处理哦");
        }


    }


    private <T> T post(String url, TypeReference typeReference) throws Exception {
        String baseUrl = "http://localhost:" + randomServerPort + "/" + url;


        URI uri = new URI(baseUrl);
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json")
                .GET();


        HttpRequest request = builder.build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return (T) mapper.readValue(response.body(), typeReference);
    }

}
