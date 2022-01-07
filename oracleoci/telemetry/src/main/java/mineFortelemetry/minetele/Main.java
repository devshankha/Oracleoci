package mineFortelemetry.minetele;
import com.oracle.bmc.auth.S2SAuthenticationDetailsProvider;
import com.oracle.pic.commons.configuration.location.Location;
import com.oracle.pic.telemetry.commons.metrics.MetricReporter;
import com.oracle.pic.telemetry.commons.metrics.Metrics;
import com.oracle.pic.telemetry.overlay.metrics.OverlayReporterBuilder;
//looks like this is the wrong import
//import com.oracle.cloudguard.commons.auth.AuthNProvider;
//this is also not correct
//import com.oracle.oci.cloudguard.commons.auth.AuthNProvider


import com.oracle.oci.cloudguard.commons.auth.AuthNProvider;



public class Main {
	
	 public static void main(String[] args) throws InterruptedException {
		  OverlayReporterBuilder builder = new OverlayReporterBuilder()
		            .project("cloudguard")
		            .readTimeout(10 * 1000) //read timeout ms
		            .connectionTimeout(10 * 1000) //connection timeout ms
		            .fleet("laptop");
		 
		  //get ad name from /etc/availability-domain
	        String ad = Location.fromEnvironmentFiles().getAvailabilityDomain().getName();
	        builder.availabilityDomain(ad);
	        
	        final String tenantId = "ocid1.tenancy.oc1..aaaaaaaa3jivujizrvqipzfzylu5ljishwwqshhrjbontac77pdnldc2mi3a";
	        S2SAuthenticationDetailsProvider s2SAuthenticationDetailsProvider =
	            new AuthNProvider(true).getS2SAuthenticationDetailsProvider(tenantId);
	        builder.provider(s2SAuthenticationDetailsProvider);
	        
	        MetricReporter metricReporter = builder.build();
	        Metrics.init(metricReporter);

	        for (int i = 0; i < 10; i++) {
	            Metrics.emit("testCount5", i);
	           
	            Thread.sleep(1000);
	        }

	
		
	}

}
