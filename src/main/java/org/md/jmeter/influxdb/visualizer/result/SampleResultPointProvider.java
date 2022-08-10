package org.md.jmeter.influxdb.visualizer.result;

import org.md.jmeter.influxdb.visualizer.config.RequestErrorMeasurement;
import org.md.jmeter.influxdb.visualizer.influxdb.client.InfluxDatabaseUtility;
import org.md.jmeter.influxdb.visualizer.config.RequestMeasurement;
import org.influxdb.dto.Point;

/**
 * The provider of the Influxdb {@link Point} based on the sample result.
 *
 * @author Michael Derevyanko
 */
public class SampleResultPointProvider {

    private final SampleResultPointContext sampleResultContext;
    private final String assertionFailureMessage;
    private Point errorPoint;

    /**
     * Creates the new instance of the {@link SampleResultPointProvider}.
     *
     * @param sampleResultContext the {@link SampleResultPointContext}.
     */
    public SampleResultPointProvider(SampleResultPointContext sampleResultContext) {
        this.sampleResultContext = sampleResultContext;

        this.assertionFailureMessage = this.sampleResultContext.getSampleResult().getFirstAssertionFailureMessage();
    }

    /**
     * Gets {@link Point}, returns the OK or KO jmeter point, depends from the sample result.
     *
     * @return {@link Point} to save.
     */
    public Point getPoint() {

        if (this.assertionFailureMessage == null) {
            return this.getOKPointBuilder()
                    .build();
        } else {
            return this.getErrorPoint().build();
        }
    }

    /**
     * Gets KO jmeter {@link Point}, saves the assertion message and response error body - depends from the settings.
     *
     * @return KO jmeter {@link Point}.
     */
    private Point.Builder getErrorPoint() {
        return Point.measurement(RequestErrorMeasurement.MEASUREMENT_NAME).time(this.sampleResultContext.getTimeToSet(), this.sampleResultContext.getPrecisionToSet())
                .tag(RequestErrorMeasurement.Tags.REQUEST_NAME, this.sampleResultContext.getSampleResult().getSampleLabel())
                .tag(RequestErrorMeasurement.Tags.RUN_ID, this.sampleResultContext.getRunId())
                .tag(RequestErrorMeasurement.Tags.TEST_NAME, this.sampleResultContext.getTestName())
                .tag(RequestErrorMeasurement.Tags.NODE_NAME, this.sampleResultContext.getNodeName())
                .tag(RequestErrorMeasurement.Tags.RESULT_CODE, this.sampleResultContext.getSampleResult().getResponseCode())
                .tag(RequestErrorMeasurement.Tags.ERROR_MSG, this.assertionFailureMessage)
                .tag(RequestErrorMeasurement.Tags.ERROR_RESPONSE_BODY, this.getErrorBody())
                .tag(RequestErrorMeasurement.Tags.GROUP_NAME, this.sampleResultContext.getGroupName())
                .addField(RequestErrorMeasurement.Fields.ERROR_COUNT, this.sampleResultContext.getSampleResult().getErrorCount());
    }

    /**
     * Gets error body.
     *
     * @return returns body of the failed response.
     */
    private String getErrorBody() {
        String errorBody = this.sampleResultContext.getSampleResult().getResponseDataAsString();
        if (errorBody != null && !errorBody.isEmpty()) {
            return InfluxDatabaseUtility.getEscapedString(errorBody);
        }

        return "ErrorBodyIsEmpty";
    }

    /**
     * Gets error body.
     *
     * @return returns body of the failed response.
     */
    private String getSamplerData() {
        String samplerData = this.sampleResultContext.getSampleResult().getSamplerData();
        if (samplerData != null && !samplerData.isEmpty()) {
            return InfluxDatabaseUtility.getEscapedString(samplerData);
        }

        return "SamplerDataIsEmpty";
    }

    /**
     * Builds the OK jmeter {@link Point}.
     *
     * @return OK jmeter {@link Point}.
     */
    private Point.Builder getOKPointBuilder() {

        return Point.measurement(RequestMeasurement.MEASUREMENT_NAME).time(this.sampleResultContext.getTimeToSet(), this.sampleResultContext.getPrecisionToSet())
                .tag(RequestMeasurement.Tags.REQUEST_NAME, this.sampleResultContext.getSampleResult().getSampleLabel())
                .tag(RequestMeasurement.Tags.RUN_ID, this.sampleResultContext.getRunId())
                .tag(RequestMeasurement.Tags.TEST_NAME, this.sampleResultContext.getTestName())
                .tag(RequestMeasurement.Tags.NODE_NAME, this.sampleResultContext.getNodeName())
                .tag(RequestMeasurement.Tags.RESULT_CODE, this.sampleResultContext.getSampleResult().getResponseCode())
                .addField(RequestMeasurement.Fields.ERROR_COUNT, this.sampleResultContext.getSampleResult().getErrorCount())
                .addField(RequestMeasurement.Fields.REQUEST_COUNT, this.sampleResultContext.getSampleResult().getSampleCount())
                .addField(RequestMeasurement.Fields.RECEIVED_BYTES, this.sampleResultContext.getSampleResult().getBytesAsLong())
                .addField(RequestMeasurement.Fields.SENT_BYTES, this.sampleResultContext.getSampleResult().getSentBytes())
                .addField(RequestMeasurement.Fields.RESPONSE_TIME, this.sampleResultContext.getSampleResult().getTime())
                .tag(RequestMeasurement.Tags.GROUP_NAME, this.sampleResultContext.getGroupName())
                .tag(RequestMeasurement.Tags.RUNNING_THREADS, this.sampleResultContext.getRunningThreads());
    }
}
