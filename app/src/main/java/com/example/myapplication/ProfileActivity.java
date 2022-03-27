package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView;
    EditText editTextUSCID;
    Button button;
    Uri uriProfileImage;
    String profileImgUrl;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    private static final int CHOOSE_IMAGE = 193;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        editTextUSCID = (EditText) findViewById(R.id.editTextUSCID);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.buttonSave);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
        
        loadUserInfo();

    }

    private void loadUserInfo() {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String photoUrl,USCIDNumber;
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(imageView);

            }

            if (user.getDisplayName() != null) {
                editTextUSCID.setText(user.getDisplayName());
            }

        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageView) {
            displayImageSelect();
        }
        else if (view.getId() == R.id.buttonSave) {
            saveUserInfo();
        }

    }

    private void saveUserInfo() {
        String USCIDNumber = editTextUSCID.getText().toString();
        Pattern pattern = Pattern.compile("\\d+");
        if (USCIDNumber.isEmpty()) {
            editTextUSCID.setError("USC ID number required");
            editTextUSCID.requestFocus();
        }
        else if(!pattern.matcher(USCIDNumber).matches()) {
            editTextUSCID.setError("numeric characters only");
            editTextUSCID.requestFocus();
        }
        else if(USCIDNumber.length() != 10) {
            editTextUSCID.setError("USC ID number must have 10 digits");
            editTextUSCID.requestFocus();
        }
        else {
            FirebaseUser user = mAuth.getCurrentUser();

            if (user != null && profileImgUrl != null) {
                UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(USCIDNumber)
                        .setPhotoUri(Uri.parse(profileImgUrl))
                        .build();

                user.updateProfile(changeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ProfileActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                imageView.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference profileImgRef = FirebaseStorage.getInstance().getReference("pics/" + System.currentTimeMillis() + ".jpg");
        if (uriProfileImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileImgRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);

                    profileImgUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    private void displayImageSelect() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"), CHOOSE_IMAGE);

    }
}