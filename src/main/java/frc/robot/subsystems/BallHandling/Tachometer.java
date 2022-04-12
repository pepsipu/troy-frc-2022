// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BallHandling;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class Tachometer extends SubsystemBase { // Get flywheel speed, should hopefully be useless with brushless
                                                // encoders

  // feeding system
  private static Counter tacheo;

  /** Creates a new ExampleSubsystem. */
  public Tachometer() {

    tacheo = new Counter(new DigitalInput(Constants.shooterTachometerPort));

  }

  public static double getShooterRPM() {
    double period = tacheo.getPeriod();
    return (1 / period) * 60;
  }

  public void resetT() {
    tacheo.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}