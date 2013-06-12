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

import java.util.ArrayList;
import java.util.List;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.UserScript;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class UserScriptDefinitionBrick extends ScriptBrick {
	protected UserScript userScript;
	private static final long serialVersionUID = 1L;

	public UserScriptDefinitionBrick(Sprite sprite, UserScript userScript) {
		this.userScript = userScript;
		this.sprite = sprite;
	}

	public UserScriptDefinitionBrick() {

	}

	@Override
	public int getRequiredResources() {
		return NO_RESOURCES;
	}

	@Override
	public Brick copyBrickForSprite(Sprite sprite, Script script) {
		UserScriptDefinitionBrick copyBrick = (UserScriptDefinitionBrick) clone();
		copyBrick.sprite = sprite;
		copyBrick.userScript = (UserScript) script;
		return copyBrick;
	}

	@Override
	public View getView(final Context context, int brickId, final BaseAdapter baseAdapter) {
		if (animationState) {
			return view;
		}

		view = View.inflate(context, R.layout.brick_user, null);

		setCheckboxView(R.id.brick_user_checkbox);

		//method moved to to DragAndDropListView since it is not working on 2.x
		/*
		 * checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		 * 
		 * @Override
		 * public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		 * 
		 * checked = isChecked;
		 * if (!checked) {
		 * for (Brick currentBrick : adapter.getCheckedBricks()) {
		 * currentBrick.setCheckedBoolean(false);
		 * }
		 * }
		 * adapter.handleCheck(brickInstance, checked);
		 * }
		 * });
		 */

		// inactive until spinner has more than one element
		//		final Spinner spinner = (Spinner) view.findViewById(R.id.brick_when_spinner);
		//		spinner.setFocusable(false);
		//		spinner.setClickable(true);
		//		ArrayAdapter<CharSequence> spinnerAdapter = new ArrayAdapter<CharSequence>(context,
		//				android.R.layout.simple_spinner_item);
		//		spinnerAdapter.add(context.getString(R.string.action_tapped));

		//		TODO: not working with OpenGL yet, uncomment this when it does
		//		spinnerAdapter.add(context.getString(R.string.action_doubleTapped));
		//		spinnerAdapter.add(context.getString(R.string.action_longPressed));
		//		spinnerAdapter.add(context.getString(R.string.action_swipeUp));
		//		spinnerAdapter.add(context.getString(R.string.action_swipeDown));
		//		spinnerAdapter.add(context.getString(R.string.action_swipeLeft));
		//		spinnerAdapter.add(context.getString(R.string.action_swipeRight));

		//		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//		spinner.setAdapter(spinnerAdapter);
		//
		//		if (whenScript.getAction() != null) {
		//			spinner.setSelection(whenScript.getPosition(), true);
		//		}
		//
		//		if (spinner.getSelectedItem() == null) {
		//			spinner.setSelection(0);
		//		}
		//
		//		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		//				spinner.setSelected(true);
		//				whenScript.setAction(position);
		//				spinner.setSelection(position);
		//				adapter.notifyDataSetChanged();
		//			}
		//
		//			public void onNothingSelected(AdapterView<?> parent) {
		//			}
		//		});
		return view;
	}

	@Override
	public View getViewWithAlpha(int alphaValue) {
		LinearLayout layout = (LinearLayout) view.findViewById(R.id.brick_user_layout);
		Drawable background = layout.getBackground();
		background.setAlpha(alphaValue);
		this.alphaValue = (alphaValue);
		return view;
	}

	@Override
	public View getPrototypeView(Context context) {
		return getView(context, 0, null);
	}

	@Override
	public Brick clone() {
		return new WhenBrick(getSprite(), null);
	}

	@Override
	public Script initScript(Sprite sprite) {
		if (userScript == null) {
			userScript = new UserScript(sprite);
		}

		return userScript;
	}
}
