package frc.robot.commands.BallHandling.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallHandling.Shoot.Shooter;

public class SetShooterPowerManual extends CommandBase { // Manual setting of shooter power. Conflicts with the current
                                                         // decision to have shooter hard-set to 0.85 power for the
                                                         // entire match.
  /**
   * Creates a new AutonomousCommand.
   */
  private final Shooter shoot;

  private double maxRPM = 5000;
  private double minRPM = 0;
  private double shooterRPM = minRPM;

  public SetShooterPowerManual(Shooter shoot) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shoot = shoot;
    addRequirements(shoot);

  }

  public void setShooterRPM() {
    double sliderValue = RobotContainer.secondaryJoystick.joystick.getRawAxis(2); // axis channel for slider
    shooterRPM = minRPM + sliderValue * (maxRPM - minRPM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    setShooterRPM();
    shoot.setRPMValue(shooterRPM); // pass RPM value to shooter subsystem
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
