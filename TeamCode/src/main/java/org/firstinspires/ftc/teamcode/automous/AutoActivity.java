package org.firstinspires.ftc.teamcode.automous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcMotor;
import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcServo;
import org.firstinspires.ftc.teamcode.ftcLib.system.ObjectDetection;

public class AutoActivity extends Thread {

    private FtcMotor ftcMotor;
    private FtcServo ftcServo;
    public PidController pidController;
    private ObjectDetection objectDetection;
    public String goldMineralDirection = "RIGHT";

    public AutoActivity(HardwareMap hardwareMap) {
        ftcMotor = new FtcMotor(hardwareMap);
        ftcServo = new FtcServo(hardwareMap);

        pidController = new PidController(ftcMotor);
        pidController.start();

        objectDetection = new ObjectDetection(hardwareMap);
        objectDetection.start();

        ftcMotor.setMovementMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ftcMotor.setRotatePower(0.1);
        ftcMotor.setForwardPower(0.05);
        ftcServo.setPhoneServoPos(0.7);
        ftcMotor.setVerticalMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ftcMotor.setVerticalMode(DcMotor.RunMode.RUN_TO_POSITION);
        ftcMotor.setVerticalCnt(0);
        ftcMotor.setVerticalPower(1);

        ftcServo.setSymbolServoPos(1);
    }


