package org.md.jmeter.influxdb.visualizer.config;

/**
 * Constants (Tag, Field, Measurement) names for the requests measurement.
 *
 * @author Alexander Wert
 * @author Michael Derevyanko (minor changes and improvements)
 */
public interface RequestMeasurement {

    /**
     * Measurement name.
     */
    String MEASUREMENT_NAME = "requestsRaw";

    /**
     * Tags.
     *
     * @author Alexander Wert
     * @author Michael Derevyanko (minor changes and improvements)
     */
    interface Tags {
        /**
         * Request name tag.
         */
        String REQUEST_NAME = "requestName";

        /**
         * Influx DB tag for a unique identifier for each execution(aka 'run') of a load test.
         */
        String RUN_ID = "runId";

        /**
         * Test name field.
         */
        String TEST_NAME = "testName";

        /**
         * Node name field.
         */
        String NODE_NAME = "nodeName";

        /**
         * Response code field.
         */
        String RESULT_CODE = "responseCode";

        /**
         * Running threads field.
         */
        String RUNNING_THREADS = "runningThreads";


    }

    /**
     * Fields.
     *
     * @author Alexander Wert
     */
    interface Fields {
        /**
         * Response time field.
         */
        String RESPONSE_TIME = "responseTime";

        /**
         * Error count field.
         */
        String ERROR_COUNT = "errorCount";

        /**
         * Error count field.
         */
        String REQUEST_COUNT = "count";


        /**
         * Sent Bytes field.
         */
        String SENT_BYTES = "sentBytes";

        /**
         * Received Bytes field.
         */
        String RECEIVED_BYTES = "receivedBytes";
    }
}
