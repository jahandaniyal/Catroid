/**
 *  Catroid: An on-device visual programming system for Android devices
 *  Copyright (C) 2010-2013 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://developer.catrobat.org/license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.content.actions;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

import org.catrobat.catroid.content.bricks.LegoNxtSensorTouchBrick.Sensor;

public class LegoNxtSensorTouchAction extends TemporalAction {

	private static final int NO_DELAY = 0;
	private Sensor sensorEnum;

	@Override
	protected void update(float percent) {
		if (sensorEnum.equals(Sensor.SENSOR_1)) {
			//			LegoNXT.sendBTCMotorMessage(NO_DELAY, Motor.MOTOR_A.ordinal(), 0, 0);
			//			LegoNXT.sendBTCMotorMessage(NO_DELAY, Motor.MOTOR_B.ordinal(), 0, 0);
			//			LegoNXT.sendBTCMotorMessage(NO_DELAY, Motor.MOTOR_C.ordinal(), 0, 0);
		} else if (sensorEnum.equals(Sensor.SENSOR_2)) {
			//			LegoNXT.sendBTCMotorMessage(NO_DELAY, Motor.MOTOR_A.ordinal(), 0, 0);
			//			LegoNXT.sendBTCMotorMessage(NO_DELAY, Motor.MOTOR_C.ordinal(), 0, 0);
		} else {
			//			LegoNXT.sendBTCMotorMessage(NO_DELAY, sensorEnum.ordinal(), 0, 0);
		}
	}

	public void setSensorEnum(Sensor sensorEnum) {
		this.sensorEnum = sensorEnum;
	}

}