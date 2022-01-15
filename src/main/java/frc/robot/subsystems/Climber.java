// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

    //feeding system

  private final Talon mArmTalon;
  private final Talon mHookTalon;
  private double currentDisplacement = 0; // start at the bottom
  private double HookTalonSpeed; // change when you figure out speed per second when Talon.set(1)
  private double lengthOfHookTalon; // change to length of hook talon in same units as HookTalonSpeed


  /** Creates a new ExampleSubsystem. */
  public Climber() {
    mArmTalon = new Talon(8);
    mHookTalon = new Talon(7);
  }


  private void setArmHeight(double height) {

  }

  

  private void changeHookDisplacement(double increment) {
    double percentOfTalonSpeed = increment / HookTalonSpeed;

    double newDisplacement = currentDisplacement + increment;

    if(newDisplacement < 0) {
      setHookDisplacement(0);
    } else if(newDisplacement > lengthOfHookTalon) {
      setHookDisplacement(lengthOfHookTalon);
    } else {
      if(percentOfTalonSpeed < 0) {
        mHookTalon.setExpiration(-percentOfTalonSpeed);
        mHookTalon.set(-1);
      } else {
        mHookTalon.setExpiration(percentOfTalonSpeed);
        mHookTalon.set(1);
      }

      currentDisplacement += increment;
    }
  }

  private void setHookDisplacement(double displacement) {
    changeHookDisplacement(currentDisplacement - displacement);
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
