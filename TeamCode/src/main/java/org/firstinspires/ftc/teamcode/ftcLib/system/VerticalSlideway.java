package org.firstinspires.ftc.teamcode.ftcLib.system;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcMotor;

public class VerticalSlideway extends Thread {

    volatile private Gamepad gamepad1, gamepad2;
    volatile private FtcMotor ftcMotor;
    private boolean flag = true;
    private boolean lock= false;

    public VerticalSlideway(Gamepad gamepad1, Gamepad gamepad2, FtcMotor ftcMotor) {
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
        if(gamepad2.left_stick_button){
            while (gamepad2.left_stick_button);
            if (!lock){
                lock = true;
                ftcMotor.setVerticalMode(DcMotor.RunMode.RUN_TO_POSITION);
                ftcMotor.setVerticalPower(1);
                ftcMotor.setVerticalCnt(ftcMotor.getVerticalEncoderCnt());
            }else {
                lock = false;
                ftcMotor.setVerticalMode(DcMotor.RunMode.RUN_USING_ENCODER);
                ftcMotor.setVerticalPower(0);
            }
        }
        if (lock){
            return;
        }
        ftcMotor.setVerticalPower(-gamepad2.left_stick_y);
    }

}
