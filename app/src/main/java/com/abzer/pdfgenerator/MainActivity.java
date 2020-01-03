package com.abzer.pdfgenerator;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        savebtn = findViewById(R.id.savebtn);
        String webData = "<!DOCTYPE html><head> <meta http-equiv=\"Content-Type\" " +
                "content=\"text/html; charset=utf-8\"> <html><body>şlkasşldkasşdksaşdkaşskdşk" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +

                "" +
                "<ul><li>123</li><li>123</li></ul>" +
                "</body></html>";


        webView.loadData(webData, "text/html", "UTF-8");

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWebPrintJob(webView);
            }
        });
    }

    private void createWebPrintJob(WebView webView) {

        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter =
                webView.createPrintDocumentAdapter();

        String jobName = getString(R.string.app_name) + " Print Test";

        if (printManager != null) {
            printManager.print(jobName, printAdapter,
                    new PrintAttributes.Builder().build());
        }
    }
}
