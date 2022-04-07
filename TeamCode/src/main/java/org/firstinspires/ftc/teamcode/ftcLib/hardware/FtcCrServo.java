package org.firstinspires.ftc.teamcode.ftcLib.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FtcCrServo {

    private CRServo crServo1,crServo2;

    //构造函数，初始化2个393电机
    public FtcCrServo(HardwareMap hardwareMap) {

        //从HardwareMap获得两个393电机对象
        crServo1 = hardwareMap.crservo.get("cs1");
        crServo2 = hardwareMap.crservo.get("cs2");

        //设置旋转方向
        crServo1.setDirection(CRServo.Direction.FORWARD);
        crServo2.setDirection(CRServo.Direction.FORWARD);
    }

    //设置两个393电机的功率
    public void setCrServoPower(double Power) {
        //注意：393的功率最多设置到+-0.9,超过这个值电机会停转，应该是一个BUG
        crServo1.setPower(Power * 0.9);
        crServo2.setPower(Power * 0.9);
    }
}
