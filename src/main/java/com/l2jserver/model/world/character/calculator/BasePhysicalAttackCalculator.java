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
package com.l2jserver.model.world.character.calculator;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.stat.BaseStats;
import com.l2jserver.util.calculator.AbstractFunction;
import com.l2jserver.util.calculator.CalculatorContext;

/**
 * Calculates the character base physical attack
 * 
 * <pre>
 * ctx.result = c.getTemplate().getBasePhysicalAttack();
 * ctx.result *= BaseStats.STR.calculateBonus(c.getStats().getStrength())
 * 		* ((100.0 - 11 + c.getLevel()) / 100.0);
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BasePhysicalAttackCalculator extends CharacterCalculator {
	@SuppressWarnings("unchecked")
	public BasePhysicalAttackCalculator() {
		super(new AbstractFunction<L2Character>(0x000) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				ctx.result = c.getTemplate().getBasePhysicalAttack();
			}
		}, new AbstractFunction<L2Character>(0x100) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				ctx.result *= BaseStats.STR.calculateBonus(c.getStats()
						.getStrength()) * ((100.0 - 11 + c.getLevel()) / 100.0);
			}
		});
	}
}