    @Override
    public void run() {
        ////////////////////////////////////////////////////////////////////////////////////////////
        //识别
        ObjectDetection.Mineral mineral1 = objectDetection.mineral;
        ftcServo.setPhoneServoPos(0.55);
        sleep(2000);
        ObjectDetection.Mineral mineral2 = objectDetection.mineral;
        ftcServo.setPhoneServoPos(0.45);
        objectDetection.shutdown();
        if (mineral1 == ObjectDetection.Mineral.GOLD && mineral2 == ObjectDetection.Mineral.SILVER) {
            goldMineralDirection = "CENTER";
        }
        else if (mineral1 == ObjectDetection.Mineral.SILVER && mineral2 == ObjectDetection.Mineral.GOLD) {
            goldMineralDirection = "LEFT";
        }
        else{
            goldMineralDirection = "RIGHT";
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        //着陆
        ftcMotor.setVerticalPower(0.6);
        ftcMotor.setVerticalCnt(3500);
        while (ftcMotor.getVerticalEncoderCnt() > 10) ;
        sleep(2000);
        ftcMotor.setMovementMode(DcMotor.RunMode.RUN_TO_POSITION);
        ////////////////////////////////////////////////////////////////////////////////////////////
        //向前一小段距离
        pidController.rstEncoder();
        pidController.setPid(1, 50, PidController.Direction.FRONT);
        while (pidController.running) ;
        ////////////////////////////////////////////////////////////////////////////////////////////
        //向右脱钩
        pidController.rstEncoder();
        pidController.setPid(1, 220, PidController.Direction.RIGHT);
        while (pidController.running) ;
        ////////////////////////////////////////////////////////////////////////////////////////////
        //向前
        pidController.rstEncoder();
        pidController.setPid(1, 500, PidController.Direction.FRONT);
        while (pidController.running) ;

        if(goldMineralDirection == "LEFT"){
            ////////////////////////////////////////////////////////////////////////////////////////////
            //降下滑轨
            ftcMotor.setVerticalCnt(1700);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左转
            pidController.rstEncoder();
            pidController.setPid(1, 470, PidController.Direction.TURNLEFT);
            while (pidController.running) ;
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放吸纳、开吸快
            ftcMotor.setRotatePower(-1);
            sleep(1000);
            ftcMotor.setRotatePower(0);
            ftcMotor.setCollectionPower(-1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //向前
            pidController.rstEncoder();
            pidController.setPid(0.3, 1600, PidController.Direction.FRONT);
            while (pidController.running) ;
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //停收块、抬吸纳
            ftcMotor.setCollectionPower(0);
            ftcMotor.setRotatePower(1);
            sleep(2500);
            ftcMotor.setRotatePower(0.1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //向前
            pidController.rstEncoder();
            pidController.setPid(1, 700, PidController.Direction.FRONT);
            while (pidController.running) ;
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左转
            pidController.rstEncoder();
            pidController.setPid(1, 1050, PidController.Direction.TURNLEFT);
            while (pidController.running) ;
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //后退
            pidController.shutdown();
            ftcMotor.setMovementMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            ftcMotor.setMovementPower(1,1,1,1);
            sleep(500);
            ftcMotor.setMovementPower(0.3,0.3,0.3,0.3);
            sleep(500);
            ftcMotor.setMovementPower(0,0,0,0);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放吉祥物
            ftcServo.setSymbolServoPos(0.3);
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //前进
            ftcMotor.setMovementPower(-1,-1,-1,-1);
            sleep(300);
            ftcMotor.setMovementPower(0,0,0,0);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //右移撞墙
            ftcMotor.setMovementPower(-0.8,0.8,-0.8,0.8);
            sleep(500);
            ftcMotor.setMovementPower(0,0,0,0);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左移脱离
            ftcMotor.setMovementPower(0.3,-0.3,0.3,-0.3);
            sleep(1000);
            ftcMotor.setMovementPower(0,0,0,0);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //前进
            ftcMotor.setMovementPower(-1,-1,-1,-1);
            sleep(1900);
            ftcMotor.setMovementPower(0,0,0,0);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放收纳
            ftcMotor.setRotatePower(-1);
            sleep(1500);
            ftcMotor.setRotatePower(0);

        }
        if(goldMineralDirection == "CENTER"){
            ////////////////////////////////////////////////////////////////////////////////////////////
            //降下滑轨
            ftcMotor.setVerticalCnt(1700);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左转
            pidController.rstEncoder();
            pidController.setPid(1, 80, PidController.Direction.TURNLEFT);
            while (pidController.running) ;
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放吸纳,吸块
            ftcMotor.setRotatePower(-1);
            sleep(1000);
            ftcMotor.setRotatePower(0);
            ftcMotor.setCollectionPower(-1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //向前
            pidController.rstEncoder();
            pidController.setPid(0.3, 1500, PidController.Direction.FRONT);
            while (pidController.running) ;
            ftcMotor.setCollectionPower(0);
            ftcMotor.setRotatePower(1);
            sleep(2000);
            ftcMotor.setRotatePower(0.1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //向前
            pidController.rstEncoder();
            pidController.setPid(1, 1500, PidController.Direction.FRONT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左转
            pidController.rstEncoder();
            pidController.setPid(1, 1200, PidController.Direction.TURNLEFT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放吉祥物
            ftcServo.setSymbolServoPos(0.3);
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //向前
            pidController.rstEncoder();
            pidController.setPid(1, 500, PidController.Direction.FRONT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左转
            pidController.rstEncoder();
            pidController.setPid(1, 160, PidController.Direction.TURNLEFT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //向前
            pidController.rstEncoder();
            pidController.setPid(1, 4300, PidController.Direction.FRONT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放收纳
            ftcMotor.setRotatePower(-1);
            sleep(1500);
            ftcMotor.setRotatePower(0);
            pidController.shutdown();
            ftcMotor.setMovementMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            ftcMotor.setMovementPower(0,0,0,0);
        }

        if(goldMineralDirection == "RIGHT"){
            ////////////////////////////////////////////////////////////////////////////////////////////
            //降下滑轨
            ftcMotor.setVerticalCnt(1500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //右转
            pidController.rstEncoder();
            pidController.setPid(1, 500, PidController.Direction.TURNRIGHT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //翻吸纳，吸块
            ftcMotor.setRotatePower(-1);
            sleep(1000);
            ftcMotor.setRotatePower(0);
            ftcMotor.setCollectionPower(-1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //直行
            pidController.rstEncoder();
            pidController.setPid(0.3, 1500, PidController.Direction.FRONT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //停吸块，翻收纳
            ftcMotor.setCollectionPower(0);
            ftcMotor.setRotatePower(1);
            sleep(2000);
            ftcMotor.setRotatePower(0.1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //直行
            pidController.rstEncoder();
            pidController.setPid(1, 800, PidController.Direction.FRONT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左转
            pidController.rstEncoder();
            pidController.setPid(1, 900, PidController.Direction.TURNLEFT);
            while (pidController.running) ;
            sleep(1000);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //直行
            pidController.rstEncoder();
            pidController.setPid(1, 2300, PidController.Direction.FRONT);
            while (pidController.running) ;
            sleep(1000);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放吉祥物
            ftcServo.setSymbolServoPos(0.3);
            sleep(500);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //左转
            pidController.rstEncoder();
            pidController.setPid(1, 970, PidController.Direction.TURNLEFT);
            while (pidController.running) ;
            sleep(1000);
            ////////////////////////////////////////////////////////////////////////////////////////////
            //直行
            pidController.rstEncoder();
            pidController.setPid(1, 5000, PidController.Direction.FRONT);
            while (pidController.running) ;
            ////////////////////////////////////////////////////////////////////////////////////////////
            //放收纳
            ftcMotor.setRotatePower(-1);
            sleep(1500);
            ftcMotor.setRotatePower(0);
            pidController.shutdown();
            ftcMotor.setMovementMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            ftcMotor.setMovementPower(0,0,0,0);

        }


    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
