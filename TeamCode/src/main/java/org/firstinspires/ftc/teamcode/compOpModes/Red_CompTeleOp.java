package org.firstinspires.ftc.teamcode.compOpModes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.FlowerIntake;
import org.firstinspires.ftc.teamcode.mechanisms.FlowerPlower;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.PositionalAim;
import org.firstinspires.ftc.teamcode.mechanisms.ShooterPIDFTesting;
import org.firstinspires.ftc.teamcode.mechanisms.Turret;

@TeleOp
public class Red_CompTeleOp extends OpMode {
    boolean turretManual = true;
    MecanumDrive drive = new MecanumDrive();
    Intake intake = new Intake();
    FlowerPlower plow = new FlowerPlower();
    FlowerIntake flowerIntake = new FlowerIntake();
    Turret turret = new Turret();
    PositionalAim autoAim = new PositionalAim();
    ShooterPIDFTesting shooter = new ShooterPIDFTesting();
    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        plow.init(hardwareMap);
        //ShooterPIDFTesting.init(this, true);
        turret.init(hardwareMap);
        autoAim.init(hardwareMap, this, true, true, null, turret);
        autoAim.aimEnabled = false;
        autoAim.launchEnabled = false;
    }

    @Override
    public void loop() {
        autoAim.loop();
        turret.loop();
        drive.driveFieldRelative (-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        if (gamepad1.right_trigger !=0 ) {
            intake.startBothIntake();
        } else if (gamepad1.left_trigger !=0) {
            intake.reverseBothIntake();
        } else {
            intake.stopIntake();
        }
        if (gamepad2.right_trigger + gamepad2.left_trigger > 0) {
            if (gamepad2.right_trigger != 0) {
                autoAim.launchEnabled = true;
            }
            if (gamepad2.left_trigger != 0) {
                turret.setLauncherVelocity(-500);
            }
        } else {
            autoAim.launchEnabled = false;
            turret.stopShooter();
        }
        if (gamepad2.xWasPressed()) {
            intake.gateOpen();
        }
        if (gamepad2.aWasPressed()) {
            intake.gateClosed();
        }
        if (gamepad1.xWasPressed()) {
            plow.plowUp();
        }
        if (gamepad1.aWasPressed()) {
            plow.plowDown();
        }
        if (gamepad1.bWasPressed()) {
            plow.plowDown();
        }
        if (turretManual = true) {
            autoAim.aimEnabled = false;
            turret.setTurretRotate(-gamepad2.left_stick_x);
        } else {
            autoAim.aimEnabled = true;
        }
        telemetry.addData("Distance From Goal:", autoAim.rangeToGoal);
        telemetry.addData("Shooter Set Velocity:", turret.velocity);
        telemetry.addData("Shooter Current Velocity:", turret.shooter1.getVelocity());
        telemetry.addData("Turret Angel:", turret.totalTurretDegrees);
    }
}
