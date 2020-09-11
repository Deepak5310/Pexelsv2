package com.example.pexels;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.Objects;

public class FullScreenImage extends AppCompatActivity {

    String originalUrl;
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = getIntent();
        originalUrl = intent.getStringExtra("originalUrl");

        photoView = findViewById(R.id.photo_view);
        Glide.with(this).load(originalUrl).into(photoView);
    }

    public void DownloadEvent(View view) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(originalUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setDestinationInExternalPublicDir("/Pexels Downloads", uri.getLastPathSegment());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
        Toast.makeText(this, "Downloading Started", Toast.LENGTH_SHORT).show();
    }
}