package com.example.fom;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.loader.content.CursorLoader;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static androidx.core.content.FileProvider.getUriForFile;
import static java.security.AccessController.getContext;

public class DoneQ extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private Button startAgain;
    private StorageReference mStorageRef;
    private ProgressBar pb;
    private TextView msg;
    public String projectName;
    private int compressedCount=0;
    public int uploadCount=0;
    FFmpeg ffmpeg;
    private void loadFFMpegBinary() {
        try {
            if (ffmpeg == null) {
                Log.d(TAG, "ffmpeg : null");
                ffmpeg = FFmpeg.getInstance(this);
            }
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    //showUnsupportedExceptionDialog();
                }

                @Override
                public void onSuccess() {
                    Log.d(TAG, "ffmpeg : correct Loaded");
                }
            });
        } catch (FFmpegNotSupportedException e) {
            //showUnsupportedExceptionDialog();
        } catch (Exception e) {
            Log.d(TAG, "EXception not supported : " + e);
        }
    }

    private void execFFmpegBinary(final String[] command) {
        try {
            ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {
                @Override
                public void onFailure(String s) {
                    Log.d(TAG, "FAILED with output : " + s);
                    //System.out.println("failsaaaaaaa");
                }

                @Override
                public void onSuccess(String s) {
                    Log.d(TAG, "SUCCESS with output : " + s);
                    //Perform action on success
                    compressedCount++;
                    if(compressedCount==3){
                        //Data.getInstance().path1+="compressed.mp4";
                        //Data.getInstance().path2+="compressed.mp4";
                        //Data.getInstance().path3+="compressed.mp4";
                        uploadAll();
                       //System.out.println("okkkkkkkkkkkkkkkkkkkkkk");
                    }

                    //System.out.println("successaaaaaaa");
                }

                @Override
                public void onProgress(String s) {
                    Log.d(TAG, "progress : " + s);
                    //System.out.println("progressaaaaaaa");
                }

                @Override
                public void onStart() {
                    Log.d(TAG, "Started command : ffmpeg " + command);
                    //System.out.println("startsaaaaaaa");
                }

                @Override
                public void onFinish() {
                    Log.d(TAG, "Finished command : ffmpeg " + command);
                    //System.out.println("finishsaaaaaaa");

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            System.out.println("runningaaaaaaaaa");
        }
    }
    private void complete(){

        StorageReference mStorageRef= FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = mStorageRef.child(projectName + ".txt");
        String data=WordUtils.capitalize(Data.getInstance().name)+ "\n"
                +Data.getInstance().mail+"\n"
                +Data.getInstance().phone+"\n"+
                Data.getInstance().date+"\n"
                + StringUtils.capitalize(Data.getInstance().question1)+"\n"
                +StringUtils.capitalize(Data.getInstance().question2)+"\n"
                +StringUtils.capitalize(Data.getInstance().question3)+"\n";
        UploadTask lastTask=riversRef.putBytes(data.getBytes());

        lastTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                pb.setVisibility(View.GONE);
                msg.setText("We received your video, it will be sent to your Email soon");
            }
        });


    }
    @Override
    public void onBackPressed() {

    }
    public void uploadAll(){
        String path, fileName;


        for (int i = 1; i<=3; i++){

            if (i == 1){
                path = Data.getInstance().path1;

            }else if (i == 2){
                path = Data.getInstance().path2;
            } else {
                path = Data.getInstance().path3;
            }

            //System.out.println("Upload path: " + path);
            Uri videoUri =  Uri.parse(path);

            fileName = projectName + "-" + i + ".mp4";
            //System.out.println("Upload name: " + fileName);

            StorageReference riversRef = mStorageRef.child(fileName);
            InputStream stream = null;
            try {

                Uri videoUriFinal =  Uri.parse(path);
                String pathFinal = RealPathUtil.getRealPath(getApplicationContext(),videoUriFinal)+"compressed.mp4";
//                File imagePath = new File(getApplicationContext().getFilesDir(), "videos");
//                File newFile = new File(imagePath, pathFinal);
//                Uri contentUri = getUriForFile(getApplicationContext(), "com.mydomain.fileprovider", newFile);
//                DocumentFile a=DocumentFile.fromFile(new File(pathFinal, "remp"));
//                Uri b=a.getUri();
                File f=new File(pathFinal);
                //stream =getContentResolver().openInputStream(b);
                stream=new FileInputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert stream != null;
            UploadTask uploadTask=riversRef.putStream(stream);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    uploadCount++;
                    if(uploadCount==3){
                        complete();
                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
        String strDate = dateFormat.format(date);
        projectName=strDate +  "-" + Data.getInstance().name.replace(" ","");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_q);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        pb=(ProgressBar) findViewById(R.id.progressBar3);
        msg=findViewById(R.id.uploadmsg);


        startAgain = (Button) findViewById(R.id.newStart);

        startAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startAgain.setClickable(false);
                pb.setVisibility(View.VISIBLE);
                msg.setVisibility(View.VISIBLE);

                loadFFMpegBinary();
                Uri videoUri1 =  Uri.parse(Data.getInstance().path1);
                String path1 = RealPathUtil.getRealPath(getApplicationContext(),videoUri1);
                //String[] command1 = {"-i",path1,"-vf","scale=854x480","-r","15","-c:a", "copy",path1+"compressed.mp4"};
                String[] command1 = {"-i",path1,"-vf","scale=854:480:force_original_aspect_ratio=decrease,pad=854:480:(ow-iw)/2:(oh-ih)/2","-r","20","-c:a", "copy",path1+"compressed.mp4"};

                execFFmpegBinary(command1);
                Uri videoUri2 =  Uri.parse(Data.getInstance().path2);
                String path2 = RealPathUtil.getRealPath(getApplicationContext(),videoUri2);
                //String[] command2 =   {"-i",path2,"-r","15","-vf","scale=854x480","-c:a", "copy",path2+"compressed.mp4"};
                String[] command2 = {"-i",path2,"-vf","scale=854:480:force_original_aspect_ratio=decrease,pad=854:480:(ow-iw)/2:(oh-ih)/2","-r","20","-c:a", "copy",path2+"compressed.mp4"};
                execFFmpegBinary(command2);
                Uri videoUri3 =  Uri.parse(Data.getInstance().path3);
                String path3 = RealPathUtil.getRealPath(getApplicationContext(),videoUri3);

                String[] command3 = {"-i",path3,"-vf","scale=854:480:force_original_aspect_ratio=decrease,pad=854:480:(ow-iw)/2:(oh-ih)/2","-r","20","-c:a", "copy",path3+"compressed.mp4"};//String[] command3 =   {"-i",path3,"-vf","scale=854x480","-r","15","-c:a", "copy",path3+"compressed.mp4"};
                execFFmpegBinary(command3);




                //uploadAll();

            }
        });

    }
}
