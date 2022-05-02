package com.oracle.osd.sharding.worker.workflows;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.core.VirtualNetworkClient;
import com.oracle.bmc.core.requests.GetVcnRequest;
import com.oracle.bmc.core.responses.GetVcnResponse;
import com.oracle.bmc.identity.IdentityClient;
import com.oracle.osd.sharding.commons.obo.OboAuthUtils;

import java.io.IOException;

public class Testclass {

    public static void main(String[] args) throws IOException {
        String PROFILE_DEFAULT = "DEFAULT";
        AuthenticationDetailsProvider authenticationDetailsProvider =
                new ConfigFileAuthenticationDetailsProvider("SHARDEDCP");
        IdentityClient identityClient =
                IdentityClient.builder().build(authenticationDetailsProvider);
        identityClient.setRegion(Region.US_ASHBURN_1);
        VirtualNetworkClient vncClient =  new VirtualNetworkClient(authenticationDetailsProvider);
        GetVcnRequest request =
                GetVcnRequest.builder()
                        .vcnId("ocid1.vcn.oc1.iad.amaaaaaab2jkvtqa6khah2gr74ca4dq4wut6wef3abzuzq6hpcq67w3kegfb")

                        .build();
        GetVcnResponse response = vncClient.getVcn(request);
        if (response.get__httpStatusCode__() == 200) {

            System.out.println("MMmMMMMMMMMM");
        }
        System.out.println(response);


    }
}
