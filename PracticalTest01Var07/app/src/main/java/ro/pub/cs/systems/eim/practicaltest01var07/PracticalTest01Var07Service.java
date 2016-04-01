package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Service;
        import android.content.Intent;
        import android.os.IBinder;

public class PracticalTest01Var07Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        String group = intent.getStringExtra("group");

        processingThread = new ProcessingThread(this, name, group);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}

