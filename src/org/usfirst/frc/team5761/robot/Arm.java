package org.usfirst.frc.team5761.robot;

import edu.wpi.first.wpilibj.Spark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiah on 3/07/2016.
 */
public class Arm {

    static final Logger LOG = LoggerFactory.getLogger(Arm.class);

    private Spark armMC = null;

    public Arm() {
        LOG.info("<constructor>");

        armMC = new Spark(9);


    }



    public void raiseArm(){

        LOG.info("raiseArm");

        armMC.set(1);
    }

    public void lowerArm(){

        LOG.info("lowerArm");
        armMC.set(-1);

    }
    public void stopArm(){
        armMC.stopMotor();
    }

    public void displayValues()
    {
        Robot.displayValue("Arm Motor: " , armMC.getRaw()+"");
    }
}
