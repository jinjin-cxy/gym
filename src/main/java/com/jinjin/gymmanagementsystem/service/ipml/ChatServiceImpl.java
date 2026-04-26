package com.jinjin.gymmanagementsystem.service.ipml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinjin.gymmanagementsystem.service.ChatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjin
 * @date 2026/4/26
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Value("${deepseek.api.key}")
    private String apiKeyFromConfig;

    @Value("${deepseek.api.url:https://api.deepseek.com/chat/completions}")
    private String apiUrl;

    @Value("${deepseek.api.model:deepseek-chat}")
    private String defaultModel;

    @Override
    public String queryChat(String content, String model) {
        if (content == null || content.trim().isEmpty()) {
            return "请输入内容";
        }

        String modelToUse = model == null || model.trim().isEmpty() ? defaultModel : model;
        String apiKey = apiKeyFromConfig;
        if (apiKey == null || apiKey.trim().isEmpty()) {
            apiKey = System.getenv("DEEPSEEK_API_KEY");
        }

        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("DeepSeek API key is not set");
        }

        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> payload = new HashMap<>();
            payload.put("model", modelToUse);
            payload.put("stream", false);

            ArrayList<Map<String, String>> messages = new ArrayList<>();
            messages.add(new HashMap<String, String>() {{
                put("role", "system");
                put("content", "你是一个健身房训练与饮食建议助手。回答要具体、可执行，并给出需要的注意事项。回答问题的时候，加上询问者的名字显得很礼貌");
            }});

            messages.add(new HashMap<String, String>() {{
                put("role", "user");
                put("content", content);
            }});

            payload.put("messages", messages);
            String requestBody = mapper.writeValueAsString(payload);

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(requestBody.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            InputStream is = responseCode >= 200 && responseCode < 300 ? connection.getInputStream() : connection.getErrorStream();

            String responseBody = readAll(is);
            if (responseCode < 200 || responseCode >= 300) {
                throw new RuntimeException("DeepSeek API 请求失败，状态码：" + responseCode + "，请求体：" + requestBody);
            }

            JsonNode root = mapper.readTree(responseBody);
            JsonNode choices = root.path("choices");
            if (choices.isArray() && !choices.isEmpty()) {
                String reply = choices.get(0).path("message").path("content").asText(null);
                if (reply != null && !reply.trim().isEmpty()) {
                    return reply;
                }
            }

            throw new RuntimeException("DeepSeek API 无法解析响应");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readAll(InputStream is) throws IOException {
        if (is == null) return "null";
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
}
