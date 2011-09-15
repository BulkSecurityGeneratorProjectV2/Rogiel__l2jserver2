/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.util.exception.L2Exception;

/**
 * Defines an {@link Template} which the player can interact with
 * 
 * @param <T>
 *            the interable {@link WorldObject} type
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Interactable<T extends WorldObject> extends TemplateCapability {
	/**
	 * Performs an interaction with this template.
	 * 
	 * @param object
	 *            the object to interact with
	 * @param character
	 *            the interacting character
	 * @param args
	 *            the action arguments
	 * @throws L2Exception
	 *             any {@link L2Exception}
	 */
	void action(T object, L2Character character, String... args)
			throws L2Exception;
}
