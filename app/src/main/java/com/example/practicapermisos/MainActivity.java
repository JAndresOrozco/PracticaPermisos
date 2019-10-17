package com.example.practicapermisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bb = getIntent().getExtras();
        Persona p = (Persona) bb.getSerializable("Persona");
        Toast.makeText(this, p.getNombre() + " " + p.getEdad(), Toast.LENGTH_SHORT).show();


        //Crear temporizador
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },4000);*/

        verifyPermission(perms, 100);
    }

    String[] perms = {
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.SEND_SMS

    };

    private Integer verifyPermission(String[] perms, Integer permsRequestCode) {
        int permsMissing = 0;

        for (int i = 0; i < perms.length; i++) {
            int perm = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                perm = checkSelfPermission(perms[i]);
            }

            if (perm == PackageManager.PERMISSION_DENIED) {
                permsMissing++;
            }
        }

        if (permsMissing != 0) {
            permsMissing = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(perms, permsRequestCode);
            }
            for (int i = 0; i < perms.length; i++) {
                int perm = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    perm = checkSelfPermission(perms[i]);
                }

                if (perm == PackageManager.PERMISSION_DENIED) {
                    permsMissing++;
                }
            }
        } else {
            Toast.makeText(this, "Tienes permisos", Toast.LENGTH_SHORT).show();
        }

        return permsMissing;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 100:
                if (verifyPermission(perms, 100) == 0) {
                    Toast.makeText(this, "Tienes permisos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent t = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8711060136"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    Activity#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for Activity#requestPermissions for more details.
                            return;
                        }
                    }
                    startActivity(t);
                }
                break;
            }

        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Btnnavegador:
                Intent t1 = new Intent(Intent.ACTION_VIEW);
                t1.setData(Uri.parse("http://www.google.com"));
                startActivity(t1);
                break;

            case R.id.Btnllamar:
                Intent t = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8711060136"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
                        return;
                    }
                }
                startActivity(t);
                break;


        }
    }
}
