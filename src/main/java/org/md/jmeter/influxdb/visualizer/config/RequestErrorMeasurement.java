package org.md.jmeter.influxdb.visualizer.config;

public interface RequestErrorMeasurement {

    /**
     * Measurement name.
     */
    String MEASUREMENT_NAME = "requestsError";

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
         * Error message.
         */
        String ERROR_MSG = "errorMessage";

        /**
         * Error response body.
         */
        String ERROR_RESPONSE_BODY = "errorResponseBody";

        /**
         * Thread group name
         */
        String GROUP_NAME = "group_name";


    }

    /**
     * Fields.
     *
     * @author Alexander Wert
     */
    interface Fields {
        /**
         * Error count field.
         */
        String ERROR_COUNT = "errorCount";

    }
}
