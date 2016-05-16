package com.fhalo.overlay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.fhalo.utils.PathLine;
import com.jiahuan.svgmapview.SVGMapView;
import com.jiahuan.svgmapview.overlay.SVGMapBaseOverlay;

import java.util.ArrayList;

/**
 * Created by SouKou on 2016/5/4.
 */
public class PathOverlay extends SVGMapBaseOverlay {

    private static final String LAYER_NAME = "path";
    private static final float pathWidth = 10.5f;
    private SVGMapView mMapView;
    private int pathColor;
    private ArrayList<PathLine> pathLines;
    private Paint pathPaint;

    public PathOverlay(SVGMapView svgMapView) {
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setAntiAlias(true);
        pathPaint.setColor(Color.GRAY);
        pathPaint.setStrokeWidth(pathWidth);

        this.mMapView = svgMapView;
        this.layerName = LAYER_NAME;
        this.showLevel = PATH_LEVEL;
    }

    public void setPathLines(ArrayList<PathLine> p) {
        this.pathLines = p;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onTap(MotionEvent event) {

    }

    @Override
    public void draw(Canvas canvas, Matrix matrix, float currentZoom, float currentRotateDegrees) {
        canvas.save();
        canvas.setMatrix(matrix);
        if (pathLines != null && pathLines.size() > 0) {
            int numLines = pathLines.size();
            for(int i=0; i< numLines; i++) {
                int startx = pathLines.get(i).getStartPoint().x;
                int starty = pathLines.get(i).getStartPoint().y;
                int endx = pathLines.get(i).getEndPoint().x;
                int endy = pathLines.get(i).getEndPoint().y;

                canvas.drawLine(startx, starty, endx, endy, pathPaint);
            }
        }

        canvas.restore();
    }
}
