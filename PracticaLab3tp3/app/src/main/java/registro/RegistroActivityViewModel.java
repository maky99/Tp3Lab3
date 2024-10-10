package registro;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sostmaky.tp1lab3.login.MainActivity;
import com.sostmaky.tp1lab3.model.Usuario;
import com.sostmaky.tp1lab3.request.ApiCliente;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario>MtUsus;
    private MutableLiveData<Uri>uriMutableLiveData;
    private Uri uri;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getMUsuario() {
        if (MtUsus == null) {
            MtUsus = new MutableLiveData<>();
        }
        return MtUsus;
    }

    public LiveData<Uri> getUriMutable() {
        if (uriMutableLiveData == null) {
            uriMutableLiveData = new MutableLiveData<>();
        }
        return uriMutableLiveData;
    }

    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                uri = data.getData();
                uriMutableLiveData.setValue(uri);
            }
        }
    }



    public void leerusuario(Intent intent){
        Bundle bundle= intent.getBundleExtra("datos");
        if (bundle!=null){
            Usuario usuario=(Usuario)bundle.getSerializable("usuario");
            if (usuario!=null){
                MtUsus.setValue(usuario);
            }
        }
    }


    public void guardarUsuario(String dni, String apellido, String nombre, String mail, String contrasena) {
        long dn = Long.parseLong(dni);

        // Veo si uri es nulo
        String imagenUri;
        if (uri != null) {
            imagenUri = uri.toString(); // Si se seleccionó una imagen usao URI
        } else {
            Usuario usuarioActual = MtUsus.getValue(); // usuario actual
            if (usuarioActual != null && usuarioActual.getImagen() != null) {
                imagenUri = usuarioActual.getImagen(); // mantengo la imagen actual
            } else {
                imagenUri = "default_image_uri"; // imagen por defecto si no hay imagen
            }
        }

        // creo el objeto con la URI de la imagen
        Usuario usuario = new Usuario(dn, apellido, nombre, mail, contrasena, imagenUri);
        ApiCliente.guardarObjeto(context, usuario);

        Toast.makeText(context, "Usuario guardado", Toast.LENGTH_SHORT).show();

        // vuelvo a la pantalla principal
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }






}
