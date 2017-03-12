package com.imnotpete.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobotApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(RobotApplication.class);

	@Autowired
	private RobotDrive robotDrive;

	public static void main(String[] args) {
		SpringApplication.run(RobotApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		for (String string : arg0) {
			System.out.println(string);

			RobotCommand.parseAndExecute(string, robotDrive);
			
			Thread.sleep(1000);
		}
		
		robotDrive.stop();
	}
}
