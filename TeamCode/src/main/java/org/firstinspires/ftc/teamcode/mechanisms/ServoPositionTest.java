
package org.firstinspires.ftc.teamcode.mechanisms;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class ServoPositionTest {
    CRServo serbitor;
    AnalogInput input;
    private double voltage = 0;
    public void init(HardwareMap hwMap) {
        serbitor = hwMap.get(CRServo.class, "serbitor");
        input = hwMap.get(AnalogInput.class, "analogInTest");
    }
    public void setPower() {
        serbitor.setPower(1);
    }
    public void getVoltage() {
        voltage = input.getVoltage();
        //Telemetry.addData("Voltage",voltage);
    }
}
