package org.usfirst.frc.team8761.robot;

import edu.wpi.first.wpilibj.Spark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Shooter {

    static final Logger LOG = LoggerFactory.getLogger(Drivetrain.class);

    private Spark flyWheelMC;
    private Spark armMC;



    /**
     * Create a Drivetrain.
     */
    public Shooter() {
        LOG.info("<constructor>");

        //myRobot = new RobotDrive(0, 1);
        flyWheelMC = new Spark(2);
        armMC = new Spark(3);

    }

    public void teleopInit()
    {
        flyWheelMC.set(1);
    }

    public void teleopPeriodic()
    {

    }

}
