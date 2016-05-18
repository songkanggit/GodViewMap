package com.fhalo.utils;

/**
 * Created by SouKou on 2016/5/18.
 */
public class LocationSvgInfo {
    private String name;
    private int svg_abs_x;
    private int svg_abs_y;
    private float geo_altitude;
    private float geo_longtitude;

    public LocationSvgInfo() {
        name = "";
        svg_abs_x = -1;
        svg_abs_y = -1;
        geo_altitude = -1;
        geo_longtitude = -1;
    }

    public LocationSvgInfo(String locationName, int svg_x, int svg_y) {
        name = locationName;
        svg_abs_y = svg_y;
        svg_abs_x = svg_x;
        geo_altitude = -1;
        geo_longtitude = -1;
    }

    public LocationSvgInfo(String locationName, int svg_x, int svg_y, float altitude, float longtitude) {
        name = locationName;
        svg_abs_y = svg_y;
        svg_abs_x = svg_x;
        geo_altitude = altitude;
        geo_longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSvg_abs_x() {
        return svg_abs_x;
    }

    public void setSvg_abs_x(int svg_abs_x) {
        this.svg_abs_x = svg_abs_x;
    }

    public int getSvg_abs_y() {
        return svg_abs_y;
    }

    public void setSvg_abs_y(int svg_abs_y) {
        this.svg_abs_y = svg_abs_y;
    }

    public float getGeo_altitude() {
        return geo_altitude;
    }

    public void setGeo_altitude(float geo_altitude) {
        this.geo_altitude = geo_altitude;
    }

    public float getGeo_longtitude() {
        return geo_longtitude;
    }

    public void setGeo_longtitude(float geo_longtitude) {
        this.geo_longtitude = geo_longtitude;
    }
}
