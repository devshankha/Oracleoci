

import com.oracle.pic.telemetry.commons.metrics.MetricReporter;
import com.oracle.pic.telemetry.commons.metrics.Metrics;
import com.oracle.pic.telemetry.overlay.metrics.OverlayReporterBuilder;

/**
 * Hello world!
 *
 */
public class AppMain {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Creating the overlay mmmm");

		OverlayReporterBuilder builder = new OverlayReporterBuilder().project("osd").readTimeout(10 * 1000) // read
																											// timeout
																											// ms
				.connectionTimeout(10 * 1000) // connection timeout ms
				.fleet("laptop").region("us-phoenix-1").availabilityDomain("phx-ad-1");

		MetricReporter metricReporter = builder.build();
		Metrics.init(metricReporter);

		for (int i = 0; i < 3; i++) {
			System.out.println("Emitting the metrics before ");
			Metrics.emit("testCount5", i);
			System.out.println("Emitting the metrics after");

			Thread.sleep(1000);

		}
		Metrics.shutdown();

	}
}
