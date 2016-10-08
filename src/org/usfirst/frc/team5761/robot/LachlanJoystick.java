package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Created by Lachlan on 27/08/2016.
 */
public class LachlanJoystick extends DriveControl {

    private Joystick stick = new Joystick(0);
    private static final int RAISE_ARM_BUTTON = 8;
    private static final int LOWER_ARM_BUTTON = 7;
    private static final int START_VISION_BUTTON = 11;
    NetworkTable table;

    public LachlanJoystick() {
        //table.setClientMode();
        table.setIPAddress("10.57.61.223");
        table = NetworkTable.getTable("datatable");
    }

    public void giveCommands(Robot robot) {
        if (stick.getRawButton(START_VISION_BUTTON)) {
            trackingCommands(robot);
        } else {
            drivingCommands(robot);
            armCommands(robot);
        }
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


    void trackingCommands(Robot robot)
    {
        double x = table.getNumber("X" , table.getNumber("centerX" , 100));
        Robot.displayValue("Vision tracking ","" +  x);

        double adjustment = 0.002;
        double leftMotorPower = x * adjustment;
        double rightMotorPower = -x * adjustment;
        Robot.displayValue("Motor power ", "" + leftMotorPower);
        SmartDashboard.putNumber("x",leftMotorPower);

        robot.drive(leftMotorPower, rightMotorPower);
    }

}
