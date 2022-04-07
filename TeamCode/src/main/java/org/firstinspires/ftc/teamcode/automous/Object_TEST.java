package org.firstinspires.ftc.teamcode.automous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.ftcLib.system.ObjectDetection;

import java.util.List;

@Autonomous(name = "object")
@Disabled

public class Object_TEST extends OpMode {

    //tenserflow资源
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    //金矿石标签,用于后边的检测物体分类
    private static final String LABEL_GOLD_MINERAL = "金矿石";
    //银矿石标签,用于后边的检测物体分类
    private static final String LABEL_SILVER_MINERAL = "银矿石";

    //VUFORIA授权key,用于启动VUFORIA
    private static final String VUFORIA_KEY = "AR0/zTT/////AAAAmdIL8I+g30YLo3EYuz3yAVA07nKyaJEVgMhWll6Na0ZrAnVTbnCofSVje6SKFXqgLYoReTavM8NfIDaY9qhKBLrqLsgJRkQBEAhigOVa02Tm8L46Z1ARtAVwJCuMsT22966JlshpbxVXra2Vns+juHYRAEh7uI0jguxlmn1NbgEGw4HJJndCLntwnUMGHDuqS2r4PsBUBL+dVrhRfuOXfzG8A77/68LRSXiImK7UEAi2HR5FLTj/QyzPaV1bYbxIqLJcUEuqkZ92jFMGyL1HbxW2SaEjjZsoqd2hTmMum3oO5ZiLD9oX7T11aeCCzLBieV4eKFUnydvgnAIrTZYmLkDfZBHLa1Y1S7P2y8ZS1iCV";
    //tenserflow物品检测对象
    private TFObjectDetector tfod;

    public ObjectDetection.Mineral mineral = ObjectDetection.Mineral.UNKNOWN;

    public int cnt = 5;
    public List<Recognition> updatedRecognitions;

    @Override
    public void init() {
        //设置vuforia参数
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        //设置VUFORIA KEY
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        //选择前置摄像头
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        //实例化vuforia对象
        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);
        //实例化tenserflow物品检测对象
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        //激活TenserFlow物品检测
        if (tfod != null) {
            tfod.activate();
        }
    }

    @Override
    public void loop() {
        if (tfod != null) {
            updatedRecognitions = tfod.getRecognitions();
            if (updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        mineral = ObjectDetection.Mineral.GOLD;
                    }
                    if (recognition.getLabel().equals(LABEL_SILVER_MINERAL)) {
                        mineral = ObjectDetection.Mineral.SILVER;
                    }
                }
            }
            else {
                cnt = 0;
            }
        }
        telemetry.addData("cnt",cnt);
        telemetry.addData("",mineral);
    }

    @Override
    public void stop() {
        if (tfod != null) {
            tfod.shutdown();
        }
    }

    public enum Mineral {
        GOLD, SILVER, UNKNOWN
    }

}
