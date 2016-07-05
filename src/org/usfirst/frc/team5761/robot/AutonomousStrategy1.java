package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiah on 3/07/2016.
 */
public class AutonomousStrategy1 {

    static final Logger LOG = LoggerFactory.getLogger(AutonomousStrategy1.class);
    private long startTimeMs = 0;
    private Robot robot;


    public AutonomousStrategy1(Robot theRobot) {
        startTimeMs = System.currentTimeMillis();

        SmartDashboard.putString("auto startTimeMs", ""+startTimeMs);
        SmartDashboard.putString("auto currentAutoRunDuration", "0");

        robot = theRobot;

        //robot.resetGyro();
    }

    public void doAutonomous()
    {
        long currentMs = System.currentTimeMillis();

        long currentAutoRunDuration = currentMs - startTimeMs;

        SmartDashboard.putString("auto currentAutoRunDuration", ""+currentAutoRunDuration);

        //The first two seconds are reserved for the gyro reset in the init

        if (currentAutoRunDuration <= 1000)
        {
            // in 1 second of auto
            LOG.info("doAutonomous in second 1000");
            robot.driveStraight(0.4);
        }
        else if (currentAutoRunDuration <= 2000)
        {
            // in 2 second of auto
            LOG.info("doAutonomous in second 2000");
            robot.driveStraight(0.8);
        }
        else if (currentAutoRunDuration <= 3000)
        {
            // in 3 second of auto
            LOG.info("doAutonomous in second 3000");
            robot.driveStraight(0.8);
        }
        else if (currentAutoRunDuration <= 4000)
        {
            // in 4 second of auto
            LOG.info("doAutonomous in second 4000");
            //robot.turnGyro(180);
            robot.stop();
            robot.drive(-0.1, 0.1);
        }
        else
        {
            LOG.info("Auto: after 5 seconds");

        }

    }
}
