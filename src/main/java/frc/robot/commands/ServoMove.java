// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Servos;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ServoMove extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Servos servos;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ServoMove(Servos subsystem) {
    servos = subsystem;
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      

      double hor = RobotContainer.driverStick.getHorizontalMovement();
      double lat = RobotContainer.driverStick.getLateralMovement();
      double rot = RobotContainer.driverStick.getRotation();
      boolean trigger = RobotContainer.driverStick.triggerPressed();
      double twist = RobotContainer.driverStick.twist();
      double throttle = RobotContainer.driverStick.throttle();
      boolean isInputed = RobotContainer.driverStick.isInputed();
      boolean topPressed = RobotContainer.driverStick.top();

      RobotContainer.driverStick.setChannelForStickButton();
      // drive_train.drive(lat, hor);
      // servos.servo1.setAngle(- (hor * 180));
     servos.servo1.setAngle(- (lat * 180));
     if (RobotContainer.driverStick.getRotation()>0)
     {
      System.out.println("l");
     }
     else
    {
      System.out.println("2");
    }
    

      //servos.servo1.setAngle(hor - 0.5);
      //System.out.println("Lat: " + lat + " Hor: "+ hor);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
