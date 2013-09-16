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
package org.catrobat.catroid.content.bricks;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.actions.ExtendedActions;

import java.util.List;

public class LegoNxtSensorTouchBrick extends BrickBaseType implements OnItemSelectedListener {
	private static final long serialVersionUID = 1L;

	public static enum Sensor {
		SENSOR_1, SENSOR_2, SENSOR_3, SENSOR_4
	}

	private transient Sensor sensorEnum;
	private String sensor;
	private transient AdapterView<?> adapterView;

	protected Object readResolve() {
		if (sensor != null) {
			sensorEnum = Sensor.valueOf(sensor);
		}
		return this;
	}

	public LegoNxtSensorTouchBrick(Sprite sprite, Sensor sensor) {
		this.sprite = sprite;
		this.sensorEnum = sensor;
		this.sensor = sensorEnum.name();
	}

	@Override
	public int getRequiredResources() {
		return BLUETOOTH_LEGO_NXT;
	}

	@Override
	public Brick copyBrickForSprite(Sprite sprite, Script script) {
		LegoNxtSensorTouchBrick copyBrick = (LegoNxtSensorTouchBrick) clone();
		copyBrick.sprite = sprite;
		return copyBrick;
	}

	@Override
	public View getPrototypeView(Context context) {
		View prototypeView = View.inflate(context, R.layout.brick_nxt_sensor_touch, null);
		Spinner legoSpinner = (Spinner) prototypeView.findViewById(R.id.sensor_touch_spinner);
		legoSpinner.setFocusableInTouchMode(false);
		legoSpinner.setFocusable(false);

		ArrayAdapter<CharSequence> sensorAdapter = ArrayAdapter.createFromResource(context, R.array.nxt_sensor_chooser,
				android.R.layout.simple_spinner_item);
		sensorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		legoSpinner.setAdapter(sensorAdapter);
		legoSpinner.setSelection(sensorEnum.ordinal());
		return prototypeView;
	}

	@Override
	public Brick clone() {
		return new LegoNxtSensorTouchBrick(getSprite(), sensorEnum);
	}

	@Override
	public View getView(Context context, int brickId, BaseAdapter baseAdapter) {
		if (animationState) {
			return view;
		}
		if (view == null) {
			alphaValue = 255;
		}
		view = View.inflate(context, R.layout.brick_nxt_sensor_touch, null);
		view = getViewWithAlpha(alphaValue);

		setCheckboxView(R.id.brick_nxt_sensor_touch_checkbox);
		final Brick brickInstance = this;
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checked = isChecked;
				adapter.handleCheck(brickInstance, isChecked);
			}
		});

		ArrayAdapter<CharSequence> sensorAdapter = ArrayAdapter.createFromResource(context, R.array.nxt_sensor_chooser,
				android.R.layout.simple_spinner_item);
		sensorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner sensorSpinner = (Spinner) view.findViewById(R.id.sensor_touch_spinner);
		sensorSpinner.setOnItemSelectedListener(this);

		if (!(checkbox.getVisibility() == View.VISIBLE)) {
			sensorSpinner.setClickable(true);
			sensorSpinner.setEnabled(true);
		} else {
			sensorSpinner.setClickable(false);
			sensorSpinner.setEnabled(false);
		}

		sensorSpinner.setAdapter(sensorAdapter);
		sensorSpinner.setSelection(sensorEnum.ordinal());
		return view;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		sensorEnum = Sensor.values()[position];
		sensor = sensorEnum.name();
		adapterView = parent;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	public View getViewWithAlpha(int alphaValue) {

		if (view != null) {

			View layout = view.findViewById(R.id.brick_nxt_sensor_touch_layout);
			Drawable background = layout.getBackground();
			background.setAlpha(alphaValue);

			TextView textLegoSensorTouchLabel = (TextView) view.findViewById(R.id.ValueTextView);
			textLegoSensorTouchLabel.setTextColor(textLegoSensorTouchLabel.getTextColors().withAlpha(alphaValue));
			Spinner sensorSpinner = (Spinner) view.findViewById(R.id.sensor_touch_spinner);
			ColorStateList color = textLegoSensorTouchLabel.getTextColors().withAlpha(alphaValue);
			sensorSpinner.getBackground().setAlpha(alphaValue);
			if (adapterView != null) {
				((TextView) adapterView.getChildAt(0)).setTextColor(color);
			}

			this.alphaValue = (alphaValue);

		}

		return view;
	}

	@Override
	public List<SequenceAction> addActionToSequence(SequenceAction sequence) {
		sequence.addAction(ExtendedActions.legoNxtSensorTouch(sensorEnum));
		return null;
	}
}
