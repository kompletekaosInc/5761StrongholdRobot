package org.usfirst.frc.team8761.robot;

/**
 * Created by jiahpang on 20/06/2016.
 */
public class DriverStation {

    private DriveControl driveControl;

    /**
     * Create a new instance of the DriverStation.
     */
    public DriverStation()
    {
        // when we get new DriveControl implementations, we can implement a selector via the SmartDasboard
        driveControl = new LogitechJoystick();
        //driveControl = new xBoxCobtrol();
    }

    //Autonomous

    //TeleOp
    public void control( Robot robot )
    {
        driveControl.giveCommands(robot);
    }


}
