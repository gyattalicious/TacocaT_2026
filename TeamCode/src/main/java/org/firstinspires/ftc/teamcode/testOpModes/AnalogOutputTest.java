package org.firstinspires.ftc.teamcode.testOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp
public class AnalogOutputTest extends OpMode {
    private CRServo servo;
    private AnalogInput analog;
    private double voltage;
    @Override
    public void init() {
        servo = hardwareMap.get(CRServo.class, "servo");
        analog = hardwareMap.get(AnalogInput.class, "analog");
    }

    @Override
    public void loop() {
        if (gamepad1.right_bumper) {
            servo.setPower(1);
        } else if (gamepad1.left_bumper) {
            servo.setPower(-1);
        } else {
            servo.setPower(0);
        }
        voltage = analog.getVoltage();
        telemetry.addData("Analog Voltage", voltage);
        telemetry.update();
    }
}
