package com.wjprogramer.flutter_background_1

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import io.flutter.app.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

class MainActivity : FlutterActivity() {
    private var forService: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(FlutterEngine(this))
        forService = Intent(this@MainActivity, MyService::class.java)
        MethodChannel(flutterView, "com.retroportalstudio.messages")
                .setMethodCallHandler(object : MethodChannel.MethodCallHandler {
                    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
                        if (methodCall.method.equals("startService")) {
                            startService()
                            result.success("Service Started")
                        }
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(forService)
    }

    private fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(forService)
        } else {
            startService(forService)
        }
    }
}
