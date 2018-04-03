/**
 *    Copyright 2013, Big Switch Networks, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License"); you may
 *    not use this file except in compliance with the License. You may obtain
 *    a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *    License for the specific language governing permissions and limitations
 *    under the License.
 **/

package net.floodlightcontroller.core.internal;

import static org.junit.Assert.assertEquals;  
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import net.floodlightcontroller.core.IOFSwitchBackend;
import net.floodlightcontroller.core.SwitchDriverSubHandshakeAlreadyStarted;
import net.floodlightcontroller.core.SwitchDriverSubHandshakeCompleted;
import net.floodlightcontroller.core.SwitchDriverSubHandshakeNotStarted;
import net.floodlightcontroller.core.util.URIUtil;

import org.projectfloodlight.openflow.protocol.OFBsnControllerConnection;
import org.projectfloodlight.openflow.protocol.OFBsnControllerConnectionState;
import org.projectfloodlight.openflow.protocol.OFBsnControllerConnectionsReply;
import org.projectfloodlight.openflow.protocol.OFConfigurationMsg;
import org.projectfloodlight.openflow.protocol.OFControllerRole;
import org.projectfloodlight.openflow.protocol.OFFactories;
import org.projectfloodlight.openflow.protocol.OFFactory;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFNiciraControllerRole;
import org.projectfloodlight.openflow.protocol.OFVersion;
import org.projectfloodlight.openflow.types.DatapathId;
import org.projectfloodlight.openflow.types.OFAuxId;
import org.projectfloodlight.openflow.types.U64;


import com.google.common.primitives.UnsignedInteger;

public class OFConfigurationMessageTest {
	
	@Test
	public void test() {
		sendConfigurationMessage((short)2,(short)4,U64.ZERO);
	}
	
	public void sendConfigurationMessage(short channel, short length_ssid, U64 ssid) {
		
		OFFactory factory = OFFactories.getFactory(OFVersion.OF_14);
		
		OFConfigurationMsg msg = factory.buildConfigurationMsg()
		//		.setXid(handshakeTransactionIds--)
				.setChannel(channel)
				.setLengthSsid(length_ssid)
				.setSSID(ssid)
				.build();

		System.out.println("msg.getExperimenter(): " + msg.getExperimenter());
		System.out.println("msg.getChannel: " + msg.getChannel());
		System.out.println("msg.getLengthSsid(): " + msg.getLengthSsid());

		String stop;
		stop = "zastav";
		
		/* Record for latency calculation */
		//echoSendTime = System.currentTimeMillis();
		//write(msg);
	}
}
