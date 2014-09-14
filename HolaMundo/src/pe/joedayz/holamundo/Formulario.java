package pe.joedayz.holamundo;

import java.io.Serializable;

import pe.joedayz.holamundo.dao.AlumnoDAO;
import pe.joedayz.holamundo.modelo.Alumno;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Formulario extends Activity {

	private FormularioHelper formularioHelper;
	Alumno alumnoSeleccionado;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		
		Intent intent = getIntent();
		
		alumnoSeleccionado = (Alumno) intent.getSerializableExtra("alumnoSeleccionado"); 
		
		formularioHelper = new FormularioHelper(this);
		
		Button boton = (Button) findViewById(R.id.botonFormulario);	
		
		if(alumnoSeleccionado != null){
			boton.setText("Modificar");
			formularioHelper.colocarAlumnoEnFormulario(alumnoSeleccionado);
		}
		
		boton.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alumno alumno = formularioHelper.guardarAlumnoDeFormulario();
				AlumnoDAO dao = new AlumnoDAO(Formulario.this);
				if(alumnoSeleccionado == null){
					dao.guardar(alumno);
				}else {
					alumno.setId(alumnoSeleccionado.getId());
					dao.modificar(alumno);
				}
				
				dao.guardar(alumno);
				dao.close();
				
				
				finish();
				
			}
		});
		

		
	}

}
