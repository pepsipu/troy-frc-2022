// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BallHandling.IndexAndIngest;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class BottomIndexer extends SubsystemBase { // Handles bottom indexer, which is connected to the ingester

  // feeding system
  private final VictorSPX bottomIndexer;
  private final Servo servo;

  // pushing ball backwards in limit switch is activated, greenwheel at bottom
  // spins when ingesting

  /** Creates a new ExampleSubsystem. */
  public BottomIndexer() {
    bottomIndexer = new VictorSPX(Constants.bottomIndexerPort);
    servo = new Servo(Constants.ingesterServo);
  }

  public void setIndexSpeed(double speed) {
    bottomIndexer.set(ControlMode.PercentOutput, speed);
  }

  public void releaseServo() {
    servo.set(1);
  }

  public void setServo(double pos) {
    servo.set(pos);
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
