// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BallHandling;

import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.math.controller.PIDController;

public class Shooter extends SubsystemBase { // handles shooter flywheel, currently is built for CANSparkMax brushed
                                             // motors which are planned to be replaced by brushless

  private CANSparkMax followerMotor;
  private CANSparkMax leaderMotor;
  private PIDController pidController; // we ditched PID
  private final DigitalInput bottomShooterLim, topShooterLim;
  private double kP, kI, kD, rpmValue, rpmThreshold;
  // private double kIz, kFF, kMaxOutput, kMinOutput;

  public Shooter() {
    bottomShooterLim = new DigitalInput(Constants.shooterBottomLimitPort);
    topShooterLim = new DigitalInput(Constants.shooterShootingLimitPort);

    leaderMotor = new CANSparkMax(Constants.flywheelPort1, MotorType.kBrushed);
    followerMotor = new CANSparkMax(Constants.flywheelPort2, MotorType.kBrushed);
    leaderMotor.setInverted(false);
    followerMotor.follow(leaderMotor, true); // motor follows leader in inverse
    setShooterPower(0.85);
    kP = 400;
    kI = 0.3;
    kD = 0;
    // kIz = 0;
    // kFF = 0.000015;
    // kMaxOutput = 1;
    // kMinOutput = -1;
    rpmValue = 500;
    rpmThreshold = 3000; // threshold away from desired rpm to activate PID control

    pidController = new PIDController(1, kI, kD);
  }

  public void setShooterPower(double speed) {
    leaderMotor.set(speed);
    // System.out.println("set the power to speed");
  }

  public void setShooterToRPM() {
    double difference = (rpmValue - Tachometer.getShooterRPM());
    double adjustValue = 0;
    if (difference > rpmThreshold) {
      adjustValue = 1;
    } else {
      adjustValue = difference / kP + 0.1;
    }

    if (adjustValue > 1) {
      adjustValue = 1;
    }
    if (adjustValue < 0)
      adjustValue = 0;

    // System.out.println(adjustValue);
    setShooterPower(adjustValue);
  }

  public void setRPMValue(double rpm) {
    rpmValue = rpm;
  }

  public double getRPMValue() {
    return rpmValue;
  }

  public boolean getBottomShooterLim() {
    return bottomShooterLim.get();
  }

  public boolean getTopShooterLim() {
    return topShooterLim.get();
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