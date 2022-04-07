package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcCrServo;
import org.firstinspires.ftc.teamcode.ftcLib.hardware.FtcMotor;
import org.firstinspires.ftc.teamcode.ftcLib.system.CollectionBox;
import org.firstinspires.ftc.teamcode.ftcLib.system.ForwardSildeway;
import org.firstinspires.ftc.teamcode.ftcLib.system.MecanumWheel;
import org.firstinspires.ftc.teamcode.ftcLib.system.UpperBox;
import org.firstinspires.ftc.teamcode.ftcLib.system.VerticalSlideway;

@TeleOp(name = "TELEOP")

public class Teleop extends OpMode {

    private CollectionBox collectionBox;
    private UpperBox upperBox;
    private ForwardSildeway forwardSildeway;
    private VerticalSlideway verticalSlideway;
    private FtcMotor ftcMotor;
    private MecanumWheel mecanumWheel;

    @Override
    public void init() {
        ftcMotor = new FtcMotor(hardwareMap);
        collectionBox = new CollectionBox(gamepad1, gamepad2, ftcMotor);
        FtcCrServo ftcCrServo = new FtcCrServo(hardwareMap);
        upperBox = new UpperBox(gamepad1, gamepad2, ftcCrServo);
        forwardSildeway = new ForwardSildeway(gamepad1, gamepad2, ftcMotor);
        verticalSlideway = new VerticalSlideway(gamepad1, gamepad2, ftcMotor);
        mecanumWheel = new MecanumWheel(gamepad1,gamepad2,ftcMotor,telemetry);
    }

    @Override
    public void start() {
        collectionBox.start();
        upperBox.start();
        forwardSildeway.start();
        verticalSlideway.start();
    }

    @Override
    public void loop() {
        mecanumWheel.activity();
        telemetry.addData("m1", ftcMotor.getLFEncoderCnt());
        telemetry.addData("m2", ftcMotor.getRFEncoderCnt());
        telemetry.addData("m3", ftcMotor.getRBEncoderCnt());
        telemetry.addData("m4", ftcMotor.getLBEncoderCnt());
    }

    @Override
    public void stop() {
        collectionBox.shutdown();
        upperBox.shutdown();
        forwardSildeway.shutdown();
        verticalSlideway.shutdown();
    }
}
