package registro;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sostmaky.tp1lab3.R;
import com.sostmaky.tp1lab3.databinding.ActivityRegistroBinding;
import com.sostmaky.tp1lab3.login.MainActivityViewModel;
import com.sostmaky.tp1lab3.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel viewModel;
    private ActivityRegistroBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        binding=ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        abrirGaleria();

//observer de la vista
        viewModel.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.tvDni.setText(usuario.getDni() + "");
                binding.tvApellido.setText(usuario.getApellido());
                binding.tvNombre.setText(usuario.getNombre());
                binding.tvEmail.setText(usuario.getMail());
                binding.tvContrasena.setText(usuario.getContrasena());

                // veo si imagen existente em el usuario
                if (usuario.getImagen() != null) {
                    binding.tvImagen.setImageURI(Uri.parse(usuario.getImagen()));
                }
            }
        });


//observer para ver la foto seleccionada
        viewModel.getUriMutable().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                if (uri != null) {
                    binding.tvImagen.setImageURI(uri);
                }
            }
        });

//boton guardar
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.guardarUsuario(binding.tvDni.getText().toString(),binding.tvApellido.getText().toString(),binding.tvNombre.getText().toString(),binding.tvEmail.getText().toString(),binding.tvContrasena.getText().toString());

            }
        });

//boton para buscar la imagen
        binding.btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });



        viewModel.leerusuario(getIntent());
    }
//para abrir la galeria
        private void abrirGaleria(){
            intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


            arl=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    viewModel.recibirFoto(result);


                }
            });
        }

}
