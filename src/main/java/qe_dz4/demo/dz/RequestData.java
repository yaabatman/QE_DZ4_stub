package qe_dz4.demo.dz;

public class RequestData {
    private final String name;
    private final String phone;

    public RequestData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}