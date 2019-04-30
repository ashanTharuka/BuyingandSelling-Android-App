package data;

/**
 * Created by DELL on 21-Feb-18.
 */

public class LocationDTO {
    private int locationId;
    private String locationName;

    public LocationDTO() {
    }

    public LocationDTO(int locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
