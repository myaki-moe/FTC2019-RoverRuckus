package org.firstinspires.ftc.teamcode.ftcLib.system;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcMotor;

public class CollectionBox extends Thread {

    volatile private Gamepad gamepad1, gamepad2;
    volatile private FtcMotor ftcMotor;
    private boolean flag = true;

    public CollectionBox(Gamepad gamepad1, Gamepad gamepad2, FtcMotor ftcMotor) {
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

        ftcMotor.setCollectionPower(gamepad2.right_stick_y);

        if (gamepad1.left_bumper) {
            ftcMotor.setRotatePower(1);
        } else if (gamepad1.right_bumper) {
            ftcMotor.setRotatePower(-1);
        } else if (gamepad2.right_trigger != 0) {
            ftcMotor.setRotatePower(0.1);
        } else if (gamepad2.left_trigger != 0) {
            ftcMotor.setRotatePower(-1);
        } else {
            ftcMotor.setRotatePower(0);
        }
    }
}


