/***************************************************************************
 *                   (C) Copyright 2003-2008 - Marauroa                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package marauroa.common.net.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import marauroa.common.net.Channel;
import marauroa.common.net.InputSerializer;
import marauroa.common.net.OutputSerializer;
import marauroa.common.net.message.Message.MessageType;

import org.junit.Test;

public class MessageTest {

	private static class MockMessage extends Message {

		public MockMessage(MessageType type, Channel channel) {
			super(type, channel);
		}

	}

	@Test
	public final void testGetAddress() {
		MockMessage mm = new MockMessage(MessageType.C2S_ACTION, new Channel());
		assertNull(mm.getAddress());
	}

	@Test
	public final void testGetType() {
		MockMessage mm = new MockMessage(MessageType.C2S_ACTION, new Channel());
		assertTrue(MessageType.C2S_ACTION == mm.getType());
	}

	@Test
	public final void testReadWriteObject() throws IOException {
		MockMessage mmOut = new MockMessage(MessageType.C2S_ACTION, new Channel());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mmOut.setClientID(1);
		mmOut.writeObject(new OutputSerializer(out));
		InputSerializer in = new InputSerializer(new ByteArrayInputStream(out.toByteArray()));
		MockMessage mmInn = new MockMessage(MessageType.C2S_ACTION, new Channel());

		mmInn.readObject(in);
		assertEquals(mmOut.getClientID(), mmInn.getClientID());
		assertEquals(mmOut.getMessageTimestamp(), mmInn.getMessageTimestamp());
		assertEquals(mmOut.getType(), mmInn.getType());
	}

	@Test
	public final void testInvalidClientId() throws IOException {
		MockMessage mmOut = new MockMessage(MessageType.C2S_ACTION, new Channel());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mmOut.setClientID(Message.CLIENTID_INVALID);
		mmOut.writeObject(new OutputSerializer(out));
		InputSerializer in = new InputSerializer(new ByteArrayInputStream(out.toByteArray()));
		MockMessage mmInn = new MockMessage(MessageType.C2S_ACTION, new Channel());

		mmInn.readObject(in);
		assertEquals(mmOut.getClientID(), mmInn.getClientID());
		assertEquals(mmOut.getMessageTimestamp(), mmInn.getMessageTimestamp());
		assertEquals(mmOut.getType(), mmInn.getType());
	}

}
