package org.firstinspires.ftc.teamcode.automous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcMotor;


public class PidController extends Thread {

    public enum Direction {
        FRONT, BACK, LEFT, RIGHT, TURNLEFT, TURNRIGHT,MIDDLE
    }

    private FtcMotor ftcMotor;
    private Direction direction = Direction.MIDDLE;
    private double power = 0;
    private int cnt = 0;
    private boolean flag = true;
    public boolean running = false;

    public PidController(FtcMotor ftcMotor) {
        this.ftcMotor = ftcMotor;
        ftcMotor.setMovementMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ftcMotor.setMovementMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setPid(double setPower, int setCount, Direction setDirection) {
        power = setPower;
        direction = setDirection;
        if (direction == Direction.FRONT) {
            ftcMotor.setMovementCnt(-setCount, -setCount, -setCount, -setCount);
        }
        if (direction == Direction.BACK) {
            ftcMotor.setMovementCnt(setCount, setCount, setCount, setCount);
        }
        if (direction == Direction.LEFT) {
            ftcMotor.setMovementCnt(setCount, -setCount, setCount, -setCount);
        }
        if (direction == Direction.RIGHT) {
            ftcMotor.setMovementCnt(-setCount, setCount, -setCount, setCount);
        }
        if (direction == Direction.TURNLEFT) {
            ftcMotor.setMovementCnt(setCount, -setCount, -setCount, setCount);
        }
        if (direction == Direction.TURNRIGHT) {
            ftcMotor.setMovementCnt(-setCount, setCount, setCount, -setCount);
        }
        cnt = setCount;
        running = true;
    }

    public void rstEncoder() {
        ftcMotor.setMovementMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ftcMotor.setMovementMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void shutdown() {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {

            double speed1 = 0;
            double speed2 = 0;
            double speed3 = 0;
            double speed4 = 0;

            if (direction == Direction.MIDDLE){
                speed1 = 0;
                speed2 = 0;
                speed3 = 0;
                speed4 = 0;
            }

            if (direction == Direction.FRONT) {
                speed1 = -power;
                speed2 = -power;
                speed3 = -power;
                speed4 = -power;
            }
            if (direction == Direction.BACK) {
                speed1 = power;
                speed2 = power;
                speed3 = power;
                speed4 = power;
            }
            if (direction == Direction.LEFT) {
                speed1 = power;
                speed2 = -power;
                speed3 = power;
                speed4 = -power;
            }
            if (direction == Direction.RIGHT) {
                speed1 = -power;
                speed2 = power;
                speed3 = -power;
                speed4 = power;
            }
            if (direction == Direction.TURNLEFT){
                speed1 = power;
                speed2 = -power;
                speed3 = -power;
                speed4 = power;
            }
            if (direction == Direction.TURNRIGHT){
                speed1 = -power;
                speed2 = power;
                speed3 = power;
                speed4 = -power;
            }

            int i1, i2, i3, i4;
            i1 = Math.abs(ftcMotor.getLFEncoderCnt());
            i2 = Math.abs(ftcMotor.getRFEncoderCnt());
            i3 = Math.abs(ftcMotor.getRBEncoderCnt());
            i4 = Math.abs(ftcMotor.getLBEncoderCnt());


            int coder = i1 + i2 + i3 + i4 - cnt * 4;

            if (Math.abs(coder)>60){
                running = true;
            }
            else {
                running = false;
            }

            int max = i1;
            if (i2 > max) max = i2;
            if (i3 > max) max = i3;
            if (i4 > max) max = i4;

            double p1 = (max - i1) * 0.0005;
            double p2 = (max - i2) * 0.0005;
            double p3 = (max - i3) * 0.0005;
            double p4 = (max - i4) * 0.0005;

            switch (direction) {
                case MIDDLE:
                    speed1 = 0;
                    speed2 = 0;
                    speed3 = 0;
                    speed4 = 0;
                case TURNLEFT:
                    speed1 += p1;
                    speed2 -= p2;
                    speed3 -= p3;
                    speed4 += p4;
                    break;
                case TURNRIGHT:
                    speed1 -= p1;
                    speed2 += p2;
                    speed3 += p3;
                    speed4 -= p4;
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
                case FRONT:
                    speed1 -= p1;
                    speed2 -= p2;
                    speed3 -= p3;
                    speed4 -= p4;
                    break;
                case BACK:
                    speed1 += p1;
                    speed2 += p2;
                    speed3 += p3;
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
            ftcMotor.setMovementPower(speed1, speed2, speed3, speed4);
        }
    }
}
