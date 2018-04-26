import java.util.Arrays;

public class SchoolgirlSolver {
    public static final int NUMBER_OF_GIRLS = 105;
    public static final int GIRLS_PER_DAY = 15;
    public static int[] week = new int[NUMBER_OF_GIRLS];
    public static boolean[][] adjacencyMatrix = new boolean[NUMBER_OF_GIRLS][NUMBER_OF_GIRLS];

    //UNCOMMENT ONLY IF DEFCON 1
    //FASTEST POSSIBLE SCHOOLGIRL SOLVER IN THE HISTORY OF SCHOOLGIRL SOLVERS
    /*
    public static void schoolgirlSolver() {
        System.out.println("Sun    Mon    Tue    Wed    Thu    Fri    Sat");
        System.out.println("---    ---    ---    ---    ---    ---    ---");
        System.out.println("ABC    ADG    AEJ    AFO    AHK    AIM    ALN");
        System.out.println("DEF    BEH    BFL    BDM    BGN    BKO    BIJ");
        System.out.println("GHI    CJM    CHO    CGL    CFI    CEN    CDK");
        System.out.println("JKL    FKN    DIN    EIK    DJO    DHL    EGO");
        System.out.println("MNO    ILO    GKM    HJN    ELM    FGJ    FHM");
    }
    */

    public static boolean isValid(int slot, int girl) {
        if (isAlreadyInDay(slot, girl)) {
            return false;
        }
        if (slot % 15 == 0 && girl != 0) {
            return false;
        }
        else if (slot % 3 == 0) {
            if (!isSlotEmpty(slot + 1) && areAdjacent(girl, week[slot + 1])) {
                return false;
            }
            if (!isSlotEmpty(slot + 2) && areAdjacent(girl, week[slot + 2])) {
                return false;
            }
        } else if (slot % 3 == 1) {
            if (areAdjacent(girl, week[slot - 1])) {
                return false;
            }
            if (week[slot -1] > girl) {
                return false;
            }
            if (!isSlotEmpty(slot + 1) && areAdjacent(girl, week[slot + 1])) {
                return false;
            }
        } else if (slot % 3 == 2) {
            if (areAdjacent(girl, week[slot - 1]) || areAdjacent(girl, week[slot - 2])) {
                return false;
            }
            if (week[slot - 1] > girl || week[slot - 2] > girl) {
                return false;
            }
        } else if ((slot % 3 == 0 || slot % 3 == 1) && slot % 15 != 0) {
            if (week[slot - 3] > girl) {
                return false;
            }
        }
        return true;
    }

    public static void placeGirl(int slot, int girl) {
        week[slot] = girl;
        if (slot % 3 == 0) {
            if (!isSlotEmpty(slot + 1)) {
                setAdjacencyMatrix(girl, week[slot + 1], true);
            }
            if (!isSlotEmpty(slot + 2)) {
                setAdjacencyMatrix(girl, week[slot + 2], true);
            }
        } else if (slot % 3 == 1) {
            if (!isSlotEmpty(slot - 1)) {
                setAdjacencyMatrix(girl, week[slot - 1], true);
            }
            if (!isSlotEmpty(slot + 1)) {
                setAdjacencyMatrix(girl, week[slot + 1], true);
            }
        } else if (slot % 3 == 2) {
            setAdjacencyMatrix(girl, week[slot - 1], true);
            setAdjacencyMatrix(girl, week[slot - 2], true);
        }

    }

    public static void removeGirl(int slot, int girl) {
        week[slot] = -1;
        if (slot % 3 == 0) {
            if (!isSlotEmpty(slot + 1)) {
                setAdjacencyMatrix(girl, week[slot + 1], false);
            }
            if (!isSlotEmpty(slot + 2)) {
                setAdjacencyMatrix(girl, week[slot + 2], false);
            }
        } else if (slot % 3 == 1) {
            setAdjacencyMatrix(girl, week[slot - 1], false);
            if (!isSlotEmpty(slot + 1)) {
                setAdjacencyMatrix(girl, week[slot + 1], false);
            }
        } else if (slot % 3 == 2) {
            setAdjacencyMatrix(girl, week[slot - 1], false);
            setAdjacencyMatrix(girl, week[slot - 2], false);
        }
    }

    public static boolean isSlotEmpty(int slot) {
        return week[slot] == -1;
    }

    public static boolean isAlreadyInDay(int slot, int girl) {
        int day = slot / 15;
        for (int i = 15 * day; i < 15 * (day + 1); i++){
            if(week[i] == girl){
                return true;
            }
        }
        return false;
    }

    public static boolean areAdjacent(int g1, int g2) {
        if (g1 == -1 || g2 == -1) {
            return false;
        }
        return adjacencyMatrix[g1][g2];
    }

    public static void setAdjacencyMatrix(int g1, int g2, boolean tf) {
        adjacencyMatrix[g1][g2] = tf;
        adjacencyMatrix[g2][g1] = tf;
    }

    public static int getFirstGirl(int slot) {
        if (slot > 14 && slot % 3 != 0) {
            return 3;
        }
        return 0;
    }

    public static int getLastGirl(int slot) {
        if (slot % 15 == 0){
            return 0;
        } else if (slot % 15 == 3 && !isFirstDay(slot)){
            return 1;
        } else if (slot % 15 == 6 && !isFirstDay(slot)){
            return 2;
        } else if (slot % 3 < 2 && !isFirstDay(slot)) {
            return 11;
        } else {
            return 14;
        }
    }

    public static boolean isFirstDay(int slot) {
        return slot < 15;
    }

    public static void resetGirlsFromSlot(int start) {
        for (int i = start; i < NUMBER_OF_GIRLS; i++) {
            if (!isSlotEmpty(i)) {
                removeGirl(i, week[i]);
            }
        }
    }

    public static void startFrom(int slot) {
        int start = getFirstGirl(slot);
        int end = getLastGirl(slot);
        for (int girl = start; girl <= end; girl++) {
            if (isValid(slot, girl)) {
                if (!isSlotEmpty(slot)) {
                    removeGirl(slot, week[slot]);
                }
                placeGirl(slot, girl);
                if (slot == NUMBER_OF_GIRLS - 1) {
                    finish();
                } else {
                    startFrom(slot + 1);
                }
            }
        }
        resetGirlsFromSlot(slot + 1);
    }

    public static void initialize() {
        for (int i = 0; i < GIRLS_PER_DAY; i++) {
            setAdjacencyMatrix(i, i, true);
        }
        Arrays.fill(week, -1);
    }

    public static void printWeek() {
        String space;
        System.out.println("MON TUE WED THU FRI SAT SUN");
        System.out.println("--- --- --- --- --- --- ---");
        for (int i = 0; i < NUMBER_OF_GIRLS; i += 15) {
            space = i + 15 < NUMBER_OF_GIRLS ? " " : "";
            System.out.print(numToLetter(week[i]) + numToLetter(week[i + 1]) + numToLetter(week[i + 2]) + space);
            if (i >= 90 && i < NUMBER_OF_GIRLS - 3) {
                i = (i % 15) - 12;
                System.out.println();
            }
        }
        System.out.println();
    }

    public static String numToLetter(int i) {
        return i > -1 && i < 15 ? String.valueOf((char)(i + 'A')) : null;
    }

    public static void finish() {
        printWeek();
        System.exit(0);
    }

    public static void main(String[] args) {
        initialize();
        startFrom(0);
    }
}
