// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drive.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ManualDrive extends CommandBase { // Manual driving
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain drive_train;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ManualDrive(DriveTrain subsystem) {
    drive_train = subsystem;
    addRequirements(drive_train);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ySpeed = (RobotContainer.primaryJoystick.getLateralMovement());
    double xSpeed = (-RobotContainer.primaryJoystick.getHorizontalMovement());
    double zRotation = (-RobotContainer.primaryJoystick.getRotation());

    // adjust movement of robot towards ball
    if (RobotContainer.primaryJoystick.button2Pressed()) {
      // x and y movement adjustment values
      double angleAdjust = drive_train.getAdjustment();
      zRotation += angleAdjust;
    }

    // set angle
    if (RobotContainer.secondaryJoystick.getLateralMovement() != 0
        || RobotContainer.secondaryJoystick.getHorizontalMovement() != 0) {
      System.out.println("setting angle");
      double y = -RobotContainer.secondaryJoystick.getLateralMovement();
      double x = RobotContainer.secondaryJoystick.getHorizontalMovement();
      double angle = Math.toDegrees(Math.atan2(y, x)); // gets angle of the joystick
      if (y < 0)
        angle += 360; // make sure angle is within 0˚ to 360˚ scale
      if (angle < 90) {
        angle += 270;
      } else {
        angle -= 90;
      }
      zRotation = drive_train.setAngle(angle);
    }

    if (RobotContainer.primaryJoystick.getJoystickPOV() == 0)
      drive_train.drive(0.25, 0, 0);
    else if (RobotContainer.primaryJoystick.getJoystickPOV() == 45)
      drive_train.drive(0.25, -0.25, 0);
    else if (RobotContainer.primaryJoystick.getJoystickPOV() == 90)
      drive_train.drive(0, -0.25, 0);
    else if (RobotContainer.primaryJoystick.getJoystickPOV() == 135)
      drive_train.drive(-0.25, -0.25, 0);
    else if (RobotContainer.primaryJoystick.getJoystickPOV() == 180)
      drive_train.drive(-0.25, 0, 0);
    else if (RobotContainer.primaryJoystick.getJoystickPOV() == 225)
      drive_train.drive(-0.25, 0.25, 0);
    else if (RobotContainer.primaryJoystick.getJoystickPOV() == 270)
      drive_train.drive(0, 0.25, 0);
    else if (RobotContainer.primaryJoystick.getJoystickPOV() == 315)
      drive_train.drive(0.25, 0.25, 0);
    else
      drive_train.drive(ySpeed, xSpeed, zRotation);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive_train.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
