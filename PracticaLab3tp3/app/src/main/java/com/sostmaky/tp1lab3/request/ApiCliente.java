package com.sostmaky.tp1lab3.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

import com.sostmaky.tp1lab3.model.Usuario;

import java.io.*;

public class ApiCliente {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }
    public static void guardarObjeto(Context context, Usuario usuario){

        File archivoObj = new File(context.getFilesDir(), "Usuarios.obj");

        // leo el objeto existente
        Usuario usuarioGuardado = null;
        if (archivoObj.exists()) {
            try {
                FileInputStream fis = new FileInputStream(archivoObj);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
                usuarioGuardado = (Usuario) ois.readObject();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                Toast.makeText(context, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
            }
        }
        // veo si el objeto tiene que ser actualizado o guardado
        if (usuarioGuardado == null || !usuario.equals(usuarioGuardado)) {
        try {
            FileOutputStream fos = new FileOutputStream(archivoObj, false);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(usuario);
            bos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(context,"error del archivo",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context,"error del entrada y salida de datos",Toast.LENGTH_SHORT).show();
        }

        }

    }


    public static void guardar(Context context, Usuario usuario){

        SharedPreferences sp=conectar(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putLong("dni",(usuario.getDni()));
        editor.putString("apellido",usuario.getApellido());
        editor.putString("nombre",usuario.getNombre());
        editor.putString("mail",usuario.getMail());
        editor.putString("contrasena",usuario.getContrasena());
        editor.putString("imagen",usuario.getImagen());
        editor.commit();
    }

    public static Usuario login(Context context,String email,String contrasena){

        Usuario usuario=null;

        SharedPreferences sp=conectar(context);
        long dni= sp.getLong("dni",-1);
        String apellido=sp.getString("apellido","-1");
        String nombre=sp.getString("nombre","-1");
        String mail=sp.getString("mail","-1");
        String contra=sp.getString("contrasena","-1");
        String imagen=sp.getString("imagen","-1");


        if (email.equals(mail) && contrasena.equals(contra)){
        usuario=new Usuario(dni,apellido,nombre,mail,contra,imagen);

        }
        return usuario;
    }

    public static Usuario loginObjeto(Context context,String email,String contrasena){

        Usuario usuario=null;
        File archivoObj = new File(context.getFilesDir(), "Usuarios.obj");
        try {
            FileInputStream fis = new FileInputStream(archivoObj);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Usuario usuarioGuardado=(Usuario) ois.readObject();

            if (email.equals(usuarioGuardado.getMail()) && contrasena.equals(usuarioGuardado.getContrasena())) {
                usuario = usuarioGuardado;
            }
            fis.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error de archivo obj",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context,"No hay mas datos obj",Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context,"No existe objeto de esta clase",Toast.LENGTH_SHORT).show();

        }

        return usuario;
    }


    public static Usuario leerDatos(Context context){
        SharedPreferences sp=conectar(context);
        int dni= sp.getInt("dni",-1);
        String apellido=sp.getString("apellido","-1");
        String nombre=sp.getString("nombre","-1");
        String email=sp.getString("email","-1");
        String contrasena=sp.getString("contrasena","-1");
        String imagen=sp.getString("imagen","-1");
        Usuario usuario=new Usuario(dni,apellido,nombre,email,contrasena,imagen);
        return usuario;
    }



}