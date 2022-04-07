package org.firstinspires.ftc.teamcode.automous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@Autonomous(name = "AUTONOMOUS")

public class AutoTest extends OpMode {

    private AutoActivity autoActivity;
    @Override
    public void init() {
        autoActivity = new AutoActivity(hardwareMap);
    }

    @Override
    public void start() {
        autoActivity.start();
    }

    @Override
    public void loop() {
        telemetry.addData("",autoActivity.goldMineralDirection);
    }

}
