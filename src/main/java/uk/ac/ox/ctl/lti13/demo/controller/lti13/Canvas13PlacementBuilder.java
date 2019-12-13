package uk.ac.ox.ctl.lti13.demo.controller.lti13;

import java.util.Map;

public class Canvas13PlacementBuilder {
    private String text;
    private boolean enabled;
    private String iconUrl;
    private Canvas13Placement.Placement placement;
    private Canvas13Placement.MessageType messageType;
    private String targetLinkUri;
    private String canvasIconClass;
    private Map<String, String> customFields;

    public Canvas13PlacementBuilder text(String text) {
        this.text = text;
        return this;
    }

    public Canvas13PlacementBuilder enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Canvas13PlacementBuilder iconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public Canvas13PlacementBuilder placement(Canvas13Placement.Placement placement) {
        this.placement = placement;
        return this;
    }

    public Canvas13PlacementBuilder messageType(Canvas13Placement.MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    public Canvas13PlacementBuilder targetLinkUri(String targetLinkUri) {
        this.targetLinkUri = targetLinkUri;
        return this;
    }

    public Canvas13PlacementBuilder canvasIconClass(String canvasIconClass) {
        this.canvasIconClass = canvasIconClass;
        return this;
    }

    public Canvas13PlacementBuilder customFields(Map<String, String> customFields) {
        this.customFields = customFields;
        return this;
    }

    public Canvas13Placement createCanvas13Placement() {
        return new Canvas13Placement(text, enabled, iconUrl, placement, messageType, targetLinkUri, canvasIconClass, customFields);
    }
}