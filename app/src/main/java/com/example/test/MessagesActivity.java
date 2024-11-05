package com.example.test;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // Kiểm tra quyền READ_SMS
        if (checkSelfPermission(android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            readMessages();
        } else {
            // Yêu cầu quyền nếu chưa có
            requestPermissions(new String[]{android.Manifest.permission.READ_SMS}, 103);
        }
    }

    private void readMessages() {
        List<String> messages = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                messages.add("Message: " + body);
            }
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        ListView messagesListView = findViewById(R.id.messagesListView);
        messagesListView.setAdapter(adapter);
    }

    // Xử lý kết quả yêu cầu quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 103) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readMessages();
            }
        }
    }
}
