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

import java.util.StringTokenizer;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.provider.ObjectIDResolver;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.npc.ActionServiceException;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.util.BufferUtils;

/**
 * Executes an bypass command
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterRequestBypass extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x23;

	/**
	 * The {@link ObjectID} resolver
	 */
	private final ObjectIDResolver idResolver;
	/**
	 * The {@link NPC} service
	 */
	private final NPCService npcService;

	/**
	 * The bypass command
	 */
	private String command;

	@Inject
	public CharacterRequestBypass(ObjectIDResolver idResolver,
			NPCService npcService) {
		this.idResolver = idResolver;
		this.npcService = npcService;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		this.command = BufferUtils.readString(buffer);
	}

	@Override
	public void process(final Lineage2Connection conn) {
		// parse command
		final StringTokenizer tokenizer = new StringTokenizer(command, "_ ");
		final String type = tokenizer.nextToken();
		if (type.equals("npc")) {
			final int objectId = Integer.parseInt(tokenizer.nextToken());
			final ObjectID<NPC> id = idResolver.resolve(objectId);
			if (!(id instanceof NPCID)) {
				conn.sendActionFailed();
				return;
			}
			final NPC npc = id.getObject();
			try {
				npcService.action(npc, conn.getCharacter(),
						createArgumentArray(tokenizer));
			} catch (ActionServiceException e) {
				conn.sendActionFailed();
			}
		}
	}

	private String[] createArgumentArray(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens())
			return new String[0];
		final String[] args = new String[tokenizer.countTokens()];
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			args[i++] = tokenizer.nextToken();
		}
		return args;
	}
}
