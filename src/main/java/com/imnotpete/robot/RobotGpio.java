package com.imnotpete.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Component
public class RobotGpio {

	private Pin ten = RaspiPin.GPIO_12;
	private Pin nine = RaspiPin.GPIO_13;
	private Pin nineteen = RaspiPin.GPIO_24;
	private Pin twenty = RaspiPin.GPIO_28;

	private GpioPinDigitalOutput tenOut;
	private GpioPinDigitalOutput nineOut;
	private GpioPinDigitalOutput nineteenOut;
	private GpioPinDigitalOutput twentyOut;

	public static enum Motor {
		LEFT, RIGHT;
	}

	public static enum MotorState {
		FORWARD, BACKWORD, OFF;
	}

	@Autowired
	public RobotGpio(GpioController gpio) {
		tenOut = gpio.provisionDigitalOutputPin(ten, PinState.LOW);
		nineOut = gpio.provisionDigitalOutputPin(nine, PinState.LOW);
		nineteenOut = gpio.provisionDigitalOutputPin(nineteen, PinState.LOW);
		twentyOut = gpio.provisionDigitalOutputPin(twenty, PinState.LOW);
	}

	public void set(Motor motor, MotorState state) {
		switch (motor) {
		case LEFT:
			setState(tenOut, nineOut, state);
			break;
		case RIGHT:
			setState(nineteenOut, twentyOut, state);
			break;
		default:
			break;
		}
	}
	
	private void setState(GpioPinDigitalOutput pin1, GpioPinDigitalOutput pin2, MotorState state) {
		switch (state) {
		case FORWARD:
			pin1.high();
			pin2.low();
			break;
		case BACKWORD:
			pin1.low();
			pin2.high();
			break;
		case OFF:
			pin1.low();
			pin2.low();
			break;
		default:
			break;
		}
	}
}
