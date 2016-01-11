package com.imnotpete.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RobotDrive {

	private RobotGpio robotGpio;
	
	@Autowired
	public RobotDrive(RobotGpio robotGpio) {
		this.robotGpio = robotGpio;
	}
	
	public void forward() {
		robotGpio.set(RobotGpio.Motor.LEFT, RobotGpio.MotorState.FORWARD);
		robotGpio.set(RobotGpio.Motor.RIGHT, RobotGpio.MotorState.FORWARD);
	}
	
	public void reverse() {
		robotGpio.set(RobotGpio.Motor.LEFT, RobotGpio.MotorState.BACKWORD);
		robotGpio.set(RobotGpio.Motor.RIGHT, RobotGpio.MotorState.BACKWORD);
	}
	
	public void left() {
		robotGpio.set(RobotGpio.Motor.LEFT, RobotGpio.MotorState.BACKWORD);
		robotGpio.set(RobotGpio.Motor.RIGHT, RobotGpio.MotorState.FORWARD);
	}
	
	public void right() {
		robotGpio.set(RobotGpio.Motor.LEFT, RobotGpio.MotorState.FORWARD);
		robotGpio.set(RobotGpio.Motor.RIGHT, RobotGpio.MotorState.BACKWORD);
	}
	
	public void stop() {
		robotGpio.set(RobotGpio.Motor.LEFT, RobotGpio.MotorState.OFF);
		robotGpio.set(RobotGpio.Motor.RIGHT, RobotGpio.MotorState.OFF);
	}
}
