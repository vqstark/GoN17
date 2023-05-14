package com.example.gon17.activity.auth;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gon17.R;
import com.example.gon17.activity.home.HomeActivity;
import com.example.gon17.adapter.ImageAdapter;
import com.example.gon17.db.CommentDB;
import com.example.gon17.model.Comment;
import com.example.gon17.model.Food;
import com.example.gon17.model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RatingActivity extends AppCompatActivity implements View.OnClickListener{
    int REQUEST_CODE = 1;
    private static final int REQUEST_CODE_PICK_IMAGES = 102;
    private ImageView imgFood, img, imageView1, imageView2, imageView3;
    private Button bt;
    private RatingBar ratingBar;
    private EditText cmt;
//    private RecyclerView mRecyclerView;
//    private ImageAdapter mAdapter;
    private List<String> imageList;

    private User user;
    private Food food;

    private byte[] img1,img2,img3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đánh giá");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

        user = (User)getIntent().getSerializableExtra("user");
        food = (Food) getIntent().getSerializableExtra("food");

        img = findViewById(R.id.imgRating);
        imgFood = findViewById(R.id.imgFood);
        bt = findViewById(R.id.btGui);
        ratingBar = findViewById(R.id.rating_bar);
        cmt = findViewById(R.id.cmt);
        imageView1 = findViewById(R.id.image_view1);
        imageView2 = findViewById(R.id.image_view2);
        imageView3 = findViewById(R.id.image_view3);
        img.setOnClickListener(this);
        bt.setOnClickListener(this);

//        mRecyclerView = findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onClick(View view) {
        if(view==img){
            // Tạo intent để mở hộp thoại chọn tệp
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // cho phép chọn nhiều tệp
            startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), REQUEST_CODE_PICK_IMAGES);
        }

        if(view==bt){
            int rating = (int) ratingBar.getRating();
            String context = cmt.getText().toString();
            Comment c = new Comment(rating, context, user, food);
            CommentDB db = new CommentDB(this);
            long status = db.addComment(c, img1, img2, img3);
            if(status!=-1)
                Toast.makeText(this, "Thêm đánh giá thành công", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Không thể tạo đánh giá. Xin vui lòng thử lại", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            imageList = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                // Nếu người dùng chọn nhiều tệp
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri selectedImageUri = clipData.getItemAt(i).getUri();
                    try {
                        if(i==0){
                            img1 = uriToByteArray(selectedImageUri);
                            imageView1.setImageURI(selectedImageUri);
                        }
                        if(i==1){
                            img2 = uriToByteArray(selectedImageUri);
                            imageView2.setImageURI(selectedImageUri);
                        }
                        if(i==2){
                            img3 = uriToByteArray(selectedImageUri);
                            imageView3.setImageURI(selectedImageUri);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

//                    String imagePath = getRealPathFromUri(selectedImageUri); // hàm getRealPathFromUri() chuyển đổi Uri thành đường dẫn thực tế của tệp
//                    imageList.add(imagePath);
//                    mAdapter = new ImageAdapter(this, imageList);
//                    mRecyclerView.setAdapter(mAdapter);
                }

            } else {
                // Nếu người dùng chỉ chọn một tệp
                Uri selectedImageUri = data.getData();
                String imagePath = getRealPathFromUri(selectedImageUri);
                imageList.add(imagePath);
                imageView1.setImageURI(selectedImageUri);
//                mAdapter = new ImageAdapter(this, imageList);
//                mRecyclerView.setAdapter(mAdapter);
            }
        }

    }
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    public byte[] uriToByteArray(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }

        byteArrayOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Call account fragment
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user",this.user);
                intent.putExtra("fragment_name", "ORDER_FRAGMENT");
                startActivity(intent);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}