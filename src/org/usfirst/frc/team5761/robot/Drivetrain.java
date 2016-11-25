package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Drivetrain {

    static final Logger LOG = LoggerFactory.getLogger(Drivetrain.class);

    static final double brakePower = 0.1;

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

        try {
            gyro = new ADXRS450_Gyro();
            Robot.displayValue("Gyro Installed", "yes");

        } catch (Exception e) {
            LOG.error("Gyro not installed correctly", e);
            Robot.displayValue("Gyro Installed", "no");
        }

        resetGyro();


    }

    public void resetGyro() {
        // attempt to create the gyro
        if (gyro != null) {
            try {
                LOG.info("resetGyro: reset");
                gyro.reset();

                LOG.info("resetGyro: calibrate");
                gyro.calibrate();

                LOG.info("resetGyro: done");

                Robot.displayValue("Gyro Installed", "yes");

            } catch (Exception e) {
                LOG.error("Gyro not installed correctly", e);
                Robot.displayValue("Gyro Installed", "no");
            }
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

    public void brake(){
        if (leftMC.get() > 0){
            leftMC.set(-brakePower);
        }
        else{
            leftMC.set(brakePower);
        }
        if (rightMC.get() > 0){
            rightMC.set(-brakePower);
        }
        else
        {
            rightMC.set(brakePower);
        }
    }
    public void hardBrake(){
        if (leftMC.get() > 0){
            leftMC.set(-0.2);
        }
        else{
            leftMC.set(0.2);
        }
        if (rightMC.get() > 0){
            rightMC.set(-0.2);
        }
        else
        {
            rightMC.set(0.2);
        }
    }


    /**
     *
     * @param power
     */
    public void followGyro(double power, double gyroTarget)
    {
        // ToDo: fill in this method
        //proportionally drives in the direction of a gyro heading, turning to face the right direction
        double currentGyroAngle = gyro.getAngle() % 360;
        double gyroPowerAdjustment = 0;
        double gyroGain = 0.05;


        //Calculates how much to turn based on the current heading and the target heading
        gyroPowerAdjustment = currentGyroAngle - gyroTarget;
        gyroPowerAdjustment = gyroPowerAdjustment * gyroGain;

        double gyroMotorPowerLeft = -power - gyroPowerAdjustment;
        double gyroMotorPowerRight = power - gyroPowerAdjustment;

        //Makes the motors move
        leftMC.set(gyroMotorPowerLeft);
        rightMC.set(gyroMotorPowerRight);

        displayValues();
    }

    /**
     * Retrieve the gyro angle reading.  Defaults to 0 if there is no gyro installed.
     *
     * @return a double representing the gyro angle (or 0 if there is no gyro installed).
     */
    public double getGyroAngle()
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

    public void displayValues()
    {
        Robot.displayValue("Gyro Angle: " , getGyroAngle()+"");
        Robot.displayValue("Left Motor: " , leftMC.get()+"");
        Robot.displayValue("Right Motor: " , rightMC.get()+"");
    }


}
