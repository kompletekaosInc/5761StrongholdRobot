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

    private AutonomousStrategy1 autoStrategy;

    private Drivetrain drivetrain;
    private Arm arm;
    private CameraServer camera1;
    private DriverStation driverStation;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        LOG.info("robotInit: BEGIN");

        drivetrain = new Drivetrain();
        arm = new Arm();

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

        LOG.info("autonomousInit: BEGIN");
        // create the only strategy we have
        autoStrategy = new AutonomousStrategy1(this);   // ToDo: allow selection of strategies if we have more than one

        displayValues();

        LOG.info("autonomousInit: END");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

        autoStrategy.doAutonomous();

        //displayValues();
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

        displayValues();
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

    public void displayValues()
    {
        drivetrain.displayValues();
        arm.displayValues();
    }


    public void drive(double left, double right) {

        drivetrain.drive(left, right);
    }
    public void stop(){
        drivetrain.stop();
    }

    public void raiseArm(){
        arm.raiseArm();
    }
    public void lowerArm(){
        arm.lowerArm();
    }
    public void stopArm(){
        arm.stopArm();
    }
    public void resetGyro(){
        drivetrain.resetGyro();
    }

    public void driveStraight(double power){
        drivetrain.followGyro(power,0);
    }
    public void driveStraight(double power, double gyroTarget){
        drivetrain.followGyro(power,gyroTarget);
    }

    public void turnGyro(double target){
        drivetrain.gyroTurn(target);
    }
}
