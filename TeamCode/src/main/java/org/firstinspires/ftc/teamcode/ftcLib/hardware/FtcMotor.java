package org.firstinspires.ftc.teamcode.ftcLib.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class FtcMotor {
    //8个电机对象
    private DcMotor lf, rf, rb, lb,forward,vertical,rotate,collection;

    //构造函数
    public FtcMotor(HardwareMap hardwareMap) {

        //映射电机硬件
        lf = hardwareMap.dcMotor.get("lf");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        lb = hardwareMap.dcMotor.get("lb");

        forward = hardwareMap.dcMotor.get("forward");
        vertical = hardwareMap.dcMotor.get("vertical");
        rotate = hardwareMap.dcMotor.get("rotate");
        collection = hardwareMap.dcMotor.get("collect");

        //设置模式
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        forward.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collection.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //设置方向
        lf.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.FORWARD);

        forward.setDirection(DcMotor.Direction.FORWARD);
        vertical.setDirection(DcMotor.Direction.FORWARD);
        rotate.setDirection(DcMotor.Direction.FORWARD);
        collection.setDirection(DcMotor.Direction.FORWARD);

        //设置零功率表现
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        forward.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vertical.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        collection.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //设置功率
        lf.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);

        forward.setPower(0);
        vertical.setPower(0);
        rotate.setPower(0);
        collection.setPower(0);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //统一设置底盘电机功率
    public void setMovementPower(double M1Power, double M2Power, double M3Power, double M4Power) {
        lf.setPower(M1Power);
        rf.setPower(M2Power);
        rb.setPower(M3Power);
        lb.setPower(M4Power);
    }

    //设置横向电机功率
    public void setForwardPower(double power){
        forward.setPower(power);
    }
    //设置纵向电机功率
    public void setVerticalPower(double power){
        vertical.setPower(power);
    }
    //设置旋转电机功率
    public void setRotatePower(double power){
        rotate.setPower(power);
    }
    //设置收块电机功率
    public void setCollectionPower(double power){
        collection.setPower(power);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    //设置横向电机编码器
    public void setForwardCnt(int cnt){
        forward.setTargetPosition(cnt);
    }
    //设置纵向电机编码器
    public void setVerticalCnt(int cnt){
        vertical.setTargetPosition(cnt);
    }
    //设置底盘电机编码器
    public void setMovementCnt(int cnt1,int cnt2,int cnt3,int cnt4){
        lf.setTargetPosition(cnt1);
        rf.setTargetPosition(cnt2);
        rb.setTargetPosition(cnt3);
        lb.setTargetPosition(cnt4);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    //设置横向电机模式
    public void setForwardMode(DcMotor.RunMode runMode){
        forward.setMode(runMode);
    }
    //设置纵向电机模式
    public void setVerticalMode(DcMotor.RunMode runMode){
        vertical.setMode(runMode);
    }
    //设置底盘电机模式
    public void setMovementMode(DcMotor.RunMode runMode){
        lf.setMode(runMode);
        rf.setMode(runMode);
        rb.setMode(runMode);
        lb.setMode(runMode);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //获取左前电机编码器数
    public int getLFEncoderCnt(){
        return lf.getCurrentPosition();
    }
    //获取右前电机编码器数
    public int getRFEncoderCnt(){
        return rf.getCurrentPosition();
    }
    //获取右后电机编码器数
    public int getRBEncoderCnt(){
        return rb.getCurrentPosition();
    }
    //获取左后电机编码器数
    public int getLBEncoderCnt(){
        return lb.getCurrentPosition();
    }
    //获取横向电机编码器数
    public int getForwardEncoderCnt(){
        return forward.getCurrentPosition();
    }
    //获取纵向电机编码器数
    public int getVerticalEncoderCnt(){
        return vertical.getCurrentPosition();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

}

