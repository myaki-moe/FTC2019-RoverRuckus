package org.firstinspires.ftc.teamcode.ftcLib.hardware;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class FtcDistanceSensor extends Thread{
    //距离传感器对象，一前一后
    private DistanceSensor dF,dB;
    //运行标志位
    private boolean flag = true;
    //两个距离变量
    public volatile double distanceFront = 0;
    public volatile double distanceBack = 0;

    //构造函数，初始化距离传感器
    public FtcDistanceSensor (HardwareMap hardwareMap){
        //从HardwareMap获得两个距离传感器对象对象
        dF = hardwareMap.get(DistanceSensor.class,"df");
        dB = hardwareMap.get(DistanceSensor.class,"db");
    }

    public void run(){
        //在新线程中不断获取距离
        while (flag){
            distanceFront = dF.getDistance(DistanceUnit.CM);
            distanceBack = dB.getDistance(DistanceUnit.CM);
            //频率 200hz
            sleep(50);
        }
    }

    //停止函数，改变标志位停止线程
    public void shutodwn(){
        flag = false;
    }

    //延时函数
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
