package com.imnotpete.robot;

public class RobotCommand {

	public static void parseAndExecute(String command, RobotDrive robotDrive) {
		switch (command) {
		case "forward":
			robotDrive.forward();
			break;
		case "reverse":
			robotDrive.reverse();
			break;
		case "left":
			robotDrive.left();
			break;
		case "right":
			robotDrive.right();
			break;
		case "stop":
			robotDrive.stop();
			break;
		default:
			throw new IllegalArgumentException(String.format("Command [%s] is invalid", command));
		}
	}
}
