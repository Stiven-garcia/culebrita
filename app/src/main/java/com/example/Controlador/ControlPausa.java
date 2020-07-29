package com.example.Controlador;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Utiles.constantes;
import com.example.culebrita.MainActivity;
import com.example.culebrita.R;

public class ControlPausa {
    private MainActivity mainActivity;
    private ImageView pausa;
    public ControlPausa(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.pausa = this.mainActivity.findViewById(R.id.imageView);
        this.pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarDialogo();
            }
        });
    }
    public void generarDialogo(){
        this.mainActivity.dialogo();
        //this.mainActivity.dialogoPerder(0,0);
    }
}
