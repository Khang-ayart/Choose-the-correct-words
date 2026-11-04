package com.choosethecorrectword.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Locale;

public class MainActivity extends Activity {
    private WebView web;
    private TextToSpeech tts;
    private volatile boolean ttsReady = false;
    private volatile boolean ttsInitDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web = new WebView(this);
        setContentView(web);

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(false);
        s.setMediaPlaybackRequiresUserGesture(false);

        // WebView của Android không có speechSynthesis, nên đọc bằng bộ TTS của máy
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                ttsReady = (status == TextToSpeech.SUCCESS);
                ttsInitDone = true;
                if (ttsReady) {
                    tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            notifyJs("start");
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            notifyJs("end");
                        }

                        @Override
                        public void onError(String utteranceId) {
                            notifyJs("error");
                        }
                    });
                }
            }
        });
        web.addJavascriptInterface(new Bridge(), "AndroidTTS");

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri u = request.getUrl();
                if ("file".equals(u.getScheme())) {
                    return false;
                }
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, u));
                } catch (Exception ignored) {
                }
                return true;
            }
        });

        web.loadUrl("file:///android_asset/index.html");
    }

    private void notifyJs(final String type) {
        web.post(new Runnable() {
            @Override
            public void run() {
                web.evaluateJavascript("window.__ttsEvent&&window.__ttsEvent('" + type + "')", null);
            }
        });
    }

    private class Bridge {
        @JavascriptInterface
        public String speak(String text, String lang, double rate) {
            if (!ttsInitDone) return "notready";
            if (!ttsReady) return "noengine";
            String result = "ok";
            boolean uk = "en-GB".equals(lang);
            int r = tts.setLanguage(uk ? Locale.UK : Locale.US);
            if (r < 0) {
                if (!uk) return "missing";
                if (tts.setLanguage(Locale.US) < 0) return "missing";
                result = "fallback";
            }
            tts.setSpeechRate((float) rate);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "ctcw");
            return result;
        }
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
