package com.itechgenie.apps.ubicomclient.dto;


import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RoomDTO implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("roomNo")
    private Integer roomNo;
    @JsonProperty("isAvailable")
    private String isAvailable;
    @JsonProperty("type")
    private Object type;
    @JsonProperty("userEmail")
    private String userEmail;
    @JsonProperty("roomTemp")
    private String roomTemp;
    @JsonProperty("roomColor")
    private String roomColor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("roomNo")
    public Integer getRoomNo() {
        return roomNo;
    }

    @JsonProperty("roomNo")
    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    @JsonProperty("isAvailable")
    public String getIsAvailable() {
        return isAvailable;
    }

    @JsonProperty("isAvailable")
    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    @JsonProperty("type")
    public Object getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("userEmail")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("userEmail")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("roomTemp")
    public String getRoomTemp() {
        return roomTemp;
    }

    @JsonProperty("roomTemp")
    public void setRoomTemp(String roomTemp) {
        this.roomTemp = roomTemp;
    }

    @JsonProperty("roomColor")
    public String getRoomColor() {
        return roomColor;
    }

    @JsonProperty("roomColor")
    public void setRoomColor(String roomColor) {
        this.roomColor = roomColor;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoomDTO{");
        sb.append("id=").append(id);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", isAvailable='").append(isAvailable).append('\'');
        sb.append(", type=").append(type);
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", roomTemp='").append(roomTemp).append('\'');
        sb.append(", roomColor='").append(roomColor).append('\'');
        sb.append(", additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }
}