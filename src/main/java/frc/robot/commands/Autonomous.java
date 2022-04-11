package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.BottomIndexer;
import frc.robot.subsystems.ClimberArm;
import frc.robot.subsystems.ClimberHooks;
import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.Tachometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tachometer;
import frc.robot.subsystems.TopIndexer;

public class Autonomous extends CommandBase {
  /**
   * Creates a new AutonomousCommand.
   */
  private final DriveTrain drive;
  private final ClimberHooks climber;
  private final ClimberArm arm;
  private final Shooter shooter;
  private final BottomIndexer bottomIndexer;
  private final TopIndexer topIndexer;
  // private final Tachometer tacheo;
  private final Timer timer = new Timer();

  private final double SHOOTER_SPEED = 0.2;

  private double desiredRPM = 1000;
  private double indexSpeed = 0.15;
  private double shootIndexSpeed = 0.25;

  private double xSpeed= 0;
  private double ySpeed= 0; 
  private double zRotation=0;
  private final double MAX_POSITION = 20; //measured in motor rotations, measure later
  private AutonStages stage = AutonStages.MOVE_TO_POS;


  public Autonomous(DriveTrain drive, ClimberHooks climber, ClimberArm arm, Shooter shooter, BottomIndexer bottomIndexer, TopIndexer topIndexer) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.drive = drive;
    this.climber = climber;
    this.arm = arm;
    this.shooter = shooter;
    this.bottomIndexer = bottomIndexer;
    this.topIndexer = topIndexer;
    timer.start();
    // this.tacheo = tacheo;
    addRequirements(drive, climber, arm, shooter, bottomIndexer, topIndexer);
  }

  private enum AutonStages {
      CLIMBER_ARM_30_AND_INGEST,
      MOVE_TO_POS,
      TURN,
      ACCELERATE_FLYWHEEL,
      SHOOT_FIRST_BALL,
      ACCELERATE_FLYWHEEL_2,
      SHOOT_SECOND_BALL,
      FINISH
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ySpeed = 0.5;
    xSpeed = 0;
    drive.resetFrontLeftEncoderPosition();
    drive.resetGyroAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("auton");
      if(RobotContainer.inTeleop) {
        System.out.println("in teleop");
          // cancel();
      } else {
      switch(stage) {
          case CLIMBER_ARM_30_AND_INGEST:
            bottomIndexer.releaseServo();
            
            if(!arm.angleLimitPressed()) {
                arm.changeArmAngle(-0.5);
            } else {
                arm.changeArmAngle(0);
                //System.out.println("move to pos");
                stage = AutonStages.MOVE_TO_POS;
            }
            break;
          case MOVE_TO_POS:
            if(!shooter.getBottomShooterLim()) {
              bottomIndexer.setIndexSpeed(-0.4);
            } else {
              bottomIndexer.setIndexSpeed(0);
            }
            //topIndexer.setIndexSpeed(0.2);
            if (drive.getFrontLeftEncoderPosition() <= MAX_POSITION) {
                //xSpeed += drive.getAdjustment()[0];
                //ySpeed += drive.getAdjustment()[1];
                //System.out.println("im mooving");
                drive.driveRR(-0.3, 0, 0);
            } else {
              drive.drive(0, 0, 0);
              //System.out.println("shoot 1");
              timer.reset();
              // topIndexer.setIndexSpeed(0.8);
              stage = AutonStages.TURN;
            }

            break;
          case TURN:
            //System.out.println("turn " + timer.get());
            if(timer.hasElapsed(2)){
              drive.drive(0,0,drive.setAngle(190));
              if(timer.hasElapsed(5)){
                topIndexer.setIndexSpeed(0.8);
                bottomIndexer.setIndexSpeed(0);
                drive.drive(0,0,0);
                timer.reset();
                stage = AutonStages.ACCELERATE_FLYWHEEL;
              }
            }
          break;

          case ACCELERATE_FLYWHEEL:
            //drive.drive(0, 0, 0);
            if(timer.hasElapsed(3)){
              bottomIndexer.setIndexSpeed(0);
              topIndexer.setIndexSpeed(0);
              stage = AutonStages.FINISH;
            }
            else if(timer.hasElapsed(2)){
              bottomIndexer.setIndexSpeed(-0.8);
              //topIndexer.setIndexSpeed(0);
              
            } else {
              bottomIndexer.setIndexSpeed(0);
            }
            break;
  
          
          case FINISH:
          drive.drive(0, 0, 0);
            cancel();
            break;
          default:
          drive.drive(0, 0, 0);
            break;

      }
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.drive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}