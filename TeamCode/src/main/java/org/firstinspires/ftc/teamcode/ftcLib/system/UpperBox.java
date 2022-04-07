package org.firstinspires.ftc.teamcode.ftcLib.system;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcCrServo;

public class UpperBox extends Thread {

    volatile private Gamepad gamepad1, gamepad2;
    volatile private FtcCrServo ftcCrServo;
    private boolean flag = true;


    public UpperBox(Gamepad gamepad1, Gamepad gamepad2, FtcCrServo ftcCrServo) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.ftcCrServo = ftcCrServo;
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

        if (gamepad2.left_bumper) {
            ftcCrServo.setCrServoPower(0.9);
        }
        else if (gamepad2.right_bumper) {
            ftcCrServo.setCrServoPower(-0.9);
        }
        else {
            ftcCrServo.setCrServoPower(0);
        }
    }



}
