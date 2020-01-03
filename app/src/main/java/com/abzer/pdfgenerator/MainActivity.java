package com.abzer.pdfgenerator;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PdfPrint;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    String k="angeo";
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        savebtn = findViewById(R.id.savebtn);
        String webData = "<!DOCTYPE html><meta http-equiv=\"Content-Type\" " +
                "content=\"text/html; charset=utf-8\"><body>şlkasşldkasşdksaşdkaşskdşk" +
                "<p>gjhgjkg</p>" +k+ "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +
                "<p>gjhgjkg</p>" + "<p>gjhgjkg</p>" +

                "" +
                "<ul><li>123</li><li>123</li></ul>" +
                "<hr style='border-top: 2px dashed grey'>" +
                "</body></html>";


        webView.loadData(webData, "text/html", "UTF-8");

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    // Do something for lollipop and above versions
                    createWebPrintJob(webView);

                } else{
                    // do something for phones running an SDK before lollipop
                    createWebPrintJobinWeb(webView);
                }

            }
        });
    }

    private void createWebPrintJobinWeb(WebView webView) {

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createWebPrintJob(WebView webView) {
        String jobName = getString(R.string.app_name) + " Document";
        PrintAttributes attributes = new PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(new PrintAttributes.Resolution("pdf", "pdf", 600, 600))
                .setMinMargins(PrintAttributes.Margins.NO_MARGINS).build();
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/PDFTest/");
        PdfPrint pdfPrint = new PdfPrint(attributes);
        pdfPrint.print(webView.createPrintDocumentAdapter(jobName), path, "output_" + System.currentTimeMillis() + ".pdf");
    }

}

