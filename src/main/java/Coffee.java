import java.net.InetAddress;
import java.util.Objects;

public class Coffee{

    boolean local;
    boolean remote;
    InetAddress address;

    public Coffee(boolean local, boolean remote, InetAddress address) {
        this.local = local;
        this.remote = remote;
        this.address = address;
    }

    boolean both(){
        return local && remote;
    }

    boolean one(){
        return local && remote;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
}