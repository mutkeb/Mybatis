package com.youkeda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youkeda.comment.control.CommentController;
import com.youkeda.comment.dataobject.CommentDO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YkdTest {

    @LocalServerPort
    int randomServerPort;
    @Autowired
    private CommentController commentController;
    @Autowired
    private ObjectMapper mapper;

    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    void contextLoads() throws Exception {

        CommentDO commentDO = new CommentDO();
        commentDO.setContent("测试" + Math.random());
        commentDO.setRefId("2000000001");
        commentDO.setUserId(1);

        httpPost("comment", commentDO);

        commentDO = new CommentDO();
        commentDO.setContent("测试" + Math.random());
        commentDO.setRefId("2000000001");
        commentDO.setUserId(1);

        httpPost("comment", commentDO);

        List<CommentDO> list = httpGet("comment/findByRefId?refId=2000000001");

        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(2);

    }

    private List<CommentDO> httpGet(String url) throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/" + url;
        URI uri = new URI(baseUrl);

        String value = restTemplate.exchange(uri, HttpMethod.GET, null, String.class).getBody();

        return mapper.readValue(value, new TypeReference<List<CommentDO>>() {
        });
    }

    private CommentDO httpPost(String url, CommentDO commentDO) throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/" + url;
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(mapper.writeValueAsString(commentDO), headers);

        ResponseEntity<CommentDO> result = restTemplate.postForEntity(uri, request, CommentDO.class);

        return result.getBody();
    }

}
