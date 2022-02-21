// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Ingester;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Climb extends CommandBase {
  private final Climber climber;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  private boolean auto;

  public Climb(Climber subsystem) {
    climber = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    auto = false; //turn on when right shoulder button is pressed
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      System.out.println("climbing");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

      // climber.turnOnHookMotor();
      while (!climber.bottomLimitPressed() && auto) {
        climber.slideHook(-1);
        climber.changeArmAngle(-1); 
      } //lift robot off of floor and position arm underneath second pole
      
      climber.slideHook(0); //reset motor
      climber.changeArmAngle(0); //reset motor

      while (!climber.topLimitPressed() && auto) {
        climber.slideHook(1);
        climber.changeArmAngle(1);
      } //position arm directly underneath second pole and slide hooks up to attatch to second pole

      climber.slideHook(0); //reset motor
      climber.changeArmAngle(0);  //reset motor
      
      while (!climber.bottomLimitPressed() && auto) {
        climber.slideHook(-1);
      } //slide robot up forward

      climber.slideHook(0); //reset motor

      while (!climber.bottomLimitPressed() && auto) {
        climber.slideHook(1);
      } //slide hooks back up to the top

    //either manual control or use third limit switch  
      if (climber.topOrBottomLimitPressed() && auto)  
        while (!climber.bottomLimitPressed()) {
          climber.slideHook(1);
          climber.changeArmAngle(-1);
        }   
      else
        while (!climber.bottomLimitPressed()&& auto) {
          climber.slideHook(1);
          climber.changeArmAngle(-1);
        }
      
      climber.slideHook(0); //reset motor
      climber.changeArmAngle(0);  //reset motor
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    double hor = RobotContainer.driverStick.getHorizontalMovement();
      climber.slideHook(0);
      climber.changeArmAngle(0);
      auto = false;
      
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return climber.getHookLimitSwitch();
    return true;
  }
}