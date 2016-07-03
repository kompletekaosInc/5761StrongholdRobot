package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Created by jiahpang on 20/06/2016.
 */
public class LogitechJoystick extends DriveControl{

    private Joystick stick;

    public LogitechJoystick()
    {
        stick = new Joystick(0);
    }

    /**
     * Joystick implementation of the core behaviour of a DriveControl.
     *
     * @param robot
     */
    public void giveCommands(Robot robot) {
        drivingCommands(robot);
        armMovements(robot);
    }

    /**
     * Read the stick values to determine the speed and direction to drive the robot.
     * Leverage the throttle position to determine speed.
     *
     * @param robot
     */
    private void drivingCommands( Robot robot ) {
        if (stick.getRawButton(3)){
            robot.stop();
            Robot.displayValue("Stopped","coast");
        }
        else if (stick.getRawButton(4)){
            robot.drive(0.01,0.01);
            Robot.displayValue("Stopped", "brake");
        }
        else if (stick.getRawButton(7)){
            robot.driveStraight(0.5);
        }
        else if (stick.getRawButton(8)){
            robot.resetGyro();
        }

        else {
            //myRobot.arcadeDrive(stick);
            double side = stick.getX();
            double speed = stick.getY();
            double throttle = stick.getThrottle();
            //LOG.debug("teleopPeriodic: raw [side:" + side + "][speed:" + speed + "][throttle:" + throttle + "]");

            Robot.displayValue("RAW side", "" + side);
            Robot.displayValue("RAW speed", "" + speed);

            throttle = (throttle - 1) / 2;
            speed = speed * throttle;
            side = side * throttle;

            double appliedSpeed = (speed * throttle);

            //LOG.debug("teleopPeriodic: [side:" + side + "][speed:" + speed + "][throttle:" + throttle + "]");
            Robot.displayValue("side", "" + side);
            Robot.displayValue("speed", "" + speed);
            Robot.displayValue("throttle", "" + throttle);


            double leftMotorPower = -speed - side;
            double rightMotorPower = (speed - side);

            // tell the robot to drive using the calculated power for the left and right motors
            robot.drive(leftMotorPower, rightMotorPower);
//        robot.drive(.5, -.5);
        }
  }



    private void armMovements(Robot robot){

        // see if the driver wants to raise the arm
        if (stick.getRawButton(12) == true)  // button 12 is pressed
        {
            //driver wants to raise the arm
            robot.raiseArm();

        }
        // see if driver wants to lower the arm
        else if (stick.getRawButton(11) == true) // Button 11 is pressed
        {
            // driver wants to lower the arm
            robot.lowerArm();
        }
        else //no button is pressed
        {
            // the driver has no arm movement commands to process
            robot.stopArm();
        }
    }
}
