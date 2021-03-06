package com.naranjatradicionaldegandia.elias.robotdomotico.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.naranjatradicionaldegandia.elias.ambos.Imagen;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.naranjatradicionaldegandia.elias.ambos.Robot;
import com.naranjatradicionaldegandia.elias.robotdomotico.R;
import com.naranjatradicionaldegandia.elias.robotdomotico.ServicioOn;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View vista;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        actualizarImagen();
    vista = root;
        // DEFINICIONES <---------------------------------------------------------------------------

        // boton limpieza
        ImageButton limpieza = vista.findViewById(R.id.limpiezabtn);
        ImageButton limpiezaactivo =  vista.findViewById(R.id.limpiezabtnactivo);

        // boton vigilancia
        ImageButton vigilancia =  vista.findViewById(R.id.vigilanciabtn);
        ImageButton vigilanciaactivo =  vista.findViewById(R.id.vigilanciabtnactivo);

        // boton encender
        ImageButton onoff =  vista.findViewById(R.id.onoffbtn);
        ImageButton onoffactivo =  vista.findViewById(R.id.onoffbtnactivo);

        // estados
        TextView limpiando =  vista.findViewById(R.id.textolimpiando);
        TextView vigilando =  vista.findViewById(R.id.textovigilando);
        TextView limpiandovigilando =  vista.findViewById(R.id.textotodo);
        TextView recargando =  vista.findViewById(R.id.textorecargando);

        // Bateria
        //Button bateriallena =  vista.findViewById(R.id.bateriallena);
        //Button bateriacasillena = findViewById(R.id.bateriacasillena);
        //Button bateriamitad = findViewById(R.id.bateriamitad);
      //  Button bateriacasivacia =  vista.findViewById(R.id.bateriacasivacia);
        //Button bateriavacia = findViewById(R.id.bateriavacia);


        // VARIABLES <------------------------------------------------------------------------------
        final int[] contadorLimpieza = {1};
        final int[] contadorVigilancia = {1};
        final int[] contadorOnoff = {1};

        final boolean[] limpiar = {false};
        final boolean[] vigilar = {false};
        final boolean[] activado = {false};


        // LLAMADAS <-------------------------------------------------------------------------------

        //cargarToolbar();



        pulsarLimpieza(limpieza, limpiezaactivo, contadorLimpieza, limpiar, activado);

        pulsarVigilancia(vigilancia, vigilanciaactivo, contadorVigilancia, vigilar, activado);

        pulsarEncendido(onoff, onoffactivo, contadorOnoff, activado, limpiar, vigilar, limpiando, vigilando, limpiandovigilando);


        // onCreate
        return root;
    }


    //=========================================================>
