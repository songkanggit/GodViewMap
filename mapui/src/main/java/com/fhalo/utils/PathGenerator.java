package com.fhalo.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Point;

import com.fhalo.application.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by SouKou on 2016/5/5.
 */
public class PathGenerator {

    private ArrayList<PathLine> pathLines;

    public PathGenerator() {
        pathLines = new ArrayList<PathLine>(){};
    }

    public void parse(Context mContext) {
        XmlResourceParser xrp = mContext.getResources().getXml(R.xml.road_path);
        try {
            while(xrp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if(xrp.getEventType() == XmlPullParser.START_TAG) {
                    if(xrp.getName().endsWith("path")) {
                        if (xrp.getAttributeCount() == 5) {
                            PathLine pp = new PathLine(
                                    Integer.valueOf(xrp.getAttributeValue(0)),
                                    new Point(Integer.valueOf(xrp.getAttributeValue(1)), Integer.valueOf(xrp.getAttributeValue(2))),
                                    new Point(Integer.valueOf(xrp.getAttributeValue(3)), Integer.valueOf(xrp.getAttributeValue(4)))
                            );
                            pathLines.add(pp);
                        }
                    }
                }
                xrp.next();
            }
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PathLine> getPathLines() {
        return this.pathLines;
    }
}
