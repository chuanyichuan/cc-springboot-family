package cc.kevinlu.ccspringbootstarterautoconfigurer.wt;

public class CcService {

    private CcProperties ccProperties;

    public void setCcProperties(CcProperties ccProperties) {
        this.ccProperties = ccProperties;
    }

    public String info() {
        return this.ccProperties.toString();
    }

}
