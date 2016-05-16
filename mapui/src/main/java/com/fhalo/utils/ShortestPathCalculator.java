package com.fhalo.utils;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by SouKou on 2016/5/6.
 */
public class ShortestPathCalculator {
    private static ArrayList<Point> LocationList = new ArrayList<>();
    private static int flag[];
    private static Point start, end;
    private static ArrayList<PathLine> pathLines = new ArrayList<>();
    private static ArrayList<PathLine> shortestPathLines = new ArrayList<>();
    private static ArrayList<PathLine> tempPathList = new ArrayList<>();
    private static int pathLenth;

    public ShortestPathCalculator(ArrayList<PathLine> pl){
        LocationList.clear();
        pathLines = pl;
        flag = new int[pathLines.size()];
        LocationList.add(pathLines.get(0).getStartPoint());
        LocationList.add(pathLines.get(0).getEndPoint());
        for(int i=0, j; i<pathLines.size(); i++) {
            flag[i] = 0;
            for(j=0; j<LocationList.size(); j++) {
                if(LocationList.get(j).x == pathLines.get(i).getStartPoint().x &&
                        LocationList.get(j).y == pathLines.get(i).getStartPoint().y) {
                    break;
                }
                if(j == LocationList.size()-1) {
                    LocationList.add(pathLines.get(i).getStartPoint());
                }
            }
            for(j=0; j<LocationList.size(); j++) {
                if(LocationList.get(j).x == pathLines.get(i).getEndPoint().x &&
                        LocationList.get(j).y == pathLines.get(i).getEndPoint().y) {
                    break;
                }
                if(j == LocationList.size()-1) {
                    LocationList.add(pathLines.get(i).getEndPoint());
                }
            }
        }

    }

    public ArrayList<PathLine> getShortestPath(Point s, Point e) {
        start = s;
        end = e;

        float start_temp, start_min = Integer.MAX_VALUE;
        float end_temp, end_min = Integer.MAX_VALUE;
        int start_flag=-1, end_flag=-1;

        for(int i=0; i<LocationList.size(); i++) {
            start_temp = (LocationList.get(i).x-start.x)*(LocationList.get(i).x-start.x)+
                    (LocationList.get(i).y-start.y)*(LocationList.get(i).y-start.y);
            if(start_min > start_temp) {
                start_min = start_temp;
                start_flag = i;
            }

            end_temp = (LocationList.get(i).x-end.x)*(LocationList.get(i).x-end.x)+
                    (LocationList.get(i).y-end.y)*(LocationList.get(i).y-end.y);
            if(end_min > end_temp) {
                end_min = end_temp;
                end_flag = i;
            }
        }

        pathLenth=Integer.MAX_VALUE;
        tempPathList.clear();
        shortestPathLines.clear();
        findPath(pathLines, LocationList.get(start_flag), LocationList.get(end_flag));
        //add last conjunctive path
        shortestPathLines.add(new PathLine(shortestPathLines.size(), LocationList.get(end_flag), end));
        shortestPathLines.add(new PathLine(shortestPathLines.size(), start, LocationList.get(start_flag)));
        return shortestPathLines;
    }

    private void findPath(ArrayList<PathLine> pathLines, Point s, Point e){
        for(int i=0; i<pathLines.size(); i++) {

            if (s.x==e.x && s.y==e.y) {
                int temp = 0;
                for(PathLine p:tempPathList) {
                    temp+=Math.abs(p.getStartPoint().x-p.getEndPoint().x) + Math.abs(p.getStartPoint().y-p.getEndPoint().y);
                }
                if(temp < pathLenth) {
                    System.out.println("temp:" + temp);
                    pathLenth = temp;
                    shortestPathLines.clear();
                    for(PathLine p:tempPathList) {
                        shortestPathLines.add(p);
                    }
                }
            } else if (flag[i] == 0 && pathLines.get(i).getStartPoint().x == s.x && pathLines.get(i).getStartPoint().y == s.y){
                flag[i] = 1;
                tempPathList.add(pathLines.get(i));
                findPath(pathLines, pathLines.get(i).getEndPoint(), e);
            } else if (flag[i] == 0 && pathLines.get(i).getEndPoint().x == s.x && pathLines.get(i).getEndPoint().y == s.y) {
                flag[i] = 1;
                tempPathList.add(pathLines.get(i));
                findPath(pathLines, pathLines.get(i).getStartPoint(), e);
            }

            if(i == pathLines.size()-1 && (s.x!=e.x || s.y!=e.y)) {
                if(tempPathList.size()!=0) {
                    tempPathList.remove(tempPathList.size()-1);
                }
            }
        }
    }
}
