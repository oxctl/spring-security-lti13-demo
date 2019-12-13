package uk.ac.ox.ctl.lti13.demo.controller.lti13;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Canvas13Placement {

    public enum MessageType {LtiResourceLinkRequest, LtiDeepLinkingRequest};

    public enum Placement {
        @JsonProperty("link_selection") LINK_SELECTION,
        @JsonProperty("assignment_selection") ASSIGNMENT_SELECTION,
        @JsonProperty("course_navigation") COURSE_NAVIGATION,
        @JsonProperty("account_navigation") ACCOUNT_NAVIGATION,
        @JsonProperty("user_navigation") USER_NAVIGATION,
        @JsonProperty("editor_button") EDITOR_BUTTON,
        @JsonProperty("migration_selection") MIGRATION_SELECTION,

        // These aren't in the API documentation, but are in the list in the UI
        // similarity_detection
        // assignment_edit
        // assignment_menu
        // assignment_view
        // collaboration
        // course_assignments_menu
        // course_home_sub_navigation
        // course_settings_sub_navigation
        // discussion_topic_menu
        // file_menu
        // global_navigation
        // homework_submission
        // module_menu
        // module_index_menu
        // post_grades
        // quiz_menu
        // resource_selection
        // student_context_card
        // tool_configuration
        // wiki_index_menu
        // wiki_page_menu

    }

    private String text;
    private boolean enabled;
    private String iconUrl;
    private Placement placement;
    private MessageType messageType;
    private String targetLinkUri;
    private String canvasIconClass;
    private Map<String, String> customFields;

    public Canvas13Placement(String text, boolean enabled, String iconUrl, Placement placement, MessageType messageType, String targetLinkUri, String canvasIconClass, Map<String, String> customFields) {
        this.text = text;
        this.enabled = enabled;
        this.iconUrl = iconUrl;
        this.placement = placement;
        this.messageType = messageType;
        this.targetLinkUri = targetLinkUri;
        this.canvasIconClass = canvasIconClass;
        this.customFields = customFields;
    }

    public String getText() {
        return text;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public Placement getPlacement() {
        return placement;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getTargetLinkUri() {
        return targetLinkUri;
    }

    public String getCanvasIconClass() {
        return canvasIconClass;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }
}
