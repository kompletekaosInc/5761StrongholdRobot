package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    static final Logger LOG = LoggerFactory.getLogger(Robot.class);

    private RobotDrive myRobot;
    private Joystick stick;

    private Spark leftMC;
    private Spark rightMC;
    ADXRS450_Gyro gyro = null;

    private int autoLoopCounter;


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

        displayValue("gyroAngle", gyroAngle);

        return gyroAngle;
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        LOG.info("robotInit: BEGIN");

        // load the DriverStation SmartDashboard
        new DriverStationSmartDashboard();  // ToDo: perhaps this should be static


        // attempt to create the gyro
        try {
            gyro = new ADXRS450_Gyro();
            gyro.reset();
            gyro.calibrate();

            displayValue("Gyro Installed", "yes");

        } catch (Exception e) {
            LOG.error("Gyro not installed correctly", e);
            displayValue("Gyro Installed", "no");
        }

        //myRobot = new RobotDrive(0, 1);
        leftMC = new Spark(0);
        rightMC = new Spark(1);
        stick = new Joystick(0);

        LOG.info("robotInit: END");
    }

    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
        autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        if (autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
        {
            //myRobot.drive(-0.5, 0.0);    // drive forwards half speed
            autoLoopCounter++;
        } else {
            //myRobot.drive(0.0, 0.0);    // stop robot
        }
    }

    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit() {

        LOG.info("teleopInit: BEGIN");
        LOG.info("teleopInit: END");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

        // see the current gyro reading
        getGyroAngle();

        //myRobot.arcadeDrive(stick);
        double side = stick.getX();
        double speed = stick.getY();
        double throttle = stick.getThrottle();
        //LOG.debug("teleopPeriodic: raw [side:" + side + "][speed:" + speed + "][throttle:" + throttle + "]");

        throttle = (throttle - 1)/2;
        speed = speed * throttle;
        side = side * throttle;

        double appliedSpeed = speed * throttle;

        //LOG.debug("teleopPeriodic: [side:" + side + "][speed:" + speed + "][throttle:" + throttle + "]");
        displayValue("side", ""+side);
        displayValue("speed", ""+speed);
        displayValue("throttle", ""+throttle);



        double leftMotorPower = -speed + -side ;
        double rightMotorPower = speed + -side;


        drive(leftMotorPower, rightMotorPower);

        //This is a TEST - WILL REMOVE UNLESS THIS IS THE BEST OPTION
       // if (button() )
    }

    /**
     * This method will drive the motors of the robot based on the inputs.
     *
     * @param leftPower
     * @param rightPower
     */
    private void drive(double leftPower, double rightPower)
    {
        LOG.debug("drive:");

        displayValue("leftPower", ""+leftPower);
        displayValue("rightPower", ""+rightPower);

        leftMC.set(leftPower);
        rightMC.set(rightPower);

    }

    /**
     * Set the left and right motor to 0.  Stop the drive.
     */
    private void stop()
    {
        leftMC.set(0);
        rightMC.set(0);

        LOG.debug("Stop");

    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    /**
     * Display the provided name and value.  This could be done by presenting the name/value to the SmartDashboard
     * as well as logging the information.
     *
     * @param name
     * @param value
     */
    private void displayValue( String name, Object value )
    {
        SmartDashboard.putString(name, ""+value);
        LOG.debug("[" + name + ":" + value + "]");
    }

}
