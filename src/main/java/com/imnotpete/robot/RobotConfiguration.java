package com.imnotpete.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

@Configuration
public class RobotConfiguration {

	@Bean(name="gpioController")
	public GpioController gpioController() {
		return GpioFactory.getInstance();
	}
}
