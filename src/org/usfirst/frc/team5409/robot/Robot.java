
package org.usfirst.frc.team5409.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;

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
	Jaguar lift;
	Encoder liftRotation;
	String direction = "None";
	DoubleSolenoid mySolenoid= new DoubleSolenoid(0,1);
	int currentGear=0;
	
	boolean isOpen=false;
	
	final float[] GEAR  = {0.33f,0.66f , 1};
	
    public void robotInit() {
    	
    	motors = new RobotDrive(0, 1, 2, 3);
    	lift  = new Jaguar(4);
    	driver = new Joystick(0);
    	//liftRotation = new Encoder()
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
    	float leftRight = 0;
    	leftRight = (float)driver.getRawAxis(0);
    	float rightTrigger = (float)driver.getRawAxis(3) * -1;
    	float leftTrigger = (float)driver.getRawAxis(2);
    	boolean rightCloseButton = driver.getRawButton(2);
    	boolean leftOpenButton = driver.getRawButton(1);
    	boolean increaseGear= driver.getRawButton(8);
    	boolean lowerGear= driver.getRawButton(7);
    	
    	//up and down motor
    	float rightStick = (float)driver.getRawAxis(5);
    	
    	
    	// Trigger Input
    	float currentSpeed =  (rightTrigger + leftTrigger) * GEAR[currentGear];
    	
    	float finalRightSpeed = currentSpeed;
    	float finalLeftSpeed = currentSpeed;
    	
    	// Final Speed Outputs
    	
    	
    	if(leftRight < 0){ // Turning Right
    		direction = "Right";
    	}else if(leftRight > 0){ // Turning Left
    		direction = "Left";
    	}
    	
    	// Value to negate from final speed
    	float turnSpeed = Math.abs(leftRight) * currentSpeed;
    	
    	// Negate speed
    	switch(direction){
    	case "Right":
    		finalRightSpeed -= turnSpeed*GEAR[currentGear];
    		break;
    	case "Left":
    		finalLeftSpeed -= turnSpeed*GEAR[currentGear];
    		break;
    	case "None":
    		break;
    	}
    	
    	if(rightTrigger!=0){
       	 finalLeftSpeed = finalLeftSpeed *((float)0.947);
       	}
       	else if(leftTrigger!=0){
       		 finalRightSpeed = finalRightSpeed*((float)0.947);
       	}
    	
    	if(rightStick !=0 ){
    		lift.set(rightStick * 10);
    		
    	}
    	if(currentSpeed != 0){
    		motors.tankDrive(finalLeftSpeed, finalRightSpeed);
    		System.out.println("Final Left Speed:" + finalLeftSpeed);
    	}else{ // Stationary turn
    		motors.tankDrive(leftRight * GEAR[currentGear], -1 * leftRight * GEAR[currentGear]);
    	}
    	
    	if (rightCloseButton&&!isOpen) {
    		mySolenoid.set(DoubleSolenoid.Value.kForward);
    		isOpen=true;
    		System.out.println("working");
    	}
    	else if (leftOpenButton&&isOpen) {
    		mySolenoid.set(DoubleSolenoid.Value.kReverse);
    		isOpen=false;
    		System.out.println("working");
    	}
    	else{
    		mySolenoid.set(DoubleSolenoid.Value.kOff);
    	}
    	
    	if(increaseGear&&currentGear<2){
    		currentGear++;
    	}
    	else if(lowerGear&&currentGear>0){
    		currentGear--;
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
