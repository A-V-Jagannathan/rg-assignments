// Answer to singleton class and data encapsulation
public class Singleton {
    private static Singleton instance;
    private String configName;
    private int version;

    private Singleton() {
        this.configName = "DefaultConfig";
        this.version = 1;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        if (version > 0) {
            this.version = version;
        }
    }
}


