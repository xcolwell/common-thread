
package io.rainbow.commonthread.server.model;

import io.rainbow.commonthread.protocolbuffers.MessageProtos.Device;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Moment;

import java.util.Collection;

import com.google.protobuf.ByteString;

// assumption: the entire common thread -
// including moment data - fits into 4GB of memory
public class CommonThread {

    // TODO max moments per device
    // TODO max devices

    public Collection<Device> getDevices() {
        throw new UnsupportedOperationException();
    }

    public Collection<Moment> getMoments() {
        throw new UnsupportedOperationException();
    }

    public Device updateDevice(Device device) {
        throw new UnsupportedOperationException();
    }

    public Moment updateMoment(Moment moment) {
        throw new UnsupportedOperationException();
    }

    public void removeDevice(Device device) {
        throw new UnsupportedOperationException();
    }

    public static interface Persistence {
        void addDevice(Device device);

        void removeDevice(ByteString id);

        void addMoment(Moment moment);

        void removeMoment(ByteString id);

        int getPendingOpCount();

        Device[] listDevices();

        Moment[] listMoments();
    }

    public static interface ChangeListener {
        void onDeviceChanged(Device device);

        void onMomentChanged(Moment moment);
    }
}
