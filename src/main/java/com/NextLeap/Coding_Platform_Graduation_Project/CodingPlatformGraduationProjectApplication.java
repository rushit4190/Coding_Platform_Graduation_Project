package com.NextLeap.Coding_Platform_Graduation_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.NextLeap.Coding_Platform_Graduation_Project.Model")
public class CodingPlatformGraduationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingPlatformGraduationProjectApplication.class, args);
	}

}
