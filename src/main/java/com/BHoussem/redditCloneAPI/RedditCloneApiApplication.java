package com.BHoussem.redditCloneAPI;

import com.BHoussem.redditCloneAPI.config.OpenAPIConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(OpenAPIConfiguration.class)
public class RedditCloneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApiApplication.class, args);
	}

}
