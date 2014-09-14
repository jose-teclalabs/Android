package pe.joedayz.holamundo;

import pe.joedayz.holamundo.dao.AlumnoDAO;
import pe.joedayz.holamundo.modelo.Alumno;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class formularioEmpleado extends Activity {

	private FormularioHelper formularioHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		
		formularioHelper = new FormularioHelper(this);
		
		Button boton = (Button) findViewById(R.id.botonFormulario);	
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alumno alumno = formularioHelper.guardarAlumnoDeFormulario();
				
				AlumnoDAO dao = new AlumnoDAO(formularioEmpleado.this);
				dao.guardar(alumno);
				dao.close();
				
				finish();
				
			}
		});
		

		
	}

}
