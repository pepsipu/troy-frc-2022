package frc.robot.controllers;


//tbs tango two controller

public class RemoteController {

    public JoystickPlus joystick;

    public RemoteController(JoystickPlus joystick) {
        this.joystick = joystick;
    } 

    public double getHorizontalMovement() {
        double x = joystick.getX();
        return x;
    }

    public double getLateralMovement() {
        double y = -joystick.getY();
        return y;
    }

    public double getRotation() {
        double t = joystick.getZ();
        return t;
    }

    public double getXRotation() {
        double xr = joystick.getXRotate();
        return xr;
    }

    public double getYRotation() {
        double yr = joystick.getYRotate();
        return yr;
    }

    public double getZRotation() {
        double zr = joystick.getZRotate();
        return zr;
    }

    public double getSlider() {
        double s = joystick.getSlider();
        return s;
    }

    public boolean rightShoulderPressed() {
        return joystick.getSlider() > 0;
    }

    public boolean leftShoulderPressed() {
        return joystick.getYRotate() > 0;
    }












    /*
     public boolean getTriggerPressed()
    {
        boolean x = joystick.getAiming();
        return x;
    } 

    public double getHorizontalMovement() {
        double x = joystick.getX();
        return Math.abs(x) >= deadzone ? k * Math.signum(x) * (Math.log(Math.abs(x) + 1 - deadzone) + c) : 0;
    }

    public double getLateralMovement() {
        double y = -joystick.getY();
        return Math.abs(y) >= deadzone ? k * Math.signum(y) * (Math.log(Math.abs(y) + 1 - deadzone) + c) : 0;
    }

    public double getRotation() {
        double t = joystick.getZ();
        return Math.abs(t) >= deadzoneT ? kT * Math.signum(t) * (Math.log(Math.abs(t) + 1 - deadzoneT) + cT) : 0;
    }

    

    public boolean triggerPressed() {
        return joystick.getTrigger();
    }

    public double twist() {
        double h = joystick.getTwist();
        return h;
    }

    public double throttle() {
        return joystick.getThrottle();
        
    }

    public boolean top() {
        return joystick.getTop();
    }

    //go into joystick directly and great new method
    public boolean isInputed() {
        boolean x= false;
        return x;
    }

    public boolean backButtonPressed() {
        return joystick.getRawButtonPressed(1);
    }
    */
}