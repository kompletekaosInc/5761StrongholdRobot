package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    static final Logger LOG = LoggerFactory.getLogger(Robot.class);



    private Drivetrain drivetrain;
    private CameraServer camera1;
    private DriverStation driverStation;


    // loop counter for measuring autonomous iterations.
    int autoLoopCounter;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        LOG.info("robotInit: BEGIN");

        drivetrain = new Drivetrain();

        try {
            camera1 = CameraServer.getInstance();
            camera1.setQuality(10);
            camera1.startAutomaticCapture("cam0");
        } catch (Exception e) {
            LOG.error("Camera not installed correctly", e);
        }

        driverStation = new DriverStation();

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
       driverStation.control(this);
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
    public static void displayValue( String name, Object value )
    {
        SmartDashboard.putString(name, value.toString());
        LOG.debug("[" + name + ":" + value + "]");
    }



    public void drive(double left, double right) {

        drivetrain.drive(left, right);
    }

    private void autonomousDrive(double currentTime, double ){

    }
}
