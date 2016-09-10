package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Created by Lachlan on 27/08/2016.
 */
public class LachlanJoystick extends DriveControl {

    private Joystick stick = new Joystick(0);
    private static final int RAISE_ARM_BUTTON = 8;
    private static final int LOWER_ARM_BUTTON = 7;

    public void giveCommands(Robot robot) {
        drivingCommands(robot);
        armCommands(robot);
    }

    void drivingCommands(Robot robot)
    {
        double x = stick.getX();
        double y = stick.getY();

        Robot.displayValue("RAW x", "" + x);
        Robot.displayValue("RAW y", "" + y);


        double leftMotorPower = y+x;
        double rightMotorPower = x-y;

        robot.drive(leftMotorPower, rightMotorPower);
    }
    void armCommands(Robot robot)
    {
        if (stick.getRawButton(RAISE_ARM_BUTTON))
            robot.raiseArm();
        else if (stick.getRawButton(LOWER_ARM_BUTTON))
            robot.lowerArm();
        else
            robot.stopArm();

    }
}
