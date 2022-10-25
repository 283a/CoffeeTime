import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.InetAddress;

public class Coffee{

    private boolean local;
    private boolean remote;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Coffee(boolean local, boolean remote) {
        this.local = local;
        this.remote = remote;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    boolean both(){
        return local && remote;
    }

    boolean one(){
        return local || remote;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "local", this.local, local);
        this.local = local;
        pcs.firePropertyChange(evt);
    }

    public boolean isRemote() {
        return remote;
    }


    public void setRemote(boolean remote) {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "remote", this.remote, remote);
        this.remote = remote;
        pcs.firePropertyChange(evt);
    }

}