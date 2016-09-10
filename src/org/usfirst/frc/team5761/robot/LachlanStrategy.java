package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Lachlan on 10/09/2016.
 */
public class LachlanStrategy extends AutonomousControl {

    private Robot robot;


    private long startTimeMs = 0;






    public LachlanStrategy(Robot theRobot) {
        startTimeMs = System.currentTimeMillis();
        robot = theRobot;
    }


    public void doAutonomous() {
        long currentMs = System.currentTimeMillis();

        long currentAutoRunDuration = currentMs - startTimeMs;

        if (currentAutoRunDuration <= 3000) {
            robot.drive(0.5, 0.9);
            robot.stopArm();
        } else if (currentAutoRunDuration <= 7000) {
            robot.stop();
            robot.raiseArm();
        } else {
            robot.stop();
            robot.stopArm();
        }
    }

}

