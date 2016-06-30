package org.usfirst.frc.team8761.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Drivetrain {

    static final Logger LOG = LoggerFactory.getLogger(Drivetrain.class);

    //RobotDrive myRobot;
    private Spark leftMC;
    private Spark rightMC;

    ADXRS450_Gyro gyro = null;


    /**
     * Create a Drivetrain.
     */
    public Drivetrain() {
        LOG.info("<constructor>");

        //myRobot = new RobotDrive(0, 1);
        leftMC = new Spark(0);
        rightMC = new Spark(1);


        // attempt to create the gyro
        try {
            gyro = new ADXRS450_Gyro();
            gyro.reset();
            gyro.calibrate();

            Robot.displayValue("Gyro Installed", "yes");

        } catch (Exception e) {
            LOG.error("Gyro not installed correctly", e);
            Robot.displayValue("Gyro Installed", "no");
        }
    }


    /**
     * This method will drive the motors of the robot based on the inputs.
     *
     * @param leftPower
     * @param rightPower
     */
    public void drive(double leftPower, double rightPower)
    {
        LOG.debug("drive: [leftPower:" + leftPower + "][rightPower:" + rightPower + "]");

        SmartDashboard.putString("leftPower", ""+leftPower);
        SmartDashboard.putString("rightPower", ""+rightPower);

        leftMC.set(leftPower);
        rightMC.set(rightPower);

    }


    public void stop()
    {
        leftMC.set(0);
        rightMC.set(0);

        LOG.debug("Stop");

    }

    /**
     *
     * @param power
     */
    public void followGyro(double power)
    {
        // ToDo: fill in this method
    }

    /**
     * Retrieve the gyro angle reading.  Defaults to 0 if there is no gyro installed.
     *
     * @return a double representing the gyro angle (or 0 if there is no gyro installed).
     */
    private double getGyroAngle()
    {
        double gyroAngle = 0;

        // we can ony get a reading from the gyro if we have one installed in the robot
        if (gyro != null)
        {
            gyroAngle = gyro.getAngle();
        }

        Robot.displayValue("gyroAngle", ""+gyroAngle);

        return gyroAngle;
    }

}
