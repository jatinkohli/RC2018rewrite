package org.usfirst.frc.team1072.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to run the elevator at a velocity using Motion Magic
 */
public class ElevatorMotionMagicCommand extends Command {

	private int targetPos;
	
	/**
	 * Command to run the elevator at a velocity using Motion Magic
	 * 
	 * @param targetPos The Position for the elevator to be moved to, not to be higher than MAX_HEIGHT or lower than MIN_HEIGHT in RobotMap.Elevator
	 */
    public ElevatorMotionMagicCommand(int targetPos) {
    	this.targetPos = targetPos;
        requires(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.getTalon().selectProfileSlot(RobotMap.Elevator.MOTION_MAGIC_SLOT, RobotMap.PRIMARY_PID_LOOP);
    	
    	Robot.elevator.getTalon().configMotionCruiseVelocity(RobotMap.Elevator.MOTION_MAGIC_CRUISE_VEL, RobotMap.TIMEOUT);
    	Robot.elevator.getTalon().configMotionAcceleration(RobotMap.Elevator.MOTION_MAGIC_ACCEL, RobotMap.TIMEOUT);
    }

    protected void execute() {
    	if (RobotMap.Elevator.MIN_HEIGHT < targetPos && targetPos > RobotMap.Elevator.MAX_HEIGHT) 
    	{
        	Robot.elevator.getTalon().set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, RobotMap.Elevator.ARB_FEED_FORWARD);
    	}
    	else
    	{
    		
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}