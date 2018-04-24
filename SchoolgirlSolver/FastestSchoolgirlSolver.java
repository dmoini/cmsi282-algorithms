
public class FastestSchoolgirlSolver {
    public static void schoolgirlSolver() {
        System.out.println("Sun    Mon    Tue    Wed    Thu    Fri    Sat");
        System.out.println("---    ---    ---    ---    ---    ---    ---");
        System.out.println("ABC    ADG    AEJ    AFO    AHK    AIM    ALN");
        System.out.println("DEF    BEH    BFL    BDM    BGN    BKO    BIJ");
        System.out.println("GHI    CJM    CHO    CGL    CFI    CEN    CDK");
        System.out.println("JKL    FKN    DIN    EIK    DJO    DHL    EGO");
        System.out.println("MNO    ILO    GKM    HJN    ELM    FGJ    FHM");
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        schoolgirlSolver();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println((double)totalTime / 1000000000.0 + " seconds");
    }
}
