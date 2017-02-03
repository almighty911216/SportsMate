package git.comalmighty911216mustmeetnow.github.sportmate2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class EditProfileActivity extends AppCompatActivity implements DialogInterface.OnDismissListener{

    ImageView imageView;
    String sport;
    TextView sport_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sport_tv = (TextView)findViewById(R.id.sport_tv);
        imageView = (ImageView)findViewById(R.id.profile_img);
        DBHelper dbHelper = new DBHelper(getApplicationContext(),"user.db",null,1);
        String img = dbHelper.read_pic();
        Log.i("TAG",img);
        Glide.with(this).load(img).bitmapTransform(new CropCircleTransformation(new CustomBitmap())).into(imageView);
        ImageButton imageButton = (ImageButton)findViewById(R.id.plus_sports);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"asd",Toast.LENGTH_SHORT).show();
                sportsPlusDialog dialog = new sportsPlusDialog(EditProfileActivity.this);
                dialog.setOnDismissListener(EditProfileActivity.this);
                dialog.show();
            }
        });

        Button button = (Button)findViewById(R.id.btn_pro_com);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main_Intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Main_Intent);
                finish();
            }
        });
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        sportsPlusDialog dialog2 = (sportsPlusDialog) dialog ;
        sport = dialog2.getName();
        sport_tv.setText(sport);

    }


    public class CustomBitmap implements BitmapPool {
        @Override
        public boolean put(Bitmap bitmap) {
            return false;
        }

        @Override
        public int getMaxSize() {
            return 0;
        }

        @Override
        public void setSizeMultiplier(float sizeMultiplier) {

        }

        @Override
        public Bitmap get(int width, int height, Bitmap.Config config) {
            return null;
        }

        @Override
        public Bitmap getDirty(int width, int height, Bitmap.Config config) {
            return null;
        }

        @Override
        public void clearMemory() {

        }

        @Override
        public void trimMemory(int level) {

        }
    }

    public class sportsPlusDialog extends Dialog {

        String sport = null;
        CheckBox cb_soc;
        CheckBox cb_tac;
        CheckBox cb_bas;
        CheckBox cb_bask;
        CheckBox cb_ten;
        CheckBox cb_bad;

        public sportsPlusDialog(Context context) {
            super(context);

        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);

            setContentView(R.layout.plus_sports_dialog);
            //밑에 두개를 이용해서 다이얼로그 뒷배경을 투명으로 바꿀수있음!
            Window window = getWindow();
            window.setBackgroundDrawableResource(android.R.color.transparent);
            cb_soc = (CheckBox)findViewById(R.id.cb_soc);
            cb_bad = (CheckBox)findViewById(R.id.cb_bad);
            cb_tac = (CheckBox)findViewById(R.id.cb_tac);
            cb_ten = (CheckBox)findViewById(R.id.cb_ten);
            cb_bas = (CheckBox)findViewById(R.id.cb_bas);
            cb_bask = (CheckBox)findViewById(R.id.cb_bask);

            Button button = (Button) findViewById(R.id.btn_com);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sport = "";
                    if(cb_soc.isChecked()) sport += cb_soc.getText().toString() + " ";
                    if(cb_bad.isChecked()) sport += cb_bad.getText().toString() + " ";
                    if(cb_tac.isChecked()) sport += cb_tac.getText().toString() + " ";
                    if(cb_ten.isChecked()) sport += cb_ten.getText().toString() + " ";
                    if(cb_bas.isChecked()) sport += cb_bas.getText().toString() + " ";
                    if(cb_bask.isChecked()) sport += cb_bask.getText().toString() + " ";
                    dismiss();
                }
            });
        }

        public String getName() {
                return sport;
        }
        @Override
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {

        }
    }

}
