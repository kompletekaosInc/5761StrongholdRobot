package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class Drivetrain {

    static final Logger LOG = LoggerFactory.getLogger(Drivetrain.class);

    //RobotDrive myRobot;
    private Spark leftMC;
    private Spark rightMC;

    /**
     * Create a Drivetrain.
     */
    public Drivetrain(int portLeftMC, int portRightMC) {
        LOG.info("<constructor>");

        //myRobot = new RobotDrive(0, 1);
        leftMC = new Spark(0);
        rightMC = new Spark(1);

    }


    /**
     * This method will drive the motors of the robot based on the inputs.
     *
     * @param leftPower
     * @param rightPower
     */
    private void drive(double leftPower, double rightPower)
    {
        LOG.debug("drive: [leftPower:" + leftPower + "][rightPower:" + rightPower + "]");

        SmartDashboard.putString("leftPower", ""+leftPower);
        SmartDashboard.putString("rightPower", ""+rightPower);

        leftMC.set(leftPower);
        rightMC.set(rightPower);

    }

    private void stop()
    {
        leftMC.set(0);
        rightMC.set(0);

        LOG.debug("Stop");

    }

}
