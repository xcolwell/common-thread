# 02/23/1014: Server Planning

To keep the project straight-forward enough to execute in one month, the server will be built on Netty using a binary protocol with protocol buffers, with each device having full information about each other device ("one big chat room model"). The tentative request and response messages are below. The server will be built with Netty with all data stored in MySQL, running on a single VPS at (rainbow.io). The server will store audio visual moments in MySQL while keeping an interpolated common thread and device list in memory. 

Requests:

- Device adds the device with the device ID to the common thread at the given coords and orientation (optional). This may be resent when the device coords or orientation change. The device is dropped from the common thread if it fails to send an AddMoment in 180s.

- Moment sent every 60s from each device. Uploads a 10s audio-visual moment from the device.

Responses:

- Moment coordinates on the common thread, sent each time the coordinates change within the intereseted regions (set in ListenMoments). The coordinates do not change with time; the only change as the interpolated spline changes between devices - on device add, remove, or move (Device). The first Moment for each ID sent to a device will have the full audio visual payload. Can be marked removed.

- Device sent for each device in the common thread. This will contain the full spline interpolation data. Can be marked removed.


# Next steps

- Implement the server logic

- Implement the client to call Device and Moment. This will require the device to send position information and create moments using the device MediaRecorder.


