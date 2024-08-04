package fon.mas.novica.quarkus.model.dto.xp;

public class ExperienceInfo {
    private int count;

    public ExperienceInfo() {
    }

    public ExperienceInfo(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
