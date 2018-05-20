package serverTask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayService {

    @Autowired
    private ObjectMapper objectMapper;

    public void printResults(List results) {
        try {

            String jsonResults = objectMapper.writeValueAsString(results);
            System.out.println(jsonResults);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
