package lk.royalInstitute.studentManagementSystem.view.tm;

public class ProgramTM {
    private String programID;
    private String program;
    private String duration;
    private double fee;

    public ProgramTM() {
    }

    public ProgramTM(String programID, String program, String duration, double fee) {
        this.programID = programID;
        this.program = program;
        this.duration = duration;
        this.fee = fee;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
