/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.calculator;

import android.view.MotionEvent;

import javax.annotation.Nonnull;

import org.solovyev.android.view.drag.DirectionDragButton;
import org.solovyev.android.view.drag.DragButton;
import org.solovyev.android.view.drag.DragDirection;
import org.solovyev.android.view.drag.SimpleOnDragListener;
import org.solovyev.common.math.Point2d;

/**
 * User: serso
 * Date: 10/24/11
 * Time: 9:52 PM
 */
public class EqualsDragProcessor implements SimpleOnDragListener.DragProcessor {

	public EqualsDragProcessor() {
	}

	@Override
	public boolean processDragEvent(@Nonnull DragDirection dragDirection, @Nonnull DragButton dragButton, @Nonnull Point2d startPoint2d, @Nonnull MotionEvent motionEvent) {
		boolean result = false;

		if (dragButton instanceof DirectionDragButton) {
			if (dragDirection == DragDirection.down) {
				CalculatorActivityLauncher.tryPlot();
				result = true;
			} else {
				final String text = ((DirectionDragButton) dragButton).getText(dragDirection);
				if ("≡".equals(text)) {
					Locator.getInstance().getCalculator().simplify();
					result = true;
				}
			}
		}

		return result;
	}
}
