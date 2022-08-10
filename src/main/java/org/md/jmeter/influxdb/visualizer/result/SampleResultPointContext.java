package org.md.jmeter.influxdb.visualizer.result;

import org.apache.jmeter.samplers.SampleResult;

import java.util.concurrent.TimeUnit;

/**
 * The data object with parameters to create Result {@link org.influxdb.dto.Point}.
 *
 * @author Michael Derevyanko
 */
public class SampleResultPointContext {

    private long timeToSet;
    private TimeUnit precisionToSet;
    private SampleResult sampleResult;
    private String nodeName;
    private String runId;
    private String testName;
    boolean errorBodyToBeSaved;

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRunningThreads() {
        return this.runningThreads.toString();
    }

    public void setRunningThreads(int runningThreads) {
        this.runningThreads = runningThreads;
    }

    private Integer runningThreads;

    /**
     * Checks whether the body of the failed repose is going to be saved.
     *
     * @return true to save body; otherwise false to skip saving.
     */
    public boolean isErrorBodyToBeSaved() {
        return errorBodyToBeSaved;
    }

    /**
     * Sets the option to save failed repose body.
     *
     * @param flag represents the option.
     */
    public void setErrorBodyToBeSaved(boolean flag) {
        this.errorBodyToBeSaved = flag;
    }

    /**
     * Gets the run id, set in jmeter option.
     *
     * @return returns run id.
     */
    public String getRunId() {
        return this.runId;
    }

    /**
     * Sets run id.
     *
     * @param runId represents the run id.
     */
    public void setRunId(String runId) {
        this.runId = runId;
    }

    /**
     * Gets the node name to sort out the PC from what test has been started.
     *
     * @return the node name.
     */
    public String getNodeName() {
        return this.nodeName;
    }

    /**
     * Sets the node name.
     *
     * @param nodeName the node name.
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * Gets the {@link SampleResult}.
     *
     * @return sample result.
     */
    public SampleResult getSampleResult() {
        return this.sampleResult;
    }

    /**
     * Sets {@link SampleResult}.
     *
     * @param sampleResult {@link SampleResult}
     */
    public void setSampleResult(SampleResult sampleResult) {
        this.sampleResult = sampleResult;
    }

    /**
     * Gets the time frame of the further {@link org.influxdb.dto.Point}.
     *
     * @return time represented in number.
     */
    public long getTimeToSet() {
        return this.timeToSet;
    }

    /**
     * Sets time of the further {@link org.influxdb.dto.Point}.
     *
     * @param timeToSet the time represented in number.
     */
    public void setTimeToSet(long timeToSet) {
        this.timeToSet = timeToSet;
    }

    /**
     * Gets the time precision to set.
     *
     * @return the time.
     */
    public TimeUnit getPrecisionToSet() {
        return precisionToSet;
    }

    /**
     * Sets the precision to set.
     *
     * @param precisionToSet the {@link TimeUnit} of the precision to set.
     */
    public void setPrecisionToSet(TimeUnit precisionToSet) {
        this.precisionToSet = precisionToSet;
    }

    /**
     * Gets the test name.
     *
     * @return the test name.
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Sets the test name.
     *
     * @param testName the test name.
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }
}
