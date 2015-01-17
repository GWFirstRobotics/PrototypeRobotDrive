
package org.usfirst.frc.team5409.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	Joystick driver;
	RobotDrive motors;
	String direction = "None";
	final float[] GEAR  = {0.5f, 0.66f, 1};
	
    public void robotInit() {
    	motors = new RobotDrive(0, 1, 2, 3);
    	driver = new Joystick(0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	motors.tankDrive(0.3, 0.3);
    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopPeriodic() {
    	// Left-Right Input
    	float leftRight = (float)driver.getRawAxis(0);
    	float rightTrigger = (float)driver.getRawAxis(3);
    	float leftTrigger = (float)driver.getRawAxis(2) * -1;
    	
    	// Trigger Input
    	float currentSpeed =  (rightTrigger + leftTrigger) * GEAR[0];
    	
    	// Final Speed Outputs
    	float finalRightSpeed = currentSpeed;
    	float finalLeftSpeed = currentSpeed;
    	
    	if(leftRight > 0){ // Turning Right
    		direction = "Right";
    	}else if(leftRight < 0){ // Turning Left
    		direction = "Left";
    	}
    	
    	// Value to negate from final speed
    	float turnSpeed = Math.abs(leftRight) * currentSpeed;
    	
    	// Negate speed
    	switch(direction){
    	case "Right":
    		finalRightSpeed -= turnSpeed;
    		break;
    	case "Left":
    		finalLeftSpeed -= turnSpeed;
    		break;
    	case "None":
    		break;
    	}
    	
    	if(currentSpeed != 0){
    		motors.tankDrive(finalLeftSpeed, finalRightSpeed);
    		System.out.println("Final Left Speed:" + finalLeftSpeed);
    	}else{ // Stationary turn
    		motors.tankDrive(leftRight * GEAR[0], -1 * leftRight * GEAR[0]);
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	if(driver.getRawButton(1)){
    		System.out.println("Test Print");
    	}
    }
    
}
