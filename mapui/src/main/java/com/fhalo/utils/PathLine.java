package com.fhalo.utils;

import android.graphics.Point;

/**
 * Created by SouKou on 2016/5/13.
 */
public class PathLine {

    private int id;
    private int STANDARD_LINE = 0;
    private int BEZIER_LINE = 1;
    private int lineType = STANDARD_LINE;

    private Point start, end;
    private Point regular;

    public PathLine(int i, Point s, Point e) {
        this.id = i;
        this.start = s;
        this.end = e;

        this.lineType = STANDARD_LINE;
    }

    public PathLine(int i, Point s, Point e, Point r) {
        this.id = i;
        this.start = s;
        this.end = e;
        this.regular = r;

        this.lineType = BEZIER_LINE;
    }

    public Point getStartPoint(){
        return this.start;
    }

    public Point getEndPoint() {
        return this.end;
    }
}
