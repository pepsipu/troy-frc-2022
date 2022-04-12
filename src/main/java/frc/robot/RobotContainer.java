// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous.Autonomous;
import frc.robot.commands.BallHandling.BallHandling;
import frc.robot.commands.BallHandling.Aiming.AdjustShooterAim;
import frc.robot.commands.BallHandling.Aiming.ShooterAimer;
import frc.robot.commands.BallHandling.Shooter.SetShooterPower;
import frc.robot.commands.BallHandling.Shooter.SetShooterPowerManual;
import frc.robot.commands.Climber.AutoClimb;
import frc.robot.commands.Climber.ControlArm;
import frc.robot.commands.Climber.ControlHooks;
import frc.robot.commands.Drive.ManualDrive;
import frc.robot.commands.PreMatch.FixServo;
import frc.robot.constants.Constants;
import frc.robot.subsystems.BallHandling.IndexAndIngest.BottomIndexer;
import frc.robot.subsystems.BallHandling.IndexAndIngest.TopIndexer;
import frc.robot.subsystems.BallHandling.Shoot.Shooter;
import frc.robot.subsystems.BallHandling.Shoot.Tachometer;
import frc.robot.subsystems.Climber.ClimberArm;
import frc.robot.subsystems.Climber.ClimberHooks;
import frc.robot.subsystems.Drive.DriveTrain;
import frc.robot.subsystems.Drive.Gyro;
import frc.robot.controllers.*;
import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer { // Initialize all global robot things here like joysticks, init subsystems,
                              // keybindings
  public static boolean inTeleop = true;
  public final static Gyro gyro = new Gyro();
  public final static DriveTrain driveTrain = new DriveTrain();

  public final static BottomIndexer bottomIndexer = new BottomIndexer();

  public final static Shooter shooter = new Shooter();

  public final static Tachometer tacheo = new Tachometer();
  public final static TopIndexer topIndexer = new TopIndexer();

  // public final static AimbotBall aimball = new AimbotBall(driveTrain);

  public static FlightJoystickController primaryJoystick = new FlightJoystickController(new Joystick(0)); // drive, turn
  public static FlightJoystickController secondaryJoystick = new FlightJoystickController(new Joystick(1)); // shoot,
                                                                                                            // set angle
  public static FlightJoystickController tertiaryJoystick = new FlightJoystickController(new Joystick(2)); // climb
                                                                                                           // (mostly)
  // public static Tango2Controller tangoIIController = new Tango2Controller(new
  // Tango2Joystick(2)); //climb

  public final static ClimberHooks hooks = new ClimberHooks();
  public final static ClimberArm arm = new ClimberArm();
  public final static ControlArm controlArm = new ControlArm(arm);
  public final static ControlHooks controlHooks = new ControlHooks(hooks);
  public final static AutoClimb autoClimb = new AutoClimb(hooks, arm, gyro);

  public final static BallHandling shootBalls = new BallHandling(shooter, bottomIndexer, topIndexer);
  public final static AdjustShooterAim adjustShooterAim = new AdjustShooterAim(driveTrain);
  public final static SetShooterPower setShooterPower = new SetShooterPower(shooter, driveTrain);
  public final static SetShooterPowerManual setShooterPowerManual = new SetShooterPowerManual(shooter);
  // public final static ShootBallsManual shootBallsManual = new
  // ShootBallsManual(shooter); <- shooter speed constant.

  // declare new shooter airmer to be ran, for driveTrain
  public final static ShooterAimer adjustAim = new ShooterAimer(driveTrain);

  public final static FixServo fixServoCmd = new FixServo(bottomIndexer);

  public final static Autonomous autonomous = new Autonomous(driveTrain, arm, shooter, bottomIndexer, topIndexer, gyro);
  // public final static AutonomousDriveToBall autonomousDrive = new
  // AutonomousDriveToBall(driveTrain, hooks, arm, shooter);
  // public final static AutonomousShootBall autonomousShoot = new
  // AutonomousShootBall(driveTrain, hooks, arm, shooter);
  public final static ManualDrive driveCommand = new ManualDrive(driveTrain);
  // public final static AimbotBall aimBall = new AimbotBall(driveTrain);

  //
  // public final static SequentialCommandGroup a = new
  // SequentialCommandGroup(flywheelShooter, autonomousCommand);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    CameraServer.startAutomaticCapture();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void configureButtonBindings() {
    /*
     * coDriverStick.btn_1.whileHeld(shootBall);
     * coDriverStick.btn_12.whenPressed(turretPresets);
     */
    // controller might not actually have any buttons, might be useless code

    // JoystickButton collectButton = new JoystickButton(driverStick.joystick, 1);
    // collectButton.whileHeld(autonomousCommand);

    // when press button "1" on frc will run shooterAimer, follow shooterAimer for
    // more info
    // Joystick joystick = new Joystick(0);
    // JoystickButton shooterButton = new JoystickButton(flightJoystick.joystick,
    // 1);
    // shooterButton.whileActiveContinuous(adjustShooter);
    // shooterButton.whenReleased(() -> adjustShooter.cancel());
    /*
     * Trigger manualTrigger = new Trigger(() -> tangoIIController.getSlider() >
     * .5);
     * manualTrigger.whileActiveContinuous(controlHooks);
     * manualTrigger.whileActiveContinuous(controlArm);
     * manualTrigger.whenActive(() -> autoClimb.cancel());
     * 
     * Trigger autoTrigger = new Trigger(() -> tangoIIController.getSlider() <= .5);
     * autoTrigger.whileActiveContinuous(autoClimb);
     * autoTrigger.whenActive(() -> controlHooks.cancel());
     * autoTrigger.whenActive(() -> controlArm.cancel());
     */

    hooks.setDefaultCommand(controlHooks);
    arm.setDefaultCommand(controlArm);

    // Limelight adjustment code
    JoystickButton adjustAimButton = new JoystickButton(secondaryJoystick.joystick, Constants.adjustAimButtonNumber);
    adjustAimButton.whileHeld(adjustShooterAim);

    // JoystickButton setShooterButton = new
    // JoystickButton(secondaryJoystick.joystick, Constants.setShooterButtonNumber);
    // setShooterButton.whileHeld(setShooterPower);

    JoystickButton setShooterManualButton = new JoystickButton(secondaryJoystick.joystick,
        Constants.setShooterManualButtonNumber);
    setShooterManualButton.whileHeld(setShooterPowerManual);

    // JoystickButton indexBottomButton = new
    // JoystickButton(primaryJoystick.joystick, Constants.bottomIndexButtonNumber);
    // indexBottomButton.whileHeld(indexBottom);

    // JoystickButton indexTopButton = new JoystickButton(primaryJoystick.joystick,
    // Constants.topIndexButtonNumber);
    // indexTopButton.whileHeld(indexTop);

    // JoystickButton shootBallsButton = new
    // JoystickButton(secondaryJoystick.joystick, Constants.shootBallsButtonNumber);
    // // <-- was unused, previously commented out line below to remove conflicting
    // button bindings. can likely be removed due to refactor of ballHandling
    // shootBallsButton.whenHeld(shootBallsManual);

    JoystickButton fixServo = new JoystickButton(tertiaryJoystick.joystick, 8);
    fixServo.whenHeld(fixServoCmd);

    // JoystickButton aimbotButton = new JoystickButton(flightJoystick.joystick,
    // Constants.aimbotButtonNumber);
    // aimbotButton.whenHeld(aimBall);
    /**
     * Get the slider position of the HID.
     *
     * @return the z position
     */

    // dR.whenPressed(servoMove);
    // JoystickButton manualButton = new JoystickButton(driverStick.joystick, 2);
    // manualButton.whenHeld(autoClimb);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  /*
   * inTeleop = false;
   * Timer t = new Timer();
   * t.start();
   * indexer.setIndexSpeed(.3);
   * ingester.setIngestRollerSpeed(.5);
   * 
   * // autonomousCommand.schedule();
   * // while (!timer.hasElapsed(2)) {}
   * // shooter.setShooterSpeed(.36); // do change lol .3 old
   * while (t.get() <3) {
   * // ingestPos.changeIngestAngle(-0.5);
   * driveTrain.drive(.6 * (t.get() / 2.5), 0);
   * 
   * }
   * driveTrain.drive(0, 0);
   * indexer.setIndexSpeed(0);
   * ingester.setIngestRollerSpeed(0);
   * // ingestPos.changeIngestAngle(0);
   */
  public void autonomousInit() {
    inTeleop = false;
    autonomous.schedule();
    // autonomousCommand.schedule();
  }

  public void teleopInit() {
    inTeleop = true;
    autonomous.cancel();
    configureButtonBindings();
    shooter.setDefaultCommand(shootBalls);
    driveTrain.setDefaultCommand(driveCommand);

  }
}
