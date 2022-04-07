package org.firstinspires.ftc.teamcode.ftcLib.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class FtcServo {
    //两个伺服对象
    private Servo servo1, servo2;

    //构造函数
    public FtcServo(HardwareMap hardwareMap) {
        //映射伺服硬件
        servo1 = hardwareMap.servo.get("phone");
        servo2 = hardwareMap.servo.get("symbol");
        //设置伺服方向
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.FORWARD);

    }

    //设置手机伺服位置
    public void setPhoneServoPos(double position) {
        servo1.setPosition(position);
    }

    //设置吉祥物伺服位置
    public void setSymbolServoPos(double position) {
        servo2.setPosition(position);
    }

}
