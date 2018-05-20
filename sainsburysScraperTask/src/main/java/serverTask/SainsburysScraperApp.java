package serverTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import serverTask.service.ScrapeService;

import static java.lang.System.exit;

@SpringBootApplication
public class SainsburysScraperApp implements CommandLineRunner {

    @Autowired
    private ScrapeService scrapeService;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(SainsburysScraperApp.class);
        app.run(args);

    }


    @Override
    public void run(String... args) throws Exception {
        scrapeService.getInfo();
        exit(0);
    }

}
