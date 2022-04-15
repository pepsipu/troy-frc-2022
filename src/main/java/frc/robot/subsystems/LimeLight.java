package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.math.controller.PIDController;

public class LimeLight extends SubsystemBase{
    private NetworkTableInstance inst;
    private NetworkTable table;
    private PIDController pidcontrol;
    private final float kp = 0.05f;
    private final float ki = 0.01f;
    private final float kd = 0.02f;

    public LimeLight(){
        pidcontrol = new PIDController(1, ki, kd);
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("limelight"); //initiate limelight table
    }
    public double getAdjustment(){
        float tx = table.getEntry("tx").getNumber(0).floatValue() * kp; //get target x position
        double steering_adjust = pidcontrol.calculate(tx); //calculate PID
        return steering_adjust;
    }
    public double getDistance(){
        double ty = table.getEntry("ty").getNumber(0).doubleValue(); //get target height
        double distance = ty*0.2 - 20; //convert target height into distance estimate
        return distance;
    }
    public void turnOnLED(){
        table.getEntry("ledMode").setDouble(3);
    }
    public void turnOffLED(){
        table.getEntry("ledMode").setDouble(1);
    }
    @Override
    public void periodic() {
        
    }
  
    @Override
    public void simulationPeriodic() {
  
    }
    
}