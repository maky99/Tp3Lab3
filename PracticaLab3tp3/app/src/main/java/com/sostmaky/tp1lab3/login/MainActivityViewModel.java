package com.sostmaky.tp1lab3.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.sostmaky.tp1lab3.model.Usuario;
import com.sostmaky.tp1lab3.request.ApiCliente;

import java.io.Serializable;

import registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void mostarFormu() {

        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //leo los datos de la pantalla de usuario y contraseña

    public void leer(String email,String contrasena ){
        Usuario usuLeido=ApiCliente.loginObjeto(context,email,contrasena);

        if(usuLeido != null) {
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("ingreso",false);
            Bundle bundle = new Bundle();
            bundle.putSerializable("usuario", usuLeido);
            intent.putExtra("datos", bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else{
        Toast.makeText(context,"Usuario no encontrado ",Toast.LENGTH_SHORT).show();

    }

    }


}
