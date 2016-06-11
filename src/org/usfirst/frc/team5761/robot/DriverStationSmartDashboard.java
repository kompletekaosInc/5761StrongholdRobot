package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by justin on 9/06/2016.
 *
 * The DriverStation SmartDashboard to allow information from the robot to be displayed to the driver and
 * to allow the driver to send data to the Robot.
 *
 * The {@link SmartDashboard} class is the bridge between robot programs and the
 * SmartDashboard on the laptop.
 *
 * <p>
 * When a value is put into the SmartDashboard here, it pops up on the
 * SmartDashboard on the laptop. Users can put values into and get values from
 * the SmartDashboard
 */
public class DriverStationSmartDashboard {

    static final Logger LOG = LoggerFactory.getLogger(DriverStationSmartDashboard.class);
    //private SmartDashboard dashboard = new SmartDashboard();


    /**
     * Create a SmartDashboard in the driver station and add the default data items to the dashboard.
     */
    public DriverStationSmartDashboard() {
        LOG.info("<constructor> Add default data to SmartDashbaord");

        // add the default standard data items to the dashboard
        SmartDashboard.putString("CurrentMillis", ""+System.currentTimeMillis());

        SendableChooser choice = new SendableChooser();
        choice.addDefault("Log Level", "INFO");
        //choice.addObject("");
    }
}
