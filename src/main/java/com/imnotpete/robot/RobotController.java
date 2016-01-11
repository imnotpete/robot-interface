package com.imnotpete.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotController {
	private Logger logger = LoggerFactory.getLogger(RobotController.class);

	private RobotDrive robotDrive;
	
	@Autowired
	public RobotController(RobotDrive robotDrive) {
		this.robotDrive = robotDrive;
	}
	
	@RequestMapping("/robot/{direction}")
	public void drive(@PathVariable("direction") String direction) {
		logger.info(String.format("Driving [%s]", direction));
		
		RobotCommand.parseAndExecute(direction, robotDrive);
	}
}
