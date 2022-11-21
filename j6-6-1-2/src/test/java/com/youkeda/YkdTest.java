package com.youkeda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youkeda.comment.model.Comment;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.CommentService;
import com.youkeda.comment.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YkdTest {

    static String sessionId;
    @LocalServerPort
    int randomServerPort;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper mapper;

    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    void contextLoads() throws Exception {


        post("api/user/login?userName=demo&pwd=123", new TypeReference<Result<User>>() {
        });


        Result<Comment> result = post("api/comment/post?refId=2050001&parentId=0&content=abc", new TypeReference<Result<Comment>>() {
        });

        if (result.getData().getId()==0){
            error("没有正确的处理 userId 哦");
        }
    }

    private <T> T post(String url, TypeReference typeReference) throws Exception {
        String baseUrl = "http://localhost:" + randomServerPort + "/" + url;


        URI uri = new URI(baseUrl);
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody());

        if (sessionId != null) {

            builder.header("Cookie", sessionId);

        }

        HttpRequest request = builder.build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String s = response.headers().firstValue("set-cookie").orElse(null);
        if (s != null) {
            sessionId = s;
        }


        return (T) mapper.readValue(response.body(), typeReference);
    }

}
