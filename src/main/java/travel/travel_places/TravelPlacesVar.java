package travel.travel_places;

import org.json.simple.JSONObject;

public class TravelPlacesVar {
    private JSONObject wisataBuatanObject;
    private JSONObject wisataPSRB;

    public String getWisataBuatanObjectName() {
        JSONObject metadata = (JSONObject) wisataBuatanObject.get("metadata");
        JSONObject dataset = (JSONObject) metadata.get("dataset");
        return dataset.get("name").toString();
    }

    public String getWisataPSRBObjectName() {
        JSONObject metadata = (JSONObject) wisataPSRB.get("metadata");
        JSONObject dataset = (JSONObject) metadata.get("dataset");
        return dataset.get("name").toString();
    }

    public JSONObject getWisataBuatanObject() {
        return wisataBuatanObject;
    }

    public JSONObject getWisataPSRBObject() {
        return wisataPSRB;
    }

    public void setWisataBuatanObject(JSONObject newValue) {
        this.wisataBuatanObject = newValue;
    }

    public void setWisataPSRBObject(JSONObject newValue) {
        this.wisataPSRB = newValue;
    }
}
