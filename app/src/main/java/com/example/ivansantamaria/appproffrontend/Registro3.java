package com.example.ivansantamaria.appproffrontend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Rubenbros on 14/04/2017.
 */

public class Registro3 extends AppCompatActivity implements MultiSpinner.MultiSpinnerListener {

    private MultiSpinner horario;
    private MultiSpinner asignaturas;
    private MultiSpinner curso;
    private Spinner modo;
    private Facade facade = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facade = new Facade();
        setContentView(R.layout.activity_registro3_profesor);
        horario = (MultiSpinner) findViewById(R.id.horariosProfesorReg);
        asignaturas = (MultiSpinner) findViewById(R.id.asignaturasProfesorReg);
        curso = (MultiSpinner) findViewById(R.id.cursoProfesorReg);
        modo = (Spinner) findViewById(R.id.modalidadProfesorReg);
        populateFields();
        Button registro = (Button) findViewById(R.id.registerbutton);
        final Intent i = new Intent(this, Perfil_Profesor.class);
        registro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final int code = guardarEnBd();
                if (code == -1) startActivity(i);
                else error(code);
            }
        });


    }

    private int guardarEnBd() {
        //Comprobar campos
        CheckBox tyc = (CheckBox) findViewById(R.id.TyC);
        ArrayList<String> horariosProf = horario.getValues();
        ArrayList<String> asignaturasProf = asignaturas.getValues();
        if (!tyc.isEnabled()) return 8;
        else if (horariosProf.isEmpty()) return 1;
        else if (asignaturasProf.isEmpty()) return 2;
        //Guardar en base de datos
        return -1;
    }

    private void populateFields() {

        horario.setItems(facade.getHorariosDisponibles(), facade.getHorariosDisponibles(),
                null, "", this);

        asignaturas.setItems(facade.getAsignaturasDisponibles(), facade.getAsignaturasDisponibles(),
                null, "", this);

        curso.setItems(facade.getCursosDisponibles(), facade.getCursosDisponibles(),
                null, "", this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.row_spinner, facade.getModalidadesDisponibles());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modo.setAdapter(adapter);
        modo.setSelection(0);

    }

    private void error(int code) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        switch (code) {
            case 1:
                dlgAlert.setMessage("Debe seleccionar al menos un horario");
                break;
            case 2:
                dlgAlert.setMessage("Debe seleccionar al menos una asignatura");
                break;
            case 8:
                dlgAlert.setMessage("Acepte los terminos y condiciones");
                break;
        }
        dlgAlert.setTitle("Error...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
    }

    public void onItemsSelected(boolean[] selected) {
        //Esto hay que ponerlo pero a saber para que... seguiremos investigando
    }
}