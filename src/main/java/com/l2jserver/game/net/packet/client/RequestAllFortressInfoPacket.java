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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.FortInfoPacket;

/**
 * The client is requesting the manor list.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RequestAllFortressInfoPacket extends AbstractClientPacket {
	/**
	 * The packet OPCODE1
	 */
	public static final int OPCODE1 = 0xd0;
	/**
	 * The packet OPCODE2
	 */
	public static final int OPCODE2 = 0x3d;

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
	}

	@Override
	public void process(final Lineage2Connection conn) {
		conn.write(new FortInfoPacket());
	}
}
