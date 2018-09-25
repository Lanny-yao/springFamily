package com.lanny.spring.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiDocGenerator {

    private static final String HTTP_HEADER_CONTENT_TYPE = "content-type";
    private static final String CONTENT_APPLICATION_JSON = "application/json";
    private static final String API_DOC_FILENAME = "springFamily_swagger_doc.yml";

    @Autowired
    private MockMvc mvc;

    @Test
    public void run() throws Exception {
        String result = mvc.perform(
                get("/v2/api-docs")
                        .header(HTTP_HEADER_CONTENT_TYPE, CONTENT_APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse()
                .getContentAsString();

        Map<String, String> node = new Gson().fromJson(result, new TypeToken<HashMap<String, String>>() {
        }.getType());
        JsonNode value = new ObjectMapper().readTree(node.get("value"));
//        ((ObjectNode) value).remove("host");

        String jsonAsYaml = new YAMLMapper().writeValueAsString(value);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(API_DOC_FILENAME))) {
            writer.write(jsonAsYaml);
        }
    }

}
