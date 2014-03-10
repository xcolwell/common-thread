
package io.rainbow.commonthread.server.model;

import java.util.Collection;

import com.google.protobuf.ByteString;

// assumption: the entire common thread -
// including moment data - fits into 4GB of memory
public class CommonThread {

    // TODO max moments per device
    // TODO max devices

    public Collection<DeviceModel> getDevices() {
        throw new UnsupportedOperationException();
    }

    public Collection<MomentModel> getMoments() {
        throw new UnsupportedOperationException();
    }

    public DeviceModel updateDevice(DeviceModel device) {
        throw new UnsupportedOperationException();
    }

    public MomentModel updateMoment(MomentModel moment) {
        throw new UnsupportedOperationException();
    }

    public void removeDevice(ByteString id) {
        throw new UnsupportedOperationException();
    }

    public static interface Persistence {
        void addDevice(DeviceModel device);

        void removeDevice(ByteString id);

        void addMoment(MomentModel moment);

        void removeMoment(ByteString id);

        int getPendingOpCount();

        DeviceModel[] listDevices();

        MomentModel[] listMoments();
    }

    public static interface ChangeListener {
        void onDeviceChanged(DeviceModel device);

        void onMomentChanged(MomentModel moment);
    }
}
