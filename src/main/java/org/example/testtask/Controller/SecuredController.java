package org.example.testtask.Controller;

import org.example.testtask.Config.MyUserDetails;
import org.example.testtask.Model.Log;
import org.example.testtask.Record.ProcessRequest;
import org.example.testtask.Record.ProcessResponse;
import org.example.testtask.Repository.ProcessingLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SecuredController {


    private final RestTemplate restTemplate;
    private final ProcessingLogRepo logRepo;

    @Value("${INTERNAL_TOKEN}")
    private String internalToken;

    @Autowired
    public SecuredController(RestTemplate restTemplate, ProcessingLogRepo logRepo) {
        this.restTemplate = restTemplate;
        this.logRepo = logRepo;
    }


    @PostMapping("/process")
    public ResponseEntity<ProcessResponse> process(@RequestBody ProcessRequest request,
                                                   @AuthenticationPrincipal MyUserDetails authentication) {
        Long userId = authentication.getUser_id();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Token", internalToken);
        headers.setContentType(MediaType.APPLICATION_JSON);


        Map<String, String> body = Map.of("text", request.getText());
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ProcessResponse processResponse = new ProcessResponse();

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "http://serviceb:8081/api/transform",
                    entity,
                    Map.class
            );

            Map<String, String> responseBody = response.getBody();
            processResponse.setResult(responseBody.get("result"));
        } catch (Exception e) {
            e.printStackTrace();
            processResponse.setResult("ERROR");
        }

        Log log = new Log();
        log.setUserId(userId);
        log.setInputText(request.getText());
        log.setOutputText(processResponse.getResult());
        logRepo.save(log);

        return ResponseEntity.ok(processResponse);
    }

}
