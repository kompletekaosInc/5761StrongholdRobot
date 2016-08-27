package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Created by Lachlan on 27/08/2016.
 */
public class LachlanJoystick extends DriveControl {

    private Joystick stick = new Joystick(0);

    public void giveCommands(Robot robot) {
        drivingCommands(robot);
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
}
