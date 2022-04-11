// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PreMatch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallHandling.IndexAndIngest.BottomIndexer;

public class FixServo extends CommandBase { // Only used in pre-match, to reset the servo holding the ingester.
  private final BottomIndexer ingest;
  public boolean isTopHit = false;
  
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public FixServo(BottomIndexer subsystem) {
    ingest = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ingest.setServo(-1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // ingest.setIndexSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
