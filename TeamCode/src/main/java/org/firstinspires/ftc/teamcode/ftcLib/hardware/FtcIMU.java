package org.firstinspires.ftc.teamcode.ftcLib.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class FtcIMU {
    //imu对象
    private BNO055IMU imu;

    //构造函数，初始化imu
    public FtcIMU(HardwareMap hardwareMap) {
        //实例化一个BNO055IMU参数对象
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        //角度单位：度
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        //加速度单位：米每二次方秒
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //校准文件
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        //不记录
        parameters.loggingEnabled = true;
        //记录标签
        parameters.loggingTag = "IMU";
        //记录加速度积分
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        //从HardwareMap获得imu对象
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        //用参数初始化imu
        imu.initialize(parameters);

    }

    public double getAngle() {
        //获得Z轴旋转角度
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }


}
