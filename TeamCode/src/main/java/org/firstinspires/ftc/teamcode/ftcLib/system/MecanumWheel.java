package org.firstinspires.ftc.teamcode.ftcLib.system;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcMotor;

public class MecanumWheel {

    private FtcMotor ftcMotor;
    volatile private Gamepad gamepad1, gamepad2;
    private double power;
    private Direction direction;
    private Direction lastDirection;
    private double speed1, speed2, speed3, speed4 = 0;
    private Telemetry telemetry;

    public enum Direction {
        FRONT, BACK, LEFT, RIGHT, TURNLEFT, TURNRIGHT, MIDDLE
    }


    public MecanumWheel(Gamepad gamepad1, Gamepad gamepad2, FtcMotor ftcMotor, Telemetry telemetry) {
        //初始化麦克纳姆轮驱动
        this.ftcMotor = ftcMotor;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.telemetry = telemetry;
    }

    public void activity() {

        getGamePadPowerAndDirection();

        switch (direction) {
            case MIDDLE:
                getMotorPower(0, 0, 0);
                break;
            case TURNLEFT:
                getMotorPower(0, 0, power);
                break;
            case TURNRIGHT:
                getMotorPower(0, 0, -power);
                break;
            case FRONT:
                getMotorPower(0, power, 0);
                break;
            case BACK:
                getMotorPower(0, -power, 0);
                break;
            case LEFT:
                getMotorPower(-power, 0, 0);
                break;
            case RIGHT:
                getMotorPower(power, 0, 0);
                break;
        }

        if (direction != lastDirection) {
            ftcMotor.setMovementMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            ftcMotor.setMovementMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lastDirection = direction;
        }


        if (direction == Direction.RIGHT || direction == Direction.LEFT) {
            getMotorAdjustPower();
        }

        ftcMotor.setMovementPower(speed1, speed2, speed3, speed4);

    }


    private void getGamePadPowerAndDirection() {

        if (gamepad1.left_trigger != 0) {
            power = gamepad1.left_trigger * 0.6;
            direction = Direction.TURNLEFT;
            return;
        }

        if (gamepad1.right_trigger != 0) {
            power = gamepad1.right_trigger * 0.6;
            direction = Direction.TURNRIGHT;
            return;
        }
        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0) {
            power = 0;
            direction = Direction.MIDDLE;
            return;
        }


        if (gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0) {

            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double abs = Math.abs(x) + Math.abs(y);
            if (abs > 1) {
                abs = 1;
            }
            power = abs;

            double degree;  //角度变量
            degree = Math.atan(y / x);    //求反三角函数正切
            degree = Math.toDegrees(degree);
            telemetry.addData("degree", degree);
            if (x > 0 && y > 0) {
                degree = 0 + degree;
            }
            if (x < 0 && y > 0) {
                degree = 180 + degree;
            }
            if (x < 0 && y < 0) {
                degree = 180 + degree;
            }
            if (x > 0 && y < 0) {
                degree = 360 + degree;
            }
            if (x > 0 && y == 0) {
                degree = 0;
            }
            if (x < 0 && y == 0) {
                degree = 180;
            }
            if (x == 0 && y < 0) {
                degree = 270;
            }
            if (x == 0 && y > 0) {
                degree = 90;
            }

            if ((degree >= 0 && degree < 60) || degree > 300) {
                direction = MecanumWheel.Direction.RIGHT;
            }
            if (degree >= 60 && degree <= 120) {
                direction = MecanumWheel.Direction.FRONT;
            }
            if (degree > 120 && degree < 240) {
                direction = MecanumWheel.Direction.LEFT;
            }
            if (degree >= 240 && degree <= 300) {
                direction = MecanumWheel.Direction.BACK;
            }
        }
    }


    private void getMotorPower(double xSpeed, double ySpeed, double aSpeed) {
        speed1 = -ySpeed - xSpeed + aSpeed;
        speed2 = -ySpeed + xSpeed - aSpeed;
        speed3 = -ySpeed - xSpeed - aSpeed;
        speed4 = -ySpeed + xSpeed + aSpeed;
        //得到四个车轮速度中的最大速度
        double max = Math.abs(speed1);
        if (max < Math.abs(speed2)) {
            max = Math.abs(speed2);
        }
        if (max < Math.abs(speed3)) {
            max = Math.abs(speed3);
        }
        if (max < Math.abs(speed4)) {
            max = Math.abs(speed4);
        }
        //如果最大速度大于限定速度就按比例缩小所有车轮速度
        if (max > 1) {
            speed1 = speed1 / max;
            speed2 = speed2 / max;
            speed3 = speed3 / max;
            speed4 = speed4 / max;
        }

    }

    private void getMotorAdjustPower() {
        int i1, i2, i3, i4;
        i1 = Math.abs(ftcMotor.getLFEncoderCnt());
        i2 = Math.abs(ftcMotor.getRFEncoderCnt());
        i3 = Math.abs(ftcMotor.getRBEncoderCnt());
        i4 = Math.abs(ftcMotor.getLBEncoderCnt());

        int max = i1;
        if (i2 > max) max = i2;
        if (i3 > max) max = i3;
        if (i4 > max) max = i4;

        double p1 = (max - i1) * 0.001;
        double p2 = (max - i2) * 0.001;
        double p3 = (max - i3) * 0.001;
        double p4 = (max - i4) * 0.001;
        switch (direction) {
            case LEFT:
                speed1 += p1;
                speed2 -= p2;
                speed3 += p3;
                speed4 -= p4;
                break;
            case RIGHT:
                speed1 -= p1;
                speed2 += p2;
                speed3 -= p3;
                speed4 += p4;
                break;
        }
        double max1 = Math.abs(speed1);
        if (max1 < Math.abs(speed2)) {
            max1 = Math.abs(speed2);
        }
        if (max1 < Math.abs(speed3)) {
            max1 = Math.abs(speed3);
        }
        if (max1 < Math.abs(speed4)) {
            max1 = Math.abs(speed4);
        }
        //如果最大速度大于限定速度就按比例缩小所有车轮速度
        if (max1 > 1) {
            speed1 = speed1 / max1;
            speed2 = speed2 / max1;
            speed3 = speed3 / max1;
            speed4 = speed4 / max1;
        }
    }

}
