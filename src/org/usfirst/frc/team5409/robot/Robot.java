
package org.usfirst.frc.team5409.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

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
	Talon leftFront;
	Talon leftBack;
	Talon rightFront;
	Talon rightBack;
    public void robotInit() {
    	driver = new Joystick(1);
    	leftFront = new Talon(0);
    	leftBack = new Talon(1);
    	rightFront = new Talon(2);
    	rightBack = new Talon(3);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic_old() {
    	float leftStickLeftSideValue = (float)driver.getRawAxis(2);
    	float leftStickRightSideValue = leftStickLeftSideValue;
    	float rightStickLeftSideValue = (float)driver.getRawAxis(3);
    	float rightStickRightSideValue = rightStickLeftSideValue;
    	float turn = (float)driver.getRawAxis(0);
    	if(rightStickLeftSideValue > 0){
    		if(turn > 0){ // Turning Right
    			rightStickRightSideValue += turn*-1;
    		}else if(turn < 0){ // Turning Left
    			rightStickLeftSideValue += turn;
    		}
	    		leftFront.set((rightStickLeftSideValue / 3) * -1);
	            leftBack.set((rightStickLeftSideValue / 3) * -1);
	            rightFront.set(rightStickRightSideValue / 3);
	            rightBack.set(rightStickRightSideValue / 3);
    	} else if(leftStickLeftSideValue > 0){
    		if(turn > 0){ // Turning Right
    			leftStickRightSideValue += turn*-1;
    		}else if(turn < 0){ // Turning Left
    			leftStickLeftSideValue += turn;
    		}
            leftFront.set((leftStickLeftSideValue / 3));
            leftBack.set((leftStickLeftSideValue / 3));
            rightFront.set((leftStickRightSideValue / 3 * -1));
            rightBack.set((leftStickRightSideValue / 3) * -1);
    	}
    	else{
        	leftFront.set(0);
            leftBack.set(0);
            rightFront.set(0);
            rightBack.set(0);
        }
    }
    
    public void teleopPeriodic() {
    	float leftStickLeftSideValue = (float)driver.getRawAxis(2);
    	float leftStickRightSideValue = leftStickLeftSideValue;
    	float rightStickLeftSideValue = (float)driver.getRawAxis(3);
    	float rightStickRightSideValue = rightStickLeftSideValue;
    	float turn = (float)driver.getRawAxis(0);
    	Movement=GetMovement();
    	switch(Movement){
    		case 'Forward':
    		break;
    		case 'Reverse':
    		break;
    		case 'Right-Forward':
    		break;
    		case 'Right-Reverse':
    		break;
    		case 'Left-Forward':
    		break;
    		case 'Left-Reverse':
    		break;
    	
    	}
    	if(rightStickLeftSideValue > 0){
    		if(turn > 0){ // Turning Right
    			rightStickRightSideValue += turn*-1;
    		}else if(turn < 0){ // Turning Left
    			rightStickLeftSideValue += turn;
    		}
	    		leftFront.set((rightStickLeftSideValue / 3) * -1);
	            leftBack.set((rightStickLeftSideValue / 3) * -1);
	            rightFront.set(rightStickRightSideValue / 3);
	            rightBack.set(rightStickRightSideValue / 3);
    	} else if(leftStickLeftSideValue > 0){
    		if(turn > 0){ // Turning Right
    			leftStickRightSideValue += turn*-1;
    		}else if(turn < 0){ // Turning Left
    			leftStickLeftSideValue += turn;
    		}
            leftFront.set((leftStickLeftSideValue / 3));
            leftBack.set((leftStickLeftSideValue / 3));
            rightFront.set((leftStickRightSideValue / 3 * -1));
            rightBack.set((leftStickRightSideValue / 3) * -1);
    	}
    	else{
        	leftFront.set(0);
            leftBack.set(0);
            rightFront.set(0);
            rightBack.set(0);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
