package kn.inferno.domain.model;

import kn.inferno.domain.model.constants.Location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name="public_holiday")
public class PublicHoliday {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PUBLIC_HOLIDAY_SEQ")
    @SequenceGenerator(name="PUBLIC_HOLIDAY_SEQ", sequenceName="PUBLIC_HOLIDAY_SEQ", allocationSize=1)
    private int id;

    @NotNull
    @Column(name = "hdate")
    private Date date;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "location_id")
    private int locationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Location getLocation () {return Location.parse(locationId);}

    public void setLocation (Location location) {this.locationId = location.getValue();}
}
