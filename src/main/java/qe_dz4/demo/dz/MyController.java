package qe_dz4.demo.dz;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MyController {

    @PostMapping("/stub")
    public String exampleEndpointPost(@RequestBody String body) {
        sleep(); // рандомное время обработки запроса
        String status;
        String token;
        Gson gson = new Gson();
        RequestData requestData = gson.fromJson(body, RequestData.class);
        if (isNumber(requestData.getPhone())) {
            status = "posted";
            token = setToken(); // генерируется токен из 20 символов
        } else {
            status = "false";
            token = "error posted number";
        }

        ResponseData responseData = new ResponseData();
        responseData.setStatus(status);
        responseData.setToken(token);
        responseData.setPhone(requestData.getPhone());

        return gson.toJson(responseData);
    }

    @PutMapping("/stub")
    public String exampleEndpointPut(@RequestBody String body) {
        sleep(); // рандомное время обработки запроса
        String status;
        String token;
        Gson gson = new Gson();
        RequestData requestData = gson.fromJson(body, RequestData.class);
        if (isNumber(requestData.getPhone())) {
            status = "updated";
            token = setToken(); // генерируется токен из 20 символов
        } else {
            status = "false";
            token = "error for update number";
        }

        ResponseData responseData = new ResponseData();
        responseData.setStatus(status);
        responseData.setToken(token);
        responseData.setPhone(requestData.getPhone());

        return gson.toJson(responseData);
    }

    private boolean isNumber(String phone) {
        String regexPattern = "8\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    private void sleep() {
        Random random = new Random();
        int delaySeconds = random.nextInt(3) + 1; // генерируем случайное число от 1 до 3
        try {
            Thread.sleep(delaySeconds * 1000); // переводим задержку в миллисекунды и "спим"
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private String setToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        return token.substring(0, 20);
    }
}


