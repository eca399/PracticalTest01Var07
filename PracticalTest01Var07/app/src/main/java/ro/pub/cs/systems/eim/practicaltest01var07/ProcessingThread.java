package ro.pub.cs.systems.eim.practicaltest01var07;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private String[] actionTypes = {"ALARM_ACTION"};
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private String name;
    private String group;

    public ProcessingThread(Context context, String name, String group) {
        this.context = context;
        this.name = name;
        this.group = group;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage(name);
            sleep();
            sendMessage(group);
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage(String message) {
        Intent intent = new Intent();
        intent.setAction(actionTypes[random.nextInt(actionTypes.length)]);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + message);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(50000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}

