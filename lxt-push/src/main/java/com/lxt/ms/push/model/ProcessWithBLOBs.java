package com.lxt.ms.push.model;

public class ProcessWithBLOBs extends Process {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_process.GRAPH_XML
     *
     * @mbggenerated Thu Apr 04 15:26:54 CST 2019
     */
    private String graphXml;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_process.BPMN_XML
     *
     * @mbggenerated Thu Apr 04 15:26:54 CST 2019
     */
    private String bpmnXml;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_process.GRAPH_XML
     *
     * @return the value of lxt_process.GRAPH_XML
     *
     * @mbggenerated Thu Apr 04 15:26:54 CST 2019
     */
    public String getGraphXml() {
        return graphXml;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_process.GRAPH_XML
     *
     * @param graphXml the value for lxt_process.GRAPH_XML
     *
     * @mbggenerated Thu Apr 04 15:26:54 CST 2019
     */
    public void setGraphXml(String graphXml) {
        this.graphXml = graphXml == null ? null : graphXml.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_process.BPMN_XML
     *
     * @return the value of lxt_process.BPMN_XML
     *
     * @mbggenerated Thu Apr 04 15:26:54 CST 2019
     */
    public String getBpmnXml() {
        return bpmnXml;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_process.BPMN_XML
     *
     * @param bpmnXml the value for lxt_process.BPMN_XML
     *
     * @mbggenerated Thu Apr 04 15:26:54 CST 2019
     */
    public void setBpmnXml(String bpmnXml) {
        this.bpmnXml = bpmnXml == null ? null : bpmnXml.trim();
    }
}