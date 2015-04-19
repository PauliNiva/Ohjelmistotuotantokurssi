package ohtu;

public class Submission {
    private String student_number;
    private int week;
    private int hours;
    private int[] tasks = new int[22];
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private boolean a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private boolean a10;
    private boolean a11;
    private boolean a12;
    private boolean a13;
    private boolean a14;
    private boolean a15;
    private boolean a16;
    private boolean a17;
    private boolean a18;
    private boolean a19;
    private boolean a20;
    private boolean a21;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setTasks() {
        if(a1)tasks[1] =1;
        if(a10)tasks[10] =1;
        if(a11)tasks[11] =1;
        if(a12)tasks[12] =1;
        if(a13)tasks[13] =1;
        if(a14)tasks[14] =1;
        if(a15)tasks[15] =1;
        if(a16)tasks[16] =1;
        if(a17)tasks[17] =1;
        if(a19)tasks[19] =1;
        if(a18)tasks[18] =1;
        if(a2)tasks[2] =1;
        if(a20)tasks[20] =1;
        if(a21)tasks[21] =1;
        if(a3)tasks[3] =1;
        if(a4)tasks[4] =1;
        if(a5)tasks[5] =1;
        if(a6)tasks[6] =1;
        if(a7)tasks[7] =1;
        if(a8)tasks[8] =1;
        if(a9)tasks[9] =1;
    }

    public boolean isA1() {
        return a1;
    }

    public boolean isA2() {
        return a2;
    }

    public boolean isA3() {
        return a3;
    }

    public boolean isA4() {
        return a4;
    }

    public boolean isA5() {
        return a5;
    }

    public boolean isA6() {
        return a6;
    }

    public boolean isA7() {
        return a7;
    }

    public boolean isA8() {
        return a8;
    }

    public boolean isA9() {
        return a9;
    }

    public boolean isA10() {
        return a10;
    }

    public boolean isA11() {
        return a11;
    }

    public boolean isA12() {
        return a12;
    }

    public boolean isA13() {
        return a13;
    }

    public boolean isA14() {
        return a14;
    }

    public boolean isA15() {
        return a15;
    }

    public boolean isA16() {
        return a16;
    }

    public boolean isA17() {
        return a17;
    }

    public boolean isA18() {
        return a18;
    }

    public boolean isA19() {
        return a19;
    }

    public boolean isA20() {
        return a20;
    }

    public boolean isA21() {
        return a21;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
        String pisteet = "";
        int yht = 0;
        setTasks();
        for (int i = 0; i < tasks.length; i++) {
            if(tasks[i]==1) {
                pisteet+= i+", ";
                yht++;
            }
        }
        return "Opiskelijanumero: "+student_number+"\n"+
                "viikko "+week+" pisteet: "+pisteet+"\n"+
                "yhteensä: "+yht+" tunnit: "+hours+"\n";
    }
    
}