package com.jenuvid.scrnmirroring.Gallery;

import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Adapter.AllPhotosFolderAdapter;
import com.jenuvid.scrnmirroring.databinding.ActivityAllPhotoFolderBinding;
import com.jenuvid.scrnmirroring.model.Model_images;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AllPhotoFolderActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Executor executor = Executors.newSingleThreadExecutor();
    boolean boolean_folder;
    AllPhotosFolderAdapter allPhotosFolderAdapter;
    ArrayList<Model_images> all_images = new ArrayList<>();
    Boolean isfirsttime = true;

    Activity activity;
    ActivityAllPhotoFolderBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityAllPhotoFolderBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;


        todo();
    }

    private void todo() {
        bottomAd();
        allPhotosFolderAdapter = new AllPhotosFolderAdapter(activity);
        x.photorecycler.setAdapter(allPhotosFolderAdapter);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                x.progressBar.setVisibility(View.VISIBLE);
                getAllFolers();
                handler.post(() -> {

                    if (!all_images.isEmpty()) {
                        x.notAvailable.setVisibility(View.GONE);
                        allPhotosFolderAdapter.setData(all_images);
                        x.progressBar.setVisibility(View.GONE);
                    } else {
                        x.notAvailable.setVisibility(View.VISIBLE);
                    }

                });
            }
        });


    }

    private void getAllFolers() {
        all_images.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = activity.getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            for (int i = 0; i < all_images.size(); i++) {
                String folderName = all_images.get(i).getStr_folder();
                if (folderName != null) {
                    if (all_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                        boolean_folder = true;
                        int_position = i;
                        break;
                    } else {
                        boolean_folder = false;
                    }
                }

            }
            if (boolean_folder) {
                ArrayList<String> al_path = new ArrayList<>(all_images.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                all_images.get(int_position).setAl_imagepath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Model_images obj_model = new Model_images(cursor.getString(column_index_folder_name), al_path);
                all_images.add(obj_model);

            }
        }

        cursor.close();
    }

    private void bottomAd() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            x.nestedScrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY > 700) {
                    if (sharedPreferencesHelper.getShowAdinApp()) {
                        if (sharedPreferencesHelper.getAdMobAdShow()) {
                            x.nativeAdLayout2.setVisibility(View.VISIBLE);
                            if (isfirsttime) {
                                AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout2, AdUtils.NativeType.NATIVE_BANNER);
                                isfirsttime = false;
                            }
                        } else {
                            x.nativeAdLayout2.setVisibility(View.GONE);
                        }

                    } else {
                        x.nativeAdLayout2.setVisibility(View.GONE);
                    }

                } else {
                    x.nativeAdLayout2.setVisibility(View.GONE);
                }

            });
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);
    }

    @Override
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                AllPhotoFolderActivity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);

    }
}