// ---------------------------- FUNCIONES ------------------------>
    //=========================================================>

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // --------------------- Cargar toolbar --------------------------------------------------------
   /* void cargarToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();
//Establecemos el icono en la ActionBar
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Robot");
    } // ()
*/

    void intermitenciapocabateria (){


    }




    // ---------------------------------------------------------------------------------------------
    // --------------------- pulsar limpieza -------------------------------------------------------
    void pulsarLimpieza(final ImageButton limpieza, final ImageButton limpiezaactivo, final int[] contadorLimpieza, final boolean[] limpiar, final boolean[] activado){

        // ACCIONES AL CLICKAR BOTON LIMPIEZA <--------------------------------------------------
        limpieza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!activado[0]){
                    contadorLimpieza[0] = contadorLimpieza[0] + 1;
                    limpiar[0] = true;

                    limpieza.setVisibility(View.INVISIBLE);
                    limpiezaactivo.setVisibility(View.VISIBLE);
                }
            } // onClick
        });
        // ACCIONES AL CLICKAR BOTON LIMPIEZA ACTIVO <-------------------------------------------
        limpiezaactivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!activado[0]) {
                    contadorLimpieza[0] = contadorLimpieza[0] + 1;
                    limpiar[0] = false;

                    limpieza.setVisibility(View.VISIBLE);
                    limpiezaactivo.setVisibility(View.INVISIBLE);
                }
            }
        });

    } // ()

    // ---------------------------------------------------------------------------------------------
    // --------------------- Pulsar vigilancia -----------------------------------------------------
    void pulsarVigilancia(final ImageButton vigilancia, final ImageButton vigilanciaactivo, final int[] contadorVigilancia, final boolean[] vigilar, final boolean[] activado){

        // ACCIONES AL CLICKAR BOTON VIGILANCIA <--------------------------------------------------
        vigilancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!activado[0]) {
                    contadorVigilancia[0] = contadorVigilancia[0] + 1;
                    vigilar[0] = true;

                    vigilancia.setVisibility(View.INVISIBLE);
                    vigilanciaactivo.setVisibility(View.VISIBLE);
                }
            }
        });
        // ACCIONES AL CLICKAR BOTON VIGILANCIA ACTIVO <-------------------------------------------
        vigilanciaactivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!activado[0]) {
                    contadorVigilancia[0] = contadorVigilancia[0] + 1;
                    vigilar[0] = false;

                    vigilancia.setVisibility(View.VISIBLE);
                    vigilanciaactivo.setVisibility(View.INVISIBLE);

                }
            }
        });


    } // ()


    public void actualizarImagen(){

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {


            public void run() {
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
                final Task<QuerySnapshot> query = FirebaseFirestore.getInstance()
                        .collection("Grabaciones")
                        .orderBy("tiempo", Query.Direction.DESCENDING)
                        .limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(HomeFragment.this.isVisible()){
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("FIREBASE", document.getId() + " => " + document.getData());

                                        String url = document.getString("url");
                                        ImageView imagen = getActivity().findViewById(R.id.imgrp);

                                        if(HomeFragment.this.isVisible()) {
                                            Glide.with(getContext())
                                                    .load(url)

                                                    .into(imagen);

                                            db.collection("Grabaciones").document(document.getId())
                                                    .delete()
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d("FIREBASE", "DocumentSnapshot successfully deleted!");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w("FIREBASE", "Error deleting document", e);
                                                        }
                                                    });
                                        }

                                    }
                                } else {
                                    Log.d("FIREBASE", "Error al cargar imagenes: ", task.getException());
                                }
                            }


                            }
                        });

                handler.postDelayed(this, 100);
            }
        };
        handler.postDelayed(runnable, 200);

    }
    // ---------------------------------------------------------------------------------------------
    // --------------------- Pulsar encendido ------------------------------------------------------
    void pulsarEncendido (final ImageButton onoff, final ImageButton onoffactivo, final int[] contadorOnoff, final boolean[] activado, final boolean[] limpiar, final boolean[] vigilar, final TextView limpiando, final TextView vigilando, final TextView limpiandovigilando){

        // ACCIONES AL CLICKAR BOTON ON/OFF <--------------------------------------------------
        onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (limpiar[0] || vigilar[0]) {
                    contadorOnoff[0] = contadorOnoff[0] + 1;
                    activado[0] = true;

                    onoff.setVisibility(View.INVISIBLE);
                    onoffactivo.setVisibility(View.VISIBLE);

                    Robot.activarModoAutomatico();

                    getActivity().startService(new Intent(getContext(), ServicioOn.class));
                    // TEXTO QUE MUESTRA ESTADO <-------------
                    Task<QuerySnapshot> query = FirebaseFirestore.getInstance()
                            .collection("Grabaciones")
                            .orderBy("tiempo", Query.Direction.DESCENDING)
                            .limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d("FIREBASE", document.getId() + " => " + document.getData());
                                            String url = document.getString("url");
                                            ImageView imagen = getActivity().findViewById(R.id.imgrp);

                                            Glide.with(getContext())
                                                    .load(url)

                                                    .into(imagen);
                                        }
                                    } else {
                                        Log.d("FIREBASE", "Error getting documents: ", task.getException());
                                    }
                                }
                            });


                    if (limpiar[0] & !vigilar[0]) {

                        limpiando.setVisibility(View.VISIBLE);


                    } else if (!limpiar[0] && vigilar[0]) {

                        vigilando.setVisibility(View.VISIBLE);

                    } else if (limpiar[0] && vigilar[0]) {

                        limpiandovigilando.setVisibility(View.VISIBLE);

                    }
                    // PARA EL MODO RECARGA //
                /*
                if(recargando){
                    // texto recargando
                }
                */
                }
            }
        });
// ACCIONES AL CLICKAR BOTON ON/OFF ACTIVO <--------------------------------------------------
        onoffactivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contadorOnoff[0] = contadorOnoff[0] +1;
                activado[0] = false;

                onoff.setVisibility(View.VISIBLE);
                onoffactivo.setVisibility(View.INVISIBLE);

                getActivity().stopService(new Intent(getContext(), ServicioOn.class));


                limpiando.setVisibility(View.INVISIBLE);
                vigilando.setVisibility(View.INVISIBLE);
                limpiandovigilando.setVisibility(View.INVISIBLE);


            }
        });


    } // ()


    //=========================================================>
// ---------------------------- ANIMACIONES ------------------------>
    //=========================================================>
    // ---------------------------------------------------------------------------------------------
    // -------------------intermitencia poca bateria -----------------------------------------------




    // menu toolbar ---------------------------------------------------------------------

 // bool

}