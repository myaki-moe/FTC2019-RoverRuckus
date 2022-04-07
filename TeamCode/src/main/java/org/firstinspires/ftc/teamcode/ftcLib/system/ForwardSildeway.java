package org.firstinspires.ftc.teamcode.ftcLib.system;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcMotor;

public class ForwardSildeway extends Thread {

    volatile private Gamepad gamepad1, gamepad2;
    volatile private FtcMotor ftcMotor;
    private boolean flag = true;
    private boolean back = false;


    public ForwardSildeway(Gamepad gamepad1, Gamepad gamepad2, FtcMotor ftcMotor) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.ftcMotor = ftcMotor;
    }

    @Override
    public void run() {
        while (flag) {
            activity();
        }
    }

    public void shutdown() {
        flag = false;
    }

    private void activity() {

        if (gamepad1.right_stick_y !=0){
            ftcMotor.setForwardPower(gamepad1.right_stick_y);
        }
        else if (gamepad2.b){
            ftcMotor.setForwardPower(1);
        }
        else{
            ftcMotor.setForwardPower(0);
        }


    }
}
