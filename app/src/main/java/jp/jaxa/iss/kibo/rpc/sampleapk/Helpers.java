package jp.jaxa.iss.kibo.rpc.sampleapk;

import android.util.SparseIntArray;

import java.util.List;
import java.util.Arrays;

import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

class Constants {
    
    /*PARENT MOVEMENTS***/
    private static final Coordinate parent12356QR = new Coordinate(
            new Point(11.453, -8.552, 5.25), new Quaternion(0, 0, 0, 0),
            1
    );

    private static final Coordinate parent4G = new Coordinate(
             new Point(10.51, -6.7185, 5.25), new Quaternion(0, 0, 0, 0),
             2
    );

     /*TARGET MOVEMENTS***/
     static final Coordinate start = new Coordinate(
             new Point(10.281791377069263, -9.820110053581466, 4.293233254462052), new Quaternion(0, 0, 0, 0), 0
     );

     static final Coordinate targetOne = new Coordinate(
             new Point(11.225, -9.923, 5.469), new Quaternion(0, 0, -0.707f, 0.707f),
             parent12356QR
     );

     static final Coordinate targetTwo = new Coordinate(
             new Point(10.463, -9.173, 4.48), new Quaternion(0.5f, 0.5f, -0.5f, 0.5f),
             parent12356QR
     );

     static final Coordinate targetThree = new Coordinate(
            new Point(10.71, -7.75, 4.48), new Quaternion(0, 0.707f, 0, 0.707f),
             parent12356QR
     );

     static final Coordinate targetFour = new Coordinate(
             new Point(10.485, -6.615, 5.17), new Quaternion(0, 0, -1, 0),
             parent4G
     );

     static final Coordinate targetFive = new Coordinate(
             new Point(11.037, -7.902, 5.312), new Quaternion(-0.5f, -0.5f, -0.5f, 0.5f),
             parent12356QR
     );

     static final Coordinate targetSix = new Coordinate(
             new Point(11.307, -9.038, 4.931), new Quaternion(0, 0, 0, 1),
             parent12356QR
     );

     static final Coordinate targetQR = new Coordinate(
             new Point(11.369, -8.5518, 4.40), new Quaternion(0.707f, 0f, -0.707f, 0f),
             parent12356QR
     );
     static final Coordinate goal = new Coordinate(
             new Point(11.143, -6.7607, 4.9654), new Quaternion(0, 0, -0.707f, 0.707f),
             parent4G
     );
    
    /*TARGET IDS**/
    static final int[] targetOneIDs = {1, 2, 3, 4};
    static final int[] targetTwoIDs = {5, 6, 7, 8};
    static final int[] targetThreeIDs = {9, 10, 11, 12};
    static final int[] targetFourIDs = {13, 14, 15, 16};
    static final int[] targetFiveIDs = {17, 18, 19, 20};
    static final int[] targetSixIDs = {21, 22, 23, 24};
}

@SuppressWarnings("unused")
class Coordinate {
    private Point point;
    private Quaternion quaternion;
    private Coordinate parent;
    private boolean has_parent;

    int parentID;

    Coordinate(Point pt, Quaternion qt, int pID) {
        point = pt;
        quaternion = qt;
        has_parent = false;
        parentID = pID;
    }
    
    Coordinate(Point pt, Quaternion qt, Coordinate prt){
        point = pt;
        quaternion = qt; 
        parent = prt;
        has_parent = true;
    }

    boolean hasParent() {return has_parent;}

    Point getPoint() {return point;}
    Quaternion getQuaternion() {return quaternion;}
    Coordinate getParent() {return parent;}

    void setPoint(Point pt) {point = pt;}
    void setQuaternion(Quaternion qt) {quaternion = qt;}
    void setParent(Coordinate prt) {parent = prt; has_parent = true;}
}

class ListUtils {

    /**
     * Checks if a list contains any elements from an int[] array
     * @param arrayList the list to check
     * @param elements the elements to find
     * @return true if any elements from elements[] are in arrayList<>
     */
    static boolean containsAny(List<Integer> arrayList, int[] elements) {
        for (int element : elements) {
            if (arrayList.contains(element)) {
                return true; }}
        return false;
    }

    @SuppressWarnings("all")
    static boolean containsAll(List<Integer> arrayList, int[] elements){
        return arrayList.containsAll(Arrays.asList(elements));
    }

    static int findMaxFromMap(List<Integer> active, SparseIntArray pointValues){
        int maxPts = 0, maxTarget = 0;

        for(int target : active){
            int pointVal = pointValues.get(target);
            if(pointVal > maxPts){
                maxPts = pointVal;
                maxTarget = target; }}

        return maxTarget;
    }

    static boolean sameMapValues(SparseIntArray map){
        int firstVal = map.valueAt(0);

        for(int i = 1; i < map.size(); i++){
            int cVal = map.valueAt(i);
            if(cVal != firstVal) return false;
        }

        return  true;
    }
}

