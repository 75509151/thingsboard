/**
 * Copyright © 2016-2021 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.transport.lwm2m.rpc;

//import com.fasterxml.jackson.core.type.TypeReference;
//import org.apache.commons.io.IOUtils;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.thingsboard.common.util.JacksonUtil;
//import org.thingsboard.server.common.data.Device;
//import org.thingsboard.server.common.data.DeviceProfile;
//import org.thingsboard.server.common.data.DeviceProfileProvisionType;
//import org.thingsboard.server.common.data.DeviceProfileType;
//import org.thingsboard.server.common.data.DeviceTransportType;
//import org.thingsboard.server.common.data.ResourceType;
//import org.thingsboard.server.common.data.TbResource;
//import org.thingsboard.server.common.data.device.credentials.lwm2m.LwM2MClientCredentials;
//import org.thingsboard.server.common.data.device.credentials.lwm2m.NoSecClientCredentials;
//import org.thingsboard.server.common.data.device.profile.DefaultDeviceProfileConfiguration;
//import org.thingsboard.server.common.data.device.profile.DeviceProfileData;
//import org.thingsboard.server.common.data.device.profile.DisabledDeviceProfileProvisionConfiguration;
//import org.thingsboard.server.common.data.device.profile.Lwm2mDeviceProfileTransportConfiguration;
//import org.thingsboard.server.common.data.security.DeviceCredentials;
//import org.thingsboard.server.common.data.security.DeviceCredentialsType;
//import org.thingsboard.server.dao.service.DaoSqlTest;
//import org.thingsboard.server.transport.lwm2m.AbstractLwM2MIntegrationTest;
//import org.thingsboard.server.transport.lwm2m.bootstrap.secure.LwM2MBootstrapConfig;
//import org.thingsboard.server.transport.lwm2m.bootstrap.secure.LwM2MBootstrapServers;
//import org.thingsboard.server.transport.lwm2m.bootstrap.secure.LwM2MServerBootstrap;
//import org.thingsboard.server.transport.lwm2m.client.LwM2MTestClient;
//import org.thingsboard.server.transport.lwm2m.secure.credentials.LwM2MCredentials;
//
//import java.util.Base64;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.Executors;
//import java.util.function.Predicate;
//
//import static org.eclipse.leshan.core.LwM2mId.ACCESS_CONTROL;
//import static org.eclipse.leshan.core.LwM2mId.DEVICE;
//import static org.eclipse.leshan.core.LwM2mId.FIRMWARE;
//import static org.eclipse.leshan.core.LwM2mId.SERVER;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.BINARY_APP_DATA_CONTAINER;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.TEMPERATURE_SENSOR;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.objectInstanceId_0;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.objectInstanceId_1;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resourceIdName_19_0_0;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resourceIdName_19_1_0;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resourceIdName_3_14;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resourceIdName_3_9;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resourceId_0;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resourceId_14;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resourceId_9;
//import static org.thingsboard.server.transport.lwm2m.rpc.RpcModelsTestHelper.resources;

import org.thingsboard.server.common.data.TbResource;
import org.thingsboard.server.common.data.device.credentials.lwm2m.NoSecClientCredentials;
import org.thingsboard.server.dao.service.DaoSqlTest;
import org.thingsboard.server.transport.lwm2m.AbstractLwM2MIntegrationTest;
import org.thingsboard.server.transport.lwm2m.bootstrap.secure.LwM2MServerBootstrap;
import org.thingsboard.server.transport.lwm2m.client.LwM2MTestClient;

import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

//@DaoSqlTest
public class RpcAbstractLwM2MIntegrationTest extends AbstractLwM2MIntegrationTest {
//
//    protected final String RPC_TRANSPORT_CONFIGURATION = "{\n" +
//            "  \"type\": \"LWM2M\",\n" +
//            "  \"observeAttr\": {\n" +
//            "    \"keyName\": {\n" +
//            "      \"" + "/" + DEVICE + "/" + objectInstanceId_0 + "/" + resourceId_9 + "\": \"" + resourceIdName_3_9 + "\",\n" +
//            "      \"" + "/" + DEVICE + "/" + objectInstanceId_0 + "/" + resourceId_14 + "\": \"" + resourceIdName_3_14 + "\",\n" +
//            "      \"" + "/" + BINARY_APP_DATA_CONTAINER + "/" + objectInstanceId_0 + "/" + resourceId_0 + "\": \"" + resourceIdName_19_0_0 + "\",\n" +
//            "      \"" + "/" + BINARY_APP_DATA_CONTAINER + "/" + objectInstanceId_1 + "/" + resourceId_0 + "\": \"" + resourceIdName_19_1_0 + "\"\n" +
//            "    },\n" +
//            "    \"observe\": [\n" +
//            "      \"" + "/" + DEVICE + "/" + objectInstanceId_0 + "/" + resourceId_9 + "\",\n" +
//            "      \"" + "/" + BINARY_APP_DATA_CONTAINER + "/" + objectInstanceId_0 + "/" + resourceId_0 + "\"\n" +
//            "    ],\n" +
//            "    \"attribute\": [\n" +
//            "    ],\n" +
//            "    \"telemetry\": [\n" +
//            "      \"" + "/" + DEVICE + "/" + objectInstanceId_0 + "/" + resourceId_9 + "\",\n" +
//            "      \"" + "/" + DEVICE + "/" + objectInstanceId_0 + "/" + resourceId_14 + "\",\n" +
//            "      \"" + "/" + BINARY_APP_DATA_CONTAINER + "/" + objectInstanceId_0 + "/" + resourceId_0 + "\",\n" +
//            "      \"" + "/" + BINARY_APP_DATA_CONTAINER + "/" + objectInstanceId_1 + "/" + resourceId_0 + "\"\n" +
//            "    ],\n" +
//            "    \"attributeLwm2m\": {}\n" +
//            "  },\n" +
//            "  \"bootstrap\": {\n" +
//            "    \"servers\": {\n" +
//            "      \"binding\": \"U\",\n" +
//            "      \"shortId\": 123,\n" +
//            "      \"lifetime\": 300,\n" +
//            "      \"notifIfDisabled\": true,\n" +
//            "      \"defaultMinPeriod\": 1\n" +
//            "    },\n" +
//            "    \"lwm2mServer\": {\n" +
//            "      \"host\": \"localhost\",\n" +
//            "      \"port\": 5686,\n" +
//            "      \"serverId\": 123,\n" +
//            "      \"serverPublicKey\": \"\",\n" +
//            "      \"bootstrapServerIs\": false,\n" +
//            "      \"clientHoldOffTime\": 1,\n" +
//            "      \"bootstrapServerAccountTimeout\": 0\n" +
//            "    },\n" +
//            "    \"bootstrapServer\": {\n" +
//            "      \"host\": \"localhost\",\n" +
//            "      \"port\": 5687,\n" +
//            "      \"serverId\": 111,\n" +
//            "      \"securityMode\": \"NO_SEC\",\n" +
//            "      \"serverPublicKey\": \"\",\n" +
//            "      \"bootstrapServerIs\": true,\n" +
//            "      \"clientHoldOffTime\": 1,\n" +
//            "      \"bootstrapServerAccountTimeout\": 0\n" +
//            "    }\n" +
//            "  },\n" +
//            "  \"clientLwM2mSettings\": {\n" +
//            "    \"clientOnlyObserveAfterConnect\": 1,\n" +
//            "    \"fwUpdateStrategy\": 1,\n" +
//            "    \"swUpdateStrategy\": 1\n" +
//            "  }\n" +
//            "}";
//
//    protected NoSecClientCredentials clientCredentials;
//    protected LwM2MTestClient client;
//    protected String deviceId;
//    public Set expectedObjects;
//    public Set expectedObjectIdVers;
//    public Set expectedInstances;
//    public Set expectedObjectIdVerInstances;
//
//    protected String objectInstanceIdVer_1;
//    protected String objectIdVer_2;
//    private static final Predicate predicate_3 = path -> (!((String) path).contains("/" + TEMPERATURE_SENSOR) && ((String) path).contains("/" + DEVICE));
//    protected String objectIdVer_3;
//    protected String objectInstanceIdVer_3;
//    protected String objectInstanceIdVer_5;
//    protected String objectIdVer_19;
//
//    public RpcAbstractLwM2MIntegrationTest(){ }
//
//    @Before
//    public void beforeTest() throws Exception {
//        executor = Executors.newScheduledThreadPool(10);
//        loginTenantAdmin();
//        wsClient = buildAndConnectWebSocketClient();
//        createDeviceProfile(RPC_TRANSPORT_CONFIGURATION);
//        clientCredentials = new NoSecClientCredentials();
//        clientCredentials.setEndpoint(ENDPOINT_SECURITY);
//        Device device = createDevice(clientCredentials);
//        deviceId = device.getId().getId().toString();
//        client = new LwM2MTestClient(executor, ENDPOINT_SECURITY);
//        client.init(SECURITY, COAP_CONFIG, 11004);
//        for (String resourceName : resources) {
//            TbResource lwModel = new TbResource();
//            lwModel.setResourceType(ResourceType.LWM2M_MODEL);
//            lwModel.setTitle(resourceName);
//            lwModel.setFileName(resourceName);
//            lwModel.setTenantId(tenantId);
//            byte[] bytes = IOUtils.toByteArray(AbstractLwM2MIntegrationTest.class.getClassLoader().getResourceAsStream("lwm2m/" + resourceName));
//            lwModel.setData(Base64.getEncoder().encodeToString(bytes));
//            lwModel = doPostWithTypedResponse("/api/resource", lwModel, new TypeReference<>() {
//            });
//            Assert.assertNotNull(lwModel);
//        }
//        expectedObjects = ConcurrentHashMap.newKeySet();
//        expectedObjectIdVers = ConcurrentHashMap.newKeySet();
//        expectedInstances = ConcurrentHashMap.newKeySet();
//        expectedObjectIdVerInstances = ConcurrentHashMap.newKeySet();
//        client.getClient().getObjectTree().getObjectEnablers().forEach((key, val) -> {
//            if (key > 0) {
//                String objectVerId = "/" + key;
//                if (!val.getObjectModel().version.equals("1.0")) {
//                    objectVerId += ("_" + val.getObjectModel().version);
//                }
//                expectedObjects.add("/" + key);
//                expectedObjectIdVers.add(objectVerId);
//                String finalObjectVerId = objectVerId;
//                val.getAvailableInstanceIds().forEach(inststanceId -> {
//                    expectedInstances.add("/" + key + "/" + inststanceId);
//                    expectedObjectIdVerInstances.add(finalObjectVerId + "/" + inststanceId);
//                });
//            }
//        });
//        objectInstanceIdVer_1 = (String) expectedObjectIdVerInstances.stream().filter(path -> (!((String) path).contains("/" + BINARY_APP_DATA_CONTAINER) && ((String) path).contains("/" + SERVER))).findFirst().get();
//        objectIdVer_2 = (String) expectedObjectIdVers.stream().filter(path -> ((String) path).contains("/" + ACCESS_CONTROL)).findFirst().get();
//        objectIdVer_3 = (String) expectedObjects.stream().filter(predicate_3).findFirst().get();
//        objectIdVer_19 = (String) expectedObjects.stream().filter(predicate_3).findFirst().get();
//        objectInstanceIdVer_3 = (String) expectedObjectIdVerInstances.stream().filter(predicate_3).findFirst().get();
//        objectInstanceIdVer_5 = (String) expectedObjectIdVerInstances.stream().filter(path -> ((String) path).contains("/" + FIRMWARE)).findFirst().get();
//        objectIdVer_19 = (String) expectedObjectIdVers.stream().filter(path -> ((String) path).contains("/" + BINARY_APP_DATA_CONTAINER)).findFirst().get();
//    }
//
//    protected void createDeviceProfile(String transportConfiguration) throws Exception {
//        deviceProfile = new DeviceProfile();
//
//        deviceProfile.setName("LwM2M_RPC");
//        deviceProfile.setType(DeviceProfileType.DEFAULT);
//        deviceProfile.setTenantId(tenantId);
//        deviceProfile.setTransportType(DeviceTransportType.LWM2M);
//        deviceProfile.setProvisionType(DeviceProfileProvisionType.DISABLED);
//        deviceProfile.setDescription(deviceProfile.getName());
//
//        DeviceProfileData deviceProfileData = new DeviceProfileData();
//        deviceProfileData.setConfiguration(new DefaultDeviceProfileConfiguration());
//        deviceProfileData.setProvisionConfiguration(new DisabledDeviceProfileProvisionConfiguration(null));
//        deviceProfileData.setTransportConfiguration(JacksonUtil.fromString(transportConfiguration, Lwm2mDeviceProfileTransportConfiguration.class));
//        deviceProfile.setProfileData(deviceProfileData);
//
//        deviceProfile = doPost("/api/deviceProfile", deviceProfile, DeviceProfile.class);
//        Assert.assertNotNull(deviceProfile);
//    }
//
//
//    protected Device createDevice(LwM2MClientCredentials clientCredentials) throws Exception {
//        Device device = new Device();
//        device.setName("Device A");
//        device.setDeviceProfileId(deviceProfile.getId());
//        device.setTenantId(tenantId);
//        device = doPost("/api/device", device, Device.class);
//        Assert.assertNotNull(device);
//        DeviceCredentials deviceCredentials =
//                doGet("/api/device/" + device.getId().getId().toString() + "/credentials", DeviceCredentials.class);
//        Assert.assertEquals(device.getId(), deviceCredentials.getDeviceId());
//        deviceCredentials.setCredentialsType(DeviceCredentialsType.LWM2M_CREDENTIALS);
//        LwM2MCredentials credentials = new LwM2MCredentials();
//        credentials.setClient(clientCredentials);
//        credentials.setBootstrap(createBootstrapConfig());
//        deviceCredentials.setCredentialsValue(JacksonUtil.toString(credentials));
//        doPost("/api/device/credentials", deviceCredentials).andExpect(status().isOk());
//        return device;
//    }
//
//    protected LwM2MBootstrapConfig createBootstrapConfig() {
//        LwM2MBootstrapConfig bootstrap = new LwM2MBootstrapConfig();
//        LwM2MBootstrapServers servers = new LwM2MBootstrapServers();
//        servers.setShortId(SHORT_SERVER_ID);
//        bootstrap.setServers(servers);
//        LwM2MServerBootstrap server = new LwM2MServerBootstrap();
//        server.setHost(HOST);
//        server.setPort(PORT);
//        server.setSecurityHost(HOST);
//        server.setSecurityPort(SECURE_PORT);
//        server.setServerId(servers.getShortId());
//        server.setBootstrapServerIs(false);
//        bootstrap.setLwm2mServer(server);
//        LwM2MServerBootstrap serverBS = new LwM2MServerBootstrap();
//        serverBS.setHost(HOST_BS);
//        serverBS.setPort(PORT_BS);
//        serverBS.setSecurityHost(HOST_BS);
//        serverBS.setSecurityPort(SECURE_PORT_BS);
//        serverBS.setServerId(SHORT_SERVER_ID_BS);
//        serverBS.setBootstrapServerIs(true);
//        bootstrap.setBootstrapServer(serverBS);
//        return bootstrap;
//    }
//
//    protected String objectIdVerToObjectId(String objectIdVer) {
//        return objectIdVer.contains("_") ? objectIdVer.split("_")[0] : objectIdVer;
//    }
//
//    protected String objectInstanceIdVerToObjectInstanceId(String objectInstanceIdVer) {
//        String[] objectIdVer = objectInstanceIdVer.split("/");
//        return objectIdVer[1].contains("_") ? objectIdVer[1].split("_")[0] + "/" + objectIdVer[2] : objectInstanceIdVer;
//    }
//
//    @After
//    public void after() {
//        if (client != null) {
//            client.destroy();
//        }
//        executor.shutdownNow();
//        if (wsClient != null) {
//            wsClient.close();
//        }
//    }
}
